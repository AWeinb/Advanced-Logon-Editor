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
import ale.view.gui.editor.fields.CheckField;
import ale.view.gui.editor.fields.ImageField;
import ale.view.gui.editor.fields.Numberfield;
import ale.view.gui.editor.fields.PaddingField;
import ale.view.gui.editor.fields.PositionField;
import ale.view.gui.editor.fields.SizeField;
import ale.view.gui.util.VerticalLayout;

public final class UserlistChangesMenu extends ChangesMenus {

    private boolean init_1;

    private JPanel userlistImageMenu;
    private JPanel userlistImageframeMenu;
    private JPanel userlistLayoutMenu;

    private SkinPropertiesVO skin;

    public UserlistChangesMenu(Color bg, SkinPropertiesVO skin) {
        this.skin = skin;
        initialize(bg);
    }

    private void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initUserlistImageMenu(bg);
                initUserlistImageframeMenu(bg);
                initUserlistLayoutMenu(bg);
                UserlistChangesMenu.this.init_1 = true;
            }
        };
        Main.executeThreads(_runOne);
    }

    public boolean isInitialized() {
        return this.init_1;
    }

    public void shutdown() {
        this.userlistImageMenu = null;
        this.userlistImageframeMenu = null;
        this.userlistLayoutMenu = null;
    }

    public JPanel getUserlistImageMenu() {
        if (!this.init_1) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.userlistImageMenu;
    }

    public JPanel getUserlistImageframeMenu() {
        if (!this.init_1) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.userlistImageframeMenu;
    }

    public JPanel getUserlistLayoutMenu() {
        if (!this.init_1) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.userlistLayoutMenu;
    }

    /*
     * ########################################################################## ##
     */

    private void initUserlistImageMenu(Color bg) {
        this.userlistImageMenu = new JPanel();
        this.userlistImageMenu.setBackground(bg);
        this.userlistImageMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // size
        int[] size = new int[] { this.skin.getUserlistImageWidth(), this.skin.getUserlistImageHeight() };
        this.userlistImageMenu.add(new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!UserlistChangesMenu.this.skin.setUserlistImageWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!UserlistChangesMenu.this.skin.setUserlistImageHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                UserlistChangesMenu.this.skin.setUserlistImageWidth(-1);
                UserlistChangesMenu.this.skin.setUserlistImageHeight(-1);
                update(UserlistChangesMenu.this.skin.getUserlistImageWidth(), UserlistChangesMenu.this.skin.getUserlistImageHeight());
            }
        });

        // padding
        final int[] padding = this.skin.getUserlistImagePadding();
        this.userlistImageMenu.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
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
                    UserlistChangesMenu.this.skin.setUserlistImagePadding(null);
                } else {
                    this.tmp[index] = i;
                    UserlistChangesMenu.this.skin.setUserlistImagePadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                UserlistChangesMenu.this.skin.setUserlistImagePadding(null);
                this.tmp = UserlistChangesMenu.this.skin.getUserlistImagePadding();
                return this.tmp;
            }
        });
    }

    private void initUserlistImageframeMenu(Color bg) {
        this.userlistImageframeMenu = new JPanel();
        this.userlistImageframeMenu.setBackground(bg);
        this.userlistImageframeMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        {
            Path initialValue = this.skin.getImgPath_UserlistImage(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        UserlistChangesMenu.this.skin.setImgPath_UserlistImage(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(UserlistChangesMenu.this.skin.getImgPath_UserlistImage(Imagetype.DEFAULT), false);
                    } else {
                        UserlistChangesMenu.this.skin.setImgPath_UserlistImage(null, Imagetype.DEFAULT);
                        updatePathField(UserlistChangesMenu.this.skin.getImgPath_UserlistImage(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_UserlistImage(Imagetype.MOUSEFOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        UserlistChangesMenu.this.skin.setImgPath_UserlistImage(file.toPath(), Imagetype.MOUSEFOCUS);
                        UserlistChangesMenu.this.skin.setImgPath_UserlistImage(file.toPath(), Imagetype.FOCUSSELECTED);
                        updatePathField(UserlistChangesMenu.this.skin.getImgPath_UserlistImage(Imagetype.MOUSEFOCUS), false);
                    } else {
                        UserlistChangesMenu.this.skin.setImgPath_UserlistImage(null, Imagetype.MOUSEFOCUS);
                        UserlistChangesMenu.this.skin.setImgPath_UserlistImage(null, Imagetype.FOCUSSELECTED);
                        updatePathField(UserlistChangesMenu.this.skin.getImgPath_UserlistImage(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_UserlistImage(Imagetype.SELECTED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        UserlistChangesMenu.this.skin.setImgPath_UserlistImage(file.toPath(), Imagetype.SELECTED);
                        updatePathField(UserlistChangesMenu.this.skin.getImgPath_UserlistImage(Imagetype.SELECTED), false);
                    } else {
                        UserlistChangesMenu.this.skin.setImgPath_UserlistImage(null, Imagetype.SELECTED);
                        updatePathField(UserlistChangesMenu.this.skin.getImgPath_UserlistImage(Imagetype.SELECTED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strUserimageOverlay + ")", defaultImage, focusImage,
                    pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImageSelected);

            this.userlistImageframeMenu.add(pane);
        }

        // size
        int[] size = new int[] { this.skin.getUserlistImageFrameWidth(), this.skin.getUserlistImageFrameHeight() };
        this.userlistImageframeMenu.add(new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!UserlistChangesMenu.this.skin.setUserlistImageFrameWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!UserlistChangesMenu.this.skin.setUserlistImageFrameHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                UserlistChangesMenu.this.skin.setUserlistImageFrameWidth(-1);
                UserlistChangesMenu.this.skin.setUserlistImageFrameHeight(-1);
                update(UserlistChangesMenu.this.skin.getUserlistImageFrameWidth(),
                        UserlistChangesMenu.this.skin.getUserlistImageFrameHeight());
            }
        });
    }

    /*
     * 
     */
    private void initUserlistLayoutMenu(Color bg) {
        this.userlistLayoutMenu = new JPanel();
        this.userlistLayoutMenu.setBackground(bg);
        this.userlistLayoutMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // size
        this.userlistLayoutMenu.add(new Numberfield(this.skin.getUserlistHeight(), 3, bg, strSizeFieldTitle, strNumfieldHeight,
                true) {
            private final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                if (!UserlistChangesMenu.this.skin.setUserlistHeight(parseInt(input))) {
                    update(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                UserlistChangesMenu.this.skin.setUserlistHeight(-1);
                update(UserlistChangesMenu.this.skin.getUserlistHeight());
            }
        });

        // checkbox
        boolean b = this.skin.getUserlistVertical();
        this.userlistLayoutMenu.add(new CheckField(b, bg, strUserlistVTitle, strUserlistVBtn) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                UserlistChangesMenu.this.skin.setUserlistVertical(selected);
            }
        });

        // position
        Position pos = this.skin.getUserlistPosition();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        this.userlistLayoutMenu.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.TOPRIGHT);
            }

            @Override
            public void topleftOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.TOPLEFT);
            }

            @Override
            public void topOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.CENTER);
            }

            @Override
            public void bottomrightOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.BOTTOMRIGHT);
            }

            @Override
            public void bottomleftOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.BOTTOMLEFT);
            }

            @Override
            public void bottomOnPressed() {
                UserlistChangesMenu.this.skin.setUserlistPosition(Position.BOTTOM);
            }
        });

        // padding
        final int[] padding = this.skin.getUserlistPadding();
        this.userlistLayoutMenu.add(new PaddingField(padding, bg, strPaddingFieldTitle + "(" + strUserlistPaddingHint + ")") {
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
                    UserlistChangesMenu.this.skin.setUserlistPadding(null);
                } else {
                    this.tmp[index] = i;
                    UserlistChangesMenu.this.skin.setUserlistPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                UserlistChangesMenu.this.skin.setUserlistPadding(null);
                this.tmp = UserlistChangesMenu.this.skin.getUserlistPadding();
                return this.tmp;
            }
        });
    }
}
