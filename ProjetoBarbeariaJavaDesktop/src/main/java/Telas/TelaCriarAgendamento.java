package Telas;

import ChamadasAPI.AgendamentoApi;
import Telas.ElementosGeral.ElementosTela;
import entities.Appointment;
import enums.PaymentType;
import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import requests.RequestScheduleServiceJson;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class TelaCriarAgendamento extends JFrame {
    
    private JPanel painelPrincipal;

    private JTextField campoNomeCliente;
    private JFormattedTextField campoTelefone;
    private JComboBox<String> comboServico;
    private JComboBox<String> comboProfissional;
    private JComboBox<String> comboMetodoPagamento;
    private JTextField campoData;
    private JTextField campoHora;
    private JButton botaoSalvar;
    private JButton botaoCancelar;

    
    public TelaCriarAgendamento() {
        super("Criar Agendamentos - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 530); 
        setLocationRelativeTo(null);
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        ElementosTela elemento = new ElementosTela();

        Runnable acaoVoltarParaAgendamento = () -> {
            // new Agendamento().setVisible(true);
            this.dispose();
        };

        JPanel painelHeader = elemento.criarHeaderSimples(
            "Novo Agendamento",
            acaoVoltarParaAgendamento
        );

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(Color.WHITE);
        painelFormulario.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int y = 0;

        // Nome do Cliente
        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Nome do Cliente:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0;
        campoNomeCliente = new JTextField(20);
        painelFormulario.add(campoNomeCliente, gbc);

        // Telefone
        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0;
        try {
            MaskFormatter mascaraTelefone = new MaskFormatter("## #####-####");
            campoTelefone = new JFormattedTextField(mascaraTelefone);
            ((JFormattedTextField) campoTelefone).setColumns(13);
        } catch (Exception e) {
            campoTelefone = new JFormattedTextField();
        }
        painelFormulario.add(campoTelefone, gbc);
        
        // Data e Hora
        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Data e Hora:"), gbc);
            JPanel painelDataHora = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            painelDataHora.setBackground(Color.WHITE);
            
            try {
                MaskFormatter mascaraData = new MaskFormatter("##/##/####");
                campoData = new JFormattedTextField(mascaraData);
                ((JFormattedTextField) campoData).setColumns(6);
            } catch (Exception e) {
                campoData = new JFormattedTextField();
            }
            try {
                MaskFormatter mascaraHora = new MaskFormatter("##:##");
                campoHora = new JFormattedTextField(mascaraHora);
                ((JFormattedTextField) campoHora).setColumns(3);
            } catch (Exception e) {
                campoHora = new JFormattedTextField();
            }
            painelDataHora.add(campoData);
            painelDataHora.add(new JLabel(" às "));
            painelDataHora.add(campoHora);
        gbc.gridx = 1; gbc.gridy = y++; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0;
        painelFormulario.add(painelDataHora, gbc);

        // Serviço
        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Serviço:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0;
        String[] servicos = {"Corte", "Barba", "Corte e Barba"};
        comboServico = new JComboBox<>(servicos);
        painelFormulario.add(comboServico, gbc);
        
        // Profissional
        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Profissional:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0;
        String[] profissionais = {"André (ID 0)", "Bruno (ID 1)", "Carlos (ID 2)"};
        comboProfissional = new JComboBox<>(profissionais);
        painelFormulario.add(comboProfissional, gbc);
        
        // Método de Pagamento
        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0.0;
        painelFormulario.add(new JLabel("Pagamento:"), gbc);
        gbc.gridx = 1; gbc.gridy = y++; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0;
        
        String[] metodosPagamento = new String[PaymentType.values().length];
        for (int i = 0; i < PaymentType.values().length; i++) {
            metodosPagamento[i] = PaymentType.values()[i].getDescription();
        }
        comboMetodoPagamento = new JComboBox<>(metodosPagamento);
        painelFormulario.add(comboMetodoPagamento, gbc);


        // Rodapé e Botões
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelRodape.setBackground(Color.WHITE);
        painelRodape.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        botaoSalvar = new JButton("Salvar Agendamento");
        botaoCancelar = new JButton("Cancelar");
        
        painelRodape.add(botaoCancelar);
        painelRodape.add(botaoSalvar);

        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.add(painelHeader, BorderLayout.NORTH);
        painelPrincipal.add(painelFormulario, BorderLayout.CENTER);
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        add(painelPrincipal);
        
        botaoSalvar.addActionListener(e -> salvarAgendamento());
        botaoCancelar.addActionListener(e -> acaoVoltarParaAgendamento.run());
    }
    
    private void salvarAgendamento() {
        try {
            String nomeCliente = campoNomeCliente.getText().trim();
            String telefone = campoTelefone.getText().replaceAll("[^0-9]", "");
            String dataStr = campoData.getText();
            String horaStr = campoHora.getText();
            String servicoSelecionado = (String) comboServico.getSelectedItem();
            String profissionalSelecionado = (String) comboProfissional.getSelectedItem();
            String metodoPagamentoSelecionado = (String) comboMetodoPagamento.getSelectedItem();

            if (nomeCliente.isEmpty() || telefone.length() < 10 || dataStr.isEmpty() || horaStr.isEmpty()) {
                  JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios e um telefone válido.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                  return;
            }

            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(dataStr + " " + horaStr, formatoEntrada);
            
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
            DateTimeFormatter formatoSaidaApi = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String dataHoraApi = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).format(formatoSaidaApi);

            List<Long> serviceIds = new ArrayList<>();
            if (servicoSelecionado.equals("Barba")) serviceIds.add(1L);
            else if (servicoSelecionado.equals("Corte e Barba")) serviceIds.add(2L);
            else if (servicoSelecionado.equals("Corte")) serviceIds.add(3L);

            Long employeeId = 0L;
            if (profissionalSelecionado.contains("(ID 1)")) employeeId = 1L;
            else if (profissionalSelecionado.contains("(ID 2)")) employeeId = 2L;
            
            PaymentType paymentType = null;
            for (PaymentType pt : PaymentType.values()) {
                if (pt.getDescription().equals(metodoPagamentoSelecionado)) {
                    paymentType = pt;
                    break;
                }
            }
            if (paymentType == null) {
                JOptionPane.showMessageDialog(this, "Método de pagamento inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Criação do Objeto de Request
            RequestScheduleServiceJson requestData = new RequestScheduleServiceJson();
            requestData.setAppointmentDateTime(dataHoraApi);
            requestData.setServiceIds(serviceIds);
            requestData.setClientName(nomeCliente);
            requestData.setClientPhone(telefone);
            requestData.setClientId(null);
            requestData.setEmployeeId(employeeId);
            requestData.setPaymentType(paymentType);

            // Chamada API
            AgendamentoApi api = new AgendamentoApi();
            Appointment agendamentoCriado = api.criarAgendamento(requestData);

            // Trata o Resultado
            if (agendamentoCriado != null) {
                JOptionPane.showMessageDialog(this,
                            "Agendamento para " + nomeCliente + " criado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                // new Agendamento().setVisible(true); 
            } else {
                JOptionPane.showMessageDialog(this,
                            "Não foi possível salvar o agendamento. Verifique a resposta da API.",
                            "Falha ao Salvar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (java.time.format.DateTimeParseException dtpe) {
            JOptionPane.showMessageDialog(this,
                         "Erro no formato da Data ou Hora.\nUse dd/MM/yyyy (ex: 19/10/2025) e HH:mm (ex: 14:30)",
                         "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                         "Ocorreu um erro inesperado ao salvar: " + ex.getMessage(),
                         "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new TelaCriarAgendamento().setVisible(true));
    }

}