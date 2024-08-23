package com.restaurante.web.cardapio;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.restaurante.Command;
import com.restaurante.model.Prato;
import com.restaurante.repository.PratoDAO;
import com.restaurante.web.template.Template;

public class CardapioListaCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		List<Prato> pratos = (List<Prato>) session.getAttribute("pratos");

		if (pratos == null) {
			try {
				PratoDAO dao = new PratoDAO();
				pratos = dao.findAll();
				session.setAttribute("pratos", pratos);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("pratos", pratos);
		Template.render("cardapio/cardapio", request, response);
	}
} 
