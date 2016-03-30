/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReader;

import Message.Pair;
import Point.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class FileReader {

    /**
     * @param args the command line arguments
     */
    private static String hex(int n) {
        // call toUpperCase() if that's required
        return String.format("0x%2s", Integer.toHexString(n)).replace(' ', '0');
    }

    private static String hex(float f) {
        // change the float to raw integer bits(according to the OP's requirement)
        return hex(Float.floatToRawIntBits(f));
    }

    public static String FileToString(String stringpath) throws IOException {
        String content = "";
        Path path = Paths.get(stringpath);
        byte[] rawData = Files.readAllBytes(path);
        for (int i = 0; i < rawData.length; i++) {
            content = content.concat(Character.toString((char) rawData[i]));
        }
        return content;
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public static String GetExtension(String stringpath) {
        File file = new File(stringpath);
        return getFileExtension(file);
    }

    public static void savefile(String stringpath, byte[] content) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(stringpath);
        fos.write(content);
        fos.close();
    }

    public byte[] StringToBytes(String str) {
        byte[] b = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            b[i] = (byte) str.charAt(i);
        }
        return b;
    }

    public int getPrivateKey(String path) throws IOException {
        String key = FileToString(path);
        return Integer.parseInt(key);
    }

    public Point getPublicKey(String path, long mod) throws IOException {
        String key = FileToString(path);
        String[] keys = key.split(" ");
        Point p = new Point(Integer.parseInt(keys[0]), Integer.parseInt(keys[1]), mod);
        return p;
    }

    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip 
        return buffer.getLong();
    }

    public void SaveFileArrPoint(String path, Pair[] pairpoints) throws IOException {
        byte[] b = new byte[pairpoints.length * 4 * 8];
        int j = 0;
        for (Pair ppoint : pairpoints) {
            byte[] btemp = longToBytes(ppoint.first().getX());
            for (int i = 0; i < btemp.length; i++) {
                b[j] = btemp[i];
                j++;
            }
            btemp = longToBytes(ppoint.first().getY());
            for (int i = 0; i < btemp.length; i++) {
                b[j] = btemp[i];
                j++;
            }
            btemp = longToBytes(ppoint.second().getX());
            for (int i = 0; i < btemp.length; i++) {
                b[j] = btemp[i];
                j++;
            }
            btemp = longToBytes(ppoint.second().getY());
            for (int i = 0; i < btemp.length; i++) {
                b[j] = btemp[i];
                j++;
            }
        }
        savefile(path, b);
    }

    public Pair[] getPairPointsFromFile(String stringpath, long p) throws IOException {
        Path path = Paths.get(stringpath);

        byte[] rawData = Files.readAllBytes(path);
        Pair[] pair = new Pair[rawData.length / 32];
        byte[] btemp = new byte[8];
        long f = 0, s;
        Point point1 = new Point(1, 1, 5), point2 = new Point(1, 1, 4);
        for (int i = 0; i < rawData.length; i++) {
            btemp[i % 8] = rawData[i];
            if (i % 8 == 7) {
                if ((i / 8) % 4 == 0) {
                    f = bytesToLong(btemp);

                }
                if ((i / 8) % 4 == 1) {
                    s = bytesToLong(btemp);

                    point1 = new Point(f, s, p);

                }
                if ((i / 8) % 4 == 2) {

                    f = bytesToLong(btemp);

                }
                if ((i / 8) % 4 == 3) {
                    s = bytesToLong(btemp);
                    point2 = new Point(f, s, p);
                    pair[i / 32 ] = new Pair(point1, point2);
                }
            }
        }
        return pair;
    }
}
