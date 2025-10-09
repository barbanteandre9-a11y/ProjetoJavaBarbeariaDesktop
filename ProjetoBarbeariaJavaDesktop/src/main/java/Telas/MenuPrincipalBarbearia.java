package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MenuPrincipalBarbearia extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDashboard;

    public MenuPrincipalBarbearia() {
        super("Menu Principal - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

         Runnable acaoVoltarParaMenu = () -> {
            new MenuPrincipalBarbearia().setVisible(true); // Abre o menu
            this.dispose(); // Fecha a tela atual de agendamento
        };

        // 2. CRIAÇÃO DO HEADER, AGORA PASSANDO A AÇÃO DE VOLTAR
        PainelHeaderResponsivo painelHeader = new PainelHeaderResponsivo(
            "/images/e94be8fd-3199-4ff7-955b-8fe7cb3de77c.jpg",
            acaoVoltarParaMenu // Passa a ação para o header
        );
        
        painelDashboard = new JPanel();
        painelDashboard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelDashboard.setLayout(new GridLayout(0, 2, 20, 20));
        painelDashboard.setBackground(Color.WHITE); 
        JPanel botaoAgendamento = elemento.criarBotaoFuncionalidadeAzul(
        "Agendamentos", 
        "\uD83D\uDCC6", 
        () -> {
        new Agendamento().setVisible(true);
        // dispose();
        }
    );

        painelDashboard.add(botaoAgendamento);

        painelDashboard.add(elemento.criarBotaoFuncionalidadeVermelho("Estoque", "\uD83D\uDCE6"));
        painelDashboard.add(elemento.criarBotaoFuncionalidadeVermelho("Faturamento", "\uD83D\uDCCA"));
        
        JPanel botaoDespesas = elemento.criarBotaoFuncionalidadeAzul(
        "Despesas", 
        "\uD83D\uDCC6", 
        () -> {
        new Despesas().setVisible(true);
        
        }
    );
        painelDashboard.add(botaoDespesas);

        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);

        painelPrincipal.add(painelHeader, BorderLayout.NORTH); 
        painelPrincipal.add(painelDashboard, BorderLayout.CENTER);

        this.add(painelPrincipal);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipalBarbearia().setVisible(true);
        });
    }
}