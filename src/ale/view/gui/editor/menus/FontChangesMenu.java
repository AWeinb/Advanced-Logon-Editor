/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.menus;

import java.awt.Color;

import javax.swing.JPanel;

import ale.controller.Main;
import ale.model.skin.SkinConstants.Fontstyle;
import ale.model.skin.SkinConstants.Fonttype;
import ale.model.skin.SkinConstants.UIFontInits;
import ale.model.skin.SkinPropertiesVO;
import ale.view.gui.editor.fields.FontField;
import ale.view.gui.editor.fields.Numberfield;
import ale.view.gui.util.VerticalLayout;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.editor.menus <br/>
 * Class  : FontChangesMenu <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 01.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class FontChangesMenu extends ChangesMenus {

    private boolean init_1;
    private boolean init_2;
    private boolean init_3;
    private boolean init_4;

    private JPanel fontshadowPanel;
    private JPanel allFontsPanel;
    private JPanel shutdownPanel;
    private JPanel shutdownMenuPanel;
    private JPanel commandBtnPanel;
    private JPanel standardBtnPanel;
    private JPanel pwfieldPanel;
    private JPanel pwResetBtnPanel;
    private JPanel loadingstatusPanel;
    private JPanel shortMsgPanel;
    private JPanel statustextListPanel;
    private JPanel statustextTilePanel;
    private JPanel nametextListPanel;
    private JPanel nametextTilePanel;

    private SkinPropertiesVO skin;

    public FontChangesMenu(Color bg, SkinPropertiesVO skin) {
        this.skin = skin;
        initialize(bg);
    }

    /**
     *
     *
     * @param bg  background color
     */
    private void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initFontshadowMenu(bg);
                initAllFontsFontMenu(bg);
                initShutdownFontMenu(bg);
                initShutdownMenuFontMenu(bg);
                FontChangesMenu.this.init_1 = true;
            }
        };
        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                initCommandBtnFontMenu(bg);
                initStandardBtnFontMenu(bg);
                initPWFieldFontMenu(bg);
                FontChangesMenu.this.init_2 = true;
            }
        };
        Runnable _runThree = new Runnable() {

            @Override
            public void run() {
                initPWResetFontMenu(bg);
                initLoadingstatusFontMenu(bg);
                initShortMsgFontMenu(bg);
                FontChangesMenu.this.init_3 = true;
            }
        };
        Runnable _runFour = new Runnable() {

            @Override
            public void run() {
                initStatusListFontMenu(bg);
                initStatusTileFontMenu(bg);
                initNameListFontMenu(bg);
                initNameTileFontMenu(bg);
                FontChangesMenu.this.init_4 = true;
            }
        };

        Main.executeThreads(_runOne, _runTwo, _runThree, _runFour);
    }

    public boolean isInitialized() {
        return this.init_1 & this.init_2 & this.init_3 & this.init_4;
    }

    public void shutdown() {
        this.fontshadowPanel = null;
        this.allFontsPanel = null;
        this.shutdownPanel = null;
        this.shutdownMenuPanel = null;
        this.commandBtnPanel = null;
        this.standardBtnPanel = null;
        this.pwfieldPanel = null;
        this.pwResetBtnPanel = null;
        this.loadingstatusPanel = null;
        this.shortMsgPanel = null;
        this.statustextListPanel = null;
        this.statustextTilePanel = null;
        this.nametextListPanel = null;
        this.nametextTilePanel = null;
    }

    public JPanel getFontshadowChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.fontshadowPanel;
    }

    public JPanel getAllFontsChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.allFontsPanel;
    }

    public JPanel getShutdownChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.shutdownPanel;
    }

    public JPanel getShutdownMenuChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.shutdownMenuPanel;
    }

    public JPanel getCommandBtnChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.commandBtnPanel;
    }

    public JPanel getStandardBtnChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.standardBtnPanel;
    }

    public JPanel getPWFieldBtnChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.pwfieldPanel;
    }

    public JPanel getPWResetBtnChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.pwResetBtnPanel;
    }

    public JPanel getLoadingstatusChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.loadingstatusPanel;
    }

    public JPanel getShortMsgChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.shortMsgPanel;
    }

    public JPanel getStatustextInListChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.statustextListPanel;
    }

    public JPanel getStatustextInTileChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.statustextTilePanel;
    }

    public JPanel getNametextInListChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.nametextListPanel;
    }

    public JPanel getNametextInTileChangesMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.nametextTilePanel;
    }

    /*
     * ########################################################################## ##
     */

    private void initFontshadowMenu(Color bg) {
        this.fontshadowPanel = new JPanel();
        this.fontshadowPanel.setBackground(bg);
        this.fontshadowPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        this.fontshadowPanel.add(new Numberfield(this.skin.getShadowIntensity(), 3, bg, strFontshadow, strFontshadowIntensity, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                FontChangesMenu.this.skin.setShadowIntensity(input != null ? parseInt(input) : -1);
            }

            @Override
            public void resetOnClick() {
                FontChangesMenu.this.skin.setShadowIntensity(-1);
                update(FontChangesMenu.this.skin.getShadowIntensity());
            }
        });
    }

    /*
     * 
     */
    private FontField shutdownFontfield;
    private FontField shutdownMenuFontfield;
    private FontField commandButtonFontfield;
    private FontField standardButtonFontfield;
    private FontField pwFieldFontfield;
    private FontField pwResetFontfield;
    private FontField loadingstatusFontfield;
    private FontField shortMessageFontfield;
    private FontField statustextFontfield;
    private FontField statustextZoomedFontfield;
    private FontField nametextFontfield;
    private FontField nametextZoomedFontfield;

    private void initAllFontsFontMenu(Color bg) {
        this.allFontsPanel = new JPanel();
        this.allFontsPanel.setBackground(bg);
        this.allFontsPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = UIFontInits.STDBUTTON.getFont();
        int initFontSizeTmp = UIFontInits.STDBUTTON.getSize();
        Color initFontColorTmp = Color.BLACK;
        boolean boldTmp = false;
        boolean underlineTmp = false;
        boolean shadowTmp = false;
        this.allFontsPanel.add(new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void sizeTyped(String input) {
                FontChangesMenu.this.shutdownFontfield.sizeTyped(input);
                FontChangesMenu.this.shutdownMenuFontfield.sizeTyped(input);
                FontChangesMenu.this.commandButtonFontfield.sizeTyped(input);
                FontChangesMenu.this.standardButtonFontfield.sizeTyped(input);
                FontChangesMenu.this.pwFieldFontfield.sizeTyped(input);
                FontChangesMenu.this.pwResetFontfield.sizeTyped(input);
                FontChangesMenu.this.loadingstatusFontfield.sizeTyped(input);
                FontChangesMenu.this.shortMessageFontfield.sizeTyped(input);
                FontChangesMenu.this.statustextFontfield.sizeTyped(input);
                FontChangesMenu.this.statustextZoomedFontfield.sizeTyped(input);
                FontChangesMenu.this.nametextFontfield.sizeTyped(input);
                FontChangesMenu.this.nametextZoomedFontfield.sizeTyped(input);
            }

            @Override
            public void fontChosen(String input) {
                FontChangesMenu.this.shutdownFontfield.fontChosen(input);
                FontChangesMenu.this.shutdownMenuFontfield.fontChosen(input);
                FontChangesMenu.this.commandButtonFontfield.fontChosen(input);
                FontChangesMenu.this.standardButtonFontfield.fontChosen(input);
                FontChangesMenu.this.pwFieldFontfield.fontChosen(input);
                FontChangesMenu.this.pwResetFontfield.fontChosen(input);
                FontChangesMenu.this.loadingstatusFontfield.fontChosen(input);
                FontChangesMenu.this.shortMessageFontfield.fontChosen(input);
                FontChangesMenu.this.statustextFontfield.fontChosen(input);
                FontChangesMenu.this.statustextZoomedFontfield.fontChosen(input);
                FontChangesMenu.this.nametextFontfield.fontChosen(input);
                FontChangesMenu.this.nametextZoomedFontfield.fontChosen(input);
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.shutdownFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.shutdownMenuFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.commandButtonFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.standardButtonFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.pwFieldFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.pwResetFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.loadingstatusFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.shortMessageFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.statustextFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.statustextZoomedFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.nametextFontfield.colorBtnPressed(argb);
                FontChangesMenu.this.nametextZoomedFontfield.colorBtnPressed(argb);
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.shutdownFontfield.boldPressed(selected);
                FontChangesMenu.this.shutdownMenuFontfield.boldPressed(selected);
                FontChangesMenu.this.commandButtonFontfield.boldPressed(selected);
                FontChangesMenu.this.standardButtonFontfield.boldPressed(selected);
                FontChangesMenu.this.pwFieldFontfield.boldPressed(selected);
                FontChangesMenu.this.pwResetFontfield.boldPressed(selected);
                FontChangesMenu.this.loadingstatusFontfield.boldPressed(selected);
                FontChangesMenu.this.shortMessageFontfield.boldPressed(selected);
                FontChangesMenu.this.statustextFontfield.boldPressed(selected);
                FontChangesMenu.this.statustextZoomedFontfield.boldPressed(selected);
                FontChangesMenu.this.nametextFontfield.boldPressed(selected);
                FontChangesMenu.this.nametextZoomedFontfield.boldPressed(selected);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.shutdownFontfield.underlinePressed(selected);
                FontChangesMenu.this.shutdownMenuFontfield.underlinePressed(selected);
                FontChangesMenu.this.commandButtonFontfield.underlinePressed(selected);
                FontChangesMenu.this.standardButtonFontfield.underlinePressed(selected);
                FontChangesMenu.this.pwFieldFontfield.underlinePressed(selected);
                FontChangesMenu.this.pwResetFontfield.underlinePressed(selected);
                FontChangesMenu.this.loadingstatusFontfield.underlinePressed(selected);
                FontChangesMenu.this.shortMessageFontfield.underlinePressed(selected);
                FontChangesMenu.this.statustextFontfield.underlinePressed(selected);
                FontChangesMenu.this.statustextZoomedFontfield.underlinePressed(selected);
                FontChangesMenu.this.nametextFontfield.underlinePressed(selected);
                FontChangesMenu.this.nametextZoomedFontfield.underlinePressed(selected);
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.shutdownFontfield.shadowPressed(selected);
                FontChangesMenu.this.shutdownMenuFontfield.shadowPressed(selected);
                FontChangesMenu.this.commandButtonFontfield.shadowPressed(selected);
                FontChangesMenu.this.standardButtonFontfield.shadowPressed(selected);
                FontChangesMenu.this.pwFieldFontfield.shadowPressed(selected);
                FontChangesMenu.this.pwResetFontfield.shadowPressed(selected);
                FontChangesMenu.this.loadingstatusFontfield.shadowPressed(selected);
                FontChangesMenu.this.shortMessageFontfield.shadowPressed(selected);
                FontChangesMenu.this.statustextFontfield.shadowPressed(selected);
                FontChangesMenu.this.statustextZoomedFontfield.shadowPressed(selected);
                FontChangesMenu.this.nametextFontfield.shadowPressed(selected);
                FontChangesMenu.this.nametextZoomedFontfield.shadowPressed(selected);
            }
        });
    }

    private void initShutdownFontMenu(Color bg) {
        this.shutdownPanel = new JPanel();
        this.shutdownPanel.setBackground(bg);
        this.shutdownPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getShutdownFont();
        int initFontSizeTmp = this.skin.getShutdownFontsize();
        Color initFontColorTmp = new Color(this.skin.getShutdownFontcolor()[1], this.skin.getShutdownFontcolor()[2],
                this.skin.getShutdownFontcolor()[3]);
        boolean boldTmp = this.skin.getShutdownFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getShutdownFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getShutdownFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getShutdownFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getShutdownFontstyle() == Fontstyle.SHADOWUNDERLINE);

        this.shutdownFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp,
                shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setShutdownFont(input);
                    updateFont(FontChangesMenu.this.skin.getShutdownFont());
                } else {
                    FontChangesMenu.this.skin.setShutdownFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setShutdownFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getShutdownFontsize());
                } else {
                    FontChangesMenu.this.skin.setShutdownFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setShutdownFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getShutdownFontcolor()[1],
                        FontChangesMenu.this.skin.getShutdownFontcolor()[2], FontChangesMenu.this.skin.getShutdownFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setShutdownFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getShutdownFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setShutdownFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getShutdownFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getShutdownFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getShutdownFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setShutdownFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getShutdownFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getShutdownFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getShutdownFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };
        this.shutdownPanel.add(this.shutdownFontfield);
    }

    private void initShutdownMenuFontMenu(Color bg) {
        this.shutdownMenuPanel = new JPanel();
        this.shutdownMenuPanel.setBackground(bg);
        this.shutdownMenuPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getShutdownMenuFont();
        int initFontSizeTmp = this.skin.getShutdownMenuFontsize();
        Color initFontColorTmp = new Color(this.skin.getShutdownMenuFontcolor()[1], this.skin.getShutdownMenuFontcolor()[2],
                this.skin.getShutdownMenuFontcolor()[3]);
        boolean boldTmp = this.skin.getShutdownMenuFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getShutdownMenuFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getShutdownMenuFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getShutdownMenuFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getShutdownMenuFontstyle() == Fontstyle.SHADOWUNDERLINE);

        this.shutdownMenuFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp,
                shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setShutdownMenuFont(input);
                    updateFont(FontChangesMenu.this.skin.getShutdownMenuFont());
                } else {
                    FontChangesMenu.this.skin.setShutdownMenuFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setShutdownMenuFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getShutdownMenuFontsize());
                } else {
                    FontChangesMenu.this.skin.setShutdownMenuFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setShutdownMenuFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getShutdownMenuFontcolor()[1],
                        FontChangesMenu.this.skin.getShutdownMenuFontcolor()[2],
                        FontChangesMenu.this.skin.getShutdownMenuFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setShutdownMenuFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getShutdownMenuFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setShutdownMenuFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getShutdownMenuFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getShutdownMenuFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getShutdownMenuFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setShutdownMenuFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getShutdownMenuFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getShutdownMenuFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getShutdownMenuFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.shutdownMenuPanel.add(this.shutdownMenuFontfield);
    }

    private void initCommandBtnFontMenu(Color bg) {
        this.commandBtnPanel = new JPanel();
        this.commandBtnPanel.setBackground(bg);
        this.commandBtnPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getCommandButtonFont();
        int initFontSizeTmp = this.skin.getCommandButtonFontsize();
        Color initFontColorTmp = new Color(this.skin.getCommandButtonFontcolor()[1], this.skin.getCommandButtonFontcolor()[2],
                this.skin.getCommandButtonFontcolor()[3]);
        boolean boldTmp = this.skin.getCommandButtonFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getCommandButtonFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getCommandButtonFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getCommandButtonFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getCommandButtonFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.commandButtonFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setCommandButtonFont(input);
                    updateFont(FontChangesMenu.this.skin.getCommandButtonFont());
                } else {
                    FontChangesMenu.this.skin.setCommandButtonFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setCommandButtonFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getCommandButtonFontsize());
                } else {
                    FontChangesMenu.this.skin.setCommandButtonFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setCommandButtonFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getCommandButtonFontcolor()[1],
                        FontChangesMenu.this.skin.getCommandButtonFontcolor()[2],
                        FontChangesMenu.this.skin.getCommandButtonFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setCommandButtonFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getCommandButtonFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setCommandButtonFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getCommandButtonFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getCommandButtonFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getCommandButtonFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setCommandButtonFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getCommandButtonFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getCommandButtonFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getCommandButtonFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.commandBtnPanel.add(this.commandButtonFontfield);
    }

    private void initStandardBtnFontMenu(Color bg) {
        this.standardBtnPanel = new JPanel();
        this.standardBtnPanel.setBackground(bg);
        this.standardBtnPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getBtn_Std_Font();
        int initFontSizeTmp = this.skin.getBtn_Std_Fontsize();
        Color initFontColorTmp = new Color(this.skin.getBtn_Std_Fontcolor()[1], this.skin.getBtn_Std_Fontcolor()[2],
                this.skin.getBtn_Std_Fontcolor()[3]);
        boolean boldTmp = this.skin.getBtn_Std_Fonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getBtn_Std_Fontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOW)
                || (this.skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.standardButtonFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setBtn_Std_Font(input);
                    updateFont(FontChangesMenu.this.skin.getBtn_Std_Font());
                } else {
                    FontChangesMenu.this.skin.setBtn_Std_Font(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setBtn_Std_Fontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getBtn_Std_Fontsize());
                } else {
                    FontChangesMenu.this.skin.setBtn_Std_Fontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setBtn_Std_Fontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getBtn_Std_Fontcolor()[1],
                        FontChangesMenu.this.skin.getBtn_Std_Fontcolor()[2], FontChangesMenu.this.skin.getBtn_Std_Fontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setBtn_Std_Fonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getBtn_Std_Fonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setBtn_Std_Fontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getBtn_Std_Fontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getBtn_Std_Fontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setBtn_Std_Fontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getBtn_Std_Fontstyle()));
                updateShadow((FontChangesMenu.this.skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.standardBtnPanel.add(this.standardButtonFontfield);
    }

    private void initPWFieldFontMenu(Color bg) {
        this.pwfieldPanel = new JPanel();
        this.pwfieldPanel.setBackground(bg);
        this.pwfieldPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getPWFieldFont();
        int initFontSizeTmp = this.skin.getPWFieldFontsize();
        Color initFontColorTmp = new Color(this.skin.getPWFieldFontcolor()[1], this.skin.getPWFieldFontcolor()[2],
                this.skin.getPWFieldFontcolor()[3]);
        boolean boldTmp = this.skin.getPWFieldFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getPWFieldFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getPWFieldFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getPWFieldFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getPWFieldFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.pwFieldFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setPWFieldFont(input);
                    updateFont(FontChangesMenu.this.skin.getPWFieldFont());
                } else {
                    FontChangesMenu.this.skin.setPWFieldFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setPWFieldFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getPWFieldFontsize());
                } else {
                    FontChangesMenu.this.skin.setPWFieldFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setPWFieldFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getPWFieldFontcolor()[1],
                        FontChangesMenu.this.skin.getPWFieldFontcolor()[2], FontChangesMenu.this.skin.getPWFieldFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setPWFieldFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getPWFieldFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setPWFieldFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getPWFieldFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getPWFieldFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getPWFieldFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin
                .setPWFieldFontstyle(getShadowFontstyle(selected, FontChangesMenu.this.skin.getPWFieldFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getPWFieldFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getPWFieldFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.pwfieldPanel.add(this.pwFieldFontfield);
    }

    private void initPWResetFontMenu(Color bg) {
        this.pwResetBtnPanel = new JPanel();
        this.pwResetBtnPanel.setBackground(bg);
        this.pwResetBtnPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getPWResetFont();
        int initFontSizeTmp = this.skin.getPWResetFontsize();
        Color initFontColorTmp = new Color(this.skin.getPWResetFontcolor()[1], this.skin.getPWResetFontcolor()[2],
                this.skin.getPWResetFontcolor()[3]);
        boolean boldTmp = this.skin.getPWResetFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getPWResetFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getPWResetFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getPWResetFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getPWResetFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.pwResetFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setPWResetFont(input);
                    updateFont(FontChangesMenu.this.skin.getPWResetFont());
                } else {
                    FontChangesMenu.this.skin.setPWResetFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setPWResetFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getPWResetFontsize());
                } else {
                    FontChangesMenu.this.skin.setPWResetFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setPWResetFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getPWResetFontcolor()[1],
                        FontChangesMenu.this.skin.getPWResetFontcolor()[2], FontChangesMenu.this.skin.getPWResetFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setPWResetFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getPWResetFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setPWResetFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getPWResetFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getPWResetFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getPWResetFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin
                .setPWResetFontstyle(getShadowFontstyle(selected, FontChangesMenu.this.skin.getPWResetFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getPWResetFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getPWResetFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.pwResetBtnPanel.add(this.pwResetFontfield);
    }

    private void initLoadingstatusFontMenu(Color bg) {
        this.loadingstatusPanel = new JPanel();
        this.loadingstatusPanel.setBackground(bg);
        this.loadingstatusPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getLoadingStatusFont();
        int initFontSizeTmp = this.skin.getLoadingStatusFontsize();
        Color initFontColorTmp = new Color(this.skin.getLoadingStatusFontcolor()[1], this.skin.getLoadingStatusFontcolor()[2],
                this.skin.getLoadingStatusFontcolor()[3]);
        boolean boldTmp = this.skin.getLoadingStatusFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getLoadingStatusFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getLoadingStatusFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getLoadingStatusFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getLoadingStatusFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.loadingstatusFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setLoadingStatusFont(input);
                    updateFont(FontChangesMenu.this.skin.getLoadingStatusFont());
                } else {
                    FontChangesMenu.this.skin.setLoadingStatusFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setLoadingStatusFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getLoadingStatusFontsize());
                } else {
                    FontChangesMenu.this.skin.setLoadingStatusFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setLoadingStatusFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getLoadingStatusFontcolor()[1],
                        FontChangesMenu.this.skin.getLoadingStatusFontcolor()[2],
                        FontChangesMenu.this.skin.getLoadingStatusFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setLoadingStatusFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getLoadingStatusFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setLoadingStatusFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getLoadingStatusFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getLoadingStatusFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getLoadingStatusFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setLoadingStatusFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getLoadingStatusFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getLoadingStatusFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getLoadingStatusFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.loadingstatusPanel.add(this.loadingstatusFontfield);
    }

    private void initShortMsgFontMenu(Color bg) {
        this.shortMsgPanel = new JPanel();
        this.shortMsgPanel.setBackground(bg);
        this.shortMsgPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getShortMessageFont();
        int initFontSizeTmp = this.skin.getShortMessageFontsize();
        Color initFontColorTmp = new Color(this.skin.getShortMessageFontcolor()[1], this.skin.getShortMessageFontcolor()[2],
                this.skin.getShortMessageFontcolor()[3]);
        boolean boldTmp = this.skin.getShortMessageFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getShortMessageFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getShortMessageFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getShortMessageFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getShortMessageFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.shortMessageFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp,
                shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setShortMessageFont(input);
                    updateFont(FontChangesMenu.this.skin.getShortMessageFont());
                } else {
                    FontChangesMenu.this.skin.setShortMessageFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setShortMessageFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getShortMessageFontsize());
                } else {
                    FontChangesMenu.this.skin.setShortMessageFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setShortMessageFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getShortMessageFontcolor()[1],
                        FontChangesMenu.this.skin.getShortMessageFontcolor()[2],
                        FontChangesMenu.this.skin.getShortMessageFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setShortMessageFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getShortMessageFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setShortMessageFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getShortMessageFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getShortMessageFontstyle() == Fontstyle.UNDERLINE)
                        | (FontChangesMenu.this.skin.getShortMessageFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setShortMessageFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getShortMessageFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getShortMessageFontstyle() == Fontstyle.SHADOW)
                        | (FontChangesMenu.this.skin.getShortMessageFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.shortMsgPanel.add(this.shortMessageFontfield);
    }

    private void initStatusListFontMenu(Color bg) {
        this.statustextListPanel = new JPanel();
        this.statustextListPanel.setBackground(bg);
        this.statustextListPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getStatustextFont();
        int initFontSizeTmp = this.skin.getStatustextFontsize();
        Color initFontColorTmp = new Color(this.skin.getStatustextFontcolor()[1], this.skin.getStatustextFontcolor()[2],
                this.skin.getStatustextFontcolor()[3]);
        boolean boldTmp = this.skin.getStatustextFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getStatustextFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getStatustextFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getStatustextFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getStatustextFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.statustextFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp,
                shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setStatustextFont(input);
                    updateFont(FontChangesMenu.this.skin.getStatustextFont());
                } else {
                    FontChangesMenu.this.skin.setStatustextFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setStatustextFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getStatustextFontsize());
                } else {
                    FontChangesMenu.this.skin.setStatustextFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setStatustextFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getStatustextFontcolor()[1],
                        FontChangesMenu.this.skin.getStatustextFontcolor()[2], FontChangesMenu.this.skin.getStatustextFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setStatustextFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getStatustextFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setStatustextFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getStatustextFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getStatustextFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getStatustextFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setStatustextFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getStatustextFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getStatustextFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getStatustextFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.statustextListPanel.add(this.statustextFontfield);
    }

    private void initStatusTileFontMenu(Color bg) {
        this.statustextTilePanel = new JPanel();
        this.statustextTilePanel.setBackground(bg);
        this.statustextTilePanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getStatustextZoomedFont();
        int initFontSizeTmp = this.skin.getStatustextZoomedFontsize();
        Color initFontColorTmp = new Color(this.skin.getStatustextZoomedFontcolor()[1], this.skin.getStatustextZoomedFontcolor()[2],
                this.skin.getStatustextZoomedFontcolor()[3]);
        boolean boldTmp = this.skin.getStatustextZoomedFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getStatustextZoomedFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.statustextZoomedFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setStatustextZoomedFont(input);
                    updateFont(FontChangesMenu.this.skin.getStatustextZoomedFont());
                } else {
                    FontChangesMenu.this.skin.setStatustextZoomedFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setStatustextZoomedFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getStatustextZoomedFontsize());
                } else {
                    FontChangesMenu.this.skin.setStatustextZoomedFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setStatustextZoomedFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getStatustextZoomedFontcolor()[1],
                        FontChangesMenu.this.skin.getStatustextZoomedFontcolor()[2],
                        FontChangesMenu.this.skin.getStatustextZoomedFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setStatustextZoomedFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getStatustextZoomedFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setStatustextZoomedFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getStatustextZoomedFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getStatustextZoomedFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setStatustextZoomedFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getStatustextZoomedFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.statustextTilePanel.add(this.statustextZoomedFontfield);
    }

    private void initNameListFontMenu(Color bg) {
        this.nametextListPanel = new JPanel();
        this.nametextListPanel.setBackground(bg);
        this.nametextListPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getNametextFont();
        int initFontSizeTmp = this.skin.getNametextFontsize();
        Color initFontColorTmp = new Color(this.skin.getNametextFontcolor()[1], this.skin.getNametextFontcolor()[2],
                this.skin.getNametextFontcolor()[3]);
        boolean boldTmp = this.skin.getNametextFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getNametextFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getNametextFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getNametextFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getNametextFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.nametextFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp,
                shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setNametextFont(input);
                    updateFont(FontChangesMenu.this.skin.getNametextFont());
                } else {
                    FontChangesMenu.this.skin.setNametextFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setNametextFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getNametextFontsize());
                } else {
                    FontChangesMenu.this.skin.setNametextFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setNametextFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getNametextFontcolor()[1],
                        FontChangesMenu.this.skin.getNametextFontcolor()[2], FontChangesMenu.this.skin.getNametextFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setNametextFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getNametextFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setNametextFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getNametextFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getNametextFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getNametextFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setNametextFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getNametextFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getNametextFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getNametextFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.nametextListPanel.add(this.nametextFontfield);
    }

    private void initNameTileFontMenu(Color bg) {
        this.nametextTilePanel = new JPanel();
        this.nametextTilePanel.setBackground(bg);
        this.nametextTilePanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = this.skin.getNametextZoomedFont();
        int initFontSizeTmp = this.skin.getNametextZoomedFontsize();
        Color initFontColorTmp = new Color(this.skin.getNametextZoomedFontcolor()[1], this.skin.getNametextZoomedFontcolor()[2],
                this.skin.getNametextZoomedFontcolor()[3]);
        boolean boldTmp = this.skin.getNametextZoomedFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (this.skin.getNametextZoomedFontstyle() == Fontstyle.UNDERLINE)
                || (this.skin.getNametextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (this.skin.getNametextZoomedFontstyle() == Fontstyle.SHADOW)
                || (this.skin.getNametextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE);
        this.nametextZoomedFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    FontChangesMenu.this.skin.setNametextZoomedFont(input);
                    updateFont(FontChangesMenu.this.skin.getNametextZoomedFont());
                } else {
                    FontChangesMenu.this.skin.setNametextZoomedFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    FontChangesMenu.this.skin.setNametextZoomedFontsize(parseInt(input));
                    updateSize(FontChangesMenu.this.skin.getNametextZoomedFontsize());
                } else {
                    FontChangesMenu.this.skin.setNametextZoomedFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                FontChangesMenu.this.skin.setNametextZoomedFontcolor(argb);
                updateColor(new Color(FontChangesMenu.this.skin.getNametextZoomedFontcolor()[1],
                        FontChangesMenu.this.skin.getNametextZoomedFontcolor()[2],
                        FontChangesMenu.this.skin.getNametextZoomedFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                FontChangesMenu.this.skin.setNametextZoomedFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(FontChangesMenu.this.skin.getNametextZoomedFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                FontChangesMenu.this.skin.setNametextZoomedFontstyle(getUnderlineFontstyle(selected,
                        FontChangesMenu.this.skin.getNametextZoomedFontstyle()));
                updateUnderline((FontChangesMenu.this.skin.getNametextZoomedFontstyle() == Fontstyle.UNDERLINE)
                        || (FontChangesMenu.this.skin.getNametextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                FontChangesMenu.this.skin.setNametextZoomedFontstyle(getShadowFontstyle(selected,
                        FontChangesMenu.this.skin.getNametextZoomedFontstyle()));
                updateShadow((FontChangesMenu.this.skin.getNametextZoomedFontstyle() == Fontstyle.SHADOW)
                        || (FontChangesMenu.this.skin.getNametextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        this.nametextTilePanel.add(this.nametextZoomedFontfield);
    }

    private Fontstyle getUnderlineFontstyle(boolean selected, Fontstyle oldValue) {
        Fontstyle ret = null;

        if (selected) {
            if (oldValue == Fontstyle.SHADOW) {
                ret = Fontstyle.SHADOWUNDERLINE;
            } else {
                ret = Fontstyle.UNDERLINE;
            }
        } else {
            if (oldValue == Fontstyle.SHADOWUNDERLINE) {
                ret = Fontstyle.SHADOW;
            } else {
                ret = Fontstyle.NONE;
            }
        }

        return ret;
    }

    private Fontstyle getShadowFontstyle(boolean selected, Fontstyle oldValue) {
        Fontstyle ret = null;

        if (selected) {
            if (oldValue == Fontstyle.UNDERLINE) {
                ret = Fontstyle.SHADOWUNDERLINE;
            } else {
                ret = Fontstyle.SHADOW;
            }
        } else {
            if (oldValue == Fontstyle.SHADOWUNDERLINE) {
                ret = Fontstyle.UNDERLINE;
            } else {
                ret = Fontstyle.NONE;
            }
        }

        return ret;
    }
}
