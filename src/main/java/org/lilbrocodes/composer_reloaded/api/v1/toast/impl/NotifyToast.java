package org.lilbrocodes.composer_reloaded.api.v1.toast.impl;

import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class NotifyToast extends SimpleToast {
    public NotifyToast(String message, int backgroundColor, int borderColor) {
        super(ComposerReloaded.identify("textures/gui/toast/attention.png"), message, backgroundColor, borderColor);
    }
}
