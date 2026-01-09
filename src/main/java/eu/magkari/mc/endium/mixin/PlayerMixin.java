package eu.magkari.mc.endium.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import eu.magkari.mc.endium.item.EndiumItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow
    protected abstract boolean isEquipped(Item item);

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isEquipped(Lnet/minecraft/world/item/Item;)Z"))
    private boolean wrapEquipped(Player instance, Item item, Operation<Boolean> original) {
        return this.isEquipped(EndiumItems.ENDIUM_TURTLE_HELMET) || original.call(instance, item);
    }
}
