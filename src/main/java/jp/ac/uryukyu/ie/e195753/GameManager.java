package jp.ac.uryukyu.ie.e195753;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    int turn;
    int level;
    ArrayList<Chara> actList = new ArrayList<>();
    Player player;
    Enemy[] enemys;
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
        while(true){
            init_map();
            while(!map.exit_check(player.getPos())){
                map.print(player,enemys);
                player.move(map,input_req());
            }
            this.level = map.level + 1;
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
    public char input_req(){
        return Main.getInput(scanner).charAt(0);
    }
}
