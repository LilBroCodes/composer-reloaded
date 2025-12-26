package org.lilbrocodes.composer_reloaded.api.v1.util.wild;

import org.lilbrocodes.composer_reloaded.api.v1.util.CharCount;

public class IdentifierWildcards {
    public static boolean matches(String id, String namespacePattern, String pathPattern) {
        if (CharCount.count(id, ':') != 1) return false;
        String[] parts = id.split(":");
        return Wildcards.matches(namespacePattern, parts[0], '_') && Wildcards.matches(pathPattern, parts[1], '_');
    }
}
