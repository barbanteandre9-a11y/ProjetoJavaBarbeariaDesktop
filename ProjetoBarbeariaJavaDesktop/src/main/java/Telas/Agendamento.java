package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities; // Adicionado para consistência

/**
 * @author ht3049931
 */
public class Agendamento extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JPanel painelDeAcoes;
    private JTable tabelaAgendamentos;

    public Agendamento() {
        super("Agendamento - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

        // 1. CRIAÇÃO DO HEADER RESPONSIVO
        // Substituímos o 'rotuloTitulo' pelo 'PainelHeaderResponsivo'
        PainelHeaderResponsivo painelHeader = new PainelHeaderResponsivo(
            "/images/e94be8fd-3199-4ff7-955b-8fe7cb3de77c.jpg"
        );

        // 2. PAINEL DE AÇÕES (código original mantido)
        painelDeAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelDeAcoes.setBackground(Color.WHITE);
        painelDeAcoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // 3. CRIAÇÃO DO BOTÃO DE AÇÃO (código original mantido)
        Runnable acaoAdicionar = () -> JOptionPane.showMessageDialog(this, "Lógica para abrir a tela de novo agendamento!");
        JPanel botaoAdicionar = elemento.criarBotaoDeAcao("Adicionar Agendamento", "➕", acaoAdicionar);
        
        painelDeAcoes.add(botaoAdicionar);

        // 4. TABELA DE EXEMPLO (código original mantido)
        String[] colunas = {"Data", "Hora", "Cliente", "Serviço", "Status"};
        Object[][] dados = {
            {"04/10/2025", "10:00", "João Silva", "Corte", "Confirmado"},
            {"04/10/2025", "11:00", "Carlos Pereira", "Barba", "Confirmado"}
        };
        tabelaAgendamentos = new JTable(dados, colunas);
        
        // 5. PAINEL DE CONTEÚDO (código original mantido)
        painelDeConteudo = new JPanel(new BorderLayout());
        painelDeConteudo.setBackground(Color.WHITE);
        painelDeConteudo.add(painelDeAcoes, BorderLayout.NORTH);
        painelDeConteudo.add(new JScrollPane(tabelaAgendamentos), BorderLayout.CENTER);

        // 6. PAINEL PRINCIPAL (organiza o HEADER e o conteúdo)
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.add(painelHeader, BorderLayout.NORTH); // Adiciona o header no topo
        painelPrincipal.add(painelDeConteudo, BorderLayout.CENTER);

        // 7. ADICIONA O PAINEL PRINCIPAL À JANELA
        this.add(painelPrincipal);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new Agendamento().setVisible(true);
        });
    }
}