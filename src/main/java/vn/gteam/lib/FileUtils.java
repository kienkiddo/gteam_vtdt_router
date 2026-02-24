package vn.gteam.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    public static void writeFile(String filePath, byte[] data) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            fos.write(data);
        } finally {
            if (fos != null){
                fos.close();
            }
        }
    }

    public static boolean exist(String filePath){
        File f = new File(filePath);
        return f.exists();
    }

    public static boolean remove(String path){
        File file = new File(path);
        return file.delete();
    }

    public static void create(String path){
        File file = new File(path);
    }

    public static byte[] readBytes(String filePath) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            return fis.readAllBytes();
        }  finally {
            if (fis != null){
                fis.close();
            }
        }
    }

    public static String readFile(String filePath) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            byte[] binaryData = fis.readAllBytes();
            return new String(binaryData, StandardCharsets.UTF_8);
        }  finally {
            if (fis != null){
                fis.close();
            }
        }
    }
}
