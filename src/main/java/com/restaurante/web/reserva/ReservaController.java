package com.restaurante.web.reserva;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.restaurante.Command;
import com.restaurante.web.ControllerHelper;


@WebServlet("/reserva/*")
public class ReservaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Map<String, Command> commandMap = new HashMap<>();

	@Override
	public void init() throws ServletException {

		commandMap.put("/reserva/agendar", new CreateReserva());
		commandMap.put("/reserva/cancelar", new CancelReserva());
		commandMap.put("/reserva/reservaCancel", new ReservaCancel());
		commandMap.put("/reserva/reservaInfo", new ReservaInfo());
		commandMap.put("/reserva/confirmada", new ReservaConfirmed());
		commandMap.put("/reserva/negada", new ReservaNegada());
		commandMap.put("/reserva/reservaCancelada", new ReservaCancelada());
		commandMap.put("/reserva/check", new  CheckDisponibilidadeCommand());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request(request, response);
	}

	private void request(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {

			Command cmd = commandMap.get(ControllerHelper.extractOperation(request));
			cmd.execute(request, response);

		} catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			throw new ServletException(e);
		}

	}

}
