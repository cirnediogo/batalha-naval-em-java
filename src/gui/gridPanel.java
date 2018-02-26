/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * gridPanel.java
 *
 * Created on May 31, 2012, 10:24:44 PM
 */
package gui;

/**
 *
 * @author DiogoCirne
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Random;
import org.openide.util.ImageUtilities;

/**
 *
 * @author DiogoCirne
 */
public class gridPanel extends javax.swing.JPanel {

    private LinkedList<Integer> xPos;
    private LinkedList<Integer> yPos;
    private int cellSize;
    private LinkedList<Color> cellColor;
    private int nCells;
    private int maxFieldSize;
    private Point totalSize;
    private int spaceWidth = 2;
    private char letters[] = {'A', 'E', 'I', 'O', 'U'};
    private char numbers[] = {'1', '2', '3', '4', '5'};
    private LinkedList<Ship> ships;
    private LinkedList<Point> occupied;

    /** Creates new form gridPanel */
    public gridPanel() {
        initComponents();
        xPos = new LinkedList<Integer>();
        yPos = new LinkedList<Integer>();
//        cellSize = new LinkedList<Integer>();
        cellColor = new LinkedList<Color>();
//        Rectangle bounds = this.getBounds();
//        System.out.println(this.getWidth()+","+this.getHeight());
        nCells = 0;
        totalSize = new Point(0, 0);
        ships = new LinkedList<Ship>();
        occupied = new LinkedList<Point>();
//        createShips();

    }

