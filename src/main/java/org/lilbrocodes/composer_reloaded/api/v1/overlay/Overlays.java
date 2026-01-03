package org.lilbrocodes.composer_reloaded.api.v1.overlay;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.*;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.v1.overlay.impl.AnimatedOverlay;
import org.lilbrocodes.composer_reloaded.api.v1.overlay.impl.Overlay;
import org.lilbrocodes.composer_reloaded.api.v1.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.PacketSerializer;
import org.lilbrocodes.composer_reloaded.api.v1.util.render.Opacitator;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.networking.ShowOverlayPayload;
import net.minecraft.text.Text;

//? if minecraft: >=1.21
import net.minecraft.client.render.RenderTickCounter;

//? if minecraft: >=1.21.3
import net.minecraft.client.render.RenderLayer;

public class Overlays {
    public static <T extends Overlay> void send(ServerPlayerEntity player, T overlay) {
        ServerPlayNetworking.send(player, new ShowOverlayPayload<>(overlay));
    }

    public static void sendTextured(ServerPlayerEntity player, Identifier texture, Overlay.Anchor anchor, AnimatedOverlay.Animation animation, double scale, int duration) {
        send(player, new TextureOverlay(texture, anchor, animation,  scale, duration, false));
    }

    public static void sendTextured(ServerPlayerEntity player, Identifier texture, Overlay.Anchor anchor, AnimatedOverlay.Animation animation, int duration) {
        send(player, new TextureOverlay(texture, anchor, animation,  1, duration, false));
    }

    public static void sendTextured(ServerPlayerEntity player, Identifier texture, Overlay.Anchor anchor, AnimatedOverlay.Animation animation, double scale, int enter, int hold, int exit) {
        send(player, new TextureOverlay(texture, anchor, animation,  scale, enter, hold, exit, false));
    }

    public static void sendTextured(ServerPlayerEntity player, Identifier texture, Overlay.Anchor anchor, AnimatedOverlay.Animation animation, int enter, int hold, int exit) {
        send(player, new TextureOverlay(texture, anchor, animation,  1, enter, hold, exit, false));
    }

    public static void sendText(ServerPlayerEntity player, Overlay.Anchor anchor, AnimatedOverlay.Animation animation, String text, int color, double scale, boolean shadow, int enter, int hold, int exit) {
        send(player, new TextOverlay(anchor, animation,  text, color, scale, shadow, enter, hold, exit));
    }

    public static void sendText(ServerPlayerEntity player, Overlay.Anchor anchor, AnimatedOverlay.Animation animation, String text, int color, boolean shadow, int enter, int hold, int exit) {
        send(player, new TextOverlay(anchor, animation,  text, color, 1, shadow, enter, hold, exit));
    }

    public static void sendText(ServerPlayerEntity player, Overlay.Anchor anchor, AnimatedOverlay.Animation animation, String text, int color, double scale, boolean shadow, int duration) {
        send(player, new TextOverlay(anchor, animation,  text, color, scale, shadow, duration));
    }

    public static void sendText(ServerPlayerEntity player, Overlay.Anchor anchor, AnimatedOverlay.Animation animation, String text, int color, boolean shadow, int duration) {
        send(player, new TextOverlay(anchor, animation,  text, color, 1, shadow, duration));
    }

    public static class TextureOverlay extends AnimatedOverlay {
        private static final Identifier ID = ComposerReloaded.identify("textured");
        private final Identifier texture;
        private final Double scale;
        private Vec2 size;
        private boolean removed = false;
        private final boolean shouldLoadTextures;

        private TextureOverlay(Identifier texture, Anchor anchor, AnimatedOverlay.Animation animation, double scale, int duration, boolean shouldLoadTextures) {
            super(anchor, animation, duration);
            this.texture = texture;
            this.scale = scale;
            this.shouldLoadTextures = shouldLoadTextures;
            init();
        }

        private TextureOverlay(Identifier texture, Anchor anchor, AnimatedOverlay.Animation animation, double scale, int enter, int hold, int exit, boolean shouldLoadTextures) {
            super(anchor, animation, enter, hold, exit);
            this.texture = texture;
            this.scale = scale;
            this.shouldLoadTextures = shouldLoadTextures;
        }

        private void remove() {
            this.removed = true;
        }

        @Override
        protected void init() {
            if (!shouldLoadTextures) return;

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;

            AbstractTexture tex = client.getTextureManager().getTexture(texture);
            if (tex == null) {
                client.player.sendMessage(Text.literal("§cInvalid texture for overlay: " + texture), false);
                remove();
                return;
            }

            Vec2 shape;
            try {
                shape = loadSize(tex);
            } catch (Exception e) {
                client.player.sendMessage(Text.literal("§cFailed to load overlay texture: " + texture), false);
                remove();
                return;
            }

            size = shape.mul(scale);
        }

