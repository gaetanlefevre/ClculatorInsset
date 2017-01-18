/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insset.server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class RomanConverterServiceImplTest {
    
    public RomanConverterServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convertDateYears method, of class RomanConverterServiceImpl.
     */
    
    @Test
    public void testConvertDateYearsOK() {
        System.out.println("convertDateYearsOK");
        String nbr = "01/01/2000";
        RomanConverterServiceImpl instance = new RomanConverterServiceImpl();
        String expResult = "I/I/MM";
        String result = instance.convertDateYears(nbr);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertDateYearsDateIncorrecte1() {
        System.out.println("convertDateYearsDateIncorrecte1");
        String nbr = "X";
        RomanConverterServiceImpl instance = new RomanConverterServiceImpl();
        instance.convertDateYears(nbr);
        String expResult = "Date incorrecte";
        String result = instance.convertDateYears(nbr);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertDateYearsDateInccorecte2() {
        System.out.println("convertDateYearsDateIncorrecte2");
        String nbr = "01/13/2000";
        RomanConverterServiceImpl instance = new RomanConverterServiceImpl();
        String expResult = "Date incorrecte";
        String result = instance.convertDateYears(nbr);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testConvertDateYearsSepInccorect() {
        System.out.println("convertDateYearsSepInccorect");
        String nbr = "01/01.2000";
        RomanConverterServiceImpl instance = new RomanConverterServiceImpl();
        String expResult = "Séparateur incorrect";
        String result = instance.convertDateYears(nbr);
        
        assertEquals(expResult, result);
    }
    /**
     * Test of convertRomanToArabe method, of class RomanConverterServiceImpl.
     */
    @Test
    public void testConvertRomanToArabeOK() {
        System.out.println("convertRomanToArabeOK");
        String nbr = "IX";
        RomanConverterServiceImpl instance = new RomanConverterServiceImpl();
        Integer expResult = 9;
        Integer result = instance.convertRomanToArabe(nbr);
        
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testConvertRomanToArabeNOK() {
        System.out.println("convertRomanToArabeNOK");
        String nbr = "test";
        RomanConverterServiceImpl instance = new RomanConverterServiceImpl();
        Integer expResult = -1;
        Integer result = instance.convertRomanToArabe(nbr);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of convertArabeToRoman method, of class RomanConverterServiceImpl.
     */
    @Test
    public void testConvertArabeToRomanOK() {
        System.out.println("convertArabeToRomanNOK");
        Integer nbr = 15;
        RomanConverterServiceImpl instance = new RomanConverterServiceImpl();
        String expResult = "XV";
        String result = instance.convertArabeToRoman(nbr);
        
        assertEquals(expResult, result);
    }
    
}