    public void createShips() {
        int[] qntShip = {2, 1};
        Ship ship;
        LinkedList<Point> usedPoints = new LinkedList<Point>();
        Random randomGenerator = new Random();
        for (int length = 3; length <= qntShip.length + 2; length++) {
            for (int i = 0; i < qntShip[length - 3]; i++) {
//                int length = 2;
                ship = new Ship(length);
                //            for (int idx = 1; idx <= 10; ++idx){
                int direc = randomGenerator.nextInt(2);
                switch (direc) {
                    case 0:
                        ship.setDirec(Direction.HORIZONTAL);
//                        System.out.println("horizontal, length = "+length);
                        break;
                    case 1:
                        ship.setDirec(Direction.VERTICAL);
//                        System.out.println("vertical, length = "+length);
                        break;
                }
                Point[] shipPoints = new Point[length];
                boolean posOK = false;
                while (!posOK) {
                    posOK = true;
                    int x = randomGenerator.nextInt(ship.getDirec().equals(Direction.HORIZONTAL) ? nCells+1-length : nCells);
                    int y = randomGenerator.nextInt(ship.getDirec().equals(Direction.VERTICAL) ? nCells+1-length : nCells);
//                    System.out.println(x+","+y);
                    shipPoints[0] = new Point(x, y);
                    if (usedPoints.contains(shipPoints[0])) {
                        posOK = false;
                    }
                    for (int j = 1; j < length; j++) {
                        if (ship.getDirec().equals(Direction.HORIZONTAL)) {
                            x++;
                        }
                        if (ship.getDirec().equals(Direction.VERTICAL)) {
                            y++;
                        }
                        shipPoints[j] = new Point(x, y);
                        if (usedPoints.contains(shipPoints[j])) {
                            posOK = false;
                        }
                        //                      if (ship.getDirec().equals(Direction.VERTICAL))
                    }
                }
                for (Point point : shipPoints) {
                    usedPoints.add(point);
                    ship.addPos(point);
                }
                occupied.addAll(usedPoints);
                ships.add(ship);
            }
        }


    }

//    public void printSize() {
//        System.out.println(this.getWidth()+","+this.getHeight());
//    }
    public void paintComponent(Graphics g) {
        Point newSize = new Point(this.getWidth(), this.getHeight());
        if (!newSize.equals(totalSize) && !xPos.isEmpty()) {
            this.refillGrid();
        }
        super.paintComponent(g);
//        g.setColor(Color.white);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, maxFieldSize, maxFieldSize);
        g.setColor(this.getBackground());
        g.fillRect(0, 0, cellSize + spaceWidth, cellSize + spaceWidth);
        g.setFont(new Font("TimesRoman", Font.BOLD, (int) (0.7 * cellSize)));
        for (int i = 1; i <= nCells; i++) {
            g.setColor(this.getBackground());
            g.fillRect(i * cellSize + (i + 1) * spaceWidth, spaceWidth, cellSize, cellSize);
            g.fillRect(spaceWidth, i * cellSize + (i + 1) * spaceWidth, cellSize, cellSize);

            g.setColor(Color.BLACK);
            g.drawChars(letters, i - 1, 1, (int) (i * cellSize + (i + 1) * spaceWidth + 0.3 * cellSize), (int) (0.8 * cellSize));
            g.drawChars(numbers, i - 1, 1, (int) (0.4 * cellSize), (int) (i * cellSize + (i + 1) * spaceWidth + 0.8 * cellSize));

        }
        for (int i = 0; i < xPos.size(); i++) {
            if (cellColor.get(i).equals(Color.RED)) {
                Image img = ImageUtilities.loadImage("imgs/explosion.png").getScaledInstance(cellSize, cellSize, 0);
                g.drawImage(img, xPos.get(i), yPos.get(i), this);
            } else if (cellColor.get(i).equals(Color.CYAN)) {
                Image img = ImageUtilities.loadImage("imgs/water2.jpg").getScaledInstance(cellSize, cellSize, 0);
                g.drawImage(img, xPos.get(i), yPos.get(i), this);
            } else {
                g.setColor(cellColor.get(i));

//            System.out.println("imprimindo aresta "+i+": "+x1.get(i)+","+y1.get(i)+","+ x2.get(i)+","+ y2.get(i)+corAresta.get(i).toString());
//            if (i == 15) {
//                    Image img = ImageUtilities.loadImage("imgs/ship_front.png").getScaledInstance(cellSize, cellSize, 0);
//                    g.drawImage(img, xPos.get(i), yPos.get(i), this);
//            } else if (i == 16) {
//                    Image img = ImageUtilities.loadImage("imgs/ship_middle.png").getScaledInstance(cellSize, cellSize, 0);
//                    g.drawImage(img, xPos.get(i), yPos.get(i), this);
//            } else if (i == 17) {
//                    Image img = ImageUtilities.loadImage("imgs/ship_middle.png").getScaledInstance(cellSize, cellSize, 0);
//                    g.drawImage(img, xPos.get(i), yPos.get(i), this);
//            } else if (i == 18) {
//                    Image img = ImageUtilities.loadImage("imgs/ship_back.png").getScaledInstance(cellSize, cellSize, 0);
//                    g.drawImage(img, xPos.get(i), yPos.get(i), this);
//            } else {
                g.fillRect(xPos.get(i), yPos.get(i), cellSize, cellSize);
            }
        }
        for (int i = 0; i < ships.size(); i++) {
            Point p = ships.get(i).getPos(0);
            int cellIndex = p.y * nCells + p.x;
            Point coord = coordCell(p);
            Image img;
            if (cellColor.get(cellIndex).equals(Color.RED)) {
                img = ImageUtilities.loadImage("imgs/ship_front_down.png").getScaledInstance(cellSize, cellSize, 0);
            } else {
                img = ImageUtilities.loadImage("imgs/ship_front.png").getScaledInstance(cellSize, cellSize, 0);
            }
//            g.drawImage(img, coord.x, coord.y, this);
            if (ships.get(i).getDirec().equals(Direction.HORIZONTAL)) {
                g.drawImage(img, coord.x, coord.y, this);
            } else {
                Graphics2D g2d = (Graphics2D) g;
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.setToTranslation((coord.x), (coord.y));
                affineTransform.rotate(Math.toRadians(90), cellSize / 2.0, cellSize / 2.0);
                g2d.drawImage(img, affineTransform, this);

//                g2d.translate((coord.x + 1) * cellSize + (coord.x + 2) * spaceWidth + cellSize / 2, (coord.y + 1) * cellSize + (coord.y + 2) * spaceWidth + cellSize / 2);
//                g2d.rotate(Math.PI / 2);
//                g2d.drawImage(img, 0, 0, this);
//                g2d.rotate(-Math.PI / 2);
//                g2d.translate(-((coord.x + 1) * cellSize + (coord.x + 2) * spaceWidth + cellSize / 2), -((coord.y + 1) * cellSize + (coord.y + 2) * spaceWidth + cellSize / 2));
            }

            p = ships.get(i).getLastPos();
            cellIndex = p.y * nCells + p.x;
            coord = coordCell(p);
            if (cellColor.get(cellIndex).equals(Color.RED)) {
                img = ImageUtilities.loadImage("imgs/ship_back_down.png").getScaledInstance(cellSize, cellSize, 0);
            } else {
//                img = ImageUtilities.loadImage("imgs/ship_front.png").getScaledInstance(cellSize, cellSize, 0);
                img = ImageUtilities.loadImage("imgs/ship_back.png").getScaledInstance(cellSize, cellSize, 0);
            }
            if (ships.get(i).getDirec().equals(Direction.HORIZONTAL)) {
                g.drawImage(img, coord.x, coord.y, this);
            } else {
                Graphics2D g2d = (Graphics2D) g;
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.setToTranslation((coord.x), (coord.y));
                affineTransform.rotate(Math.toRadians(90), cellSize / 2.0, cellSize / 2.0);
                g2d.drawImage(img, affineTransform, this);

//                g2d.translate((coord.x + 1) * cellSize + (coord.x + 2) * spaceWidth + cellSize / 2, (coord.y + 1) * cellSize + (coord.y + 2) * spaceWidth + cellSize / 2);
//                g2d.rotate(Math.PI / 2);
//                g2d.drawImage(img, 0, 0, this);
//                g2d.rotate(-Math.PI / 2);
//                g2d.translate(-((coord.x + 1) * cellSize + (coord.x + 2) * spaceWidth + cellSize / 2), -((coord.y + 1) * cellSize + (coord.y + 2) * spaceWidth + cellSize / 2));
            }

            for (int j = 1; j < ships.get(i).getLength() - 1; j++) {
                p = ships.get(i).getPos(j);
                cellIndex = p.y * nCells + p.x;
                coord = coordCell(p);
                if (cellColor.get(cellIndex).equals(Color.RED)) {
                    img = ImageUtilities.loadImage("imgs/ship_middle_down.png").getScaledInstance(cellSize, cellSize, 0);
                } else {
                    img = ImageUtilities.loadImage("imgs/ship_middle.png").getScaledInstance(cellSize, cellSize, 0);
                }
                if (ships.get(i).getDirec().equals(Direction.HORIZONTAL)) {
                    g.drawImage(img, coord.x, coord.y, this);
                } else {
                    Graphics2D g2d = (Graphics2D) g;
                    AffineTransform affineTransform = new AffineTransform();
                    affineTransform.setToTranslation((coord.x), (coord.y));
                    affineTransform.rotate(Math.toRadians(90), cellSize / 2.0, cellSize / 2.0);
                    g2d.drawImage(img, affineTransform, this);

//                g2d.translate((coord.x + 1) * cellSize + (coord.x + 2) * spaceWidth + cellSize / 2, (coord.y + 1) * cellSize + (coord.y + 2) * spaceWidth + cellSize / 2);
//                g2d.rotate(Math.PI / 2);
//                g2d.drawImage(img, 0, 0, this);
//                g2d.rotate(-Math.PI / 2);
//                g2d.translate(-((coord.x + 1) * cellSize + (coord.x + 2) * spaceWidth + cellSize / 2), -((coord.y + 1) * cellSize + (coord.y + 2) * spaceWidth + cellSize / 2));
                }
            }

        }

    }

