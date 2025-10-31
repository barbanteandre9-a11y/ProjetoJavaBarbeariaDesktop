package Telas;

import ChamadasAPI.DespesasService;
import Telas.ElementosGeral.ElementosTela;
import Telas.ElementosGeral.PainelHeaderResponsivo;
import entities.Expense;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class Despesas extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JPanel painelDeAcoes;
    private JTable tabelaDespesas;
    private JTextField campoPesquisa;
    private TableRowSorter<DefaultTableModel> sorter;
    private JLabel labelAviso;
    private DefaultTableModel modelo;
    private DespesasService despesasService;
   

    public Despesas() {
        super("Despesas - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        inicializarComponentes();
        despesasService = new DespesasService();
       
        carregarDadosNaTabela();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

        PainelHeaderResponsivo painelHeader = new PainelHeaderResponsivo(
            "/images/BannerDespesas.png",
            () -> {
                this.dispose();
            }
        );

        painelDeAcoes = new JPanel(new BorderLayout());
        painelDeAcoes.setBackground(Color.WHITE);
        painelDeAcoes.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painelEsquerda.setBackground(Color.WHITE);

        campoPesquisa = new JTextField(25);
       
        JButton botaoPesquisar = new JButton("🔍");
       
        botaoPesquisar.addActionListener(e -> {
            despesasService.buscarDespesas();
        });
       
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
   

    private void carregarDadosNaTabela() {
        labelAviso.setText("Carregando despesas...");
        labelAviso.setVisible(true);

        // SwingWorker para executar a chamada de API fora da Event Dispatch Thread (EDT)
        new SwingWorker<List<Expense>, Void>() {

            @Override
            protected List<Expense> doInBackground() throws Exception {
                // Chama o serviço passando null para paginação, como solicitado
                return despesasService.buscarDespesas();
            }

            @Override
            protected void done() {
                try {
                   
                    List<Expense> despesas = get();

                    // Limpa os dados antigos da tabela
                    modelo.setRowCount(0);

                    // Verifica se a lista está vazia
                    if (despesas.isEmpty()) {
                        labelAviso.setText("Não foi possível carregar despesas ou a lista está vazia.");
                        return;
                    }

                    // Popula a tabela com os novos dados
                    // Colunas: {"Despesa", "Valor Total", "Data de Vencimento", "Situação"}
                    for (Expense despesa : despesas) {
                       
                        modelo.addRow(new Object[]{
                            despesa.getNotes(),
                            despesa.getAmount(),
                            despesa.getDueDate(),
                            }
                            );
                    }

                    labelAviso.setText("");
                    labelAviso.setVisible(false);

                } catch (Exception e) {
                    // Trata erros que ocorreram durante a execução do doInBackground()
                    JOptionPane.showMessageDialog(Despesas.this,
                            "Falha ao carregar dados. Verifique a API C# e a conexão.",
                            "Erro de Conexão/Processamento",
                            JOptionPane.ERROR_MESSAGE);
                   
                    labelAviso.setText("Falha ao carregar dados. Verifique o console para detalhes.");
                    e.printStackTrace();
                }
            }
        }.execute(); // Inicia o SwingWorker
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Despesas().setVisible(true));
    }
}
