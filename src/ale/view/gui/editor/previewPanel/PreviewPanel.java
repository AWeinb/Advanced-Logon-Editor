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
import ale.view.gui.editor.previewPanel.elements.AccBtn;
import ale.view.gui.editor.previewPanel.elements.Branding;
import ale.view.gui.editor.previewPanel.elements.LocaleBtn;
import ale.view.gui.editor.previewPanel.elements.SecurityMenu;
import ale.view.gui.editor.previewPanel.elements.Shutdown;
import ale.view.gui.editor.previewPanel.elements.Userlist;
import ale.view.gui.editor.previewPanel.elements.Usertile;
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
    private AccBtn btnAcc;
    private LocaleBtn btnLocale;
    private Shutdown shutdown;
    private Branding brd;
    private SecurityMenu sec;
    private Userlist userlist;
    private Usertile usertile;

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

        this.btnAcc = new AccBtn(skinproperties, this);
        this.btnLocale = new LocaleBtn(skinproperties, this);
        this.shutdown = new Shutdown(skinproperties, this);
        this.brd = new Branding(skinproperties, this);
        this.sec = new SecurityMenu(skinproperties, this);
        this.userlist = new Userlist(skinproperties, this);
        this.usertile = new Usertile(skinproperties, this);
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
    public void stopRepaintTimer() {
        if (this.timer != null) {
            this.timer.stop();
            this.readThread.interrupt();
            try {
                this.readThread.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
            this.brd.paintComponents(g);
            this.btnAcc.paintComponents(g);
            this.shutdown.paintComponents(g);
            this.btnLocale.paintComponents(g);
            if (this.showUserlist) {
                this.userlist.paintComponents(g);
            } else if (this.showUsertile) {
                this.usertile.paintComponents(g);
            } else {
                this.sec.paintComponents(g);
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
