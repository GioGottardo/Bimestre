package br.univel.url;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoReport {
	
	private Connection con;
	private static BancoReport self;

	private BancoReport() {
		try {
			Class.forName("org.postgresql.Driver");
			this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "banco01");
			
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							BancoReport.this.con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public Connection getconexao(){
		return con;
	}
	
	public final static synchronized BancoReport getInstance(){
		if(self == null){
			self = new BancoReport();
		}
		return self;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("So 1");
	}

}
