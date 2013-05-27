/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.fields;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ale.view.gui.GUIConstants;
import ale.view.gui.util.VerticalLayout;

public abstract class Numberfield extends Field {

    private static final long serialVersionUID = 1L;
    private static final int FIELDHEIGHT = 88;
    private JTextField numberfield;
    private int numberLength;

    public Numberfield(int initialValue, int maxInputLength, Color bg, String fieldTitle, String fieldlabel, boolean showResetBtn) {
        this.numberLength = maxInputLength;

        if (bg == null) {
            bg = Color.WHITE;
        }

        if (fieldTitle == null) {
            fieldTitle = "";
        }

        if (fieldlabel == null) {
            fieldlabel = "";
        }

        create(initialValue, bg, fieldTitle, fieldlabel, showResetBtn);
    }

    public abstract void onKeyReleased(String input);

    public abstract void resetOnClick();

    public void update(int num) {
        this.numberfield.setText(num + "");
    }

    public void update(Color color) {
        this.numberfield.setForeground(color);
    }

    /*
     * 
     */
    private void create(int initialValue, Color bg, String fieldTitle, String fieldlabel, boolean showResetBtn) {
        setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, FIELDHEIGHT));
        setBackground(bg);
        setBorder(BorderFactory.createTitledBorder(fieldTitle));
        setLayout(new VerticalLayout(5, VerticalLayout.CENTER));

        JPanel panel = new JPanel();
        panel.setBackground(bg);
        this.add(panel);

        JLabel label = new JLabel(fieldlabel + ": ");
        panel.add(label);

        this.numberfield = new JTextField();
        this.numberfield.setColumns(this.numberLength);
        this.numberfield.setToolTipText(Field.strHeightTooltip);
        this.numberfield.setText(initialValue + "");
        panel.add(this.numberfield);

        this.numberfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controlSizeInput(Numberfield.this.numberfield)
                        && controlSizeInputLength(Numberfield.this.numberfield, Numberfield.this.numberLength)) {
                    Numberfield.this.numberfield.setForeground(Color.BLACK);
                    onKeyReleased(Numberfield.this.numberfield.getText());
                } else {
                    Numberfield.this.numberfield.setForeground(Color.RED);
                    onKeyReleased(null);
                }
            }
        });

        JPanel panel2 = new JPanel();
        panel2.setBackground(bg);
        this.add(panel2);

        JButton resetBtn = new JButton(Field.strReset);
        resetBtn.setEnabled(showResetBtn);
        resetBtn.setBackground(bg);
        panel2.add(resetBtn);

        resetBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetOnClick();
            }
        });
    }
}
