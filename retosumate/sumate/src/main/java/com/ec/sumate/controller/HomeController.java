package com.ec.sumate.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ec.sumate.dao.ClasificacionDAO;
import com.ec.sumate.dao.EventoDAO;
import com.ec.sumate.dao.UsuarioDAO;
import com.ec.sumate.entity.Clasificacion;
import com.ec.sumate.entity.Evento;
import com.ec.sumate.entity.Usuario;
import com.ec.sumate.service.ServicioUsuarios;
import com.ec.sumate.service.impl.ServicioUsuariosImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

//	@Autowired
//	private EventoDAO eventoDao;
//	
//	@Autowired
//	private ClasificacionDAO clasificacionDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

//	@RequestMapping(value="/usuarios", method = RequestMethod.GET)
//	public ModelAndView getUsuarios(Locale locale, Model model) {
//		List<Usuario> listUsers = usuarioDao.getList();
//		ModelAndView modelVw = new ModelAndView("usuarios");
//		modelVw.addObject("usuariosList", listUsers);
//		return modelVw;
//	}
//	
//	@RequestMapping(value="/eventos", method = RequestMethod.GET)
//	public ModelAndView getEventos(Locale locale, Model model) {
//		List<Evento> listEvents = eventoDao.getList();
//		ModelAndView modelViw = new ModelAndView("eventos");
//		modelViw.addObject("eventosList", listEvents);
//		return modelViw;
//	}
//	
//	@RequestMapping(value="/clasificaciones", method = RequestMethod.GET)
//	public ModelAndView getClasificaciones() {
//		List<Clasificacion> listEvents = clasificacionDao.getList();
//		ModelAndView model = new ModelAndView("clasificaciones");
//		model.addObject("clasificacionesList", listEvents);
//		return model;
//	}
	
	private ServicioUsuariosImpl servicioUsuarioImpl = new ServicioUsuariosImpl();
	
	public ServicioUsuariosImpl getServicioUsuario() {
		return servicioUsuarioImpl;
	}
	
	@RequestMapping(value="/getusuarioinfo", method = RequestMethod.GET)
	public ModelAndView getUsuarioInfo(@RequestParam("userId")Long userId) {
		
		ModelAndView model = new ModelAndView("usuarioInfo");
		Usuario usuario = getServicioUsuario().getUsuarioById(userId);
		model.addObject("usuarioInfo", usuario);
		return model;
	}
}
