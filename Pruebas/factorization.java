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
public class factorization {
    static int primes [] = new int [2000];
    public static void main (String args []){
        numberFactorizacion a;
        numberFactorizacion b;
        Scanner s = new Scanner(System.in);
        Primes.llenarPrimos(primes);
        //imprimirPrimos();
        System.out.print("Ingrese el numero a:");
        a= new numberFactorizacion(s.nextInt());
        //System.out.print("\n Ingrese el numero b:");
        //b= new numberFactorizacion(s.nextInt());
        factorizar(a);
        System.out.println("a=" + formula(a));
        /*factorizar(b);
        
        System.out.println("b=" + formula(b));
        int mcd= comparar(a,b);
        System.out.println(" maximo Comun divisor de a y b es = " + mcd);*/
        
        imprimirPrimos();
        
    }

    

    private static void imprimirPrimos() {
        for (int i=0;i<primes.length;i++){
            System.out.print(primes[i]+ " ");
        }
    }

    private static void factorizar(numberFactorizacion n) {
        int counter = 0;
        while(n.value!=1){
            if(n.value%primes[counter]==0){
                n.exponentPrimes[counter]++;
                n.value=n.value/primes[counter];
                counter=0;
            }
            else{
                counter++;
            }
        }
    }

    private static String formula(numberFactorizacion a) {
        String formula="";
        for(int i=0; i<primes.length;i++){
            if(a.exponentPrimes[i]!=0){
                if(!formula.isEmpty())
                    formula+="*";
                formula+=primes[i] + "^" + a.exponentPrimes[i];
            }
        }
        return formula;
    }

    private static int comparar(numberFactorizacion a, numberFactorizacion b) {
        String formulaMCD="";
        int resultado=1;
        for(int i=0; i<primes.length;i++){
            if(a.exponentPrimes[i]!=0 && b.exponentPrimes[i]!=0){
                if(a.exponentPrimes[i]<=b.exponentPrimes[i]){
                    if(!formulaMCD.isEmpty())
                        formulaMCD+="*";
                    resultado = (int) (resultado*Math.pow(primes[i], a.exponentPrimes[i]));
                    formulaMCD+=primes[i] + "^"+ a.exponentPrimes[i];
                }
                else{
                    if(!formulaMCD.isEmpty())
                        formulaMCD+="*";
                    resultado = (int) (resultado*Math.pow(primes[i], b.exponentPrimes[i]));
                    formulaMCD+=primes[i] + "^"+ b.exponentPrimes[i];
                }
            }
        }
        System.out.println("Formula MCD a y b:" + formulaMCD);
        return resultado;
    }
    
}
