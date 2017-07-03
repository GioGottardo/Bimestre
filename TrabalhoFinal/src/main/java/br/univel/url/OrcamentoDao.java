package br.univel.url;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrcamentoDao {
	
	public void Orcamentoprod(List<OrcamentoProduto>produtos, Orcamento prodorc){
		BancoReport banco = BancoReport.getInstance();
		Connection con = banco.getconexao();
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO ORCAMENTO (VALORTOTAL) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
			ps.setBigDecimal(1, prodorc.getValortotal());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			Long idOrcamento = rs.getLong(1);
			
			for (OrcamentoProduto op : produtos) {
				PreparedStatement pk = con.prepareStatement
						("INSERT INTO ORCAMENTO_PRODUTO (ID_PRODUTO,ID_ORCAMENTO,QUANTIDADE) VALUES (?,?,?);");
				
				pk.setLong(1, op.getIdproduto());
				pk.setLong(2, idOrcamento);
				pk.setLong(3, op.getQuantidade());
				pk.executeUpdate();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
