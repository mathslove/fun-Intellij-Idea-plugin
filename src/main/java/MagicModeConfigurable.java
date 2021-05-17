import EventsHandler.EventsSound;
import TypingAnimation.MagicMode;
import com.intellij.openapi.options.ConfigurableBase;
import org.jetbrains.annotations.NotNull;


public class MagicModeConfigurable extends ConfigurableBase<MagicModeConfigurableUI, PluginSettings> {
    private final PluginSettings settings;
    public MagicModeConfigurable(@NotNull PluginSettings settings) {
        super("Magic.mode", "Magic Mode", "Magic.mode");
        this.settings = settings;
    }

    public MagicModeConfigurable() {
        this(new PluginSettings(MagicMode.getInstance(), EventsSound.getInstance()));
    }

    @NotNull
    @Override
    protected PluginSettings getSettings() {
        return settings;
    }

    @Override
    protected MagicModeConfigurableUI createUi() {
        return new MagicModeConfigurableUI(settings);
    }
}
