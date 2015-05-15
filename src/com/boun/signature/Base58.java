package com.boun.signature;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class Base58 {

	private static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private static final BigInteger BASE = BigInteger.valueOf(58);
    
    public static boolean checkEncoding(String s){
    	
    	for(int i=0;i<s.length();++i){
    		
    		if(!ALPHABET.contains(s.charAt(i)+"")){
    			return false;
    		}
    		
    	}
    	
    	return true;
    	
    }

    public static String encode(byte[] input) {
       
        BigInteger bi = new BigInteger(1, input);
        StringBuffer s = new StringBuffer();
        while (bi.compareTo(BASE) >= 0) {
            BigInteger mod = bi.mod(BASE);
            s.insert(0, ALPHABET.charAt(mod.intValue()));
            bi = bi.subtract(mod).divide(BASE);
        }
        s.insert(0, ALPHABET.charAt(bi.intValue()));
        
        for (byte anInput : input) {
            if (anInput == 0)
                s.insert(0, ALPHABET.charAt(0));
            else
                break;
        }
        return s.toString();
    }

    public static byte[] decode(String input) throws Exception {
        byte[] bytes = decodeToBigInteger(input).toByteArray();
        
        boolean stripSignByte = bytes.length > 1 && bytes[0] == 0 && bytes[1] < 0;
        
        int leadingZeros = 0;
        for (int i = 0; input.charAt(i) == ALPHABET.charAt(0); i++) {
            leadingZeros++;
        }
    
        byte[] tmp = new byte[bytes.length - (stripSignByte ? 1 : 0) + leadingZeros];
        System.arraycopy(bytes, stripSignByte ? 1 : 0, tmp, leadingZeros, tmp.length - leadingZeros);
        return tmp;
    }

    public static BigInteger decodeToBigInteger(String input) throws Exception {
        BigInteger bi = BigInteger.valueOf(0);

        for (int i = input.length() - 1; i >= 0; i--) {
            int alphaIndex = ALPHABET.indexOf(input.charAt(i));
            if (alphaIndex == -1) {
                throw new Exception("Illegal character " + input.charAt(i) + " at " + i);
            }
            bi = bi.add(BigInteger.valueOf(alphaIndex).multiply(BASE.pow(input.length() - 1 - i)));
        }
        return bi;
    }

    public static byte[] decodeChecked(String input) throws Exception {
        byte[] tmp = decode(input);
        if (tmp.length < 4)
            throw new Exception("Input too short");
        byte[] checksum = new byte[4];
        System.arraycopy(tmp, tmp.length - 4, checksum, 0, 4);
        byte[] bytes = new byte[tmp.length - 4];
        System.arraycopy(tmp, 0, bytes, 0, tmp.length - 4);
        tmp = doubleDigest(bytes, 0, bytes.length);
        byte[] hash = new byte[4];
        System.arraycopy(tmp, 0, hash, 0, 4);
        if (!Arrays.equals(hash, checksum))
            throw new Exception("Checksum does not validate");
        return bytes;
    }
    
    public static byte[] doubleDigest(byte[] input, int offset, int length) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(input, offset, length);
            byte[] first = digest.digest();
            return digest.digest(first);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);  // Cannot happen.
        }
    }
    
}
