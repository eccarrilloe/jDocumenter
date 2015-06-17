import java.math.BigInteger;



public class DEScall {
	  public static void main(String args[]) throws Exception {

		  String k="133457799BBCDFF1";
		  byte[] key = new BigInteger(k,16).toByteArray();
		  DES a=new DES(key);
		  
		  String m="0123456789ABCDEF";
		  byte[] clearText = new BigInteger(m,16).toByteArray();
		  byte[] encriptado=a.encrypt(clearText);
		  byte[] desencriptado = a.decrypt(encriptado);
		 
		  String mensaje=bytesToHex(clearText);
		  String mencriptado=bytesToHex(encriptado);
		  String mdesencriptado=bytesToHex(desencriptado);
		  
		   System.out.println("\nTexto Original(HEX)   = "+new String(mensaje));
		   System.out.println("Texto Cifrado(HEX)    = "+new String(mencriptado));
		   System.out.println("Texto Descifrado(HEX) = "+new String(mdesencriptado));
		    

		  }
	  final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	  public static String bytesToHex(byte[] bytes) {
		    char[] hexChars = new char[bytes.length * 2];
		    for ( int j = 0; j < bytes.length; j++ ) {
		        int v = bytes[j] & 0xFF;
		        hexChars[j * 2] = hexArray[v >>> 4];
		        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		    }
		    return new String(hexChars);
		}
}
