package jp.ac.uryukyu.ie.e195753;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void move_check() throws IOException {
        Player betatester = new Player();
        Map testmap = new Map(0,betatester);
        int[] pos = {0,0};
        assertEquals(testmap.move_check(pos),false);
    }
    @Test
    void move_check2() throws IOException {
        Player betatester = new Player();
        Map testmap = new Map(0,betatester);
        int[] pos = {-1,-1};
        assertEquals(testmap.move_check(pos),false);
    }
    @Test
    void move_check3() throws IOException {
        Player betatester = new Player();
        Map testmap = new Map(0,betatester);
        int[] pos = {1,1};
        assertEquals(testmap.move_check(pos),true);
    }

    @Test
    void exit_check() throws IOException {
        Player betatester = new Player();
        Map testmap = new Map(0,betatester);
        int[] pos = {1,1};
        assertEquals(testmap.exit_check(pos),true);
    }
    @Test
    void exit_check2() throws IOException {
        Player betatester = new Player();
        Map testmap = new Map(0,betatester);
        int[] pos = {3,3};
        assertEquals(testmap.exit_check(pos),false);
    }
}