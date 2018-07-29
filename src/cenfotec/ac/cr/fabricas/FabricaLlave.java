package cenfotec.ac.cr.fabricas;

import cenfotec.ac.cr.crearLlave.AsymKey;
import cenfotec.ac.cr.crearLlave.DESKey;
import cenfotec.ac.cr.crearLlave.SymKey;
import cenfotec.ac.cr.interfaces.Llave;

public class FabricaLlave {
	public static Llave crear(String tipo) {
		switch (tipo) {
		case "ASYMETRIC":
			return new AsymKey();
		case "SYMETRIC":
			return new SymKey();
		case "DES":
			return new DESKey();
		default:
			return null;
		}
	}
}
