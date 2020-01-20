package jp.ac.uryukyu.ie.e195753;

import java.util.Arrays;

public abstract class Chara {
    int[] pos;
    int[] pos_will;
    int atk;
    int hp;
    int mp;
    int level;
    boolean move_check = true;

    abstract boolean move(Map map,char[] angles);

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public void setPos_will(int[] pos_will) {
        this.pos_will = pos_will;
    }
}
class Player extends Chara{
    Player(){
        level = 1;
        int[] pos_will  = new int[2];
        int[] pos  = new int[2];
    }
    @Override
    boolean move(Map map,char[] angles) {
        pos_will = Arrays.copyOf(pos,pos.length);
        for(char angle:angles){
            switch (angle){
                case 'w':
                    pos_will[0]--;
                    break;
                case 'a':
                    pos_will[1]--;
                    break;
                case 's':
                    pos_will[0]++;
                    break;
                case 'd':
                    pos_will[1]++;
                    break;
                case ';':
                    System.out.println("終了します...");
                    System.exit(0);
                default:
                    System.out.println("入力された文字が違います");
                    move_check = false;
            }
            if(map.move_check(pos_will)){
                this.pos = Arrays.copyOf(pos_will,pos_will.length);
            }else{
                move_check = false;
            }
        }
        return move_check;
    }
}