package SoundHandler;

import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;
import java.io.File;
import java.io.IOException;


public class SoundPlayer {

    public static void playClip(File clipFile) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                class AudioListener implements LineListener {
                    private boolean done = false;

                    @Override
                    public synchronized void update(LineEvent event) {
                        Type eventType = event.getType();
                        if (eventType == Type.STOP || eventType == Type.CLOSE) {
                            done = true;
                            notifyAll();
                        }
                    }

                    public synchronized void waitUntilDone() throws InterruptedException {
                        while (!done) {
                            wait();
                        }
                    }
                }
                AudioListener listener = new AudioListener();
                try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile)) {
                    Clip clip = AudioSystem.getClip();
                    try (clip) {
                        clip.addLineListener(listener);
                        clip.open(audioInputStream);
                        clip.start();
                        listener.waitUntilDone();
                    } catch (LineUnavailableException | InterruptedException | IOException e) {
                        throw new Exception("Everything is bad");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Thread " + clipFile.getName()).start();
    }
}
