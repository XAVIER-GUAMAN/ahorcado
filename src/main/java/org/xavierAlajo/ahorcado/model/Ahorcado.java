package org.xavierAlajo.ahorcado.model;

import java.util.Random;

public class Ahorcado {
	int vidas;
	String letraIntroducida;
	int intentos;
	String letraPertenece;
	String palabraSecreta = palabraSecreta();
	char[] palabraGuion = guionesContienePalabra(palabraSecreta);

	public static String palabraSecreta() {

		String[] listadoPalabras = { "casa", "perro", "gato", "anillo", "coche", "virus", "curso", "maquina" };
		Random r = new Random();
		int posicion = r.nextInt(listadoPalabras.length);

		String palabraSecreta = listadoPalabras[posicion]; // se elige una palabra de manera aleatoria

		return palabraSecreta;

	}

	static char[] guionesContienePalabra(String palabraSecreta) {
		int nLetras = palabraSecreta.length(); // cuenta el numero de letras que contiene la palabra

		char[] palabraGuiones = new char[nLetras];
		for (int i = 0; i < nLetras; i++) {
			palabraGuiones[i] = '-';
		}

		return palabraGuiones;
	}

	public static String letrasIntroducidas() {
		return "";

	}

	public Ahorcado() {
		super();
	}

	public Ahorcado(int vidas, String letraIntroducida, int intentos, String letraPertenece, String palabraSecreta) {
		super();
		this.vidas = vidas;
		this.letraIntroducida = letraIntroducida;
		this.intentos = intentos;
		this.letraPertenece = letraPertenece;
		this.palabraSecreta = palabraSecreta;
	}

	public char[] getPalabraGuiones() {
		return getPalabraGuiones();
	}

	public void setPalabraGuiones(char[] palabraGuiones) {
		this.palabraGuion = palabraGuiones;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public String getLetraIntroducida() {
		return letraIntroducida;
	}

	public void setLetraIntroducida(String letraIntroducida) {
		this.letraIntroducida = letraIntroducida;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public String getLetraPertenece() {
		return letraPertenece;
	}

	public void setLetraPertenece(String letraPertenece) {
		this.letraPertenece = letraPertenece;
	}

	public String getPalabraSecreta() {
		return palabraSecreta;
	}

	public void setPalabraSecreta(String palabraSecreta) {
		this.palabraSecreta = palabraSecreta;
	}

}
