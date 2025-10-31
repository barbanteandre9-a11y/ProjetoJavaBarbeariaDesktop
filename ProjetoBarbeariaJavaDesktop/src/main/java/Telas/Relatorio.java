/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import ChamadasAPI.AgendamentoApi;
import Telas.ElementosGeral.ElementosTela;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author andre
 */
public class Relatorio extends JFrame {
    private JPanel painelPrincipal;
    private JPanel painelDeConteudo;
    private JTextField campoDataInicio;
    private JTextField campoDataFim;
    
    
    public Relatorio (){
        super("Relatório - Sistema de Barbearia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        
        ElementosTela elemento = new ElementosTela();
        
        Runnable acaoVoltarParaMenu = () -> {
            new MenuPrincipalBarbearia().setVisible(true); 
            this.dispose();
        };

        JPanel painelHeader = elemento.criarHeaderSimples(
            "Relatório de faturamento",
            acaoVoltarParaMenu
        );
        
        painelDeConteudo = new JPanel(new GridBagLayout());
        painelDeConteudo.setBackground(Color.WHITE);
        painelDeConteudo.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // painel de filtros 
        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10)); 
        painelFiltros.setBackground(Color.WHITE);
        
        Font fonteLabel = new Font("Arial", Font.BOLD, 14); 
        Font fonteCampo = new Font("Arial", Font.PLAIN, 14); 

        JLabel labelDataInicio = new JLabel("Data de início:"); 
        labelDataInicio.setFont(fonteLabel);
        painelFiltros.add(labelDataInicio); 

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            campoDataInicio = new JFormattedTextField(mascaraData);
            ((JFormattedTextField) campoDataInicio).setColumns(8);
            ((JFormattedTextField) campoDataInicio).setFocusLostBehavior(JFormattedTextField.PERSIST);
        } catch (Exception e) {
            campoDataInicio = new JFormattedTextField();
            ((JFormattedTextField) campoDataInicio).setColumns(8);
            ((JFormattedTextField) campoDataInicio).setFocusLostBehavior(JFormattedTextField.PERSIST);
        }
        campoDataInicio.setFont(fonteCampo);
        painelFiltros.add(campoDataInicio); 
        
        
        JLabel labelDataFim = new JLabel("Data final:"); 
        labelDataFim.setFont(fonteLabel);
        painelFiltros.add(labelDataFim); 
        
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            campoDataFim = new JFormattedTextField(mascaraData);
            ((JFormattedTextField) campoDataFim).setColumns(8);
            ((JFormattedTextField) campoDataFim).setFocusLostBehavior(JFormattedTextField.PERSIST);
        } catch (Exception e) {
            campoDataFim = new JFormattedTextField();
            ((JFormattedTextField) campoDataFim).setColumns(8);
            ((JFormattedTextField) campoDataFim).setFocusLostBehavior(JFormattedTextField.PERSIST);
        }
        campoDataFim.setFont(fonteCampo);
        painelFiltros.add(campoDataFim); 
        
        GridBagConstraints gbcConteudo = new GridBagConstraints();
        gbcConteudo.gridx = 0;
        gbcConteudo.gridy = 0;
        gbcConteudo.anchor = GridBagConstraints.NORTH; 
        gbcConteudo.weighty = 1.0; 
        painelDeConteudo.add(painelFiltros, gbcConteudo);
        
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(Color.WHITE);
        painelBotao.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        
        Runnable acaoLimpar = () -> {
            campoDataInicio.setText("");
            campoDataFim.setText("");
        };
        
        Runnable GerarRelatorio = () -> {
            
        };
        
        JPanel limparInformacoes = elemento.criarBotaoDeAcao("Limpar", "\uD83D\uDDD1", acaoLimpar);
        JPanel botaoGerarRelatorio = elemento.criarBotaoDeAcao("Gerar Relatório", "➕", GerarRelatorio);
        
        painelBotao.add(limparInformacoes);
        painelBotao.add(botaoGerarRelatorio);

        // --- Montagem Final ---
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(painelHeader, BorderLayout.NORTH);
        painelPrincipal.add(painelDeConteudo, BorderLayout.CENTER); 
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);
        add(painelPrincipal);
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Relatorio().setVisible(true));
    }
}
