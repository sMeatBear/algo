package app.basic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileCopyer {
    public static void main(String[] args) {
        File f = new File("copy_test.txt");

        File copied = new File("src/app/basic/copied.txt");
        
        try {
            Files.copy(f.toPath(), copied.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}