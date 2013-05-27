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

public abstract class SizeField extends Field {

    private static final long serialVersionUID = 1L;
    private int numberLength;
    private JTextField width;
    private JTextField height;
    private static final int FIELDHEIGHT = 88;

    public SizeField(int[] initialValue, int maxInputLength, Color bg, String fieldTitle, boolean showResetBtn) {
        if ((initialValue == null) || (initialValue.length != 2)) {
            throw new IllegalArgumentException();
        }

        this.numberLength = maxInputLength;

        if (bg == null) {
            bg = Color.WHITE;
        }

        if (fieldTitle == null) {
            fieldTitle = "";
        }

        create(initialValue, bg, fieldTitle, showResetBtn);
    }

    public abstract void widthOnKeyReleased(String input);

    public abstract void heightOnKeyReleased(String input);

    public abstract void resetOnClick();

    public void update(int w, int h) {
        this.width.setText(w + "");
        this.height.setText(h + "");
    }

    public void updateWidthfieldColor(Color color) {
        this.width.setForeground(color);
    }

    public void updateHeightfieldColor(Color color) {
        this.height.setForeground(color);
    }

    private void create(int[] initialValue, Color bg, String fieldTitle, boolean showResetBtn) {
        setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, FIELDHEIGHT));
        setBackground(bg);
        setBorder(BorderFactory.createTitledBorder(fieldTitle));
        setLayout(new VerticalLayout(5, VerticalLayout.CENTER));

        JPanel panel = new JPanel();
        panel.setBackground(bg);
        this.add(panel);

        this.width = new JTextField();
        this.width.setColumns(this.numberLength);
        this.width.setToolTipText(Field.strWidthTooltip);
        this.width.setText(initialValue[0] + "");
        panel.add(this.width);

        JLabel x = new JLabel("x");
        panel.add(x);

        this.height = new JTextField();
        this.height.setColumns(this.numberLength);
        this.height.setToolTipText(Field.strHeightTooltip);
        this.height.setText(initialValue[1] + "");
        panel.add(this.height);

        this.width.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controlSizeInput(SizeField.this.width) && controlSizeInputLength(SizeField.this.width, SizeField.this.numberLength)) {
                    SizeField.this.width.setForeground(Color.BLACK);
                    widthOnKeyReleased(SizeField.this.width.getText());
                } else {
                    SizeField.this.width.setForeground(Color.RED);
                    widthOnKeyReleased(null);
                }
            }
        });

        this.height.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controlSizeInput(SizeField.this.height) && controlSizeInputLength(SizeField.this.height, SizeField.this.numberLength)) {
                    SizeField.this.height.setForeground(Color.BLACK);
                    heightOnKeyReleased(SizeField.this.height.getText());
                } else {
                    SizeField.this.height.setForeground(Color.RED);
                    heightOnKeyReleased(null);
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