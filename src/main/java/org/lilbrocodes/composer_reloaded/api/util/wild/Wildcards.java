package org.lilbrocodes.composer_reloaded.api.util.wild;

import java.util.ArrayList;
import java.util.List;

public final class Wildcards {
    private Wildcards() {

    }

    public static boolean matches(String pattern, String path, char sep) {
        List<String> pTokens = tokenize(pattern, sep);
        List<String> sTokens = tokenize(path, sep);
        return matchTokens(pTokens, 0, sTokens, 0);
    }

    private static List<String> tokenize(String str, char sep) {
        List<String> out = new ArrayList<>();
        int last = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == sep) {
                out.add(str.substring(last, i));
                last = i + 1;
            }
        }
        out.add(str.substring(last));
        return out;
    }

    private static boolean matchTokens(List<String> pat, int pi,
                                       List<String> path, int si) {

        while (pi < pat.size() && si < path.size()) {
            String p = pat.get(pi);

            if (p.equals("**")) {
                // collapse multiple **
                while (pi + 1 < pat.size() && pat.get(pi + 1).equals("**"))
                    pi++;

                // ** at end consumes everything
                if (pi == pat.size() - 1)
                    return true;

                // otherwise try consuming variable number of segments
                for (int k = si; k <= path.size(); k++) {
                    if (matchTokens(pat, pi + 1, path, k))
                        return true;
                }
                return false;
            }

            if (!matchSingleSegment(p, path.get(si)))
                return false;

            pi++;
            si++;
        }

        while (pi < pat.size() && pat.get(pi).equals("**"))
            pi++;

        return pi == pat.size() && si == path.size();
    }

    private static boolean matchSingleSegment(String pat, String str) {
        return wildcardSegmentToRegex(pat).matcher(str).matches();
    }

    private static java.util.regex.Pattern wildcardSegmentToRegex(String segment) {
        StringBuilder sb = new StringBuilder();
        sb.append("^");

        for (int i = 0; i < segment.length(); i++) {
            char c = segment.charAt(i);

            if (c == '*') {
                sb.append(".*"); // wildcard inside a segment
                continue;
            }

            // escape regex meta-chars
            if ("\\.[]{}()+-?^$|".indexOf(c) >= 0) {
                sb.append("\\");
            }
            sb.append(c);
        }

        sb.append("$");
        return java.util.regex.Pattern.compile(sb.toString());
    }
}
