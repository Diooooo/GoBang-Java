package com.coursedesign.gobang.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2016/10/6.
 */
public class MainFrame extends JFrame {

    private Container con;
    private Board board;

    public MainFrame(String title){
        con = this.getContentPane();
        con.setLayout(null);

        board = new Board();
        con.add(board);

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(566, 595);

        //窗体居中
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();

        if (frameSize.height > screenSize.height){
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width){
            frameSize.width = screenSize.width;
        }

        this.setLocation((screenSize.width - frameSize.width) / 2 ,(screenSize.height - frameSize.height) / 2);
    }

    public static void main(String[] args){
        JFrame main = new MainFrame("五子棋");
        main.setVisible(true);
    }
}
