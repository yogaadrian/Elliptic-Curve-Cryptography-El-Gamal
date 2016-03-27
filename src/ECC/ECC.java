/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECC;

import Point.Point;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class ECC {
    long pri;
    Point pub;
    
    long a;
    long b;
    long p;    
    
    Point base;
    private Random generator = new Random();
    
    public ECC (long a, long b, long p) {
        this.a = a;
        this.b = b;
        this.p = p;
    }
    
    public void setBase (Point p) {
        base = p;
    }
    
    public long generatePrivateKey() {
        pri = (generator.nextInt()*5) % (p-1);
        return pri;
    }
    
    public Point generatePublicKey(long pri) {
        pub = base.multiply(pri, a);
        return pub;
    }
    
    public void setPrivateKey(long pri) {
        this.pri = pri;
    }

    public void setPublicKey(Point pub) {
        this.pub = pub;
    }
    
}
