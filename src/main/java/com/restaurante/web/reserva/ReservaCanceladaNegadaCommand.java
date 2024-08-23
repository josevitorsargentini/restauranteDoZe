package com.restaurante.web.reserva;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurante.Command;
import com.restaurante.web.template.Template;

public class ReservaCanceladaNegadaCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Template.render("reserva/reservaNaoCancelada", request, response);
	} 
}