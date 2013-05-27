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
import ale.model.skin.SkinPropertiesVO;
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

    private boolean init_1;
    private boolean init_2;
    private boolean init_3;

    private JPanel accBtn;
    private JPanel comBtn;
    private JPanel stdBtn;
    private JPanel pwdBtn;
    private JPanel shutdFrame;
    private JPanel shutdBtn;
    private JPanel shutdMenu;
    private JPanel localeBtn;
    private JPanel misc;

    private SkinPropertiesVO skin;

    public ButtonChangesMenu(Color bg, SkinPropertiesVO skin) {
        this.skin = skin;
        initialize(bg);
    }

    private void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initAccBtn(bg);
                initComBtn(bg);
                initStdBtn(bg);
                ButtonChangesMenu.this.init_1 = true;
            }
        };

        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                initPwdBtn(bg);
                initShutdFrame(bg);
                initShutdBtn(bg);
                ButtonChangesMenu.this.init_2 = true;
            }
        };

        Runnable _runThree = new Runnable() {

            @Override
            public void run() {
                initShutdMenu(bg);
                initlocaleBtn(bg);
                initMisc(bg);
                ButtonChangesMenu.this.init_3 = true;
            }
        };
        Main.executeThreads(_runOne, _runTwo, _runThree);
    }

    public boolean isInitialized() {
        return this.init_1 & this.init_2 & this.init_3;
    }

    public void shutdown() {
        this.accBtn = null;
        this.comBtn = null;
        this.stdBtn = null;
        this.pwdBtn = null;
        this.shutdFrame = null;
        this.shutdBtn = null;
        this.shutdMenu = null;
        this.localeBtn = null;
        this.misc = null;
    }

    public JPanel getAccessibilityBtnMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.accBtn;
    }

    public JPanel getCommandBtnMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.comBtn;
    }

    public JPanel getStandardBtnMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.stdBtn;
    }

    public JPanel getPasswordBtnMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.pwdBtn;
    }

    public JPanel getShutdownFrameMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.shutdFrame;
    }

    public JPanel getShutdownBtnMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.shutdBtn;
    }

    public JPanel getShutdownmenuMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.shutdMenu;
    }

    public JPanel getLocaleBtnMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.localeBtn;
    }

    public JPanel getMiscMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.misc;
    }

    /*
     * ########################################################################## ##
     */

    /*
     * 
     */
    private void initAccBtn(Color bg) {
        this.accBtn = new JPanel();
        this.accBtn.setBackground(bg);
        this.accBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        Path initialValue = this.skin.getImgPath_AccessSym();
        final ImageField imageField = new ImageField(initialValue, bg, strImgFieldTitle + "(" + strAccessSym + ")", false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    ButtonChangesMenu.this.skin.setImgPath_AccessSym(file.toPath());
                    updatePathField(ButtonChangesMenu.this.skin.getImgPath_AccessSym(), false);
                } else {
                    ButtonChangesMenu.this.skin.setImgPath_AccessSym(null);
                    updatePathField(ButtonChangesMenu.this.skin.getImgPath_AccessSym(), true);
                }
            }
        };

        // size
        int[] size = new int[] { this.skin.getAccButtonWidth(), this.skin.getAccButtonHeight() };
        final SizeField sizeField = new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setAccButtonWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setAccButtonHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                ButtonChangesMenu.this.skin.setAccButtonWidth(FAILURE);
                ButtonChangesMenu.this.skin.setAccButtonHeight(FAILURE);
                update(ButtonChangesMenu.this.skin.getAccButtonWidth(), ButtonChangesMenu.this.skin.getAccButtonHeight());
            }
        };

        // position
        Position pos = this.skin.getAccButtonPosition();
        boolean[] active = { false, false, false, true, false, true, false, false, false };
        final PositionField positionField = new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

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
                ButtonChangesMenu.this.skin.setAccButtonPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                ButtonChangesMenu.this.skin.setAccButtonPosition(Position.LEFT);
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
        boolean posNone = this.skin.getAccButtonPosition() == Position.NONE;
        this.accBtn.add(new CheckField(posNone, bg, strHideFieldTitle, strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    ButtonChangesMenu.this.skin.setAccButtonPosition(Position.NONE);
                } else {
                    ButtonChangesMenu.this.skin.setAccButtonPosition(null);
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

        this.accBtn.add(imageField);
        this.accBtn.add(sizeField);
        this.accBtn.add(positionField);
    }

    /*
     * 
     */
    private void initComBtn(Color bg) {
        this.comBtn = new JPanel();
        this.comBtn.setBackground(bg);
        this.comBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        {
            Path initialValue = this.skin.getImgPath_CommandBtn(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtn(Imagetype.DEFAULT), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtn(null, Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtn(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_CommandBtn(Imagetype.MOUSEFOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtn(file.toPath(), Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtn(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtn(Imagetype.MOUSEFOCUS), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtn(null, Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtn(null, Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtn(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_CommandBtn(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtn(Imagetype.PRESSED), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtn(null, Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtn(Imagetype.PRESSED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            this.comBtn.add(pane);
        }

        // image
        {
            Path initialValue = this.skin.getImgPath_CommandBtnArrow(Imagetype.DEFAULT);
            JPanel symDefault = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtnArrow(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtnArrow(Imagetype.DEFAULT), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtnArrow(null, Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtnArrow(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_CommandBtnArrow(Imagetype.MOUSEFOCUS);
            JPanel symFocus = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtnArrow(file.toPath(), Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtnArrow(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtnArrow(Imagetype.MOUSEFOCUS), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtnArrow(null, Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtnArrow(null, Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtnArrow(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_CommandBtnArrow(Imagetype.PRESSED);
            JPanel symPressed = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtnArrow(file.toPath(), Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtnArrow(Imagetype.PRESSED), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_CommandBtnArrow(null, Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_CommandBtnArrow(Imagetype.PRESSED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + " (" + strComSym + ")", symDefault, symFocus, symPressed);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            this.comBtn.add(pane);
        }

        // border
        final int[] borderthickness = this.skin.getCommandButtonBorderthickness();
        this.comBtn.add(new BorderField(borderthickness, bg, strBorderFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setCommandButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setCommandButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setCommandButtonBorderthickness(null);
                this.tmp = ButtonChangesMenu.this.skin.getCommandButtonBorderthickness();
                return this.tmp;
            }
        });

        // size
        int[] size = new int[] { this.skin.getCommandButtonWidth(), this.skin.getCommandButtonHeight() };
        this.comBtn.add(new SizeField(size, 3, bg, strSizeFieldTitle + "(" + strMinSize + ")", true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setCommandButtonMinWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setCommandButtonMinHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                ButtonChangesMenu.this.skin.setCommandButtonMinWidth(FAILURE);
                ButtonChangesMenu.this.skin.setCommandButtonMinHeight(FAILURE);
                update(ButtonChangesMenu.this.skin.getCommandButtonWidth(), ButtonChangesMenu.this.skin.getCommandButtonHeight());
            }
        });

        // margin
        final int[] margin = this.skin.getCommandButtonMargin();
        this.comBtn.add(new MarginField(margin, bg, strMarginFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setCommandButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setCommandButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setCommandButtonMargin(null);
                this.tmp = ButtonChangesMenu.this.skin.getCommandButtonMargin();
                return this.tmp;
            }
        });

        // padding
        final int[] padding = this.skin.getCommandButtonPadding();
        this.comBtn.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setCommandButtonPadding(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setCommandButtonPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setCommandButtonPadding(null);
                this.tmp = ButtonChangesMenu.this.skin.getCommandButtonPadding();
                return this.tmp;
            }
        });

        // checkbox
        boolean b = this.skin.getCommandButtonArrowpositionIsRight();
        this.comBtn.add(new CheckField(b, bg, strComSymbolRightTitle, strComSymRight) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                ButtonChangesMenu.this.skin.setCommandButtonArrowpositionOnRight(selected);
            }
        });
    }

    /*
     * blue generic btn, used as background for symbols and text, like cancel, locale and the misc options btn.
     */
    private void initStdBtn(Color bg) {
        this.stdBtn = new JPanel();
        this.stdBtn.setBackground(bg);
        this.stdBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        {
            Path initialValue = this.skin.getImgPath_StandardBtn(Imagetype.DEFAULT);
            JPanel defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_StandardBtn(Imagetype.DEFAULT), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(null, Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_StandardBtn(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_StandardBtn(Imagetype.MOUSEFOCUS);
            JPanel focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(file.toPath(), Imagetype.MOUSEFOCUS_KEYFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(file.toPath(), Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_StandardBtn(Imagetype.MOUSEFOCUS), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(null, Imagetype.MOUSEFOCUS_KEYFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(null, Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(null, Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_StandardBtn(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_StandardBtn(Imagetype.PRESSED);
            JPanel pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_StandardBtn(Imagetype.PRESSED), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_StandardBtn(null, Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_StandardBtn(Imagetype.PRESSED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            this.stdBtn.add(pane);
        }

        // border
        final int[] borderthickness = this.skin.getStandardButtonBorderthickness();
        this.stdBtn.add(new BorderField(borderthickness, bg, strBorderFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setStandardButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setStandardButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setStandardButtonBorderthickness(null);
                this.tmp = ButtonChangesMenu.this.skin.getStandardButtonBorderthickness();
                return this.tmp;
            }
        });

        // size
        int[] size = new int[] { this.skin.getStandardButtonWidth(), this.skin.getStandardButtonHeight() };
        this.stdBtn.add(new SizeField(size, 3, bg, strSizeFieldTitle + "(" + strMinSize + ")", true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setStandardButtonMinWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setStandardButtonMinHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                ButtonChangesMenu.this.skin.setStandardButtonMinWidth(FAILURE);
                ButtonChangesMenu.this.skin.setStandardButtonMinHeight(FAILURE);
                update(ButtonChangesMenu.this.skin.getStandardButtonWidth(), ButtonChangesMenu.this.skin.getStandardButtonHeight());
            }
        });

        // margin
        final int[] margin = this.skin.getStandardButtonMargin();
        this.stdBtn.add(new MarginField(margin, bg, strMarginFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setStandardButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setStandardButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setStandardButtonMargin(null);
                this.tmp = ButtonChangesMenu.this.skin.getStandardButtonMargin();
                return this.tmp;
            }
        });

        // padding
        final int[] padding = this.skin.getStandardButtonPadding();
        this.stdBtn.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setStandardButtonPadding(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setStandardButtonPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setStandardButtonPadding(null);
                this.tmp = ButtonChangesMenu.this.skin.getStandardButtonPadding();
                return this.tmp;
            }
        });

        // position
        Position pos = this.skin.getStandardButtonPosition();
        boolean[] active = { false, false, false, true, true, true, false, false, false };
        this.stdBtn.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

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
                ButtonChangesMenu.this.skin.setStandardButtonPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                ButtonChangesMenu.this.skin.setStandardButtonPosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                ButtonChangesMenu.this.skin.setStandardButtonPosition(Position.CENTER);
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
    private void initPwdBtn(Color bg) {
        this.pwdBtn = new JPanel();
        this.pwdBtn.setBackground(bg);
        this.pwdBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        final JTabbedPane imagepane;
        {
            Path initialValue = this.skin.getImgPath_PWBtn(Imagetype.DEFAULT);
            JPanel defaultImage = new ImageField(initialValue, bg, false) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_PWBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_PWBtn(Imagetype.DEFAULT), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_PWBtn(null, Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_PWBtn(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_PWBtn(Imagetype.MOUSEFOCUS_KEYFOCUS);
            JPanel focusImage = new ImageField(initialValue, bg, false) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_PWBtn(file.toPath(), Imagetype.MOUSEFOCUS_KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_PWBtn(Imagetype.MOUSEFOCUS_KEYFOCUS), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_PWBtn(null, Imagetype.MOUSEFOCUS_KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_PWBtn(Imagetype.MOUSEFOCUS_KEYFOCUS), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_PWBtn(Imagetype.PRESSED);
            JPanel pressedImage = new ImageField(initialValue, bg, false) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_PWBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_PWBtn(Imagetype.PRESSED), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_PWBtn(null, Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_PWBtn(Imagetype.PRESSED), true);
                    }
                }
            };

            imagepane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            imagepane.setTitleAt(0, strImageDefault);
            imagepane.setTitleAt(1, strImageFocus);
            imagepane.setTitleAt(2, strImagePressed);
        }

        // size
        int[] size = new int[] { this.skin.getPasswordButtonWidth(), this.skin.getPasswordButtonHeight() };
        final SizeField sizefield = new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setPasswordButtonWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setPasswordButtonHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                ButtonChangesMenu.this.skin.setPasswordButtonWidth(FAILURE);
                ButtonChangesMenu.this.skin.setPasswordButtonHeight(FAILURE);
                update(ButtonChangesMenu.this.skin.getPasswordButtonWidth(), ButtonChangesMenu.this.skin.getPasswordButtonHeight());
            }
        };

        // border
        final int[] borderthickness = this.skin.getPasswordButtonBorderthickness();
        final BorderField borderfield = new BorderField(this.skin.getPasswordButtonBorderthickness(), bg, strBorderFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setPasswordButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setPasswordButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setPasswordButtonBorderthickness(null);
                this.tmp = ButtonChangesMenu.this.skin.getPasswordButtonBorderthickness();
                return this.tmp;
            }
        };

        // margin
        final int[] margin = this.skin.getPasswordButtonMargin();
        final MarginField marginfield = new MarginField(margin, bg, strMarginFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setPasswordButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setPasswordButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setPasswordButtonMargin(null);
                this.tmp = ButtonChangesMenu.this.skin.getPasswordButtonMargin();
                return this.tmp;
            }
        };

        // checkbox
        boolean hidden = (this.skin.getPasswordButtonWidth() == 0) || (this.skin.getPasswordButtonHeight() == 0);
        this.pwdBtn.add(new CheckField(hidden, bg, strHideFieldTitle, strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    ButtonChangesMenu.this.skin.setPasswordButtonWidth(0);
                    ButtonChangesMenu.this.skin.setPasswordButtonHeight(0);

                    imagepane.setVisible(false);
                    sizefield.setVisible(false);
                    borderfield.setVisible(false);
                    marginfield.setVisible(false);

                } else {
                    ButtonChangesMenu.this.skin.setPasswordButtonWidth(-1);
                    ButtonChangesMenu.this.skin.setPasswordButtonHeight(-1);
                    sizefield.update(ButtonChangesMenu.this.skin.getPasswordButtonWidth(),
                            ButtonChangesMenu.this.skin.getPasswordButtonHeight());

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

        this.pwdBtn.add(imagepane);
        this.pwdBtn.add(sizefield);
        this.pwdBtn.add(borderfield);
        this.pwdBtn.add(marginfield);
    }

    /*
     * 
     */
    private void initShutdFrame(Color bg) {
        this.shutdFrame = new JPanel();
        this.shutdFrame.setBackground(bg);
        this.shutdFrame.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // position
        Position pos = this.skin.getShutdownframePosition();
        boolean[] active = { false, true, false, true, false, true, false, true, false };
        this.shutdFrame.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
            }

            @Override
            public void topleftOnPressed() {
            }

            @Override
            public void topOnPressed() {
                ButtonChangesMenu.this.skin.setShutdownframePosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                ButtonChangesMenu.this.skin.setShutdownframePosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                ButtonChangesMenu.this.skin.setShutdownframePosition(Position.LEFT);
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
                ButtonChangesMenu.this.skin.setShutdownframePosition(Position.BOTTOM);
            }
        });
    }

    /*
     * 
     */
    private void initShutdBtn(Color bg) {
        this.shutdBtn = new JPanel();
        this.shutdBtn.setBackground(bg);
        this.shutdBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        final JTabbedPane pane;
        {
            Path initialValue = this.skin.getImgPath_ShutdownBtn(Imagetype.DEFAULT);
            JPanel defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownBtn(Imagetype.DEFAULT), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(null, Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownBtn(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_ShutdownBtn(Imagetype.MOUSEFOCUS);
            JPanel focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.MOUSEFOCUS_KEYFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownBtn(Imagetype.MOUSEFOCUS), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(null, Imagetype.MOUSEFOCUS_KEYFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(null, Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(null, Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownBtn(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_ShutdownBtn(Imagetype.PRESSED);
            JPanel pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownBtn(Imagetype.PRESSED), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownBtn(null, Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownBtn(Imagetype.PRESSED), true);
                    }
                }
            };

            pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);
            this.shutdBtn.add(pane);
        }

        // border
        final int[] borderthickness = this.skin.getShutdownButtonBorderthickness();
        final BorderField borderfield = new BorderField(borderthickness, bg, strBorderFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setShutdownButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setShutdownButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setShutdownButtonBorderthickness(null);
                this.tmp = ButtonChangesMenu.this.skin.getShutdownButtonBorderthickness();
                return this.tmp;
            }
        };

        // margin
        final int[] margin = this.skin.getShutdownButtonMargin();
        final MarginField marginfield = new MarginField(margin, bg, strMarginFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setShutdownButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setShutdownButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setShutdownButtonMargin(null);
                this.tmp = ButtonChangesMenu.this.skin.getShutdownButtonMargin();
                return this.tmp;
            }
        };

        // position
        Position pos = this.skin.getShutdownButtonPosition();
        boolean[] active = { false, true, false, true, false, true, false, true, false };
        final PositionField posfield = new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
            }

            @Override
            public void topleftOnPressed() {
            }

            @Override
            public void topOnPressed() {
                ButtonChangesMenu.this.skin.setShutdownButtonPosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                ButtonChangesMenu.this.skin.setShutdownButtonPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                ButtonChangesMenu.this.skin.setShutdownButtonPosition(Position.LEFT);
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
                ButtonChangesMenu.this.skin.setShutdownButtonPosition(Position.BOTTOM);
            }
        };

        // size
        int[] size = new int[] { this.skin.getShutdownButtonWidth(), this.skin.getShutdownButtonHeight() };
        final SizeField sizefield = new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setShutdownButtonWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setShutdownButtonHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                ButtonChangesMenu.this.skin.setShutdownButtonWidth(FAILURE);
                ButtonChangesMenu.this.skin.setShutdownButtonHeight(FAILURE);
                update(ButtonChangesMenu.this.skin.getShutdownButtonWidth(), ButtonChangesMenu.this.skin.getShutdownButtonHeight());
            }
        };

        // image
        Path initialValue = this.skin.getImgPath_ShutdownSym();
        final ImageField sym = new ImageField(initialValue, bg, strImgFieldTitle + "(" + strShdSym + ")", false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    ButtonChangesMenu.this.skin.setImgPath_ShutdownSym(file.toPath());
                    updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownSym(), false);
                } else {
                    ButtonChangesMenu.this.skin.setImgPath_ShutdownSym(null);
                    updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownSym(), true);
                }
            }
        };

        // image
        initialValue = this.skin.getImgPath_ShutdownUpdateSym();
        final ImageField symUpd = new ImageField(initialValue, bg, strImgFieldTitle + "(" + strShdUpdateSym + ")", false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    ButtonChangesMenu.this.skin.setImgPath_ShutdownUpdateSym(file.toPath());
                    updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownUpdateSym(), false);
                } else {
                    ButtonChangesMenu.this.skin.setImgPath_ShutdownUpdateSym(null);
                    updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownUpdateSym(), true);
                }
            }
        };

        // Content
        final ContentField contentfield = new ContentField(this.skin.getShutdownButtonContent(), bg, strContentFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void contentUpdate(String input) {
                if ((input == null) || input.equals("")) {
                    ButtonChangesMenu.this.skin.setShutdownButtonContent(null);

                    sym.setVisible(true);
                    symUpd.setVisible(true);

                } else {
                    ButtonChangesMenu.this.skin.setShutdownButtonContent(input);

                    sym.setVisible(false);
                    symUpd.setVisible(false);
                }
            }
        };

        // checkbox
        boolean hidden = (this.skin.getShutdownButtonWidth() == 0) || (this.skin.getShutdownButtonHeight() == 0);
        this.shutdBtn.add(new CheckField(hidden, bg, strHideFieldTitle, strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    ButtonChangesMenu.this.skin.setShutdownButtonWidth(0);
                    ButtonChangesMenu.this.skin.setShutdownButtonHeight(0);

                    pane.setVisible(false);
                    borderfield.setVisible(false);
                    marginfield.setVisible(false);
                    posfield.setVisible(false);
                    sizefield.setVisible(false);
                    contentfield.setVisible(false);
                    sym.setVisible(false);
                    symUpd.setVisible(false);

                } else {
                    ButtonChangesMenu.this.skin.setShutdownButtonWidth(-1);
                    ButtonChangesMenu.this.skin.setShutdownButtonHeight(-1);
                    sizefield.update(ButtonChangesMenu.this.skin.getShutdownButtonWidth(),
                            ButtonChangesMenu.this.skin.getShutdownButtonHeight());

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

        this.shutdBtn.add(pane);
        this.shutdBtn.add(borderfield);
        this.shutdBtn.add(marginfield);
        this.shutdBtn.add(posfield);
        this.shutdBtn.add(sizefield);
        this.shutdBtn.add(contentfield);
        this.shutdBtn.add(sym);
        this.shutdBtn.add(symUpd);

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

        if ((this.skin.getShutdownButtonContent() != null) && !this.skin.getShutdownButtonContent().equals("")) {
            sym.setVisible(false);
            symUpd.setVisible(false);
        }
    }

    /*
     * 
     */
    private void initShutdMenu(Color bg) {
        this.shutdMenu = new JPanel();
        this.shutdMenu.setBackground(bg);
        this.shutdMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        final JTabbedPane pane;
        {
            Path initialValue = this.skin.getImgPath_ShutdownMenu(Imagetype.DEFAULT);
            JPanel defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownMenu(Imagetype.DEFAULT), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(null, Imagetype.DEFAULT);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownMenu(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_ShutdownMenu(Imagetype.MOUSEFOCUS);
            JPanel focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.MOUSEFOCUS_KEYFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownMenu(Imagetype.MOUSEFOCUS), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(null, Imagetype.MOUSEFOCUS_KEYFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(null, Imagetype.MOUSEFOCUS);
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(null, Imagetype.KEYFOCUS);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownMenu(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_ShutdownMenu(Imagetype.PRESSED);
            JPanel pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(file.toPath(), Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownMenu(Imagetype.PRESSED), false);
                    } else {
                        ButtonChangesMenu.this.skin.setImgPath_ShutdownMenu(null, Imagetype.PRESSED);
                        updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownMenu(Imagetype.PRESSED), true);
                    }
                }
            };

            pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);
        }

        // border
        final int[] borderthickness = this.skin.getShutdownmenuButtonBorderthickness();
        final BorderField borderfield = new BorderField(borderthickness, bg, strBorderFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setShutdownmenuButtonBorderthickness(null);
                this.tmp = ButtonChangesMenu.this.skin.getShutdownmenuButtonBorderthickness();
                return this.tmp;
            }
        };

        // margin
        final int[] margin = this.skin.getShutdownmenuButtonMargin();
        final MarginField marginfield = new MarginField(margin, bg, strMarginFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonMargin(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setShutdownmenuButtonMargin(null);
                this.tmp = ButtonChangesMenu.this.skin.getShutdownmenuButtonMargin();
                return this.tmp;
            }
        };

        // size
        int[] size = new int[] { this.skin.getShutdownmenuButtonWidth(), this.skin.getShutdownmenuButtonHeight() };
        final SizeField sizefield = new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setShutdownmenuButtonWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!ButtonChangesMenu.this.skin.setShutdownmenuButtonHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                ButtonChangesMenu.this.skin.setShutdownmenuButtonWidth(FAILURE);
                ButtonChangesMenu.this.skin.setShutdownmenuButtonHeight(FAILURE);
                update(ButtonChangesMenu.this.skin.getShutdownmenuButtonWidth(), ButtonChangesMenu.this.skin.getShutdownmenuButtonHeight());
            }
        };

        // image
        Path initialValue = this.skin.getImgPath_ShutdownArrowSym();
        final ImageField sym = new ImageField(initialValue, bg, strImgFieldTitle + "(" + strShdArrowSym + ")", false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    ButtonChangesMenu.this.skin.setImgPath_ShutdownArrowSym(file.toPath());
                    updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownArrowSym(), false);
                } else {
                    ButtonChangesMenu.this.skin.setImgPath_ShutdownArrowSym(null);
                    updatePathField(ButtonChangesMenu.this.skin.getImgPath_ShutdownArrowSym(), true);
                }
            }
        };

        final ContentField contentfield = new ContentField(this.skin.getShutdownmenuButtonContent(), bg, strContentFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void contentUpdate(String input) {
                if ((input == null) || input.equals("")) {
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonContent(null);

                    sym.setVisible(true);
                } else {
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonContent(input);

                    sym.setVisible(false);
                }
            }
        };

        // checkbox
        boolean hidden = (this.skin.getShutdownmenuButtonWidth() == 0) || (this.skin.getShutdownmenuButtonHeight() == 0);
        this.shutdMenu.add(new CheckField(hidden, bg, strHideFieldTitle, strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonWidth(0);
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonHeight(0);

                    pane.setVisible(false);
                    borderfield.setVisible(false);
                    marginfield.setVisible(false);
                    sizefield.setVisible(false);
                    contentfield.setVisible(false);
                    sym.setVisible(false);

                } else {
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonWidth(-1);
                    ButtonChangesMenu.this.skin.setShutdownmenuButtonHeight(-1);
                    sizefield.update(ButtonChangesMenu.this.skin.getShutdownmenuButtonWidth(),
                            ButtonChangesMenu.this.skin.getShutdownmenuButtonHeight());

                    pane.setVisible(true);
                    borderfield.setVisible(true);
                    marginfield.setVisible(true);
                    sizefield.setVisible(true);
                    contentfield.setVisible(true);
                    sym.setVisible(true);
                }
            }
        });

        this.shutdMenu.add(pane);
        this.shutdMenu.add(borderfield);
        this.shutdMenu.add(marginfield);
        this.shutdMenu.add(sizefield);
        this.shutdMenu.add(contentfield);
        this.shutdMenu.add(sym);

        if (hidden) {
            pane.setVisible(false);
            borderfield.setVisible(false);
            marginfield.setVisible(false);
            sizefield.setVisible(false);
            contentfield.setVisible(false);
            sym.setVisible(false);
        }

        if ((this.skin.getShutdownmenuButtonContent() != null) && !this.skin.getShutdownmenuButtonContent().equals("")) {
            sym.setVisible(false);
        }
    }

    /*
     * 
     */
    private void initlocaleBtn(Color bg) {
        this.localeBtn = new JPanel();
        this.localeBtn.setBackground(bg);
        this.localeBtn.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // position
        Position pos = this.skin.getLocaleButtonPosition();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        final PositionField positionField = new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.TOPRIGHT);
            }

            @Override
            public void topleftOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.TOPLEFT);
            }

            @Override
            public void topOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.CENTER);
            }

            @Override
            public void bottomrightOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.BOTTOMRIGHT);
            }

            @Override
            public void bottomleftOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.BOTTOMLEFT);
            }

            @Override
            public void bottomOnPressed() {
                ButtonChangesMenu.this.skin.setLocaleButtonPosition(Position.BOTTOM);
            }
        };

        // padding
        final int[] padding = this.skin.getLocaleButtonPadding();
        final PaddingField paddingfield = new PaddingField(padding, bg, strPaddingFieldTitle) {
            private final long serialVersionUID = 1L;

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
                    ButtonChangesMenu.this.skin.setLocaleButtonPadding(null);
                } else {
                    this.tmp[index] = i;
                    ButtonChangesMenu.this.skin.setLocaleButtonPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                ButtonChangesMenu.this.skin.setLocaleButtonPadding(null);
                this.tmp = ButtonChangesMenu.this.skin.getLocaleButtonPadding();
                return this.tmp;
            }
        };

        // checkbox
        boolean hide = !this.skin.getLocaleButtonVisible();
        this.localeBtn.add(new CheckField(hide, bg, strHideFieldTitle, strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                ButtonChangesMenu.this.skin.setLocaleButtonVisibility(!selected);
                positionField.setVisible(!selected);
                paddingfield.setVisible(!selected);
            }
        });

        if (hide) {
            positionField.setVisible(false);
            paddingfield.setVisible(false);
        }

        this.localeBtn.add(positionField);
        this.localeBtn.add(paddingfield);
    }

    /*
     * 
     */
    private void initMisc(Color bg) {
        this.misc = new JPanel();
        this.misc.setBackground(bg);
        this.misc.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // checkbox
        boolean b = this.skin.getCombinedShdAcc();
        this.misc.add(new CheckField(b, bg, strCombineAccShdTitle, strCombineAccShdLabel) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                ButtonChangesMenu.this.skin.setCombinedShdAcc(selected);
            }
        });

        // position
        Position pos = this.skin.getCombinedShdAccPosition();
        boolean[] active = { false, true, false, true, false, true, false, true, false };
        this.misc.add(new PositionField(pos, active, bg, strCombinedAccShdPosTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
            }

            @Override
            public void topleftOnPressed() {
            }

            @Override
            public void topOnPressed() {
                ButtonChangesMenu.this.skin.setCombinedShdAcc_Position(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                ButtonChangesMenu.this.skin.setCombinedShdAcc_Position(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                ButtonChangesMenu.this.skin.setCombinedShdAcc_Position(Position.LEFT);
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
                ButtonChangesMenu.this.skin.setCombinedShdAcc_Position(Position.BOTTOM);
            }
        });
    }
}
