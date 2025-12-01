package org.lilbrocodes.composer_reloaded.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.util.Scaling;
import org.lilbrocodes.composer_reloaded.api.util.Vec2;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ScalingTest {
    private MinecraftClient mockClient;
    private Window mockWindow;

    @BeforeEach
    void setup() {
        mockClient = mock(MinecraftClient.class);
        mockWindow = mock(Window.class);
        when(mockClient.getWindow()).thenReturn(mockWindow);
    }

    @Test
    void testGetScale() {
        when(mockWindow.getFramebufferWidth()).thenReturn(1920);
        when(mockWindow.getScaledWidth()).thenReturn(960);

        try (MockedStatic<MinecraftClient> clientStatic = mockStatic(MinecraftClient.class)) {
            clientStatic.when(MinecraftClient::getInstance).thenReturn(mockClient);

            double scale = Scaling.getScale();
            assertEquals(2.0, scale);
        }
    }

    @Test
    void testToScaledAndToPixels() {
        when(mockWindow.getFramebufferWidth()).thenReturn(1920);
        when(mockWindow.getScaledWidth()).thenReturn(960);

        try (MockedStatic<MinecraftClient> clientStatic = mockStatic(MinecraftClient.class)) {
            clientStatic.when(MinecraftClient::getInstance).thenReturn(mockClient);

            // double methods
            assertEquals(50, Scaling.toScaled(100));
            assertEquals(200, Scaling.toPixels(100));

            // int methods
            assertEquals(50, Scaling.toScaled(100));
            assertEquals(200, Scaling.toPixels(100));

            // Vec2 methods
            Vec2 original = new Vec2(100, 200);
            Vec2 scaled = Scaling.toScaled(original);
            assertEquals(50, scaled.x);
            assertEquals(100, scaled.y);

            Vec2 pixels = Scaling.toPixels(scaled);
            assertEquals(100, pixels.x);
            assertEquals(200, pixels.y);
        }
    }
}
