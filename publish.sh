#!/bin/bash
set -e

# --- 1. Token Retrieval (rbw fields) ---
echo "Fetching tokens from Bitwarden..."
if ! rbw ls > /dev/null 2>&1; then
    echo "rbw is locked. Please run 'rbw unlock' first."
    exit 1
fi

export MODRINTH_TOKEN=$(rbw get "tokens" --field "Modrinth")
export CODEBERG_TOKEN=$(rbw get "tokens" --field "Codeberg")

# --- 2. Extract Metadata ---
VERSION=$(grep "mod_version" gradle.properties | cut -d'=' -f2 | xargs)
TAG_NAME="v$VERSION"
OWNER="Maganoos"
REPO="endium-enhanced"

# --- 3. Build & Publish ---
chmod +x gradlew
./gradlew build modrinth

# --- 4. Forgejo / Codeberg Release ---
echo "Creating Codeberg release $TAG_NAME..."

JSON_PAYLOAD=$(python3 -c "import json, sys;
changelog = sys.stdin.read();
print(json.dumps({
    'tag_name': '$TAG_NAME',
    'name': '$TAG_NAME',
    'body': changelog,
    'draft': False,
    'prerelease': False
}))" < CHANGELOG.md)

RESPONSE=$(curl -s -X POST "https://codeberg.org/api/v1/repos/$OWNER/$REPO/releases" \
    -H "Authorization: token $CODEBERG_TOKEN" \
    -H "Content-Type: application/json" \
    -d "$JSON_PAYLOAD")

RELEASE_ID=$(echo "$RESPONSE" | jq -r '.id // empty')

if [[ -z "$RELEASE_ID" || "$RELEASE_ID" == "null" ]]; then
    echo "Release creation didn't return an ID. Checking for existing tag..."
    RELEASE_ID=$(curl -s -H "Authorization: token $CODEBERG_TOKEN" \
        "https://codeberg.org/api/v1/repos/$OWNER/$REPO/releases/tags/$TAG_NAME" | jq -r '.id // empty')
fi

# --- 5. Asset Upload ---
if [[ -n "$RELEASE_ID" && "$RELEASE_ID" != "null" ]]; then
    echo "Release ID: $RELEASE_ID. Uploading assets..."

    for jar in ./build/libs/*-"$VERSION".jar; do
        [[ -e "$jar" ]] || continue
        filename=$(basename "$jar")

        echo "Uploading $filename..."
        curl -s -X POST \
            "https://codeberg.org/api/v1/repos/$OWNER/$REPO/releases/$RELEASE_ID/assets?name=$filename" \
            -H "Authorization: token $CODEBERG_TOKEN" \
            -H "Content-Type: application/octet-stream" \
            --upload-file "$jar"
    done

    echo "Release successfully published to Codeberg."
else
    echo "Error: Could not determine Release ID."
    echo "Full API Response: $RESPONSE"
    exit 1
fi
