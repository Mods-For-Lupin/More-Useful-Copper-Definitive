package com.cursee.more_useful_copper.platform;

import com.cursee.more_useful_copper.platform.services.IPlatformHelper;
import java.nio.file.Path;
import java.util.function.BiFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.Builder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public Path getGameDirectory() {

        return FMLLoader.getGamePath();
    }

    @Override
    public <T extends BlockEntity> BlockEntityType.Builder<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> constructor, Block... validBlocks) {

        return BlockEntityType.Builder.of(constructor::apply, validBlocks);
    }

    @Override
    public <T extends Entity> EntityType.Builder<T> createEntityType(BiFunction<EntityType<T>, Level, T> constructor, MobCategory mobCategory) {

        return EntityType.Builder.<T>of(constructor::apply, mobCategory);
    }

    @Override
    public <T extends AbstractContainerMenu> MenuType<T> createMenuType(BiFunction<Integer, Inventory, T> constructor, FeatureFlagSet requiredFeatures) {

        return new MenuType<T>(constructor::apply, requiredFeatures);
    }

    @Override
    public Builder createCreativeTabBuilder() {

        return CreativeModeTab.builder();
    }

    @Override
    public boolean isClientSide() {
        return FMLLoader.getDist().isClient();
    }
}