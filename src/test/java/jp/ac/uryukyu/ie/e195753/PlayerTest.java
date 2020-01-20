package jp.ac.uryukyu.ie.e195753;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void move() throws IOException {
        Player betatester = new Player();
        Map testmap = new Map(0,betatester);
        betatester.setPos(testmap.getStart());
        char[] move_list = {'w','w','w','w'};
        assertEquals(betatester.move(testmap,move_list),true);
    }
    @Test
    void move2() throws IOException {
        Player betatester = new Player();
        Map testmap = new Map(0,betatester);
        betatester.setPos(testmap.getStart());
        char[] move_list = {'w','a','a','w'};
        assertEquals(betatester.move(testmap,move_list),true);
    }
    @Test
    void move3() throws IOException {
        Player betatester = new Player();
        Map testmap = new Map(0,betatester);
        betatester.setPos(testmap.getStart());
        char[] move_list = {'w','a','a','a','w','d'};
        assertEquals(betatester.move(testmap,move_list),false);
    }
}