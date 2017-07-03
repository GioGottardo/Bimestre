package br.univel.url;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JTextField textField;
	private JTable table;
	private ProdutoModel modelo = new ProdutoModel();
	private ProdutoModelQtd model = new ProdutoModelQtd();
	private List<OrcamentoProduto> orcamentoprod = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar");
		mnArquivo.add(mntmExportar);
		
		JMenuItem mntmImprimir = new JMenuItem("Imprimir");
		mnArquivo.add(mntmImprimir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnCarregarProdutos = new JButton("Carregar Produtos");
		btnCarregarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarprodutos();
			}
		});
		panel.add(btnCarregarProdutos);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Produtos", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Orçamento", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblAdicioneUmProduto = new JLabel("Adicione um produto");
		GridBagConstraints gbc_lblAdicioneUmProduto = new GridBagConstraints();
		gbc_lblAdicioneUmProduto.anchor = GridBagConstraints.WEST;
		gbc_lblAdicioneUmProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdicioneUmProduto.gridx = 0;
		gbc_lblAdicioneUmProduto.gridy = 0;
		panel_1.add(lblAdicioneUmProduto, gbc_lblAdicioneUmProduto);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoproduto();
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.WEST;
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 1;
		gbc_button.gridy = 0;
		panel_1.add(button, gbc_button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		model.preencherResultado(new ArrayList<Produto>(), new ArrayList<Long>());
		table_1.setModel(model);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Clientes", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_4.add(scrollPane_2, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton orcamento = new JButton("Orçamento");
		orcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				geraorcamento();
			}
		});
		
		JLabel lblTotal = new JLabel("Total");
		panel_2.add(lblTotal);
		
		textField = new JTextField();
		textField.setEditable(false);
		panel_2.add(textField);
		textField.setColumns(10);
		panel_2.add(orcamento);
	}

	protected void geraorcamento() {
		Orcamento orcfinal = new Orcamento();
		orcfinal.setValortotal(new BigDecimal(textField.getText()));
		
		OrcamentoDao gr = new OrcamentoDao();
		gr.Orcamentoprod(orcamentoprod, orcfinal);
		JOptionPane.showMessageDialog(null, "Seu Orçamento foi criado com sucesso!");
		model.preencherResultado(new ArrayList<Produto>(), new ArrayList<Long>());
		model.fireTableDataChanged();
		textField.setText(0 + "");
	}

	protected void carregarprodutos() {
		
		String url = "http://www.master10.com.py/lista-txt/dowload";
		LeitorProdutoUrl lpu = new LeitorProdutoUrl();
		try {
			List<Produto> lista = lpu.lerPrudutos(url);
			modelo.preencherResultado(lista);
			table.setModel(modelo);
			
			ProdutoDao.insertprodutos(lista);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void novoproduto() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaBusca frame = new JanelaBusca();
					frame.setProdutos(model.getlista());
					frame.setquantidades(model.getquantidade());
					frame.setlistaorcamento(orcamentoprod);
					
					frame.addWindowListener(new WindowAdapter() {
						
						@Override
						public void windowClosed(WindowEvent e) {
							model.fireTableDataChanged();
							Calculvalortotal();
						}
						
					});
					
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Calculvalortotal() {
		BigDecimal valortotal = new BigDecimal(0);
		
		ProdutoDao fs = new ProdutoDao();
		
		for (OrcamentoProduto op : orcamentoprod) {
			BigDecimal quantidade = new BigDecimal(op.getQuantidade().toString());
			Produto pt = fs.Busca1(op.getIdproduto());
			valortotal = valortotal.add(pt.getValorReal().multiply(quantidade));
		}
		
		textField.setText(valortotal.toString());
	}

}
