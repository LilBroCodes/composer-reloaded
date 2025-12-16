package org.lilbrocodes.composer_reloaded.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import org.lilbrocodes.composer_reloaded.client.render.block_entity.PlushBlockEntityRenderer;
import org.lilbrocodes.composer_reloaded.common.networking.ClearToastsPayload;
import org.lilbrocodes.composer_reloaded.common.networking.NotifyToastPayload;
import org.lilbrocodes.composer_reloaded.common.networking.SimpleToastPayload;
import org.lilbrocodes.composer_reloaded.common.registry.ComposerBlockEntities;
import org.lilbrocodes.composer_reloaded.common.registry.ComposerBlocks;

public class ComposerReloadedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ComposerBlocks.PLUSH, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(ComposerBlockEntities.PLUSH, PlushBlockEntityRenderer::new);

        ClearToastsPayload.registerHandler();
        NotifyToastPayload.registerHandler();
        SimpleToastPayload.registerHandler();
    }
}
