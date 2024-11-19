package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    File currentFile = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt");
    
    /**
     * Change current working file
     * @param currentFile
     */
    void setCurrentFile(final File currentFile) {
        this.currentFile = currentFile;
    }

    /**
     * 
     * @return current working file
     */
    File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * 
     * @return path to the current working file
     */
    String getCurrentFilePath() {
        return this.currentFile.getAbsolutePath();
    }

    /**
     * Writes something in the current working file
     * @param toWrite string to write
     * @throws IOException if an error occours
     */
    void writeFile(String toWrite) throws IOException {
        if (this.currentFile.canWrite()) {/* 
            try (PrintStream ps = new PrintStream(this.getCurrentFilePath(), StandardCharsets.UTF_8)) {
                ps.print(toWrite);
            }*/
            Files.write(Path.of(this.getCurrentFilePath()), toWrite.getBytes(StandardCharsets.UTF_8));
        } else {
            throw new IOException("File cannot be wrote");
        }
    }

}
