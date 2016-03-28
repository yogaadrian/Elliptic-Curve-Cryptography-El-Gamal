/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import Point.Point;

/**
 *
 * @author Asus
 */
public class Pair {
    private final Point first;
    private final Point second;

    public Pair(Point first, Point second) {
        this.first   = first;
        this.second  = second;
    }

    public Point first()  { return first; }
    public Point second() { return second; }
}
