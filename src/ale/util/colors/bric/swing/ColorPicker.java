/*
 * @(#)ColorPicker.java
 *
 * $Date: 2012-07-03 01:10:05 -0500 (Tue, 03 Jul 2012) $
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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ale.util.colors.bric.plaf.ColorPickerSliderUI;
import ale.view.gui.util.GUIStrings;

/**
 * This is a panel that offers a robust set of controls to pick a color.
 * <P>
 * This was originally intended to replace the <code>JColorChooser</code>. To use this class to create a color choosing
 * dialog, simply call: <BR>
 * <code>ColorPicker.showDialog(frame, originalColor);</code>
 * <P>
 * However this panel is also resizable, and it can exist in other contexts. For example, you might try the following
 * panel: <BR>
 * <code>ColorPicker picker = new ColorPicker(false, false);</code> <BR>
 * <code>picker.setPreferredSize(new Dimension(200,160));</code> <BR>
 * <code>picker.setMode(ColorPicker.HUE);</code>
 * <P>
 * This will create a miniature color picker that still lets the user choose from every available color, but it does not
 * include all the buttons and numeric controls on the right side of the panel. This might be ideal if you are working
 * with limited space, or non-power-users who don't need the RGB values of a color. The <code>main()</code> method of
 * this class demonstrates possible ways you can customize a <code>ColorPicker</code> component.
 * <P>
 * To listen to color changes to this panel, you can add a <code>PropertyChangeListener</code> listening for changes to
 * the <code>SELECTED_COLOR_PROPERTY</code>. This will be triggered only when the RGB value of the selected color
 * changes.
 * <P>
 * To listen to opacity changes to this panel, use a <code>PropertyChangeListener</code> listening for changes to the
 * <code>OPACITY_PROPERTY</code>.
 * 
 */
public class ColorPicker extends JPanel {
    private static final long serialVersionUID = 3L;

    /** The localized strings used in this (and related) panel(s). */
    protected static ResourceBundle strings = GUIStrings.getCurrentLocale();

    /**
     * This creates a modal dialog prompting the user to select a color.
     * <P>
     * This uses a generic dialog title: "Choose a Color", and does not include opacity.
     * 
     * @param owner
     *            the dialog this new dialog belongs to. This must be a Frame or a Dialog. Java 1.6 supports Windows
     *            here, but this package is designed/compiled to work in Java 1.4, so an
     *            <code>IllegalArgumentException</code> will be thrown if this component is a <code>Window</code>.
     * @param originalColor
     *            the color the <code>ColorPicker</code> initially points to.
     * @return the <code>Color</code> the user chooses, or <code>null</code> if the user cancels the dialog.
     */
    public static Color showDialog(Color originalColor) {
        return showDialog(null, originalColor, false);
    }

    /**
     * This creates a modal dialog prompting the user to select a color.
     * <P>
     * This uses a generic dialog title: "Choose a Color".
     * 
     * @param owner
     *            the dialog this new dialog belongs to. This must be a Frame or a Dialog. Java 1.6 supports Windows
     *            here, but this package is designed/compiled to work in Java 1.4, so an
     *            <code>IllegalArgumentException</code> will be thrown if this component is a <code>Window</code>.
     * @param originalColor
     *            the color the <code>ColorPicker</code> initially points to.
     * @param includeOpacity
     *            whether to add a control for the opacity of the color.
     * @return the <code>Color</code> the user chooses, or <code>null</code> if the user cancels the dialog.
     */
    public static Color showDialog(Color originalColor, boolean includeOpacity) {
        return showDialog(null, originalColor, includeOpacity);
    }

    /**
     * This creates a modal dialog prompting the user to select a color.
     * 
     * @param owner
     *            the dialog this new dialog belongs to. This must be a Frame or a Dialog. Java 1.6 supports Windows
     *            here, but this package is designed/compiled to work in Java 1.4, so an
     *            <code>IllegalArgumentException</code> will be thrown if this component is a <code>Window</code>.
     * @param title
     *            the title for the dialog.
     * @param originalColor
     *            the color the <code>ColorPicker</code> initially points to.
     * @param includeOpacity
     *            whether to add a control for the opacity of the color.
     * @return the <code>Color</code> the user chooses, or <code>null</code> if the user cancels the dialog.
     */
    public static Color showDialog(String title, Color originalColor, boolean includeOpacity) {
        ColorPickerDialog d;
        d = new ColorPickerDialog(originalColor, includeOpacity);
        d.setTitle(title == null ?
                strings.getString("ColorPickerDialogTitle") :
                    title);
        d.pack();
        d.setVisible(true);
        return d.getColor();
    }

