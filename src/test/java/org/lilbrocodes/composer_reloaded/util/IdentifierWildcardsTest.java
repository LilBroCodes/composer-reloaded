package org.lilbrocodes.composer_reloaded.util;

import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.util.wild.IdentifierWildcards;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdentifierWildcardsTest {

    @Test
    void testValidExactMatch() {
        assertTrue(IdentifierWildcards.matches("minecraft:stone",
                "minecraft", "stone"));
    }

    @Test
    void testRejectInvalidIdentifierFormat() {
        assertFalse(IdentifierWildcards.matches("minecraftstone", "minecraft", "stone"));
        assertFalse(IdentifierWildcards.matches("a:b:c", "a", "b"));
    }

    @Test
    void testNamespaceWildcardSingleSegment() {
        assertTrue(IdentifierWildcards.matches("foo_bar:example",
                "foo_*", "example"));

        assertFalse(IdentifierWildcards.matches("minecraft:stone",
                "foo_*", "example"));
    }

    @Test
    void testPathWildcardMultiSegment() {
        assertTrue(IdentifierWildcards.matches("foo_bar:super_mega_block",
                "foo_bar", "super_*_block"));

        assertFalse(IdentifierWildcards.matches("foo_bar:super_mega_extra_block",
                "foo_bar", "super_*_block"));
    }

    @Test
    void testDoubleAsteriskInPath() {
        assertTrue(IdentifierWildcards.matches("foo_bar:super_mega_extra_block",
                "foo_bar", "super_**_block"));

        assertTrue(IdentifierWildcards.matches("foo_bar:super_block",
                "foo_bar", "super_**_block"));
    }

    @Test
    void testRegexSpecialCharEscaping() {
        assertTrue(IdentifierWildcards.matches("test:foo.bar",
                "test", "foo.bar"));

        assertFalse(IdentifierWildcards.matches("test:fooXbar",
                "test", "foo.bar"));
    }

    @Test
    void testMixedNamespaceAndPathPatterns() {
        assertTrue(IdentifierWildcards.matches("abc_bar:long_blue_item",
                "abc_*", "long_*_item"));

        assertFalse(IdentifierWildcards.matches("xyz_bar:long_blue_item",
                "abc_*", "long_*_item"));
    }
}
