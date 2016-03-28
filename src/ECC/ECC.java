/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECC;

import Message.Pair;
import Point.Point;
import java.util.ArrayList;
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
    
    public Point getPub() {
        return pub;
    }
    
    public long getP(){
        return p;
    }
 
    public int getA(){
        return (int)a;
    }
    
    public int getB(){
        return (int)b;
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
    
    public Pair[] encyrpt(Point[] plain, int k) {
        ArrayList<Pair> ret = new ArrayList<>();

        for (Point p: plain) {        
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
            Point f = base.multiply(k, a);
            Point s = p.add(pub.multiply(k, a));
            System.out.println("{ (" + f.getX() + ", " + f.getY() + "), (" + s.getX() + ", " + s.getY() + ") }");
            ret.add(new Pair(f, s));
        }
        System.out.println("is here?");
        Pair[] pair=new Pair[ret.size()];
        pair=ret.toArray(pair);
        return pair;
    }
    
    public Point[] decrypt(ArrayList<Pair> cipher, int k) {
        ArrayList<Point> ret = new ArrayList<>();
        
        for (Pair p: cipher) {
            System.out.println("{ (" + (p.first()).getX() + ", " + (p.first()).getY() + "), (" + (p.second()).getX() + ", " + (p.second()).getY() + ") }");
            Point bkB = (base.multiply(k, a)).multiply(pri, a);
            bkB = (p.second()).subtract(bkB);
            System.out.println("(" + bkB.getX() + ", " + bkB.getY() + ")");
            
            ret.add(bkB);
        }
        
        Point[] r = new Point[ret.size()];
        r = ret.toArray(r);
        
        return r;        
    }
}
