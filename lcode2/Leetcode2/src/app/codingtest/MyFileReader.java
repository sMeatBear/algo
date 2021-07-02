package app.codingtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader {
    List<String> fileReader(String fileName) {
        List<String> ret = new ArrayList<>();

        // create a buffer
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                ret.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static void main(String[] args) {
        MyFileReader mf = new MyFileReader();
        List<String> ret = mf.fileReader("Leetcode2/copy_test.txt");
        System.out.println(ret);
    }
}
