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
import javax.swing.JPanel;
import javax.swing.JTextField;

import ale.view.gui.GUIConstants;
import ale.view.gui.util.VerticalLayout;

public abstract class PaddingField extends Field {

    private static final long serialVersionUID = 1L;
    private static final int NUMBERLENGTH = 3;
    private static final int FIELDHEIGHT = 80;

    public PaddingField(int[] initialValue, Color bg, String fieldTitle) {
        if ((initialValue != null) && (initialValue.length != 4)) {
            throw new IllegalArgumentException();
        }

        if (bg == null) {
            bg = Color.WHITE;
        }

        if (fieldTitle == null) {
            fieldTitle = "";
        }

        create(initialValue, bg, fieldTitle);
    }

    public abstract void leftFieldOnKeyReleased(String input);

    public abstract void topFieldOnKeyReleased(String input);

    public abstract void rightFieldOnKeyReleased(String input);

    public abstract void bottomFieldOnKeyReleased(String input);

    public abstract int[] reset();

    private void create(int[] init, Color bg, String fieldTitle) {
        setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, FIELDHEIGHT));
        setBackground(bg);
        setBorder(BorderFactory.createTitledBorder(fieldTitle));
        setLayout(new VerticalLayout(0, VerticalLayout.CENTER));
        setToolTipText(Field.strPaddingTooltip);

        JPanel panel = new JPanel();
        panel.setBackground(bg);
        this.add(panel);

        final JTextField left = new JTextField();
        left.setColumns(NUMBERLENGTH + 1);
        left.setToolTipText(Field.strPaddingTooltip);
        panel.add(left);

        final JTextField top = new JTextField();
        top.setColumns(NUMBERLENGTH + 1);
        top.setToolTipText(Field.strPaddingTooltip);
        panel.add(top);

        final JTextField right = new JTextField();
        right.setColumns(NUMBERLENGTH + 1);
        right.setToolTipText(Field.strPaddingTooltip);
        panel.add(right);

        final JTextField bottom = new JTextField();
        bottom.setColumns(NUMBERLENGTH + 1);
        bottom.setToolTipText(Field.strPaddingTooltip);
        panel.add(bottom);

        if (init != null) {
            left.setText(init[0] + "");
            top.setText(init[1] + "");
            right.setText(init[2] + "");
            bottom.setText(init[3] + "");
        }

        left.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controlThicknessInput(left, NUMBERLENGTH, true)) {
                    leftFieldOnKeyReleased(left.getText());
                }
            }
        });

        top.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controlThicknessInput(top, NUMBERLENGTH, true)) {
                    topFieldOnKeyReleased(top.getText());
                }
            }
        });

        right.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controlThicknessInput(right, NUMBERLENGTH, true)) {
                    rightFieldOnKeyReleased(right.getText());
                }
            }
        });

        bottom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controlThicknessInput(bottom, NUMBERLENGTH, true)) {
                    bottomFieldOnKeyReleased(bottom.getText());
                }
            }
        });

        JButton reset = new JButton(Field.strReset);
        reset.setBackground(bg);
        this.add(reset);
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] tmp = reset();
                if ((tmp != null) && (tmp.length == 4)) {
                    left.setText(tmp[0] + "");
                    top.setText(tmp[1] + "");
                    right.setText(tmp[2] + "");
                    bottom.setText(tmp[3] + "");
                }
            }
        });
    }
}