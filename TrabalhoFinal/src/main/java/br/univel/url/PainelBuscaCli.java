package br.univel.url;

import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import br.univel.cliente.Cliente;
import br.univel.cliente.ClienteTableModel;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PainelBuscaCli extends JPanel {
	private JTextField textField;
	private JTable table;
	private Consumer<Cliente> consumerOnOk;
	private Runnable runnableOnCancel;

	/**
	 * Create the panel.
	 */
	public PainelBuscaCli() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 0);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		add(lblNome, gbc_lblNome);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		
		// O WindowBuilder vai ignorar o código que está
		// dentro desses comentários:
		
		// $hide>>$
		configuraTela();
		// $hide<<$
	}

	private void configuraTela() {
		ClienteTableModel model = new ClienteTableModel();
		table.setModel(model);

		
		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					busca(textField.getText().trim());
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					// Seleciona o primeiro item na tabela.
					table.getSelectionModel().setSelectionInterval(0, 0);
					// Transfere o foco pra frente.
					textField.transferFocus();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (PainelBuscaCli.this.runnableOnCancel != null) {
						PainelBuscaCli.this.runnableOnCancel.run();
					}
				}
			}
		});
		
		table.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					table.transferFocusBackward();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					// Pra cancelar a ação padrão que já existe
					// na JTable com a tecla Enter.
					e.consume();
					
					int idx = table.getSelectedRow();
					if (idx != -1) {
						Cliente cli = ((ClienteTableModel)table.getModel()).getPetAt(idx);
						if (cli == null) {
							return;
						}
						PainelBuscaCli.this.consumerOnOk.accept(cli);
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (PainelBuscaCli.this.runnableOnCancel != null) {
						PainelBuscaCli.this.runnableOnCancel.run();
					}
				}
			}
		});
	}

	protected void busca(String palavra) {

		List<Cliente> lista = buscaNoBanco(palavra);

		((ClienteTableModel) table.getModel()).preencherResultado(lista);

	}

	/**
	 * Simula uma busca no banco, como se pedisse para um DAO ou fizesse o
	 * Statement diretamente no banco.
	 * 
	 * @param palavra
	 * 
	 * @return
	 */
	private List<Cliente> buscaNoBanco(String palavra) {

		List<Cliente> lista = new ArrayList<Cliente>();

		for (int i = 0; i < DADOS_FICTICIOS.length; i++) {
			if (DADOS_FICTICIOS[i][1].toLowerCase().startsWith(palavra)) {
				Cli pet = new cli();

				pet.setId(Integer.parseInt(DADOS_FICTICIOS[i][0]));
				pet.setNome(DADOS_FICTICIOS[i][1]);
				pet.setRaca(DADOS_FICTICIOS[i][2]);

				lista.add(pet);
			}
		}

		return lista;
	}

	public void setOnOk(Consumer<Cliente> c) {
		this.consumerOnOk = c;
	}

	public void setOnCancel(Runnable r) {
		this.runnableOnCancel = r;
	}

	@Override
	public void setVisible(boolean arg0) {
		
		super.setVisible(arg0);
		
		// Depois que ficar visivel e terminar tudo
		// o que foi agendado para a EDT, solicita o
		// foco do teclado para o textField.
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				textField.requestFocusInWindow();
			}
		});
	}

	private static final String[][] DADOS_FICTICIOS = new String[][] {

	
}
