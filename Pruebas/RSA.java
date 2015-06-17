/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eea;

import static eea.factorization.primes;
import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class RSA {
    static int primos []= new int [500] ;
    static double n;
    static double e;
    
    public static void main (String [] args){
        
        int p=1;
        int q;        
        int phi;
        double cifrado[] = {2303235,5103604,3714430,205423,6901157,2508992,1049928,4157769,1173182,968466,
                            4588169,6023370,1504123,1401163};
        
        Primes.llenarPrimos(primos);
        double decifrado[] = new double[14];
        n=6995201;
        e=822883;
        p= primos[buscarPq(p)];
       
        q=(int) (n/p);
        phi = (q-1)*(p-1);
        int array []= EEA.EEA((int)e, phi);
        int d=array[1];
        if(d<=0){
            d=d+phi;
            
        }
        String bin = binario ((int)d);
        System.out.println("\n Mensaje decifrado: ");
        for(int i=0; i<cifrado.length;i++){
            
            decifrado[i]= powerMod(cifrado[i],bin);
            System.out.print(" m"+(i+1)+": " + decifrado[i] + ", ");
            if((i+1)%8==0){
                System.out.println("");
            }
            
        }
        
        bin = binario ((int)e);
        System.out.println("\nMensaje cifrado: ");
        for(int i=0;i<cifrado.length;i++){
            System.out.print("c"+ (i+1) + ": " +powerMod(decifrado[i], bin) + ", ");
            if((i+1)%8==0){
                System.out.println("");
            }
            
            
        }
    }
      private static void imprimirPrimos() {
        for (int i=0;i<primos.length;i++){
            System.out.print(primos[i]+ " ");
        }
    }

    private static int buscarPq(int primo1) {
        primo1=primos[0];
        
        int naux= (int)n;
        for(int i = 1; i<primos.length;i++){
            
            if(naux%primo1==0){
                return (i-1);
            }
            primo1= primos[i];
        }
        return 0;
        
    }

    private static double powerMod(double ci, String bin) {
        double z=1;
        double aux=1;
        for (int i=0; i<bin.length();i++){
            if(bin.charAt(i)=='1'){
                aux=(double)((z%n)*(double)(z%n))%n;
                aux=(double)((ci%n)*aux)%n;
                z=aux;
            }
            else{
                aux=((z%n)*(z%n));
                z=(double)((aux)%n);
            }
        }
        return z;
    }

    private static String binario(int d) {
        int exponente = 0;
        int num=1;
        int division=1;
        int aux= d;
        boolean binary = false;
        String numBin="";
        while (!binary){
            num = (int) Math.pow(2, exponente);
            division=d/num;
            if(division>=1 && division<2){
                break;
            }
            else{
                exponente++;
            }
        }
        int pow=1;
        
        while(aux!=0){
            pow = (int)Math.pow(2, exponente);
            if(aux-pow>=0){
                aux = aux- pow;
                numBin+="1";
            }
            else{
                numBin+="0";
            }
            exponente--;
        }
        
        return numBin;
    }
}
