import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MagicModeConfigurableUI implements ConfigurableUi<PluginSettings> {

    private JPanel mainPanel;
    private JCheckBox MagicModeState;
    private JCheckBox EnvSoundState;
    private JLabel Tittle;

    public MagicModeConfigurableUI(PluginSettings settings) {
        MagicModeState.setSelected(settings.magicMode.isEnabled());
        EnvSoundState.setSelected(settings.eventsSound.isEnabled());
    }

    @Override
    public void reset(@NotNull PluginSettings settings) {
        MagicModeState.setSelected(settings.magicMode.isEnabled());
        EnvSoundState.setSelected(settings.eventsSound.isEnabled());
    }

    @Override
    public boolean isModified(@NotNull PluginSettings settings) {
        return MagicModeState.isSelected() != settings.magicMode.isEnabled() ||
                EnvSoundState.isSelected() != settings.eventsSound.isEnabled();
    }

    @Override
    public void apply(@NotNull PluginSettings settings) throws ConfigurationException {
        settings.magicMode.setEnablement(MagicModeState.isSelected());
        settings.eventsSound.setEnablement(EnvSoundState.isSelected());
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return mainPanel;
    }
}
