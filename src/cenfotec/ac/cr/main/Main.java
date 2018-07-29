package cenfotec.ac.cr.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import cenfotec.ac.cr.controller.Controller;

public class Main {

	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static Controller mainController = new Controller();

	public static void main(String[] args) throws Exception {
		int opc;

		do {
			mostrarMenu();
			opc = leerOpcion();
			ejecutarAccion(opc);
		} while (opc != 4);
	}

	public static void mostrarMenu() throws IOException {

		System.out.println();
		System.out.println("------  1. ASYMETRIC  -----");
		System.out.println("------  2. SYMETRIC   -----");
		System.out.println("------  3. DES        -----");
		System.out.println("------  4. SALIR      -----");
	}

	public static int leerOpcion() throws IOException {

		int opcion;

		System.out.println("Seleccione accion a realizar");
		opcion = Integer.parseInt(in.readLine());

		return opcion;
	}

	public static void ejecutarAccion(int pOpcion) throws Exception {

		switch (pOpcion) {

		case 1:
			menu("ASYMETRIC");
			break;
		case 2:
			menu("SYMETRIC");
			break;
		case 3:
			menu("DES");
			break;
		case 4:
			break;

		default:
			System.out.println("Opcion invalida");

		}

	}

	private static void menu(String type) throws Exception {
		int option;
		System.out.println("1.Create" + type + " key");
		System.out.println("2.Encript" + type + "Message");
		System.out.println("3.Decrypt" + type + " Message");
		System.out.println("4.Exit " + type);
		option = Integer.parseInt(in.readLine());

		switch (option) {

		case 1:
			createKey(type);
			break;
		case 2:
			encryptMessage(type);
			break;
		case 3:
			decryptMessage(type);
			break;
		case 4:
			break;

		default:
			System.out.println("Opcion invalida");

		}

	}

	private static void createKey(String type) throws Exception {
		System.out.println("Key name: ");
		String name = in.readLine();
		mainController.crearLlave(name, type);
	}

	private static void encryptMessage(String type) throws Exception {
		System.out.println("Key name: ");
		String name = in.readLine();
		System.out.println("Message name: ");
		String messageName = in.readLine();
		System.out.println("Message: ");
		String message = in.readLine();
		mainController.encriptar(messageName, message, name, type);
	}

	private static void decryptMessage(String type) throws Exception {
		System.out.println("Key name: ");
		String keyName = in.readLine();
		System.out.println("Message name: ");
		String messageName = in.readLine();
		mainController.desencriptar(messageName, keyName, type);
	}

}
