package jp.ac.uryukyu.ie.e195753;

import java.io.*;
import java.util.Arrays;

public class Map {
    String name;
    int[] size;
    int[] start;
    int[]  exit;
    int level;
    char[][] raw_field;
    char[][] field;
    Map(int level) throws IOException {

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
        raw_field = new char[size[0]][size[1]];
        field = new char[size[0]][size[1]];
        for (int i = 0;i<size[1];i++){
            String log;
            log = br.readLine();
            int j=0;
            for(char chip:log.toCharArray()){
                raw_field[i][j] = chip;
                j++;
            }
        }

    }
    public int[] getStart() {
        return start;
    }


    public boolean move_check(int[] pos){
        return raw_field[pos[0]][pos[1]] != '#';
    }
    public boolean exit_check(int[] pos){
        return (pos[0] == exit[0]) & (pos[1] == exit[1]);
    }
    public void print(Player player ,Enemy[] ch){
        Main.cls();
        for(int i=0;i<this.size[0];i++){
            if (this.size[1] >= 0) System.arraycopy(raw_field[i], 0, field[i], 0, this.size[1]);
        }
        field[player.pos[0]][player.pos[1]] = '@';
        if(ch != null){
            for(Enemy enemy:ch){
                field[enemy.pos[0]][enemy.pos[1]] = enemy.type;
            }
        }
        for(int i=0;i<this.size[0];i++){
            for(int j = 0;j<this.size[1];j++){
                System.out.print(field[i][j]);
            }
            System.out.print('\n');
        }
    }
}
