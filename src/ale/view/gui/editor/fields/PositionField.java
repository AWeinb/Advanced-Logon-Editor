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
import javax.swing.JPanel;
import javax.swing.border.Border;

import ale.model.skin.SkinConstants.Position;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.VerticalLayout;

public abstract class PositionField extends Field {

    private static final long serialVersionUID = 1L;
    private static final int FIELDHEIGHT = 95;
    private JCheckBox[] checkboxes;

    public PositionField(Position initialValue, boolean[] active, Color bg, String fieldTitle) {
        if ((active == null) || (active.length != 9)) {
            throw new IllegalArgumentException();
        }

        if (bg == null) {
            bg = Color.WHITE;
        }

        if (fieldTitle == null) {
            fieldTitle = "";
        }

        create(initialValue, active, bg, fieldTitle);
    }

    public abstract void topleftOnPressed();

    public abstract void topOnPressed();

    public abstract void toprightOnPressed();

    public abstract void centerleftOnPressed();

    public abstract void centerOnPressed();

    public abstract void centerrightOnPressed();

    public abstract void bottomleftOnPressed();

    public abstract void bottomOnPressed();

    public abstract void bottomrightOnPressed();

    public void setSelectionOnPosition(Position pos) {
        for (JCheckBox checkboxe : this.checkboxes) {
            checkboxe.setSelected(false);
        }

        switch (pos) {
            case TOPLEFT:
                this.checkboxes[0].setSelected(true);
                break;
            case TOP:
                this.checkboxes[1].setSelected(true);
                break;
            case TOPRIGHT:
                this.checkboxes[2].setSelected(true);
                break;
            case LEFT:
                this.checkboxes[3].setSelected(true);
                break;
            case CENTER:
                this.checkboxes[4].setSelected(true);
                break;
            case RIGHT:
                this.checkboxes[5].setSelected(true);
                break;
            case BOTTOMLEFT:
                this.checkboxes[6].setSelected(true);
                break;
            case BOTTOM:
                this.checkboxes[7].setSelected(true);
                break;
            case BOTTOMRIGHT:
                this.checkboxes[8].setSelected(true);
                break;

            default:
        }
    }

    /*
     * 
     */
    private void create(Position initialValue, boolean[] active, Color bg, String fieldTitle) {
        setBackground(bg);
        setPreferredSize(new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, FIELDHEIGHT));
        setLayout(new VerticalLayout(0, VerticalLayout.CENTER));
        setBorder(BorderFactory.createTitledBorder(fieldTitle));

        this.checkboxes = new JCheckBox[9];
        Border cb = BorderFactory.createEmptyBorder(0, 3, 0, 3);

        for (int i = 0; i < this.checkboxes.length; i++) {
            this.checkboxes[i] = new JCheckBox();
            this.checkboxes[i].setBorder(cb);
            this.checkboxes[i].setFocusable(false);
            this.checkboxes[i].setBackground(bg);
            this.checkboxes[i].setEnabled(active[i]);
            this.checkboxes[i].setToolTipText(Field.strPosTooltip[i]);
        }

        if (active[0]) {
            this.checkboxes[0].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(0);
                    PositionField.this.checkboxes[0].setSelected(false);
                    topleftOnPressed();
                }
            });
        }
        if (active[1]) {
            this.checkboxes[1].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(1);
                    PositionField.this.checkboxes[1].setSelected(false);
                    topOnPressed();
                }
            });
        }
        if (active[2]) {
            this.checkboxes[2].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(2);
                    PositionField.this.checkboxes[2].setSelected(false);
                    toprightOnPressed();
                }
            });
        }
        if (active[3]) {
            this.checkboxes[3].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(3);
                    PositionField.this.checkboxes[3].setSelected(false);
                    centerleftOnPressed();
                }
            });
        }
        if (active[4]) {
            this.checkboxes[4].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(4);
                    PositionField.this.checkboxes[4].setSelected(false);
                    centerOnPressed();
                }
            });
        }
        if (active[5]) {
            this.checkboxes[5].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(5);
                    PositionField.this.checkboxes[5].setSelected(false);
                    centerrightOnPressed();
                }
            });
        }
        if (active[6]) {
            this.checkboxes[6].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(6);
                    PositionField.this.checkboxes[6].setSelected(false);
                    bottomleftOnPressed();
                }
            });
        }
        if (active[7]) {
            this.checkboxes[7].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(7);
                    PositionField.this.checkboxes[7].setSelected(false);
                    bottomOnPressed();
                }
            });
        }
        if (active[8]) {
            this.checkboxes[8].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clear(8);
                    PositionField.this.checkboxes[8].setSelected(false);
                    bottomrightOnPressed();
                }
            });
        }

        if (initialValue != null) {
            switch (initialValue) {
                case BOTTOM:
                    this.checkboxes[7].setSelected(true);
                    break;
                case BOTTOMLEFT:
                    this.checkboxes[6].setSelected(true);
                    break;
                case BOTTOMRIGHT:
                    this.checkboxes[8].setSelected(true);
                    break;
                case CENTER:
                    this.checkboxes[4].setSelected(true);
                    break;
                case LEFT:
                    this.checkboxes[3].setSelected(true);
                    break;
                case RIGHT:
                    this.checkboxes[5].setSelected(true);
                    break;
                case TOP:
                    this.checkboxes[1].setSelected(true);
                    break;
                case TOPLEFT:
                    this.checkboxes[0].setSelected(true);
                    break;
                case TOPRIGHT:
                    this.checkboxes[2].setSelected(true);
                    break;
                default:
                    break;
            }
        }

        JPanel panelTop = new JPanel();
        panelTop.setBorder(null);
        panelTop.setBackground(bg);
        panelTop.add(this.checkboxes[0]);
        panelTop.add(this.checkboxes[1]);
        panelTop.add(this.checkboxes[2]);
        this.add(panelTop);

        JPanel panelMiddle = new JPanel();
        panelMiddle.setBorder(null);
        panelMiddle.setBackground(bg);
        panelMiddle.add(this.checkboxes[3]);
        panelMiddle.add(this.checkboxes[4]);
        panelMiddle.add(this.checkboxes[5]);
        this.add(panelMiddle);

        JPanel panelBottom = new JPanel();
        panelBottom.setBorder(null);
        panelBottom.setBackground(bg);
        panelBottom.add(this.checkboxes[6]);
        panelBottom.add(this.checkboxes[7]);
        panelBottom.add(this.checkboxes[8]);
        this.add(panelBottom);
    }

    private void clear(int exceptFor) {
        for (int i = 0; i < this.checkboxes.length; i++) {
            if (i != exceptFor) {
                this.checkboxes[i].setSelected(false);
            }
        }
    }
}