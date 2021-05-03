package EventsHandlers;

import com.intellij.execution.ExecutionListener;
import com.intellij.execution.ExecutionManager;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class EventDetector {
    private final String resourceDir = "C:\\Users\\mmas6\\IntellijProjects\\IntellijPlugin\\src\\main\\resources\\sounds";
    private final String soundBuildStarting = "magic.wav";
    private final String soundBuildTerminated = "oww.wav";
    private final String soundSample = "sample.wav";
    private final FileResourceLoader loader = new FileResourceLoader(resourceDir);

    public EventDetector(Project project) throws Exception {
        MessageBusConnection connection = project.getMessageBus().connect();
        connection.subscribe(ExecutionManager.EXECUTION_TOPIC, new ExecutionListener() {
            @Override
            public void processStarting(@NotNull String executorId, @NotNull ExecutionEnvironment env) {
                try {
                    System.out.println(executorId);
                    SoundPlayer.playClip(loader.getFile(soundBuildStarting));
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                    e.printStackTrace();
                    Messages.showMessageDialog("Error while trying to play clip", "Error", Messages.getErrorIcon());
                }
            }

            @Override
            public void processNotStarted(@NotNull String executorId, @NotNull ExecutionEnvironment env) {
                try {
                    SoundPlayer.playClip(loader.getFile(soundBuildTerminated));
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                    e.printStackTrace();
                    Messages.showMessageDialog("Error while trying to play clip", "Error", Messages.getErrorIcon());
                }
            }
        });
    }
}
