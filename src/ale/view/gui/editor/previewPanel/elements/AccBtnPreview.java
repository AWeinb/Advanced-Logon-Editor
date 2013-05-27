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
import ale.model.skin.SkinConstants.Position;
import ale.model.skin.SkinPropertiesVO;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.editor.previewPanel.elements <br/>
 * Class  : AccBtn <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>AccBtn</code> class contains the accessibilty preview.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 17.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class AccBtnPreview extends PreviewElement {

    private static final long serialVersionUID = 1L;
    private SkinPropertiesVO skin;
    private JPanel parent;
    private BufferedImage ground;
    private BufferedImage symbol;

    /**
     * @param skinproperties the skin layout
     * @param parent parent component
     */
    public AccBtnPreview(SkinPropertiesVO skinproperties, JPanel parent) {
        if (skinproperties == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Wrong parameter!");
            throw iae;
        }

        this.skin = skinproperties;
        this.parent = parent;
    }

    public void shutdown() {
        this.skin = null;
        this.parent = null;
        this.ground = null;
        this.symbol = null;
    }

    @Override
    public void paintComponents(Graphics g) {
        if (!this.skin.getAccButtonPosition().equals(Position.NONE)) {
            Graphics g2 = g.create();

            int x, y;
            int w, h;
            Rectangle bounds = this.parent.getBounds();

            if (this.skin.isChanged() || (this.ground == null) || (this.symbol == null)) {
                try {
                    String p = this.skin.getImgPath_StandardBtn(Imagetype.DEFAULT).toString()
                            .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                    this.ground = ImageIO.read(new File(p));
                    p = this.skin.getImgPath_AccessSym().toString()
                            .replaceFirst(Constants.DEFAULT_SKINIMAGE_TYPE, Constants.DEFAULT_INPUTIMAGE_TYPE);
                    this.symbol = ImageIO.read(new File(p));
                } catch (IOException e) {
                    ;
                }
            }

            w = this.skin.getAccButtonWidth();
            h = this.skin.getAccButtonHeight();

            x = 30;
            y = bounds.height - h - 30;

            g2.drawImage(this.ground, x, y, w, h, null);

            g2.setClip(x, y, w, h);
            x += (w / 2) - (this.symbol.getWidth() / 2);

            g2.drawImage(this.symbol, x, y + 2, null);
        }
    }
}
