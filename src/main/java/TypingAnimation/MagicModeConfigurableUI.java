package TypingAnimation;

import com.intellij.openapi.options.ConfigurableUi;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MagicModeConfigurableUI implements ConfigurableUi<MagicMode> {

    private JPanel mainPanel;
    private JCheckBox MagicModeEnabled;

    public MagicModeConfigurableUI(MagicMode MagicMode) {
        MagicModeEnabled.setSelected(MagicMode.isEnabled());
    }

    @Override
    public void reset(@NotNull MagicMode MagicMode) {
        MagicModeEnabled.setSelected(MagicMode.isEnabled());
    }

    @Override
    public boolean isModified(@NotNull MagicMode MagicMode) {
        return MagicModeEnabled.isSelected() != MagicMode.isEnabled();
    }

    @Override
    public void apply(@NotNull MagicMode MagicMode) throws ConfigurationException {
        MagicMode.setEnabled(MagicModeEnabled.isSelected());
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return mainPanel;
    }
}
