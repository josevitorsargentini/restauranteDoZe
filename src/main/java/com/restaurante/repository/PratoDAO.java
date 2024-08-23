package com.restaurante.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.restaurante.model.Prato;

public class PratoDAO {

	public List<Prato> findAll() throws SQLException {
		List<Prato> pratos = new ArrayList<>();

		try (Connection conn = DatabaseConnector.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id, nome, descricao, preco FROM prato;")) {

			while (rs.next()) {
				Prato prato = mapRow(rs);
				pratos.add(prato);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pratos;
	}

	private Prato mapRow(ResultSet rs) throws SQLException {

		Prato prato = new Prato();
		prato.setId(rs.getInt("id"));
		prato.setNome(rs.getString("nome"));
		prato.setDescricao(rs.getString("descricao"));
		prato.setPreco(rs.getDouble("preco"));

		return prato;
	}

}