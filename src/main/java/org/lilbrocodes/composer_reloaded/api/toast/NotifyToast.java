package org.lilbrocodes.composer_reloaded.api.toast;

import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.render.ColorProgram;

public class NotifyToast extends SimpleToast {
    public NotifyToast(String message, int backgroundColor, int borderColor) {
        super(ComposerReloaded.identify("textures/gui/toast/attention.png"), message, backgroundColor, borderColor);
    }

    public NotifyToast(String message, ColorProgram backgroundColorProgram, ColorProgram borderColorProgram) {
        super(ComposerReloaded.identify("textures/gui/toast/attention.png"), message, backgroundColorProgram, borderColorProgram);
    }
}
