/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

/**
 *
 * @author ht3049931
 */
public class ElementosTela extends javax.swing.JFrame {
    
    private static final Color COR_PAINEL_BOTAO = new Color(13, 71, 161); // Azul escuro
    private static final Color COR_PAINEL_BOTAO_HOVER = new Color(21, 101, 192); // Azul mais claro para hover
    private static final Color COR_TEXTO = Color.WHITE;
    private static final Font FONTE_BOTAO = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font FONTE_ICONE = new Font("Segoe UI Symbol", Font.PLAIN, 48); // Fonte que suporta ícones unicode
    private static final Font FONTE_BOTAO_ACAO = new Font("Segoe UI", Font.BOLD, 12);
    private static final Font FONTE_ICONE_ACAO = new Font("Segoe UI Symbol", Font.PLAIN, 20);

    
            
    public JPanel criarBotaoFuncionalidade(String texto, String iconeUnicode) {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBackground(COR_PAINEL_BOTAO);
        painel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Borda arredondada
        Border margem = new EmptyBorder(15, 15, 15, 15);
        Border bordaComposta = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_PAINEL_BOTAO, 10, true), // Simula o arredondamento
            margem
        );
        painel.setBorder(bordaComposta);

        // Ícone
        JLabel iconeLabel = new JLabel(iconeUnicode, SwingConstants.CENTER);
        iconeLabel.setFont(FONTE_ICONE);
        iconeLabel.setForeground(COR_TEXTO);

        // Texto
        JLabel textoLabel = new JLabel(texto, SwingConstants.CENTER);
        textoLabel.setFont(FONTE_BOTAO);
        textoLabel.setForeground(COR_TEXTO);

        painel.add(iconeLabel, BorderLayout.CENTER);
        painel.add(textoLabel, BorderLayout.SOUTH);

        // Adicionar eventos do mouse
        painel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Efeito hover ao entrar o mouse
                painel.setBackground(COR_PAINEL_BOTAO_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                painel.setBackground(COR_PAINEL_BOTAO);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                    ElementosTela.this,
                    "Abrindo a tela de: " + texto,
                    "Navegação",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        return painel;
    }
    
    public JLabel criarRotuloTitulo(String textoAlternativo, String caminhoIcone) {
        JLabel rotulo = new JLabel();
        rotulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Tenta carregar o ícone. Se falhar, usa o texto alternativo.
        try {
            rotulo.setIcon(new ImageIcon(ElementosTela.class.getResource(caminhoIcone)));
        } catch (Exception e) {
            System.err.println("Aviso: Imagem não encontrada em '" + caminhoIcone + "'. Usando texto alternativo.");
            rotulo.setText(textoAlternativo);
            rotulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
            rotulo.setForeground(Color.WHITE);
        }
        
        return rotulo;
    }
    
    public JPanel criarBotaoDeAcao(String texto, String iconeUnicode, Runnable acaoAoClicar) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        painel.setBackground(COR_PAINEL_BOTAO);
        painel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        Border margem = new EmptyBorder(1, 1, 1, 1);
        Border bordaComposta = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_PAINEL_BOTAO, 2, true),
            margem
        );
        painel.setBorder(bordaComposta);

        
        JLabel iconeLabel = new JLabel(iconeUnicode);
        iconeLabel.setFont(FONTE_ICONE_ACAO);
        iconeLabel.setForeground(COR_TEXTO);

        
        JLabel textoLabel = new JLabel(texto);
        textoLabel.setFont(FONTE_BOTAO_ACAO);
        textoLabel.setForeground(COR_TEXTO);
        
        painel.add(iconeLabel);
        painel.add(textoLabel);

        painel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                painel.setBackground(COR_PAINEL_BOTAO_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                painel.setBackground(COR_PAINEL_BOTAO);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Executa a ação específica passada para este botão
                if (acaoAoClicar != null) {
                    acaoAoClicar.run();
                }
            }
        });

        return painel;
    }
}
