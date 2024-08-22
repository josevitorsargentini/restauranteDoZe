package com.restaurante.web.reserva;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurante.Command;
import com.restaurante.web.ControllerHelper;
import com.restaurante.web.HomeServlet;

@WebServlet("/reserva/*")
public class ReservaController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request(request, response);
	}

private void request(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try {
			
			Command cmd = getCommand(request);
			cmd.execute(request, response);
			
		} catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			throw new ServletException(e);
		}

	}
	
	private Command getCommand(HttpServletRequest request) {
		String operation = ControllerHelper.extractOperation(request);
		System.out.println(operation);
		if (operation.matches(".*\\.(css|js|png|jpg|jpeg|gif|svg)$")) {
	        return null;
	    }
		
		Command cmd = null;
		switch (operation) {
			case "/reserva/agendar":
				cmd = new CreateReserva();
				break;
			
			case "/reserva/reservaInfo":
				cmd = new ReservaInfo();
				break;
				
			case "/reserva/confirmada":
				cmd = new ReservaConfirmed();
				break;
			case "/reserva/negada":
				cmd = new ReservaNegada();
				break;
			
			default:
	            System.err.println("Operação desconhecida: " + operation);
	            break;
		
		}
		
		return cmd;
	}


}	

