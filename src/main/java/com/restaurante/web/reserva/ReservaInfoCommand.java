package com.restaurante.web.reserva;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurante.Command;
import com.restaurante.model.Reserva;
import com.restaurante.repository.ReservaDAO;
import com.restaurante.web.template.Template;


public class ReservaInfoCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Template.render("reserva/reservaInfo", request, response);
	} 
}
