package com.restaurante.web.reserva;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurante.Command;
import com.restaurante.model.Reserva;
import com.restaurante.repository.ReservaDAO;


public class CreateReserva implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Reserva reserva = new Reserva();

		reserva.setNome(request.getParameter("nome"));
		reserva.setCpf(request.getParameter("cpf"));
		reserva.setEmail(request.getParameter("email"));
		reserva.setData(LocalDate.parse(request.getParameter("data_reserva"))); 

	
		ReservaDAO dao = new ReservaDAO();

		if (!dao.isDateAvailable(LocalDate.parse(request.getParameter("data_reserva"))) || !dao.isCpfAvailable(LocalDate.parse(request.getParameter("data_reserva")),request.getParameter("cpf")) || dao.isDataLimit(LocalDate.parse(request.getParameter("data_reserva")))) {
		
			response.sendRedirect(request.getContextPath() + "/reserva/negada");
		} else {
			dao.save(reserva);

			response.sendRedirect(request.getContextPath() + "/reserva/confirmada");

		}
	}
}