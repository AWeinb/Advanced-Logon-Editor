/*
 * @(#)ColorPickerDialog.java
 *
 * $Date: 2011-05-02 16:01:45 -0500 (Mon, 02 May 2011) $
 *
 * Copyright (c) 2011 by Jeremy Wood.
 * All rights reserved.
 *
 * The copyright of this software is owned by Jeremy Wood.
 * You may not use, copy or modify this software, except in
 * accordance with the license agreement you entered into with
 * Jeremy Wood. For details see accompanying license terms.
 * 
 * This software is probably, but not necessarily, discussed here:
 * http://javagraphics.java.net/
 * 
 * That site should also contain the most recent official version
 * of this software.  (See the SVN repository for more details.)
 */
package ale.util.colors.bric.swing;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This wraps a <code>ColorPicker</code> in a simple dialog with "OK" and "Cancel" options.
 * <P>
 * (This object is used by the static calls in <code>ColorPicker</code> to show a dialog.)
 * 
 */
class ColorPickerDialog extends JDialog {

    private static final long serialVersionUID = 2L;

    ColorPicker cp;
    int alpha;
    Color returnValue = null;
    ActionListener okListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ColorPickerDialog.this.returnValue = ColorPickerDialog.this.cp.getColor();
        }
    };
    DialogFooter footer;

    public ColorPickerDialog(Color color, boolean includeOpacity) {
        initialize(color, includeOpacity);
    }

    public ColorPickerDialog(Dialog owner, Color color, boolean includeOpacity) {
        super(owner);
        initialize(color, includeOpacity);
    }

    private void initialize(Color color, boolean includeOpacity) {
        this.cp = new ColorPicker(true, includeOpacity);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                 | InstantiationException
                 | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {
        }
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(this.cp, c);
        c.gridy++;
        this.footer = DialogFooter.createDialogFooter(new JComponent[] {},
                DialogFooter.OK_CANCEL_OPTION, DialogFooter.OK_OPTION, DialogFooter.ESCAPE_KEY_TRIGGERS_CANCEL);
        c.gridy++;
        c.weighty = 0;
        getContentPane().add(this.footer, c);
        this.cp.setRGB(color.getRed(), color.getGreen(), color.getBlue());
        this.cp.setOpacity(color.getAlpha());
        this.alpha = color.getAlpha();
        pack();
        setLocationRelativeTo(null);

        this.footer.getButton(DialogFooter.OK_OPTION).addActionListener(this.okListener);
    }

    /**
     * @return the color committed when the user clicked 'OK'. Note this returns <code>null</code> if the user canceled
     *         this dialog, or exited via the close decoration.
     */
    public Color getColor() {
        return this.returnValue;
    }
}
