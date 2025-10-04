package Telas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PainelHeaderResponsivo extends JPanel {

    private Image imagem;

    public PainelHeaderResponsivo(String caminhoImagem) {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource(caminhoImagem));
            this.imagem = icone.getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem do header: " + caminhoImagem);
            this.imagem = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagem != null) {
            int painelLargura = this.getWidth();
            
            double proporcao = (double) imagem.getHeight(null) / (double) imagem.getWidth(null);
            int painelAltura = (int) (painelLargura * proporcao);

            g.drawImage(imagem, 0, 0, painelLargura, painelAltura, null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (imagem != null) {
            int painelLargura = this.getWidth() > 0 ? this.getWidth() : 800;
            
            double proporcao = (double) imagem.getHeight(null) / (double) imagem.getWidth(null);
            int painelAltura = (int) (painelLargura * proporcao);
            
            return new Dimension(painelLargura, painelAltura);
        }
        return new Dimension(100, 50);
    }
}