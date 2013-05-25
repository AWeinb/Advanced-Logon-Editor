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

public class Userlist extends PreviewElement {

    private static final long serialVersionUID = 1L;
    private SkinPropertiesVO skin;
    private JPanel parent;
    private BufferedImage border;
    private BufferedImage user;
    private BufferedImage guest;

    public Userlist(SkinPropertiesVO skin, JPanel parent) {
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

        if (this.skin.isChanged() || (this.border == null) || (this.user == null) || (this.guest == null)) {
            try {
                String p = this.skin.getImgPath_UserlistImage(Imagetype.DEFAULT).toString()
                        .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                this.border = ImageIO.read(new File(p));

                p = Constants.EDITOR_USERIMG.toString();
                this.user = ImageIO.read(new File(p));

                p = Constants.EDITOR_GUESTIMG.toString();
                this.guest = ImageIO.read(new File(p));
            } catch (IOException e) {
            }
        }

        w = this.skin.getUserlistImageFrameWidth();
        h = this.skin.getUserlistImageFrameHeight();

        int[] padding = this.skin.getUserlistImagePadding();

        int spacing = 40;
        x = (bounds.width / 2) - w - spacing;
        y = (bounds.height / 2) + 50;

        g2.drawImage(this.user, x + padding[0], y + padding[1], w - padding[0] - padding[2], h - padding[1] - padding[3], null);
        g2.drawImage(this.border, x, y, w, h, null);

        x += w + (2 * spacing);

        g2.drawImage(this.guest, x + padding[0], y + padding[1], w - padding[0] - padding[2], h - padding[1] - padding[3], null);
        g2.drawImage(this.border, x, y, w, h, null);
    }
}
