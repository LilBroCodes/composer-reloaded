package org.lilbrocodes.composer_reloaded.internal.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import org.lilbrocodes.composer_reloaded.internal.client.render.block_entity.PlushBlockEntityRenderer;
import org.lilbrocodes.composer_reloaded.internal.networking.ClearOverlaysPayload;
import org.lilbrocodes.composer_reloaded.internal.networking.ClearToastsPayload;
import org.lilbrocodes.composer_reloaded.internal.networking.ShowOverlayPayload;
import org.lilbrocodes.composer_reloaded.internal.networking.TriggerToastPayload;
import org.lilbrocodes.composer_reloaded.internal.registry.ModBlockEntities;
import org.lilbrocodes.composer_reloaded.internal.registry.ModBlocks;

public class ComposerReloadedClient implements ClientModInitializer {
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
