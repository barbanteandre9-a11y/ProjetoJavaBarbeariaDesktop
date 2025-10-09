/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author andre
 */
public class BotaoRenderer extends JButton implements TableCellRenderer {
        public BotaoRenderer() {
            setOpaque(true);
            setBackground(new Color(0, 123, 255));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

            try {
                ImageIcon icon = new ImageIcon(getClass().getResource("/images/lapis.png"));
                Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(img));
                setText("");
            } catch (Exception e) {
                setText("✏️");
            }

            // Efeito hover
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    setBackground(new Color(0, 105, 217));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    setBackground(new Color(0, 123, 255));
                }
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

