package org.lilbrocodes.composer_reloaded.api.v1.events.builtin;

import org.lilbrocodes.composer_reloaded.api.v1.events.ClientScrollEvents;
import org.lilbrocodes.composer_reloaded.api.v1.events.ServerScrollEvents;

/**
 * A scroll event that synchronizes between client and server.
 * <p>
 * Implementations receive both {@link ClientScrollEvents.ClientScrollAction} and
 * {@link ServerScrollEvents.ServerScrollAction} callbacks.
 * </p>
 */
public interface SynchronizedScrollEvent extends ClientScrollEvents.ClientScrollAction, ServerScrollEvents.ServerScrollAction {

}
