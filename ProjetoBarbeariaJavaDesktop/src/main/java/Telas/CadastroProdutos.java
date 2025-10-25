package Telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CadastroProdutos extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JPanel painelFormulario;

    private JLabel lblNome;
    private JLabel lblQuantidade;
    private JLabel lblPreco;

    private JTextField txtNome;
    private JTextField txtQuantidade;
    private JTextField txtPreco;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public CadastroProdutos() {
        super("Cadastro de Produtos");
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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels e campos
        gbc.gridx = 0;
        gbc.gridy = 0;
        lblNome = new JLabel("Nome do Produto:");
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelFormulario.add(lblNome, gbc);

        gbc.gridx = 1;
        txtNome = new JTextField(20);
        painelFormulario.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelFormulario.add(lblQuantidade, gbc);

        gbc.gridx = 1;
        txtQuantidade = new JTextField(10);
        painelFormulario.add(txtQuantidade, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        lblPreco = new JLabel("Preço (R$):");
        lblPreco.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelFormulario.add(lblPreco, gbc);

        gbc.gridx = 1;
        txtPreco = new JTextField(10);
        painelFormulario.add(txtPreco, gbc);

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
        SwingUtilities.invokeLater(() -> new CadastroProdutos().setVisible(true));
    }
}
