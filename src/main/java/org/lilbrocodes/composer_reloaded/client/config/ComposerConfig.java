package org.lilbrocodes.composer_reloaded.client.config;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.util.EnumTranslatable;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import org.jetbrains.annotations.NotNull;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;

@SuppressWarnings("CanBeFinal")
public class ComposerConfig extends Config {
    public ComposerConfig() {
        super(ComposerReloaded.identify("config"));
    }

    public static ComposerConfig INSTANCE;

    @Override
    public int defaultPermLevel() {
        return 0;
    }

    @Override
    public @NotNull FileType fileType() {
        return FileType.JSONC;
    }

    @Override
    public @NotNull SaveType saveType() {
        return SaveType.SEPARATE;
    }

    @Name("Rainbow Effect on Duplicate Keybinds")
    public boolean rainbowDuplicateKeybinds = false;
    @Name("Rainbow Effect Speed")
    public ValidatedInt rainbowEffectSpeed = new ValidatedInt(3, 10, 1);
    @Name("Allow Duplicate Keybinds")
    public BindsMode allowDuplicateKeybinds = BindsMode.MC_AND_CM;

    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public static void initialize() {
        if (ComposerReloaded.dupedBinds())
            INSTANCE = ConfigApiJava.registerAndLoadConfig(ComposerConfig::new, RegisterType.CLIENT);
    }

    public enum BindsMode implements EnumTranslatable {
        NONE, MC_AND_CM, ALL;

        @Override
        public @NotNull String prefix() {
            return "composer_reloaded.binds_mode";
        }
    }
}