    /**
     * <code>PropertyChangeEvents</code> will be triggered for this property when the selected color changes.
     * <P>
     * (Events are only created when then RGB values of the color change. This means, for example, that the change from
     * HSB(0,0,0) to HSB(.4,0,0) will <i>not</i> generate events, because when the brightness stays zero the RGB color
     * remains (0,0,0). So although the hue moved around, the color is still black, so no events are created.)
     * 
     */
    public static final String SELECTED_COLOR_PROPERTY = "selected color";

    /**
     * <code>PropertyChangeEvents</code> will be triggered for this property when <code>setModeControlsVisible()</code>
     * is called.
     */
    public static final String MODE_CONTROLS_VISIBLE_PROPERTY = "mode controls visible";

    /**
     * <code>PropertyChangeEvents</code> will be triggered when the opacity value is adjusted.
     */
    public static final String OPACITY_PROPERTY = "opacity";

    /**
     * <code>PropertyChangeEvents</code> will be triggered when the mode changes. (That is, when the wheel switches from
     * HUE, SAT, BRI, RED, GREEN, or BLUE modes.)
     */
    public static final String MODE_PROPERTY = "mode";

    /** Used to indicate when we're in "hue mode". */
    public static final int HUE = 0;
    /** Used to indicate when we're in "brightness mode". */
    public static final int BRI = 1;
    /** Used to indicate when we're in "saturation mode". */
    public static final int SAT = 2;
    /** Used to indicate when we're in "red mode". */
    public static final int RED = 3;
    /** Used to indicate when we're in "green mode". */
    public static final int GREEN = 4;
    /** Used to indicate when we're in "blue mode". */
    public static final int BLUE = 5;

