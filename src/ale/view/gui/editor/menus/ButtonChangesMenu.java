/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.menus;

import java.awt.Color;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ale.controller.Main;
import ale.model.skin.SkinConstants.Imagetype;
import ale.model.skin.SkinConstants.Position;
import ale.view.gui.editor.fields.BorderField;
import ale.view.gui.editor.fields.CheckField;
import ale.view.gui.editor.fields.ContentField;
import ale.view.gui.editor.fields.ImageField;
import ale.view.gui.editor.fields.MarginField;
import ale.view.gui.editor.fields.PaddingField;
import ale.view.gui.editor.fields.PositionField;
import ale.view.gui.editor.fields.SizeField;
import ale.view.gui.util.VerticalLayout;

public final class ButtonChangesMenu extends ChangesMenus {

    private static boolean init_1;
    private static boolean init_2;
    private static boolean init_3;

    private static JPanel accBtn;
    private static JPanel comBtn;
    private static JPanel stdBtn;
    private static JPanel pwdBtn;
    private static JPanel shutdFrame;
    private static JPanel shutdBtn;
    private static JPanel shutdMenu;
    private static JPanel localeBtn;
    private static JPanel misc;

    private ButtonChangesMenu() {
    }

    public static void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initAccBtn(bg);
                initComBtn(bg);
                initStdBtn(bg);
                init_1 = true;
            }
        };

        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                initPwdBtn(bg);
                initShutdFrame(bg);
                initShutdBtn(bg);
                init_2 = true;
            }
        };

        Runnable _runThree = new Runnable() {

            @Override
            public void run() {
                initShutdMenu(bg);
                initlocaleBtn(bg);
                initMisc(bg);
                init_3 = true;
            }
        };
        Main.executeThreads(_runOne, _runTwo, _runThree);
    }

    protected static boolean isInitialized() {
        return init_1 & init_2 & init_3;
    }

    public static JPanel getAccessibilityBtnMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.accBtn;
    }

    public static JPanel getCommandBtnMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.comBtn;
    }

    public static JPanel getStandardBtnMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.stdBtn;
    }

    public static JPanel getPasswordBtnMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.pwdBtn;
    }

    public static JPanel getShutdownFrameMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.shutdFrame;
    }

    public static JPanel getShutdownBtnMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.shutdBtn;
    }

    public static JPanel getShutdownmenuMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.shutdMenu;
    }

    public static JPanel getLocaleBtnMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.localeBtn;
    }

    public static JPanel getMiscMenu() {
        if (!ButtonChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return ButtonChangesMenu.misc;
    }

    /*
     * ########################################################################## ##
     */

    /*
     * 
     */
    private static void initAccBtn(Color bg) {
        ButtonChangesMenu.accBtn = new JPanel();
        ButtonChangesMenu.accBtn.setBackground(bg);
        ButtonChangesMenu.accBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        Path initialValue = skin.getImgPath_AccessSym();
        final ImageField imageField = new ImageField(initialValue, bg, strImgFieldTitle + "(" + strAccessSym + ")", false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    skin.setImgPath_AccessSym(file.toPath());
                    updatePathField(skin.getImgPath_AccessSym(), false);
                } else {
                    skin.setImgPath_AccessSym(null);
                    updatePathField(skin.getImgPath_AccessSym(), true);
                }
            }
        };

        // size
        int[] size = new int[] { skin.getAccButtonWidth(), skin.getAccButtonHeight() };
        final SizeField sizeField = new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setAccButtonWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setAccButtonHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setAccButtonWidth(FAILURE);
                skin.setAccButtonHeight(FAILURE);
                update(skin.getAccButtonWidth(), skin.getAccButtonHeight());
            }
        };

        // position
        Position pos = skin.getAccButtonPosition();
        boolean[] active = { false, false, false, true, false, true, false, false, false };
        final PositionField positionField = new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
            }

            @Override
            public void topleftOnPressed() {
            }

            @Override
            public void topOnPressed() {
            }

            @Override
            public void centerrightOnPressed() {
                skin.setAccButtonPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setAccButtonPosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
            }

            @Override
            public void bottomrightOnPressed() {
            }

            @Override
            public void bottomleftOnPressed() {
            }

            @Override
            public void bottomOnPressed() {
            }
        };

        // checkbox
        boolean posNone = skin.getAccButtonPosition() == Position.NONE;
        ButtonChangesMenu.accBtn.add(new CheckField(posNone, bg, strHideFieldTitle, strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    skin.setAccButtonPosition(Position.NONE);
                } else {
                    skin.setAccButtonPosition(null);
                }

                imageField.setVisible(!selected);
                sizeField.setVisible(!selected);
                positionField.setVisible(!selected);
            }
        });

        if (posNone) {
            imageField.setVisible(false);
            sizeField.setVisible(false);
            positionField.setVisible(false);
        }

        ButtonChangesMenu.accBtn.add(imageField);
        ButtonChangesMenu.accBtn.add(sizeField);
        ButtonChangesMenu.accBtn.add(positionField);
    }

    /*
     * 
     */
    private static void initComBtn(Color bg) {
        ButtonChangesMenu.comBtn = new JPanel();
        ButtonChangesMenu.comBtn.setBackground(bg);
        ButtonChangesMenu.comBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        {
            Path initialValue = skin.getImgPath_CommandBtn(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_CommandBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_CommandBtn(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_CommandBtn(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_CommandBtn(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = skin.getImgPath_CommandBtn(Imagetype.MOUSEFOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_CommandBtn(file.toPath(), Imagetype.MOUSEFOCUS);
                        skin.setImgPath_CommandBtn(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_CommandBtn(Imagetype.MOUSEFOCUS), false);
                    } else {
                        skin.setImgPath_CommandBtn(null, Imagetype.MOUSEFOCUS);
                        skin.setImgPath_CommandBtn(null, Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_CommandBtn(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = skin.getImgPath_CommandBtn(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_CommandBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_CommandBtn(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_CommandBtn(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_CommandBtn(Imagetype.PRESSED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            ButtonChangesMenu.comBtn.add(pane);
        }

        // image
        {
            Path initialValue = skin.getImgPath_CommandBtnArrow(Imagetype.DEFAULT);
            JPanel symDefault = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_CommandBtnArrow(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_CommandBtnArrow(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_CommandBtnArrow(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_CommandBtnArrow(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = skin.getImgPath_CommandBtnArrow(Imagetype.MOUSEFOCUS);
            JPanel symFocus = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_CommandBtnArrow(file.toPath(), Imagetype.MOUSEFOCUS);
                        skin.setImgPath_CommandBtnArrow(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_CommandBtnArrow(Imagetype.MOUSEFOCUS), false);
                    } else {
                        skin.setImgPath_CommandBtnArrow(null, Imagetype.MOUSEFOCUS);
                        skin.setImgPath_CommandBtnArrow(null, Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_CommandBtnArrow(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = skin.getImgPath_CommandBtnArrow(Imagetype.PRESSED);
            JPanel symPressed = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_CommandBtnArrow(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_CommandBtnArrow(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_CommandBtnArrow(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_CommandBtnArrow(Imagetype.PRESSED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + " (" + strComSym + ")", symDefault, symFocus, symPressed);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            ButtonChangesMenu.comBtn.add(pane);
        }

        // border
        final int[] borderthickness = skin.getCommandButtonBorderthickness();
        ButtonChangesMenu.comBtn.add(new BorderField(borderthickness, bg, strBorderFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (borderthickness == null) ? new int[] { 0, 0, 0, 0 } : borderthickness;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setCommandButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    skin.setCommandButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setCommandButtonBorderthickness(null);
                this.tmp = skin.getCommandButtonBorderthickness();
                return this.tmp;
            }
        });

        // size
        int[] size = new int[] { skin.getCommandButtonWidth(), skin.getCommandButtonHeight() };
        ButtonChangesMenu.comBtn.add(new SizeField(size, 3, bg, strSizeFieldTitle + "(" + strMinSize + ")", true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setCommandButtonMinWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setCommandButtonMinHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setCommandButtonMinWidth(FAILURE);
                skin.setCommandButtonMinHeight(FAILURE);
                update(skin.getCommandButtonWidth(), skin.getCommandButtonHeight());
            }
        });

        // margin
        final int[] margin = skin.getCommandButtonMargin();
        ButtonChangesMenu.comBtn.add(new MarginField(margin, bg, strMarginFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (margin == null) ? new int[] { 0, 0, 0, 0 } : margin;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setCommandButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    skin.setCommandButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setCommandButtonMargin(null);
                this.tmp = skin.getCommandButtonMargin();
                return this.tmp;
            }
        });

        // padding
        final int[] padding = skin.getCommandButtonPadding();
        ButtonChangesMenu.comBtn.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (padding == null) ? new int[] { 0, 0, 0, 0 } : padding;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                try {
                    i = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    i = FAILURE;
                }

                if (i == FAILURE) {
                    skin.setCommandButtonPadding(null);
                } else {
                    this.tmp[index] = i;
                    skin.setCommandButtonPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setCommandButtonPadding(null);
                this.tmp = skin.getCommandButtonPadding();
                return this.tmp;
            }
        });

        // checkbox
        boolean b = skin.getCommandButtonArrowpositionIsRight();
        ButtonChangesMenu.comBtn.add(new CheckField(b, bg, strComSymbolRightTitle, strComSymRight) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setCommandButtonArrowpositionOnRight(selected);
            }
        });
    }

    /*
     * blue generic btn, used as background for symbols and text, like cancel, locale and the misc options btn.
     */
    private static void initStdBtn(Color bg) {
        ButtonChangesMenu.stdBtn = new JPanel();
        ButtonChangesMenu.stdBtn.setBackground(bg);
        ButtonChangesMenu.stdBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        {
            Path initialValue = skin.getImgPath_StandardBtn(Imagetype.DEFAULT);
            JPanel defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_StandardBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_StandardBtn(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_StandardBtn(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_StandardBtn(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = skin.getImgPath_StandardBtn(Imagetype.MOUSEFOCUS);
            JPanel focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_StandardBtn(file.toPath(), Imagetype.MOUSEFOCUS_KEYFOCUS);
                        skin.setImgPath_StandardBtn(file.toPath(), Imagetype.MOUSEFOCUS);
                        skin.setImgPath_StandardBtn(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_StandardBtn(Imagetype.MOUSEFOCUS), false);
                    } else {
                        skin.setImgPath_StandardBtn(null, Imagetype.MOUSEFOCUS_KEYFOCUS);
                        skin.setImgPath_StandardBtn(null, Imagetype.MOUSEFOCUS);
                        skin.setImgPath_StandardBtn(null, Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_StandardBtn(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = skin.getImgPath_StandardBtn(Imagetype.PRESSED);
            JPanel pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_StandardBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_StandardBtn(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_StandardBtn(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_StandardBtn(Imagetype.PRESSED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            ButtonChangesMenu.stdBtn.add(pane);
        }

        // border
        final int[] borderthickness = skin.getStandardButtonBorderthickness();
        ButtonChangesMenu.stdBtn.add(new BorderField(borderthickness, bg, strBorderFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (borderthickness == null) ? new int[] { 0, 0, 0, 0 } : borderthickness;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setStandardButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    skin.setStandardButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setStandardButtonBorderthickness(null);
                this.tmp = skin.getStandardButtonBorderthickness();
                return this.tmp;
            }
        });

        // size
        int[] size = new int[] { skin.getStandardButtonWidth(), skin.getStandardButtonHeight() };
        ButtonChangesMenu.stdBtn.add(new SizeField(size, 3, bg, strSizeFieldTitle + "(" + strMinSize + ")", true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setStandardButtonMinWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setStandardButtonMinHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setStandardButtonMinWidth(FAILURE);
                skin.setStandardButtonMinHeight(FAILURE);
                update(skin.getStandardButtonWidth(), skin.getStandardButtonHeight());
            }
        });

        // margin
        final int[] margin = skin.getStandardButtonMargin();
        ButtonChangesMenu.stdBtn.add(new MarginField(margin, bg, strMarginFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (margin == null) ? new int[] { 0, 0, 0, 0 } : margin;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setStandardButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    skin.setStandardButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setStandardButtonMargin(null);
                this.tmp = skin.getStandardButtonMargin();
                return this.tmp;
            }
        });

        // padding
        final int[] padding = skin.getStandardButtonPadding();
        ButtonChangesMenu.stdBtn.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (padding == null) ? new int[] { 0, 0, 0, 0 } : padding;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                try {
                    i = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    i = FAILURE;
                }

                if (i == FAILURE) {
                    skin.setStandardButtonPadding(null);
                } else {
                    this.tmp[index] = i;
                    skin.setStandardButtonPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setStandardButtonPadding(null);
                this.tmp = skin.getStandardButtonPadding();
                return this.tmp;
            }
        });

        // position
        Position pos = skin.getStandardButtonPosition();
        boolean[] active = { false, false, false, true, true, true, false, false, false };
        ButtonChangesMenu.stdBtn.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
            }

            @Override
            public void topleftOnPressed() {
            }

            @Override
            public void topOnPressed() {
            }

            @Override
            public void centerrightOnPressed() {
                skin.setStandardButtonPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setStandardButtonPosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                skin.setStandardButtonPosition(Position.CENTER);
            }

            @Override
            public void bottomrightOnPressed() {
            }

            @Override
            public void bottomleftOnPressed() {
            }

            @Override
            public void bottomOnPressed() {
            }
        });
    }

    /*
     * 
     */
    private static void initPwdBtn(Color bg) {
        ButtonChangesMenu.pwdBtn = new JPanel();
        ButtonChangesMenu.pwdBtn.setBackground(bg);
        ButtonChangesMenu.pwdBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        final JTabbedPane imagepane;
        {
            Path initialValue = skin.getImgPath_PWBtn(Imagetype.DEFAULT);
            JPanel defaultImage = new ImageField(initialValue, bg, false) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_PWBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_PWBtn(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_PWBtn(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_PWBtn(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = skin.getImgPath_PWBtn(Imagetype.MOUSEFOCUS_KEYFOCUS);
            JPanel focusImage = new ImageField(initialValue, bg, false) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_PWBtn(file.toPath(), Imagetype.MOUSEFOCUS_KEYFOCUS);
                        updatePathField(skin.getImgPath_PWBtn(Imagetype.MOUSEFOCUS_KEYFOCUS), false);
                    } else {
                        skin.setImgPath_PWBtn(null, Imagetype.MOUSEFOCUS_KEYFOCUS);
                        updatePathField(skin.getImgPath_PWBtn(Imagetype.MOUSEFOCUS_KEYFOCUS), true);
                    }
                }
            };

            initialValue = skin.getImgPath_PWBtn(Imagetype.PRESSED);
            JPanel pressedImage = new ImageField(initialValue, bg, false) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_PWBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_PWBtn(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_PWBtn(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_PWBtn(Imagetype.PRESSED), true);
                    }
                }
            };

            imagepane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            imagepane.setTitleAt(0, strImageDefault);
            imagepane.setTitleAt(1, strImageFocus);
            imagepane.setTitleAt(2, strImagePressed);
        }

        // size
        int[] size = new int[] { skin.getPasswordButtonWidth(), skin.getPasswordButtonHeight() };
        final SizeField sizefield = new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setPasswordButtonWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setPasswordButtonHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setPasswordButtonWidth(FAILURE);
                skin.setPasswordButtonHeight(FAILURE);
                update(skin.getPasswordButtonWidth(), skin.getPasswordButtonHeight());
            }
        };

        // border
        final int[] borderthickness = skin.getPasswordButtonBorderthickness();
        final BorderField borderfield = new BorderField(skin.getPasswordButtonBorderthickness(), bg, strBorderFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (borderthickness == null) ? new int[] { 0, 0, 0, 0 } : borderthickness;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setPasswordButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    skin.setPasswordButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setPasswordButtonBorderthickness(null);
                this.tmp = skin.getPasswordButtonBorderthickness();
                return this.tmp;
            }
        };

        // margin
        final int[] margin = skin.getPasswordButtonMargin();
        final MarginField marginfield = new MarginField(margin, bg, strMarginFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (margin == null) ? new int[] { 0, 0, 0, 0 } : margin;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setPasswordButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    skin.setPasswordButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setPasswordButtonMargin(null);
                this.tmp = skin.getPasswordButtonMargin();
                return this.tmp;
            }
        };

        // checkbox
        boolean hidden = (skin.getPasswordButtonWidth() == 0) || (skin.getPasswordButtonHeight() == 0);
        ButtonChangesMenu.pwdBtn.add(new CheckField(hidden, bg, strHideFieldTitle, strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    skin.setPasswordButtonWidth(0);
                    skin.setPasswordButtonHeight(0);

                    imagepane.setVisible(false);
                    sizefield.setVisible(false);
                    borderfield.setVisible(false);
                    marginfield.setVisible(false);

                } else {
                    skin.setPasswordButtonWidth(-1);
                    skin.setPasswordButtonHeight(-1);
                    sizefield.update(skin.getPasswordButtonWidth(), skin.getPasswordButtonHeight());

                    imagepane.setVisible(true);
                    sizefield.setVisible(true);
                    borderfield.setVisible(true);
                    marginfield.setVisible(true);
                }
            }
        });

        if (hidden) {
            imagepane.setVisible(false);
            sizefield.setVisible(false);
            borderfield.setVisible(false);
            marginfield.setVisible(false);
        }

        ButtonChangesMenu.pwdBtn.add(imagepane);
        ButtonChangesMenu.pwdBtn.add(sizefield);
        ButtonChangesMenu.pwdBtn.add(borderfield);
        ButtonChangesMenu.pwdBtn.add(marginfield);
    }

    /*
     * 
     */
    private static void initShutdFrame(Color bg) {
        ButtonChangesMenu.shutdFrame = new JPanel();
        ButtonChangesMenu.shutdFrame.setBackground(bg);
        ButtonChangesMenu.shutdFrame.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // position
        Position pos = skin.getShutdownframePosition();
        boolean[] active = { false, true, false, true, false, true, false, true, false };
        ButtonChangesMenu.shutdFrame.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
            }

            @Override
            public void topleftOnPressed() {
            }

            @Override
            public void topOnPressed() {
                skin.setShutdownframePosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                skin.setShutdownframePosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setShutdownframePosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
            }

            @Override
            public void bottomrightOnPressed() {
            }

            @Override
            public void bottomleftOnPressed() {
            }

            @Override
            public void bottomOnPressed() {
                skin.setShutdownframePosition(Position.BOTTOM);
            }
        });
    }

    /*
     * 
     */
    private static void initShutdBtn(Color bg) {
        ButtonChangesMenu.shutdBtn = new JPanel();
        ButtonChangesMenu.shutdBtn.setBackground(bg);
        ButtonChangesMenu.shutdBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        final JTabbedPane pane;
        {
            Path initialValue = skin.getImgPath_ShutdownBtn(Imagetype.DEFAULT);
            JPanel defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_ShutdownBtn(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_ShutdownBtn(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_ShutdownBtn(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = skin.getImgPath_ShutdownBtn(Imagetype.MOUSEFOCUS);
            JPanel focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.MOUSEFOCUS_KEYFOCUS);
                        skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.MOUSEFOCUS);
                        skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_ShutdownBtn(Imagetype.MOUSEFOCUS), false);
                    } else {
                        skin.setImgPath_ShutdownBtn(null, Imagetype.MOUSEFOCUS_KEYFOCUS);
                        skin.setImgPath_ShutdownBtn(null, Imagetype.MOUSEFOCUS);
                        skin.setImgPath_ShutdownBtn(null, Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_ShutdownBtn(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = skin.getImgPath_ShutdownBtn(Imagetype.PRESSED);
            JPanel pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_ShutdownBtn(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_ShutdownBtn(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_ShutdownBtn(Imagetype.PRESSED), true);
                    }
                }
            };

            pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);
            ButtonChangesMenu.shutdBtn.add(pane);
        }

        // border
        final int[] borderthickness = skin.getShutdownButtonBorderthickness();
        final BorderField borderfield = new BorderField(borderthickness, bg, strBorderFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (borderthickness == null) ? new int[] { 0, 0, 0, 0 } : borderthickness;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setShutdownButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    skin.setShutdownButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setShutdownButtonBorderthickness(null);
                this.tmp = skin.getShutdownButtonBorderthickness();
                return this.tmp;
            }
        };

        // margin
        final int[] margin = skin.getShutdownButtonMargin();
        final MarginField marginfield = new MarginField(margin, bg, strMarginFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (margin == null) ? new int[] { 0, 0, 0, 0 } : margin;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setShutdownButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    skin.setShutdownButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setShutdownButtonMargin(null);
                this.tmp = skin.getShutdownButtonMargin();
                return this.tmp;
            }
        };

        // position
        Position pos = skin.getShutdownButtonPosition();
        boolean[] active = { false, true, false, true, false, true, false, true, false };
        final PositionField posfield = new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
            }

            @Override
            public void topleftOnPressed() {
            }

            @Override
            public void topOnPressed() {
                skin.setShutdownButtonPosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                skin.setShutdownButtonPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setShutdownButtonPosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
            }

            @Override
            public void bottomrightOnPressed() {
            }

            @Override
            public void bottomleftOnPressed() {
            }

            @Override
            public void bottomOnPressed() {
                skin.setShutdownButtonPosition(Position.BOTTOM);
            }
        };

        // size
        int[] size = new int[] { skin.getShutdownButtonWidth(), skin.getShutdownButtonHeight() };
        final SizeField sizefield = new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setShutdownButtonWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setShutdownButtonHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setShutdownButtonWidth(FAILURE);
                skin.setShutdownButtonHeight(FAILURE);
                update(skin.getShutdownButtonWidth(), skin.getShutdownButtonHeight());
            }
        };

        // image
        Path initialValue = skin.getImgPath_ShutdownSym();
        final ImageField sym = new ImageField(initialValue, bg, strImgFieldTitle + "(" + strShdSym + ")", false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    skin.setImgPath_ShutdownSym(file.toPath());
                    updatePathField(skin.getImgPath_ShutdownSym(), false);
                } else {
                    skin.setImgPath_ShutdownSym(null);
                    updatePathField(skin.getImgPath_ShutdownSym(), true);
                }
            }
        };

        // image
        initialValue = skin.getImgPath_ShutdownUpdateSym();
        final ImageField symUpd = new ImageField(initialValue, bg, strImgFieldTitle + "(" + strShdUpdateSym + ")", false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    skin.setImgPath_ShutdownUpdateSym(file.toPath());
                    updatePathField(skin.getImgPath_ShutdownUpdateSym(), false);
                } else {
                    skin.setImgPath_ShutdownUpdateSym(null);
                    updatePathField(skin.getImgPath_ShutdownUpdateSym(), true);
                }
            }
        };

        // Content
        final ContentField contentfield = new ContentField(skin.getShutdownButtonContent(), bg, strContentFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void contentUpdate(String input) {
                if ((input == null) || input.equals("")) {
                    skin.setShutdownButtonContent(null);

                    sym.setVisible(true);
                    symUpd.setVisible(true);

                } else {
                    skin.setShutdownButtonContent(input);

                    sym.setVisible(false);
                    symUpd.setVisible(false);
                }
            }
        };

        // checkbox
        boolean hidden = (skin.getShutdownButtonWidth() == 0) || (skin.getShutdownButtonHeight() == 0);
        ButtonChangesMenu.shutdBtn.add(new CheckField(hidden, bg, strHideFieldTitle, strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    skin.setShutdownButtonWidth(0);
                    skin.setShutdownButtonHeight(0);

                    pane.setVisible(false);
                    borderfield.setVisible(false);
                    marginfield.setVisible(false);
                    posfield.setVisible(false);
                    sizefield.setVisible(false);
                    contentfield.setVisible(false);
                    sym.setVisible(false);
                    symUpd.setVisible(false);

                } else {
                    skin.setShutdownButtonWidth(-1);
                    skin.setShutdownButtonHeight(-1);
                    sizefield.update(skin.getShutdownButtonWidth(), skin.getShutdownButtonHeight());

                    pane.setVisible(true);
                    borderfield.setVisible(true);
                    marginfield.setVisible(true);
                    posfield.setVisible(true);
                    sizefield.setVisible(true);
                    contentfield.setVisible(true);
                    sym.setVisible(true);
                    symUpd.setVisible(true);
                }
            }
        });

        ButtonChangesMenu.shutdBtn.add(pane);
        ButtonChangesMenu.shutdBtn.add(borderfield);
        ButtonChangesMenu.shutdBtn.add(marginfield);
        ButtonChangesMenu.shutdBtn.add(posfield);
        ButtonChangesMenu.shutdBtn.add(sizefield);
        ButtonChangesMenu.shutdBtn.add(contentfield);
        ButtonChangesMenu.shutdBtn.add(sym);
        ButtonChangesMenu.shutdBtn.add(symUpd);

        if (hidden) {
            pane.setVisible(false);
            borderfield.setVisible(false);
            marginfield.setVisible(false);
            posfield.setVisible(false);
            sizefield.setVisible(false);
            contentfield.setVisible(false);
            sym.setVisible(false);
            symUpd.setVisible(false);
        }

        if ((skin.getShutdownButtonContent() != null) && !skin.getShutdownButtonContent().equals("")) {
            sym.setVisible(false);
            symUpd.setVisible(false);
        }
    }

    /*
     * 
     */
    private static void initShutdMenu(Color bg) {
        ButtonChangesMenu.shutdMenu = new JPanel();
        ButtonChangesMenu.shutdMenu.setBackground(bg);
        ButtonChangesMenu.shutdMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        final JTabbedPane pane;
        {
            Path initialValue = skin.getImgPath_ShutdownMenu(Imagetype.DEFAULT);
            JPanel defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_ShutdownMenu(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_ShutdownMenu(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_ShutdownMenu(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = skin.getImgPath_ShutdownMenu(Imagetype.MOUSEFOCUS);
            JPanel focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.MOUSEFOCUS_KEYFOCUS);
                        skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.MOUSEFOCUS);
                        skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_ShutdownMenu(Imagetype.MOUSEFOCUS), false);
                    } else {
                        skin.setImgPath_ShutdownMenu(null, Imagetype.MOUSEFOCUS_KEYFOCUS);
                        skin.setImgPath_ShutdownMenu(null, Imagetype.MOUSEFOCUS);
                        skin.setImgPath_ShutdownMenu(null, Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_ShutdownMenu(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = skin.getImgPath_ShutdownMenu(Imagetype.PRESSED);
            JPanel pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_ShutdownMenu(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_ShutdownMenu(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_ShutdownMenu(Imagetype.PRESSED), true);
                    }
                }
            };

            pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);
        }

        // border
        final int[] borderthickness = skin.getShutdownmenuButtonBorderthickness();
        final BorderField borderfield = new BorderField(borderthickness, bg, strBorderFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (borderthickness == null) ? new int[] { 0, 0, 0, 0 } : borderthickness;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setShutdownmenuButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    skin.setShutdownmenuButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setShutdownmenuButtonBorderthickness(null);
                this.tmp = skin.getShutdownmenuButtonBorderthickness();
                return this.tmp;
            }
        };

        // margin
        final int[] margin = skin.getShutdownmenuButtonMargin();
        final MarginField marginfield = new MarginField(margin, bg, strMarginFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (margin == null) ? new int[] { 0, 0, 0, 0 } : margin;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                try {
                    i = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    i = FAILURE;
                }

                if (i == FAILURE) {
                    skin.setShutdownmenuButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    skin.setShutdownmenuButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setShutdownmenuButtonMargin(null);
                this.tmp = skin.getShutdownmenuButtonMargin();
                return this.tmp;
            }
        };

        // size
        int[] size = new int[] { skin.getShutdownmenuButtonWidth(), skin.getShutdownmenuButtonHeight() };
        final SizeField sizefield = new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setShutdownmenuButtonWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setShutdownmenuButtonHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setShutdownmenuButtonWidth(FAILURE);
                skin.setShutdownmenuButtonHeight(FAILURE);
                update(skin.getShutdownmenuButtonWidth(), skin.getShutdownmenuButtonHeight());
            }
        };

        // image
        Path initialValue = skin.getImgPath_ShutdownArrowSym();
        final ImageField sym = new ImageField(initialValue, bg, strImgFieldTitle + "(" + strShdArrowSym + ")", false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    skin.setImgPath_ShutdownArrowSym(file.toPath());
                    updatePathField(skin.getImgPath_ShutdownArrowSym(), false);
                } else {
                    skin.setImgPath_ShutdownArrowSym(null);
                    updatePathField(skin.getImgPath_ShutdownArrowSym(), true);
                }
            }
        };

        final ContentField contentfield = new ContentField(skin.getShutdownmenuButtonContent(), bg, strContentFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void contentUpdate(String input) {
                if ((input == null) || input.equals("")) {
                    skin.setShutdownmenuButtonContent(null);

                    sym.setVisible(true);
                } else {
                    skin.setShutdownmenuButtonContent(input);

                    sym.setVisible(false);
                }
            }
        };

        // checkbox
        boolean hidden = (skin.getShutdownmenuButtonWidth() == 0) || (skin.getShutdownmenuButtonHeight() == 0);
        ButtonChangesMenu.shutdMenu.add(new CheckField(hidden, bg, strHideFieldTitle, strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    skin.setShutdownmenuButtonWidth(0);
                    skin.setShutdownmenuButtonHeight(0);

                    pane.setVisible(false);
                    borderfield.setVisible(false);
                    marginfield.setVisible(false);
                    sizefield.setVisible(false);
                    contentfield.setVisible(false);
                    sym.setVisible(false);

                } else {
                    skin.setShutdownmenuButtonWidth(-1);
                    skin.setShutdownmenuButtonHeight(-1);
                    sizefield.update(skin.getShutdownmenuButtonWidth(), skin.getShutdownmenuButtonHeight());

                    pane.setVisible(true);
                    borderfield.setVisible(true);
                    marginfield.setVisible(true);
                    sizefield.setVisible(true);
                    contentfield.setVisible(true);
                    sym.setVisible(true);
                }
            }
        });

        ButtonChangesMenu.shutdMenu.add(pane);
        ButtonChangesMenu.shutdMenu.add(borderfield);
        ButtonChangesMenu.shutdMenu.add(marginfield);
        ButtonChangesMenu.shutdMenu.add(sizefield);
        ButtonChangesMenu.shutdMenu.add(contentfield);
        ButtonChangesMenu.shutdMenu.add(sym);

        if (hidden) {
            pane.setVisible(false);
            borderfield.setVisible(false);
            marginfield.setVisible(false);
            sizefield.setVisible(false);
            contentfield.setVisible(false);
            sym.setVisible(false);
        }

        if ((skin.getShutdownmenuButtonContent() != null) && !skin.getShutdownmenuButtonContent().equals("")) {
            sym.setVisible(false);
        }
    }

    /*
     * 
     */
    private static void initlocaleBtn(Color bg) {
        ButtonChangesMenu.localeBtn = new JPanel();
        ButtonChangesMenu.localeBtn.setBackground(bg);
        ButtonChangesMenu.localeBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // position
        Position pos = skin.getLocaleButtonPosition();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        final PositionField positionField = new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                skin.setLocaleButtonPosition(Position.TOPRIGHT);
            }

            @Override
            public void topleftOnPressed() {
                skin.setLocaleButtonPosition(Position.TOPLEFT);
            }

            @Override
            public void topOnPressed() {
                skin.setLocaleButtonPosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                skin.setLocaleButtonPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setLocaleButtonPosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                skin.setLocaleButtonPosition(Position.CENTER);
            }

            @Override
            public void bottomrightOnPressed() {
                skin.setLocaleButtonPosition(Position.BOTTOMRIGHT);
            }

            @Override
            public void bottomleftOnPressed() {
                skin.setLocaleButtonPosition(Position.BOTTOMLEFT);
            }

            @Override
            public void bottomOnPressed() {
                skin.setLocaleButtonPosition(Position.BOTTOM);
            }
        };

        // padding
        final int[] padding = skin.getLocaleButtonPadding();
        final PaddingField paddingfield = new PaddingField(padding, bg, strPaddingFieldTitle) {
            private static final long serialVersionUID = 1L;

            private int[] tmp = (padding == null) ? new int[] { 0, 0, 0, 0 } : padding;

            @Override
            public void topFieldOnKeyReleased(String input) {
                controlAndSet(input, 1);
            }

            @Override
            public void rightFieldOnKeyReleased(String input) {
                controlAndSet(input, 2);
            }

            @Override
            public void leftFieldOnKeyReleased(String input) {
                controlAndSet(input, 0);
            }

            @Override
            public void bottomFieldOnKeyReleased(String input) {
                controlAndSet(input, 3);
            }

            private void controlAndSet(String s, int index) {
                int i = FAILURE;
                if (s != null) {
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        i = FAILURE;
                    }
                }

                if (i == FAILURE) {
                    skin.setLocaleButtonPadding(null);
                } else {
                    this.tmp[index] = i;
                    skin.setLocaleButtonPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setLocaleButtonPadding(null);
                this.tmp = skin.getLocaleButtonPadding();
                return this.tmp;
            }
        };

        // checkbox
        boolean hide = !skin.getLocaleButtonVisible();
        ButtonChangesMenu.localeBtn.add(new CheckField(hide, bg, strHideFieldTitle, strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setLocaleButtonVisibility(!selected);
                positionField.setVisible(!selected);
                paddingfield.setVisible(!selected);
            }
        });

        if (hide) {
            positionField.setVisible(false);
            paddingfield.setVisible(false);
        }

        ButtonChangesMenu.localeBtn.add(positionField);
        ButtonChangesMenu.localeBtn.add(paddingfield);
    }

    /*
     * 
     */
    private static void initMisc(Color bg) {
        ButtonChangesMenu.misc = new JPanel();
        ButtonChangesMenu.misc.setBackground(bg);
        ButtonChangesMenu.misc.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // checkbox
        boolean b = skin.getCombinedShdAcc();
        ButtonChangesMenu.misc.add(new CheckField(b, bg, strCombineAccShdTitle, strCombineAccShdLabel) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setCombinedShdAcc(selected);
            }
        });

        // position
        Position pos = skin.getCombinedShdAccPosition();
        boolean[] active = { false, true, false, true, false, true, false, true, false };
        ButtonChangesMenu.misc.add(new PositionField(pos, active, bg, strCombinedAccShdPosTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
            }

            @Override
            public void topleftOnPressed() {
            }

            @Override
            public void topOnPressed() {
                skin.setCombinedShdAcc_Position(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                skin.setCombinedShdAcc_Position(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setCombinedShdAcc_Position(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
            }

            @Override
            public void bottomrightOnPressed() {
            }

            @Override
            public void bottomleftOnPressed() {
            }

            @Override
            public void bottomOnPressed() {
                skin.setCombinedShdAcc_Position(Position.BOTTOM);
            }
        });
    }
}
