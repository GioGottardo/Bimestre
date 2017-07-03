package br.univel.cliente;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel {

	private List<Cliente> lista1;

	void preencherResultado(List<Cliente> resultado) {
		this.lista1 = resultado;
		
		// O comando abaixo solicita atualização da visão.
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		if (lista1 == null)
			return 0;

		return lista1.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Cliente cl = lista1.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return cl.getId();
		case 1:
			return cl.getNome();
		}

		return null;
	}

	public Cliente getPetAt(int idx) {
		if (idx >= this.lista1.size()) {
			return null;
		}
		return this.lista1.get(idx);
	}
}
