package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;

/**
 * @author ht3049931
 */
public class Agendamento extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo; 
    private JPanel painelDeAcoes;    
    private JLabel rotuloTitulo;
    private JTable tabelaAgendamentos; // NOVO: Espaço para a futura tabela


    public Agendamento() {
        super("Agendamento - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

        rotuloTitulo = elemento.criarRotuloTitulo("Área de Agendamento", "/images/Gemini_Generated_Image_8n3h3u8n3h3u8n3h.png");

        // 2. PAINEL DE AÇÕES
        painelDeAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelDeAcoes.setBackground(Color.WHITE);
        painelDeAcoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espaçamento

        // 3. CRIAÇÃO DO BOTÃO DE AÇÃO
        // Ícone de "mais": ➕ (U+2795)
        Runnable acaoAdicionar = () -> JOptionPane.showMessageDialog(this, "Lógica para abrir a tela de novo agendamento!");
        JPanel botaoAdicionar = elemento.criarBotaoDeAcao("Adicionar Agendamento", "➕", acaoAdicionar);
        
        // Adiciona o botão ao painel de ações
        painelDeAcoes.add(botaoAdicionar);

        // tabela de exemplo
        String[] colunas = {"Data", "Hora", "Cliente", "Serviço", "Status"};
        Object[][] dados = {
            {"03/10/2025", "10:00", "João Silva", "Corte", "Confirmado"},
            {"03/10/2025", "11:00", "Carlos Pereira", "Barba", "Confirmado"}
        };
        tabelaAgendamentos = new JTable(dados, colunas);
        
        // 5. PAINEL DE CONTEÚDO (que organiza a barra de ações e a tabela)
        painelDeConteudo = new JPanel(new BorderLayout());
        painelDeConteudo.setBackground(Color.WHITE);
        painelDeConteudo.add(painelDeAcoes, BorderLayout.NORTH); // Botão fica em cima
        painelDeConteudo.add(new JScrollPane(tabelaAgendamentos), BorderLayout.CENTER); // Tabela fica no centro

        // 6. PAINEL PRINCIPAL (que organiza o título e o conteúdo)
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.add(rotuloTitulo, BorderLayout.NORTH);
        painelPrincipal.add(painelDeConteudo, BorderLayout.CENTER);

        // 7. ADICIONA O PAINEL PRINCIPAL À JANELA
        this.add(painelPrincipal);
    }

    // ... (seu método main continua o mesmo)
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Agendamento().setVisible(true);
        });
    }
}
