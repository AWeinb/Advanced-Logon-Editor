/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.previewPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import ale.controller.Main;
import ale.model.skin.SkinConstants;
import ale.model.skin.SkinPropertiesVO;
import ale.view.gui.GUIConstants;
import ale.view.gui.editor.previewPanel.elements.AccBtnPreview;
import ale.view.gui.editor.previewPanel.elements.BrandingPreview;
import ale.view.gui.editor.previewPanel.elements.LocaleBtnPreview;
import ale.view.gui.editor.previewPanel.elements.SecurityMenuPreview;
import ale.view.gui.editor.previewPanel.elements.ShutdownPreview;
import ale.view.gui.editor.previewPanel.elements.UserlistPreview;
import ale.view.gui.editor.previewPanel.elements.UsertilePreview;
import ale.view.gui.util.GUIStrings;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.editor.previewPanel <br/>
 * Class  : PreviewPanel <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>PreviewPanel</code> class represents the center of the editor. It is not finished. TODO  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 24.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class PreviewPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private AccBtnPreview btnAccPreview;
    private LocaleBtnPreview btnLocalePreview;
    private ShutdownPreview shutdownPreview;
    private BrandingPreview brdPreview;
    private SecurityMenuPreview secPreview;
    private UserlistPreview userlistPreview;
    private UsertilePreview usertilePreview;

    private SkinPropertiesVO skin;

    private boolean scaleBg;
    private boolean showUserlist = true;
    private boolean showUsertile = false;

    private Timer timer;
    private short counter = 0;
    private BufferedImage backgroundImage;
    private Thread readThread;
    private boolean firstStart = true;

    /**
     * @param skinproperties the skin layout definition object.
     */
    public PreviewPanel(final SkinPropertiesVO skinproperties) {
        if (skinproperties == null) {
            throw new IllegalArgumentException();
        }
        this.skin = skinproperties;

        this.btnAccPreview = new AccBtnPreview(skinproperties, this);
        this.btnLocalePreview = new LocaleBtnPreview(skinproperties, this);
        this.shutdownPreview = new ShutdownPreview(skinproperties, this);
        this.brdPreview = new BrandingPreview(skinproperties, this);
        this.secPreview = new SecurityMenuPreview(skinproperties, this);
        this.userlistPreview = new UserlistPreview(skinproperties, this);
        this.usertilePreview = new UsertilePreview(skinproperties, this);
    }

    /**
     * Lets the preview show the usertile.
     * 
     */
    public void showUsertile() {
        this.showUsertile = true;
        this.showUserlist = false;
        this.repaint();
    }

    /**
     * Lets the preview show the userlist.
     * 
     */
    public void showUserlist() {
        this.showUserlist = true;
        this.showUsertile = false;
        this.repaint();
    }

    /**
     * Lets the preview show the security menu.
     * 
     */
    public void showSecurityMenu() {
        this.showUserlist = false;
        this.showUsertile = false;
        this.repaint();
    }

    /**
     * Sets the image scaling for the preview panel background.
     *
     * @param scale boolean
     */
    public void scaleBackgroundImage(boolean scale) {
        this.scaleBg = scale;
    }

    /**
     * 
     * 
     */
    public void startRepaintTimer() {
        if (this.timer == null) {
            if ((this.readThread == null) || !this.readThread.isAlive()) {
                createBackgroundThread();
                this.readThread.start();
            }

            this.timer = new Timer(GUIConstants.REPAINTTICK, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
        }

        this.timer.start();
    }

    /**
     *
     * 
     */
    public void shutdown() {
        if (this.timer != null) {
            this.timer.stop();
            this.readThread.interrupt();
            try {
                this.readThread.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.timer = null;
        this.backgroundImage = null;
        this.skin = null;
        this.btnAccPreview.shutdown();
        this.btnLocalePreview.shutdown();
        this.shutdownPreview.shutdown();
        this.brdPreview.shutdown();
        this.secPreview.shutdown();
        this.userlistPreview.shutdown();
        this.usertilePreview.shutdown();
        this.btnAccPreview = null;
        this.btnLocalePreview = null;
        this.shutdownPreview = null;
        this.brdPreview = null;
        this.secPreview = null;
        this.userlistPreview = null;
        this.usertilePreview = null;

        Runtime.getRuntime().gc();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        if (this.backgroundImage == null) {
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g2.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
            g2.drawString(GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_PREVIEWADJUST), (g.getClipBounds().width / 2) - 100,
                    g.getClipBounds().height / 2);
            return;
        }

        if (this.scaleBg) {
            g2.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.drawImage(this.backgroundImage, 0, 0, null);
        }

        {
            this.brdPreview.paintComponents(g);
            this.btnAccPreview.paintComponents(g);
            this.shutdownPreview.paintComponents(g);
            this.btnLocalePreview.paintComponents(g);
            if (this.showUserlist) {
                this.userlistPreview.paintComponents(g);
            } else if (this.showUsertile) {
                this.usertilePreview.paintComponents(g);
            } else {
                this.secPreview.paintComponents(g);
            }
        }

        if ((this.counter % 2) == 0) {
            g.setColor(GUIConstants.WARNING_BG);
            g.drawOval(1, 1, 5, 5);
            this.counter = 0;
        }
        this.counter++;
    }

    private void createBackgroundThread() {
        this.readThread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    if (PreviewPanel.this.skin.isChanged() || PreviewPanel.this.firstStart) {
                        PreviewPanel.this.firstStart = false;

                        try {
                            if (PreviewPanel.this.skin.getBackgroundImgChanged() && (PreviewPanel.this.skin.getBackground() != null)) {
                                PreviewPanel.this.backgroundImage = ImageIO.read(PreviewPanel.this.skin.getBackground().toFile());
                            } else {
                                PreviewPanel.this.backgroundImage = ImageIO.read(SkinConstants.UIDefaultImagePaths.BACKGROUND_PREVIEW
                                        .getPath().toFile());
                            }
                        } catch (IOException e) {
                            Main.getLogger().error("IOExc in the backgroundimage reader thread.", e);
                        }
                    }

                    try {
                        sleep(GUIConstants.REPAINTTICK);
                    } catch (InterruptedException e) {
                        interrupt();
                    }
                }
            }
        };
    }
}
