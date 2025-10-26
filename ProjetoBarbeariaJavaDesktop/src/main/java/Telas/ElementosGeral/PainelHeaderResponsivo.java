package Telas.ElementosGeral;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PainelHeaderResponsivo extends JPanel {

    private Image imagem;
    private static final int ALTURA_HEADER_FIXA = 300; 

    public PainelHeaderResponsivo(String caminhoImagem, Runnable acaoVoltar) {
        this.setLayout(new BorderLayout());
        this.setOpaque(false); // Garante transparência para a imagem de fundo aparecer

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource(caminhoImagem));
            this.imagem = icone.getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem do header: " + caminhoImagem);
            this.imagem = null;
        }

        if (acaoVoltar != null) {
            ElementosTela elementos = new ElementosTela();
            
            JPanel botaoVoltar = elementos.criarBotaoIcone("🏠", acaoVoltar);

            JPanel wrapperBotao = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            wrapperBotao.setOpaque(false);
            
            wrapperBotao.add(botaoVoltar);

            this.add(wrapperBotao, BorderLayout.NORTH);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagem != null) {
            g.drawImage(imagem, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int larguraDesejada = this.getParent() != null ? this.getParent().getWidth() : 1920;
        
        return new Dimension(larguraDesejada, ALTURA_HEADER_FIXA);
    }
}