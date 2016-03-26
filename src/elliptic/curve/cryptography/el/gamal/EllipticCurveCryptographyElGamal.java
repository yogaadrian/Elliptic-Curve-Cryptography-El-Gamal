/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elliptic.curve.cryptography.el.gamal;

import Message.Message;
import Point.Point;

/**
 *
 * @author yoga
 */
public class EllipticCurveCryptographyElGamal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Message m = new Message("hai apakabar", -1, 1, 1283, 10);
        m.encode();
        m.decode();
        System.out.println(m.getPlain());
    }
    
}
