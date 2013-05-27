/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.menus;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ale.Constants;
import ale.controller.Main;
import ale.model.skin.BrandingVO.Brandingsize;
import ale.model.skin.SkinConstants.Animation;
import ale.model.skin.SkinConstants.CommandButton;
import ale.model.skin.SkinConstants.Imagetype;
import ale.model.skin.SkinConstants.Position;
import ale.model.skin.SkinConstants.UISizeInits;
import ale.view.gui.editor.fields.AnimationField;
import ale.view.gui.editor.fields.BorderField;
import ale.view.gui.editor.fields.CheckField;
import ale.view.gui.editor.fields.ImageField;
import ale.view.gui.editor.fields.Numberfield;
import ale.view.gui.editor.fields.PaddingField;
import ale.view.gui.editor.fields.PositionField;
import ale.view.gui.editor.fields.SizeField;
import ale.view.gui.util.VerticalLayout;

public final class GeneralChangesMenu extends ChangesMenus {

    private static boolean init_1;
    private static boolean init_2;
    private static boolean init_3;

    private static JPanel backgroundMenu;
    private static JPanel brandingMenu;
    private static JPanel windowMenu;
    private static JPanel optionsBarMenu;
    private static JPanel secOptMenu;
    private static JPanel sliderMenu;
    private static JPanel loadingstatusMenu;

    private GeneralChangesMenu() {
    }

