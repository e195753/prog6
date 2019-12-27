package jp.ac.uryukyu.ie.e195753;

import java.util.Scanner;

public abstract class Item {
    String name;
    int max;
    String info;

    public String getName() {
        return name;
    }

    public int getMax() {
        return max;
    }

    public String getInfo() {
        return info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    abstract void use(Chara user, Chara target);
}
class Weapon extends Item{
    int atk;
    int def;
    Weapon(String name,int atk,int def,String info){}
    @Override
    void use(Chara user, Chara target) {

    }
}
