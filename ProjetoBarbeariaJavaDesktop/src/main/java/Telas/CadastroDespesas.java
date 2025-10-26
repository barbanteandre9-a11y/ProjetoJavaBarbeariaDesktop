package Telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CadastroDespesas extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JPanel painelFormulario;

    private JLabel lblDespesa;
    private JLabel lblTipo;
    private JLabel lblValor;
    private JLabel lblDescricao;

    private JTextField txtDespesa;
    private JTextField txtTipo;
    private JTextField txtValor;
    private JTextField txtDescricao;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public CadastroDespesas() {
        super("Cadastro de Despesas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);

        painelDeConteudo = new JPanel(new BorderLayout());
        painelDeConteudo.setBorder(new EmptyBorder(20, 40, 20, 40));
        painelDeConteudo.setBackground(Color.WHITE);

        // Painel do formulário
        painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos do formulário
        gbc.gridx = 0;
        gbc.gridy = 0;
        lblDespesa = new JLabel("Despesa:");
        lblDespesa.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelFormulario.add(lblDespesa, gbc);

        gbc.gridx = 1;
        txtDespesa = new JTextField(15);
        painelFormulario.add(txtDespesa, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelFormulario.add(lblTipo, gbc);

        gbc.gridx = 1;
        txtTipo = new JTextField(15);
        painelFormulario.add(txtTipo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        lblValor = new JLabel("Valor Total:");
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelFormulario.add(lblValor, gbc);

        gbc.gridx = 1;
        txtValor = new JTextField(10);
        painelFormulario.add(txtValor, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        lblDescricao = new JLabel("Descrição:");
        lblDescricao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelFormulario.add(lblDescricao, gbc);

        gbc.gridx = 1;
        txtDescricao = new JTextField(15);
        painelFormulario.add(txtDescricao, gbc);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painelBotoes.setBackground(Color.WHITE);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        // Montagem geral
        painelDeConteudo.add(painelFormulario, BorderLayout.CENTER);
        painelDeConteudo.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.add(painelDeConteudo, BorderLayout.CENTER);

        add(painelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroDespesas().setVisible(true));
    }
}
