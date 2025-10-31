package Telas;

import Telas.ElementosGeral.ElementosTela;
import Telas.ElementosGeral.PainelHeaderResponsivo;
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
        setSize(1024, 1080);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

         Runnable acaoVoltarParaMenu = () -> {
            new MenuPrincipalBarbearia().setVisible(true); // Abre o menu
            this.dispose(); // Fecha a tela atual de agendamento
        };

        // header
        PainelHeaderResponsivo painelHeader = new PainelHeaderResponsivo(
            "/images/teste.jpg",
            acaoVoltarParaMenu
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
         dispose();
        }
    );

        painelDashboard.add(botaoAgendamento);

        
        JPanel botaoEstoque = elemento.criarBotaoFuncionalidadeVermelho("Estoque", "\uD83D\uDCE6", () -> {
            new NewEstoque().setVisible(true);
        });
        
        painelDashboard.add(botaoEstoque);
        
        JPanel botaoRelatorio = elemento.criarBotaoFuncionalidadeVermelho(
                "Faturamento", 
                "\uD83D\uDCCA",
                () -> {
                    new Relatorio().setVisible(true);
                }
        );
        painelDashboard.add(botaoRelatorio);
        
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