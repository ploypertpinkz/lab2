import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OXTest {
    @Test
    public void shouldCreateOXObjectCorrestly(){
        OX ox = new OX();
        //อยากจะได้ " 012\n0---\n1---\n2---\n"
        assertEquals(" 012\n0---\n1---\n2---\n",ox.getTableString());
        assertEquals("X",ox.getCurrentPlayer());
        assertEquals(0,ox.getCountO());
        assertEquals(0,ox.getCountX());
        assertEquals(0,ox.getCountDraw());

    }
    @Test
    public void put(){
        OX ox = new OX();
        ox.put(1,1);
        assertEquals(" 012\n0---\n1-X-\n2---\n",ox.getTableString());

        ox.put(0,0);
        assertEquals(" 012\n0X--\n1-X-\n2---\n",ox.getTableString());

        ox.put(2,1);
        assertEquals(" 012\n0X--\n1-XX\n2---\n",ox.getTableString());

        ox.switchPlayer();
        ox.put(0,2);
        assertEquals(" 012\n0X--\n1-XX\n2O--\n",ox.getTableString());

    }

    @Test
    public void putTwice(){
        OX ox = new OX();
        assertTrue(ox.put(0,0));
        assertFalse(ox.put(0,0));
    }

    @Test
    public void putOver(){
        OX ox = new OX();
        assertFalse(ox.put(0,-1));
        assertFalse(ox.put(0,3));
        assertFalse(ox.put(3,-1));
        assertFalse(ox.put(-1,3));

    }
    @Test
    public void getOver(){
        OX ox =new OX();
        assertNull(ox.get(0,-1));
        assertNull(ox.get(0,3));
        assertNull(ox.get(3,-1));
        assertNull(ox.get(-1,-1));
    }

    @Test
    public void checkWin1(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertEquals(" 012\n0X--\n1X--\n2X--\n",ox.getTableString());
        assertEquals(true,ox.checkWin(0,0));
        assertEquals(true,ox.checkWin(0,1));
        assertEquals(true,ox.checkWin(0,2));
        assertEquals(false,ox.checkWin(1,0));
        assertEquals(false,ox.checkWin(1,1));
        assertEquals(false,ox.checkWin(1,2));

    }

    @Test
    public void checkWin2(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(0,1);
        assertEquals(" 012\n0X--\n1X--\n2---\n",ox.getTableString());
        assertEquals(false,ox.checkWin(0,0));
        assertEquals(false,ox.checkWin(0,1));

    }

    @Test
    public void checkWin3(){
        OX ox = new OX();
        ox.put(0,2);
        ox.put(1,2);
        ox.put(2,2);

        assertEquals(true,ox.checkWin(1,2));
        assertEquals(true,ox.checkWin(1,2));
        assertEquals(true,ox.checkWin(1,2));

    }

    @Test
    public void checkWin4(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);

        assertEquals(" 012\n0X--\n1-X-\n2--X\n",ox.getTableString());
        assertEquals(true,ox.checkWin(0,0));
        assertEquals(true,ox.checkWin(1,1));
        assertEquals(true,ox.checkWin(2,2));

    }

    @Test
    public void checkWin5(){
        OX ox = new OX();
        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertEquals(true,ox.checkWin(2,0));
        assertEquals(true,ox.checkWin(2,1));
        assertEquals(true,ox.checkWin(2,2));
        assertEquals(false,ox.checkWin(1,0));
        assertEquals(false,ox.checkWin(1,1));
        assertEquals(false,ox.checkWin(1,2));

    }

    @Test
    public void checkWin6() {
        OX ox = new OX();
        ox.put(2, 0);
        ox.put(1, 1);
        ox.put(0, 2);

        assertEquals(true, ox.checkWin(2, 0));
        assertEquals(true, ox.checkWin(1, 1));
        assertEquals(true, ox.checkWin(0, 2));
    }

    @Test
    public void switchPlayer(){
        OX ox = new OX();
        ox.switchPlayer();
        assertEquals("O",ox.getCurrentPlayer());
        ox.switchPlayer();
        assertEquals("X",ox.getCurrentPlayer());
    }

    @Test
    public void reset(){
        OX ox = new OX();
        ox.put(2,0);
        ox.put(1,1);
        ox.put(0,2);
        ox.reset();
        assertEquals(" 012\n0---\n1---\n2---\n",ox.getTableString());
        assertEquals("X",ox.getCurrentPlayer());
        assertEquals(0,ox.getTurnCount());

    }

    @Test
    public void getTurnCount(){
        OX ox = new OX();

        assertEquals(0,ox.getTurnCount());
        ox.put(0,0);
        assertEquals(1,ox.getTurnCount());
    }

    @Test
    public void isDraw(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());

        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());

        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.isDraw());
    }

    @Test
    public void getScoreX(){
        OX ox = new OX();
        assertEquals(0,ox.getScoreX());
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertEquals(1,ox.getScoreX());

    }

    @Test
    public void getScoreO(){
        OX ox = new OX();
        ox.switchPlayer();
        assertEquals(0,ox.getScoreO());
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertEquals(1,ox.getScoreO());

    }

    @Test
    public void getScoreDraw(){
        OX ox = new OX();

        assertEquals(0,ox.getScoreDraw());

        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());

        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());

        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertEquals(1,ox.getScoreDraw());

    }

}