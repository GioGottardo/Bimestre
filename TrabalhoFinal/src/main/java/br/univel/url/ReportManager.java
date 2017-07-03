package br.univel.url;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.univel.cliente.Cliente;
import br.univel.cliente.ClienteDao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {

	private static final String JASPER_CONTATO = "C:\\Users\\G\\JaspersoftWorkspace\\MyReports\\Blank_A4.jasper";
	
	

	public void imprimir() {
		JasperPrint jasperPrintPDF = getPrint();
		Locale locale = Locale.getDefault();
		JasperViewer.viewReport(jasperPrintPDF, false, locale);
	}
	

	public void exportar() {
		JasperPrint jasperPrintPDF = getPrint();
		
		SimpleDateFormat sdf = new SimpleDateFormat("(yyyy-MM-dd)_HH-mm-ss");
		String data = sdf.format(new Date());

		String nomePdf = "relatorio_clientes_" + data + ".pdf";
		
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrintPDF, nomePdf);
			Desktop.getDesktop().open(new File(nomePdf));
		} catch (JRException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private JasperPrint getPrint() {
		Connection con = BancoReport.getInstance().getconexao();
		try {
			return JasperFillManager.fillReport(JASPER_CONTATO, null, con);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void exportarCustom() {
		String strFile = "C:\\Users\\G\\JaspersoftWorkspace\\MyReports\\Blank_A4.jasper";
		
		ClienteDao dao = new ClienteDao();
		List<Cliente> lista = dao.getTodos();
		
		JRDataSource customDs = new CustomClienteReport(lista);

		JasperPrint jp;
		try {
			jp = JasperFillManager.fillReport(strFile, null, customDs);
			
			Locale locale = Locale.getDefault();
			JasperViewer.viewReport(jp, false, locale);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		ReportManager rm = new ReportManager();
		rm.exportarCustom();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
