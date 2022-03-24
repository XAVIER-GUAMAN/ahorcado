package org.xavierAlajo.ahorcado.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xavierAlajo.ahorcado.colecciones.Colecciones;
import org.xavierAlajo.ahorcado.model.Ahorcado;
import org.xavierAlajo.ahorcado.model.UsuarioLoginDTO;

@Controller
@RequestMapping("/acceso")
public class AccesoController {
	static Ahorcado ahorcado = new Ahorcado();
	static String palabraOculta = ahorcado.getPalabraSecreta();
	
	@GetMapping("login")
	public ModelAndView devuelveFormulario(HttpServletRequest solicitudHttp) throws UnknownHostException {
		ModelAndView mAV = new ModelAndView();

		Map<String, String> listaErrores = new HashMap<String, String>();

		// -------- Obtener dirección IP ----------
		String ip = solicitudHttp.getRemoteAddr();

		System.out.println(ip);

		// -------- Obtener version del Navegador ----------
		String navegador = solicitudHttp.getHeader("user-agent");

		// Se crea una sesion
		HttpSession session = solicitudHttp.getSession();

		if (session.getAttribute("usuario1") != null) {
			mAV.addObject("usuario", session.getAttribute("usuario1"));
		} else {
			mAV.addObject("usuario", new UsuarioLoginDTO());
		}

		UsuarioLoginDTO usuarioLogin = new UsuarioLoginDTO();

		// se envia a la vista los siguiente parametros
		mAV.addObject("direccionIP", ip);
		mAV.addObject("navegador", navegador);
		mAV.addObject("usuarioLogin", usuarioLogin);

		// ------------ Coleccciones ----------
		mAV.addObject("credenciales", Colecciones.credenciales);
		mAV.addObject("listaErrores", Colecciones.listaErrores);
		mAV.addObject("listaErrores", listaErrores);

		// redirige a la vista Login.html
		mAV.setViewName("login");
		return mAV;
	}

	@PostMapping("login")
	public ModelAndView recibirCredenciales(@Validated UsuarioLoginDTO usuarioLogin, BindingResult bin,
			HttpServletRequest solicitudHttp) throws UnknownHostException {

		ModelAndView mAV = new ModelAndView();

		/**
		 * Obtener dirección IP y version del navegador
		 */
		// ------------- Direccion IP ---------
		String navegador = solicitudHttp.getHeader("user-agent");
		String ip = solicitudHttp.getRemoteAddr();
		String direccionIP = ip;

		// ------------- Navegador -----------
		String equipo = InetAddress.getLocalHost().getHostName();

		// Se crea una sesion
		HttpSession session = solicitudHttp.getSession();
		session.setAttribute("usuarioLogin", usuarioLogin);

		if (session.getAttribute("usuario") == null) {
			session.setAttribute("usuario", new UsuarioLoginDTO());
		}

		// --------- Comprobacion de usuario/contraseña ---------
		String usuarioIntroducido = usuarioLogin.getUsuario();
		String claveIntroducida = usuarioLogin.getClave();

		System.out.println("usuario introducido es:" + usuarioIntroducido);
		System.out.println("clave introducida es:" + claveIntroducida);

		// ------ Recorrer la coleccion para ver si existe o no el usuario
		boolean acceso = false;
		acceso = Colecciones.verificarUsuario(usuarioIntroducido, claveIntroducida);

		Map<String, String> listaErrores = new HashMap<String, String>();

		if (usuarioIntroducido.equals("")) {
			listaErrores.put("usuario", "El campo usuario no puede estar vacio");
		}

		if (usuarioLogin.getClave() != null) {
			if (usuarioLogin.getClave().equals("")) {
				listaErrores.put("clave", "El campo clave no puede estar vacio");
			}
		}
		if (!acceso) {
			listaErrores.put("acceso", "Usuario contraseña no validos");
		}

		// ------------ Coleccciones ----------
		mAV.addObject("credenciales", Colecciones.credenciales);

		// ------ Parametros ------
		mAV.addObject("usuarioLogin", usuarioLogin);
		mAV.addObject("direccionIP", direccionIP);
		mAV.addObject("navegador", navegador);
		mAV.addObject("listaErrores", Colecciones.listaErrores);

		mAV.addObject("listaErrores", listaErrores);

		if (!acceso) {
			mAV.setViewName("login");
		} else {
			mAV.setViewName("partidaAhorcado");
		}

		return mAV;
	}

