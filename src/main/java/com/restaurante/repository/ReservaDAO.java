package com.restaurante.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.restaurante.model.Reserva;

public class ReservaDAO {
	public Reserva save(Reserva reserva) throws SQLException   {
	    if (reserva == null) {
	        throw new IllegalStateException("cliente == null");
	    }

	    try (Connection conn = DatabaseConnector.getConnection()) {

	        try {
	            /* Iniciando transação */
	            conn.setAutoCommit(false);

	            /* Inserindo registro na tabela `cliente` */
	            PreparedStatement ps = conn.prepareStatement(
	                    "INSERT INTO Reserva (nome, cpf, email, data_reserva) VALUES (?, ?, ?, ?);", 
	                    Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, reserva.getNome());
	            ps.setString(2, reserva.getCpf());
	            ps.setString(3, reserva.getEmail());
	            ps.setDate(4, Date.valueOf(reserva.getData())); // Supondo que `getDataReserva()` retorna um LocalDate
	            
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            if (!rs.next()) {
	                throw new SQLException();
	            }
	            reserva.setId(rs.getInt(1));

	            /* Finalizando transação */
	            conn.commit();
	        } catch (SQLException e) {
	            conn.rollback();
	            throw e;
	        }

	    } catch (SQLException e) {
	        throw new SQLException(e);
	    }

	    return reserva;
	}
	
	public boolean reservaLimite(LocalDate data) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM reserva WHERE data_reserva = ?";
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setDate(1, Date.valueOf(data));
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count >= 3; 
	        }
	    }
	    return false;
	}
	
	public boolean reservaCpfLimite(LocalDate data, String cpf) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM reserva WHERE data_reserva = ? AND cpf = ?";
	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setDate(1, Date.valueOf(data));
	        stmt.setString(2, cpf);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; // Retorna true se já houver uma reserva com o CPF para a data especificada
	        }
	    }
	    return false; // Retorna false se não houver reservas para a data e CPF especificados
	}
}
