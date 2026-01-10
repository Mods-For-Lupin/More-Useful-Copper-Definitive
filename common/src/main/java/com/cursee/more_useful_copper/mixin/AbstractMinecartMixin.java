package com.cursee.more_useful_copper.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// @Mixin(AbstractMinecart.class)
public class AbstractMinecartMixin {

//  @Inject(method = "moveAlongTrack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getDeltaMovement()Lnet/minecraft/world/phys/Vec3;"), locals = LocalCapture.CAPTURE_FAILHARD)
//  private void more_useful_copper$moveAlongTrack$HEAD(BlockPos pos, BlockState state, CallbackInfo ci, double d0, double d1, double d2, Vec3 vec3, boolean flag, boolean flag1, double d3) {
//
//  }

//  @Inject(method = "moveAlongTrack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/AbstractMinecart;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", ordinal = 9))
//  private void more_useful_copper$moveAlongTrack(BlockPos pos, BlockState state, CallbackInfo ci) {
//
//    AbstractMinecart cart = (AbstractMinecart) (Object) this;
//
//    if (state.is(Blocks.POWERED_RAIL) && state.getValue(PoweredRailBlock.POWERED)) {
////      var m = cart.getDeltaMovement();
////      m.multiply(2, 2, 2);
//      cart.setDeltaMovement(cart.getDeltaMovement().multiply(4, 1, 4));
//    }
//  }

}
