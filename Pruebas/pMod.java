package a;

import java.math.BigInteger;

public class pMod {


		    public static void main(String[] args) {
		    	
		    	String factores=factores(6995201);
		    	
		    	int p=Integer.parseInt(factores.substring(0, factores.indexOf(",")));
		    	int q=Integer.parseInt(factores.substring(factores.indexOf(",")+1,factores.length()-1));
		    	
		    	int n= p*q;
		    	
		    	int totem= (p-1)*(q-1);
		    	
		    	BigInteger e = new BigInteger(Long.toString(822883));
		       	
		    	BigInteger N = new BigInteger(Long.toString(n));
		    
		    	BigInteger D = e.modInverse(new BigInteger(Long.toString(totem)));
		    	
		    	int[] d= new int[D.bitLength()];
		    	//{1,0,1,0,0,1,0,0,0,0,0,0,1,1,0,0,1,0,0,0,1,1}
		    	for (int a=0;a<D.bitLength();a++){
		    		if(D.testBit(a)){	
		    		d[D.bitLength()-1-a]=1;}else d[D.bitLength()-1-a]=0;
		    	}
		    	
		    	int[] c= {2303235,5103604,3714430,205423,6901157,2508992,1049928,4157769,1173182,968466,4588169,6023370,1504123,1401163};	
		    	
		    	BigInteger[] cipher=new BigInteger[c.length];
		    	
		    	for (int a=0;a<c.length;a++){
		    		cipher[a]=new BigInteger(Long.toString(c[a]));
		    				
		    	}
		    	
		    	BigInteger[] msj=decrypt(cipher,d,N,false);
		    	
		    	String aChar = new Character((char)Integer.parseInt(msj[1].toString())).toString();
		    	
		    	String m="m=[";
		    	for (int a=0;a<cipher.length;a++){
		    		
		    		m+=msj[a]+",";
		    		int fin=2;
		    		if (msj[a].toString().length()==6){fin=3;}
		    		System.out.print(new Character((char)Integer.parseInt(msj[a].toString().substring(0, fin))).toString());
		    		System.out.print(new Character((char)Integer.parseInt(msj[a].toString().substring(fin, msj[a].toString().length()))).toString());
		    		}
		    	m=m.substring(0,m.length()-1);
		    	m+="]";
		    	System.out.print("\n"+m);
		    	}
		    
		    
public static String factores(int numero1){
		    	int numero=numero1;
		    	String fact="";
		        for(int i=2;i<numero1;i++){

		            while(numero%i==0){
		                numero=numero/i;
		                fact+=i+",";
		              //Para evitar hacer cálculos innecesarios :
		                 if(numero==1){
		                    return fact;
		                }
		            }
		        }
				return fact;

		    }
		    
public static BigInteger[] decrypt(BigInteger[] cipher,int[] d,BigInteger N, boolean display){
		    			    	
		    	BigInteger[][] pmod = new BigInteger[cipher.length][d.length];
		    	
		    	for (int a=0;a<cipher.length;a++){
		    	pmod[a][0]=cipher[a];
		    	}
		    	
		    	for (int a=0;a<cipher.length;a++){
		    		for (int b=1;b<d.length;b++){
		    			if(d[b]==1){pmod[a][b]=pmod[a][0].multiply(pmod[a][b-1].multiply(pmod[a][b-1])).mod(N);

		    			}else{pmod[a][b]=pmod[a][b-1].multiply(pmod[a][b-1]).mod(N);

		    			}

		    		}
		    		
		    	}
		    	if (display){
		    	for (int a=0;a<cipher.length;a++){
		    		for (int b=0;b<d.length;b++){
		    			if (b==d.length-1){
		    			System.out.print(pmod[a][b]);
		    			}else System.out.print(pmod[a][b]+",");
		    			}
		    			System.out.print("\n");
    				
		    		}
		    	}
		    	BigInteger[] msj=new BigInteger[cipher.length];
		    	for (int a=0;a<cipher.length;a++){
		    		
		    		msj[a]=pmod[a][d.length-1];
    				
		    		}
				return msj;	
		    }
		        
		    }

		
