package jp.ac.uryukyu.ie.e195753;

import java.io.*;
import java.util.ArrayList;

/**
 * ワープポイント用クラス。
 *  char alphabet; //ポイントの名前。小文字から大文字に移動できる
 *  ArrayList<Integer> warp_in; //ワープ開始地点。小文字。
 *  ArrayList<Integer> warp_out; //ワープ終了地点。大文字。
 * Created by e195753 on 2020/01/20.
 */
class Warper{
    char alphabet;
    ArrayList<Integer> warp_in = new ArrayList<>(2);
    ArrayList<Integer> warp_out = new ArrayList<>(2) ;

    /**
     * コンストラクタ。ポイント名やポイントの座標を指定する。
     * @param alphabet ポイント名
     * @param warp_in_x,warp_in_y ワープ開始地点
     * @param warp_out_x,warp_out_y ワープ終了地点
     */
    Warper(char alphabet,int warp_in_x,int warp_in_y,int warp_out_x,int warp_out_y){
            this.alphabet = alphabet;
            warp_in.add(warp_in_x);
            warp_in.add(warp_in_y);
            warp_out.add(warp_out_x);
            warp_out.add(warp_out_y);
    }

}

/**
 * マップクラス。
 *  int[] size,start,exit; それぞれマップの大きさ、スタート、ゴールを表す
 *  int level; //マップのレベル。または順番、名前。
 *  char[][] raw_field; //マップ本体のデータ。
 *  char[][] raw_field; //マップ本体のデータのコピー。出力用。ターン制の頃の名残。
 *  Player player; //操作する主人公。
 *  ArrayList<Warper> warpers; //ワープポイントのまとめ
 * Created by e195753 on 2020/01/20.
 */
public class Map {
    int[] size;
    int[] start;
    int[]  exit;
    int level;
    char[][] raw_field;
    char[][] field;
    Player player;
    ArrayList<Warper> warpers = new ArrayList<>();

    /**
     * コンストラクタ。
     * テキストファイルからマップを作成する。
     * メインの機能は他のメソッド二つに丸投げしている
     * @param level マップの名前
     * @param player 操作する主人公
     */
    Map(int level,Player player) throws IOException {
        this.player = player;
        this.level = level;
        start = new int[2];
        exit = new int[2];
        size = new int[2];

        System.out.println("level:"+level);
        InputStream is = getClass().getResourceAsStream("/map" + this.level + ".txt");
        BufferedReader br;
        try{
            br = new BufferedReader(new InputStreamReader(is));
        }catch (NullPointerException e){
            this.level = 1;
            System.out.println("マップファイルが存在しないため最初のレベルを読み込みます");
            is = getClass().getResourceAsStream("/map" + this.level + ".txt");
            try{
                br = new BufferedReader(new InputStreamReader(is));
            }catch (NullPointerException e2){
                System.out.println("map1.txtすら存在しないため終了します");
                System.exit(-1);
            }
        }finally {
            br = new BufferedReader(new InputStreamReader(is));
        }
        map_analyze_head(br);
        map_analyze_body(br);
    }

    /**
     * テキストファイル解析メソッド１。
     * 主にマップサイズとかスタート地点とかそんな系
     * @param br テキストファイルを読んでくれるなんかすごいやつ
     */
    void map_analyze_head(BufferedReader br) throws IOException {
        String line;
        line = br.readLine();
        for(int i = 0;i<2;i++){
            start[i] = Integer.parseInt(line.split(" ")[i]);
        }
        line = br.readLine();
        for(int i = 0;i<2;i++){
            exit[i] = Integer.parseInt(line.split(" ")[i]);
        }
        line = br.readLine();
        for(int i = 0;i<2;i++){
            size[i] = Integer.parseInt(line.split(" ")[i]);
        }
    }

    /**
     * テキストファイル解析メソッド２。
     * 主にマップデータ本体を読み取ってワープ地点とか作ったりする。
     * @param br テキストファイルを読んでくれるなんかすごいやつ
     */
    void map_analyze_body(BufferedReader br) throws IOException {
        raw_field = new char[size[0]][size[1]];
        field = new char[size[0]][size[1]];
        for (int i = 0;i<size[1];i++){
            String log;
            log = br.readLine();
            int j=0;
            for(char chip:log.toCharArray()){
                raw_field[i][j] = chip;
                boolean checker = true;
                if((int)'a' <= chip & chip <= (int)'z') {
                    if(warpers.size() == 0){
                        warpers.add(new Warper(chip,i,j,-1,-1));
                    }else{
                        for(Warper warper:warpers){
                            if(warper.alphabet == chip){
                                warper.warp_in.set(0,i);
                                warper.warp_in.set(1,j);
                                checker = false;
                            }
                        }
                        if(checker){
                            warpers.add(new Warper(chip,i,j,-1,-1));
                        }
                    }
                }else if((int)'A' <= chip & chip <= (int)'Z'){
                    if(warpers.size() == 0){
                        warpers.add(new Warper((char)(chip + 32),-1,-1,i,j));
                    }else{
                        for(Warper warper:warpers){
                            if(warper.alphabet == chip + 32){
                                warper.warp_out.set(0,i);
                                warper.warp_out.set(1,j);
                                checker = false;
                            }
                        }
                        if(checker){
                            warpers.add(new Warper((char)(chip + 32),-1,-1,i,j));
                        }
                    }

                }
                j++;
            }
        }
    }

    /**
     * startのgetter。
     */
    public int[] getStart() {
        return start;
    }

    /**
     * 指定した座標へ移動できるかのチェックを行うメソッド。
     * ついでにワープポイントの起動も行う（ワープポイントがあったら壁ないので）
     * @param pos 目的地点
     */
    public boolean move_check(int[] pos){
        try{
            if((raw_field[pos[0]][pos[1]] == '#' || raw_field[pos[0]][pos[1]] == ' ') || (raw_field[pos[0]][pos[1]] == '>' || raw_field[pos[0]][pos[1]] == '<')){
                if(raw_field[pos[0]][pos[1]] == '#'){
                    System.out.println("壁に衝突しました");
                }
                return raw_field[pos[0]][pos[1]] != '#';
            }else{
                if(warpers != null){
                    for(Warper warper:warpers){
                        if(warper.alphabet == raw_field[pos[0]][pos[1]]){
                            player.pos_will[0] = warper.warp_out.get(0);
                            player.pos_will[1] = warper.warp_out.get(1);
                            return true;
                        }
                    }
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("マップを超過しました");
            return false;
        }
        return false;
    }

    /**
     * ゴールについたか判定するメソッド。
     * @param pos 現在の地点
     */
    public boolean exit_check(int[] pos){
        return (pos[0] == exit[0]) & (pos[1] == exit[1]);
    }

    /**
     * マップデータを画面に出力するメソッド
     */
    public void print(){
        Main.cls();
        for(int i=0;i<this.size[0];i++){
            if (this.size[1] >= 0) System.arraycopy(raw_field[i], 0, field[i], 0, this.size[1]);
        }
        field[player.pos[0]][player.pos[1]] = '@';
        for(int i=0;i<this.size[0];i++){
            for(int j = 0;j<this.size[1];j++){
                System.out.print(field[i][j]);
            }
            System.out.print('\n');
        }
    }
}
