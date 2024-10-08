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
		commandMap.put("/reserva/cancelar", new ReservaCancelarCommand());
		commandMap.put("/reserva/cancelar/form", new ReservaCancelFormsCommand());
		commandMap.put("/reserva/cancelar/negada", new ReservaCanceladaNegadaCommand());
		commandMap.put("/reserva/cancelar/confirmada", new ReservaCanceladaCommand());
		commandMap.put("/reserva/agendar", new CreateReservaCommand());
		commandMap.put("/reserva/form", new ReservaInfoCommand());
		commandMap.put("/reserva/confirmada", new ReservaConfirmedCommand());
		commandMap.put("/reserva/negada", new ReservaNaoConfirmada());
		
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
