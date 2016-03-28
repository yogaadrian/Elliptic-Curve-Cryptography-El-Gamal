/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import static java.lang.Math.sqrt;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;
import Point.Point;

/**
 *
 * @author Asus
 */
public class Message {
    String plain;    
    ArrayList<Point> message = new ArrayList<>();
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
    
    public Message(Point[] pt, int a, int b, long p, int k) {
        for (int i = 0; i < pt.length; i++) {
            message.add(pt[i]);
        }
        this.a = a;
        this.b = b;
        this.p = p;
        this.k = k;
    }
    
    public String getPlain() {
        return plain;
    }
    public Point[] getMessage(){
        Point[] points=new Point[message.size()];
        points=message.toArray(points);
        return points;
    }
    
    public void setMessage(Point[] mess){
        message=new ArrayList<Point>(Arrays.asList(mess));
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
            if (l % 256 != 0) {
                ret = (char)(l % 256) + ret;
            }
            l = l / 256;
        }
        
        return ret;
    }
        
    private double getY(int x) {
        double y = -0.1;
        BigInteger EC;
        
        long q = (long)(Math.pow(x,3) + x*a + b)%p;   
        
        /* Karena gatau lagi cara nyari kongruensi, dicoba terus maksimal 1000x */
        int n = 10000;
        for (int i = 0; i < n; i++) {
            y = sqrt(q);
            if ((int)y == y) { i = n+1; }  
            q += p;
        }
                
        return y;
    }
    
    public void encode() {
        String msg[] = plain.split("(?<=\\G..)");
        
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
            /*System.out.println("y=" + y + " x="+ x);
            System.out.println("offset=" + offset + " n="+ n);*/
            message.add(new Point((long)x, (long)y, p));
        }
    }
    
    public void decode() {
        //System.out.println("Str= " + intToString((x-1)/k));
        plain = "";
        for ( Point p: message ){
            plain += intToString(((int)p.getX()-1)/k);
        }
    }
    
}
