package eu.magkari.mc.endium.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import eu.magkari.mc.endium.item.EndiumItems;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    @Shadow
    @Nullable
    public abstract ItemStack getWeaponItem();

    @WrapOperation(method = "onHitEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;baseDamage:D", opcode = Opcodes.GETFIELD))
    private double wrapDamage(AbstractArrow instance, Operation<Double> original) {
        var originalD = original.call(instance);
        return instance.getWeaponItem() != null && instance.getWeaponItem().is(EndiumItems.ENDIUM_BOW) ? originalD * 1.2 : originalD ;
    }

    @Definition(id = "getType", method = "Lnet/minecraft/world/entity/Entity;getType()Lnet/minecraft/world/entity/EntityType;")
    @Definition(id = "ENDERMAN", field = "Lnet/minecraft/world/entity/EntityType;ENDERMAN:Lnet/minecraft/world/entity/EntityType;")
    @Expression("?.getType() == ENDERMAN")
    @ModifyExpressionValue(method = "onHitEntity", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private boolean imNotAnEndermanISwear(boolean original) {
        return (this.getWeaponItem() == null || !this.getWeaponItem().is(EndiumItems.ENDIUM_BOW)) && original;
    }
}
