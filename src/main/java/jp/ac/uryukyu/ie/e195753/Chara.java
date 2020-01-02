package jp.ac.uryukyu.ie.e195753;

import java.util.Arrays;

public abstract class Chara {
    int[] pos;
    int[] pos_will;
    int atk;
    int hp;
    int mp;
    int level;
    Weapon weapon;

    abstract int move(Map map,char angle);

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
    Item[] inventory;
    Player(){
        level = 1;
        int[] pos_will  = new int[2];
        int[] pos  = new int[2];
    }
    @Override
    int move(Map map,char angle) {
        pos_will = Arrays.copyOf(pos,pos.length);
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
            default:
                System.out.println("入力された文字が違います");
                return -1;
        }
        if(map.move_check(pos_will)){
            this.pos = Arrays.copyOf(pos_will,pos_will.length);
            return 1;
        }else{
            return 0;
        }
    }
    void addInventory(Item item){

    }
}
class Enemy extends Chara{
    char type;
    Enemy(){
        level = 1;
        type = 'e';
    }
    @Override
    int move(Map map, char angle) {
        return 0;
    }
}

