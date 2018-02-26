package gui;

import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author DiogoCirne
 */

enum Direction {

    VERTICAL, HORIZONTAL
}

public class Ship {
    
    LinkedList<Point> posList;
    private Direction direc;
    private int length;

    public Ship(int length) {
        this.length = length;
        posList = new LinkedList<Point>();
    }

    public Direction getDirec() {
        return direc;
    }

    public void setDirec(Direction direc) {
        this.direc = direc;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public LinkedList<Point> getPosList() {
        return posList;
    }

    public void setPosList(LinkedList<Point> posList) {
        this.posList = posList;
    }
    
    public void addPos(int x, int y) {
        posList.add(new Point(x,y));
    }
    
    public void addPos(Point p) {
//        System.out.println(p);
        posList.add(p);
    }
    
    public Point getPos(int index) {
        return posList.get(index);
    }
    
    public Point getLastPos() {
        return posList.getLast();
    }
    
}
