package br.univel.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univel.url.BancoReport;

public class ClienteDao {
	
	private static final String SQL_BUSCA_TODOS = "SELECT * FROM CONTATO";

	public List<Cliente> getTodos() {
		
		Connection con = BancoReport
				.getInstance()
				.getconexao();
		
		List<Cliente> lista = new ArrayList<>();
		try (PreparedStatement ps = con
					.prepareStatement(SQL_BUSCA_TODOS);
				ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getLong(1));
				c.setNome(rs.getString(2));
				lista.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void insere(Cliente c) {
		
	}
	
	public void atualiza(int id, Cliente c) {
		
	}
	
	public void exclui(int id) {
		
	}
	
}
