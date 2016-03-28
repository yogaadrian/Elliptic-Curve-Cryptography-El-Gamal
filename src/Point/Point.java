/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Point;

import static java.lang.Math.pow;

/**
 *
 * @author yoga
 */
public class Point {
    private long x,y;
    private long mod;
    public Point(){
        x=0;
        y=0;
    }
    public Point(long x, long y, long mod){
        this.mod=mod;
        /*this.x=((x<0) ? x+mod : x);
        this.y=((y<0) ? y+mod : y);*/
        this.x = bePositive(x, mod);
        this.y = bePositive(y, mod);
    }
    
    public boolean isEqual(Point p){
        if(x==p.getX() && y==p.getY())
            return true;
        else
            return false;
    }
    
    private long bePositive(long a, long mod) {
        while ( a < 0 ) {
            a = a + mod;
        }
        return a;
    }
    
    public long getX(){
        return x;
    }
    public long getY(){
        return y;
    }
    public void setX(long x){
        //this.x=((x<0) ? x+mod : x);        
        this.x = bePositive(x, mod);
    }
    public void setY(long y){
        //this.y=((y<0) ? y+mod : y);
        this.y = bePositive(y, mod);
    }
    
    public long inversemod(long base,long mod){
        long i=1;
        long tempbase;
        if(base<0){
            tempbase=base+mod;
        }
        else
            tempbase=base;
        boolean found=false;
        while(!found){
            if((tempbase*i)%mod==1){
                break;
            }
            i++;
        }
        return i;
    }
    
    public Point add(Point p){
        if(!isEqual(p)){
            long gradien;
            long ygradien=p.getY()-this.getY();
            long xgradien=p.getX()-this.getX();
            if(xgradien==0){
                System.out.println("takhingga");
                return this;
            }            
            xgradien = bePositive(xgradien, mod);
            ygradien = bePositive(ygradien, mod);
            xgradien=inversemod(xgradien,mod);
            gradien=(ygradien*xgradien)%mod;
            long xhasil=(((long) pow((double)gradien,2))-p.getX()-x)%mod;
            long yhasil=(gradien*(p.getX()-xhasil)-p.getY())%mod;
            Point r= new Point(xhasil,yhasil,mod);
            return r;
        }
        else{
            System.out.println("fungsi add error");
            return this;
        }
    }
    
    public Point neg(){
        Point r= new Point(x,y*-1,mod);
        return r;
    }
    
    public Point subtract(Point p){
        if(!isEqual(p.neg())){
            return add(p.neg());
        }
        else{
            System.out.println("fungsi subtract error");
            return this;
        }
    }
    
    public Point kuadrat(long aElips){
        long gradien;
        long ygradien=2*y;
        long xgradien=(3*(long)pow((double)x,2))+aElips;
        xgradien=((xgradien<0) ? xgradien+mod : xgradien);
        ygradien=((ygradien<0) ? ygradien+mod : ygradien);
        ygradien=inversemod(ygradien,mod);
        gradien=(ygradien*xgradien)%mod;
        
        long xhasil=(((long) pow((double)gradien,2))-(2*x))%mod;
        long yhasil=(gradien*(x-xhasil)-y)%mod;
        Point r= new Point(xhasil,yhasil,mod);
        return r;
    }
    
    public Point multiply(long n,long aElips){
        Point p=this;
        for(int i=1;i<n;i++){
            if(isEqual(p)){
                p=kuadrat(aElips);
            }
            else{
               p=p.add(this);
            }
            //System.out.println("p: (" + p.getX() + ", " + p.getY() + ")");
        }
        return p;
    }
    
    
}
