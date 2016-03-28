/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elliptic.curve.cryptography.el.gamal;

import ECC.ECC;
import FileReader.FileReader;
import Message.Message;
import Message.Pair;
import Point.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
       
        Point m = new Point(3, 4, 100003);
        Point g = new Point(2, 6, 100003);
        Point h = new Point(2, 6, 100003);
        Point i = m.subtract(g);
              i = i.add(h);
        System.out.println("Sub: (" + i.getX() + ", " + i.getY() + ")");       
        
        ECC ecc = new ECC(1, 2, 2000003);
        ecc.setBase(new Point(1,4, 2000003));
        ecc.setPrivateKey(21);
        ecc.generatePublicKey(21);
        System.out.println("Pub: (" + ecc.getPub().getX() + ", " + ecc.getPub().getY() + ")");
        
        Point p = new Point(262132, 5621, 2000003);
        Point[] pl = new Point[1];
        pl[0] = p;
        Pair[] pa = ecc.encyrpt(pl, 10);
        Point[] po = ecc.decrypt(new ArrayList<Pair>(Arrays.asList(pa)), 10);
        
        //Point add = new Point();
        
    }
    
}
