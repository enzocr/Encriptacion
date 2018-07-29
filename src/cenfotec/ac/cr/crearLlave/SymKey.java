package cenfotec.ac.cr.crearLlave;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cenfotec.ac.cr.interfaces.Llave;

public class SymKey implements Llave {

	public void createKey(String name) throws Exception {

		StringBuilder randomkey = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			randomkey.append(Integer.parseInt(Double.toString((Math.random() + 0.1) * 1000).substring(0, 2)));
		}
		byte[] key = randomkey.toString().getBytes("UTF-8");
		writeBytesFile(name, key, ".key");
	}

	private void writeBytesFile(String name, byte[] content, String type) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream("C:/encrypt/symetric/" + name + type);
		fos.write(content);
		fos.close();
	}

}
