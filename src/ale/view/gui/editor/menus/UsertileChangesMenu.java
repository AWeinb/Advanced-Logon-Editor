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
import ale.view.gui.editor.fields.ImageField;
import ale.view.gui.editor.fields.MarginField;
import ale.view.gui.editor.fields.Numberfield;
import ale.view.gui.editor.fields.PaddingField;
import ale.view.gui.editor.fields.PositionField;
import ale.view.gui.editor.fields.SizeField;
import ale.view.gui.util.VerticalLayout;

public final class UsertileChangesMenu extends ChangesMenus {

    private static boolean init_1;
    private static boolean init_2;

    private static JPanel usermenuImageMenu;
    private static JPanel usermenuImageframeMenu;
    private static JPanel usermenuLayoutMenu;
    private static JPanel usermenuPWFieldMenu;

    private UsertileChangesMenu() {
    }

    public static void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initUsermenuImageMenu(bg);
                initUsermenuImageframeMenu(bg);
                init_1 = true;
            }
        };

        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                initUsermenuLayoutMenu(bg);
                initUsermenuPWFieldMenu(bg);
                init_2 = true;
            }
        };
        Main.executeThreads(_runOne, _runTwo);
    }

    protected static boolean isInitialized() {
        return init_1 & init_2;
    }

    public static JPanel getUsertileImageMenu() {
        if (!UsertileChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return UsertileChangesMenu.usermenuImageMenu;
    }

    public static JPanel getUsertileImageframeMenu() {
        if (!UsertileChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return UsertileChangesMenu.usermenuImageframeMenu;
    }

    public static JPanel getUsertileLayoutMenu() {
        if (!UsertileChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return UsertileChangesMenu.usermenuLayoutMenu;
    }

    public static JPanel getUsertilePWFieldMenu() {
        if (!UsertileChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return UsertileChangesMenu.usermenuPWFieldMenu;
    }

    /*
     * ########################################################################## ##
     */

    /*
     * 
     */
    private static void initUsermenuImageMenu(Color bg) {
        UsertileChangesMenu.usermenuImageMenu = new JPanel();
        UsertileChangesMenu.usermenuImageMenu.setBackground(bg);
        UsertileChangesMenu.usermenuImageMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // size
        int[] size = new int[] { skin.getUsertileImageWidth(), skin.getUsertileImageHeight() };
        UsertileChangesMenu.usermenuImageMenu.add(new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setUsertileImageWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setUsertileImageHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setUsertileImageWidth(-1);
                skin.setUsertileImageHeight(-1);
                update(skin.getUsertileImageWidth(), skin.getUsertileImageHeight());
            }
        });

        // padding
        final int[] padding = skin.getUsertileImagePadding();
        UsertileChangesMenu.usermenuImageMenu.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
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
                    skin.setUsertileImagePadding(null);
                } else {
                    this.tmp[index] = i;
                    skin.setUsertileImagePadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setUsertileImagePadding(null);
                this.tmp = skin.getUsertileImagePadding();
                return this.tmp;
            }
        });

        // position
        Position pos = skin.getUsertileImagePosition();
        boolean[] active = { false, false, false, true, true, true, false, false, false };
        UsertileChangesMenu.usermenuImageMenu.add(new PositionField(pos, active, bg, strPosFieldTitle) {
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
                skin.setUsertileImagePosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setUsertileImagePosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                skin.setUsertileImagePosition(Position.CENTER);
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

    private static void initUsermenuImageframeMenu(Color bg) {
        UsertileChangesMenu.usermenuImageframeMenu = new JPanel();
        UsertileChangesMenu.usermenuImageframeMenu.setBackground(bg);
        UsertileChangesMenu.usermenuImageframeMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // imagePath
        Path initialValue = skin.getImgPath_UsertileImage();
        UsertileChangesMenu.usermenuImageframeMenu.add(new ImageField(initialValue, bg, strImgFieldTitle + "(" + strUserimageOverlay + ")",
                true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    skin.setImgPath_UsertileImage(file.toPath());
                    updatePathField(skin.getImgPath_UsertileImage(), false);
                } else {
                    skin.setImgPath_UsertileImage(null);
                    updatePathField(skin.getImgPath_UsertileImage(), true);
                }
            }
        });

        // size
        int[] size = new int[] { skin.getUsertileImageFrameWidth(), skin.getUsertileImageFrameHeight() };
        UsertileChangesMenu.usermenuImageframeMenu.add(new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setUsertileImageFrameWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setUsertileImageFrameHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setUsertileImageFrameWidth(-1);
                skin.setUsertileImageFrameHeight(-1);
                update(skin.getUsertileImageFrameWidth(), skin.getUsertileImageFrameHeight());
            }
        });
    }

    /*
     * 
     */
    private static void initUsermenuLayoutMenu(Color bg) {
        UsertileChangesMenu.usermenuLayoutMenu = new JPanel();
        UsertileChangesMenu.usermenuLayoutMenu.setBackground(bg);
        UsertileChangesMenu.usermenuLayoutMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // checkbox
        boolean b = skin.getUsertileLayoutIsHorizontal();
        UsertileChangesMenu.usermenuLayoutMenu.add(new CheckField(b, bg, strUsertileHorizontalFieldTitle, strUsertileHorizontalLabel) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setUsertileLayout(selected);
            }
        });

        // checkbox
        b = skin.getPWAreaPositionOnRightOfTexts();
        UsertileChangesMenu.usermenuLayoutMenu
                .add(new CheckField(b, bg, strUsertilePWRightOfTextsFieldTitle, strUsertilePWRightOfTextLabel) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void btnPressed(boolean selected) {
                        skin.setPWAreaPositionOnRightOfTexts(selected);
                    }
                });

        // checkbox
        b = skin.getStatusOnRightSide();
        UsertileChangesMenu.usermenuLayoutMenu
                .add(new CheckField(b, bg, strUsertileStatusOnRightFieldTitle, strUsertileStatusOnRightLabel) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void btnPressed(boolean selected) {
                        skin.setStatusPositionOnRight(selected);
                    }
                });

        // position
        Position pos = skin.getUsertilePosition();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        UsertileChangesMenu.usermenuLayoutMenu.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                skin.setUsertilePosition(Position.TOPRIGHT);
            }

            @Override
            public void topleftOnPressed() {
                skin.setUsertilePosition(Position.TOPLEFT);
            }

            @Override
            public void topOnPressed() {
                skin.setUsertilePosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                skin.setUsertilePosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setUsertilePosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                skin.setUsertilePosition(Position.CENTER);
            }

            @Override
            public void bottomrightOnPressed() {
                skin.setUsertilePosition(Position.BOTTOMRIGHT);
            }

            @Override
            public void bottomleftOnPressed() {
                skin.setUsertilePosition(Position.BOTTOMLEFT);
            }

            @Override
            public void bottomOnPressed() {
                skin.setUsertilePosition(Position.BOTTOM);
            }
        });
    }

    private static void initUsermenuPWFieldMenu(Color bg) {
        UsertileChangesMenu.usermenuPWFieldMenu = new JPanel();
        UsertileChangesMenu.usermenuPWFieldMenu.setBackground(bg);
        UsertileChangesMenu.usermenuPWFieldMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        {
            Path initialValue = skin.getImgPath_PWField(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, false) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_PWField(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_PWField(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_PWField(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_PWField(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = skin.getImgPath_PWField(Imagetype.MOUSEFOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, false) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_PWField(file.toPath(), Imagetype.MOUSEFOCUS);
                        skin.setImgPath_PWField(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_PWField(Imagetype.MOUSEFOCUS), false);
                    } else {
                        skin.setImgPath_PWField(null, Imagetype.MOUSEFOCUS);
                        skin.setImgPath_PWField(null, Imagetype.KEYFOCUS);
                        updatePathField(skin.getImgPath_PWField(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = skin.getImgPath_PWField(Imagetype.DISABLED);
            ImageField pressedImage = new ImageField(initialValue, bg, false) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_PWField(file.toPath(), Imagetype.DISABLED);
                        updatePathField(skin.getImgPath_PWField(Imagetype.DISABLED), false);
                    } else {
                        skin.setImgPath_PWField(null, Imagetype.DISABLED);
                        updatePathField(skin.getImgPath_PWField(Imagetype.DISABLED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImageSelected);

            UsertileChangesMenu.usermenuPWFieldMenu.add(pane);
        }

        // border
        final int[] borderthickness = skin.getPWfieldBorderthickness();
        UsertileChangesMenu.usermenuPWFieldMenu.add(new BorderField(borderthickness, bg, strBorderFieldTitle) {
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
                    skin.setPWfieldBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    skin.setPWfieldBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setPWfieldBorderthickness(null);
                this.tmp = skin.getPWfieldBorderthickness();
                return this.tmp;
            }
        });

        // size
        int[] size = new int[] { skin.getPWfieldWidth(), skin.getPWfieldHeight() };
        UsertileChangesMenu.usermenuPWFieldMenu.add(new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setPWfieldWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setPWfieldHeight(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setPWfieldWidth(-1);
                skin.setPWfieldHeight(-1);
                update(skin.getPWfieldWidth(), skin.getPWfieldHeight());
            }
        });

        // padding
        final int[] margin = skin.getPWfieldMargin();
        UsertileChangesMenu.usermenuPWFieldMenu.add(new MarginField(margin, bg, strMarginFieldTitle) {
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
                    skin.setPWfieldMargin(null);
                } else {
                    this.tmp[index] = i;
                    skin.setPWfieldMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setPWfieldMargin(null);
                this.tmp = skin.getPWfieldMargin();
                return this.tmp;
            }
        });

        int tmp = skin.getPasswordfieldUpshift();
        UsertileChangesMenu.usermenuPWFieldMenu.add(new Numberfield(tmp, 3, bg, strPWAreaUpShift, strPWAreaShiftamount, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                skin.setPasswordfieldUpshift(parseInt(input));
            }

            @Override
            public void resetOnClick() {
                //
            }
        });

        tmp = skin.getPasswordfieldDownshift();
        UsertileChangesMenu.usermenuPWFieldMenu.add(new Numberfield(tmp, 3, bg, strPWAreaDownShift, strPWAreaShiftamount, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                skin.setPasswordfieldDownshift(parseInt(input));
            }

            @Override
            public void resetOnClick() {
                //
            }
        });

        tmp = skin.getPasswordfieldRightshift();
        UsertileChangesMenu.usermenuPWFieldMenu.add(new Numberfield(tmp, 3, bg, strPWAreaRightShift, strPWAreaShiftamount, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                skin.setPasswordfieldRightshift(parseInt(input));
            }

            @Override
            public void resetOnClick() {
                //
            }
        });
    }
}
