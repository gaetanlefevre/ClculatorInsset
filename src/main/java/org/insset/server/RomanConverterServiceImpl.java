/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insset.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.insset.client.service.RomanConverterService;

/**
 *
 * @author user
 */
@SuppressWarnings("serial")
public class RomanConverterServiceImpl extends RemoteServiceServlet implements
        RomanConverterService {
    
    // Nombres décimaux équivalents aux valeurs des nombres romains
    private final int[] nombres = {1000, 500, 100, 50, 10, 5, 1 };
    // Variable de limite des années 2000
    private final int MAX = 2000;
    // Cette chaîne de caractères contient tous les symboles des chiffres romains
    private final String symboles = "MDCLXVI";
    private String decimaux;
    
    /**
     * Conversion des dates (décimal vers romain)
     * @param String nbr
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public String convertDateYears(String nbr) throws IllegalArgumentException {
        String jourA,moisA,anneeA, sep1,sep2;
        // Test d'exception par rapport à la longueur de chaîne et la conversion en int
        try {
            jourA = nbr.substring(0,2);
            moisA = nbr.substring(3,5);
            anneeA = nbr.substring(6);
            
            sep1 = nbr.substring(2,3);
            sep2 = nbr.substring(5,6);
            
            Integer.parseInt(jourA);
            Integer.parseInt(moisA);
            Integer.parseInt(anneeA);
            
        }
        catch(Exception e) {
            return "Date incorrecte";
        }
        // Test des séparateurs
        if((sep1.equals("/") || sep1.equals("-")) && (sep2.equals("/") || sep2.equals("-"))) {
            // Test du format de la date
            if((Integer.parseInt(jourA) < 32) && ((Integer.parseInt(moisA) < 13)) && ((Integer.parseInt(anneeA) < 2001))) {
                String jourR = this.convertArabeToRoman(Integer.parseInt(jourA));
                String moisR = this.convertArabeToRoman(Integer.parseInt(moisA));
                String anneeR = this.convertArabeToRoman(Integer.parseInt(anneeA));

                return jourR+"/"+moisR+"/"+anneeR;
            }
            else {
                return "Date incorrecte";
            }
            
        }
        else {
            return "Séparateur incorrect";
        }
    }

    /**
     * Conversion des chiffres romains vers les chiffres décimaux
     * @param String nbr
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Integer convertRomanToArabe(String nbr) throws IllegalArgumentException {
        String rom = "";
        boolean bool = false;
        int x=0;
        int p=0;
        
        nbr=nbr.intern();
        nbr=nbr.toUpperCase();
        
        // Boucle de conversion de chaque caractère
        for(int i=0; i<nbr.length(); ++i)
        {
           this.decimaux=nbr.substring(i,i+1);
           
              for(int j=0; j<this.symboles.length();j++)
              {
                   rom=this.symboles.substring(j,j+1);
                   
                   if(this.decimaux.equals(rom)) {bool=true;break;}
                   else {bool=false;}
              }
              
          if(bool==false){x = -1; break;}
        }
        
        if(bool)
        {
            for(int i=0; i<nbr.length(); ++i)
            {
              this.decimaux=nbr.substring(i,i+1);
              for(int j=0; j<this.symboles.length();j++)
              {
                  rom=this.symboles.substring(j,j+1);
                   if(this.decimaux.equals(rom))
                   { if(j>=p){x+=this.nombres[j];
                                p=j;
                         }else{
                               x-=(2*this.nombres[p]-this.nombres[j]);
                               p=j;}
                     }
                }
            }
        }
        // Test de valeur MAX
        if(x > MAX) {
            return -1;
        }
        else {
            return x;
        }
        
    }


    /**
     * Conversion des chiffres décimaux vers les chiffres romains
     * @param Integer nbr
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public String convertArabeToRoman(Integer nbr) throws IllegalArgumentException {
        int temp=nbr;
        int k=0;
        String conversion="";
        // Vérifie si la valeur dépasse les années 2000
        if(nbr > MAX) {
            return "Nombre trop grand";
        }
        
        // Conversion
        for(int i=0;i< this.nombres.length;++i)
        {
            if(temp==4)
            {
                temp=0;
                conversion=conversion+"IV";
            }
            while(temp>=this.nombres[i] && k<3)
            {
                ++k;
                temp=temp-this.nombres[i];
                conversion=conversion+this.symboles.charAt(i);
            }
            if(i <this.nombres.length-1)
            {
               if(temp/this.nombres[i+1]>1 && (((temp/this.nombres[i+1])*this.nombres[i+1])+this.nombres[i+1])==this.nombres[i])
                {   
                    temp=temp-(this.nombres[i]-this.nombres[i+1]);
                    conversion=conversion+this.symboles.charAt(i+1)+this.symboles.charAt(i);
                }
            }
            if(i<this.nombres.length-2)
            {
                if(temp/this.nombres[i+2]>1 && (((temp/this.nombres[i+2])*this.nombres[i+2])+this.nombres[i+2])==this.nombres[i])
                { 
                    temp=temp-(this.nombres[i]-this.nombres[i+2]);
                    conversion=conversion+this.symboles.charAt(i+2)+this.symboles.charAt(i);
                }
            }
            k=0;
            if(temp==0){break;}
        }   
            return conversion;
    } 

}
