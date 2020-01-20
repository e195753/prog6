package jp.ac.uryukyu.ie.e195753;

import java.io.IOException;
import java.util.Scanner;

/**
 * ゲーム処理用クラス。
 * クラスとクラスの橋渡しを担っている
 *  int turn; //経過ターン。過去の遺物につき消去予定中。
 *  int level; //マップの名前。
 *  Player player; //操作する主人公。
 *  Map map; //遊ぶマップ。
 * Created by e195753 on 2020/01/20.
 */
public class GameManager {
    int turn;
    int level;
    Player player;
    Scanner scanner;
    Map map;

    /**
     * コンストラクタ。
     * タイトル画面を出力する。
     * @param scanner 文字入力を受け付けてくれるなんかすごいやつ
     */
    GameManager(Scanner scanner) {
        this.scanner = scanner;
        level = 1;
        this.player = new Player();
        System.out.println(
                "####################\n" +
                "#     report 6     #\n" +
                "#     e195753J     #\n" +
                "#                  #\n" +
                "#   input  start   #\n" +
                "#                  #\n" +
                "####################\n");

        while(true){
            String input = Main.getInput(scanner);
            if(input.equals("start")){
                break;
            }
            if(input.equals("3")){
                level = 3;
                break;
            }
        }

    }

    /**
     * マップを遊び始めるメソッド。
     * 正確にはマップからマップへの移動を司るメソッド
     */
    public void play() throws IOException {
        while(true){
            init_map();
            map.print();
            if(!player.move(map,input_req())){
                gameover();
                break;
            }
            if(!map.exit_check(player.getPos())){
                gameover();
                break;
            }else{
                this.level = map.level + 1;
            }
        }
    }

    /**
     * マップ初期化メソッド。
     * 主人公を初期位置に移動させる。
     */
    public void init_map() throws IOException {
        map_load();
        player.setPos(map.getStart());
        turn = 1;
    }

    /**
     * マップ読み込みメソッド。
     */
    public void map_load(int level) throws IOException {
        this.map = new Map(level,player);

    }
    public void map_load() throws IOException { map_load(this.level);}

    /**
     * 入力読み取りメソッド。
     * 入力した文字列を文字の配列に変換する。
     * @return 文字列を分解した文字の配列
     */
    public char[] input_req(){
        return Main.getInput(scanner).toCharArray();
    }

    /**
     * ゲームオーバーメソッド＾。
     * がめおべら画面を出して終了する。
     */
    public void gameover(){
        System.out.println(
                        "####################\n" +
                        "#     report 6     #\n" +
                        "#     e195753J     #\n" +
                        "#                  #\n" +
                        "#    GAME   OVER   #\n" +
                        "#                  #\n" +
                        "####################\n");
        System.exit(0);
    }
}
