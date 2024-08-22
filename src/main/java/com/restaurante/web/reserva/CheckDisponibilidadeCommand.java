package com.restaurante.web.reserva;

import com.restaurante.Command;
import com.restaurante.repository.ReservaDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class CheckDisponibilidadeCommand implements Command {

    private ReservaDAO reservaDAO = new ReservaDAO(); // Instanciar o DAO

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dataReserva = request.getParameter("data_reserva");
        LocalDate dataReservaParse = LocalDate.parse(dataReserva);

        boolean disponivel = false;
		try {
			disponivel = reservaDAO.isDateAvailable(dataReservaParse);
		} catch (SQLException e) {
			e.printStackTrace();
		} // Usar o DAO para verificar disponibilidade

        response.setContentType("application/json");
        response.getWriter().print("{\"disponivel\": " + disponivel + "}");
    }
}
