package cenfotec.ac.cr.fabricas;

import cenfotec.ac.cr.desencriptar.DecryptToASYM;
import cenfotec.ac.cr.desencriptar.DecryptToDES;
import cenfotec.ac.cr.desencriptar.DecryptToSYM;
import cenfotec.ac.cr.interfaces.Desencriptacion;

public class FabricaDesencriptacion {
	public static Desencriptacion crear(String tipo) {
		switch (tipo) {
		case "ASYMETRIC":
			return new DecryptToASYM();
		case "SYMETRIC":
			return new DecryptToSYM();
		case "DES":
			return new DecryptToDES();
		default:
			return null;
		}
	}
}
