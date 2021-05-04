package EventsHandlers;

import SoundHandler.FileResourceLoader;
import SoundHandler.SoundPlayer;
import com.intellij.execution.ExecutionListener;
import com.intellij.execution.ExecutionManager;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EventDetector {
    //ToDo: remake hardcode to resource
    private final String resourceDir = "C:\\Users\\mmas6\\IntellijProjects\\IntellijPlugin\\src\\main\\resources\\sounds";
    private final String soundBuildStarting = "magic.wav";
    private final String soundBuildTerminated = "oww.wav";
    private final String soundSample = "sample.wav";

    public EventDetector(Project project) throws Exception {
        FileResourceLoader.setRootDir(resourceDir);
        MessageBusConnection connection = project.getMessageBus().connect();
        connection.subscribe(ExecutionManager.EXECUTION_TOPIC, new ExecutionListener() {
            @Override
            public void processStarting(@NotNull String executorId, @NotNull ExecutionEnvironment env) {
                try {
                    System.out.println(executorId);
                    SoundPlayer.playClip(FileResourceLoader.getFile(soundBuildStarting));
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                    e.printStackTrace();
                    Messages.showMessageDialog("Error while trying to play clip", "Error", Messages.getErrorIcon());
                }
            }

            @Override
            public void processNotStarted(@NotNull String executorId, @NotNull ExecutionEnvironment env) {
                try {
                    SoundPlayer.playClip(FileResourceLoader.getFile(soundBuildTerminated));
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                    e.printStackTrace();
                    Messages.showMessageDialog("Error while trying to play clip", "Error", Messages.getErrorIcon());
                }
            }
        });
    }
}
