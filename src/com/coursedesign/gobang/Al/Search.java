package com.coursedesign.gobang.Al;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * Created by lenovo on 2016/10/7.
 */
public class Search {

    /**** 各棋型估分 ****/
    public static final int ONE = 10;
    public static final int TWO = 100;
    public static final int THREE = 1000;
    public static final int FOUR = 100000;
    public static final int FIVE = 1000000;

    public static final int BLOCK_ONE = 1;
    public static final int BLOCK_TWO = 10;
    public static final int BLOCK_THREE = 100;
    public static final int BLOCK_FOUR = 10000;

    /**** 棋面状态 ****/
    public int[][] chessbord = {
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
    };

    /**** 电脑估分 ****/
    public int[][] comScore = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    /**** 玩家估分 ****/
    public int[][] humScore = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    /**
     *
     * @param count 总棋子数
     * @param block 端点数
     * @param empty 连子数
     * @return 单方向棋型估分（横，竖，左斜，右斜）
     */
    public static int typeScore(int count, int block, int empty){
        if(empty <= 0){
            if(count >= 5) {
                return FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 1:
                        return ONE;
                    case 2:
                        return TWO;
                    case 3:
                        return THREE;
                    case 4:
                        return FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 1:
                        return BLOCK_ONE;
                    case 2:
                        return BLOCK_TWO;
                    case 3:
                        return BLOCK_THREE;
                    case 4:
                        return BLOCK_FOUR;
                }
            }

        }else if(empty == 1 || empty == count - 1){
            if(count >= 6) {
                return FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 2:
                        return TWO/2;
                    case 3:
                        return THREE;
                    case 4:
                        return BLOCK_FOUR;
                    case 5:
                        return FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 2:
                        return BLOCK_TWO;
                    case 3:
                        return BLOCK_THREE;
                    case 4:
                        return BLOCK_FOUR;
                    case 5:
                        return BLOCK_FOUR;
                }
            }
        }else if(empty == 2 || empty == count - 2){
            if(count >= 7) {
                return FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 3:
                        return THREE;
                    case 4:
                    case 5:
                        return BLOCK_FOUR;
                    case 6:
                        return FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 3:
                        return BLOCK_THREE;
                    case 4:
                        return BLOCK_FOUR;
                    case 5:
                        return BLOCK_FOUR;
                    case 6:
                        return FOUR;
                }
            }

            if(block == 2) {
                switch(count) {
                    case 4:
                    case 5:
                    case 6:
                        return BLOCK_FOUR;
                }
            }
        }else if(empty == 3 || empty == count - 3){
            if(count >= 8) {
                return FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 4:
                    case 5:
                        return THREE;
                    case 6:
                        return BLOCK_FOUR;
                    case 7:
                        return FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 4:
                    case 5:
                    case 6:
                        return BLOCK_FOUR;
                    case 7:
                        return FOUR;
                }
            }

            if(block == 2) {
                switch(count) {
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        return BLOCK_FOUR;
                }
            }
        }else if(empty == 4 || empty == count - 4){
            if(count >= 9) {
                return FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        return FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        return BLOCK_FOUR;
                    case 8:
                        return FOUR;
                }
            }

            if(block == 2) {
                switch(count) {
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        return BLOCK_FOUR;
                }
            }
        }else if(empty == 5 || empty == count - 5){
            return FIVE;
        }
        return 0;
    }

    /**
     *
     * @param value 单方向棋型估分的加和
     * @return 对棋型分数进行优化，降低单冲四分数，提高冲四活三分数，提升双冲四分数
     */
    public static int valueScore(int value) {
        if(value < FOUR && value >= BLOCK_FOUR) {
            if(value >= BLOCK_FOUR && value < (BLOCK_FOUR + THREE)) {
                //单独冲四，意义不大
                return THREE;
            } else if(value >= BLOCK_FOUR + THREE && value < BLOCK_FOUR * 2) {
                return FOUR;  //冲四活三，比双三分高，相当于自己形成活四
            } else {
                //双冲四 比活四分数也高
                return FOUR * 2;
            }
        }
        return value;
    }

    /**
     *
     * @param x
     * @param y
     * 更新human和computer对当前空位的估分
     */
    public void updatePoint(int x, int y){
        int cS = evaluatePoint(chessbord, x + y * 15, 1);
        int hS = evaluatePoint(chessbord, x + y * 15, 0);
        comScore[y][x] = cS;
        humScore[y][x] = hS;
    }

    /**
     *
     * @param x
     * @param y
     * 更新human和computer对棋面各空位的估分
     */
    public void update(int x, int y){
        int radius = 8;
        int length = 15;

        for(int i = -radius; i < radius; i++){
            int posX = x;
            int posY = y + i;
            if(posY < 0)
                continue;
            if(posY >= length)
                break;
            if(chessbord[posY][posX] != 2) {
                humScore[posY][posX] = 0;
                comScore[posY][posX] = 0;
                continue;
            }
            updatePoint(posX, posY);
        }

        for(int i = -radius; i < radius; i++){
            int posX = x + i;
            int posY = y;
            if(posX < 0)
                continue;
            if(posX >= length)
                break;
            if(chessbord[posY][posX] != 2) {
                humScore[posY][posX] = 0;
                comScore[posY][posX] = 0;
                continue;
            }
            updatePoint(posX, posY);
        }

        for(int i = -radius; i < radius; i++){
            int posX = x + i;
            int posY = y + i;
            if(posY < 0 || posX < 0)
                continue;
            if(posY >= length || posX >= length)
                break;
            if(chessbord[posY][posX] != 2) {
                humScore[posY][posX] = 0;
                comScore[posY][posX] = 0;
                continue;
            }
            updatePoint(posX, posY);
        }

        for(int i = -radius; i < radius; i++){
            int posX = x - i;
            int posY = y + i;
            if(posY < 0 || posX >= length)
                continue;
            if(posY >= length || posX < 0)
                break;
            if(chessbord[posY][posX] != 2) {
                humScore[posY][posX] = 0;
                comScore[posY][posX] = 0;
                continue;
            }
            updatePoint(posX, posY);
        }
    }

    /**
     *
     * @param board
     * @param yourType
     * @param oppositeType
     * @return 棋面估分（目前仅应用到电脑对棋局的估分）
     */
    public int evaluate(int[][] board, int yourType, int oppositeType){
        /*棋局的估分*/
        int value;

        int computerMax = -FIVE;
        int humanMax = -FIVE;

        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                if(board[j][i] == 2){
                    computerMax = comScore[j][i] > computerMax ? comScore[j][i] : computerMax;
                    humanMax = humScore[j][i] > humanMax ? humScore[j][i] : humanMax;
                }
            }
        }
        value = yourType > oppositeType ? computerMax - humanMax : humanMax - computerMax;

        return value;
    }

    /**
     *
     * @param board
     * @param position
     * @param type
     * @return 当前空位的估分
     */
    public int evaluatePoint(int[][] board, int position, int type){
        int value = 0;
        int count = 1;
        int secondCount = 0;
        int block = 0;
        int empty = -1;
        int length = 15;

        int x = position % 15;
        int y = position / 15;

        /*纵向*/

        /*此棋子↓*/
        for(int i = y + 1; ; i++){
            if(i >= length){
                block++;
                break;
            }

            int chessType = board[i][x];

            if(chessType == 2){
                if(empty == -1 && i < length - 1 && board[i + 1][x] == type){
                    empty = count;
                    continue;
                }else
                    break;
            }else if(chessType == type){
                count++;
                continue;
            }else{
                block++;
                break;
            }
        }

        /*此棋子↑*/
        for(int i = y - 1;  ;i--){
            if(i < 0){
                block++;
                break;
            }

            int chessType = board[i][x];

            if(chessType == 2){
                if(empty == -1 && i > 0 && board[i - 1][x] == type){
                    empty = 0;
                    continue;
                }else
                    break;
            }else if(chessType == type){
                secondCount++;
                if(empty != -1)
                    empty++;
                continue;
            }else{
                block++;
                break;
            }
        }

        count += secondCount;
        value += typeScore(count, block, empty);

        /*重置计数变量*/
        count = 1;
        block = 0;
        empty = -1;
        secondCount = 0;

        /*横向*/

        /*此棋子→*/
        for(int i = x + 1; ; i++){
            if(i >= length){
                block++;
                break;
            }

            int chessType = board[y][i];

            if(chessType == 2){
                if(empty == -1 && i < length - 1 && board[y][i + 1] == type){
                    empty = count;
                    continue;
                }else
                    break;
            }else if(chessType == type){
                count++;
                continue;
            }else{
                block++;
                break;
            }
        }
        /*此棋子←*/
        for(int i = x - 1;  ;i--){
            if(i < 0){
                block++;
                break;
            }

            int chessType = board[y][i];

            if(chessType == 2){
                if(empty == -1 && i > 0 && board[y][i - 1] == type){
                    empty = 0;
                    continue;
                }else
                    break;
            }else if(chessType == type){
                secondCount++;
                if(empty != -1)
                    empty++;
                continue;
            }else{
                block++;
                break;
            }
        }

        count += secondCount;
        value += typeScore(count, block, empty);

        /*重置计数变量*/
        count = 1;
        block = 0;
        empty = -1;
        secondCount = 0;

        /*右斜*/

        /*此棋子下方*/
        for(int i = 1; ; i++){
            int posX = x + i;
            int posY = y + i;
            if(posX >= length || posY >= length){
                block++;
                break;
            }

            int chessType = board[posY][posX];

            if(chessType == 2) {
                if(empty == -1 && (posX < length - 1 && posY < length - 1) && board[posY + 1][posX + 1] == type) {
                    empty = count;
                    continue;
                } else {
                    break;
                }
            }else if(chessType == type){
                count++;
                continue;
            }else{
                block++;
                break;
            }

        }

        /*此棋子上方*/
        for(int i = 1; ; i++){
            int posX = x - i;
            int posY = y - i;
            if(posX < 0 || posY < 0){
                block++;
                break;
            }

            int chessType = board[posY][posX];

            if(chessType == 2) {
                if(empty == -1 && (posX > 0 && posY > 0) && board[posY - 1][posX - 1] == type) {
                    empty = 0;
                    continue;
                } else {
                    break;
                }
            }else if(chessType == type){
                secondCount++;
                if(empty != -1)
                    empty++;
                continue;
            }else{
                block++;
                break;
            }
        }

        count += secondCount;
        value += typeScore(count, block, empty);

        /*重置计数变量*/
        count = 1;
        block = 0;
        empty = -1;
        secondCount = 0;

        /*左斜*/

        /*此棋子下方*/
        for(int i = 1; ; i++){
            int posX = x - i;
            int posY = y + i;
            if(posX < 0 || posY >= length){
                block++;
                break;
            }

            int chessType = board[posY][posX];

            if(chessType == 2) {
                if(empty == -1 && (posX > 0 && posY < length - 1) && board[posY + 1][posX - 1] == type) {
                    empty = count;
                    continue;
                } else {
                    break;
                }
            }else if(chessType == type){
                count++;
                continue;
            }else{
                block++;
                break;
            }

        }

        /*此棋子上方*/
        for(int i = 1; ; i++) {
            int posX = x + i;
            int posY = y - i;
            if (posX >= length || posY < 0) {
                block++;
                break;
            }

            int chessType = board[posY][posX];

            if (chessType == 2) {
                if (empty == -1 && (posX < length - 1 && posY > 0) && board[posY - 1][posX + 1] == type) {
                    empty = 0;
                    continue;
                } else {
                    break;
                }
            } else if (chessType == type) {
                secondCount++;
                if (empty != -1)
                    empty++;
                continue;
            } else {
                block++;
                break;
            }
        }

        count += secondCount;
        value += typeScore(count, block, empty);

        return valueScore(value);
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param count
     * @return 当前空位四周是否有棋子（任意颜色）
     */
    public boolean hasNeighbor(int x, int y, int width, int count){
        for(int i = x - width; i <= x + width; i++){
            if(i < 0)
                continue;
            if(i >= 15)
                break;
            for(int j = y - width; j <= y + width; j++){
                if(j < 0)
                    continue;
                if(j >= 15)
                    break;
                if(chessbord[j][i] != 2)
                    count--;
                if(count <= 0)
                    return true;
            }
        }
        return false;
    }

    /**
     * 优先度：
     * 1.电脑形成五连子
     * 2.玩家形成五连子
     * 3.电脑形成活四，冲四，双三
     * 4.玩家形成活四，冲四，双三
     * 5.一般单双子
     * @return 空棋子位
     */
    public ArrayList<Integer> generator(){
        ArrayList<Integer> fives = new ArrayList<>();
        ArrayList<Integer> fours = new ArrayList<>();
        ArrayList<Integer> blocked_fours = new ArrayList<>();
        ArrayList<Integer> twoThrees = new ArrayList<>();
        ArrayList<Integer> threes = new ArrayList<>();
        ArrayList<Integer> twos = new ArrayList<>();
        ArrayList<Integer> neighbors = new ArrayList<>();
        ArrayList<Integer> nextNeighbors = new ArrayList<>();

        ArrayList<Integer> temp = new ArrayList<>();

        int computerScore;
        int humanScore;

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(chessbord[j][i] == 2){
                    int position = i + j * 15;
                    if(hasNeighbor(i, j, 1, 1)){
                        computerScore = comScore[j][i];
                        humanScore = humScore[j][i];

                        if(computerScore >= FIVE){
                            ArrayList<Integer> temp5 = new ArrayList<>();
                            temp5.add(position);
                            return temp5;
                        }else if(humanScore >= FIVE){
                            fives.add(position);
                        }else if(computerScore >= FOUR){
                            fours.add(0, position);
                        }else if(humanScore >= FOUR){
                            fours.add(position);
                        }else if(computerScore >= BLOCK_FOUR){
                            blocked_fours.add(0, position);
                        }else if(humanScore >= BLOCK_FOUR){
                            blocked_fours.add(position);
                        }else if(computerScore >= THREE * 2){
                            twoThrees.add(0, position);
                        }else if(humanScore >= THREE * 2){
                            twoThrees.add(position);
                        }else if(computerScore >= THREE){
                            threes.add(0, position);
                        }else if(humanScore >= THREE){
                            threes.add(position);
                        }else if(computerScore >= TWO){
                            twos.add(0, position);
                        }else if(humanScore >= TWO){
                            twos.add(position);
                        }else{
                            neighbors.add(position);
                        }
                    }else if(hasNeighbor(i ,j, 2, 1)){
                        nextNeighbors.add(position);
                    }
                }
            }
        }

        if(fives.size() > 0){
            temp.add(fives.get(0));
            return temp;
        }
        if(fours.size() > 0){
            return fours;
        }
        if(blocked_fours.size() > 0){
            temp.add(blocked_fours.get(0));
            return temp;
        }
        if(twoThrees.size() > 0){
            twoThrees.addAll(threes);
            return twoThrees;
        }

        neighbors.addAll(nextNeighbors);
        twos.addAll(neighbors);
        threes.addAll(twos);
        return threes;
    }

    /**
     * Alpha - Beta 剪枝算法
     * @param board
     * @param deep 检索深度
     * @return 落子位置
     */
    public int maxmin(int[][] board, int deep){
        int bestValue = -9999999;
        ArrayList<Integer> points = generator();
        Vector<Integer> bestPoints = new Vector<>();

        for(int i = 0; i < points.size(); i++){
            int pointPos = points.get(i);
            int x = pointPos % 15;
            int y = pointPos / 15;
            board[y][x] = 1;
            int tempMax = min(board, deep - 1, -9999999, 9999999, pointPos);
            if(x < 3 || x > 11 || y < 3 || y > 11) {
                if(tempMax > 0) {
                    tempMax /= 2;
                }else{
                    tempMax *= 2;
                }
            }
            if(tempMax == bestValue){
                bestPoints.add(pointPos);
            }else if(tempMax > bestValue){
                bestValue = tempMax;
                if(!bestPoints.isEmpty())
                    bestPoints.removeAllElements();

                bestPoints.add(pointPos);
            }
            board[pointPos / 15][pointPos % 15] = 2;
        }
        System.out.println(points.size() + " " + bestPoints.size() + " " + bestValue);
        return bestPoints.get((0 + bestPoints.size()) / 2);
    }

    /**
     * 在max层调用min（）函数，返回min层的最大值
     * @param board
     * @param deep
     * @param alpha
     * @param beta
     * @param pos
     * @return 估分
     */
    public int min(int[][] board, int deep, int alpha, int beta, int pos){
        int stateValue = evaluate(board, 1, 0);
        if(deep <= 0 || win(board, pos, 0) || win(board, pos, 1)){
            return stateValue;
        }

        int bestValue = 9999999;
        ArrayList<Integer> points = generator();

        for(int i = 0; i < points.size(); i++){
            int pointPos = points.get(i);
            board[pointPos / 15][pointPos % 15] = 0;
            int tempMin = max(board, deep - 1, alpha, bestValue < beta ? bestValue : beta, pointPos);
            board[pointPos / 15][pointPos % 15] = 2;

            if(tempMin < bestValue){
                bestValue = tempMin;
            }
            if(tempMin > beta)
                break;

            if(deep + 1 == 2 && bestValue < FOUR){
                ArrayList<Point> checkmatePoints = checkmate(board, pointPos, 1, 2);
                if(checkmatePoints != null && checkmatePoints.size() > 0){
//                    System.out.println("checkmate points : " + checkmatePoints.size());
                    return FIVE;
                }
            }

        }
//        System.out.println(alpha +  " " + beta);
        return bestValue;
    }

    /**
     * 在min层调用max（）函数，返回max层的最小值
     * @param board
     * @param deep
     * @param alpha
     * @param beta
     * @param pos
     * @return 估分
     */
    public int max(int[][] board, int deep, int alpha, int beta, int pos)   {
        int stateValue = evaluate(board, 1, 0);
        if(deep <= 0 || win(board, pos, 1) || win(board, pos, 0)){
            return stateValue;
        }

        int bestValue = -9999999;
        ArrayList<Integer> points = generator();

        for(int i = 0; i < points.size(); i++){
            int pointPos = points.get(i);
            board[pointPos / 15][pointPos % 15] = 0;
            int tempMax = min(board, deep - 1,  bestValue > alpha ? bestValue : alpha, beta, pointPos);
            board[pointPos / 15][pointPos % 15] = 2;

            if(tempMax > bestValue){
                bestValue = tempMax;
            }
//            if(tempMax > beta)
//                return tempMax;
            if(tempMax < alpha)
                break;

//            if(deep == 2 && bestValue < FOUR){
//                ArrayList<Point> checkmatePoints = checkmate(board, pointPos, 1, 4);
//                if(checkmatePoints.size() > 0){
//                    System.out.println("checkmate points : " + checkmatePoints.size());
//                    return FIVE;
//                }
//            }
        }
//        System.out.println(alpha +  " " + beta);
        return bestValue;
    }

    /**
     * 胜利判断
     * @param board
     * @param pos
     * @param type
     * @return type型棋子是否连五
     */
    public boolean win(int[][] board, int pos, int type){
        int length = 15;
        int co;
        if(pos == -1){
            co = 0;
        }else{
            co = 1;
        }
        int count = co;

        int x = pos % 15;
        int y = pos / 15;

        /*纵向*/
        for(int i = y + 1; ; i++){
            if(i >= length)
                break;
            if(board[i][x] != type)
                break;
            count++;
        }
        for(int i = y - 1; ; i--){
            if(i < 0)
                break;
            if(board[i][x] != type)
                break;
            count++;
        }
        if(count >= 5)
            return true;

        /*横向*/
        count = co;
        for(int i = x + 1; ; i++){
            if(i >= length)
                break;
            if(board[y][i] != type)
                break;
            count++;
        }
        for(int i = x - 1; ; i--){
            if(i < 0)
                break;
            if(board[y][i] != type)
                break;
            count++;
        }
        if(count >= 5)
            return true;

        /*右斜*/
        count = co;
        for(int i = 1; ; i++){
            int posX = x + i;
            int posY = y + i;
            if(posX >= length || posY >= length)
                break;
            if(board[posY][posX] != type)
                break;
            count++;
        }
        for(int i = 1; ; i++){
            int posX = x - i;
            int posY = y - i;
            if(posX < 0 || posY < 0)
                break;
            if(board[posY][posX] != type)
                break;
            count++;
        }
        if(count >= 5)
            return true;

        /*左斜*/
        count = co;
        for(int i = 1; ; i++){
            int posX = x - i;
            int posY = y + i;
            if(posX < 0 || posY >= length)
                break;
            if(board[posY][posX] != type)
                break;
            count++;
        }
        for(int i = 1; ; i++){
            int posX = x + i;
            int posY = y - i;
            if(posX >= length || posY < 0)
                break;
            if(board[posY][posX] != type)
                break;
            count++;
        }
        if(count >= 5)
            return true;

        return false;
    }

    /**
     *
     * @param type 自己的棋种
     * @return 返回对手棋子棋种
     */
    public int oppositeType(int type){
        switch(type){
            case 1:
                return 0;
            case 0:
                return 1;
        }
        return -1;
    }

    /**
     * 在max层调用（电脑），寻找当前层的所有大于score的位置节点（活三，冲四）
     * @param board
     * @param type
     * @param score
     * @return
     */
    public ArrayList<Point> findMax(int[][] board, int type, int score){
        ArrayList<Point> result = new ArrayList<>();

        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[j][i] == 2) {
                    if (hasNeighbor(i, j, 2, 1)) {
                        int position = i + j * 15;
                        int tempScore = evaluatePoint(board, position, type);
                        if (tempScore >= FIVE) {
                            ArrayList<Point> temp = new ArrayList<>();
                            temp.add(new Point(position, tempScore));
                            return temp;
                        }
                        if (tempScore >= score) {
                            result.add(new Point(position, tempScore));
                        }
                    }
                }
            }
        }

            Collections.sort(result, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o2.getScore() - o1.getScore();
                }
            });

        return result;
    }

    /**
     * 在min层调用（玩家），寻找当前层的所有己方和对方的的活三，冲四节点
     * @param board
     * @param type
     * @param score
     * @return
     */
    public ArrayList<Point> findMin(int[][] board, int type, int score){
        ArrayList<Point> result = new ArrayList<>();
        ArrayList<Point> fives = new ArrayList<>();
        ArrayList<Point> fours = new ArrayList<>();

        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if(board[j][i] == 2){
                    if(hasNeighbor(i, j, 2, 1)){
                        int position = i + j * 15;
                        int computer = (type == 0 ? comScore[i][j] : humScore[i][j]);
                        int human = (type == 0 ? humScore[i][j] : comScore[i][j]);
                        if(computer >= FIVE){
                            ArrayList<Point> temp = new ArrayList<>();
                            temp.add(new Point(position, -computer));
                        }
                        if(computer >= FOUR){
                            fours.add(0, new Point(position, -computer));
                            continue;
                        }
                        if(human >= FIVE){
                            fives.add(new Point(position, human));
                            continue;
                        }
                        if(human >= FOUR){
                            fours.add(new Point(position, human));
                            continue;
                        }
                        if(computer > score || human > score){
                            result.add(new Point(position, computer));
                        }
                    }
                }
            }
        }
        if(fives.size() > 0){
            ArrayList<Point> temp = new ArrayList<>();
            temp.add(new Point(fives.get(0).getPosition(), fives.get(0).getScore()));
        }
        if(fours.size() > 0){
            ArrayList<Point> temp = new ArrayList<>();
            temp.add(new Point(fours.get(0).getPosition(), fours.get(0).getScore()));
        }

        Collections.sort(result, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o2.getScore() - o1.getScore();
            }
        });
        return result;
    }

    /**
     * 在max层调用，寻找活三冲四位置，只要找到一个便返回结果，否则返回null
     * @param board
     * @param position
     * @param type
     * @param deep
     * @param maxScore
     * @param minScore
     * @return
     */
    public ArrayList<Point> checkmateMax(int[][] board, int position, int type, int deep, int maxScore, int minScore){
        ArrayList<Point> result = new ArrayList<>();

        ArrayList<Point> points = findMax(board, type, maxScore);
        if(points.size() > 0 && points.get(0).getScore() > FOUR){
            result.add(new Point(points.get(0).getPosition(), points.get(0).getScore()));
            return result;
        }
        for(int i = 0; i < points.size(); i++){
            Point temp = points.get(i);
            int x = temp.getPosition() % 15;
            int y = temp.getPosition() / 15;
            board[y][x] = type;
            ArrayList<Point> min = checkmateMin(board, temp.getPosition(), type, deep - 1, maxScore, minScore);
            board[y][x] = 2;
            if(min != null) {
                if (!min.isEmpty()) {
                    min.add(0, temp);
                    return min;
                }else{
                    result.add(temp);
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 在min层调用，寻找双方活三冲四位置，找到一个便可以保证不在下回合输掉，直接返回即可，否则返回null
     * @param board
     * @param position
     * @param type
     * @param deep
     * @param maxScore
     * @param minScore
     * @return
     */
    public ArrayList<Point> checkmateMin(int[][] board, int position, int type, int deep, int maxScore, int minScore){
        if(deep <= 0){
            return null;
        }

        ArrayList<Point> points = findMin(board, oppositeType(type), minScore);
        if(points.size() == 0 || points.get(0).getScore() < -FOUR){
            return null;
        }
        ArrayList<Point> canBeDefenced = new ArrayList<>();
        if(win(board,position,type)){
            return canBeDefenced;
        }
        if(win(board, position, oppositeType(type))){
            return null;
        }

        for(int i = 0; i < points.size(); i++){
            Point temp = points.get(i);
            int x = temp.getPosition() % 15;
            int y = temp.getPosition() / 15;
            board[y][x] = oppositeType(type);
            ArrayList<Point> max = checkmateMax(board, temp.getPosition(), type, deep - 1, maxScore, minScore);
            board[y][x] = 2;
            if(max != null && !max.isEmpty()){
                max.add(0, temp);
                canBeDefenced.addAll(max);
                continue;
            }
            else{
                return null;
            }
        }
        return canBeDefenced;
    }

    /**
     * 迭代加深
     * @param board
     * @param position
     * @param type
     * @param deep
     * @param maxScore
     * @param minScore
     * @return
     */
    public ArrayList<Point> deeping(int[][] board, int position, int type, int deep, int maxScore, int minScore){
        ArrayList<Point> result;
        for(int i = 1; i <= deep; i++){
            result = checkmateMax(board, position, type, i, maxScore, minScore);
            if(result != null)
                return result;
        }
        return null;
    }

    /**
     * 算杀
     * @param board
     * @param position
     * @param type
     * @param deep
     * @return
     */
    public ArrayList<Point> checkmate(int[][] board, int position, int type, int deep){
        ArrayList<Point> result;
        int maxScore;
        int minScore;

        maxScore = FOUR;
        minScore = FIVE;
        result = deeping(board, position, type, deep, maxScore, minScore);

        return result;
    }

}
