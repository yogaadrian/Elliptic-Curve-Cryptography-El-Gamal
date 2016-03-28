/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReader;
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
    public String PSNR="";

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
    
    private static String FileToString(String stringpath) throws IOException{
        String content="";
        Path path=Paths.get(stringpath);
        byte[] rawData = Files.readAllBytes(path);
        for(int i=0;i<rawData.length;i++){
            content=content.concat(Character.toString((char)rawData[i]));
        }
        return content;
    }
    
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    private static String GetExtension(String stringpath){
        File file = new File(stringpath);
        return getFileExtension(file);
    }
    
    private static void savefile(String stringpath,byte[] content) throws FileNotFoundException, IOException{
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
    
    public Point getPublicKey(String path,int mod) throws IOException{
        String key=FileToString(path);
        String[] keys=key.split(" ");
        Point p=new Point(Integer.parseInt(keys[0]),Integer.parseInt(keys[1]),mod);
        return p;
    }
}
