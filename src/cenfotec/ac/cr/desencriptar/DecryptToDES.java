package cenfotec.ac.cr.desencriptar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import cenfotec.ac.cr.interfaces.Desencriptacion;

public class DecryptToDES implements Desencriptacion {

	@Override
	public void decryptMessage(String messageName, String keyName) throws Exception {

		byte[] key = readKeyFile(keyName);
		byte[] encryptedMessage = readMessageFile(messageName);
		System.out.println(encryptedMessage.length);
		Cipher cipher = Cipher.getInstance("DES");
		SecretKeySpec k = new SecretKeySpec(key, "DES");
		cipher.init(Cipher.DECRYPT_MODE, k);
		byte[] DecryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(DecryptedData, StandardCharsets.UTF_8);
		System.out.println("El mensaje era: ");
		System.out.println(message);

	}
	private byte[] readMessageFile(String messageName) throws Exception {
		File file = new File("C:/encrypt/DES/" + messageName + ".encript");
		int length = (int) file.length();
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[length];
		reader.read(bytes, 0, length);
		Decoder oneDecoder = Base64.getDecoder();
		reader.close();
		return oneDecoder.decode(bytes);
	}


	private byte[] readKeyFile(String keyName) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:/encrypt/DES/" + keyName + ".key"));
		String everything = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			everything = sb.toString();
		} finally {
			br.close();
		}
		return everything.getBytes(StandardCharsets.UTF_8);
	}

}