    public Point coordCell(Point indexCell) {
        int x = (indexCell.x + 1) * cellSize + (indexCell.x + 2) * spaceWidth;
        int y = (indexCell.y + 1) * cellSize + (indexCell.y + 2) * spaceWidth;
        return new Point(x, y);
    }

    public void drawSquare(Graphics g, int x, int y, Color col) {

        xPos.add(x);
        yPos.add(y);
        cellColor.add(col);

        super.paintComponent(g);
//        g.setColor(Color.white);
//        g.fillRect(x,y,size,size);
    }

    public void fillGrid(int nCells) {
//        System.out.println("fillGrid");
        this.nCells = nCells;
//        int spaceWidth = 2;
        int minSize = Math.min(this.getWidth(), this.getHeight());
//        double d = 2.4;
//        int a = Integer.d.
        cellSize = (int) Math.floor((minSize - (nCells + 2) * spaceWidth) / (nCells + 1));
        maxFieldSize = (nCells + 1) * cellSize + (nCells + 2) * spaceWidth;
        for (int i = 1; i <= nCells; i++) {
            for (int j = 1; j <= nCells; j++) {

                this.drawSquare(this.getGraphics(), j * cellSize + (j + 1) * spaceWidth, i * cellSize + (i + 1) * spaceWidth, Color.WHITE);

            }
        }
        totalSize = new Point(this.getWidth(), this.getHeight());
    }

    public void refillGrid() {
//        System.out.println("refillGrid");
//        int spaceWidth = 2;
        int minSize = Math.min(this.getWidth(), this.getHeight());
//        double d = 2.4;
//        int a = Integer.d.
        cellSize = (int) Math.floor((minSize - (nCells + 2) * spaceWidth) / (nCells + 1));
        maxFieldSize = (nCells + 1) * cellSize + (nCells + 2) * spaceWidth;
        for (int i = 1; i <= nCells; i++) {
            for (int j = 1; j <= nCells; j++) {
                xPos.set((i - 1) * nCells + j - 1, j * cellSize + (j + 1) * spaceWidth);
                yPos.set((i - 1) * nCells + j - 1, i * cellSize + (i + 1) * spaceWidth);
//                this.drawSquare(this.getGraphics(), (j-1)*cellSize+j*spaceWidth, (i-1)*cellSize+i*spaceWidth, Color.WHITE);
            }
        }
        totalSize = new Point(this.getWidth(), this.getHeight());
    }

    public char[] getLetters() {
        return letters;
    }

    public char[] getNumbers() {
        return numbers;
    }

    public int getnCells() {
        return nCells;
    }

    public void setCellColor(int index, Color c) {
        cellColor.set(index, c);
        this.paintComponent(this.getGraphics());
    }

    public boolean isOccupied(Point p) {
        return occupied.contains(p);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