        @Override
        protected void render(DrawContext context,/*? if minecraft: <=1.20.6 {*//*float*//*? } else {*/RenderTickCounter/*? }*/ f, int x, int y) {
            Opacitator.drawWithOpacity(
                    getOpacity(f),
                    //? if minecraft: <=1.20.6 {
                    /*(ignored, ignored1) -> context.drawTexture(
                            texture,
                            x, y,
                            0, 0,
                            size.x(), size.y(),
                            size.x(), size.y()
                    )
                    *///? } else if minecraft: <=1.21 {
                    /*(alpha, ignored) -> context.drawTexturedQuad(
                            texture,
                            x, x + getSize().x(),
                            y, y + getSize().y(),
                            100,
                            0f, 1f,
                            0f, 1f,
                            1f, 1f, 1f, alpha
                    )
                    *///? } else {
                    (ignored, alpha) -> context.drawTexturedQuad(
                            RenderLayer::getGuiTextured,
                            texture,
                            x, x + getSize().x(),
                            y, y + getSize().y(),
                            0f, 1f,
                            0f, 1f,
                            (alpha << 24) | 0x00FFFFFF
                    )
                    //? }
            );
        }

        @Override
        protected Vec2 getPadding() {
            return new Vec2(10, 10);
        }

        @Override
        protected Vec2 getSize() {
            return size;
        }

        private Vec2 loadSize(AbstractTexture tex) throws Exception {
            if (tex instanceof NativeImageBackedTexture nat) {
                NativeImage image = nat.getImage();
                if (image == null) {
                    throw new Exception("NativeImageBackedTexture image is null");
                }
                return new Vec2(image.getWidth(), image.getHeight());
            }

            if (tex instanceof ResourceTexture res) {
                //? if minecraft: <=1.21.3 {
                /*ResourceTexture.TextureData data = res.loadTextureData(MinecraftClient.getInstance().getResourceManager());

                if (data == null || data.image == null) {
                    throw new Exception("ResourceTexture failed to load TextureData");
                }

                return new Vec2(data.image.getWidth(), data.image.getHeight());
                *///? } else {
                TextureContents contents = res.loadContents(MinecraftClient.getInstance().getResourceManager());

                if (contents == null || contents.image() == null) {
                    throw new Exception("ResourceTexture failed to load TextureContents");
                }

                return new Vec2(contents.image().getWidth(), contents.image().getHeight());
                //? }
            }

            throw new Exception("Unsupported AbstractTexture type: " + tex.getClass().getName());
        }

        @Override
        public boolean shouldRemove() {
            return super.shouldRemove() || removed;
        }

        @Override
        public Identifier getId() {
            return ID;
        }

        public static class Serializer implements PacketSerializer<TextureOverlay> {
            @Override
            public void write(TextureOverlay object, PacketByteBuf buf) {
                buf.writeIdentifier(object.texture);
                buf.writeEnumConstant(object.anchor);
                buf.writeDouble(object.scale);
                object.writeAnimation(buf);
            }

            @Override
            public TextureOverlay read(PacketByteBuf buf) {
                return new TextureOverlay(
                        buf.readIdentifier(),
                        buf.readEnumConstant(Overlay.Anchor.class),
                        null,
                        buf.readDouble(),
                        3,
                        true
                ).readAnimation(buf);
            }
        }
    }

    public static class TextOverlay extends AnimatedOverlay {
        private static final Identifier ID = ComposerReloaded.identify("text");
        private final String text;
        private final int color;
        private final double scale;
        private final boolean shadow;

        private TextOverlay(Anchor anchor, Animation animation, String text, int color, double scale, boolean shadow, int duration) {
            super(anchor, animation, duration);
            this.text = text;
            this.color = color;
            this.scale = scale;
            this.shadow = shadow;
        }

        private TextOverlay(Anchor anchor, Animation animation, String text, int color, double scale, boolean shadow, int enter, int hold, int exit) {
            super(anchor, animation, enter, hold, exit);
            this.text = text;
            this.color = color;
            this.scale = scale;
            this.shadow = shadow;
        }

        @Override
        protected void render(DrawContext context, /*? if minecraft: <=1.20.6 {*//*float*//*? } else {*/RenderTickCounter/*? }*/ f, int x, int y) {
            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            float opacity = getOpacity(f);
            Opacitator.drawWithOpacity(opacity, false, (ignored, alpha) -> context.drawText(textRenderer, text, x, y, (alpha << 24) | color, shadow));
        }

        @Override
        protected Vec2 getPadding() {
            return new Vec2(10, 10);
        }

        @Override
        protected Vec2 getSize() {
            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            return new Vec2(textRenderer.getWidth(text), textRenderer.getWrappedLinesHeight(text, Integer.MAX_VALUE));
        }

        @Override
        public Identifier getId() {
            return ID;
        }

        public static class Serializer implements PacketSerializer<TextOverlay> {
            @Override
            public void write(TextOverlay object, PacketByteBuf buf) {
                buf.writeEnumConstant(object.anchor);
                buf.writeString(object.text);
                buf.writeInt(object.color);
                buf.writeDouble(object.scale);
                buf.writeBoolean(object.shadow);
                object.writeAnimation(buf);
            }

            @Override
            public TextOverlay read(PacketByteBuf buf) {
                return new TextOverlay(
                        buf.readEnumConstant(Anchor.class),
                        null,
                        buf.readString(),
                        buf.readInt(),
                        buf.readDouble(),
                        buf.readBoolean(),
                        3
                ).readAnimation(buf);
            }
        }
    }
}
