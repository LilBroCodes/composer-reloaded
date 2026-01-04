package com.codex.composer.internal.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import com.codex.composer.internal.client.render.block_entity.PlushBlockEntityRenderer;
import com.codex.composer.internal.networking.ClearOverlaysPayload;
import com.codex.composer.internal.networking.ClearToastsPayload;
import com.codex.composer.internal.networking.ShowOverlayPayload;
import com.codex.composer.internal.networking.TriggerToastPayload;
import com.codex.composer.internal.registry.ModBlockEntities;
import com.codex.composer.internal.registry.ModBlocks;

public class ComposerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLUSH, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(ModBlockEntities.PLUSH, PlushBlockEntityRenderer::new);

        ClearToastsPayload.registerHandler();
        ClearOverlaysPayload.registerHandler();
        TriggerToastPayload.registerHandler();
        ShowOverlayPayload.registerHandler();
    }
}
