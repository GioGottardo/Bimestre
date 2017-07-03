package br.univel.url;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
	
	static List<Produto> buscarprodutos(String nome){
		List<Produto> list = new ArrayList<>();
		
		BancoReport banco = BancoReport.getInstance();
		
		try {
			PreparedStatement ps = banco.getconexao().prepareStatement("SELECT * FROM PRODUTO WHERE DESCRICAO LIKE ?");
			ps.setString(1, "%" + nome.toUpperCase() + "%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Produto produto = new Produto();
				produto.setId(rs.getLong(1));
				produto.setDescricao(rs.getString(2));
				produto.setValorReal(rs.getBigDecimal(3));
				list.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	static void insertprodutos(List<Produto> lista){
		BancoReport banco = BancoReport.getInstance();
		
		try {
			PreparedStatement delecao = banco.getconexao().prepareStatement("delete from PRODUTO");
			delecao.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		for (Produto produto : lista) {
			try {
				PreparedStatement ps = banco.getconexao().prepareStatement("INSERT INTO PRODUTO VALUES (?,?,?)");
				ps.setLong(1, produto.getId());
				ps.setString(2, produto.getDescricao());
				ps.setBigDecimal(3, produto.getValorReal());
				ps.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static Produto Busca1(Long id){
		BancoReport banco = BancoReport.getInstance();
		
		try {
			PreparedStatement ps = banco.getconexao().prepareStatement("SELECT * FROM PRODUTO WHERE ID = (?)");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Produto pt = new Produto();
			pt.setId(rs.getLong(1));
			pt.setDescricao(rs.getString(2));
			pt.setValorReal(rs.getBigDecimal(3));
			return pt;
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
