package GamePackage.tests;

import GamePackage.Player;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;

public class testPlayer {

    @Test
    public void test(){
        Player p1 = new Player("Bob", Color.black);
        assertEquals(p1.getName(), "Bob");
        assertEquals(p1.getColor(), Color.black);
        assertEquals(0, p1.getScore());
        p1.addScore();
        p1.addScore();
        assertEquals(2, p1.getScore());

        Player p2 = new Player("Adam", Color.red);
        p2.setName("NoMoreAdam");
        p2.setColor(Color.white);
        assertEquals("NoMoreAdam", p2.getName());
        assertEquals(Color.white, p2.getColor());
    }
}
