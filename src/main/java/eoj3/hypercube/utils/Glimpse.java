package eoj3.hypercube.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Glimpse {

    private static final int LENGTH_LIMIT = 24;

    public static String glimpse(File file) {
        try {
            FileInputStream is = new FileInputStream(file);
            byte[] buffer = new byte[LENGTH_LIMIT + 1];
            int len = is.read(buffer);
            String res;
            if (len > LENGTH_LIMIT) {
                res = new String(Arrays.copyOfRange(buffer, 0, 32)) + "...";
            } else {
                res = new String(buffer);
            }
            is.close();
            return res.replace("[\r\n]", " ").trim();
        } catch (IOException e) {
            return "File not found";
        }
    }
}
