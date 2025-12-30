package org.lilbrocodes.composer_reloaded.internal.client.config;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.util.EnumTranslatable;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import org.jetbrains.annotations.NotNull;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

//? if minecraft: <=1.20.1 || >=1.21 {
import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
//? }

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

    //? if minecraft: <=1.20.1 || >=1.21 {
    @Override
    public @NotNull FileType fileType() {
        return FileType.JSONC;
    }

    @Override
    public @NotNull SaveType saveType() {
        return SaveType.SEPARATE;
    }
    //? }

    //? if minecraft: <=1.20.1 || >=1.21
    @Name("Rainbow Effect on Duplicate Keybinds")
    public boolean rainbowEffectOnDuplicateKeybinds = false;

    //? if minecraft: <=1.20.1 || >=1.21
    @Name("Rainbow Effect Speed")
    public ValidatedInt rainbowEffectSpeed = new ValidatedInt(3, 10, 1);

    //? if minecraft: <=1.20.1 || >=1.21
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
