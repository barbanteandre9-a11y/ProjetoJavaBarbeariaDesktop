package Telas;

import Telas.ElementosGeral.ElementosTela;
import Telas.ElementosGeral.PainelHeaderResponsivo;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class NewDespesas extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JPanel painelDeAcoes;
    private JTable tabelaDespesas;
    private JTextField campoPesquisa;
    private TableRowSorter<DefaultTableModel> sorter;
    private JLabel labelAviso;
    private DefaultTableModel modelo;

    public NewDespesas() {
        super("Despesas - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

        PainelHeaderResponsivo painelHeader = new PainelHeaderResponsivo(
            "/images/BannerDespesas.png",
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


    JPanel painelBotaoAdicionar = elemento.criarBotaoDeAcao("Cadastrar Despesa", "➕", () -> {});
    painelDireita.add(painelBotaoAdicionar);

    JPanel painelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    painelCenter.setBackground(Color.WHITE);

    JPanel painelBotaoRelatorio = elemento.criarBotaoDeAcao("Gerar Relatório", "📄", () -> {});
    painelCenter.add(painelBotaoRelatorio);

        painelDeAcoes.add(painelEsquerda, BorderLayout.WEST);
        painelDeAcoes.add(painelCenter, BorderLayout.CENTER);
        painelDeAcoes.add(painelDireita, BorderLayout.EAST);

        String[] colunas = {"Despesa", "Valor Total", "Data de Vencimento", "Situação"};

        modelo = new DefaultTableModel(new Object[0][colunas.length], colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        tabelaDespesas = new JTable(modelo);
        tabelaDespesas.setFillsViewportHeight(true);
        tabelaDespesas.setRowHeight(32);
        tabelaDespesas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaDespesas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabelaDespesas.getTableHeader().setBackground(new Color(245, 245, 245));
        tabelaDespesas.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        tabelaDespesas.setGridColor(new Color(230, 230, 230));

        sorter = new TableRowSorter<>(modelo);
        tabelaDespesas.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(tabelaDespesas);
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
        SwingUtilities.invokeLater(() -> new NewDespesas().setVisible(true));
    }
}
