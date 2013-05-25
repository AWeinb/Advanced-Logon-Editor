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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ale.util.colors.bric.swing.ColorPicker;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.VerticalLayout;

public abstract class FontField extends Field {

    private static final long serialVersionUID = 1L;
    private static final Dimension FONTCHOOSER_DIM = new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH - 20, 25);
    private static final int FIELDHEIGHT = 200;
    private static final int NUMBERLENGTH = 2;

    private JTextField fontInputField;
    private JTextField sizeInputField;
    private JButton colorBtn;
    private JCheckBox boldBtn;
    private JCheckBox underlineBtn;
    private JCheckBox shadowBtn;

    public FontField(Color bg, String fieldTitle, String initialFont, int initialSize, Color initialColor, boolean bold, boolean underline,
            boolean shadow) {
        if (initialColor == null) {
            initialColor = Color.GREEN;
        }

        if (fieldTitle == null) {
            fieldTitle = "";
        }

        create(bg, fieldTitle, initialFont, initialSize, initialColor, bold, underline, shadow);
    }

    public abstract void fontChosen(String input);

    public void updateFont(String font) {
        this.fontInputField.setText(font);
    }

    public abstract void sizeTyped(String input);

    public void updateSize(int size) {
        this.sizeInputField.setText(size + "");
    }

    public abstract void colorBtnPressed(int[] argb);

    public void updateColor(Color color) {
        this.colorBtn.setForeground(color);
    }

    public abstract void boldPressed(boolean selected);

    public void updateBold(boolean bold) {
        this.boldBtn.setSelected(!bold);
    }

    public abstract void underlinePressed(boolean selected);

    public void updateUnderline(boolean underline) {
        this.underlineBtn.setSelected(!underline);
    }

    public abstract void shadowPressed(boolean selected);

    public void updateShadow(boolean shadow) {
        this.shadowBtn.setSelected(!shadow);
    }

    @Override
    protected int parseInt(String input) {
        int i;

        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        return i;
    }

    private void create(Color bg, String title, String initialFont, int initialSize, final Color initialColor, boolean bold,
            boolean underline, boolean shadow) {
        setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, FIELDHEIGHT));
        setBackground(bg);
        setLayout(new VerticalLayout(5, VerticalLayout.CENTER));
        setBorder(BorderFactory.createTitledBorder(title));

        this.fontInputField = new JTextField(initialFont);
        this.fontInputField.setPreferredSize(GUIConstants.DEFAULT_BUTTON_DIM);
        this.fontInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                fontChosen(FontField.this.fontInputField.getText());
            }
        });
        this.add(this.fontInputField);

        JPanel sizePanel = new JPanel();
        sizePanel.setBackground(bg);

        JLabel sizeLbl = new JLabel(Field.strFontsizeLabel);
        sizeLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        sizeLbl.setBackground(bg);

        this.sizeInputField = new JTextField(NUMBERLENGTH + 1);
        this.sizeInputField.setText(initialSize + "");
        this.sizeInputField
                .setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0), this.sizeInputField.getBorder()));
        this.sizeInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (controlThicknessInput(FontField.this.sizeInputField, NUMBERLENGTH, false)) {
                    sizeTyped(FontField.this.sizeInputField.getText());

                } else {
                    sizeTyped(null);
                }
            }
        });

        sizePanel.add(sizeLbl);
        sizePanel.add(this.sizeInputField);
        this.add(sizePanel);

        this.colorBtn = new JButton(Field.strFontColorBtn);
        this.colorBtn.setBackground(bg);
        this.colorBtn.setFocusable(false);
        this.colorBtn.setForeground(initialColor);
        this.colorBtn.setMinimumSize(new Dimension(FONTCHOOSER_DIM.width / 2, 23));
        this.colorBtn.addMouseListener(new MouseAdapter() {
            private Color c = initialColor;

            @Override
            public void mousePressed(MouseEvent e) {
                this.c = ColorPicker.showDialog(this.c);
                if (this.c != null) {
                    colorBtnPressed(new int[] { this.c.getAlpha(), this.c.getRed(), this.c.getGreen(), this.c.getBlue() });
                    FontField.this.colorBtn.setForeground(this.c);
                }
            }
        });
        this.add(this.colorBtn);

        this.boldBtn = new JCheckBox(Field.strBoldBtn);
        this.boldBtn.setSelected(bold);
        this.boldBtn.setBackground(bg);
        this.boldBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boldPressed(!FontField.this.boldBtn.isSelected());
            }
        });
        this.add(this.boldBtn);

        this.underlineBtn = new JCheckBox(Field.strUnderlineBtn);
        this.underlineBtn.setSelected(underline);
        this.underlineBtn.setBackground(bg);
        this.underlineBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                underlinePressed(!FontField.this.underlineBtn.isSelected());
            }
        });
        this.add(this.underlineBtn);

        this.shadowBtn = new JCheckBox(Field.strShadowBtn);
        this.shadowBtn.setSelected(shadow);
        this.shadowBtn.setBackground(bg);
        this.shadowBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                shadowPressed(!FontField.this.shadowBtn.isSelected());
            }
        });
        this.add(this.shadowBtn);
    }
}
