package com.codex.composer.api.v1.toast.impl;

import com.codex.composer.internal.Composer;

public class NotifyToast extends SimpleToast {
    public NotifyToast(String message, int backgroundColor, int borderColor) {
        super(Composer.identify("textures/gui/toast/attention.png"), message, backgroundColor, borderColor);
    }
}
