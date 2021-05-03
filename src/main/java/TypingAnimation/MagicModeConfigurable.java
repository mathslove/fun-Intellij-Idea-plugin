package TypingAnimation;

import com.intellij.openapi.options.ConfigurableBase;
import org.jetbrains.annotations.NotNull;


public class MagicModeConfigurable extends ConfigurableBase<MagicModeConfigurableUI, MagicMode> {

    private final MagicMode settings;

    public MagicModeConfigurable(@NotNull MagicMode settings) {
        super("Magic.mode", "Magic Mode", "Magic.mode");
        this.settings = settings;
    }

    public MagicModeConfigurable() {
        this(MagicMode.getInstance());
    }

    @NotNull
    @Override
    protected MagicMode getSettings() {
        if (settings == null) {
            throw new IllegalStateException("Magic mode is null");
        }
        return settings;
    }

    @Override
    protected MagicModeConfigurableUI createUi() {
        return new MagicModeConfigurableUI(settings);
    }
}
