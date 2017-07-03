package br.univel.url;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

	
	public class ProdutoModelQtd extends AbstractTableModel {
	
	
	private List<Produto> lista;
	private List<Long> quantidades;

	void preencherResultado(List<Produto> resultado,List<Long> quantidades) {
		this.lista = resultado;
		this.quantidades = quantidades;
		
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return ("Id");
		case 1:
			return ("Descricao");
		case 2:
			return ("Valor");
		case 3:
			return ("Quantidade");
		}
		return null;
		
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		if (lista == null)
			return 0;

		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Produto p = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getDescricao();
		case 2:
			return p.getValorReal();
		case 3:
			return this.quantidades.get(rowIndex);
		}

		return null;
	}

	public Produto getPetAt(int idx) {
		if (idx >= this.lista.size()) {
			return null;
		}
		return this.lista.get(idx);
	}
	
	public List<Produto> getlista(){
		return lista == null ? new ArrayList<Produto>() : lista;
	}
	
	public List<Long> getquantidade(){
		return lista == null ? new ArrayList<Long>() : quantidades;
	}
	
}





