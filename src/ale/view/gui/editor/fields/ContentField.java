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

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import ale.view.gui.GUIConstants;

public abstract class ContentField extends Field {

    private static final int FIELDHEIGHT = 60;
    private static final int MAXCONTENT = 50;

    public ContentField(String initialValue, Color bg, String fieldTitle) {
        if (bg == null) {
            setBackground(Color.WHITE);
        }

        if (fieldTitle == null) {
            fieldTitle = "";
        }

        if (initialValue == null) {
            initialValue = "";
        }

        create(initialValue, bg, fieldTitle);
    }

    public abstract void contentUpdate(String input);

    private void create(String initialValue, Color bg, String fieldTitle) {
        setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, FIELDHEIGHT));
        setBackground(bg);
        setBorder(BorderFactory.createTitledBorder(fieldTitle));

        final JTextField contentInputField = new JTextField(initialValue);
        contentInputField.setPreferredSize(GUIConstants.DEFAULT_BUTTON_DIM);
        contentInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (contentInputField.getText().length() <= MAXCONTENT) {
                    contentUpdate(contentInputField.getText());

                } else {
                    while (contentInputField.getText().length() > MAXCONTENT) {
                        contentInputField.setText(contentInputField.getText().substring(0,
                                contentInputField.getText().length() - 1));
                    }
                }
            }
        });
        this.add(contentInputField);
    }
}
