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
import ale.model.skin.SkinPropertiesVO;
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

    private boolean init_1;
    private boolean init_2;
    private boolean init_3;

    private JPanel backgroundMenu;
    private JPanel brandingMenu;
    private JPanel windowMenu;
    private JPanel optionsBarMenu;
    private JPanel secOptMenu;
    private JPanel sliderMenu;
    private JPanel loadingstatusMenu;

    private SkinPropertiesVO skin;

    public GeneralChangesMenu(Color bg, SkinPropertiesVO skin) {
        this.skin = skin;
        initialize(bg);
    }

    private void initialize(final Color bg) {
        Runnable _runOne = new Runnable() {

            @Override
            public void run() {
                initBackgroundMenu(bg);
                initBrandingMenu(bg);
                initWindowMenu(bg);
                GeneralChangesMenu.this.init_1 = true;
            }
        };

        Runnable _runTwo = new Runnable() {

            @Override
            public void run() {
                initOptionsBarMenu(bg);
                initSecOptMenu(bg);
                GeneralChangesMenu.this.init_2 = true;
            }
        };

        Runnable _runThree = new Runnable() {

            @Override
            public void run() {
                initSliderMenu(bg);
                initLoadingstatusMenu(bg);
                GeneralChangesMenu.this.init_3 = true;
            }
        };
        Main.executeThreads(_runOne, _runTwo, _runThree);
    }

    public boolean isInitialized() {
        return this.init_1 & this.init_2 & this.init_3;
    }

    public void shutdown() {
        this.backgroundMenu = null;
        this.brandingMenu = null;
        this.windowMenu = null;
        this.optionsBarMenu = null;
        this.secOptMenu = null;
        this.sliderMenu = null;
        this.loadingstatusMenu = null;
    }

    public JPanel getBackgroundMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.backgroundMenu;
    }

    public JPanel getBrandingMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.brandingMenu;
    }

    public JPanel getWindowMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.windowMenu;
    }

    public JPanel getOptionBarMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.optionsBarMenu;
    }

    public JPanel getSecOptMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.secOptMenu;
    }

    public JPanel getSliderMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.sliderMenu;
    }

    public JPanel getLoadingstatusMenu() {
        if (!isInitialized()) {
            IllegalStateException e = new IllegalStateException("Call init first!");
            throw e;
        }

        return this.loadingstatusMenu;
    }

    private void initBackgroundMenu(Color bg) {
        this.backgroundMenu = new JPanel();
        this.backgroundMenu.setBackground(bg);
        this.backgroundMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        Path initialValue = this.skin.getBackground();
        ImageField imgField = new ImageField(initialValue, bg, strImgFieldTitle, false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                try {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setBackground(file.toPath());
                        updatePathField(GeneralChangesMenu.this.skin.getBackground(), false);
                    } else {
                        GeneralChangesMenu.this.skin.setBackground(null);
                        updatePathField(GeneralChangesMenu.this.skin.getBackground(), true);
                    }
                } catch (IOException e) {
                    Main.showProblemMessage(e.getMessage());
                }
            }
        };
        imgField.setImageFileFilter(Constants.DEFAULT_BACKGROUND_TYPE);
        this.backgroundMenu.add(imgField);
    }

    private void initBrandingMenu(Color bg) {
        this.brandingMenu = new JPanel();
        this.brandingMenu.setBackground(bg);
        this.brandingMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // image
        Path path = this.skin.getBranding().getImage(Brandingsize.SMALL);
        final ImageField imagefield = new ImageField(path, bg, strImgFieldTitle, false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    Path tmp = file.toPath();
                    GeneralChangesMenu.this.skin.setBranding(tmp, tmp, tmp);
                    updatePathField(GeneralChangesMenu.this.skin.getBranding().getImage(Brandingsize.SMALL), false);
                } else {
                    GeneralChangesMenu.this.skin.setBranding(null, null, null);
                    updatePathField(GeneralChangesMenu.this.skin.getBranding().getImage(Brandingsize.SMALL), true);
                }

            }
        };

        // position
        Position pos = this.skin.getBrandingPosition();
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
                GeneralChangesMenu.this.skin.setBrandingPosition(Position.TOP);
            }

            @Override
            public void centerrightOnPressed() {
                GeneralChangesMenu.this.skin.setBrandingPosition(Position.RIGHT);
            }

            @Override
            public void centerleftOnPressed() {
                GeneralChangesMenu.this.skin.setBrandingPosition(Position.LEFT);
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
                GeneralChangesMenu.this.skin.setBrandingPosition(Position.BOTTOM);
            }
        };

        // checkbox
        boolean hidden = this.skin.getBrandingPosition() == Position.NONE;
        this.brandingMenu.add(new CheckField(hidden, bg, strHideFieldTitle, strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    GeneralChangesMenu.this.skin.setBrandingPosition(Position.NONE);
                    posfield.setVisible(false);
                    imagefield.setVisible(false);

                } else {
                    GeneralChangesMenu.this.skin.setBrandingPosition(null);
                    posfield.setSelectionOnPosition(GeneralChangesMenu.this.skin.getBrandingPosition());
                    posfield.setVisible(true);
                    imagefield.setVisible(true);

                }
            }
        });

        if (hidden) {
            posfield.setVisible(false);
            imagefield.setVisible(false);
        }

        this.brandingMenu.add(posfield);
        this.brandingMenu.add(imagefield);
    }

    private void initWindowMenu(Color bg) {
        this.windowMenu = new JPanel();
        this.windowMenu.setBackground(bg);
        this.windowMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        final ImageField winImage = new ImageField(this.skin.getImgPath_Window(), bg, strImgFieldTitle, false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    GeneralChangesMenu.this.skin.setImgPath_Window(file.toPath());
                    updatePathField(GeneralChangesMenu.this.skin.getImgPath_Window(), false);
                } else {
                    GeneralChangesMenu.this.skin.setImgPath_Window(null);
                    updatePathField(GeneralChangesMenu.this.skin.getImgPath_Window(), true);
                }

            }
        };

        int[] size = new int[] { this.skin.getWindow_Width(), this.skin.getWindow_Height() };
        final SizeField winSize = new SizeField(size, 4, bg, strSizeFieldTitle, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void widthOnKeyReleased(String input) {
                if (!GeneralChangesMenu.this.skin.setWindowWidth(parseInt(input))) {
                    updateWidthfieldColor(Color.RED);
                }
            }

            @Override
            public void heightOnKeyReleased(String input) {
                if (!GeneralChangesMenu.this.skin.setWindowHeight(parseInt(input))) {
                    updateHeightfieldColor(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                GeneralChangesMenu.this.skin.setWindowWidth(-1);
                GeneralChangesMenu.this.skin.setWindowHeight(-1);
                update(GeneralChangesMenu.this.skin.getWindow_Width(), GeneralChangesMenu.this.skin.getWindow_Height());
            }
        };

        final int[] borderthickness = this.skin.getWindow_Borderthickness();
        final BorderField winBorder = new BorderField(borderthickness, bg, strBorderFieldTitle) {
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
                    GeneralChangesMenu.this.skin.setWindowBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    GeneralChangesMenu.this.skin.setWindowBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                GeneralChangesMenu.this.skin.setWindowBorderthickness(null);
                this.tmp = GeneralChangesMenu.this.skin.getWindow_Borderthickness();
                return this.tmp;
            }
        };

        final int[] padding = this.skin.getWindow_Padding();
        final PaddingField winPadding = new PaddingField(padding, bg, strPaddingFieldTitle) {
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
                    GeneralChangesMenu.this.skin.setWindowPadding(null);
                } else {
                    this.tmp[index] = i;
                    GeneralChangesMenu.this.skin.setWindowPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                GeneralChangesMenu.this.skin.setWindowPadding(null);
                this.tmp = GeneralChangesMenu.this.skin.getWindow_Padding();
                return this.tmp;
            }
        };

        Position pos = this.skin.getWindow_Position();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        final PositionField winPosition = new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.TOPRIGHT);

            }

            @Override
            public void topleftOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.TOPLEFT);

            }

            @Override
            public void topOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.TOP);

            }

            @Override
            public void centerrightOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.RIGHT);

            }

            @Override
            public void centerleftOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.LEFT);

            }

            @Override
            public void centerOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.CENTER);

            }

            @Override
            public void bottomrightOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.BOTTOMRIGHT);

            }

            @Override
            public void bottomleftOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.BOTTOMLEFT);

            }

            @Override
            public void bottomOnPressed() {
                GeneralChangesMenu.this.skin.setWindowPosition(Position.BOTTOM);

            }
        };

        Animation animInit = this.skin.getWindow_Animation();
        final AnimationField winAnim = new AnimationField(bg, animInit, strAnimationFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void originalAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowAnimation(Animation.ORIGINAL);
            }

            @Override
            public void horizontalSlowAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowAnimation(Animation.RECTANGLE_H_SLOW);
            }

            @Override
            public void horizontalFastAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowAnimation(Animation.RECTANGLE_H_FAST);
            }

            @Override
            public void verticalSlowAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowAnimation(Animation.RECTANGLE_V_SLOW);
            }

            @Override
            public void verticalFastAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowAnimation(Animation.RECTANGLE_V_FAST);
            }
        };

        animInit = this.skin.getWindow_InnerAnimation();
        AnimationField winAnimInner = new AnimationField(bg, animInit, strAnimationFieldTitle + " " + strWindowInnerAnimation) {
            private final long serialVersionUID = 1L;

            @Override
            public void originalAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowInnerAnimation(Animation.ORIGINAL);
            }

            @Override
            public void horizontalSlowAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowInnerAnimation(Animation.RECTANGLE_H_SLOW);
            }

            @Override
            public void horizontalFastAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowInnerAnimation(Animation.RECTANGLE_H_FAST);
            }

            @Override
            public void verticalSlowAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowInnerAnimation(Animation.RECTANGLE_V_SLOW);
            }

            @Override
            public void verticalFastAnimChosen() {
                GeneralChangesMenu.this.skin.setWindowInnerAnimation(Animation.RECTANGLE_V_FAST);
            }
        };

        // checkbox
        boolean b = this.skin.isWindowActive();
        this.windowMenu.add(new CheckField(b, bg, strActivateWindowTitle, strWindowActiveLabel) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                GeneralChangesMenu.this.skin.setWindowActive(selected);

                winImage.setVisible(GeneralChangesMenu.this.skin.isWindowActive());
                winSize.setVisible(GeneralChangesMenu.this.skin.isWindowActive());
                winBorder.setVisible(GeneralChangesMenu.this.skin.isWindowActive());
                winPadding.setVisible(GeneralChangesMenu.this.skin.isWindowActive());
                winPosition.setVisible(GeneralChangesMenu.this.skin.isWindowActive());
                winAnim.setVisible(GeneralChangesMenu.this.skin.isWindowActive());
            }
        });

        this.windowMenu.add(winAnimInner);
        this.windowMenu.add(winAnim);
        this.windowMenu.add(winImage);
        this.windowMenu.add(winSize);
        this.windowMenu.add(winBorder);
        this.windowMenu.add(winPadding);
        this.windowMenu.add(winPosition);

        if (!this.skin.isWindowActive()) {
            winImage.setVisible(false);
            winSize.setVisible(false);
            winBorder.setVisible(false);
            winPadding.setVisible(false);
            winPosition.setVisible(false);
            winAnim.setVisible(false);
        }
    }

    private void initOptionsBarMenu(Color bg) {
        this.optionsBarMenu = new JPanel();
        this.optionsBarMenu.setBackground(bg);
        this.optionsBarMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // size
        final Numberfield numfield = new Numberfield(this.skin.getOptionsbarHeight(), 3, bg, strSizeFieldTitle, strNumfieldHeight, true) {
            private final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                if (!GeneralChangesMenu.this.skin.setOptionsbarHeight(parseInt(input))) {
                    update(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                GeneralChangesMenu.this.skin.setOptionsbarHeight(FAILURE);
                update(GeneralChangesMenu.this.skin.getOptionsbarHeight());
            }
        };

        // checkbox
        boolean b = this.skin.getOptionsbarHeight() == 0;
        this.optionsBarMenu.add(new CheckField(b, bg, strHideFieldTitle, strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                if (selected) {
                    GeneralChangesMenu.this.skin.setOptionsbarHeight(0);
                    numfield.setVisible(false);

                } else {
                    GeneralChangesMenu.this.skin.setOptionsbarHeight(UISizeInits.OPTIONSBAR.getHeight());
                    numfield.update(GeneralChangesMenu.this.skin.getOptionsbarHeight());
                    numfield.setVisible(true);
                }
            }
        });

        this.optionsBarMenu.add(numfield);
    }

    private void initSecOptMenu(Color bg) {
        this.secOptMenu = new JPanel();
        this.secOptMenu.setBackground(bg);
        this.secOptMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // position
        Position pos = this.skin.getSecurityMenu_Position();
        boolean[] active = { true, true, true, true, true, true, true, true, true };
        this.secOptMenu.add(new PositionField(pos, active, bg, strPosFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void toprightOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.TOPRIGHT);

            }

            @Override
            public void topleftOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.TOPLEFT);

            }

            @Override
            public void topOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.TOP);

            }

            @Override
            public void centerrightOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.RIGHT);

            }

            @Override
            public void centerleftOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.LEFT);

            }

            @Override
            public void centerOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.CENTER);

            }

            @Override
            public void bottomrightOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.BOTTOMRIGHT);

            }

            @Override
            public void bottomleftOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.BOTTOMLEFT);

            }

            @Override
            public void bottomOnPressed() {
                GeneralChangesMenu.this.skin.setSecurityMenuPosition(Position.BOTTOM);

            }
        });

        // padding
        final int[] padding = this.skin.getSecurityMenu_Padding();
        this.secOptMenu.add(new PaddingField(padding, bg, strPaddingFieldTitle) {
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
                    GeneralChangesMenu.this.skin.setSecurityMenuPadding(null);
                } else {
                    this.tmp[index] = i;
                    GeneralChangesMenu.this.skin.setSecurityMenuPadding(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                GeneralChangesMenu.this.skin.setSecurityMenuPadding(null);
                this.tmp = new int[] { 0, 0, 0, 0 };
                return this.tmp;
            }
        });

        // checkbox
        boolean b = !this.skin.getCommandButtonVisibility(CommandButton.LOCK);
        this.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_lock + ")", strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                GeneralChangesMenu.this.skin.setCommandButtonVisibility(CommandButton.LOCK, !selected);
            }
        });

        // checkbox
        b = !this.skin.getCommandButtonVisibility(CommandButton.SWITCH);
        this.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_switch + ")", strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                GeneralChangesMenu.this.skin.setCommandButtonVisibility(CommandButton.SWITCH, !selected);
            }
        });

        // checkbox
        b = !this.skin.getCommandButtonVisibility(CommandButton.LOGOUT);
        this.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_logout + ")", strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                GeneralChangesMenu.this.skin.setCommandButtonVisibility(CommandButton.LOGOUT, !selected);
            }
        });

        // checkbox
        b = !this.skin.getCommandButtonVisibility(CommandButton.PASSWORD);
        this.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_passwd + ")", strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                GeneralChangesMenu.this.skin.setCommandButtonVisibility(CommandButton.PASSWORD, !selected);
            }
        });

        // checkbox
        b = !this.skin.getCommandButtonVisibility(CommandButton.TASKMANAGER);
        this.secOptMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strCommandBtn_taskman + ")", strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                GeneralChangesMenu.this.skin.setCommandButtonVisibility(CommandButton.TASKMANAGER, !selected);
            }
        });
    }

    private void initSliderMenu(Color bg) {
        this.sliderMenu = new JPanel();
        this.sliderMenu.setBackground(bg);
        this.sliderMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // up
        // image
        {
            Path initialValue = this.skin.getImgPath_SliderArrowUp(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowUp(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowUp(Imagetype.DEFAULT), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowUp(null, Imagetype.DEFAULT);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowUp(Imagetype.DEFAULT), true);
                    }

                }
            };

            initialValue = this.skin.getImgPath_SliderArrowUp(Imagetype.FOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowUp(file.toPath(), Imagetype.FOCUS);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowUp(Imagetype.FOCUS), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowUp(null, Imagetype.FOCUS);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowUp(Imagetype.FOCUS), true);
                    }

                }
            };

            initialValue = this.skin.getImgPath_SliderArrowUp(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowUp(file.toPath(), Imagetype.PRESSED);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowUp(Imagetype.PRESSED), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowUp(null, Imagetype.PRESSED);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowUp(Imagetype.PRESSED), true);
                    }

                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strSliderUp + ")", defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            this.sliderMenu.add(pane);
        }

        // down
        // image
        {
            Path initialValue = this.skin.getImgPath_SliderArrowDown(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowDown(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowDown(Imagetype.DEFAULT), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowDown(null, Imagetype.DEFAULT);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowDown(Imagetype.DEFAULT), true);
                    }

                }
            };

            initialValue = this.skin.getImgPath_SliderArrowDown(Imagetype.FOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowDown(file.toPath(), Imagetype.FOCUS);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowDown(Imagetype.FOCUS), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowDown(null, Imagetype.FOCUS);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowDown(Imagetype.FOCUS), true);
                    }

                }
            };

            initialValue = this.skin.getImgPath_SliderArrowDown(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowDown(file.toPath(), Imagetype.PRESSED);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowDown(Imagetype.PRESSED), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderArrowDown(null, Imagetype.PRESSED);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderArrowDown(Imagetype.PRESSED), true);
                    }

                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strSliderDown + ")", defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            this.sliderMenu.add(pane);
        }

        // bar
        // image
        {
            Path initialValue = this.skin.getImgPath_SliderBar(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderBar(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderBar(Imagetype.DEFAULT), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderBar(null, Imagetype.DEFAULT);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderBar(Imagetype.DEFAULT), true);
                    }

                }
            };

            initialValue = this.skin.getImgPath_SliderBar(Imagetype.FOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderBar(file.toPath(), Imagetype.FOCUS);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderBar(Imagetype.FOCUS), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderBar(null, Imagetype.FOCUS);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderBar(Imagetype.FOCUS), true);
                    }

                }
            };

            initialValue = this.skin.getImgPath_SliderBar(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderBar(file.toPath(), Imagetype.PRESSED);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderBar(Imagetype.PRESSED), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderBar(null, Imagetype.PRESSED);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderBar(Imagetype.PRESSED), true);
                    }

                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strSliderBar + ")", defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            this.sliderMenu.add(pane);
        }

        // midbtn
        // image
        {
            Path initialValue = this.skin.getImgPath_SliderMidBtn(Imagetype.DEFAULT);
            ImageField defaultImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderMidBtn(file.toPath(), Imagetype.DEFAULT);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderMidBtn(Imagetype.DEFAULT), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderMidBtn(null, Imagetype.DEFAULT);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderMidBtn(Imagetype.DEFAULT), true);
                    }

                }
            };

            initialValue = this.skin.getImgPath_SliderMidBtn(Imagetype.FOCUS);
            ImageField focusImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderMidBtn(file.toPath(), Imagetype.FOCUS);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderMidBtn(Imagetype.FOCUS), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderMidBtn(null, Imagetype.FOCUS);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderMidBtn(Imagetype.FOCUS), true);
                    }

                }
            };

            initialValue = this.skin.getImgPath_SliderMidBtn(Imagetype.PRESSED);
            ImageField pressedImage = new ImageField(initialValue, bg, true) {
                private final long serialVersionUID = 1L;

                @Override
                public void onFileChosen(File file) {
                    if (file != null) {
                        GeneralChangesMenu.this.skin.setImgPath_SliderMidBtn(file.toPath(), Imagetype.PRESSED);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderMidBtn(Imagetype.PRESSED), false);
                    } else {
                        GeneralChangesMenu.this.skin.setImgPath_SliderMidBtn(null, Imagetype.PRESSED);
                        updatePathField(GeneralChangesMenu.this.skin.getImgPath_SliderMidBtn(Imagetype.PRESSED), true);
                    }

                }
            };

            JTabbedPane pane = createTabbedPane(bg, strImgFieldTitle + "(" + strSliderBtn + ")", defaultImage, focusImage, pressedImage);
            pane.setTitleAt(0, strImageDefault);
            pane.setTitleAt(1, strImageFocus);
            pane.setTitleAt(2, strImagePressed);

            this.sliderMenu.add(pane);
        }
    }

    private void initLoadingstatusMenu(Color bg) {
        this.loadingstatusMenu = new JPanel();
        this.loadingstatusMenu.setBackground(bg);
        this.loadingstatusMenu.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        this.loadingstatusMenu.add(new ImageField(this.skin.getImgPathLoadingStatusBg(), bg, strImgFieldTitle, false) {
            private final long serialVersionUID = 1L;

            @Override
            public void onFileChosen(File file) {
                if (file != null) {
                    GeneralChangesMenu.this.skin.setImgPathLoadingStatusBg(file.toPath());
                    updatePathField(GeneralChangesMenu.this.skin.getImgPathLoadingStatusBg(), false);
                } else {
                    GeneralChangesMenu.this.skin.setImgPathLoadingStatusBg(null);
                    updatePathField(GeneralChangesMenu.this.skin.getImgPathLoadingStatusBg(), true);
                }
            }
        });

        // size
        this.loadingstatusMenu.add(new Numberfield(this.skin.getLoadingStatusWidth(), 3, bg, strSizeFieldTitle, strNumfieldWidth,
                true) {
            private final long serialVersionUID = 1L;

            @Override
            public void onKeyReleased(String input) {
                if (!GeneralChangesMenu.this.skin.setLoadingStatusWidth(parseInt(input))) {
                    update(Color.RED);
                }
            }

            @Override
            public void resetOnClick() {
                GeneralChangesMenu.this.skin.setLoadingStatusWidth(0);
                update(GeneralChangesMenu.this.skin.getLoadingStatusWidth());
            }
        });

        final int[] borderthickness = this.skin.getLoadingStatusBorderthickness();
        this.loadingstatusMenu.add(new BorderField(borderthickness, bg, strBorderFieldTitle) {
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
                    GeneralChangesMenu.this.skin.setLoadingStatusBorderthickness(null);
                } else {
                    this.tmp[index] = i;
                    GeneralChangesMenu.this.skin.setLoadingStatusBorderthickness(this.tmp);
                }
            }

            @Override
            public int[] reset() {
                GeneralChangesMenu.this.skin.setLoadingStatusBorderthickness(null);
                // tmp = initial value
                this.tmp[0] = 0;
                this.tmp[1] = 0;
                this.tmp[2] = 0;
                this.tmp[3] = 0;
                return this.tmp;
            }
        });

        Animation animInit = this.skin.getLoadingStatusAnimation();
        this.loadingstatusMenu.add(new AnimationField(bg, animInit, strAnimationFieldTitle) {
            private final long serialVersionUID = 1L;

            @Override
            public void originalAnimChosen() {
                GeneralChangesMenu.this.skin.setLoadingStatusAnimation(Animation.ORIGINAL);
            }

            @Override
            public void horizontalSlowAnimChosen() {
                GeneralChangesMenu.this.skin.setLoadingStatusAnimation(Animation.RECTANGLE_H_SLOW);
            }

            @Override
            public void horizontalFastAnimChosen() {
                GeneralChangesMenu.this.skin.setLoadingStatusAnimation(Animation.RECTANGLE_H_FAST);
            }

            @Override
            public void verticalSlowAnimChosen() {
                GeneralChangesMenu.this.skin.setLoadingStatusAnimation(Animation.RECTANGLE_V_SLOW);
            }

            @Override
            public void verticalFastAnimChosen() {
                GeneralChangesMenu.this.skin.setLoadingStatusAnimation(Animation.RECTANGLE_V_FAST);
            }
        });

        // checkbox
        boolean b = this.skin.getLoadingStatusRinganimHidden();
        this.loadingstatusMenu.add(new CheckField(b, bg, strHideFieldTitle + " (" + strLoadingRinganim + ")", strHide) {
            private final long serialVersionUID = 1L;

            @Override
            public void btnPressed(boolean selected) {
                GeneralChangesMenu.this.skin.setLoadingStatusRinganimHidden(selected);
            }
        });
    }
}
