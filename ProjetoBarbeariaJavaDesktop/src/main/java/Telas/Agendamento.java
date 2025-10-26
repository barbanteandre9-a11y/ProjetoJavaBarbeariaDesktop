package Telas;

import ChamadasAPI.AgendamentoApi;
import Telas.ElementosGeral.ElementosTela;
import Telas.ElementosGeral.PainelHeaderResponsivo;
import Telas.ElementosTelaAgendamento.BotaoEditor;
import Telas.ElementosTelaAgendamento.BotaoRenderer;
import entities.Appointment;
import java.awt.*;
import java.util.List;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.RowFilter;

public class Agendamento extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JPanel painelDeAcoes;
    public JTable tabelaAgendamentos;
    private JTextField campoPesquisa;
    private TableRowSorter<TableModel> sorter;
    private JLabel labelAviso;

    private final AgendamentoApi apiService = new AgendamentoApi();
    private DefaultTableModel modelo;

    private List<Appointment> listaAgendamentos = Collections.emptyList();

    public Agendamento() {
        super("Agendamento - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 1080);
        setLocationRelativeTo(null);
        inicializarComponentes();
        
        carregarAgendamentosNaTabela();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();
        
        Runnable acaoVoltarParaMenu = () -> {
            new MenuPrincipalBarbearia().setVisible(true); 
             this.dispose();
        };

        PainelHeaderResponsivo painelHeader = new PainelHeaderResponsivo(
            "/images/teste.jpg",
            acaoVoltarParaMenu
        );

        painelDeAcoes = new JPanel(new BorderLayout());
        painelDeAcoes.setBackground(Color.WHITE);
        painelDeAcoes.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Pesquisa
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
        // FIX: Reaplicando configurações de estilo
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.setBackground(new Color(220, 53, 69));
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        botaoLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        painelEsquerda.add(campoPesquisa);
        painelEsquerda.add(botaoPesquisar);
        painelEsquerda.add(botaoLimpar);

        // Botão Novo Agendamento
        JPanel painelDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        painelDireita.setBackground(Color.WHITE);

        Runnable acaoAdicionar = () -> {
            new TelaCriarAgendamento().setVisible(true); 
        };
        JPanel botaoAdicionar = elemento.criarBotaoDeAcao("Novo Agendamento", "➕", acaoAdicionar);

        painelDireita.add(botaoAdicionar);

        painelDeAcoes.add(painelEsquerda, BorderLayout.WEST);
        painelDeAcoes.add(painelDireita, BorderLayout.EAST);

        String[] colunas = {"Data", "Hora", "Cliente", "Telefone", "Serviço", "Método de Pagamentos", "Status", "Preço", "Ações"};
        
        modelo = new DefaultTableModel(new Object[0][colunas.length], colunas) {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return column == 8; // Coluna "Ações" (índice 8)
             }
         };
         
        tabelaAgendamentos = new JTable(modelo);
        tabelaAgendamentos.setFont(new Font("Dialog", Font.PLAIN, 14)); 
        tabelaAgendamentos.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 14));
        tabelaAgendamentos.getTableHeader().setBackground(new Color(245, 245, 245));
        tabelaAgendamentos.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        tabelaAgendamentos.setGridColor(new Color(230, 230, 230));

        sorter = new TableRowSorter<>(modelo);
        tabelaAgendamentos.setRowSorter(sorter);

        tabelaAgendamentos.getColumn("Ações").setCellRenderer(new BotaoRenderer());
        tabelaAgendamentos.getColumn("Ações").setCellEditor(new BotaoEditor(this));

        JScrollPane scroll = new JScrollPane(tabelaAgendamentos);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        labelAviso = new JLabel("Carregando agendamentos...", SwingConstants.CENTER);
        labelAviso.setFont(new Font("Dialog", Font.ITALIC, 14));
        labelAviso.setForeground(Color.GRAY);

        botaoPesquisar.addActionListener(e -> aplicarFiltro());
        botaoLimpar.addActionListener(e -> limparPesquisa());

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
    
 
    public void recarregarTabela() {
        modelo.setRowCount(0);
        labelAviso.setText("Recarregando agendamentos...");
        carregarAgendamentosNaTabela();
    }
    
    public Appointment getAppointmentPorLinhaVisivel(int linhaVisivel) {
        if (linhaVisivel < 0 || sorter == null || modelo.getRowCount() == 0) {
             return null;
        }
        
        // Converte o índice da linha visível para o índice da lista interna (modelo)
        int linhaModelo = sorter.convertRowIndexToModel(linhaVisivel);
        
        if (linhaModelo >= 0 && linhaModelo < listaAgendamentos.size()) {
            return listaAgendamentos.get(linhaModelo);
        }
        return null;
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
                    
                    listaAgendamentos = agendamentos; 
                    
                    modelo.setRowCount(0);
                    
                    if (listaAgendamentos.isEmpty()) {
                        labelAviso.setText("Não foi possível carregar agendamentos ou a lista está vazia.");
                        return;
                    }
                    
                    for (Appointment ap : listaAgendamentos) {
                        String statusDescricao = ap.getStatus() != null ? ap.getStatus().getDescription() : "N/A";
                        String pagamentoDescricao = ap.getPaymentType() != null ? ap.getPaymentType().getDescription() : "N/A";
                        
                        modelo.addRow(new Object[]{
                            ap.getFormattedDate(),
                            ap.getFormattedTime(),
                            ap.getClientName(),
                            ap.getClientPhone(),
                            "ID " + ap.getServiceId(), 
                            pagamentoDescricao,
                            statusDescricao,
                            ap.getFormattedPrice(),
                            null
                        });
                    }
                    labelAviso.setText("");
                    
                } catch (Exception e) {
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

    // pesquisa
    private void aplicarFiltro() {
        String termo = campoPesquisa.getText().trim();
        
        if (termo.isEmpty()) {
            sorter.setRowFilter(null);
            labelAviso.setText("");
            return;
        }

        try {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + termo));
        } catch (java.util.regex.PatternSyntaxException e) {
            sorter.setRowFilter(null); 
        }

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
        recarregarTabela(); 
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Agendamento().setVisible(true));
    }
}