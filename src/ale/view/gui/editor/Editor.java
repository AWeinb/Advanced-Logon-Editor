/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ale.controller.Main;
import ale.controller.Settings;
import ale.model.skin.Skin;
import ale.view.gui.GUIConstants;
import ale.view.gui.GUIConstants.RightMenu;
import ale.view.gui.editor.menus.ChangesMenus;
import ale.view.gui.editor.previewPanel.PreviewPanel;
import ale.view.gui.util.GUIStrings;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.editor <br/>
 * Class  : Editor <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>Editor</code> is the dialog which shows option to manipulate a skin object. TODO: The preview is not finished.   <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 24.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class Editor extends JFrame implements WindowListener {
    private static final long serialVersionUID = 1L;

    private String[] strArGenChangeOptions = new String[RightMenu.GEN_CHANGES_MENU.getMenuCount() + 1];
    private String[] strArBtnChangeOptions = new String[RightMenu.BTN_CHANGES_MENU.getMenuCount() + 1];
    private String[] strArUserlistOptions = new String[RightMenu.USERLIST_CHANGES_MENU.getMenuCount() + 1];
    private String[] strArUsertileOptions = new String[RightMenu.USERTILE_CHANGES_MENU.getMenuCount() + 1];
    private String[] strArFontOptions = new String[RightMenu.FONT_MENU.getMenuCount() + 1];

    private PreviewPanel previewPanel;
    private EditorLeftMenu leftMenu;
    private EditorRightMenu rightMenu;
    private EditorBottomMenu btmMenu;

    private RightMenu shown;
    private Skin skin;

    /**
     * @param skin skinobject
     */
    public Editor(final Skin skin) {
        if (skin == null) {
            throw new IllegalArgumentException();
        }
        this.skin = skin;

        setUpStringArrays();
        initMenus();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Editor.this.setTitle(GUIConstants.PROGRAM_TITLE + " - " + skin.getName());
                Editor.this.setIconImage(GUIConstants.PROGRAM_ICON);
                Editor.this.setJMenuBar(new EditorMenuBar(skin));
                Editor.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                Editor.this.addWindowListener(Editor.this);
                Editor.this.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                Editor.this.setMinimumSize(GUIConstants.MIN_EDITOR_DIM);

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) ((screenSize.getWidth() * (100 - GUIConstants.EDITOR_SIDEGAP_PERCENT)) / 100f);
                int height = (int) ((screenSize.getHeight() * (100 - GUIConstants.EDITOR_SIDEGAP_PERCENT)) / 100f);
                Editor.this.setSize(width, height);
                Editor.this.setLocationRelativeTo(null);

                create();
                setFocusToLeftMenu();

                Editor.this.setVisible(true);
            }
        });
    }

    private void setUpStringArrays() {
        this.strArGenChangeOptions[0] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_DEFAULTCBOPTION);
        this.strArGenChangeOptions[1] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESOPT_1);
        this.strArGenChangeOptions[2] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESOPT_2);
        this.strArGenChangeOptions[3] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESOPT_3);
        this.strArGenChangeOptions[4] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESOPT_4);
        this.strArGenChangeOptions[5] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESOPT_5);
        this.strArGenChangeOptions[6] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESOPT_6);
        this.strArGenChangeOptions[7] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESOPT_7);

        this.strArBtnChangeOptions[0] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_DEFAULTCBOPTION);
        this.strArBtnChangeOptions[1] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_1);
        this.strArBtnChangeOptions[2] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_2);
        this.strArBtnChangeOptions[3] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_3);
        this.strArBtnChangeOptions[4] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_4);
        this.strArBtnChangeOptions[5] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_5);
        this.strArBtnChangeOptions[6] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_6);
        this.strArBtnChangeOptions[7] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_7);
        this.strArBtnChangeOptions[8] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_8);
        this.strArBtnChangeOptions[9] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESOPT_9);

        this.strArUserlistOptions[0] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_DEFAULTCBOPTION);
        this.strArUserlistOptions[1] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERLISTCHANGESOPT_1);
        this.strArUserlistOptions[2] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERLISTCHANGESOPT_2);
        this.strArUserlistOptions[3] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERLISTCHANGESOPT_3);

        this.strArUsertileOptions[0] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_DEFAULTCBOPTION);
        this.strArUsertileOptions[1] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERTILECHANGESOPT_1);
        this.strArUsertileOptions[2] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERTILECHANGESOPT_2);
        this.strArUsertileOptions[3] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERTILECHANGESOPT_3);
        this.strArUsertileOptions[4] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERTILECHANGESOPT_4);

        this.strArFontOptions[0] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_DEFAULTCBOPTION);
        this.strArFontOptions[1] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_1);
        this.strArFontOptions[2] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_2);
        this.strArFontOptions[3] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_3);
        this.strArFontOptions[4] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_4);
        this.strArFontOptions[5] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_5);
        this.strArFontOptions[6] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_6);
        this.strArFontOptions[7] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_7);
        this.strArFontOptions[8] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_8);
        this.strArFontOptions[9] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_9);
        this.strArFontOptions[10] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_10);
        this.strArFontOptions[11] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_11);
        this.strArFontOptions[12] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_12);
        this.strArFontOptions[13] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_13);
        this.strArFontOptions[14] = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESOPT_14);
    }

    private void initMenus() {
        ChangesMenus.initialize(GUIConstants.DEFAULT_BACKGROUND, Editor.this.skin.getProperties());
    }

    private void create() {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                Editor.this.previewPanel = new PreviewPanel(Editor.this.skin.getProperties());
            }
        };

        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                Editor.this.leftMenu = new EditorLeftMenu(Editor.this.skin, Editor.this, GUIConstants.DEFAULT_BACKGROUND);
            }
        };
        Runnable _runThree = new Runnable() {

            @Override
            public void run() {
                Editor.this.rightMenu = new EditorRightMenu(Editor.this, GUIConstants.DEFAULT_BACKGROUND);
            }
        };
        Runnable _runFour = new Runnable() {

            @Override
            public void run() {
                Editor.this.btmMenu = new EditorBottomMenu(Editor.this, GUIConstants.DEFAULT_BACKGROUND);
            }
        };
        Main.executeThreads(_runOne, _runTwo, _runThree, _runFour);

        while ((this.previewPanel == null) || (this.leftMenu == null) || (this.rightMenu == null) || (this.btmMenu == null)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                ;
            }
        }

        this.previewPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        this.add(this.previewPanel, BorderLayout.CENTER);

        this.add(this.leftMenu, BorderLayout.WEST);
        this.add(this.rightMenu, BorderLayout.EAST);
        this.add(this.btmMenu, BorderLayout.SOUTH);

        this.previewPanel.scaleBackgroundImage(Settings.getEditorBgScaled());
        this.previewPanel.startRepaintTimer();
    }

    /**
     * Shows a messagebox and asks to restart. It would be complicated to update all menus.
     * 
     */
    public void updateLocale() {
        JOptionPane.showMessageDialog(this, GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_UDATELOCALE));
    }

    /**
     * Sets the author name.
     *
     * @param author String
     */
    public void setSkinAuthor(String author) {
        this.skin.setAuthor(author);
    }

    /**
     * Returns the author name.
     *
     * @return String
     */
    public String getSkinAuthor() {
        return this.skin.getAuthor();
    }

    /**
     * Sets the website adress.
     *
     * @param website String
     */
    public void setSkinWebsite(String website) {
        this.skin.setWebsite(website);
    }

    /**
     * Returns the webadress.
     *
     * @return String
     */
    public String getSkinWebsite() {
        return this.skin.getWebsite();
    }

    /**
     * Sets the previewimage.
     *
     * @param image Path to the image.
     */
    public void setPreviewimage(Path image) {
        try {
            this.skin.setImage(image);
            this.skin.setSkinChanged(true);
        } catch (IOException e) {
            Main.showProblemMessage(e.getMessage());
        }
    }

    /**
     * Returns the path to the preview image.
     *
     * @return Path
     */
    public Path getPreviewimage() {
        return this.skin.getImage();
    }

    protected void setFocusToLeftMenu() {
        this.leftMenu.setFocusToButton(0);
    }

    protected void setFocusToRightMenu() {
        this.rightMenu.setFocusToCombobox();
    }

    protected void showUserlistPreview() {
        this.previewPanel.showUserlist();
    }

    protected void showUsertilePreview() {
        this.previewPanel.showUsertile();
    }

    protected void showSecurityMenuPreview() {
        this.previewPanel.showSecurityMenu();
    }

    protected void setPreviewBackgroundScaling(boolean scale) {
        this.previewPanel.scaleBackgroundImage(scale);
    }

    /*
     * 
     */
    protected void showMenu(final RightMenu menu) {
        if (this.rightMenu.isHidden()) {
            this.rightMenu.clickHideButton();
        }

        if (this.shown != menu) {
            this.rightMenu.clearContent();

            String[] menustringArray = null;
            String info;
            switch (menu) {
                case GEN_CHANGES_MENU:
                    menustringArray = this.strArGenChangeOptions;
                    info = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_GENCHANGESINFO);
                    this.rightMenu.setInfotext(info);
                    break;

                case BTN_CHANGES_MENU:
                    menustringArray = this.strArBtnChangeOptions;
                    info = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BTNCHANGESINFO);
                    this.rightMenu.setInfotext(info);
                    break;

                case USERLIST_CHANGES_MENU:
                    menustringArray = this.strArUserlistOptions;
                    info = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERLISTCHANGESINFO);
                    this.rightMenu.setInfotext(info);
                    break;

                case USERTILE_CHANGES_MENU:
                    menustringArray = this.strArUsertileOptions;
                    info = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERTILECHANGESINFO);
                    this.rightMenu.setInfotext(info);
                    break;

                case FONT_MENU:
                    menustringArray = this.strArFontOptions;
                    info = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCHANGESINFO);
                    this.rightMenu.setInfotext(info);
                    break;

                default:
                    throw new IllegalArgumentException();
            }

            this.rightMenu.updateMenu(menustringArray, menu);
            this.shown = menu;
        }
    }

    @Override
    public void dispose() {
        this.previewPanel.stopRepaintTimer();
        super.dispose();
    }

    @Override
    public void windowActivated(WindowEvent e) {
        ;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        ;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Main.showQuitConfirmationDialog(this.skin, false, true);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        ;
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        ;
    }

    @Override
    public void windowIconified(WindowEvent e) {
        ;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        ;
    }
}