package br.edu.cassio;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Criptografia {

	
	public static String encriptar(String sentenca){
		String encriptado = "";
		for (int i=0 ; i < sentenca.length(); i++) {
			Character charAt = sentenca.charAt(i);
			int ascii = (int) charAt;
			char c = (char) (ascii*2);
			encriptado = encriptado + c;
		}
		return encriptado;
	}
	
	private  String toString(List<Byte> bytes) {
		
//		  return new String(Bytes.toArray(bytes), StandardCharsets.UTF_8);
		return null;
	}
	
	

//	  int getIntBytesFromStr(String texto) {
//	    List<int> bytes = utf8.encode(texto);
//	    var join = bytes.join();
//	    return int.parse(join);
//	  }
	
	public static String decriptar(String sentenca){
		String decrypt = "";
		for (int i=0 ; i < sentenca.length(); i++) {
			Character charAt = sentenca.charAt(i);
			int ascii = (int) charAt;
			char c = (char) (ascii/2);
			decrypt += c;
		}
		return decrypt;
	}
			
	public static void main(String argv[]) throws Exception {
		String texto = "abcdefg";
		String encriptado = encriptar(texto);
		System.out.println(encriptado);
		String decriptado = decriptar(encriptado);
		System.out.println(decriptado);
	}
}
