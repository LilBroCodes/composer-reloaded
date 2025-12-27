package org.lilbrocodes.composer_reloaded.api.v1.tooltips;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lilbrocodes.composer_reloaded.api.v1.util.builder.BuilderFields;
import org.lilbrocodes.composer_reloaded.api.v1.util.builder.ListBuilder;

import java.util.List;
import java.util.function.Function;

/**
 * Represents a single tooltip section with optional nested sections.
 */
public class Section {
    private final String title;
    private final String details;
    private final Function<TooltipContext, Modifier> requiredButtonProvider;
    private final ContentProvider content;
    private final List<Section> nestedSections;
    private final Formatting[] titleFormat;
    private final Formatting[] contentFormat;
    private final Formatting[] hiddenFormat;

    private Section(String title, String details, Function<TooltipContext, Modifier> requiredButtonProvider, ContentProvider content, List<Section> nestedSections, Formatting[] titleFormat, Formatting[] contentFormat, Formatting[] hiddenFormat) {
        this.title = title;
        this.details = details;
        this.requiredButtonProvider = requiredButtonProvider;
        this.content = content;
        this.nestedSections = nestedSections;
        this.titleFormat = titleFormat;
        this.contentFormat = contentFormat;
        this.hiddenFormat = hiddenFormat;
    }

    public static Builder create() {
        return new Builder();
    }

    /**
     * Appends this section to the output list based on context.
     */
    public void append(TooltipContext context, List<Text> out) {
        Modifier requiredButtons = requiredButtonProvider.apply(context);

        boolean buttonsPressed = requiredButtons == null || requiredButtons.matches(context);

        if (!buttonsPressed) {
            out.add(Text.translatable("composer_reloaded.dynamic_tooltips.hidden", requiredButtons.toString(), Text.translatable(details)).formatted(hiddenFormat));
            return;
        }

        if (!title.isBlank()) out.add(Text.translatable(title).formatted(titleFormat));

        if (content != null) {
            String main = content.get(context);
            if (main != null && !main.isEmpty()) {
                out.add(Text.translatable(main).formatted(contentFormat));
            }
        }

        if (nestedSections != null) {
            for (Section child : nestedSections) {
                child.append(context, out);
            }
        }
    }

    public static class Builder {
        private String title;
        private String details = "";
        private Function<TooltipContext, Modifier> requiredButtonProvider;
        private ContentProvider content;
        private final ListBuilder<Builder, Section> nestedSections = new ListBuilder<>(this);
        private final ListBuilder<Builder, Formatting> titleFormatter = new ListBuilder<>(this);
        private final ListBuilder<Builder, Formatting> contentFormatter = new ListBuilder<>(this, List.of(
                Formatting.GRAY
        ));
        private final ListBuilder<Builder, Formatting> hiddenFormatter = new ListBuilder<>(this, List.of(
                Formatting.GRAY
        ));

        private Builder() {

        }

        public Builder title(String value) {
            this.title = value;
            return this;
        }

        public Builder details(String value) {
            this.details = value;
            return this;
        }

        public Builder keyCombination(Function<TooltipContext, Modifier> provider) {
            this.requiredButtonProvider = provider;
            return this;
        }

        public Builder content(ContentProvider content) {
            this.content = content;
            return this;
        }

        public ListBuilder<Builder, Section> children() {
            return nestedSections;
        }

        public ListBuilder<Builder, Formatting> titleFormat() {
            return titleFormatter;
        }

        public ListBuilder<Builder, Formatting> contentFormat() {
            return contentFormatter;
        }

        public ListBuilder<Builder, Formatting> hiddenFormat() {
            return hiddenFormatter;
        }

        public Section build() {
            BuilderFields.verify(this);
            return new Section(
                    title,
                    details.isBlank() ? "details" : details,
                    requiredButtonProvider,
                    content,
                    nestedSections.build(),
                    titleFormatter.toArray(Formatting.class),
                    contentFormatter.toArray(Formatting.class),
                    hiddenFormatter.toArray(Formatting.class));
        }
    }
}