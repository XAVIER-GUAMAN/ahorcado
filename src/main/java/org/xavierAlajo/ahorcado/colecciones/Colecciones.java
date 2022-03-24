package org.xavierAlajo.ahorcado.colecciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class Colecciones {

	public static SortedMap<String, String> credenciales = new TreeMap<String, String>();
	public static ArrayList<String> listaL = new ArrayList<String>();
	public static SortedMap<String, String> listaErrores = new TreeMap<String, String>();
	public static ArrayList<String> listadoPalabrasIntroducidas = new ArrayList<String>();;

	// Se crean unos usuarios de muestra
	static {
		credenciales.put("root@mail.com", "root");
		credenciales.put("prueba1@mail.com", "prueba1");
		credenciales.put("alfa@mail.com", "alfa");
		credenciales.put("beta@mail.com", "beta");

	}

	public static boolean verificarUsuario(String usuario, String clave) {
		String claveMapa;

		claveMapa = credenciales.get(usuario);

		if (claveMapa != null) {
			if (claveMapa.equals(clave)) {
				return true;
			}
		}
		return false;
	}

}
