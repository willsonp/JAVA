/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rwp0002
 */
public class Encriptar {
    
    private static final char[] HEXADECIMAL = {
    '0', '1', '2', '3', '4', '5', '6', '7',
    '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
  //Puede serte util tener este metodo aparte
  public static String hexEncode(byte[] data) {
    StringBuilder sb = new StringBuilder(2 * data.length);
    for (int i = 0; i < data.length; i++) {
      int low = (data[i] & 0x0f);
      int high = ((data[i] & 0xf0) >> 4);
      sb.append(HEXADECIMAL[high]);
      sb.append(HEXADECIMAL[low]);
    }
    return sb.toString();
  }
  public static byte[] hash(String stringToHash, String alg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance(alg);
    return md.digest(stringToHash.getBytes());
  }
  public static String convertToSHA1(String stringToHash) {
    try {
      return hexEncode(hash(stringToHash, "SHA-1"));
    } catch (NoSuchAlgorithmException ex) {
      //Eso no deberia pasar nunca a menos que la JVM de plano estuviera mal instalada
    }
    return "";
  }
  public static String convertToMD5(String stringToHash) {
    try {
      return hexEncode(hash(stringToHash, "MD5"));
    } catch (NoSuchAlgorithmException ex) {
      //Eso no deberia pasar nunca a menos que la JVM de plano estuviera mal instalada
    }
    return "";
  }
                
}
