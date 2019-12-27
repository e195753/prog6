package jp.ac.uryukyu.ie.e195753;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    int turn;
    int level;
    ArrayList<Chara> actList = new ArrayList<>();
    Scanner scanner;
    GameManager(Scanner scanner){
        this.scanner = scanner;
        level = 1;
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
        }
    }
}
