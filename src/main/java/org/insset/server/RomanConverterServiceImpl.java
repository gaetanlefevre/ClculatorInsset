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
    
    private final int[] nombres = {1000, 500, 100, 50, 10, 5, 1 };
    private final int MAX = 2000;
    private final String symboles = "MDCLXVI";
    private String decimaux;

    @Override
    public String convertDateYears(String nbr) throws IllegalArgumentException {
        //Implement your code
        return "XV/III/MX";
    }

    @Override
    public Integer convertRomanToArabe(String nbr) throws IllegalArgumentException {
        String rom = "";
        boolean bool = false;
        int x=0;
        int p=0;
        
        //System.out.print("Entrez un nombre en chiffres romains : ");
        
        nbr=nbr.intern();
        nbr=nbr.toUpperCase();
        /*System.out.println("La valeur entre "+chaine);*/
        
        
        for(int i=0; i<nbr.length(); ++i)
        {
           this.decimaux=nbr.substring(i,i+1);
           
              for(int j=0; j<this.symboles.length();j++)
              {
                   rom=this.symboles.substring(j,j+1);
                   
                   if(this.decimaux.equals(rom)) {bool=true;break;}
                   else {bool=false;}
              }
              
          if(bool==false){/*System.out.println("Conversion impossible, nombre romain mal formé.");*/break;}
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
          //System.out.println("décimaux("+chaine+") = "+x);
        }
        return x;
    }

    @Override
    public String convertArabeToRoman(Integer nbr) throws IllegalArgumentException {
        int temp=nbr;
        int k=0;
        String conversion="";
        //System.out.print("Entrez un nombre (en chiffres décimaux) compris entre 1 et " + MAX + " : "); 
        
        /*System.out.println("La valeur entre "+chaine);*/
        /*Conversion*/
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
        //System.out.println("romains("+nbr+") = "+conversion);
        return conversion;
    } 

}
