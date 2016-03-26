/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elliptic.curve.cryptography.el.gamal;

import static java.lang.Math.sqrt;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 * @author Asus
 */
public class Message {
    String plain;    
    /* Point p[] */
    int a;
    int b;
    long p;
    int k;
    
    public Message(String msg, int a, int b, long p, int k) {
        plain = msg;
        this.a = a;
        this.b = b;
        this.p = p;
        this.k = k;
    }
    
    private int stringToInt(String s) {
        /* For security reasons, representasi maksimum 2 karakter */
        int ret = 0;
        
        for ( int i = 0; i < s.length(); i++ ) {
            ret *= 256;
            ret += (int) s.charAt(i);
        }
        
        return ret;
    }
    
    private String intToString(int l) {
        String ret = "";
        
        for ( int i = 0; i < 3; i++ ) {
            ret = (char)(l % 256) + ret;
            l = l / 256;
        }
        
        return ret;
    }
        
    private double getY(int x) {
        double y = -0.1;
        BigInteger EC;
        
        long q = (long)(Math.pow(x,3) + x*a + b)%p;   
        
        /* Karena gatau lagi cara nyari kongruensi, dicoba terus maksimal 50x */
        for (int i = 0; i < 50; i++) {
            y = sqrt(q);
            if ((int)y == y) { i = 50; }  
            q += p;
        }
                
        return y;
    }
    
    public void encode(String s) {
        String msg[] = s.split("(?<=\\G..)");
        
        for (int i = 0; i < msg.length; i++) {
            int m = stringToInt(msg[i]);
            int x = (m * k) + 1;
            double y = getY(x);
            
            int offset = 0;
            int n = 1;
            while ( (int)y != y ) {
                ++n;
                if ( n > (k - 1) ) {
                    ++offset;
                    n = 1;
                }

                x = (m * (k + offset)) + n;
                y = getY(x);            
            }
            System.out.println("y=" + y + " x="+ x);
            decode(x, (int)y);
        }
    }
    
    public void decode(int x, int y) {
        System.out.println("Str= " + intToString((x-1)/k));
    }
    
}
