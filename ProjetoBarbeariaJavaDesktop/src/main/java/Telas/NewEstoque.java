package Telas;

import Telas.ElementosGeral.ElementosTela;
import Telas.ElementosGeral.PainelHeaderResponsivo;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class NewEstoque extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JPanel painelDeAcoes;
    private JTable tabelaEstoque;
    private JTextField campoPesquisa;
    private TableRowSorter<DefaultTableModel> sorter;
    private JLabel labelAviso;
    private DefaultTableModel modelo;

    public NewEstoque() {
        super("Estoque - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

        PainelHeaderResponsivo painelHeader = new PainelHeaderResponsivo(
            "/images/e94be8fd-3199-4ff7-955b-8fe7cb3de77c.jpg",
            () -> {}
        );

        painelDeAcoes = new JPanel(new BorderLayout());
        painelDeAcoes.setBackground(Color.WHITE);
        painelDeAcoes.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painelEsquerda.setBackground(Color.WHITE);

        campoPesquisa = new JTextField(25);
        JButton botaoPesquisar = new JButton("🔍");
        JButton botaoLimpar = new JButton("Limpar");

        painelEsquerda.add(campoPesquisa);
        painelEsquerda.add(botaoPesquisar);
        painelEsquerda.add(botaoLimpar);

        JPanel painelDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        painelDireita.setBackground(Color.WHITE);

        JPanel botaoAdicionar = elemento.criarBotaoDeAcao("Cadastrar Item", "➕", () -> {});
        painelDireita.add(botaoAdicionar);

        painelDeAcoes.add(painelEsquerda, BorderLayout.WEST);
        painelDeAcoes.add(painelDireita, BorderLayout.EAST);

        String[] colunas = {"Código", "Produto", "Quantidade", "Preço", "Editar"};

        modelo = new DefaultTableModel(new Object[0][colunas.length], colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        tabelaEstoque = new JTable(modelo);
        tabelaEstoque.setFillsViewportHeight(true);
        tabelaEstoque.setRowHeight(32);
        tabelaEstoque.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaEstoque.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabelaEstoque.getTableHeader().setBackground(new Color(245, 245, 245));
        tabelaEstoque.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        tabelaEstoque.setGridColor(new Color(230, 230, 230));

        sorter = new TableRowSorter<>(modelo);
        tabelaEstoque.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabelaEstoque);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        labelAviso = new JLabel("Carregando dados...", SwingConstants.CENTER);
        labelAviso.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        labelAviso.setForeground(Color.GRAY);

        painelDeConteudo = new JPanel(new BorderLayout());
        painelDeConteudo.setBackground(Color.WHITE);
        painelDeConteudo.add(painelDeAcoes, BorderLayout.NORTH);
        painelDeConteudo.add(scroll, BorderLayout.CENTER);
        painelDeConteudo.add(labelAviso, BorderLayout.SOUTH);

        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.add(painelHeader, BorderLayout.NORTH);
        painelPrincipal.add(painelDeConteudo, BorderLayout.CENTER);

        add(painelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewEstoque().setVisible(true));
    }
}
