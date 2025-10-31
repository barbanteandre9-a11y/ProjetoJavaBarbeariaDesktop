package Telas;

import ChamadasAPI.AgendamentoApi;
import Telas.ElementosGeral.ElementosTela;
import entities.Appointment;
import enums.AppointmentStatus;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class telaAtualizarAgendamento extends JFrame {

    private JPanel painelPrincipal;
    private JButton botaoConfirmarAgendamento;
    private JButton botaoConcluirAgendamento;
    
    private final Agendamento telaPrincipal;
    private final Appointment agendamentoAtual;
    private final AgendamentoApi apiService = new AgendamentoApi();

    public telaAtualizarAgendamento(Agendamento telaPrincipal, Appointment agendamento) {
        super("Editar Agendamento - Cliente: " + agendamento.getClientName());
        this.telaPrincipal = telaPrincipal;
        this.agendamentoAtual = agendamento;
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        inicializarComponentes();
        
        conectarAcoesBotoes();
    }

    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

        Runnable acaoVoltarParaAgendamento = () -> {
             this.dispose();
        };

        JPanel painelHeader = elemento.criarHeaderSimples(
            "Editar Agendamento", 
            acaoVoltarParaAgendamento
        );

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(Color.WHITE);
        painelFormulario.setBorder(new EmptyBorder(50, 20, 50, 20)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        botaoConfirmarAgendamento = new JButton("Confirmar Agendamento");
        botaoConcluirAgendamento = new JButton("Concluir Agendamento");
        
        gbc.weightx = 1.0; 
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.gridx = 0; 
        painelFormulario.add(botaoConfirmarAgendamento, gbc);

        gbc.gridx = 1; 
        gbc.insets = new Insets(0, 10, 0, 0); 
        painelFormulario.add(botaoConcluirAgendamento, gbc);

        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.add(painelHeader, BorderLayout.NORTH);
        painelPrincipal.add(painelFormulario, BorderLayout.CENTER);

        add(painelPrincipal);
        pack();
    }
    
    private void conectarAcoesBotoes() {
        
        botaoConfirmarAgendamento.addActionListener(e -> {
            executarChamadaAPI(APIMethod.PATCH, "confirmado", AppointmentStatus.CONFIRMADO);
        });

        botaoConcluirAgendamento.addActionListener(e -> {
            executarChamadaAPI(APIMethod.PUT, "concluído", AppointmentStatus.CONCLUÍDO);
        });
    }

    private enum APIMethod { PATCH, PUT }

    private void executarChamadaAPI(APIMethod method, String acao, AppointmentStatus novoStatus) {
        
        if (agendamentoAtual == null) return;
        
        int resposta = JOptionPane.showConfirmDialog(this, 
            "Deseja realmente marcar o agendamento de " + agendamentoAtual.getClientName() + " como " + acao + "?", 
            "Confirmação de Status", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            
            final long idParaAtualizar = agendamentoAtual.getId();

            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    
                    if (method == APIMethod.PATCH) {
                        return apiService.confirmarAgendamentoPatch(idParaAtualizar);
                    } 
                    else if (method == APIMethod.PUT) {
                        return apiService.concluirAtendimentoPut(idParaAtualizar);
                    }
                    
                    return false;
                }

                @Override
                    protected void done() {
                try {
                    boolean sucesso = get();

                    // sucesso
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Agendamento " + acao + " com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); 
                        telaPrincipal.recarregarTabela();
                    } else { 
                        // Este bloco é para quando doInBackground retorna 'false' (erro HTTP sem exceção)
                        JOptionPane.showMessageDialog(null, "Falha ao " + acao + " o agendamento. Verifique o console.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (java.util.concurrent.ExecutionException e) {
                    // imprimir log do erro no output console
                    e.printStackTrace(); 

                    Throwable causa = e.getCause(); // Pega a exceção real que contém a mensagem da API
                    String mensagemErro = "Erro inesperado do sistema.";

                    if (causa != null) {
                        mensagemErro = causa.getMessage();

                        // tratamento para ver se o agendamento já está confirmado para assim conseguir concluir ele
                        if (mensagemErro.toLowerCase().contains("não foi confirmado") || 
                            mensagemErro.toLowerCase().contains("não está confirmado")) {

                            // exibe mensagem para o usuário caso ele não tenha confirmado ainda
                            JOptionPane.showMessageDialog(
                                telaPrincipal, 
                                "Não é possível concluir a tarefa. O agendamento deve ser CONFIRMADO primeiro.", 
                                "Ação Inválida", 
                                JOptionPane.WARNING_MESSAGE
                            );
                            return;
                        }
                    }

                    // trata os outros erros
                    JOptionPane.showMessageDialog(
                        telaPrincipal, 
                        "Erro de API:\n" + mensagemErro, 
                        "Falha na Operação", 
                        JOptionPane.ERROR_MESSAGE
                    );

                } catch (Exception e) {
                    e.printStackTrace(); 
                    JOptionPane.showMessageDialog(null, "Erro inesperado do sistema: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
            }.execute();
        }
    }
}