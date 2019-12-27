package jp.ac.uryukyu.ie.e195753;

import java.io.*;

public class Map {
    String name;
    int[] size;
    int[] start;
    int[]  exit;
    int level;
    String[] raw_field;
    String[] field;
    Map(int level) throws IOException {
        this.level = level;
        start = new int[2];
        File file = new File("src/main/lib/map" + level + ".txt");
        FileReader filereader = new FileReader(file);
        BufferedReader br = new BufferedReader(filereader);
        String line;
        line = br.readLine();
        for(int i = 0;i<=1;i++){
            start[i] = Integer.parseInt(line.split(" ")[i]);
        }
        line = br.readLine();
        for(int i = 0;i<2;i++){
            size[i] = Integer.parseInt(line.split(" ")[i]);
        }
        for (int i = 0;i<size[1];i++){
            raw_field[i] = br.readLine();
        }

    }
    public boolean move_check(int[] pos){
        return raw_field[pos[1]].charAt(pos[0]) != '#';
    }
}
