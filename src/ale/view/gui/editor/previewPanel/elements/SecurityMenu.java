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

public class SecurityMenu extends PreviewElement {

    private static final long serialVersionUID = 1L;
    private SkinPropertiesVO skin;
    private JPanel parent;
    private BufferedImage ground;
    private BufferedImage symbol;

    public SecurityMenu(SkinPropertiesVO skin, JPanel parent) {
        if (skin == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Wrong parameter!");
            throw iae;
        }

        this.skin = skin;
        this.parent = parent;
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics g2 = g.create();

        int x, y;
        int w, h;
        Rectangle bounds = this.parent.getBounds();

        if (this.skin.isChanged() || (this.ground == null) || (this.symbol == null)) {
            try {
                String p = this.skin.getImgPath_CommandBtn(Imagetype.DEFAULT).toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.ground = ImageIO.read(new File(p));

                p = this.skin.getImgPath_CommandBtnArrow(Imagetype.DEFAULT).toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.symbol = ImageIO.read(new File(p));
            } catch (IOException e) {
            }
        }

        w = this.skin.getCommandButtonWidth();
        h = this.skin.getCommandButtonHeight();

        int eps = 10;
        x = (bounds.width / 2) - (w / 2);
        y = (bounds.height / 2) - (3 * (h + eps));

        for (int i = 0; i < 6; i++) {
            g2.setClip(null);
            g2.drawImage(this.ground, x, y, w, h, null);
            g2.setClip(x, y, w, h);
            g2.drawImage(this.symbol, x + 5, (y + (h / 2)) - (this.symbol.getHeight() / 2), null);

            y += h + eps;
        }
    }
}
