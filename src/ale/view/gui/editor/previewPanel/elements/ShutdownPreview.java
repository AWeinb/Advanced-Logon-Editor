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
import ale.model.skin.SkinConstants.Imagetype;
import ale.model.skin.SkinPropertiesVO;

public class ShutdownPreview extends PreviewElement {

    private static final long serialVersionUID = 1L;
    private SkinPropertiesVO skin;
    private JPanel parent;
    private BufferedImage btn;
    private BufferedImage menu;
    private BufferedImage btnSym;
    private BufferedImage menuSym;

    public ShutdownPreview(SkinPropertiesVO skin, JPanel parent) {
        if (skin == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Wrong parameter!");
            throw iae;
        }

        this.skin = skin;
        this.parent = parent;
    }

    public void shutdown() {
        this.skin = null;
        this.parent = null;
        this.btn = null;
        this.menu = null;
        this.btnSym = null;
        this.menuSym = null;
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics g2 = g.create();

        int x, y;
        int w, h;
        Rectangle bounds = this.parent.getBounds();

        if (this.skin.isChanged() || (this.btn == null) || (this.menu == null) || (this.btnSym == null) || (this.menuSym == null)) {
            try {
                String p = this.skin.getImgPath_ShutdownBtn(Imagetype.DEFAULT).toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.btn = ImageIO.read(new File(p));
                p = this.skin.getImgPath_ShutdownMenu(Imagetype.DEFAULT).toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.menu = ImageIO.read(new File(p));

                p = this.skin.getImgPath_ShutdownSym().toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.btnSym = ImageIO.read(new File(p));
                p = this.skin.getImgPath_ShutdownArrowSym().toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.menuSym = ImageIO.read(new File(p));
            } catch (IOException e) {
                ;
            }
        }

        w = this.skin.getShutdownmenuButtonWidth();
        h = this.skin.getShutdownmenuButtonHeight() > this.skin.getShutdownButtonHeight() ? this.skin.getShutdownmenuButtonHeight()
                : this.skin
                .getShutdownButtonHeight();

        x = bounds.width - w - 30;
        y = bounds.height - h - 30;

        g2.drawImage(this.menu, x, y, w, h, null);
        g2.setClip(x, y, w, h);
        g2.drawImage(this.menuSym, (x + (w / 2)) - (this.menuSym.getWidth() / 2), (y + (h / 2)) - (this.menuSym.getHeight() / 2), null);

        g2.setClip(null);
        w = this.skin.getShutdownButtonWidth();

        x -= w;

        g2.drawImage(this.btn, x, y, w, h, null);
        g2.setClip(x, y, w, h);
        g2.drawImage(this.btnSym, (x + (w / 2)) - (this.btnSym.getWidth() / 2), (y + (h / 2)) - (this.btnSym.getHeight() / 2), null);
    }
}
