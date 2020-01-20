package jp.ac.uryukyu.ie.e195753;

import java.util.Arrays;

/**
 * キャラクターの抽象クラス
 * 敵キャラ実装しようとしていた頃の名残。
 * int[] pos; //現在の居場所。
 * int[] pos_will; //移動予定の場所。
 * boolean move_check = true; //移動可能かどうかの判定。trueで可能。一度でもfalseになるとgame over。
 * Created by e195753 on 2020/01/20.
 */
public abstract class Chara {
    int[] pos;
    int[] pos_will;
    boolean move_check = true;

    abstract boolean move(Map map,char[] angles);

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}

/**
 * 操作する主人公のクラス
 * Created by e195753 on 2020/01/20.
 */
class Player extends Chara{

    /**
     * コンストラクタ。
     */
    Player(){
        int[] pos_will  = new int[2];
        int[] pos  = new int[2];
    }
    /**
     * 移動メソッド。
     * 入力から移動可能か判断し可能なら移動する。
     * @param map マップ。主に移動先の情報を取得するため。
     * @param angles 移動方向の配列。
     */
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