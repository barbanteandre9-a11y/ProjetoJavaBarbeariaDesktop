package Telas.ElementosGeral;

import java.awt.BorderLayout; // Importe o BorderLayout
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
// (Adicione os outros imports necessários se o seu editor não fizer automaticamente)
  

public class PainelHeaderResponsivo extends JPanel {

    private Image imagem;

    // O construtor agora aceita uma "ação de voltar"
    public PainelHeaderResponsivo(String caminhoImagem, Runnable acaoVoltar) {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource(caminhoImagem));
            this.imagem = icone.getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem do header: " + caminhoImagem);
            this.imagem = null;
        }

        // Define o layout do painel do header para organizar o botão
        this.setLayout(new BorderLayout());

        // Se uma ação de voltar foi fornecida, cria e adiciona o botão
        if (acaoVoltar != null) {
            ElementosTela elementos = new ElementosTela();
            JPanel botaoVoltar = elementos.criarBotaoIcone("🏠", acaoVoltar);

            // Adiciona o botão na posição OESTE (esquerda) do header
            this.add(botaoVoltar, BorderLayout.WEST);
        }
    }

    // O método paintComponent desenha a imagem no fundo, atrás do botão
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagem != null) {
            g.drawImage(imagem, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    // O método getPreferredSize foi atualizado para não ser mais necessário
    // para o redimensionamento, mas é bom mantê-lo para o tamanho inicial.
    @Override
    public Dimension getPreferredSize() {
        if (imagem != null) {
            double proporcao = (double) imagem.getHeight(null) / (double) imagem.getWidth(null);
            int alturaPreferida = (int) (1000 * proporcao); // Baseado na largura da janela
            return new Dimension(1000, alturaPreferida);
        }
        return new Dimension(100, 50);
    }
}