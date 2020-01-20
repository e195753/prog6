package jp.ac.uryukyu.ie.e195753;

import java.io.IOException;
import java.util.Scanner;
/**
 * Created by e195753 on 2020/01/20.
 */
public class Main {

    /**
     * 画面初期化メソッド
     * どうしてもやりたかったのでhttp://simplesandsamples.com/clear.java.htmlから丸々コピー。
     */
    public static void cls(){
        System.out.print("\033[2J");
    }

    /**
     * 入力催促メッセ出力メソッド
     * @param scanner 文字入力取得用
     */
    public static String getInput(Scanner scanner){
        System.out.println("入力してください:");
        return scanner.next();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        GameManager GM = new GameManager(scanner);
        GM.play();
    }
}
