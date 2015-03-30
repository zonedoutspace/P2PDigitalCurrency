package com.boun.signature;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.boun.file.FileOperations;
import com.boun.structures.StringsKeyPair;

public class Generator {

	public static final String publicKeyFile = "/resources/keys/publicKey.txt";
	public static final String privateKeyFile = "/resources/keys/privateKey.txt";



	public static void createMyKeys() throws Exception {

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(512);
		KeyPair keyPair = kpg.genKeyPair();

		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		byte[] privateByte = privateKey.getEncoded();
		byte[] publicByte  = publicKey.getEncoded();

		FileOperations.writeFile(privateKeyFile,Base58.encode(privateByte));
		FileOperations.writeFile(publicKeyFile,Base58.encode(publicByte));		  
	}
	
	public static StringsKeyPair createKeys() throws Exception {

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(512);
		KeyPair keyPair = kpg.genKeyPair();

		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		byte[] privateByte = privateKey.getEncoded();
		byte[] publicByte  = publicKey.getEncoded();

		StringsKeyPair stringsKeyPair = new StringsKeyPair();
		
		stringsKeyPair.setPrivateKey(Base58.encode(privateByte));
		stringsKeyPair.setPublicKey(Base58.encode(publicByte));
		
		return stringsKeyPair;
	}

	public static PrivateKey getMyPrivateKey() throws Exception{

		String keyString =  FileOperations.readFile(privateKeyFile);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		KeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base58.decode(keyString));
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

		return privateKey;

	}


	public static PublicKey getMyPublicKey() throws Exception{

		String keyString =  FileOperations.readFile(publicKeyFile);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base58.decode(keyString));
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

		return publicKey;
	}
	
	public static String getMyPublicKeyString() throws Exception{

		return  FileOperations.readFile(publicKeyFile);
		
	}

	public static PublicKey getPublicKey(String publicKeyString) throws Exception{

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base58.decode(publicKeyString));
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

		return publicKey;

	}

	public static String sign(String content) throws Exception{

		Signature sig = Signature.getInstance("MD5WithRSA");
		sig.initSign(getMyPrivateKey());
		sig.update(content.getBytes());
		byte[] signatureBytes = sig.sign();
		return Base58.encode(signatureBytes);
	}

	public static boolean checkSign(String content,String sign,PublicKey publicKey) throws Exception{

		Signature sig = Signature.getInstance("MD5WithRSA");
		sig.initVerify(publicKey);
		sig.update(content.getBytes());
		return sig.verify(Base58.decode(sign));

	}


}