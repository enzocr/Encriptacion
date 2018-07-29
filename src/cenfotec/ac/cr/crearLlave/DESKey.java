package cenfotec.ac.cr.crearLlave;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import cenfotec.ac.cr.interfaces.Llave;

public class DESKey implements Llave {

	@Override
	public void createKey(String name) throws Exception {
		//KeyPairGenerator kpg = KeyPairGenerator.getInstance("DES");
		//KeyFactory fact = KeyFactory.getInstance("DES");
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		SecretKey desKey = kg.generateKey();
		
		
		
		//DESKeySpec key = new DESKeySpec(name.getBytes());
		//SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

		//kpg.initialize(2048);
		//KeyPair kp = kpg.genKeyPair();
		//DESKeySpec pub = fact.getKeySpec(kp.getPublic(), DESKeySpec.class);

		//writeBytesFile(name, desKey.getEncoded(), ".key");
		saveToFile("C:/encrypt/DES/" + name + "public.key", desKey.getEncoded());
	}
	public void saveToFile(String fileName, byte[] content ) throws IOException {
		ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		try {
			oout.writeObject(content);
		} catch (Exception e) {
			throw new IOException("Unexpected error", e);
		} finally {
			oout.close();
		}
	}
	private void writeBytesFile(String name, byte[] content, String type) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream("C:/encrypt/DES/" + name + type);
		fos.write(content);
		fos.close();
	}
}
