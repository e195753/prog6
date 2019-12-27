package jp.ac.uryukyu.ie.e195753;

public abstract class Chara {
    int[] pos;
    int[] pos_will;
    int atk;
    int hp;
    int mp;
    int level;
    Weapon weapon;

    abstract int move(Map map,int angle);
}
class Player extends Chara{
    Item[] inventory;
    Player(){
        level = 1;
        int[] pos_will  = new int[2];
    }
    @Override
    int move(Map map,int angle) {
       return 1;
    }
    void addInventory(Item item){

    }
}

