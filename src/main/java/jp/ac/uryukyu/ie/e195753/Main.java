package jp.ac.uryukyu.ie.e195753;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void cls(){
        System.out.print("\033[2J");
    }
    public static String getInput(Scanner scanner){
        System.out.println("入力してください:");
        return scanner.next();
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        GameManager GM = new GameManager(scanner);
    }
}
