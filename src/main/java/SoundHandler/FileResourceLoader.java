package SoundHandler;

import java.io.File;
import java.nio.file.Path;

public class FileResourceLoader {
    private static Path rootDir;
    private static FileResourceLoader instance = null;

    public static void setRootDir(String rootPath) throws Exception {
        if (Path.of(rootPath).toFile().isDirectory()) {
            rootDir = Path.of(rootPath);
        } else {
            throw new Exception("Path doesn't lead to dir " + (rootPath));
        } }

    public FileResourceLoader getInstance(){
        return instance;
    }

    public static File getFile(String filename) {
        return rootDir.resolve(filename).toFile();
    }
}
