package cenfotec.ac.cr.desencriptar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.Cipher;

import cenfotec.ac.cr.interfaces.Desencriptacion;

public class DecryptToASYM implements Desencriptacion {
	public void decryptMessage(String messageName, String keyName) throws Exception {
		PrivateKey privKey = (PrivateKey) readKeyFromFile(keyName, "private");
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		byte[] encryptedMessage = readMessageFile(messageName);
		byte[] decryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(decryptedData, StandardCharsets.UTF_8);
		System.out.println("El mensaje era: ");
		System.out.println(message);
	}

	private byte[] readMessageFile(String messageName) throws Exception {
		File file = new File("C:/encrypt/asymetric/" + messageName + ".encript");
		int length = (int) file.length();
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[length];
		reader.read(bytes, 0, length);
		reader.close();
		Decoder oneDecoder = Base64.getDecoder();
		return oneDecoder.decode(bytes);

	}

	Key readKeyFromFile(String keyFileName, String type) throws IOException {
		InputStream in = new FileInputStream("C:/encrypt/asymetric/" + keyFileName + type + ".key");
		ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));
		try {
			BigInteger m = (BigInteger) oin.readObject();
			BigInteger e = (BigInteger) oin.readObject();
			if (type.equals("public")) {
				RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
				KeyFactory fact = KeyFactory.getInstance("RSA");
				PublicKey pubKey = fact.generatePublic(keySpec);
				return pubKey;
			} else {
				RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
				KeyFactory fact = KeyFactory.getInstance("RSA");
				PrivateKey pubKey = fact.generatePrivate(keySpec);
				return pubKey;
			}
		} catch (Exception e) {
			throw new RuntimeException("Spurious serialisation error", e);
		} finally {
			oin.close();
		}
	}

}
