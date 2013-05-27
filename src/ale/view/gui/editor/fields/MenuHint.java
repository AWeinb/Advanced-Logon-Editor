/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.fields;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ale.view.gui.GUIConstants;

public final class MenuHint extends JPanel {

    private static final long serialVersionUID = 1L;
    private static MenuHint instance;
    private static JTextPane comboboxHint;

    private static final int FIELDHEIGHT = 400;

    private MenuHint(Color bg, String text) {
        if (bg == null) {
            bg = Color.WHITE;
        }

        if (text == null) {
            throw new IllegalArgumentException();
        }

        create(bg, text);
    }

    public static MenuHint getInstance(Color bg, String text) {
        if (MenuHint.instance == null) {
            MenuHint.instance = new MenuHint(bg, text);
        } else {
            comboboxHint.setBackground(bg);
            comboboxHint.setText(text);
        }

        return instance;
    }

    private void create(Color bg, String text) {
        comboboxHint = new JTextPane();
        comboboxHint.setText(text);
        comboboxHint.setBackground(bg);
        comboboxHint.setMargin(new Insets(30, 0, 10, 0));
        comboboxHint.setFont(GUIConstants.DEFAULT_HEADING_FONT);
        comboboxHint.setEditable(false);
        comboboxHint.setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH - 10, FIELDHEIGHT));

        StyledDocument doc = comboboxHint.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        setBackground(bg);
        this.add(comboboxHint);
    }
}