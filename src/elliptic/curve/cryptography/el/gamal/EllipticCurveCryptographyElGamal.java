/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elliptic.curve.cryptography.el.gamal;

import FileReader.FileReader;
import Message.Message;
import Point.Point;
import java.io.IOException;

/**
 *
 * @author yoga
 */
public class EllipticCurveCryptographyElGamal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FileReader fr=new FileReader();
        System.out.println(fr.getPrivateKey("pri.txt"));
        
        System.out.println(fr.getPublicKey("pub.txt",15).getX());
        
        System.out.println(fr.getPublicKey("pub.txt",15).getY());
    }
    
}
