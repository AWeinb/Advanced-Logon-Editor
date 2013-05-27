/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.previewPanel.elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ale.Constants;
import ale.model.skin.BrandingVO.Brandingsize;
import ale.model.skin.SkinConstants.Position;
import ale.model.skin.SkinPropertiesVO;

public class Branding extends PreviewElement {

    private static final long serialVersionUID = 1L;
    private SkinPropertiesVO skin;
    private JPanel parent;
    private BufferedImage branding;

    public Branding(SkinPropertiesVO skin, JPanel parent) {
        if (skin == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Wrong parameter!");
            throw iae;
        }

        this.skin = skin;
        this.parent = parent;
    }

    @Override
    public void paintComponents(Graphics g) {
        if (!this.skin.getBrandingPosition().equals(Position.NONE)) {
            Graphics g2 = g.create();

            int x, y;
            int w, h;
            Rectangle bounds = this.parent.getBounds();

            if (this.skin.isChanged() || (this.branding == null)) {
                try {
                    String p = this.skin.getBranding().getImage(Brandingsize.SMALL).toString()
                            .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                    this.branding = ImageIO.read(new File(p));
                } catch (IOException e) {
                }
            }

            w = this.branding.getWidth();
            h = this.skin.getOptionsbarHeight() > this.branding.getHeight() ? this.branding.getHeight() : this.skin.getOptionsbarHeight();

            int spacing = 20;
            x = (bounds.width / 2) - (this.branding.getWidth() / 2);
            y = bounds.height - h - spacing;

            g2.drawImage(this.branding, x, y, w, h, null);
        }
    }
}
