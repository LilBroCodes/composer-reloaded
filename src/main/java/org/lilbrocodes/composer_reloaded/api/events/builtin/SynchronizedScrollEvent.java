package org.lilbrocodes.composer_reloaded.api.events.builtin;

import org.lilbrocodes.composer_reloaded.api.events.ClientScrollEvents;
import org.lilbrocodes.composer_reloaded.api.events.ServerScrollEvents;

public interface SynchronizedScrollEvent extends ClientScrollEvents.ClientScrollAction, ServerScrollEvents.ServerScrollAction {

}
