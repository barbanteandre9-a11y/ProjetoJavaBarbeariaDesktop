/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas.ElementosTelaAgendamento;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author andre
 */
public class BotaoEditor extends AbstractCellEditor implements TableCellEditor, java.awt.event.ActionListener {
        private JButton botao;
        private int linha;

        public BotaoEditor(JFrame frame) {
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

            // Efeito hover
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
            JOptionPane.showMessageDialog(null,
                "Editar informações do agendamento da linha " + (linha + 1));
        }
    }
    

