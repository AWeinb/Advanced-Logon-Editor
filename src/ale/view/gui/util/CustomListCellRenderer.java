/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.chooser <br/>
 * Class  : CustomListCellRenderer <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>CustomListCellRenderer</code> class is used for the choosers skin listing.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 24.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class CustomListCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;

    private Color cellBg1 = Color.WHITE;
    private Color cellBg2 = this.cellBg1;

    /**
     * 
     */
    public CustomListCellRenderer() {
        super();
    }

    /**
     * Sets the background of all lines of the list.
     *
     * @param bg Color
     */
    public void setListCellBackground(Color bg) {
        this.cellBg1 = bg;
    }

    /**
     * Sets the background of the cells which are not connected.
     *
     * @param bgOne Color which is used for the first, third, ... cell.
     * @param bgTwo  Color
     */
    public void setListCellBackground(Color bgOne, Color bgTwo) {
        this.cellBg1 = bgOne;
        this.cellBg2 = bgTwo;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if ((index % 2) == 0) {
            if (!isSelected) {
                c.setBackground(this.cellBg1);
            }
        } else {
            if (!isSelected) {
                c.setBackground(this.cellBg2);
            }
        }

        return c;
    }

    @Override
    public void setText(String text) {
        super.setText(" " + text);
    }
}
