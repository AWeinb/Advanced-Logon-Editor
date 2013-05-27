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
import ale.view.gui.editor.fields.CheckField;
import ale.view.gui.editor.fields.ImageField;
import ale.view.gui.editor.fields.Numberfield;
import ale.view.gui.editor.fields.PaddingField;
import ale.view.gui.editor.fields.PositionField;
import ale.view.gui.editor.fields.SizeField;
import ale.view.gui.util.VerticalLayout;

public final class UserlistChangesMenu extends ChangesMenus {

    private static boolean init_1;

    private static JPanel userlistImageMenu;
    private static JPanel userlistImageframeMenu;
    private static JPanel userlistLayoutMenu;

    private UserlistChangesMenu() {
    }

    public static void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initUserlistImageMenu(bg);
                initUserlistImageframeMenu(bg);
                initUserlistLayoutMenu(bg);
                init_1 = true;
            }
        };
        Main.executeThreads(_runOne);
    }

    protected static boolean isInitialized() {
        return init_1;
    }

    public static JPanel getUserlistImageMenu() {
        if (!UserlistChangesMenu.init_1) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return UserlistChangesMenu.userlistImageMenu;
    }

    public static JPanel getUserlistImageframeMenu() {
        if (!UserlistChangesMenu.init_1) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return UserlistChangesMenu.userlistImageframeMenu;
    }

    public static JPanel getUserlistLayoutMenu() {
        if (!UserlistChangesMenu.init_1) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return UserlistChangesMenu.userlistLayoutMenu;
    }

    /*
     * ########################################################################## ##
     */

    private static void initUserlistImageMenu(Color bg) {
        UserlistChangesMenu.userlistImageMenu = new JPanel();
        UserlistChangesMenu.userlistImageMenu.setBackground(bg);
        UserlistChangesMenu.userlistImageMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // size
        int[] size = new int[] { skin.getUserlistImageWidth(), skin.getUserlistImageHeight() };
        UserlistChangesMenu.userlistImageMenu.add(new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setUserlistImageWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setUserlistImageHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setUserlistImageWidth(-1);
                skin.setUserlistImageHeight(-1);
                update(skin.getUserlistImageWidth(), skin.getUserlistImageHeight());
            }
        });

        // padding
        final int[] padding = skin.getUserlistImagePadding();
        UserlistChangesMenu.userlistImageMenu.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
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
                    skin.setUserlistImagePadding(null);
                } else {
                    this.tmp[index] = i;
                    skin.setUserlistImagePadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setUserlistImagePadding(null);
                this.tmp = skin.getUserlistImagePadding();
                return this.tmp;
            }
        });
    }

    private static void initUserlistImageframeMenu(Color bg) {
        UserlistChangesMenu.userlistImageframeMenu = new JPanel();
        UserlistChangesMenu.userlistImageframeMenu.setBackground(bg);
        UserlistChangesMenu.userlistImageframeMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        {
            Path initialValue = skin.getImgPath_UserlistImage(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_UserlistImage(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_UserlistImage(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_UserlistImage(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_UserlistImage(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = skin.getImgPath_UserlistImage(Imagetype.MOUSEFOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_UserlistImage(file.toPath(), Imagetype.MOUSEFOCUS);
                        skin.setImgPath_UserlistImage(file.toPath(), Imagetype.FOCUSSELECTED);
                        updatePathField(skin.getImgPath_UserlistImage(Imagetype.MOUSEFOCUS), false);
                    } else {
                        skin.setImgPath_UserlistImage(null, Imagetype.MOUSEFOCUS);
                        skin.setImgPath_UserlistImage(null, Imagetype.FOCUSSELECTED);
                        updatePathField(skin.getImgPath_UserlistImage(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = skin.getImgPath_UserlistImage(Imagetype.SELECTED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_UserlistImage(file.toPath(), Imagetype.SELECTED);
                        updatePathField(skin.getImgPath_UserlistImage(Imagetype.SELECTED), false);
                    } else {
                        skin.setImgPath_UserlistImage(null, Imagetype.SELECTED);
                        updatePathField(skin.getImgPath_UserlistImage(Imagetype.SELECTED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strUserimageOverlay + ")", defaultImage, focusImage,
                    pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImageSelected);

            UserlistChangesMenu.userlistImageframeMenu.add(pane);
        }

        // size
        int[] size = new int[] { skin.getUserlistImageFrameWidth(), skin.getUserlistImageFrameHeight() };
        UserlistChangesMenu.userlistImageframeMenu.add(new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setUserlistImageFrameWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setUserlistImageFrameHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setUserlistImageFrameWidth(-1);
                skin.setUserlistImageFrameHeight(-1);
                update(skin.getUserlistImageFrameWidth(), skin.getUserlistImageFrameHeight());
            }
        });
    }

    /*
     * 
     */
    private static void initUserlistLayoutMenu(Color bg) {
        UserlistChangesMenu.userlistLayoutMenu = new JPanel();
        UserlistChangesMenu.userlistLayoutMenu.setBackground(bg);
        UserlistChangesMenu.userlistLayoutMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // size
        UserlistChangesMenu.userlistLayoutMenu.add(new Numberfield(skin.getUserlistHeight(), 3, bg, strSizeFieldTitle, strNumfieldHeight,
                true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                if (!skin.setUserlistHeight(parseInt(input))) {
                    update(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setUserlistHeight(-1);
                update(skin.getUserlistHeight());
            }
        });

        // checkbox
        boolean b = skin.getUserlistVertical();
        UserlistChangesMenu.userlistLayoutMenu.add(new CheckField(b, bg, strUserlistVTitle, strUserlistVBtn) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setUserlistVertical(selected);
            }
        });

        // position
        Position pos = skin.getUserlistPosition();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        UserlistChangesMenu.userlistLayoutMenu.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                skin.setUserlistPosition(Position.TOPRIGHT);
            }

            @Override
            public void topleftOnPressed() {
                skin.setUserlistPosition(Position.TOPLEFT);
            }

            @Override
            public void topOnPressed() {
                skin.setUserlistPosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                skin.setUserlistPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setUserlistPosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                skin.setUserlistPosition(Position.CENTER);
            }

            @Override
            public void bottomrightOnPressed() {
                skin.setUserlistPosition(Position.BOTTOMRIGHT);
            }

            @Override
            public void bottomleftOnPressed() {
                skin.setUserlistPosition(Position.BOTTOMLEFT);
            }

            @Override
            public void bottomOnPressed() {
                skin.setUserlistPosition(Position.BOTTOM);
            }
        });

        // padding
        final int[] padding = skin.getUserlistPadding();
        UserlistChangesMenu.userlistLayoutMenu
        .add(new PaddingField(padding, bg, strPaddingFieldTitle + "(" + strUserlistPaddingHint + ")") {
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
                    skin.setUserlistPadding(null);
                } else {
                    this.tmp[index] = i;
                    skin.setUserlistPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setUserlistPadding(null);
                this.tmp = skin.getUserlistPadding();
                return this.tmp;
            }
        });
    }
}
