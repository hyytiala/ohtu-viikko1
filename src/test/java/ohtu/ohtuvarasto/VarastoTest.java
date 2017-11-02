package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto negatiivinen;
    Varasto eityhja;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        negatiivinen = new Varasto(-10);
        eityhja = new Varasto(10, 3);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiYliMenoa(){
        varasto.lisaaVarastoon(20);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaVarastoon(){
        varasto.lisaaVarastoon(-10);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanLiikaa(){
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(50);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaVarastosta(){
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(-2);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void NegatiivinenVarasto(){
        assertEquals(0, negatiivinen.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void eiTyhjaVarasto(){
        assertEquals(10, eityhja.getTilavuus(), vertailuTarkkuus);
        assertEquals(3, eityhja.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiTyhjaNegatiivinen(){
        Varasto testi = new Varasto(-10, 5);
        
        assertEquals(0, testi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoIsompiKuinTilavuus(){
        Varasto testi = new Varasto(5, 10);
        
        assertEquals(5, testi.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, testi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoNegatiivinen(){
        Varasto testi = new Varasto(10, -2);
        
        assertEquals(0, testi.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoToString(){
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}