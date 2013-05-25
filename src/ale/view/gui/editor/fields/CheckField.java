/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.fields;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;

import ale.view.gui.GUIConstants;
import ale.view.gui.util.VerticalLayout;

public abstract class CheckField extends Field {

    private static final long serialVersionUID = 1L;
    private static final int FIELDHEIGHT = 48;

    public CheckField(boolean initialValue, Color bg, String fieldTitle, String checkboxFunction) {
        if (bg == null) {
            setBackground(Color.WHITE);
        }

        if (fieldTitle == null) {
            fieldTitle = "";
        }

        if (checkboxFunction == null) {
            checkboxFunction = "";
        }

        create(initialValue, bg, fieldTitle, checkboxFunction);
    }

    public abstract void btnPressed(boolean selected);

    private void create(boolean initialValue, Color bg, String fieldTitle, String checkboxFunction) {
        setBackground(bg);
        setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, FIELDHEIGHT));
        setLayout(new VerticalLayout(0, VerticalLayout.CENTER));
        setBorder(BorderFactory.createTitledBorder(fieldTitle));

        final JCheckBox checkbox = new JCheckBox();
        checkbox.setText(checkboxFunction);
        checkbox.setBackground(bg);
        checkbox.setSelected(initialValue);
        checkbox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnPressed(!checkbox.isSelected());
            }
        });
        this.add(checkbox);
    }
}