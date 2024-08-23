package com.restaurante.web.reserva;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurante.Command;
import com.restaurante.repository.ReservaDAO;


public class ReservaCancelarCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		final String cpf = request.getParameter("cpf");
		final LocalDate dataReserva = LocalDate.parse(request.getParameter("data_reserva"));
	
		ReservaDAO dao = new ReservaDAO();
		
		if (dao.cancelReserva(cpf, dataReserva)) {
		
			response.sendRedirect(request.getContextPath() + "/reserva/cancelar/confirmada");
		} else {
			response.sendRedirect(request.getContextPath() + "/reserva/cancelar/negada");
		}
	}
}