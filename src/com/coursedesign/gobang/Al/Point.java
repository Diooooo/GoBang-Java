package com.coursedesign.gobang.Al;

/**
 * Created by lenovo on 2016/10/13.
 */
public class Point {
    private int position;
    private int score;

    public Point(int position, int score) {
        this.position = position;
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }
}
