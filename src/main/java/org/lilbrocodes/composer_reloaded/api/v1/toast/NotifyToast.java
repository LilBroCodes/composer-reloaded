package org.lilbrocodes.composer_reloaded.api.v1.toast;

import org.lilbrocodes.composer_reloaded.api.v1.render.ColorProgram;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class NotifyToast extends SimpleToast {
    public NotifyToast(String message, int backgroundColor, int borderColor) {
        super(ComposerReloaded.identify("textures/gui/toast/attention.png"), message, backgroundColor, borderColor);
    }

    public NotifyToast(String message, ColorProgram backgroundColorProgram, ColorProgram borderColorProgram) {
        super(ComposerReloaded.identify("textures/gui/toast/attention.png"), message, backgroundColorProgram, borderColorProgram);
    }
}
