package EventsHandlers;

import java.io.File;
import java.nio.file.Path;

public class FileResourceLoader {
    private final Path rootDir;

    public FileResourceLoader(String rootPath) throws Exception {
        if (Path.of(rootPath).toFile().isDirectory()) {
            rootDir = Path.of(rootPath);
        } else {
            throw new Exception("Path doesn't lead to dir " + (rootPath));
        }
    }

    public File getFile(String filename) {
        return rootDir.resolve(filename).toFile();
    }
}
