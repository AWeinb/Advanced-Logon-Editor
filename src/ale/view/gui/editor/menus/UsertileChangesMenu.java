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
import ale.view.gui.editor.fields.ImageField;
import ale.view.gui.editor.fields.MarginField;
import ale.view.gui.editor.fields.Numberfield;
import ale.view.gui.editor.fields.PaddingField;
import ale.view.gui.editor.fields.PositionField;
import ale.view.gui.editor.fields.SizeField;
import ale.view.gui.util.VerticalLayout;

public final class UsertileChangesMenu extends ChangesMenus {

    private boolean init_1;
    private boolean init_2;

    private JPanel usermenuImageMenu;
    private JPanel usermenuImageframeMenu;
    private JPanel usermenuLayoutMenu;
    private JPanel usermenuPWFieldMenu;

    private SkinPropertiesVO skin;

    public UsertileChangesMenu(Color bg, SkinPropertiesVO skin) {
        this.skin = skin;
        initialize(bg);
    }

    private void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initUsermenuImageMenu(bg);
                initUsermenuImageframeMenu(bg);
                UsertileChangesMenu.this.init_1 = true;
            }
        };

        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                initUsermenuLayoutMenu(bg);
                initUsermenuPWFieldMenu(bg);
                UsertileChangesMenu.this.init_2 = true;
            }
        };
        Main.executeThreads(_runOne, _runTwo);
    }

    public boolean isInitialized() {
        return this.init_1 & this.init_2;
    }

    public void shutdown() {
        this.usermenuImageMenu = null;
        this.usermenuImageframeMenu = null;
        this.usermenuLayoutMenu = null;
        this.usermenuPWFieldMenu = null;
    }

    public JPanel getUsertileImageMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.usermenuImageMenu;
    }

    public JPanel getUsertileImageframeMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.usermenuImageframeMenu;
    }

    public JPanel getUsertileLayoutMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.usermenuLayoutMenu;
    }

    public JPanel getUsertilePWFieldMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.usermenuPWFieldMenu;
    }

    /*
     * ########################################################################## ##
     */

    /*
     * 
     */
    private void initUsermenuImageMenu(Color bg) {
        this.usermenuImageMenu = new JPanel();
        this.usermenuImageMenu.setBackground(bg);
        this.usermenuImageMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // size
        int[] size = new int[] { this.skin.getUsertileImageWidth(), this.skin.getUsertileImageHeight() };
        this.usermenuImageMenu.add(new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!UsertileChangesMenu.this.skin.setUsertileImageWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!UsertileChangesMenu.this.skin.setUsertileImageHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                UsertileChangesMenu.this.skin.setUsertileImageWidth(-1);
                UsertileChangesMenu.this.skin.setUsertileImageHeight(-1);
                update(UsertileChangesMenu.this.skin.getUsertileImageWidth(), UsertileChangesMenu.this.skin.getUsertileImageHeight());
            }
        });

        // padding
        final int[] padding = this.skin.getUsertileImagePadding();
        this.usermenuImageMenu.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
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
                    UsertileChangesMenu.this.skin.setUsertileImagePadding(null);
                } else {
                    this.tmp[index] = i;
                    UsertileChangesMenu.this.skin.setUsertileImagePadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                UsertileChangesMenu.this.skin.setUsertileImagePadding(null);
                this.tmp = UsertileChangesMenu.this.skin.getUsertileImagePadding();
                return this.tmp;
            }
        });

        // position
        Position pos = this.skin.getUsertileImagePosition();
        boolean[] active = { false, false, false, true, true, true, false, false, false };
        this.usermenuImageMenu.add(new PositionField(pos, active, bg, strPosFieldTitle) {
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
                UsertileChangesMenu.this.skin.setUsertileImagePosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                UsertileChangesMenu.this.skin.setUsertileImagePosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                UsertileChangesMenu.this.skin.setUsertileImagePosition(Position.CENTER);
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

    private void initUsermenuImageframeMenu(Color bg) {
        this.usermenuImageframeMenu = new JPanel();
        this.usermenuImageframeMenu.setBackground(bg);
        this.usermenuImageframeMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // imagePath
        Path initialValue = this.skin.getImgPath_UsertileImage();
        this.usermenuImageframeMenu.add(new ImageField(initialValue, bg, strImgFieldTitle + "(" + strUserimageOverlay + ")",
                true) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    UsertileChangesMenu.this.skin.setImgPath_UsertileImage(file.toPath());
                    updatePathField(UsertileChangesMenu.this.skin.getImgPath_UsertileImage(), false);
                } else {
                    UsertileChangesMenu.this.skin.setImgPath_UsertileImage(null);
                    updatePathField(UsertileChangesMenu.this.skin.getImgPath_UsertileImage(), true);
                }
            }
        });

        // size
        int[] size = new int[] { this.skin.getUsertileImageFrameWidth(), this.skin.getUsertileImageFrameHeight() };
        this.usermenuImageframeMenu.add(new SizeField(size, 3, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!UsertileChangesMenu.this.skin.setUsertileImageFrameWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!UsertileChangesMenu.this.skin.setUsertileImageFrameHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                UsertileChangesMenu.this.skin.setUsertileImageFrameWidth(-1);
                UsertileChangesMenu.this.skin.setUsertileImageFrameHeight(-1);
                update(UsertileChangesMenu.this.skin.getUsertileImageFrameWidth(),
                        UsertileChangesMenu.this.skin.getUsertileImageFrameHeight());
            }
        });
    }

    /*
     * 
     */
    private void initUsermenuLayoutMenu(Color bg) {
        this.usermenuLayoutMenu = new JPanel();
        this.usermenuLayoutMenu.setBackground(bg);
        this.usermenuLayoutMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // checkbox
        boolean b = this.skin.getUsertileLayoutIsHorizontal();
        this.usermenuLayoutMenu.add(new CheckField(b, bg, strUsertileHorizontalFieldTitle, strUsertileHorizontalLabel) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                UsertileChangesMenu.this.skin.setUsertileLayout(selected);
            }
        });

        // checkbox
        b = this.skin.getPWAreaPositionOnRightOfTexts();
        this.usermenuLayoutMenu
        .add(new CheckField(b, bg, strUsertilePWRightOfTextsFieldTitle, strUsertilePWRightOfTextLabel) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                UsertileChangesMenu.this.skin.setPWAreaPositionOnRightOfTexts(selected);
            }
        });

        // checkbox
        b = this.skin.getStatusOnRightSide();
        this.usermenuLayoutMenu
        .add(new CheckField(b, bg, strUsertileStatusOnRightFieldTitle, strUsertileStatusOnRightLabel) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                UsertileChangesMenu.this.skin.setStatusPositionOnRight(selected);
            }
        });

        // position
        Position pos = this.skin.getUsertilePosition();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        this.usermenuLayoutMenu.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.TOPRIGHT);
            }

            @Override
            public void topleftOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.TOPLEFT);
            }

            @Override
            public void topOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.LEFT);
            }

            @Override
            public void centerOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.CENTER);
            }

            @Override
            public void bottomrightOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.BOTTOMRIGHT);
            }

            @Override
            public void bottomleftOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.BOTTOMLEFT);
            }

            @Override
            public void bottomOnPressed() {
                UsertileChangesMenu.this.skin.setUsertilePosition(Position.BOTTOM);
            }
        });
    }

    private void initUsermenuPWFieldMenu(Color bg) {
        this.usermenuPWFieldMenu = new JPanel();
        this.usermenuPWFieldMenu.setBackground(bg);
        this.usermenuPWFieldMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        {
            Path initialValue = this.skin.getImgPath_PWField(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, false) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        UsertileChangesMenu.this.skin.setImgPath_PWField(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(UsertileChangesMenu.this.skin.getImgPath_PWField(Imagetype.DEFAULT), false);
                    } else {
                        UsertileChangesMenu.this.skin.setImgPath_PWField(null, Imagetype.DEFAULT);
                        updatePathField(UsertileChangesMenu.this.skin.getImgPath_PWField(Imagetype.DEFAULT), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_PWField(Imagetype.MOUSEFOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, false) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        UsertileChangesMenu.this.skin.setImgPath_PWField(file.toPath(), Imagetype.MOUSEFOCUS);
                        UsertileChangesMenu.this.skin.setImgPath_PWField(file.toPath(), Imagetype.KEYFOCUS);
                        updatePathField(UsertileChangesMenu.this.skin.getImgPath_PWField(Imagetype.MOUSEFOCUS), false);
                    } else {
                        UsertileChangesMenu.this.skin.setImgPath_PWField(null, Imagetype.MOUSEFOCUS);
                        UsertileChangesMenu.this.skin.setImgPath_PWField(null, Imagetype.KEYFOCUS);
                        updatePathField(UsertileChangesMenu.this.skin.getImgPath_PWField(Imagetype.MOUSEFOCUS), true);
                    }
                }
            };

            initialValue = this.skin.getImgPath_PWField(Imagetype.DISABLED);
            ImageField pressedImage = new ImageField(initialValue, bg, false) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        UsertileChangesMenu.this.skin.setImgPath_PWField(file.toPath(), Imagetype.DISABLED);
                        updatePathField(UsertileChangesMenu.this.skin.getImgPath_PWField(Imagetype.DISABLED), false);
                    } else {
                        UsertileChangesMenu.this.skin.setImgPath_PWField(null, Imagetype.DISABLED);
                        updatePathField(UsertileChangesMenu.this.skin.getImgPath_PWField(Imagetype.DISABLED), true);
                    }
                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle, defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImageSelected);

            this.usermenuPWFieldMenu.add(pane);
        }

        // border
        final int[] borderthickness = this.skin.getPWfieldBorderthickness();
        this.usermenuPWFieldMenu.add(new BorderField(borderthickness, bg, strBorderFieldTitle) {
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
                    UsertileChangesMenu.this.skin.setPWfieldBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    UsertileChangesMenu.this.skin.setPWfieldBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                UsertileChangesMenu.this.skin.setPWfieldBorderthickness(null);
                this.tmp = UsertileChangesMenu.this.skin.getPWfieldBorderthickness();
                return this.tmp;
            }
        });

        // size
        int[] size = new int[] { this.skin.getPWfieldWidth(), this.skin.getPWfieldHeight() };
        this.usermenuPWFieldMenu.add(new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!UsertileChangesMenu.this.skin.setPWfieldWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!UsertileChangesMenu.this.skin.setPWfieldHeight(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                UsertileChangesMenu.this.skin.setPWfieldWidth(-1);
                UsertileChangesMenu.this.skin.setPWfieldHeight(-1);
                update(UsertileChangesMenu.this.skin.getPWfieldWidth(), UsertileChangesMenu.this.skin.getPWfieldHeight());
            }
        });

        // padding
        final int[] margin = this.skin.getPWfieldMargin();
        this.usermenuPWFieldMenu.add(new MarginField(margin, bg, strMarginFieldTitle) {
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
                    UsertileChangesMenu.this.skin.setPWfieldMargin(null);
                } else {
                    this.tmp[index] = i;
                    UsertileChangesMenu.this.skin.setPWfieldMargin(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                UsertileChangesMenu.this.skin.setPWfieldMargin(null);
                this.tmp = UsertileChangesMenu.this.skin.getPWfieldMargin();
                return this.tmp;
            }
        });

        int tmp = this.skin.getPasswordfieldUpshift();
        this.usermenuPWFieldMenu.add(new Numberfield(tmp, 3, bg, strPWAreaUpShift, strPWAreaShiftamount, false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                UsertileChangesMenu.this.skin.setPasswordfieldUpshift(parseInt(input));
            }

            @Override
            public void resetOnClick() {
                //
            }
        });

        tmp = this.skin.getPasswordfieldDownshift();
        this.usermenuPWFieldMenu.add(new Numberfield(tmp, 3, bg, strPWAreaDownShift, strPWAreaShiftamount, false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                UsertileChangesMenu.this.skin.setPasswordfieldDownshift(parseInt(input));
            }

            @Override
            public void resetOnClick() {
                //
            }
        });

        tmp = this.skin.getPasswordfieldRightshift();
        this.usermenuPWFieldMenu.add(new Numberfield(tmp, 3, bg, strPWAreaRightShift, strPWAreaShiftamount, false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                UsertileChangesMenu.this.skin.setPasswordfieldRightshift(parseInt(input));
            }

            @Override
            public void resetOnClick() {
                //
            }
        });
    }
}
