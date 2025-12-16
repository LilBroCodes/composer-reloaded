package org.lilbrocodes.composer_reloaded.api.util.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.concurrent.CompletableFuture;

public class ColorArgumentType implements ArgumentType<Integer> {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    protected ColorArgumentType() {

    }

    public static ColorArgumentType color() {
        return new ColorArgumentType();
    }

    @Override
    public Integer parse(StringReader reader) throws CommandSyntaxException {
        int start = reader.getCursor();
        if (!reader.canRead() || reader.peek() != '#') {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherParseException()
                    .create("Color must start with #");
        }
        reader.skip();

        StringBuilder hex = new StringBuilder();
        while (reader.canRead() && hex.length() < 8 && isHex(reader.peek())) {
            hex.append(reader.read());
        }

        if (hex.length() != 6 && hex.length() != 8) {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherParseException()
                    .create("Color must be in #RRGGBB or #RRGGBBAA format");
        }

        return (int) Long.parseLong(hex.toString(), 16);
    }

    private boolean isHex(char c) {
        return (c >= '0' && c <= '9') ||
                (c >= 'a' && c <= 'f') ||
                (c >= 'A' && c <= 'F');
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String remaining = builder.getRemaining();

        if (!remaining.startsWith("#")) {
            return builder.buildFuture();
        }

        int length = remaining.length() - 1;
        if (length >= 8) return builder.buildFuture();

        for (char c : HEX_DIGITS) {
            builder.suggest(remaining + c);
        }

        return builder.buildFuture();
    }
}
