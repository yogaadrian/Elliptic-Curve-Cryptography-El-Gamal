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
    
    public static String FileToString(String stringpath) throws IOException{
        String content="";
        Path path=Paths.get(stringpath);
        byte[] rawData = Files.readAllBytes(path);
        for(int i=0;i<rawData.length;i++){
            content=content.concat(Character.toString((char)rawData[i]));
        }
        return content;
    }
    
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    public static String GetExtension(String stringpath){
        File file = new File(stringpath);
        return getFileExtension(file);
    }
    
    public static void savefile(String stringpath,byte[] content) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(stringpath);
            fos.write(content);
            fos.close();
    }
    
    public byte[] StringToBytes(String str){
        byte[] b= new byte[str.length()];
        for(int i=0;i<str.length();i++){
            b[i]=(byte)str.charAt(i);
        }
        return b;
    }
    public int getPrivateKey(String path) throws IOException{
        String key=FileToString(path);
        return Integer.parseInt(key);
    }       
    
    public Point getPublicKey(String path,long mod) throws IOException{
        String key=FileToString(path);
        String[] keys=key.split(" ");
        Point p=new Point(Integer.parseInt(keys[0]),Integer.parseInt(keys[1]),mod);
        return p;
    }
    
    public void SaveFileArrPoint(String path, Pair[] pairpoints) throws IOException{
        String content= "";
        for (Pair ppoint : pairpoints) {
            content = content + String.valueOf(ppoint.first().getX()) + " " + String.valueOf(ppoint.first().getY()) + " " + String.valueOf(ppoint.second().getX()) + " " + String.valueOf(ppoint.second().getY()) + " ";
        }
        savefile(path,StringToBytes(content));
    }
    
    public Pair[] getPairPointsFromFile(String path,long p) throws IOException{
        String content=FileToString(path);
        String[] splitcontent=content.split(" ");
        Pair[] pairpoints= new Pair[splitcontent.length/4];
        for(int i=0;i<splitcontent.length;i=i+4){
            Point p1= new Point((long)Integer.parseInt(splitcontent[i]),(long)Integer.parseInt(splitcontent[i+1]),p);
            Point p2=new Point((long)Integer.parseInt(splitcontent[i+2]),(long)Integer.parseInt(splitcontent[i+3]),p);;
            pairpoints[i/4]= new Pair(p1,p2);
        }
        return pairpoints;
    }
}
