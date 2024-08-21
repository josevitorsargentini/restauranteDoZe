package com.restaurante.web.reserva;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurante.Command;
import com.restaurante.model.Reserva;
import com.restaurante.repository.ReservaDAO;
import com.restaurante.web.template.Template;


public class CreateReserva implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Reserva reserva = new Reserva();
		
		 reserva.setNome(request.getParameter("nome"));
		 reserva.setCpf(request.getParameter("cpf"));
		 reserva.setEmail(request.getParameter("email"));
		 reserva.setData(LocalDate.parse(request.getParameter("data_reserva"))); // Supondo que data_reserva é LocalDate

		    // Salva a reserva usando o DAO
		 ReservaDAO dao = new ReservaDAO();
		 dao.save(reserva);
		
		 response.sendRedirect("home");

	} 
}
