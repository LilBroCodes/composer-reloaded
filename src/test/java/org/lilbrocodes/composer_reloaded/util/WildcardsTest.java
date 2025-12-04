package org.lilbrocodes.composer_reloaded.util;

import org.junit.jupiter.api.Test;
import org.lilbrocodes.composer_reloaded.api.util.wild.Wildcards;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WildcardsTest {
    private static final char SEP = '/';

    @Test
    void testLiteralExactMatch() {
        assertTrue(Wildcards.matches("abc", "abc", SEP));
    }

    @Test
    void testLiteralNoMatch() {
        assertFalse(Wildcards.matches("abc", "abcd", SEP));
    }

    @Test
    void testSingleAsteriskNoSeparator() {
        assertTrue(Wildcards.matches("abc*cba", "abcXYZcba", SEP));
        assertTrue(Wildcards.matches("*", "", SEP));
        assertTrue(Wildcards.matches("*", "hello", SEP));
        assertFalse(Wildcards.matches("*", "a/b", SEP)); // cannot cross separator
    }

    @Test
    void testSingleAsteriskCannotCrossSeparator() {
        assertFalse(Wildcards.matches("foo/*/bar", "foo/a/b/bar", SEP));
        assertTrue(Wildcards.matches("foo/*/bar", "foo/x/bar", SEP));
    }

    @Test
    void testDoubleAsteriskCrossesSeparators() {
        assertTrue(Wildcards.matches("foo/**/bar", "foo/a/b/c/bar", SEP));
        assertTrue(Wildcards.matches("foo/**/bar", "foo/bar", SEP));
        assertTrue(Wildcards.matches("foo/**", "foo/a/b/c", SEP));
    }

    @Test
    void testDoubleAsteriskEdgeCases() {
        assertTrue(Wildcards.matches("**", "", SEP));
        assertTrue(Wildcards.matches("**", "a", SEP));
        assertTrue(Wildcards.matches("**", "a/b/c", SEP));
    }

    @Test
    void testJsonPattern() {
        assertTrue(Wildcards.matches("src/**/json/*.json",
                "src/main/resources/json/test.json", SEP));
        assertTrue(Wildcards.matches("src/**/json/*.json",
                "src/json/file.json", SEP));

        assertFalse(Wildcards.matches("src/**/json/*.json",
                "src/json/sub/file.json", SEP)); // * does not cross separator
        assertFalse(Wildcards.matches("src/**/json/*.json",
                "src/main/resources/json/sub/file.json", SEP));
    }

    @Test
    void testStartsWithDoubleAsterisk() {
        assertTrue(Wildcards.matches("**/test", "test", SEP));
        assertTrue(Wildcards.matches("**/test", "a/b/test", SEP));
        assertFalse(Wildcards.matches("**/test", "a/b/testing", SEP));
    }

    @Test
    void testEndsWithDoubleAsterisk() {
        assertTrue(Wildcards.matches("foo/**", "foo/a/b/c", SEP));
        assertTrue(Wildcards.matches("foo/**", "foo/", SEP));
        assertTrue(Wildcards.matches("foo/**", "foo", SEP));
        assertFalse(Wildcards.matches("foo/**", "bar/foo", SEP));
    }

    @Test
    void testCustomSeparator() {
        char dot = '.';
        assertTrue(Wildcards.matches("a.*.c", "a.b.c", dot));
        assertFalse(Wildcards.matches("a.*.c", "a.b.d.c", dot));

        assertTrue(Wildcards.matches("a.**.c", "a.b.d.c", dot));
        assertTrue(Wildcards.matches("a.**.c", "a.c", dot));
    }

    @Test
    void testRegexSpecialCharsAreEscaped() {
        assertTrue(Wildcards.matches("foo.bar", "foo.bar", SEP));
        assertFalse(Wildcards.matches("foo.bar", "fooXbar", SEP));

        assertTrue(Wildcards.matches("foo.*.bar", "foo.X.bar", SEP));
        assertFalse(Wildcards.matches("foo.*.bar", "foo/X/bar", SEP));
    }

    @Test
    void testEmptyCases() {
        assertTrue(Wildcards.matches("", "", SEP));
        assertFalse(Wildcards.matches("", "a", SEP));
        assertFalse(Wildcards.matches("a", "", SEP));
    }

    @Test
    void testMixedWildcards() {
        assertTrue(Wildcards.matches("foo*/**/bar*.txt",
                "foobar/x/y/bar123.txt", SEP));

        assertFalse(Wildcards.matches("foo*/*/bar*.txt",
                "foo/x/bar/bar123.txt", SEP));
    }
}
