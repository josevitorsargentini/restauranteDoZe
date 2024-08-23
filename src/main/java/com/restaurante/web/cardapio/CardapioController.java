package com.restaurante.web.cardapio;

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

@WebServlet("/cardapio/*")
public class CardapioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Map<String, Command> commandMap = new HashMap<>();

	@Override
	public void init() throws ServletException {
		commandMap.put("/cardapio/listar", new CardapioListaCommand());
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

