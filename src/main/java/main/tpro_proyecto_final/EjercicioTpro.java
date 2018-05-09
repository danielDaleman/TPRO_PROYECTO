/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.tpro_proyecto_final;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

/**
 *
 * @author 2108419 - Daniel Felipe Moreno D'Aleman
 */
public class EjercicioTpro {
    //Matriz de operaciones
    private static char[][] matriz = {  {'b', 'b', 'a'}, 
                                        {'c', 'b', 'a'}, 
                                        {'a', 'c', 'c'} };
    
    //Alfabeto de caracteres
    private static char[] alfabeto = {'a','b','c'};
    
    private static final List<Character> xh = new ArrayList<Character>();
    
    private static boolean res = false;
    
    public static boolean ejercicio(List<Character> xh){    
        /**Scanner teclado=new Scanner(System.in);
        String cadena =teclado.next();**/                
        boolean dato;        
        dato = paren('a',xh);
        return(dato);        
    } 
    
    
    /**
     * 
     * @param z caracter al cual se quiere comprobar si es posible llegar
     * @param x Cadena de caracteres del alfabeto
     * @return True, si es posible llegar a z desde la cadena x, False lo contrario
     */
    public static boolean paren(char z, List<Character> x){                        
        //Caso base 
        if(x.size() == 1){            
            return (x.get(0) == z);
        }
        else{       
            //Caso recurrente
            for(int i=0; i<x.size()-1; i++){                
                for(int j=0; j<alfabeto.length; j++){
                    for(int k=0; k<alfabeto.length; k++){                        
                        //Mirar cual es la combinacion que sirve para llegar a z.
                        if(matriz[j][k] == z){                                   
                            //Separar la cadena de forma x0 hasta xi+1, con el caracter j
                            //de igual manera de forma xi+1 hasta xn, con el caracter k
                            //Con esto se mirar si es posible o no llegar al caracter inicial.
                            //Si es posible retornar True, de lo contrario procede a probar la siguiente forma
                            if(paren(alfabeto[j], x.subList(0, i+1)) &&
                                    paren(alfabeto[k],x.subList(i+1, x.size()))){                                
                                return true;
                            }else{
                                res = false;
                            }                            
                        }
                    }                                
                }                                
            }            
        }        
        
        return res;
    
    }            
}
