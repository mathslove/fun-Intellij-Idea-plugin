import EventsHandler.EventsSound;
import TypingAnimation.MagicMode;
import org.jetbrains.annotations.NotNull;


public class PluginSettings {
    public final MagicMode magicMode;
    public final EventsSound eventsSound;

    public PluginSettings(@NotNull MagicMode magicMode, @NotNull EventsSound eventsSound){
        this.magicMode = magicMode;
        this.eventsSound = eventsSound;
    }

}
