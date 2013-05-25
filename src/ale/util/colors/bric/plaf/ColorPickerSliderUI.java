/*
 * @(#)ColorPickerSliderUI.java
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
package ale.util.colors.bric.plaf;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.basic.BasicSliderUI;

import ale.util.colors.bric.swing.ColorPicker;
import ale.util.colors.bric.swing.ColorPickerPanel;

/**
 * This is a SliderUI designed specifically for the <code>ColorPicker</code>.
 * 
 */
public class ColorPickerSliderUI extends BasicSliderUI {
    ColorPicker colorPicker;

    /** Half of the height of the arrow */
    int ARROW_HALF = 8;

    int[] intArray = new int[Toolkit.getDefaultToolkit().getScreenSize().height];
    BufferedImage bi = new BufferedImage(1, this.intArray.length, BufferedImage.TYPE_INT_RGB);
    int lastMode = -1;

    public ColorPickerSliderUI(JSlider b, ColorPicker cp) {
        super(b);
        this.colorPicker = cp;
        cp.getColorPanel().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ColorPickerSliderUI.this.calculateGeometry();
                ColorPickerSliderUI.this.slider.repaint();
            }
        });
    }

    @Override
    public void paintThumb(Graphics g) {
        int y = this.thumbRect.y + (this.thumbRect.height / 2);
        Polygon polygon = new Polygon();
        polygon.addPoint(0, y - this.ARROW_HALF);
        polygon.addPoint(this.ARROW_HALF, y);
        polygon.addPoint(0, y + this.ARROW_HALF);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.black);
        g2.fill(polygon);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(1));
        g2.draw(polygon);
    }

    @Override
    protected void calculateThumbSize() {
        super.calculateThumbSize();
        this.thumbRect.height += 4;
        this.thumbRect.y -= 2;
    }

    @Override
    protected void calculateTrackRect() {
        super.calculateTrackRect();
        ColorPickerPanel cp = this.colorPicker.getColorPanel();
        int size = Math.min(ColorPickerPanel.MAX_SIZE, Math.min(cp.getWidth(), cp.getHeight()));
        int max = this.slider.getHeight() - (this.ARROW_HALF * 2) - 2;
        if (size > max) {
            size = max;
        }
        this.trackRect.y = (this.slider.getHeight() / 2) - (size / 2);
        this.trackRect.height = size;
    }

    @Override
    public synchronized void paintTrack(Graphics g) {
        int mode = this.colorPicker.getMode();
        if ((mode == ColorPicker.HUE) || (mode == ColorPicker.BRI) || (mode == ColorPicker.SAT)) {
            float[] hsb = this.colorPicker.getHSB();
            if (mode == ColorPicker.HUE) {
                for (int y = 0; y < this.trackRect.height; y++) {
                    float hue = ((float) y) / ((float) this.trackRect.height);
                    this.intArray[y] = Color.HSBtoRGB(hue, 1, 1);
                }
            } else if (mode == ColorPicker.SAT) {
                for (int y = 0; y < this.trackRect.height; y++) {
                    float sat = 1 - (((float) y) / ((float) this.trackRect.height));
                    this.intArray[y] = Color.HSBtoRGB(hsb[0], sat, hsb[2]);
                }
            } else {
                for (int y = 0; y < this.trackRect.height; y++) {
                    float bri = 1 - (((float) y) / ((float) this.trackRect.height));
                    this.intArray[y] = Color.HSBtoRGB(hsb[0], hsb[1], bri);
                }
            }
        } else {
            int[] rgb = this.colorPicker.getRGB();
            if (mode == ColorPicker.RED) {
                for (int y = 0; y < this.trackRect.height; y++) {
                    int red = 255 - (int) (((y * 255) / this.trackRect.height) + .49);
                    this.intArray[y] = (red << 16) + (rgb[1] << 8) + (rgb[2]);
                }
            } else if (mode == ColorPicker.GREEN) {
                for (int y = 0; y < this.trackRect.height; y++) {
                    int green = 255 - (int) (((y * 255) / this.trackRect.height) + .49);
                    this.intArray[y] = (rgb[0] << 16) + (green << 8) + (rgb[2]);
                }
            } else if (mode == ColorPicker.BLUE) {
                for (int y = 0; y < this.trackRect.height; y++) {
                    int blue = 255 - (int) (((y * 255) / this.trackRect.height) + .49);
                    this.intArray[y] = (rgb[0] << 16) + (rgb[1] << 8) + (blue);
                }
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        Rectangle r = new Rectangle(6, this.trackRect.y, 14, this.trackRect.height);
        if (this.slider.hasFocus()) {
            PlafPaintUtils.paintFocus(g2, r, 3);
        }

        this.bi.getRaster().setDataElements(0, 0, 1, this.trackRect.height, this.intArray);
        TexturePaint p = new TexturePaint(this.bi, new Rectangle(0, this.trackRect.y, 1, this.bi.getHeight()));
        g2.setPaint(p);
        g2.fillRect(r.x, r.y, r.width, r.height);

        PlafPaintUtils.drawBevel(g2, r);
    }

    @Override
    public void paintFocus(Graphics g) {
    }

    /**
     * This overrides the default behavior for this slider and sets the thumb to where the user clicked. From a design
     * standpoint, users probably don't want to scroll through several colors to get where they clicked: they simply
     * want the color they selected.
     */
    MouseInputAdapter myMouseListener = new MouseInputAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            ColorPickerSliderUI.this.slider.setValueIsAdjusting(true);
            updateSliderValue(e);
        }

        private void updateSliderValue(MouseEvent e) {
            int v;
            if (ColorPickerSliderUI.this.slider.getOrientation() == SwingConstants.HORIZONTAL) {
                int x = e.getX();
                v = valueForXPosition(x);
            } else {
                int y = e.getY();
                v = valueForYPosition(y);
            }
            ColorPickerSliderUI.this.slider.setValue(v);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            updateSliderValue(e);
            ColorPickerSliderUI.this.slider.setValueIsAdjusting(false);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            updateSliderValue(e);
        }
    };

    @Override
    protected void installListeners(JSlider slider) {
        super.installListeners(slider);
        slider.removeMouseListener(this.trackListener);
        slider.removeMouseMotionListener(this.trackListener);
        slider.addMouseListener(this.myMouseListener);
        slider.addMouseMotionListener(this.myMouseListener);
        slider.setOpaque(false);
    }

    @Override
    protected void uninstallListeners(JSlider slider) {
        super.uninstallListeners(slider);
        slider.removeMouseListener(this.myMouseListener);
        slider.removeMouseMotionListener(this.myMouseListener);
    }

}
