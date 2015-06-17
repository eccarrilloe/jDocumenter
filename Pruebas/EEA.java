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
public class EEA {

    static int resultado[]= new int[3];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Scanner reader = new Scanner(System.in);
       System.out.print("Ingrese el valor de a: ");
       int a= reader.nextInt();
       System.out.print("\nIngrese el valor de n: ");
       int n = reader.nextInt();
       
       resultado = EEA(a,n);
       
       System.out.println("Maximo comun divisor:" + resultado[0] + "\n x: "+ resultado[1]+
                 "\n y: " + resultado[2]);
    }
    
    static int [] EEA (int a, int b){
        int c[] = new int[3];
        if(b==0){
            c[0]=a;
            c[1]=1;
            c[2]=0;
            return c;
        }
        c= EEA(b, a%b);
        int d,x,y;
        d=c[0];
        x=c[2];
        y=c[1]- (int)Math.floor(a/b)*c[2];
        
        c[0]= d;
        c[1]=x;
        c[2]=y;
        
        return c;
    }
}
