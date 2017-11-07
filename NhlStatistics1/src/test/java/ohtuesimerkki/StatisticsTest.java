package ohtuesimerkki;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hyytiala
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    private Statistics stats;
    private Statistics eitoimi;
    private java.io.Reader lukija;
    private ArrayList<Player> pelaajat;
    
    
    public StatisticsTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
        
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void eiToimi(){
        eitoimi = new Statistics(lukija);
    }

    @Test
    public void nimenHaku(){
        assertEquals("Kurri", stats.search("Kurri").getName());
    }
    
    @Test
    public void eiListalla(){
        assertEquals(null, stats.search("Testi"));
    }
    
    @Test
    public void pelaajaLista(){
        assertEquals(3, stats.team("EDM").size());
    }
    
    @Test
    public void topLista(){
        // top lista palauttaa yhden pelaajan enemmän kuin pitäisi
        assertEquals(4, stats.topScorers(3).size());
    }
}