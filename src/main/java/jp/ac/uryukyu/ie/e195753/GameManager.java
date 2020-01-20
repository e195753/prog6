package jp.ac.uryukyu.ie.e195753;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    int turn;
    int level;
    ArrayList<Chara> actList = new ArrayList<>();
    Player player;
    Scanner scanner;
    Map map;

    Map printmap;
    GameManager(Scanner scanner) throws IOException {
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
    public void play() throws IOException {
        while(true){
            init_map();
            map.print(player);
            if(!player.move(map,input_req())){
                gameover();
            }
            if(!map.exit_check(player.getPos())){
                gameover();
            }else{
                this.level = map.level + 1;
            }
        }
    }
    public void init_map() throws IOException {
        if(level == 1){
            this.player = new Player();
        }
        map_load();
        player.setPos(map.getStart());
        turn = 1;
    }
    public void map_load(int level) throws IOException {
        this.map = new Map(level);

    }
    public void map_load() throws IOException {
        map_load(this.level);
    }
    public char[] input_req(){
        return Main.getInput(scanner).toCharArray();
    }
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
