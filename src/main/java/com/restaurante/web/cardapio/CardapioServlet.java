package com.restaurante.web.cardapio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurante.model.Prato;
import com.restaurante.repository.PratoDAO;
import com.restaurante.web.template.Template;


@WebServlet("/cardapio")
public class CardapioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			PratoDAO dao = new PratoDAO();
	        List<Prato> pratos;
			pratos = dao.findAll();	
	        request.setAttribute("pratos", pratos);
	        Template.render("cardapio", request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
