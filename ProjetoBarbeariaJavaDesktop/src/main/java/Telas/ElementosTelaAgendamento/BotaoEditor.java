package Telas.ElementosTelaAgendamento;

import Telas.Agendamento;
import Telas.telaAtualizarAgendamento;
import entities.Appointment; 

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionListener; 
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class BotaoEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    
    private final JButton botao;
    private int linha; 
    private final Agendamento telaPrincipal; 

    public BotaoEditor(Agendamento tela) { 
        this.telaPrincipal = tela;

        botao = new JButton();
        botao.setBackground(new Color(0, 123, 255));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/lapis.png"));
            Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
            botao.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            botao.setText("✏️");
        }

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                botao.setBackground(new Color(0, 105, 217));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                botao.setBackground(new Color(0, 123, 255));
            }
        });
        
        botao.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(
             JTable table, Object value, boolean isSelected, int row, int column) {
        this.linha = row; 
        return botao;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        fireEditingStopped(); 

        Appointment agendamentoParaEditar = telaPrincipal.getAppointmentPorLinhaVisivel(this.linha);

        if (agendamentoParaEditar != null) {
            telaAtualizarAgendamento telaEdicao = new telaAtualizarAgendamento(
                telaPrincipal, 
                agendamentoParaEditar
            );
            
            telaEdicao.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(
                telaPrincipal, 
                "Erro: Não foi possível carregar os dados do agendamento (ID não encontrado).", 
                "Erro de Dados", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}