	@GetMapping("partidaAhorcado")
	public ModelAndView devuelveForm(HttpServletRequest solicitudHttp, UsuarioLoginDTO usuarioLogin)
			throws UnknownHostException {
		ModelAndView mAV = new ModelAndView();
		// Se crea una sesion
		HttpSession session = solicitudHttp.getSession();

		if (session.getAttribute("usuario") != null) {
			mAV.addObject("usuarioLogin", session.getAttribute("usuario"));
		} else {
			mAV.addObject("usuarioLogin", new UsuarioLoginDTO());
		}

		// Obtener dirección IP
		String ip = InetAddress.getLocalHost().getHostAddress();
		// Obtener version del Navegador
		String navegador = solicitudHttp.getHeader("user-agent");
		// Obtener direccion MAC

		String direccionIP = ip;

		String usuario = "";
		String letraIntroducida = "";

		// se envia a la vista los siguiente parametros
		mAV.addObject("direccionIP", direccionIP);
		mAV.addObject("navegador", navegador);
		mAV.addObject("usuario", usuario);
		mAV.addObject("usuarioLogin", usuarioLogin);

		mAV.addObject("letraIntroducida", letraIntroducida);

		// redirige a la vista
		mAV.setViewName("partidaAhorcado");
		return mAV;

	}

	@PostMapping("post")
	public ModelAndView recibirDatosAhorcado(@Validated UsuarioLoginDTO usuarioLogin, BindingResult bin,
			HttpServletRequest solicitudHttp, @RequestParam(required = false) String clave,
			@RequestParam(required = false) String letraIntroducida) throws UnknownHostException {

		ModelAndView mAV = new ModelAndView();
		// ------------- Direccion IP ---------
		String ip = solicitudHttp.getRemoteAddr();
		String direccionIP = ip;

		// ------------- Navegador -----------
		String navegador = solicitudHttp.getHeader("user-agent");

		HttpSession session = solicitudHttp.getSession();
		session.setAttribute("usuarioLogin", usuarioLogin);

		if (session.getAttribute("usuario") == null) {
			session.setAttribute("usuario", new UsuarioLoginDTO());
		}

		// Almacenar las palabras
		ArrayList<String> listadoPalabrasIntroducidas = new ArrayList<String>();
		Colecciones.listadoPalabrasIntroducidas.add(letraIntroducida);

		String letra = letraIntroducida;

//		Colecciones.listadoPalabrasIntroducidas.forEach((listadoLetras) -> {
//			System.out.println(listadoLetras);
//			
//			mAV.addObject("listadoLetras", listadoLetras);
//		});
//		

		for (int x = 0; x < Colecciones.listadoPalabrasIntroducidas.size(); x++) {
			String listadoLetras = Colecciones.listadoPalabrasIntroducidas.get(x);

			mAV.addObject("listadoLetras", listadoLetras);
			System.out.print(listadoLetras);

		}

		System.out.println("letra introducida " + letraIntroducida);

		// ------ Parametros ------
		mAV.addObject("usuarioLogin", usuarioLogin);
		mAV.addObject("direccionIP", direccionIP);
		mAV.addObject("navegador", navegador);
		mAV.addObject("letraIntroducida", letraIntroducida);

		mAV.addObject("letra", letra);

		mAV.addObject("palabraOculta", palabraOculta); // --------- Palabra oculta -----------

		mAV.setViewName("partidaAhorcado");

		return mAV;
	}
}