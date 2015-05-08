package com.boun.hashes;

import java.security.MessageDigest;

import com.boun.signature.Base58;

public class HashOperations {

	public static String getHash(String input) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(input.getBytes("UTF-8"));
		return Base58.encode(md.digest());
	}
	
	public static boolean checkHash(String content,String hash) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(content.getBytes("UTF-8"));
		String correctHash = Base58.encode(md.digest());
		return hash.equals(correctHash);		
	}
	
}
