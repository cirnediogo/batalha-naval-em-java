package gui;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author DiogoCirne
 */
public class Processamento {
    
    private LinkedList<Point> received;
    private LinkedList<Point> sent;
    private gridPanel field;
    private gridPanel enemyField;

    public Processamento(gridPanel field, gridPanel enemyField) {
        this.field = field;
        this.enemyField = enemyField;
        this.sent = new LinkedList<Point>();
        this.received = new LinkedList<Point>();
    }
    
    public Point getCellPoint(String cell) {
        char[] letters = field.getLetters();
        char[] numbers = field.getNumbers();
        char l = cell.charAt(0);
        char n = cell.charAt(1);
        int x = -1;
        int y = -1;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == l) x = i;
            if (numbers[i] == n) y = i;
        }
        Point p = new Point(x,y);
        return p;
    }
    
    public void play(String cell, String res) {
        Point p = getCellPoint(cell);
        System.out.println("atacou celula "+p);
        sent.add(p);
        int cellIndex = p.y*enemyField.getnCells()+p.x;
        if (res.equals("acertou"))
            enemyField.setCellColor(cellIndex, Color.RED);
        else
            enemyField.setCellColor(cellIndex, Color.CYAN);
    }
    
    public String confere(String cell) {
        String res = "";
        Point p = getCellPoint(cell);
        int cellIndex = p.y*enemyField.getnCells()+p.x;
        if (field.isOccupied(p)) {
            res = "acertou";
            field.setCellColor(cellIndex, Color.RED);
        } else {
            res = "errou";
            field.setCellColor(cellIndex, Color.CYAN);
        }
        return res;
    }
    
    public String preWrite(String cell) {
        String res = cell;
        Point p = getCellPoint(cell);
        System.out.println(p);
        if (p.x == -1 || p.y == -1) res = "célula inexistente";
        else if (sent.contains(p)) res = "ataque duplicado";
        return res;
    }
    
}
