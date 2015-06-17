/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eea;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class Phi {
    public static void main (String args[]){
        Scanner s= new Scanner(System.in);
        System.out.print("Ingrese el valor a calcular: ");  
        int n= s.nextInt();
        System.out.println("phi("+ n+ ")= "+phi(n));
    }

    private static int phi(int n) {
        int numberphi=0;
         for(int i=1; i<n; i++){
             if(EEA.EEA(n, i)[0]==1){
                 numberphi++;
             }
         }
         return numberphi;
    }
}
