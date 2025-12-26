package org.lilbrocodes.composer_reloaded.api.render;

import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.velora.particle.VeloraParticle.ParticleShape;

//? if minecraft: <=1.20.1 {
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
//?}

@SuppressWarnings("ALL")
public class ParticleRenderer {
    public static void renderShape(ParticleShape shape, Vec2 position, double size, double rotation, int color) {
        switch (shape) {
            case CIRCLE -> renderCircle(position, size, color);
            case QUAD -> renderQuad(position, size, rotation, color);
            case TRIANGLE -> renderTriangle(position, size, rotation, color);
        }
    }

    private static void renderCircle(Vec2 center, double radius, int color) {
        final int segments = 5 + (int) radius;
        Vec2[] vertices = new Vec2[segments];

        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            double x = center.x + Math.cos(angle) * radius;
            double y = center.y + Math.sin(angle) * radius;
            vertices[i] = new Vec2(x, y);
        }

        for (int i = 0; i < segments; i++) {
            Vec2 v1 = vertices[i];
            Vec2 v2 = vertices[(i + 1) % segments];
            drawTriangle(center, v1, v2, color);
        }
    }

    private static void renderQuad(Vec2 center, double size, double rotation, int color) {
        //? if minecraft: <=1.20.1 {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        int r = (color >> 24) & 0xFF;
        int g = (color >> 16) & 0xFF;
        int b = (color >> 8) & 0xFF;
        int a = color & 0xFF;

        double half = size / 2;

        Vec2[] vertices = new Vec2[]{
                new Vec2(-half, -half),
                new Vec2(-half, half),
                new Vec2(half, half),
                new Vec2(half, -half),
        };

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = rotateVec(vertices[i], rotation);
            vertices[i] = new Vec2(vertices[i].x + center.x, vertices[i].y + center.y);
        }

        for (Vec2 p : vertices) {
            buffer.vertex(p.x, p.y, 0f).color(r, g, b, a).next();
        }

        tessellator.draw();
        RenderSystem.disableBlend();
        //? }
        // TODO: Reimplement for 1.21.4
    }

    private static void renderTriangle(Vec2 center, double size, double rotation, int color) {
        double height = Math.sqrt(3) / 2 * size;
        Vec2[] vertices = new Vec2[]{
                new Vec2(0, -2 * height / 3),
                new Vec2(-size / 2, height / 3),
                new Vec2(size / 2, height / 3)
        };
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = rotateVec(vertices[i], rotation);
            vertices[i] = new Vec2(vertices[i].x + center.x, vertices[i].y + center.y);
        }

        drawTriangle(vertices[0], vertices[1], vertices[2], color);
    }

    private static Vec2 rotateVec(Vec2 v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vec2(
                v.x * cos - v.y * sin,
                v.x * sin + v.y * cos
        );
    }


    /**
     *  This method is literal magic. I still have no idea how or why it works, somehow I flipped my ccw calculations, idk bro.
     *  If someone wants to fix this, please do as I have no clue how to do so.
     */
    private static void drawTriangle(Vec2 v1, Vec2 v2, Vec2 v3, int color) {
        //? if minecraft: <=1.20.1 {
        double cross = (v2.x - v1.x) * (v3.y - v1.y) - (v2.y - v1.y) * (v3.x - v1.x);

        boolean ccw = cross > 0;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);

        int r = (color >> 24) & 0xFF;
        int g = (color >> 16) & 0xFF;
        int b = (color >> 8) & 0xFF;
        int a = color & 0xFF;

        if (!ccw) {
            for (Vec2 vert : List.of(v1, v2, v3)) {
                buffer.vertex(vert.x, vert.y, 0f).color(r, g, b, a).next();
            }
        } else {
            for (Vec2 vert : List.of(v1, v3, v2)) {
                buffer.vertex(vert.x, vert.y, 0f).color(r, g, b, a).next();
            }
        }

        tessellator.draw();
        RenderSystem.disableBlend();
        //? }
        // TODO: Reimplement for 1.21.4
    }

    public static void renderTexturedQuad(Identifier texture, Vec2 center, double size, double rotation, int color) {
        //? if minecraft: <=1.20.1 {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);

        int r = (color >> 24) & 0xFF;
        int g = (color >> 16) & 0xFF;
        int b = (color >> 8) & 0xFF;
        int a = color & 0xFF;

        double half = size / 2;

        Vec2[] corners = new Vec2[]{
                new Vec2(-half, -half),
                new Vec2(-half, half),
                new Vec2(half, half),
                new Vec2(half, -half)
        };

        float[] uvs = {
                0f, 0f,
                0f, 1f,
                1f, 1f,
                1f, 0f
        };

        for (int i = 0; i < 4; i++) {
            Vec2 rotated = rotateVec(corners[i], rotation);
            Vec2 finalPos = new Vec2(center.x + rotated.x, center.y + rotated.y);
            buffer.vertex(finalPos.x, finalPos.y, 0)
                    .texture(uvs[i * 2], uvs[i * 2 + 1])
                    .color(r, g, b, a)
                    .next();
        }

        tessellator.draw();
        RenderSystem.disableBlend();
        //? }
        // TODO: Reimplement for 1.21.4
    }

    public static void renderAnimatedQuad(
            Identifier texture,
            Vec2 center,
            double size,
            double rotation,
            int color,
            int frameIndex,
            int totalFrames
    ) {
        //? if minecraft: <=1.20.1 {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);

        int r = (color >> 24) & 0xFF;
        int g = (color >> 16) & 0xFF;
        int b = (color >> 8) & 0xFF;
        int a = color & 0xFF;

        double half = size / 2;

        // Calculate vertical UVs for the current frame
        float vFrameHeight = 1f / totalFrames;
        float vMin = vFrameHeight * frameIndex;
        float vMax = vMin + vFrameHeight;

        Vec2[] corners = new Vec2[]{
                new Vec2(-half, -half),
                new Vec2(-half, half),
                new Vec2(half, half),
                new Vec2(half, -half)
        };

        float[] uvs = {
                0f, vMin,
                0f, vMax,
                1f, vMax,
                1f, vMin
        };

        for (int i = 0; i < 4; i++) {
            Vec2 rotated = rotateVec(corners[i], rotation);
            Vec2 finalPos = new Vec2(center.x + rotated.x, center.y + rotated.y);
            buffer.vertex(finalPos.x, finalPos.y, 0)
                    .texture(uvs[i * 2], uvs[i * 2 + 1])
                    .color(r, g, b, a)
                    .next();
        }

        tessellator.draw();
        RenderSystem.disableBlend();
        //? }
        // TODO: Reimplement for 1.21.4
    }

}
