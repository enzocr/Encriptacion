package cenfotec.ac.cr.manager;

import cenfotec.ac.cr.fabricas.FabricaDesencriptacion;
import cenfotec.ac.cr.fabricas.FabricaEncriptacion;
import cenfotec.ac.cr.fabricas.FabricaLlave;
import cenfotec.ac.cr.interfaces.Desencriptacion;
import cenfotec.ac.cr.interfaces.Encriptacion;
import cenfotec.ac.cr.interfaces.Llave;

public class Manager {

	Encriptacion encriptacion;
	Desencriptacion desencriptacion;
	Llave llave;

	public void encriptar(String messageName, String message, String keyName, String type) throws Exception {
		encriptacion = FabricaEncriptacion.crear(type);
		encriptacion.encryptMessage(messageName, message, keyName);
	}

	public void desencriptar(String messageName, String keyName, String type) throws Exception {
		desencriptacion = FabricaDesencriptacion.crear(type);
		desencriptacion.decryptMessage(messageName, keyName);
	}

	public void crearLlave(String name, String type) throws Exception {
		llave = FabricaLlave.crear(type);
		llave.createKey(name);
	}
}
