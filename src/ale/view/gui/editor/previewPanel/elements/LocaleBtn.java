/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.previewPanel.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ale.Constants;
import ale.model.skin.SkinConstants.Imagetype;
import ale.model.skin.SkinPropertiesVO;

public class LocaleBtn extends PreviewElement {

    private static final long serialVersionUID = 1L;
    private SkinPropertiesVO skin;
    // private JPanel parent;

    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    private BufferedImage ground;

    public LocaleBtn(SkinPropertiesVO skin, JPanel parent) {
        if (skin == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Wrong parameter!");
            throw iae;
        }

        this.skin = skin;
        // this.parent = parent;
    }

    @Override
    public void paintComponents(Graphics g) {
        if (this.skin.getLocaleButtonVisible()) {
            Graphics g2 = g.create();

            int x, y;
            int w, h;

            if (this.skin.isChanged() || (this.ground == null)) {
                try {
                    String p = this.skin.getImgPath_StandardBtn(Imagetype.DEFAULT).toString()
                            .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                    this.ground = ImageIO.read(new File(p));
                } catch (IOException e) {
                }
            }

            w = 32;
            h = 28;

            x = 10;
            y = 10;

            g2.drawImage(this.ground, x, y, w, h, null);
            g2.setColor(Color.WHITE);
            g2.setFont(this.font);
            g2.drawString("EN", x + 7, y + 18);
        }
    }
}