    public static void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initBackgroundMenu(bg);
                initBrandingMenu(bg);
                initWindowMenu(bg);
                init_1 = true;
            }
        };

        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                initOptionsBarMenu(bg);
                initSecOptMenu(bg);
                init_2 = true;
            }
        };

        Runnable _runThree = new Runnable() {

            @Override
            public void run() {
                initSliderMenu(bg);
                initLoadingstatusMenu(bg);
                init_3 = true;
            }
        };
        Main.executeThreads(_runOne, _runTwo, _runThree);
    }

    protected static boolean isInitialized() {
        return init_1 & init_2 & init_3;
    }

    public static JPanel getBackgroundMenu() {
        if (!GeneralChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return GeneralChangesMenu.backgroundMenu;
    }

    public static JPanel getBrandingMenu() {
        if (!GeneralChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return GeneralChangesMenu.brandingMenu;
    }

    public static JPanel getWindowMenu() {
        if (!GeneralChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return GeneralChangesMenu.windowMenu;
    }

    public static JPanel getOptionBarMenu() {
        if (!GeneralChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return GeneralChangesMenu.optionsBarMenu;
    }

    public static JPanel getSecOptMenu() {
        if (!GeneralChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return GeneralChangesMenu.secOptMenu;
    }

    public static JPanel getSliderMenu() {
        if (!GeneralChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return GeneralChangesMenu.sliderMenu;
    }

    public static JPanel getLoadingstatusMenu() {
        if (!GeneralChangesMenu.isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return GeneralChangesMenu.loadingstatusMenu;
    }

    private static void initBackgroundMenu(Color bg) {
        GeneralChangesMenu.backgroundMenu = new JPanel();
        GeneralChangesMenu.backgroundMenu.setBackground(bg);
        GeneralChangesMenu.backgroundMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        Path initialValue = skin.getBackground();
        ImageField imgField = new ImageField(initialValue, bg, strImgFieldTitle, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                try {
                    if (file != null) {
                        skin.setBackground(file.toPath());
                        updatePathField(skin.getBackground(), false);
                    } else {
                        skin.setBackground(null);
                        updatePathField(skin.getBackground(), true);
                    }
                } catch (IOException e) {
                    Main.showProblemMessage(e.getMessage());
                }
            }
        };
        imgField.setImageFileFilter(Constants.DEFAULT_BACKGROUND_TYPE);
        GeneralChangesMenu.backgroundMenu.add(imgField);
    }

    private static void initBrandingMenu(Color bg) {
        GeneralChangesMenu.brandingMenu = new JPanel();
        GeneralChangesMenu.brandingMenu.setBackground(bg);
        GeneralChangesMenu.brandingMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        Path path = skin.getBranding().getImage(Brandingsize.SMALL);
        final ImageField imagefield = new ImageField(path, bg, strImgFieldTitle, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    Path tmp = file.toPath();
                    skin.setBranding(tmp, tmp, tmp);
                    updatePathField(skin.getBranding().getImage(Brandingsize.SMALL), false);
                } else {
                    skin.setBranding(null, null, null);
                    updatePathField(skin.getBranding().getImage(Brandingsize.SMALL), true);
                }

            }
        };

        // position
        Position pos = skin.getBrandingPosition();
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
                skin.setBrandingPosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                skin.setBrandingPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                skin.setBrandingPosition(Position.LEFT);
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
                skin.setBrandingPosition(Position.BOTTOM);
            }
        };

        // checkbox
        boolean hidden = skin.getBrandingPosition() == Position.NONE;
        GeneralChangesMenu.brandingMenu.add(new CheckField(hidden, bg, strHideFieldTitle, strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    skin.setBrandingPosition(Position.NONE);
                    posfield.setVisible(false);
                    imagefield.setVisible(false);

                } else {
                    skin.setBrandingPosition(null);
                    posfield.setSelectionOnPosition(skin.getBrandingPosition());
                    posfield.setVisible(true);
                    imagefield.setVisible(true);

                }
            }
        });

        if (hidden) {
            posfield.setVisible(false);
            imagefield.setVisible(false);
        }

        GeneralChangesMenu.brandingMenu.add(posfield);
        GeneralChangesMenu.brandingMenu.add(imagefield);
    }

    private static void initWindowMenu(Color bg) {
        GeneralChangesMenu.windowMenu = new JPanel();
        GeneralChangesMenu.windowMenu.setBackground(bg);
        GeneralChangesMenu.windowMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        final ImageField winImage = new ImageField(skin.getImgPath_Window(), bg, strImgFieldTitle, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    skin.setImgPath_Window(file.toPath());
                    updatePathField(skin.getImgPath_Window(), false);
                } else {
                    skin.setImgPath_Window(null);
                    updatePathField(skin.getImgPath_Window(), true);
                }

            }
        };

        int[] size = new int[] { skin.getWindow_Width(), skin.getWindow_Height() };
        final SizeField winSize = new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!skin.setWindowWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!skin.setWindowHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setWindowWidth(-1);
                skin.setWindowHeight(-1);
                update(skin.getWindow_Width(), skin.getWindow_Height());
            }
        };

        final int[] borderthickness = skin.getWindow_Borderthickness();
        final BorderField winBorder = new BorderField(borderthickness, bg, strBorderFieldTitle) {
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
                    skin.setWindowBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    skin.setWindowBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setWindowBorderthickness(null);
                this.tmp = skin.getWindow_Borderthickness();
                return this.tmp;
            }
        };

        final int[] padding = skin.getWindow_Padding();
        final PaddingField winPadding = new PaddingField(padding, bg, strPaddingFieldTitle) {
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
                    skin.setWindowPadding(null);
                } else {
                    this.tmp[index] = i;
                    skin.setWindowPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setWindowPadding(null);
                this.tmp = skin.getWindow_Padding();
                return this.tmp;
            }
        };

        Position pos = skin.getWindow_Position();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        final PositionField winPosition = new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                skin.setWindowPosition(Position.TOPRIGHT);

            }

            @Override
            public void topleftOnPressed() {
                skin.setWindowPosition(Position.TOPLEFT);

            }

            @Override
            public void topOnPressed() {
                skin.setWindowPosition(Position.TOP);

            }

            @Override
            public void centerrightOnPressed() {
                skin.setWindowPosition(Position.RIGHT);

            }

            @Override
            public void centerleftOnPressed() {
                skin.setWindowPosition(Position.LEFT);

            }

            @Override
            public void centerOnPressed() {
                skin.setWindowPosition(Position.CENTER);

            }

            @Override
            public void bottomrightOnPressed() {
                skin.setWindowPosition(Position.BOTTOMRIGHT);

            }

            @Override
            public void bottomleftOnPressed() {
                skin.setWindowPosition(Position.BOTTOMLEFT);

            }

            @Override
            public void bottomOnPressed() {
                skin.setWindowPosition(Position.BOTTOM);

            }
        };

        Animation animInit = skin.getWindow_Animation();
        final AnimationField winAnim = new AnimationField(bg, animInit, strAnimationFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void originalAnimChosen() {
                skin.setWindowAnimation(Animation.ORIGINAL);
            }

            @Override
            public void horizontalSlowAnimChosen() {
                skin.setWindowAnimation(Animation.RECTANGLE_H_SLOW);
            }

            @Override
            public void horizontalFastAnimChosen() {
                skin.setWindowAnimation(Animation.RECTANGLE_H_FAST);
            }

            @Override
            public void verticalSlowAnimChosen() {
                skin.setWindowAnimation(Animation.RECTANGLE_V_SLOW);
            }

            @Override
            public void verticalFastAnimChosen() {
                skin.setWindowAnimation(Animation.RECTANGLE_V_FAST);
            }
        };

        animInit = skin.getWindow_InnerAnimation();
        AnimationField winAnimInner = new AnimationField(bg, animInit, strAnimationFieldTitle + " " + strWindowInnerAnimation) {
            private static final long serialVersionUID = 1L;

            @Override
            public void originalAnimChosen() {
                skin.setWindowInnerAnimation(Animation.ORIGINAL);
            }

            @Override
            public void horizontalSlowAnimChosen() {
                skin.setWindowInnerAnimation(Animation.RECTANGLE_H_SLOW);
            }

            @Override
            public void horizontalFastAnimChosen() {
                skin.setWindowInnerAnimation(Animation.RECTANGLE_H_FAST);
            }

            @Override
            public void verticalSlowAnimChosen() {
                skin.setWindowInnerAnimation(Animation.RECTANGLE_V_SLOW);
            }

            @Override
            public void verticalFastAnimChosen() {
                skin.setWindowInnerAnimation(Animation.RECTANGLE_V_FAST);
            }
        };

        // checkbox
        boolean b = skin.isWindowActive();
        GeneralChangesMenu.windowMenu.add(new CheckField(b, bg, strActivateWindowTitle, strWindowActiveLabel) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setWindowActive(selected);

                winImage.setVisible(skin.isWindowActive());
                winSize.setVisible(skin.isWindowActive());
                winBorder.setVisible(skin.isWindowActive());
                winPadding.setVisible(skin.isWindowActive());
                winPosition.setVisible(skin.isWindowActive());
                winAnim.setVisible(skin.isWindowActive());
            }
        });

        GeneralChangesMenu.windowMenu.add(winAnimInner);
        GeneralChangesMenu.windowMenu.add(winAnim);
        GeneralChangesMenu.windowMenu.add(winImage);
        GeneralChangesMenu.windowMenu.add(winSize);
        GeneralChangesMenu.windowMenu.add(winBorder);
        GeneralChangesMenu.windowMenu.add(winPadding);
        GeneralChangesMenu.windowMenu.add(winPosition);

        if (!skin.isWindowActive()) {
            winImage.setVisible(false);
            winSize.setVisible(false);
            winBorder.setVisible(false);
            winPadding.setVisible(false);
            winPosition.setVisible(false);
            winAnim.setVisible(false);
        }
    }

    private static void initOptionsBarMenu(Color bg) {
        GeneralChangesMenu.optionsBarMenu = new JPanel();
        GeneralChangesMenu.optionsBarMenu.setBackground(bg);
        GeneralChangesMenu.optionsBarMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // size
        final Numberfield numfield = new Numberfield(skin.getOptionsbarHeight(), 3, bg, strSizeFieldTitle, strNumfieldHeight, true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                if (!skin.setOptionsbarHeight(parseInt(input))) {
                    update(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setOptionsbarHeight(FAILURE);
                update(skin.getOptionsbarHeight());
            }
        };

        // checkbox
        boolean b = skin.getOptionsbarHeight() == 0;
        GeneralChangesMenu.optionsBarMenu.add(new CheckField(b, bg, strHideFieldTitle, strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    skin.setOptionsbarHeight(0);
                    numfield.setVisible(false);

                } else {
                    skin.setOptionsbarHeight(UISizeInits.OPTIONSBAR.getHeight());
                    numfield.update(skin.getOptionsbarHeight());
                    numfield.setVisible(true);
                }
            }
        });

        GeneralChangesMenu.optionsBarMenu.add(numfield);
    }

    private static void initSecOptMenu(Color bg) {
        GeneralChangesMenu.secOptMenu = new JPanel();
        GeneralChangesMenu.secOptMenu.setBackground(bg);
        GeneralChangesMenu.secOptMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // position
        Position pos = skin.getSecurityMenu_Position();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        GeneralChangesMenu.secOptMenu.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                skin.setSecurityMenuPosition(Position.TOPRIGHT);

            }

            @Override
            public void topleftOnPressed() {
                skin.setSecurityMenuPosition(Position.TOPLEFT);

            }

            @Override
            public void topOnPressed() {
                skin.setSecurityMenuPosition(Position.TOP);

            }

            @Override
            public void centerrightOnPressed() {
                skin.setSecurityMenuPosition(Position.RIGHT);

            }

            @Override
            public void centerleftOnPressed() {
                skin.setSecurityMenuPosition(Position.LEFT);

            }

            @Override
            public void centerOnPressed() {
                skin.setSecurityMenuPosition(Position.CENTER);

            }

            @Override
            public void bottomrightOnPressed() {
                skin.setSecurityMenuPosition(Position.BOTTOMRIGHT);

            }

            @Override
            public void bottomleftOnPressed() {
                skin.setSecurityMenuPosition(Position.BOTTOMLEFT);

            }

            @Override
            public void bottomOnPressed() {
                skin.setSecurityMenuPosition(Position.BOTTOM);

            }
        });

        // padding
        final int[] padding = skin.getSecurityMenu_Padding();
        GeneralChangesMenu.secOptMenu.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
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
                    skin.setSecurityMenuPadding(null);
                } else {
                    this.tmp[index] = i;
                    skin.setSecurityMenuPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setSecurityMenuPadding(null);
                this.tmp = new int[] { 0, 0, 0, 0 };
                return this.tmp;
            }
        });

        // checkbox
        boolean b = !skin.getCommandButtonVisibility(CommandButton.LOCK);
        GeneralChangesMenu.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_lock + ")", strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setCommandButtonVisibility(CommandButton.LOCK, !selected);
            }
        });

        // checkbox
        b = !skin.getCommandButtonVisibility(CommandButton.SWITCH);
        GeneralChangesMenu.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_switch + ")", strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setCommandButtonVisibility(CommandButton.SWITCH, !selected);
            }
        });

        // checkbox
        b = !skin.getCommandButtonVisibility(CommandButton.LOGOUT);
        GeneralChangesMenu.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_logout + ")", strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setCommandButtonVisibility(CommandButton.LOGOUT, !selected);
            }
        });

        // checkbox
        b = !skin.getCommandButtonVisibility(CommandButton.PASSWORD);
        GeneralChangesMenu.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_passwd + ")", strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setCommandButtonVisibility(CommandButton.PASSWORD, !selected);
            }
        });

        // checkbox
        b = !skin.getCommandButtonVisibility(CommandButton.TASKMANAGER);
        GeneralChangesMenu.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_taskman + ")", strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setCommandButtonVisibility(CommandButton.TASKMANAGER, !selected);
            }
        });
    }

    private static void initSliderMenu(Color bg) {
        GeneralChangesMenu.sliderMenu = new JPanel();
        GeneralChangesMenu.sliderMenu.setBackground(bg);
        GeneralChangesMenu.sliderMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // up
        // image
        {
            Path initialValue = skin.getImgPath_SliderArrowUp(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderArrowUp(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_SliderArrowUp(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_SliderArrowUp(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_SliderArrowUp(Imagetype.DEFAULT), true);
                    }

                }
            };

            initialValue = skin.getImgPath_SliderArrowUp(Imagetype.FOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderArrowUp(file.toPath(), Imagetype.FOCUS);
                        updatePathField(skin.getImgPath_SliderArrowUp(Imagetype.FOCUS), false);
                    } else {
                        skin.setImgPath_SliderArrowUp(null, Imagetype.FOCUS);
                        updatePathField(skin.getImgPath_SliderArrowUp(Imagetype.FOCUS), true);
                    }

                }
            };

            initialValue = skin.getImgPath_SliderArrowUp(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderArrowUp(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_SliderArrowUp(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_SliderArrowUp(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_SliderArrowUp(Imagetype.PRESSED), true);
                    }

                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strSliderUp + ")", defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            GeneralChangesMenu.sliderMenu.add(pane);
        }

        // down
        // image
        {
            Path initialValue = skin.getImgPath_SliderArrowDown(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderArrowDown(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_SliderArrowDown(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_SliderArrowDown(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_SliderArrowDown(Imagetype.DEFAULT), true);
                    }

                }
            };

            initialValue = skin.getImgPath_SliderArrowDown(Imagetype.FOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderArrowDown(file.toPath(), Imagetype.FOCUS);
                        updatePathField(skin.getImgPath_SliderArrowDown(Imagetype.FOCUS), false);
                    } else {
                        skin.setImgPath_SliderArrowDown(null, Imagetype.FOCUS);
                        updatePathField(skin.getImgPath_SliderArrowDown(Imagetype.FOCUS), true);
                    }

                }
            };

            initialValue = skin.getImgPath_SliderArrowDown(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderArrowDown(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_SliderArrowDown(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_SliderArrowDown(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_SliderArrowDown(Imagetype.PRESSED), true);
                    }

                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strSliderDown + ")", defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            GeneralChangesMenu.sliderMenu.add(pane);
        }

        // bar
        // image
        {
            Path initialValue = skin.getImgPath_SliderBar(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderBar(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_SliderBar(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_SliderBar(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_SliderBar(Imagetype.DEFAULT), true);
                    }

                }
            };

            initialValue = skin.getImgPath_SliderBar(Imagetype.FOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderBar(file.toPath(), Imagetype.FOCUS);
                        updatePathField(skin.getImgPath_SliderBar(Imagetype.FOCUS), false);
                    } else {
                        skin.setImgPath_SliderBar(null, Imagetype.FOCUS);
                        updatePathField(skin.getImgPath_SliderBar(Imagetype.FOCUS), true);
                    }

                }
            };

            initialValue = skin.getImgPath_SliderBar(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderBar(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_SliderBar(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_SliderBar(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_SliderBar(Imagetype.PRESSED), true);
                    }

                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strSliderBar + ")", defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            GeneralChangesMenu.sliderMenu.add(pane);
        }

        // midbtn
        // image
        {
            Path initialValue = skin.getImgPath_SliderMidBtn(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderMidBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_SliderMidBtn(Imagetype.DEFAULT), false);
                    } else {
                        skin.setImgPath_SliderMidBtn(null, Imagetype.DEFAULT);
                        updatePathField(skin.getImgPath_SliderMidBtn(Imagetype.DEFAULT), true);
                    }

                }
            };

            initialValue = skin.getImgPath_SliderMidBtn(Imagetype.FOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderMidBtn(file.toPath(), Imagetype.FOCUS);
                        updatePathField(skin.getImgPath_SliderMidBtn(Imagetype.FOCUS), false);
                    } else {
                        skin.setImgPath_SliderMidBtn(null, Imagetype.FOCUS);
                        updatePathField(skin.getImgPath_SliderMidBtn(Imagetype.FOCUS), true);
                    }

                }
            };

            initialValue = skin.getImgPath_SliderMidBtn(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private static final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        skin.setImgPath_SliderMidBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_SliderMidBtn(Imagetype.PRESSED), false);
                    } else {
                        skin.setImgPath_SliderMidBtn(null, Imagetype.PRESSED);
                        updatePathField(skin.getImgPath_SliderMidBtn(Imagetype.PRESSED), true);
                    }

                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strSliderBtn + ")", defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            GeneralChangesMenu.sliderMenu.add(pane);
        }
    }

    private static void initLoadingstatusMenu(Color bg) {
        GeneralChangesMenu.loadingstatusMenu = new JPanel();
        GeneralChangesMenu.loadingstatusMenu.setBackground(bg);
        GeneralChangesMenu.loadingstatusMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        GeneralChangesMenu.loadingstatusMenu.add(new ImageField(skin.getImgPathLoadingStatusBg(), bg, strImgFieldTitle, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    skin.setImgPathLoadingStatusBg(file.toPath());
                    updatePathField(skin.getImgPathLoadingStatusBg(), false);
                } else {
                    skin.setImgPathLoadingStatusBg(null);
                    updatePathField(skin.getImgPathLoadingStatusBg(), true);
                }
            }
        });

        // size
        GeneralChangesMenu.loadingstatusMenu.add(new Numberfield(skin.getLoadingStatusWidth(), 3, bg, strSizeFieldTitle, strNumfieldWidth,
                true) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                if (!skin.setLoadingStatusWidth(parseInt(input))) {
                    update(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                skin.setLoadingStatusWidth(0);
                update(skin.getLoadingStatusWidth());
            }
        });

        final int[] borderthickness = skin.getLoadingStatusBorderthickness();
        GeneralChangesMenu.loadingstatusMenu.add(new BorderField(borderthickness, bg, strBorderFieldTitle) {
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
                    skin.setLoadingStatusBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    skin.setLoadingStatusBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                skin.setLoadingStatusBorderthickness(null);
                // tmp = initial value
                this.tmp[0] = 0;
                this.tmp[1] = 0;
                this.tmp[2] = 0;
                this.tmp[3] = 0;
                return this.tmp;
            }
        });

        Animation animInit = skin.getLoadingStatusAnimation();
        GeneralChangesMenu.loadingstatusMenu.add(new AnimationField(bg, animInit, strAnimationFieldTitle) {
            private static final long serialVersionUID = 1L;

            @Override
            public void originalAnimChosen() {
                skin.setLoadingStatusAnimation(Animation.ORIGINAL);
            }

            @Override
            public void horizontalSlowAnimChosen() {
                skin.setLoadingStatusAnimation(Animation.RECTANGLE_H_SLOW);
            }

            @Override
            public void horizontalFastAnimChosen() {
                skin.setLoadingStatusAnimation(Animation.RECTANGLE_H_FAST);
            }

            @Override
            public void verticalSlowAnimChosen() {
                skin.setLoadingStatusAnimation(Animation.RECTANGLE_V_SLOW);
            }

            @Override
            public void verticalFastAnimChosen() {
                skin.setLoadingStatusAnimation(Animation.RECTANGLE_V_FAST);
            }
        });

        // checkbox
        boolean b = skin.getLoadingStatusRinganimHidden();
        GeneralChangesMenu.loadingstatusMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strLoadingRinganim + ")", strHide) {
            private static final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                skin.setLoadingStatusRinganimHidden(selected);
            }
        });
    }
}
