package eu.magkari.mc.endium.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import eu.magkari.mc.endium.item.EndiumItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@WrapMethod(method = "getFishingLuckBonus")
	private static int moreLuckBonus(ServerLevel serverLevel, ItemStack itemStack, Entity entity, Operation<Integer> original) {
        var luck = original.call(serverLevel, itemStack, entity);
        return itemStack.is(EndiumItems.ENDIUM_ROD) ? luck + 2 : luck;
    }
}