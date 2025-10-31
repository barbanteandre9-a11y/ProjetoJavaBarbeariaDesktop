/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas.ElementosGeral;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ht3049931
 */
public class ElementosTela extends javax.swing.JFrame {
    
    //cores do botão azul
    public static final Color COR_PAINEL_BOTAO_AZUL = new Color(13, 71, 161); // Azul escuro
    public static final Color COR_PAINEL_BOTAO_HOVER_AZUL = new Color(21, 101, 192); // Azul mais claro para hover
    
    //cores do botão vermelho
    public static final Color COR_PAINEL_BOTAO_VERMELHO = new Color(115, 63, 45); // vermelho mais forte
    public static final Color COR_PAINEL_BOTAO_HOVER_VERMELHO = new Color(140, 55, 55); // vermelho mais claro
    
    private static final Color COR_TEXTO = Color.WHITE;
    private static final Font FONTE_BOTAO = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font FONTE_ICONE = new Font("Segoe UI Symbol", Font.PLAIN, 48); // Fonte que suporta ícones unicode
    private static final Font FONTE_BOTAO_ACAO = new Font("Segoe UI", Font.BOLD, 12);
    private static final Font FONTE_ICONE_ACAO = new Font("Segoe UI Symbol", Font.PLAIN, 20);

    
            
    public JPanel criarBotaoFuncionalidadeAzul(String texto, String iconeUnicode, Runnable acaoAoClicar) {
    JPanel painel = new JPanel();
    painel.setLayout(new BorderLayout());
    painel.setBackground(COR_PAINEL_BOTAO_AZUL);
    painel.setCursor(new Cursor(Cursor.HAND_CURSOR));

    Border margem = new EmptyBorder(15, 15, 15, 15);
    Border bordaComposta = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(COR_PAINEL_BOTAO_AZUL, 5, true),
        margem
    );
    painel.setBorder(bordaComposta);

    JLabel iconeLabel = new JLabel(iconeUnicode, SwingConstants.CENTER);
    iconeLabel.setFont(FONTE_ICONE);
    iconeLabel.setForeground(COR_TEXTO);

    JLabel textoLabel = new JLabel(texto, SwingConstants.CENTER);
    textoLabel.setFont(FONTE_BOTAO);
    textoLabel.setForeground(COR_TEXTO);

    painel.add(iconeLabel, BorderLayout.CENTER);
    painel.add(textoLabel, BorderLayout.SOUTH);

    painel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            painel.setBackground(COR_PAINEL_BOTAO_HOVER_AZUL);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            painel.setBackground(COR_PAINEL_BOTAO_AZUL);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (acaoAoClicar != null) {
                acaoAoClicar.run();
            }
        }
    });

    return painel;
}

    public JPanel criarBotaoNavegacao(
    String texto,
    String iconeUnicode,
    Color corFundo,
    Color corHover,
    int larguraBorda,
    Runnable acaoAoClicar
    ) {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBackground(corFundo);
        painel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Border margem = new EmptyBorder(15, 15, 15, 15);
        Border bordaComposta = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corFundo, larguraBorda, true),
            margem
        );
        painel.setBorder(bordaComposta);

        JLabel iconeLabel = new JLabel(iconeUnicode, SwingConstants.CENTER);
        iconeLabel.setFont(FONTE_ICONE);
        iconeLabel.setForeground(COR_TEXTO);

        JLabel textoLabel = new JLabel(texto, SwingConstants.CENTER);
        textoLabel.setFont(FONTE_BOTAO);
        textoLabel.setForeground(COR_TEXTO);

        painel.add(iconeLabel, BorderLayout.CENTER);
        painel.add(textoLabel, BorderLayout.SOUTH);

        painel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                painel.setBackground(corHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                painel.setBackground(corFundo);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (acaoAoClicar != null) {
                    acaoAoClicar.run();
                }
            }
        });

        return painel;
    }

    
    public JPanel criarBotaoFuncionalidadeVermelho(String texto, String iconeUnicode, Runnable acaoAoClicar) {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBackground(COR_PAINEL_BOTAO_VERMELHO);
        painel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Borda arredondada
        Border margem = new EmptyBorder(15, 15, 15, 15);
        Border bordaComposta = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_PAINEL_BOTAO_VERMELHO, 10, true), // Simula o arredondamento
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
                painel.setBackground(COR_PAINEL_BOTAO_HOVER_VERMELHO);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                painel.setBackground(COR_PAINEL_BOTAO_VERMELHO);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                acaoAoClicar.run();
            }
            
        });

        return painel;
    }

    
    public JPanel criarBotaoDeAcao(String texto, String iconeUnicode, Runnable acaoAoClicar) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        painel.setBackground(COR_PAINEL_BOTAO_AZUL);
        painel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        Border margem = new EmptyBorder(1, 1, 1, 1);
        Border bordaComposta = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_PAINEL_BOTAO_AZUL, 2, true),
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
                painel.setBackground(COR_PAINEL_BOTAO_HOVER_AZUL);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                painel.setBackground(COR_PAINEL_BOTAO_AZUL);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            painel.dispatchEvent(e); // reenvia o clique para quem adicionou o painel
            }

        });

        return painel;
    }
    
    public JPanel criarBotaoIcone(String iconeUnicode, Runnable acaoAoClicar) {
    // Painel que servirá de botão
    JPanel painel = new JPanel();
    painel.setOpaque(false); 
    painel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    painel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 

    JLabel iconeLabel = new JLabel(iconeUnicode);
    iconeLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 28)); 
    iconeLabel.setForeground(Color.WHITE); // Cor branca para contrastar com o header

    painel.add(iconeLabel);

    // Adiciona a ação de clique
    painel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (acaoAoClicar != null) {
                acaoAoClicar.run();
            }
        }
    });

    return painel;
}
}
