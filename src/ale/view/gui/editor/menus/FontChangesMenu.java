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

    private static boolean init_1;
    private static boolean init_2;
    private static boolean init_3;
    private static boolean init_4;

    private static JPanel fontshadowPanel;
    private static JPanel allFontsPanel;
    private static JPanel shutdownPanel;
    private static JPanel shutdownMenuPanel;
    private static JPanel commandBtnPanel;
    private static JPanel standardBtnPanel;
    private static JPanel pwfieldPanel;
    private static JPanel pwResetBtnPanel;
    private static JPanel loadingstatusPanel;
    private static JPanel shortMsgPanel;
    private static JPanel statustextListPanel;
    private static JPanel statustextTilePanel;
    private static JPanel nametextListPanel;
    private static JPanel nametextTilePanel;

    private FontChangesMenu() {
    }

    /**
     *
     *
     * @param bg  background color
     */
    public static void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initFontshadowMenu(bg);
                initAllFontsFontMenu(bg);
                initShutdownFontMenu(bg);
                initShutdownMenuFontMenu(bg);
                init_1 = true;
            }
        };
        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                initCommandBtnFontMenu(bg);
                initStandardBtnFontMenu(bg);
                initPWFieldFontMenu(bg);
                init_2 = true;
            }
        };
        Runnable _runThree = new Runnable() {

            @Override
            public void run() {
                initPWResetFontMenu(bg);
                initLoadingstatusFontMenu(bg);
                initShortMsgFontMenu(bg);
                init_3 = true;
            }
        };
        Runnable _runFour = new Runnable() {

            @Override
            public void run() {
                initStatusListFontMenu(bg);
                initStatusTileFontMenu(bg);
                initNameListFontMenu(bg);
                initNameTileFontMenu(bg);
                init_4 = true;
            }
        };

        Main.executeThreads(_runOne, _runTwo, _runThree, _runFour);
    }

    protected static boolean isInitialized() {
        return init_1 & init_2 & init_3 & init_4;
    }

    public static JPanel getFontshadowChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.fontshadowPanel;
    }

    public static JPanel getAllFontsChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.allFontsPanel;
    }

    public static JPanel getShutdownChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.shutdownPanel;
    }

    public static JPanel getShutdownMenuChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.shutdownMenuPanel;
    }

    public static JPanel getCommandBtnChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.commandBtnPanel;
    }

    public static JPanel getStandardBtnChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.standardBtnPanel;
    }

    public static JPanel getPWFieldBtnChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.pwfieldPanel;
    }

    public static JPanel getPWResetBtnChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.pwResetBtnPanel;
    }

    public static JPanel getLoadingstatusChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.loadingstatusPanel;
    }

    public static JPanel getShortMsgChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.shortMsgPanel;
    }

    public static JPanel getStatustextInListChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.statustextListPanel;
    }

    public static JPanel getStatustextInTileChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.statustextTilePanel;
    }

    public static JPanel getNametextInListChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.nametextListPanel;
    }

    public static JPanel getNametextInTileChangesMenu() {
        if (!FontChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return FontChangesMenu.nametextTilePanel;
    }

    private static void initFontshadowMenu(Color bg) {
        FontChangesMenu.fontshadowPanel = new JPanel();
        FontChangesMenu.fontshadowPanel.setBackground(bg);
        FontChangesMenu.fontshadowPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        FontChangesMenu.fontshadowPanel.add(new Numberfield(skin.getShadowIntensity(), 3, bg, strFontshadow, strFontshadowIntensity, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                skin.setShadowIntensity(input != null ? parseInt(input) : -1);
            }

            @Override
            public void resetOnClick() {
                skin.setShadowIntensity(-1);
                update(skin.getShadowIntensity());
            }
        });
    }

    /*
     * 
     */
    private static FontField shutdownFontfield;
    private static FontField shutdownMenuFontfield;
    private static FontField commandButtonFontfield;
    private static FontField standardButtonFontfield;
    private static FontField pwFieldFontfield;
    private static FontField pwResetFontfield;
    private static FontField loadingstatusFontfield;
    private static FontField shortMessageFontfield;
    private static FontField statustextFontfield;
    private static FontField statustextZoomedFontfield;
    private static FontField nametextFontfield;
    private static FontField nametextZoomedFontfield;

    private static void initAllFontsFontMenu(Color bg) {
        FontChangesMenu.allFontsPanel = new JPanel();
        FontChangesMenu.allFontsPanel.setBackground(bg);
        FontChangesMenu.allFontsPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = UIFontInits.STDBUTTON.getFont();
        int initFontSizeTmp = UIFontInits.STDBUTTON.getSize();
        Color initFontColorTmp = Color.BLACK;
        boolean boldTmp = false;
        boolean underlineTmp = false;
        boolean shadowTmp = false;
        FontChangesMenu.allFontsPanel.add(new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void sizeTyped(String input) {
                shutdownFontfield.sizeTyped(input);
                shutdownMenuFontfield.sizeTyped(input);
                commandButtonFontfield.sizeTyped(input);
                standardButtonFontfield.sizeTyped(input);
                pwFieldFontfield.sizeTyped(input);
                pwResetFontfield.sizeTyped(input);
                loadingstatusFontfield.sizeTyped(input);
                shortMessageFontfield.sizeTyped(input);
                statustextFontfield.sizeTyped(input);
                statustextZoomedFontfield.sizeTyped(input);
                nametextFontfield.sizeTyped(input);
                nametextZoomedFontfield.sizeTyped(input);
            }

            @Override
            public void fontChosen(String input) {
                shutdownFontfield.fontChosen(input);
                shutdownMenuFontfield.fontChosen(input);
                commandButtonFontfield.fontChosen(input);
                standardButtonFontfield.fontChosen(input);
                pwFieldFontfield.fontChosen(input);
                pwResetFontfield.fontChosen(input);
                loadingstatusFontfield.fontChosen(input);
                shortMessageFontfield.fontChosen(input);
                statustextFontfield.fontChosen(input);
                statustextZoomedFontfield.fontChosen(input);
                nametextFontfield.fontChosen(input);
                nametextZoomedFontfield.fontChosen(input);
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                shutdownFontfield.colorBtnPressed(argb);
                shutdownMenuFontfield.colorBtnPressed(argb);
                commandButtonFontfield.colorBtnPressed(argb);
                standardButtonFontfield.colorBtnPressed(argb);
                pwFieldFontfield.colorBtnPressed(argb);
                pwResetFontfield.colorBtnPressed(argb);
                loadingstatusFontfield.colorBtnPressed(argb);
                shortMessageFontfield.colorBtnPressed(argb);
                statustextFontfield.colorBtnPressed(argb);
                statustextZoomedFontfield.colorBtnPressed(argb);
                nametextFontfield.colorBtnPressed(argb);
                nametextZoomedFontfield.colorBtnPressed(argb);
            }

            @Override
            public void boldPressed(boolean selected) {
                shutdownFontfield.boldPressed(selected);
                shutdownMenuFontfield.boldPressed(selected);
                commandButtonFontfield.boldPressed(selected);
                standardButtonFontfield.boldPressed(selected);
                pwFieldFontfield.boldPressed(selected);
                pwResetFontfield.boldPressed(selected);
                loadingstatusFontfield.boldPressed(selected);
                shortMessageFontfield.boldPressed(selected);
                statustextFontfield.boldPressed(selected);
                statustextZoomedFontfield.boldPressed(selected);
                nametextFontfield.boldPressed(selected);
                nametextZoomedFontfield.boldPressed(selected);
            }

            @Override
            public void underlinePressed(boolean selected) {
                shutdownFontfield.underlinePressed(selected);
                shutdownMenuFontfield.underlinePressed(selected);
                commandButtonFontfield.underlinePressed(selected);
                standardButtonFontfield.underlinePressed(selected);
                pwFieldFontfield.underlinePressed(selected);
                pwResetFontfield.underlinePressed(selected);
                loadingstatusFontfield.underlinePressed(selected);
                shortMessageFontfield.underlinePressed(selected);
                statustextFontfield.underlinePressed(selected);
                statustextZoomedFontfield.underlinePressed(selected);
                nametextFontfield.underlinePressed(selected);
                nametextZoomedFontfield.underlinePressed(selected);
            }

            @Override
            public void shadowPressed(boolean selected) {
                shutdownFontfield.shadowPressed(selected);
                shutdownMenuFontfield.shadowPressed(selected);
                commandButtonFontfield.shadowPressed(selected);
                standardButtonFontfield.shadowPressed(selected);
                pwFieldFontfield.shadowPressed(selected);
                pwResetFontfield.shadowPressed(selected);
                loadingstatusFontfield.shadowPressed(selected);
                shortMessageFontfield.shadowPressed(selected);
                statustextFontfield.shadowPressed(selected);
                statustextZoomedFontfield.shadowPressed(selected);
                nametextFontfield.shadowPressed(selected);
                nametextZoomedFontfield.shadowPressed(selected);
            }
        });
    }

    private static void initShutdownFontMenu(Color bg) {
        FontChangesMenu.shutdownPanel = new JPanel();
        FontChangesMenu.shutdownPanel.setBackground(bg);
        FontChangesMenu.shutdownPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getShutdownFont();
        int initFontSizeTmp = skin.getShutdownFontsize();
        Color initFontColorTmp = new Color(skin.getShutdownFontcolor()[1], skin.getShutdownFontcolor()[2], skin.getShutdownFontcolor()[3]);
        boolean boldTmp = skin.getShutdownFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getShutdownFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getShutdownFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getShutdownFontstyle() == Fontstyle.SHADOW) || (skin.getShutdownFontstyle() == Fontstyle.SHADOWUNDERLINE);

        shutdownFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setShutdownFont(input);
                    updateFont(skin.getShutdownFont());
                } else {
                    skin.setShutdownFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setShutdownFontsize(parseInt(input));
                    updateSize(skin.getShutdownFontsize());
                } else {
                    skin.setShutdownFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setShutdownFontcolor(argb);
                updateColor(new Color(skin.getShutdownFontcolor()[1], skin.getShutdownFontcolor()[2], skin.getShutdownFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setShutdownFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getShutdownFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setShutdownFontstyle(getUnderlineFontstyle(selected, skin.getShutdownFontstyle()));
                updateUnderline((skin.getShutdownFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getShutdownFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setShutdownFontstyle(getShadowFontstyle(selected, skin.getShutdownFontstyle()));
                updateShadow((skin.getShutdownFontstyle() == Fontstyle.SHADOW)
                        || (skin.getShutdownFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };
        FontChangesMenu.shutdownPanel.add(shutdownFontfield);
    }

    private static void initShutdownMenuFontMenu(Color bg) {
        FontChangesMenu.shutdownMenuPanel = new JPanel();
        FontChangesMenu.shutdownMenuPanel.setBackground(bg);
        FontChangesMenu.shutdownMenuPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getShutdownMenuFont();
        int initFontSizeTmp = skin.getShutdownMenuFontsize();
        Color initFontColorTmp = new Color(skin.getShutdownMenuFontcolor()[1], skin.getShutdownMenuFontcolor()[2],
                skin.getShutdownMenuFontcolor()[3]);
        boolean boldTmp = skin.getShutdownMenuFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getShutdownMenuFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getShutdownMenuFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getShutdownMenuFontstyle() == Fontstyle.SHADOW)
                || (skin.getShutdownMenuFontstyle() == Fontstyle.SHADOWUNDERLINE);

        shutdownMenuFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setShutdownMenuFont(input);
                    updateFont(skin.getShutdownMenuFont());
                } else {
                    skin.setShutdownMenuFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setShutdownMenuFontsize(parseInt(input));
                    updateSize(skin.getShutdownMenuFontsize());
                } else {
                    skin.setShutdownMenuFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setShutdownMenuFontcolor(argb);
                updateColor(new Color(skin.getShutdownMenuFontcolor()[1], skin.getShutdownMenuFontcolor()[2],
                        skin.getShutdownMenuFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setShutdownMenuFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getShutdownMenuFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setShutdownMenuFontstyle(getUnderlineFontstyle(selected, skin.getShutdownMenuFontstyle()));
                updateUnderline((skin.getShutdownMenuFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getShutdownMenuFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setShutdownMenuFontstyle(getShadowFontstyle(selected, skin.getShutdownMenuFontstyle()));
                updateShadow((skin.getShutdownMenuFontstyle() == Fontstyle.SHADOW)
                        || (skin.getShutdownMenuFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.shutdownMenuPanel.add(shutdownMenuFontfield);
    }

    private static void initCommandBtnFontMenu(Color bg) {
        FontChangesMenu.commandBtnPanel = new JPanel();
        FontChangesMenu.commandBtnPanel.setBackground(bg);
        FontChangesMenu.commandBtnPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getCommandButtonFont();
        int initFontSizeTmp = skin.getCommandButtonFontsize();
        Color initFontColorTmp = new Color(skin.getCommandButtonFontcolor()[1], skin.getCommandButtonFontcolor()[2],
                skin.getCommandButtonFontcolor()[3]);
        boolean boldTmp = skin.getCommandButtonFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getCommandButtonFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getCommandButtonFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getCommandButtonFontstyle() == Fontstyle.SHADOW)
                || (skin.getCommandButtonFontstyle() == Fontstyle.SHADOWUNDERLINE);
        commandButtonFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setCommandButtonFont(input);
                    updateFont(skin.getCommandButtonFont());
                } else {
                    skin.setCommandButtonFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setCommandButtonFontsize(parseInt(input));
                    updateSize(skin.getCommandButtonFontsize());
                } else {
                    skin.setCommandButtonFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setCommandButtonFontcolor(argb);
                updateColor(new Color(skin.getCommandButtonFontcolor()[1], skin.getCommandButtonFontcolor()[2],
                        skin.getCommandButtonFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setCommandButtonFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getCommandButtonFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setCommandButtonFontstyle(getUnderlineFontstyle(selected, skin.getCommandButtonFontstyle()));
                updateUnderline((skin.getCommandButtonFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getCommandButtonFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setCommandButtonFontstyle(getShadowFontstyle(selected, skin.getCommandButtonFontstyle()));
                updateShadow((skin.getCommandButtonFontstyle() == Fontstyle.SHADOW)
                        || (skin.getCommandButtonFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.commandBtnPanel.add(commandButtonFontfield);
    }

    private static void initStandardBtnFontMenu(Color bg) {
        FontChangesMenu.standardBtnPanel = new JPanel();
        FontChangesMenu.standardBtnPanel.setBackground(bg);
        FontChangesMenu.standardBtnPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getBtn_Std_Font();
        int initFontSizeTmp = skin.getBtn_Std_Fontsize();
        Color initFontColorTmp = new Color(skin.getBtn_Std_Fontcolor()[1], skin.getBtn_Std_Fontcolor()[2], skin.getBtn_Std_Fontcolor()[3]);
        boolean boldTmp = skin.getBtn_Std_Fonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getBtn_Std_Fontstyle() == Fontstyle.UNDERLINE)
                || (skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOW) || (skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOWUNDERLINE);
        standardButtonFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setBtn_Std_Font(input);
                    updateFont(skin.getBtn_Std_Font());
                } else {
                    skin.setBtn_Std_Font(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setBtn_Std_Fontsize(parseInt(input));
                    updateSize(skin.getBtn_Std_Fontsize());
                } else {
                    skin.setBtn_Std_Fontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setBtn_Std_Fontcolor(argb);
                updateColor(new Color(skin.getBtn_Std_Fontcolor()[1], skin.getBtn_Std_Fontcolor()[2], skin.getBtn_Std_Fontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setBtn_Std_Fonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getBtn_Std_Fonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setBtn_Std_Fontstyle(getUnderlineFontstyle(selected, skin.getBtn_Std_Fontstyle()));
                updateUnderline((skin.getBtn_Std_Fontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setBtn_Std_Fontstyle(getShadowFontstyle(selected, skin.getBtn_Std_Fontstyle()));
                updateShadow((skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOW)
                        || (skin.getBtn_Std_Fontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.standardBtnPanel.add(standardButtonFontfield);
    }

    private static void initPWFieldFontMenu(Color bg) {
        FontChangesMenu.pwfieldPanel = new JPanel();
        FontChangesMenu.pwfieldPanel.setBackground(bg);
        FontChangesMenu.pwfieldPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getPWFieldFont();
        int initFontSizeTmp = skin.getPWFieldFontsize();
        Color initFontColorTmp = new Color(skin.getPWFieldFontcolor()[1], skin.getPWFieldFontcolor()[2], skin.getPWFieldFontcolor()[3]);
        boolean boldTmp = skin.getPWFieldFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getPWFieldFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getPWFieldFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getPWFieldFontstyle() == Fontstyle.SHADOW) || (skin.getPWFieldFontstyle() == Fontstyle.SHADOWUNDERLINE);
        pwFieldFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setPWFieldFont(input);
                    updateFont(skin.getPWFieldFont());
                } else {
                    skin.setPWFieldFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setPWFieldFontsize(parseInt(input));
                    updateSize(skin.getPWFieldFontsize());
                } else {
                    skin.setPWFieldFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setPWFieldFontcolor(argb);
                updateColor(new Color(skin.getPWFieldFontcolor()[1], skin.getPWFieldFontcolor()[2], skin.getPWFieldFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setPWFieldFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getPWFieldFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setPWFieldFontstyle(getUnderlineFontstyle(selected, skin.getPWFieldFontstyle()));
                updateUnderline((skin.getPWFieldFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getPWFieldFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setPWFieldFontstyle(getShadowFontstyle(selected, skin.getPWFieldFontstyle()));
                updateShadow((skin.getPWFieldFontstyle() == Fontstyle.SHADOW) || (skin.getPWFieldFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.pwfieldPanel.add(pwFieldFontfield);
    }

    private static void initPWResetFontMenu(Color bg) {
        FontChangesMenu.pwResetBtnPanel = new JPanel();
        FontChangesMenu.pwResetBtnPanel.setBackground(bg);
        FontChangesMenu.pwResetBtnPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getPWResetFont();
        int initFontSizeTmp = skin.getPWResetFontsize();
        Color initFontColorTmp = new Color(skin.getPWResetFontcolor()[1], skin.getPWResetFontcolor()[2], skin.getPWResetFontcolor()[3]);
        boolean boldTmp = skin.getPWResetFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getPWResetFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getPWResetFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getPWResetFontstyle() == Fontstyle.SHADOW) || (skin.getPWResetFontstyle() == Fontstyle.SHADOWUNDERLINE);
        pwResetFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setPWResetFont(input);
                    updateFont(skin.getPWResetFont());
                } else {
                    skin.setPWResetFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setPWResetFontsize(parseInt(input));
                    updateSize(skin.getPWResetFontsize());
                } else {
                    skin.setPWResetFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setPWResetFontcolor(argb);
                updateColor(new Color(skin.getPWResetFontcolor()[1], skin.getPWResetFontcolor()[2], skin.getPWResetFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setPWResetFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getPWResetFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setPWResetFontstyle(getUnderlineFontstyle(selected, skin.getPWResetFontstyle()));
                updateUnderline((skin.getPWResetFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getPWResetFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setPWResetFontstyle(getShadowFontstyle(selected, skin.getPWResetFontstyle()));
                updateShadow((skin.getPWResetFontstyle() == Fontstyle.SHADOW) || (skin.getPWResetFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.pwResetBtnPanel.add(pwResetFontfield);
    }

    private static void initLoadingstatusFontMenu(Color bg) {
        FontChangesMenu.loadingstatusPanel = new JPanel();
        FontChangesMenu.loadingstatusPanel.setBackground(bg);
        FontChangesMenu.loadingstatusPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getLoadingStatusFont();
        int initFontSizeTmp = skin.getLoadingStatusFontsize();
        Color initFontColorTmp = new Color(skin.getLoadingStatusFontcolor()[1], skin.getLoadingStatusFontcolor()[2],
                skin.getLoadingStatusFontcolor()[3]);
        boolean boldTmp = skin.getLoadingStatusFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getLoadingStatusFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getLoadingStatusFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getLoadingStatusFontstyle() == Fontstyle.SHADOW)
                || (skin.getLoadingStatusFontstyle() == Fontstyle.SHADOWUNDERLINE);
        loadingstatusFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setLoadingStatusFont(input);
                    updateFont(skin.getLoadingStatusFont());
                } else {
                    skin.setLoadingStatusFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setLoadingStatusFontsize(parseInt(input));
                    updateSize(skin.getLoadingStatusFontsize());
                } else {
                    skin.setLoadingStatusFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setLoadingStatusFontcolor(argb);
                updateColor(new Color(skin.getLoadingStatusFontcolor()[1], skin.getLoadingStatusFontcolor()[2],
                        skin.getLoadingStatusFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setLoadingStatusFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getLoadingStatusFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setLoadingStatusFontstyle(getUnderlineFontstyle(selected, skin.getLoadingStatusFontstyle()));
                updateUnderline((skin.getLoadingStatusFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getLoadingStatusFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setLoadingStatusFontstyle(getShadowFontstyle(selected, skin.getLoadingStatusFontstyle()));
                updateShadow((skin.getLoadingStatusFontstyle() == Fontstyle.SHADOW)
                        || (skin.getLoadingStatusFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.loadingstatusPanel.add(loadingstatusFontfield);
    }

    private static void initShortMsgFontMenu(Color bg) {
        FontChangesMenu.shortMsgPanel = new JPanel();
        FontChangesMenu.shortMsgPanel.setBackground(bg);
        FontChangesMenu.shortMsgPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getShortMessageFont();
        int initFontSizeTmp = skin.getShortMessageFontsize();
        Color initFontColorTmp = new Color(skin.getShortMessageFontcolor()[1], skin.getShortMessageFontcolor()[2],
                skin.getShortMessageFontcolor()[3]);
        boolean boldTmp = skin.getShortMessageFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getShortMessageFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getShortMessageFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getShortMessageFontstyle() == Fontstyle.SHADOW)
                || (skin.getShortMessageFontstyle() == Fontstyle.SHADOWUNDERLINE);
        shortMessageFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setShortMessageFont(input);
                    updateFont(skin.getShortMessageFont());
                } else {
                    skin.setShortMessageFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setShortMessageFontsize(parseInt(input));
                    updateSize(skin.getShortMessageFontsize());
                } else {
                    skin.setShortMessageFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setShortMessageFontcolor(argb);
                updateColor(new Color(skin.getShortMessageFontcolor()[1], skin.getShortMessageFontcolor()[2],
                        skin.getShortMessageFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setShortMessageFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getShortMessageFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setShortMessageFontstyle(getUnderlineFontstyle(selected, skin.getShortMessageFontstyle()));
                updateUnderline((skin.getShortMessageFontstyle() == Fontstyle.UNDERLINE)
                        | (skin.getShortMessageFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setShortMessageFontstyle(getShadowFontstyle(selected, skin.getShortMessageFontstyle()));
                updateShadow((skin.getShortMessageFontstyle() == Fontstyle.SHADOW)
                        | (skin.getShortMessageFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.shortMsgPanel.add(shortMessageFontfield);
    }

    private static void initStatusListFontMenu(Color bg) {
        FontChangesMenu.statustextListPanel = new JPanel();
        FontChangesMenu.statustextListPanel.setBackground(bg);
        FontChangesMenu.statustextListPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getStatustextFont();
        int initFontSizeTmp = skin.getStatustextFontsize();
        Color initFontColorTmp = new Color(skin.getStatustextFontcolor()[1], skin.getStatustextFontcolor()[2],
                skin.getStatustextFontcolor()[3]);
        boolean boldTmp = skin.getStatustextFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getStatustextFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getStatustextFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getStatustextFontstyle() == Fontstyle.SHADOW)
                || (skin.getStatustextFontstyle() == Fontstyle.SHADOWUNDERLINE);
        statustextFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setStatustextFont(input);
                    updateFont(skin.getStatustextFont());
                } else {
                    skin.setStatustextFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setStatustextFontsize(parseInt(input));
                    updateSize(skin.getStatustextFontsize());
                } else {
                    skin.setStatustextFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setStatustextFontcolor(argb);
                updateColor(new Color(skin.getStatustextFontcolor()[1], skin.getStatustextFontcolor()[2], skin.getStatustextFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setStatustextFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getStatustextFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setStatustextFontstyle(getUnderlineFontstyle(selected, skin.getStatustextFontstyle()));
                updateUnderline((skin.getStatustextFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getStatustextFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setStatustextFontstyle(getShadowFontstyle(selected, skin.getStatustextFontstyle()));
                updateShadow((skin.getStatustextFontstyle() == Fontstyle.SHADOW)
                        || (skin.getStatustextFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.statustextListPanel.add(statustextFontfield);
    }

    private static void initStatusTileFontMenu(Color bg) {
        FontChangesMenu.statustextTilePanel = new JPanel();
        FontChangesMenu.statustextTilePanel.setBackground(bg);
        FontChangesMenu.statustextTilePanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getStatustextZoomedFont();
        int initFontSizeTmp = skin.getStatustextZoomedFontsize();
        Color initFontColorTmp = new Color(skin.getStatustextZoomedFontcolor()[1], skin.getStatustextZoomedFontcolor()[2],
                skin.getStatustextZoomedFontcolor()[3]);
        boolean boldTmp = skin.getStatustextZoomedFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getStatustextZoomedFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOW)
                || (skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE);
        statustextZoomedFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setStatustextZoomedFont(input);
                    updateFont(skin.getStatustextZoomedFont());
                } else {
                    skin.setStatustextZoomedFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setStatustextZoomedFontsize(parseInt(input));
                    updateSize(skin.getStatustextZoomedFontsize());
                } else {
                    skin.setStatustextZoomedFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setStatustextZoomedFontcolor(argb);
                updateColor(new Color(skin.getStatustextZoomedFontcolor()[1], skin.getStatustextZoomedFontcolor()[2],
                        skin.getStatustextZoomedFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setStatustextZoomedFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getStatustextZoomedFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setStatustextZoomedFontstyle(getUnderlineFontstyle(selected, skin.getStatustextZoomedFontstyle()));
                updateUnderline((skin.getStatustextZoomedFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setStatustextZoomedFontstyle(getShadowFontstyle(selected, skin.getStatustextZoomedFontstyle()));
                updateShadow((skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOW)
                        || (skin.getStatustextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.statustextTilePanel.add(statustextZoomedFontfield);
    }

    private static void initNameListFontMenu(Color bg) {
        FontChangesMenu.nametextListPanel = new JPanel();
        FontChangesMenu.nametextListPanel.setBackground(bg);
        FontChangesMenu.nametextListPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getNametextFont();
        int initFontSizeTmp = skin.getNametextFontsize();
        Color initFontColorTmp = new Color(skin.getNametextFontcolor()[1], skin.getNametextFontcolor()[2], skin.getNametextFontcolor()[3]);
        boolean boldTmp = skin.getNametextFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getNametextFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getNametextFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getNametextFontstyle() == Fontstyle.SHADOW) || (skin.getNametextFontstyle() == Fontstyle.SHADOWUNDERLINE);
        nametextFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp, underlineTmp,
                shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setNametextFont(input);
                    updateFont(skin.getNametextFont());
                } else {
                    skin.setNametextFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setNametextFontsize(parseInt(input));
                    updateSize(skin.getNametextFontsize());
                } else {
                    skin.setNametextFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setNametextFontcolor(argb);
                updateColor(new Color(skin.getNametextFontcolor()[1], skin.getNametextFontcolor()[2], skin.getNametextFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setNametextFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getNametextFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setNametextFontstyle(getUnderlineFontstyle(selected, skin.getNametextFontstyle()));
                updateUnderline((skin.getNametextFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getNametextFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setNametextFontstyle(getShadowFontstyle(selected, skin.getNametextFontstyle()));
                updateShadow((skin.getNametextFontstyle() == Fontstyle.SHADOW)
                        || (skin.getNametextFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.nametextListPanel.add(nametextFontfield);
    }

    private static void initNameTileFontMenu(Color bg) {
        FontChangesMenu.nametextTilePanel = new JPanel();
        FontChangesMenu.nametextTilePanel.setBackground(bg);
        FontChangesMenu.nametextTilePanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        String initFontTmp = skin.getNametextZoomedFont();
        int initFontSizeTmp = skin.getNametextZoomedFontsize();
        Color initFontColorTmp = new Color(skin.getNametextZoomedFontcolor()[1], skin.getNametextZoomedFontcolor()[2],
                skin.getNametextZoomedFontcolor()[3]);
        boolean boldTmp = skin.getNametextZoomedFonttype() == Fonttype.BOLD;
        boolean underlineTmp = (skin.getNametextZoomedFontstyle() == Fontstyle.UNDERLINE)
                || (skin.getNametextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE);
        boolean shadowTmp = (skin.getNametextZoomedFontstyle() == Fontstyle.SHADOW)
                || (skin.getNametextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE);
        nametextZoomedFontfield = new FontField(bg, strFontFieldTitle, initFontTmp, initFontSizeTmp, initFontColorTmp, boldTmp,
                underlineTmp, shadowTmp) {
            private static final long serialVersionUID = 1L;

            @Override
            public void fontChosen(String input) {
                if (!input.equals("")) {
                    skin.setNametextZoomedFont(input);
                    updateFont(skin.getNametextZoomedFont());
                } else {
                    skin.setNametextZoomedFont(null);
                }
            }

            @Override
            public void sizeTyped(String input) {
                if (input != null) {
                    skin.setNametextZoomedFontsize(parseInt(input));
                    updateSize(skin.getNametextZoomedFontsize());
                } else {
                    skin.setNametextZoomedFontsize(-1);
                }
            }

            @Override
            public void colorBtnPressed(int[] argb) {
                skin.setNametextZoomedFontcolor(argb);
                updateColor(new Color(skin.getNametextZoomedFontcolor()[1], skin.getNametextZoomedFontcolor()[2],
                        skin.getNametextZoomedFontcolor()[3]));
            }

            @Override
            public void boldPressed(boolean selected) {
                skin.setNametextZoomedFonttype(selected ? Fonttype.BOLD : Fonttype.NORMAL);
                updateBold(skin.getNametextZoomedFonttype() == Fonttype.BOLD);
            }

            @Override
            public void underlinePressed(boolean selected) {
                skin.setNametextZoomedFontstyle(getUnderlineFontstyle(selected, skin.getNametextZoomedFontstyle()));
                updateUnderline((skin.getNametextZoomedFontstyle() == Fontstyle.UNDERLINE)
                        || (skin.getNametextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }

            @Override
            public void shadowPressed(boolean selected) {
                skin.setNametextZoomedFontstyle(getShadowFontstyle(selected, skin.getNametextZoomedFontstyle()));
                updateShadow((skin.getNametextZoomedFontstyle() == Fontstyle.SHADOW)
                        || (skin.getNametextZoomedFontstyle() == Fontstyle.SHADOWUNDERLINE));
            }
        };

        FontChangesMenu.nametextTilePanel.add(nametextZoomedFontfield);
    }

    private static Fontstyle getUnderlineFontstyle(boolean selected, Fontstyle oldValue) {
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

    private static Fontstyle getShadowFontstyle(boolean selected, Fontstyle oldValue) {
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
