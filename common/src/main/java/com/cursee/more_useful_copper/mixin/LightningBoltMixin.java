package com.cursee.more_useful_copper.mixin;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.block.GardenStakeBlock;
import com.cursee.more_useful_copper.impl.common.function.LightningBoltEvents;
import com.cursee.more_useful_copper.impl.common.registry.ModBlocks;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin {

  @Shadow
  private int life;

  @Shadow
  protected abstract BlockPos getStrikePosition();

  @Inject(at = @At("HEAD"), method = "tick")
  private void more_useful_copper$tick(CallbackInfo ci) {

    LightningBolt self = (LightningBolt) (Object) this;

    LightningBoltEvents.onLightningTick(self, this.life, this.getStrikePosition());
  }
}
