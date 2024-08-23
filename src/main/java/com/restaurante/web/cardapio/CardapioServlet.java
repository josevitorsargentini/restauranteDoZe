package com.restaurante.web.cardapio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.restaurante.model.Prato;
import com.restaurante.repository.PratoDAO;
import com.restaurante.web.template.Template;


@WebServlet("/cardapio")
public class CardapioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