    /** The vertical slider */
    private JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);

    private int currentRed = 0;
    private int currentGreen = 0;
    private int currentBlue = 0;

    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            Object src = e.getSource();

            if (ColorPicker.this.hue.contains(src) || ColorPicker.this.sat.contains(src) || ColorPicker.this.bri.contains(src)) {
                if (ColorPicker.this.adjustingSpinners > 0) {
                    return;
                }

                setHSB(ColorPicker.this.hue.getFloatValue() / 360f,
                        ColorPicker.this.sat.getFloatValue() / 100f,
                        ColorPicker.this.bri.getFloatValue() / 100f);
            } else if (ColorPicker.this.red.contains(src) || ColorPicker.this.green.contains(src) || ColorPicker.this.blue.contains(src)) {
                if (ColorPicker.this.adjustingSpinners > 0) {
                    return;
                }

                setRGB(ColorPicker.this.red.getIntValue(),
                        ColorPicker.this.green.getIntValue(),
                        ColorPicker.this.blue.getIntValue());
            } else if (src == ColorPicker.this.colorPanel) {
                if (ColorPicker.this.adjustingColorPanel > 0) {
                    return;
                }

                int mode = getMode();
                if ((mode == HUE) || (mode == BRI) || (mode == SAT)) {
                    float[] hsb = ColorPicker.this.colorPanel.getHSB();
                    setHSB(hsb[0], hsb[1], hsb[2]);
                } else {
                    int[] rgb = ColorPicker.this.colorPanel.getRGB();
                    setRGB(rgb[0], rgb[1], rgb[2]);
                }
            } else if (src == ColorPicker.this.slider) {
                if (ColorPicker.this.adjustingSlider > 0) {
                    return;
                }

                int v = ColorPicker.this.slider.getValue();
                Option option = getSelectedOption();
                option.setValue(v);
            } else if (ColorPicker.this.alpha.contains(src)) {
                if (ColorPicker.this.adjustingOpacity > 0) {
                    return;
                }
                int v = ColorPicker.this.alpha.getIntValue();
                setOpacity(v);
            } else if (src == ColorPicker.this.opacitySlider) {
                if (ColorPicker.this.adjustingOpacity > 0) {
                    return;
                }

                setOpacity(ColorPicker.this.opacitySlider.getValue());
            }
        }
    };

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src == ColorPicker.this.hue.radioButton) {
                setMode(HUE);
            } else if (src == ColorPicker.this.bri.radioButton) {
                setMode(BRI);
            } else if (src == ColorPicker.this.sat.radioButton) {
                setMode(SAT);
            } else if (src == ColorPicker.this.red.radioButton) {
                setMode(RED);
            } else if (src == ColorPicker.this.green.radioButton) {
                setMode(GREEN);
            } else if (src == ColorPicker.this.blue.radioButton) {
                setMode(BLUE);
            }
        }
    };

    /**
     * @return the currently selected <code>Option</code>
     */
    private Option getSelectedOption() {
        int mode = getMode();
        if (mode == HUE) {
            return this.hue;
        } else if (mode == SAT) {
            return this.sat;
        } else if (mode == BRI) {
            return this.bri;
        } else if (mode == RED) {
            return this.red;
        } else if (mode == GREEN) {
            return this.green;
        } else {
            return this.blue;
        }
    }

    /**
     * This thread will wait a second or two before committing the text in the hex TextField. This gives the user a
     * chance to finish typing... but if the user is just waiting for something to happen, this makes sure after a
     * second or two something happens.
     */
    class HexUpdateThread extends Thread {
        long myStamp;
        String text;

        public HexUpdateThread(long stamp, String s) {
            this.myStamp = stamp;
            this.text = s;
        }

        @Override
        public void run() {
            if (SwingUtilities.isEventDispatchThread() == false) {
                long WAIT = 1500;

                while ((System.currentTimeMillis() - this.myStamp) < WAIT) {
                    try {
                        long delay = WAIT - (System.currentTimeMillis() - this.myStamp);
                        if (delay < 1) {
                            delay = 1;
                        }
                        Thread.sleep(delay);
                    } catch (Exception e) {
                        Thread.yield();
                    }
                }
                SwingUtilities.invokeLater(this);
                return;
            }

            if (this.myStamp != ColorPicker.this.hexDocListener.lastTimeStamp) {
                // another event has come along and trumped this one
                return;
            }

            if (this.text.length() > 6) {
                this.text = this.text.substring(0, 6);
            }
            while (this.text.length() < 6) {
                this.text = this.text + "0";
            }
            if (ColorPicker.this.hexField.getText().equals(this.text)) {
                return;
            }

            int pos = ColorPicker.this.hexField.getCaretPosition();
            ColorPicker.this.hexField.setText(this.text);
            ColorPicker.this.hexField.setCaretPosition(pos);
        }
    }

    HexDocumentListener hexDocListener = new HexDocumentListener();

    class HexDocumentListener implements DocumentListener {
        long lastTimeStamp;

        @Override
        public void changedUpdate(DocumentEvent e) {
            this.lastTimeStamp = System.currentTimeMillis();

            if (ColorPicker.this.adjustingHexField > 0) {
                return;
            }

            String s = ColorPicker.this.hexField.getText();
            s = stripToHex(s);
            if (s.length() == 6) {
                // the user typed 6 digits: we can work with this:
                try {
                    int i = Integer.parseInt(s, 16);
                    setRGB(((i >> 16) & 0xff), ((i >> 8) & 0xff), ((i) & 0xff));
                    return;
                } catch (NumberFormatException e2) {
                    // this shouldn't happen, since we already stripped out non-hex characters.
                    e2.printStackTrace();
                }
            }
            Thread thread = new HexUpdateThread(this.lastTimeStamp, s);
            thread.start();
            while ((System.currentTimeMillis() - this.lastTimeStamp) == 0) {
                Thread.yield();
            }
        }

        /** Strips a string down to only uppercase hex-supported characters. */
        private String stripToHex(String s) {
            s = s.toUpperCase();
            String s2 = "";
            for (int a = 0; a < s.length(); a++) {
                char c = s.charAt(a);
                if ((c == '0') || (c == '1') || (c == '2') || (c == '3') || (c == '4') || (c == '5') ||
                        (c == '6') || (c == '7') || (c == '8') || (c == '9') || (c == '0') ||
                        (c == 'A') || (c == 'B') || (c == 'C') || (c == 'D') || (c == 'E') || (c == 'F')) {
                    s2 = s2 + c;
                }
            }
            return s2;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changedUpdate(e);
        }
    };

    private Option alpha = new Option(strings.getString("alphaLabel"), 255);
    private Option hue = new Option(strings.getString("hueLabel"), 360);
    private Option sat = new Option(strings.getString("saturationLabel"), 100);
    private Option bri = new Option(strings.getString("brightnessLabel"), 100);
    private Option red = new Option(strings.getString("redLabel"), 255);
    private Option green = new Option(strings.getString("greenLabel"), 255);
    private Option blue = new Option(strings.getString("blueLabel"), 255);
    private ColorSwatch preview = new ColorSwatch(50);
    private JLabel hexLabel = new JLabel(strings.getString("hexLabel"));
    private JTextField hexField = new JTextField("000000");

    /**
     * Used to indicate when we're internally adjusting the value of the spinners. If this equals zero, then incoming
     * events are triggered by the user and must be processed. If this is not equal to zero, then incoming events are
     * triggered by another method that's already responding to the user's actions.
     */
    private int adjustingSpinners = 0;

    /**
     * Used to indicate when we're internally adjusting the value of the slider. If this equals zero, then incoming
     * events are triggered by the user and must be processed. If this is not equal to zero, then incoming events are
     * triggered by another method that's already responding to the user's actions.
     */
    private int adjustingSlider = 0;

    /**
     * Used to indicate when we're internally adjusting the selected color of the ColorPanel. If this equals zero, then
     * incoming events are triggered by the user and must be processed. If this is not equal to zero, then incoming
     * events are triggered by another method that's already responding to the user's actions.
     */
    private int adjustingColorPanel = 0;

    /**
     * Used to indicate when we're internally adjusting the value of the hex field. If this equals zero, then incoming
     * events are triggered by the user and must be processed. If this is not equal to zero, then incoming events are
     * triggered by another method that's already responding to the user's actions.
     */
    private int adjustingHexField = 0;

    /**
     * Used to indicate when we're internally adjusting the value of the opacity. If this equals zero, then incoming
     * events are triggered by the user and must be processed. If this is not equal to zero, then incoming events are
     * triggered by another method that's already responding to the user's actions.
     */
    private int adjustingOpacity = 0;

    /**
     * The "expert" controls are the controls on the right side of this panel: the labels/spinners/radio buttons.
     */
    private JPanel expertControls = new JPanel(new GridBagLayout());

    private ColorPickerPanel colorPanel = new ColorPickerPanel();

    private JSlider opacitySlider = new JSlider(0, 255, 255);
    private JLabel opacityLabel = new JLabel(strings.getString("opacityLabel"));

    /** Create a new <code>ColorPicker</code> with all controls visible except opacity. */
    public ColorPicker() {
        this(true, false);
    }

    /**
     * Create a new <code>ColorPicker</code>.
     * 
     * @param showExpertControls
     *            the labels/spinners/buttons on the right side of a <code>ColorPicker</code> are optional. This boolean
     *            will control whether they are shown or not.
     *            <P>
     *            It may be that your users will never need or want numeric control when they choose their colors, so
     *            hiding this may simplify your interface.
     * @param includeOpacity
     *            whether the opacity controls will be shown
     */
    public ColorPicker(boolean showExpertControls, boolean includeOpacity) {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Insets normalInsets = new Insets(3, 3, 3, 3);

        JPanel options = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = normalInsets;
        ButtonGroup bg = new ButtonGroup();

        // put them in order
        Option[] optionsArray = new Option[] {
                this.hue, this.sat, this.bri, this.red, this.green, this.blue
        };

        for (int a = 0; a < optionsArray.length; a++) {
            if ((a == 3) || (a == 6)) {
                c.insets = new Insets(normalInsets.top + 10, normalInsets.left, normalInsets.bottom, normalInsets.right);
            } else {
                c.insets = normalInsets;
            }
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.NONE;
            options.add(optionsArray[a].label, c);
            c.gridx++;
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.HORIZONTAL;
            if (optionsArray[a].spinner != null) {
                options.add(optionsArray[a].spinner, c);
            } else {
                options.add(optionsArray[a].slider, c);
            }
            c.gridx++;
            c.fill = GridBagConstraints.NONE;
            options.add(optionsArray[a].radioButton, c);
            c.gridy++;
            c.gridx = 0;
            bg.add(optionsArray[a].radioButton);
        }
        c.insets = new Insets(normalInsets.top + 10, normalInsets.left, normalInsets.bottom, normalInsets.right);
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        options.add(this.hexLabel, c);
        c.gridx++;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        options.add(this.hexField, c);
        c.gridy++;
        c.gridx = 0;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        options.add(this.alpha.label, c);
        c.gridx++;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        options.add(this.alpha.spinner, c);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = normalInsets;
        c.gridwidth = 2;
        add(this.colorPanel, c);

        c.gridwidth = 1;
        c.insets = normalInsets;
        c.gridx += 2;
        c.weighty = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0;
        add(this.slider, c);

        c.gridx++;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        add(this.expertControls, c);

        c.gridx = 0;
        c.gridheight = 1;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = normalInsets;
        c.anchor = GridBagConstraints.CENTER;
        add(this.opacityLabel, c);
        c.gridx++;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(this.opacitySlider, c);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.insets = new Insets(normalInsets.top, normalInsets.left + 8, normalInsets.bottom + 10, normalInsets.right + 8);
        this.expertControls.add(this.preview, c);
        c.gridy++;
        c.weighty = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(normalInsets.top, normalInsets.left, 0, normalInsets.right);
        this.expertControls.add(options, c);

        this.preview.setOpaque(true);
        this.colorPanel.setPreferredSize(new Dimension(this.expertControls.getPreferredSize().height,
                this.expertControls.getPreferredSize().height));

        this.slider.addChangeListener(this.changeListener);
        this.colorPanel.addChangeListener(this.changeListener);
        this.slider.setUI(new ColorPickerSliderUI(this.slider, this));
        this.hexField.getDocument().addDocumentListener(this.hexDocListener);
        setMode(BRI);

        setExpertControlsVisible(showExpertControls);

        setOpacityVisible(includeOpacity);

        this.opacitySlider.addChangeListener(this.changeListener);

        setOpacity(255);
        setOpaque(this, false);

        this.preview.setForeground(getColor());
    }

    private static void setOpaque(JComponent jc, boolean opaque) {
        if (jc instanceof JTextField) {
            return;
        }

        jc.setOpaque(false);
        if (jc instanceof JSpinner) {
            return;
        }

        for (int a = 0; a < jc.getComponentCount(); a++) {
            JComponent child = (JComponent) jc.getComponent(a);
            setOpaque(child, opaque);
        }
    }

    /**
     * This controls whether the hex field (and label) are visible or not.
     * <P>
     * Note this lives inside the "expert controls", so if <code>setExpertControlsVisible(false)</code> has been called,
     * then calling this method makes no difference: the hex controls will be hidden.
     */
    public void setHexControlsVisible(boolean b) {
        this.hexLabel.setVisible(b);
        this.hexField.setVisible(b);
    }

    /**
     * This controls whether the preview swatch visible or not.
     * <P>
     * Note this lives inside the "expert controls", so if <code>setExpertControlsVisible(false)</code> has been called,
     * then calling this method makes no difference: the swatch will be hidden.
     */
    public void setPreviewSwatchVisible(boolean b) {
        this.preview.setVisible(b);
    }

    /**
     * The labels/spinners/buttons on the right side of a <code>ColorPicker</code> are optional. This method will
     * control whether they are shown or not.
     * <P>
     * It may be that your users will never need or want numeric control when they choose their colors, so hiding this
     * may simplify your interface.
     * 
     * @param b
     *            whether to show or hide the expert controls.
     */
    public void setExpertControlsVisible(boolean b) {
        this.expertControls.setVisible(b);
    }

    /**
     * @return the current HSB coordinates of this <code>ColorPicker</code>. Each value is between [0,1].
     * 
     */
    public float[] getHSB() {
        return new float[] {
                this.hue.getFloatValue() / 360f,
                this.sat.getFloatValue() / 100f,
                this.bri.getFloatValue() / 100f
        };
    }

    /**
     * @return the current RGB coordinates of this <code>ColorPicker</code>. Each value is between [0,255].
     * 
     */
    public int[] getRGB() {
        return new int[] {
                this.currentRed,
                this.currentGreen,
                this.currentBlue
        };
    }

    /**
     * Returns the currently selected opacity (a float between 0 and 1).
     * 
     * @return the currently selected opacity (a float between 0 and 1).
     */
    public float getOpacity() {
        return (this.opacitySlider.getValue()) / 255f;
    }

    private int lastOpacity = 255;

    /**
     * Sets the currently selected opacity.
     * 
     * @param v
     *            an int between 0 and 255.
     */
    public void setOpacity(int v) {
        if ((v < 0) || (v > 255)) {
            throw new IllegalArgumentException("The opacity (" + v + ") must be between 0 and 255.");
        }
        this.adjustingOpacity++;
        try {
            this.opacitySlider.setValue(v);
            this.alpha.spinner.setValue(new Integer(v));
            if (this.lastOpacity != v) {
                firePropertyChange(OPACITY_PROPERTY, new Integer(this.lastOpacity), new Integer(v));
                Color c = this.preview.getForeground();
                this.preview.setForeground(new Color(c.getRed(), c.getGreen(), c.getBlue(), v));
            }
            this.lastOpacity = v;
        } finally {
            this.adjustingOpacity--;
        }
    }

    /**
     * Sets the mode of this <code>ColorPicker</code>. This is especially useful if this picker is in non-expert mode,
     * so the radio buttons are not visible for the user to directly select.
     * 
     * @param mode
     *            must be HUE, SAT, BRI, RED, GREEN or BLUE.
     */
    public void setMode(int mode) {
        if (!((mode == HUE) || (mode == SAT) || (mode == BRI) || (mode == RED) || (mode == GREEN) || (mode == BLUE))) {
            throw new IllegalArgumentException("mode must be HUE, SAT, BRI, REd, GREEN, or BLUE");
        }
        putClientProperty(MODE_PROPERTY, new Integer(mode));
        this.hue.radioButton.setSelected(mode == HUE);
        this.sat.radioButton.setSelected(mode == SAT);
        this.bri.radioButton.setSelected(mode == BRI);
        this.red.radioButton.setSelected(mode == RED);
        this.green.radioButton.setSelected(mode == GREEN);
        this.blue.radioButton.setSelected(mode == BLUE);

        this.colorPanel.setMode(mode);
        this.adjustingSlider++;
        try {
            this.slider.setValue(0);
            Option option = getSelectedOption();
            this.slider.setInverted(mode == HUE);
            int max = option.getMaximum();
            this.slider.setMaximum(max);
            this.slider.setValue(option.getIntValue());
            this.slider.repaint();

            if ((mode == HUE) || (mode == SAT) || (mode == BRI)) {
                setHSB(this.hue.getFloatValue() / 360f,
                        this.sat.getFloatValue() / 100f,
                        this.bri.getFloatValue() / 100f);
            } else {
                setRGB(this.red.getIntValue(),
                        this.green.getIntValue(),
                        this.blue.getIntValue());

            }
        } finally {
            this.adjustingSlider--;
        }
    }

    /**
     * This controls whether the radio buttons that adjust the mode are visible.
     * <P>
     * (These buttons appear next to the spinners in the expert controls.)
     * <P>
     * Note these live inside the "expert controls", so if <code>setExpertControlsVisible(false)</code> has been called,
     * then these will never be visible.
     * 
     * @param b
     */
    public void setModeControlsVisible(boolean b) {
        this.hue.radioButton.setVisible(b && this.hue.isVisible());
        this.sat.radioButton.setVisible(b && this.sat.isVisible());
        this.bri.radioButton.setVisible(b && this.bri.isVisible());
        this.red.radioButton.setVisible(b && this.red.isVisible());
        this.green.radioButton.setVisible(b && this.green.isVisible());
        this.blue.radioButton.setVisible(b && this.blue.isVisible());
        putClientProperty(MODE_CONTROLS_VISIBLE_PROPERTY, new Boolean(b));
    }

    /**
     * @return the current mode of this <code>ColorPicker</code>. <BR>
     *         This will return <code>HUE</code>, <code>SAT</code>, <code>BRI</code>, <code>RED</code>,
     *         <code>GREEN</code>, or <code>BLUE</code>.
     *         <P>
     *         The default mode is <code>BRI</code>, because that provides the most aesthetic/recognizable color wheel.
     */
    public int getMode() {
        Integer i = (Integer) getClientProperty(MODE_PROPERTY);
        if (i == null) {
            return -1;
        }
        return i.intValue();
    }

    /**
     * Sets the current color of this <code>ColorPicker</code>. This method simply calls <code>setRGB()</code> and
     * <code>setOpacity()</code>.
     * 
     * @param c
     *            the new color to use.
     */
    public void setColor(Color c) {
        setRGB(c.getRed(), c.getGreen(), c.getBlue());
        setOpacity(c.getAlpha());
    }

    /**
     * Sets the current color of this <code>ColorPicker</code>
     * 
     * @param r
     *            the red value. Must be between [0,255].
     * @param g
     *            the green value. Must be between [0,255].
     * @param b
     *            the blue value. Must be between [0,255].
     */
    public void setRGB(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            throw new IllegalArgumentException("The red value (" + r + ") must be between [0,255].");
        }
        if ((g < 0) || (g > 255)) {
            throw new IllegalArgumentException("The green value (" + g + ") must be between [0,255].");
        }
        if ((b < 0) || (b > 255)) {
            throw new IllegalArgumentException("The blue value (" + b + ") must be between [0,255].");
        }

        Color lastColor = getColor();

        boolean updateRGBSpinners = this.adjustingSpinners == 0;

        this.adjustingSpinners++;
        this.adjustingColorPanel++;
        int alpha = this.alpha.getIntValue();
        try {
            if (updateRGBSpinners) {
                this.red.setValue(r);
                this.green.setValue(g);
                this.blue.setValue(b);
            }
            this.preview.setForeground(new Color(r, g, b, alpha));
            float[] hsb = new float[3];
            Color.RGBtoHSB(r, g, b, hsb);
            this.hue.setValue((int) ((hsb[0] * 360f) + .49f));
            this.sat.setValue((int) ((hsb[1] * 100f) + .49f));
            this.bri.setValue((int) ((hsb[2] * 100f) + .49f));
            this.colorPanel.setRGB(r, g, b);
            updateHexField();
            updateSlider();
        } finally {
            this.adjustingSpinners--;
            this.adjustingColorPanel--;
        }
        this.currentRed = r;
        this.currentGreen = g;
        this.currentBlue = b;
        Color newColor = getColor();
        if (lastColor.equals(newColor) == false) {
            firePropertyChange(SELECTED_COLOR_PROPERTY, lastColor, newColor);
        }
    }

    /**
     * @return the current <code>Color</code> this <code>ColorPicker</code> has selected.
     *         <P>
     *         This is equivalent to: <BR>
     *         <code>int[] i = getRGB();</code> <BR>
     *         <code>return new Color(i[0], i[1], i[2], opacitySlider.getValue());</code>
     */
    public Color getColor() {
        int[] i = getRGB();
        return new Color(i[0], i[1], i[2], this.opacitySlider.getValue());
    }

    private void updateSlider() {
        this.adjustingSlider++;
        try {
            int mode = getMode();
            if (mode == HUE) {
                this.slider.setValue(this.hue.getIntValue());
            } else if (mode == SAT) {
                this.slider.setValue(this.sat.getIntValue());
            } else if (mode == BRI) {
                this.slider.setValue(this.bri.getIntValue());
            } else if (mode == RED) {
                this.slider.setValue(this.red.getIntValue());
            } else if (mode == GREEN) {
                this.slider.setValue(this.green.getIntValue());
            } else if (mode == BLUE) {
                this.slider.setValue(this.blue.getIntValue());
            }
        } finally {
            this.adjustingSlider--;
        }
        this.slider.repaint();
    }

    /**
     * This returns the panel with several rows of spinner controls.
     * <P>
     * Note you can also call methods such as <code>setRGBControlsVisible()</code> to adjust which controls are showing.
     * <P>
     * (This returns the panel this <code>ColorPicker</code> uses, so if you put it in another container, it will be
     * removed from this <code>ColorPicker</code>.)
     * 
     * @return the panel with several rows of spinner controls.
     */
    public JPanel getExpertControls() {
        return this.expertControls;
    }

    /**
     * This shows or hides the RGB spinner controls.
     * <P>
     * Note these live inside the "expert controls", so if <code>setExpertControlsVisible(false)</code> has been called,
     * then calling this method makes no difference: the RGB controls will be hidden.
     * 
     * @param b
     *            whether the controls should be visible or not.
     */
    public void setRGBControlsVisible(boolean b) {
        this.red.setVisible(b);
        this.green.setVisible(b);
        this.blue.setVisible(b);
    }

    /**
     * This shows or hides the HSB spinner controls.
     * <P>
     * Note these live inside the "expert controls", so if <code>setExpertControlsVisible(false)</code> has been called,
     * then calling this method makes no difference: the HSB controls will be hidden.
     * 
     * @param b
     *            whether the controls should be visible or not.
     */
    public void setHSBControlsVisible(boolean b) {
        this.hue.setVisible(b);
        this.sat.setVisible(b);
        this.bri.setVisible(b);
    }

    /**
     * This shows or hides the alpha controls.
     * <P>
     * Note the alpha spinner live inside the "expert controls", so if <code>setExpertControlsVisible(false)</code> has
     * been called, then this method does not affect that spinner. However, the opacity slider is <i>not</i> affected by
     * the visibility of the export controls.
     * 
     * @param b
     */
    public void setOpacityVisible(boolean b) {
        this.opacityLabel.setVisible(b);
        this.opacitySlider.setVisible(b);
        this.alpha.label.setVisible(b);
        this.alpha.spinner.setVisible(b);
    }

    /** @return the <code>ColorPickerPanel</code> this <code>ColorPicker</code> displays. */
    public ColorPickerPanel getColorPanel() {
        return this.colorPanel;
    }

    /**
     * Sets the current color of this <code>ColorPicker</code>
     * 
     * @param h
     *            the hue value.
     * @param s
     *            the saturation value. Must be between [0,1].
     * @param b
     *            the blue value. Must be between [0,1].
     */
    public void setHSB(float h, float s, float b) {
        if (Float.isInfinite(h) || Float.isNaN(h)) {
            throw new IllegalArgumentException("The hue value (" + h + ") is not a valid number.");
        }
        // hue is cyclic, so it can be any value:
        while (h < 0) {
            h++;
        }
        while (h > 1) {
            h--;
        }

        if ((s < 0) || (s > 1)) {
            throw new IllegalArgumentException("The saturation value (" + s + ") must be between [0,1]");
        }
        if ((b < 0) || (b > 1)) {
            throw new IllegalArgumentException("The brightness value (" + b + ") must be between [0,1]");
        }

        Color lastColor = getColor();

        boolean updateHSBSpinners = this.adjustingSpinners == 0;
        this.adjustingSpinners++;
        this.adjustingColorPanel++;
        try {
            if (updateHSBSpinners) {
                this.hue.setValue((int) ((h * 360f) + .49f));
                this.sat.setValue((int) ((s * 100f) + .49f));
                this.bri.setValue((int) ((b * 100f) + .49f));
            }

            Color c = new Color(Color.HSBtoRGB(h, s, b));
            int alpha = this.alpha.getIntValue();
            c = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
            this.preview.setForeground(c);
            this.currentRed = c.getRed();
            this.currentGreen = c.getGreen();
            this.currentBlue = c.getBlue();
            this.red.setValue(this.currentRed);
            this.green.setValue(this.currentGreen);
            this.blue.setValue(this.currentBlue);
            this.colorPanel.setHSB(h, s, b);
            updateHexField();
            updateSlider();
            this.slider.repaint();
        } finally {
            this.adjustingSpinners--;
            this.adjustingColorPanel--;
        }
        Color newColor = getColor();
        if (lastColor.equals(newColor) == false) {
            firePropertyChange(SELECTED_COLOR_PROPERTY, lastColor, newColor);
        }
    }

    private void updateHexField() {
        this.adjustingHexField++;
        try {
            int r = this.red.getIntValue();
            int g = this.green.getIntValue();
            int b = this.blue.getIntValue();

            int i = (r << 16) + (g << 8) + b;
            String s = Integer.toHexString(i).toUpperCase();
            while (s.length() < 6) {
                s = "0" + s;
            }
            if (this.hexField.getText().equalsIgnoreCase(s) == false) {
                this.hexField.setText(s);
            }
        } finally {
            this.adjustingHexField--;
        }
    }

    class Option {
        JRadioButton radioButton = new JRadioButton();
        JSpinner spinner;
        JSlider slider;
        JLabel label;

        public Option(String text, int max) {
            this.spinner = new JSpinner(new SpinnerNumberModel(0, 0, max, 5));
            this.spinner.addChangeListener(ColorPicker.this.changeListener);

            /*
             * this tries out Tim Boudreaux's new slider UI. It's a good UI, but I think for the ColorPicker the numeric
             * controls are more useful. That is: users who want click-and-drag control to choose their colors don't
             * need any of these Option objects at all; only power users who may have specific RGB values in mind will
             * use these controls: and when they do limiting them to a slider is unnecessary. That's my current
             * position... of course it may not be true in the real world... :)
             */
            // slider = new JSlider(0,max);
            // slider.addChangeListener(changeListener);
            // slider.setUI(new org.netbeans.paint.api.components.PopupSliderUI());

            this.label = new JLabel(text);
            this.radioButton.addActionListener(ColorPicker.this.actionListener);
        }

        public void setValue(int i) {
            if (this.slider != null) {
                this.slider.setValue(i);
            }
            if (this.spinner != null) {
                this.spinner.setValue(new Integer(i));
            }
        }

        public int getMaximum() {
            if (this.slider != null) {
                return this.slider.getMaximum();
            }
            return ((Number) ((SpinnerNumberModel) this.spinner.getModel()).getMaximum()).intValue();
        }

        public boolean contains(Object src) {
            return ((src == this.slider) || (src == this.spinner) || (src == this.radioButton) || (src == this.label));
        }

        public float getFloatValue() {
            return getIntValue();
        }

        public int getIntValue() {
            if (this.slider != null) {
                return this.slider.getValue();
            }
            return ((Number) this.spinner.getValue()).intValue();
        }

        public boolean isVisible() {
            return this.label.isVisible();
        }

        public void setVisible(boolean b) {
            boolean radioButtonsAllowed = true;
            Boolean z = (Boolean) getClientProperty(MODE_CONTROLS_VISIBLE_PROPERTY);
            if (z != null) {
                radioButtonsAllowed = z.booleanValue();
            }

            this.radioButton.setVisible(b && radioButtonsAllowed);
            if (this.slider != null) {
                this.slider.setVisible(b);
            }
            if (this.spinner != null) {
                this.spinner.setVisible(b);
            }
            this.label.setVisible(b);
        }
    }
}
