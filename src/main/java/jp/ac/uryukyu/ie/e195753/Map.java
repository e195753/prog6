package jp.ac.uryukyu.ie.e195753;

import java.io.*;
import java.util.ArrayList;

class Warper{
    char alphabet;
    ArrayList<Integer> warp_in = new ArrayList<>(2);
    ArrayList<Integer> warp_out = new ArrayList<>(2) ;
    Warper(char alphabet,int warp_in_x,int warp_in_y,int warp_out_x,int warp_out_y){
            this.alphabet = alphabet;
            warp_in.add(warp_in_x);
            warp_in.add(warp_in_y);
            warp_out.add(warp_out_x);
            warp_out.add(warp_out_y);
    }

}
public class Map {
    int[] size;
    int[] start;
    int[]  exit;
    int level;
    char[][] raw_field;
    char[][] field;
    Player player;
    ArrayList<Warper> warpers = new ArrayList<>();
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
    public int[] getStart() {
        return start;
    }


    public boolean move_check(int[] pos){
        if((raw_field[pos[0]][pos[1]] == '#' || raw_field[pos[0]][pos[1]] == ' ') || (raw_field[pos[0]][pos[1]] == '>' || raw_field[pos[0]][pos[1]] == '<')){
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
        }return false;
    }
    public boolean exit_check(int[] pos){
        return (pos[0] == exit[0]) & (pos[1] == exit[1]);
    }
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
