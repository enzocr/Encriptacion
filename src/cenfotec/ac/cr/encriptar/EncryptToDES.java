package cenfotec.ac.cr.encriptar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import cenfotec.ac.cr.interfaces.Encriptacion;

public class EncryptToDES implements Encriptacion {

	@Override
	public void encryptMessage(String messageName, String message, String keyName) throws Exception {

		PublicKey pubKey = (PublicKey) readKeyFromFile(keyName, "public");
		Cipher cipher = Cipher.getInstance("DES");

		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
		Encoder oneEncoder = Base64.getEncoder();
		encryptedData = oneEncoder.encode(encryptedData);
		writeBytesFile(messageName, encryptedData, ".encript");

	}

	Key readKeyFromFile(String keyFileName, String type) throws IOException {
		InputStream in = new FileInputStream("C:/encrypt/DES/" + keyFileName + type + ".key");
		ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));
		try {
			BigInteger m = (BigInteger) oin.readObject();
			BigInteger e = (BigInteger) oin.readObject();
			if (type.equals("public")) {
				DESKeySpec keySpec = new DESKeySpec(keyFileName.getBytes());
				KeyFactory fact = KeyFactory.getInstance("DES");
				PublicKey pubKey = fact.generatePublic(keySpec);
				return pubKey;
			} else {
				DESKeySpec keySpec = new DESKeySpec(keyFileName.getBytes());
				KeyFactory fact = KeyFactory.getInstance("DES");
				PrivateKey pubKey = fact.generatePrivate(keySpec);
				return pubKey;
			}
		} catch (Exception e) {
			throw new RuntimeException("Spurious serialisation error", e);
		} finally {
			oin.close();
		}
	}

	private void writeBytesFile(String name, byte[] content, String type) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream("C:/encrypt/DES/" + name + type);
		fos.write(content);
		fos.close();
	}

}