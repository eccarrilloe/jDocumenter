/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eea;

import static eea.factorization.primes;

/**
 *
 * @author Nicolas
 */
public class Primes {
    public static void llenarPrimos(int primes []) {
        int n = 2;
        for(int i=0; i<primes.length;i++){
            primes[i]= n;
            n= getnextPrime(n);
        }
    }

    private static int getnextPrime(int n) {
        boolean isPrime= false;
        int prime=n+1;
        while(!isPrime){
            isPrime= isPrime(prime);
            if(!isPrime){
                prime++;
            }
        }
        return prime;
    }

    private static boolean isPrime(int prime) {
        for(int i=2; i<prime;i++){
            if(prime%i==0){
                return false;
            }
        }
        return true;
    }
}
