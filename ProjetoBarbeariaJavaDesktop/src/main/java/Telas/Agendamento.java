// Local: Telas/Agendamento.java
package Telas;

import ChamadasAPI.AgendamentoApi;
import Telas.ElementosGeral.ElementosTela;
import Telas.ElementosGeral.PainelHeaderResponsivo;
import Telas.ElementosTelaAgendamento.BotaoEditor;
import Telas.ElementosTelaAgendamento.BotaoRenderer;
import entities.Appointment; 
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.RowFilter;

public class Agendamento extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JPanel painelDeAcoes;
    private JTable tabelaAgendamentos;
    private JTextField campoPesquisa;
    private TableRowSorter<TableModel> sorter;
    private JLabel labelAviso;

    // Variáveis de instância para a API e o modelo
    private final AgendamentoApi apiService = new AgendamentoApi();
    private DefaultTableModel modelo;

    public Agendamento() {
        super("Agendamento - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        inicializarComponentes();
        
        // carrega as informações pegas da API
        carregarAgendamentosNaTabela();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

        Runnable acaoVoltarParaMenu = () -> {
             new MenuPrincipalBarbearia().setVisible(true); 
             this.dispose();
        };

        // 2. Header
        PainelHeaderResponsivo painelHeader = new PainelHeaderResponsivo(
            "/images/e94be8fd-3199-4ff7-955b-8fe7cb3de77c.jpg",
            acaoVoltarParaMenu
        );

        // 3. Painel de ações principal (usando BorderLayout)
        painelDeAcoes = new JPanel(new BorderLayout());
        painelDeAcoes.setBackground(Color.WHITE);
        painelDeAcoes.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Subpainel esquerdo (pesquisa)
        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painelEsquerda.setBackground(Color.WHITE);

        campoPesquisa = new JTextField(25);

        JButton botaoPesquisar = new JButton("🔍");
        botaoPesquisar.setFocusPainted(false);
        botaoPesquisar.setBackground(new Color(0, 123, 255));
        botaoPesquisar.setForeground(Color.WHITE);
        botaoPesquisar.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        botaoPesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.setBackground(new Color(220, 53, 69));
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        botaoLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        painelEsquerda.add(campoPesquisa);
        painelEsquerda.add(botaoPesquisar);
        painelEsquerda.add(botaoLimpar);

        // Subpainel direito (botão de novo agendamento)
        JPanel painelDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        painelDireita.setBackground(Color.WHITE);

        Runnable acaoAdicionar = () -> JOptionPane.showMessageDialog(this, "Abrir tela de novo agendamento!");
        JPanel botaoAdicionar = elemento.criarBotaoDeAcao("Novo Agendamento", "➕", acaoAdicionar);

        painelDireita.add(botaoAdicionar);

        painelDeAcoes.add(painelEsquerda, BorderLayout.WEST);
        painelDeAcoes.add(painelDireita, BorderLayout.EAST);

        // 4. Tabela
        String[] colunas = {"Data", "Hora", "Cliente", "Serviço", "Status", "Preço", "Ações"};
        
        modelo = new DefaultTableModel(new Object[0][colunas.length], colunas) {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return column == 6; // Coluna "Ações" (índice 6)
             }
         };
         
        tabelaAgendamentos = new JTable(modelo);
        tabelaAgendamentos.setFillsViewportHeight(true);
        tabelaAgendamentos.setRowHeight(32);
        tabelaAgendamentos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaAgendamentos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabelaAgendamentos.getTableHeader().setBackground(new Color(245, 245, 245));
        tabelaAgendamentos.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        tabelaAgendamentos.setGridColor(new Color(230, 230, 230));

        sorter = new TableRowSorter<>(modelo);
        tabelaAgendamentos.setRowSorter(sorter);

        // Botões de editar na tabela
        tabelaAgendamentos.getColumn("Ações").setCellRenderer(new BotaoRenderer());
        tabelaAgendamentos.getColumn("Ações").setCellEditor(new BotaoEditor(this));

        JScrollPane scroll = new JScrollPane(tabelaAgendamentos);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // 5. Label de aviso quando não há resultados
        labelAviso = new JLabel("Carregando agendamentos...", SwingConstants.CENTER);
        labelAviso.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        labelAviso.setForeground(Color.GRAY);

        // Ações dos botões
        botaoPesquisar.addActionListener(e -> aplicarFiltro());
        botaoLimpar.addActionListener(e -> limparPesquisa());

        // Painel de conteúdo
        painelDeConteudo = new JPanel(new BorderLayout());
        painelDeConteudo.setBackground(Color.WHITE);
        painelDeConteudo.add(painelDeAcoes, BorderLayout.NORTH);
        painelDeConteudo.add(scroll, BorderLayout.CENTER);
        painelDeConteudo.add(labelAviso, BorderLayout.SOUTH);

        // Painel principal
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.add(painelHeader, BorderLayout.NORTH);
        painelPrincipal.add(painelDeConteudo, BorderLayout.CENTER);

        add(painelPrincipal);
    }
    
    private void carregarAgendamentosNaTabela() {
        labelAviso.setText("Carregando agendamentos...");
        
        new SwingWorker<List<Appointment>, Void>() {
            
            @Override
            protected List<Appointment> doInBackground() throws Exception {
                return apiService.buscarTodosAgendamentos();
            }

            @Override
            protected void done() {
                try {
                    List<Appointment> agendamentos = get();
                    
                    // limpa dados antigos
                    modelo.setRowCount(0);

                    if (agendamentos.isEmpty()) {
                        labelAviso.setText("Não foi possível carregar agendamentos ou a lista está vazia.");
                        return;
                    }
                    
                    // Popula a tabela
                    for (Appointment ap : agendamentos) {
                        modelo.addRow(new Object[]{
                            ap.getFormattedDate(),
                            ap.getFormattedTime(),
                            ap.getClientName(),
                            "ID " + ap.getServiceId(), 
                            ap.getStatus() != null ? ap.getStatus().toString() : "N/A", 
                            ap.getFormattedPrice(),
                            null 
                        });
                    }
                    labelAviso.setText("");
                    
                } catch (Exception e) {
                    // Trata exceções da thread de fundo (falha no get())
                    JOptionPane.showMessageDialog(Agendamento.this, 
                        "Falha ao carregar dados. Verifique a API C# e a conexão.", 
                        "Erro de Conexão/Processamento", 
                        JOptionPane.ERROR_MESSAGE);
                    labelAviso.setText("Falha ao carregar dados. Verifique o console para detalhes.");
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    private void aplicarFiltro() {
        String termo = campoPesquisa.getText().trim();
        if (termo.isEmpty()) {
            sorter.setRowFilter(null);
            labelAviso.setText("");
            return;
        }

        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + termo));

        if (tabelaAgendamentos.getRowCount() == 0) {
            labelAviso.setText("Nenhum agendamento encontrado para: \"" + termo + "\"");
        } else {
            labelAviso.setText("");
        }
    }

    private void limparPesquisa() {
        campoPesquisa.setText("");
        sorter.setRowFilter(null);
        labelAviso.setText("");
        carregarAgendamentosNaTabela(); 
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Agendamento().setVisible(true));
    }
}