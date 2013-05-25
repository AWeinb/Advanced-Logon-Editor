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

public class Usertile extends PreviewElement {

    private static final long serialVersionUID = 1L;
    private SkinPropertiesVO skin;
    private JPanel parent;
    private BufferedImage border;
    private BufferedImage user;
    private BufferedImage pw;

    public Usertile(SkinPropertiesVO skin, JPanel parent) {
        if ((skin == null) || (parent == null)) {
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

        if (this.skin.isChanged() || (this.border == null) || (this.user == null) || (this.pw == null)) {
            try {
                String p = this.skin.getImgPath_UsertileImage().toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.border = ImageIO.read(new File(p));

                p = Constants.EDITOR_USERIMG.toString().replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.user = ImageIO.read(new File(p));

                p = this.skin.getImgPath_PWBtn(Imagetype.DEFAULT).toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.pw = ImageIO.read(new File(p));
            } catch (IOException e) {
            }
        }

        w = this.skin.getUsertileImageFrameWidth();
        h = this.skin.getUsertileImageFrameHeight();

        x = (bounds.width / 2) - (w / 2);
        y = (bounds.height / 2) - (h / 2);
        int[] padding = this.skin.getUsertileImagePadding();

        g2.drawImage(this.user, x + padding[0], y + padding[1], w - padding[0] - padding[2], h - padding[1] - padding[3], null);
        g2.drawImage(this.border, x, y, w, h, null);

        int spacing = 30;
        y += w + spacing;

        w = this.skin.getPasswordButtonWidth();
        h = this.skin.getPasswordButtonHeight();

        x = (bounds.width / 2) - (w / 2);

        g2.drawImage(this.pw, x, y, w, h, null);
    }
}
