package cenfotec.ac.cr.fabricas;

import cenfotec.ac.cr.encriptar.EncryptToASYM;
import cenfotec.ac.cr.encriptar.EncryptToDES;
import cenfotec.ac.cr.encriptar.EncryptToSYM;
import cenfotec.ac.cr.interfaces.Encriptacion;

public class FabricaEncriptacion {

	public static Encriptacion crear(String tipo) {
		switch (tipo) {
		case "ASYMETRIC":
			return new EncryptToASYM();
		case "SYMETRIC":
			return new EncryptToSYM();
		case "DES":
			return new EncryptToDES();
		default:
			return null;
		}
	}

}
