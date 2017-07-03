package br.univel.url;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JanelaBusca extends JFrame {

	private JPanel contentPane;
	private JTextField txfNome;
	private JTable table;
	private JTextField textField;
	private ProdutoModel modelo = new ProdutoModel();
	private List<Produto> produtos;
	private List<OrcamentoProduto> orcamentoprod;
	private List<Long> quantidades;
	/**
	 * Launch the application.

	/**
	 * Create the frame.
	 */
	public JanelaBusca() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
				JLabel lblNome = new JLabel("Nome");
				GridBagConstraints gbc_lblNome = new GridBagConstraints();
				gbc_lblNome.anchor = GridBagConstraints.EAST;
				gbc_lblNome.insets = new Insets(0, 0, 5, 5);
				gbc_lblNome.gridx = 0;
				gbc_lblNome.gridy = 0;
				contentPane.add(lblNome, gbc_lblNome);
		
				txfNome = new JTextField();
				GridBagConstraints gbc_txfNome = new GridBagConstraints();
				gbc_txfNome.insets = new Insets(0, 0, 5, 5);
				gbc_txfNome.fill = GridBagConstraints.HORIZONTAL;
				gbc_txfNome.gridx = 1;
				gbc_txfNome.gridy = 0;
				contentPane.add(txfNome, gbc_txfNome);
				txfNome.setColumns(10);
				
				JButton btnNewButton = new JButton("Buscar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buscarprodutos();
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton.gridx = 2;
				gbc_btnNewButton.gridy = 0;
				contentPane.add(btnNewButton, gbc_btnNewButton);
				
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 3;
				gbc_scrollPane.gridwidth = 3;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 1;
				contentPane.add(scrollPane, gbc_scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				
				
				JPanel panel = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.LEADING);
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.gridwidth = 3;
				gbc_panel.insets = new Insets(0, 0, 0, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 4;
				contentPane.add(panel, gbc_panel);
				
				JLabel lblQuantidade = new JLabel("Quantidade");
				panel.add(lblQuantidade);
				
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(10);
				
				JButton btnConfirmar = new JButton("Confirmar");
				btnConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmarproduto();
					}
				});
				panel.add(btnConfirmar);
	}

	protected void confirmarproduto() {
		Produto selecionado = modelo.getPetAt(table.getSelectedRow());
		if(selecionado != null){
			this.produtos.add(selecionado);
			OrcamentoProduto oc = new OrcamentoProduto();
			oc.setIdproduto(selecionado.getId());
			
			try {
				oc.setQuantidade(Long.parseLong(textField.getText()));
				this.quantidades.add(oc.getQuantidade());
			} catch(NumberFormatException e) {
				oc.setQuantidade(1L);
			}
			
			
			this.orcamentoprod.add(oc);
			this.dispose();
		}
	}
	
	public void setProdutos(List<Produto> produtos){
		this.produtos = produtos;
	}
	
	public void setquantidades(List<Long> quantidades){
		this.quantidades = quantidades;
	}
	
	public void setlistaorcamento(List<OrcamentoProduto> orcamentoprod){
		this.orcamentoprod = orcamentoprod;
	}

	protected void buscarprodutos() {
		List<Produto> lista = ProdutoDao.buscarprodutos(txfNome.getText().trim());
		if (lista.size() != 0) {
			modelo.preencherResultado(lista);
			table.setModel(modelo);
			modelo.fireTableDataChanged();
		}
		
	}



}
