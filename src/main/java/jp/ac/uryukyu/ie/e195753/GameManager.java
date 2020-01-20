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
    public void init_map() throws IOException {
        map_load();
        player.setPos(map.getStart());
        turn = 1;
    }
    public void map_load(int level) throws IOException {
        this.map = new Map(level,player);

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
