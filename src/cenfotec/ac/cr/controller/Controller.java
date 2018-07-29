package cenfotec.ac.cr.controller;

import cenfotec.ac.cr.manager.Manager;

public class Controller {

	public void encriptar(String messageName, String message, String keyName, String type) throws Exception {

		Manager manager = new Manager();
		manager.encriptar(messageName, message, keyName, type);
	}

	public void desencriptar(String messageName, String keyName, String type) throws Exception {

		Manager manager = new Manager();
		manager.desencriptar(messageName, keyName, type);
	}

	public void crearLlave(String name, String type) throws Exception {

		Manager manager = new Manager();
		manager.crearLlave(name, type);
	}

}
