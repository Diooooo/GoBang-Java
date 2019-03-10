package com.coursedesign.gobang.UI;

import com.coursedesign.gobang.Al.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/10/6.
 */
public class Board extends JPanel {

    public final int LATTICE_WIDTH = 35;
    private ArrayList<Circle> chesses;
    private int chessType = 0;

    public Board(){
        Search search = new Search();

        setBackground(Color.white.darker());
        setBounds(0, 0, 560, 560);

        chesses = new ArrayList<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = getX(e.getX());
                int y = getY(e.getY());
                if(x < 0 || y < 0){
                    return;
                }
                search.chessbord[y][x] = 0;
                search.update(x, y);
                if(search.win(search.chessbord, x + y * 15, 0)) {
                    System.out.println("you win");
                    JDialog result = new JDialog();
                    result.setBounds(300, 400, 200, 100);
                    result.setResizable(false);
                    result.setTitle("Game End");
                    result.add(new JLabel("You Win!"));
                    result.setVisible(true);
                }

                System.out.println("human " + x + "," + y);
//                for(int i = 0; i < 15; i ++){
//                    for(int j = 0; j < 15; j++){
//                        System.out.print(search.chessbord[i][j] + "\t");
//                    }
//                    System.out.print("\t\t");
//                    for(int j = 0; j < 15; j++){
//                        System.out.print(search.humScore[i][j] + "\t");
//                    }
//                    System.out.print("\t\t");
//                    for(int j = 0; j < 15; j++){
//                        System.out.print(search.comScore[i][j] + "\t");
//                    }
//                    System.out.println();
//                }
                add(x, y, 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int pos = search.maxmin(search.chessbord, 4);
                int x = pos % 15;
                int y = pos / 15;

                search.chessbord[y][x] = 1;
                if(search.win(search.chessbord, x + y * 15, 1)) {
                    System.out.println("computer win");
                    JDialog result = new JDialog();
                    result.setBounds(300, 400, 200, 100);
                    result.setResizable(false);
                    result.setTitle("Game End");
                    result.add(new JLabel("You Lose!"));
                    result.setVisible(true);
                }
                search.update(x, y);
                System.out.println("computer " + x + "," + y);
//                for(int i = 0; i < 15; i ++){
//                    for(int j = 0; j < 15; j++){
//                        System.out.print(search.chessbord[i][j] + "\t");
//                    }
//                    System.out.print("\t\t");
//                    for(int j = 0; j < 15; j++){
//                        System.out.print(search.humScore[i][j] + "\t");
//                    }
//                    System.out.print("\t\t");
//                    for(int j = 0; j < 15; j++){
//                        System.out.print(search.comScore[i][j] + "\t");
//                    }
//                    System.out.println();
//                }
                add(x, y, 1);
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i < 15; i++){
            g2.drawLine(LATTICE_WIDTH, LATTICE_WIDTH * (i + 1), 560 - LATTICE_WIDTH, LATTICE_WIDTH * (i + 1));
            g2.drawLine(LATTICE_WIDTH * (i + 1), LATTICE_WIDTH, LATTICE_WIDTH * (i + 1), 560 - LATTICE_WIDTH);
        }
        g2.drawRect(278, 278, 4, 4);
        g2.drawRect(138, 138, 4, 4);
        g2.drawRect(418, 138, 4, 4);
        g2.drawRect(138, 418, 4, 4);
        g2.drawRect(418, 418, 4, 4);

        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(8 * LATTICE_WIDTH - 17, 8 * LATTICE_WIDTH - 17, 34, 34));

        for(Circle chess : chesses){
            if(chess.getType() == 0){
                g2.setColor(Color.white);
            }else if(chess.getType() == 1){
                g2.setColor(Color.black);
            }
            g2.fill(chess.getCircle());
        }
    }
    public int getX(int x){
        if(x < LATTICE_WIDTH / 2 || x > LATTICE_WIDTH / 2 + LATTICE_WIDTH * 15){
            return -1;
        }
        if(x - x / LATTICE_WIDTH * LATTICE_WIDTH > LATTICE_WIDTH / 2){
            return x / LATTICE_WIDTH;
        }
        return x / LATTICE_WIDTH - 1;
    }

    public int getY(int y){
        if(y < LATTICE_WIDTH / 2 || y > LATTICE_WIDTH / 2 + LATTICE_WIDTH * 15){
            return -1;
        }
        if(y - y / LATTICE_WIDTH * LATTICE_WIDTH > LATTICE_WIDTH / 2){
            return y / LATTICE_WIDTH;
        }
        return y / LATTICE_WIDTH - 1;
    }

    public void add(int X, int Y, int type){
        chessType = type;
        int x = (X + 1) * LATTICE_WIDTH - 17;
        int y = (Y + 1) * LATTICE_WIDTH - 17;
        chesses.add(new Circle(x, y, 34, type));
        repaint();
    }
}
