/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.io.IOException;
import java.nio.file.Path;

import ale.Constants;
import ale.controller.Main;
import ale.model.skin.SkinConstants.Animation;
import ale.model.skin.SkinConstants.CommandButton;
import ale.model.skin.SkinConstants.Fontstyle;
import ale.model.skin.SkinConstants.Fonttype;
import ale.model.skin.SkinConstants.Imagetype;
import ale.model.skin.SkinConstants.Position;
import ale.model.skin.SkinConstants.UIAnimationInits;
import ale.model.skin.SkinConstants.UIBorderthicknessInits;
import ale.model.skin.SkinConstants.UIDefaultImagePaths;
import ale.model.skin.SkinConstants.UIFontInits;
import ale.model.skin.SkinConstants.UIMarginInits;
import ale.model.skin.SkinConstants.UIPaddingInits;
import ale.model.skin.SkinConstants.UIPositionInits;
import ale.model.skin.SkinConstants.UIResNumbers;
import ale.model.skin.SkinConstants.UIShiftInits;
import ale.model.skin.SkinConstants.UISizeInits;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.skin <br/>
 * Class  : SkinPropertiesVO <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>SkinPropertiesVO</code> class contains getter und setter for skin settings.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class SkinPropertiesVO {

    private Path imageDir;
    private boolean somethingChanged;

    /**
     * 
     * 
     * @param skinImageDir The path to the directory in which the images should be saved.
     * @param preview the skinpreview object
     */
    public SkinPropertiesVO(Path skinImageDir, SkinPreviewVO preview) {
        assert (this.imageDir != null) && (preview != null);
        this.imageDir = skinImageDir;
        this.skinPreview = preview;
        this.somethingChanged = false;
    }

    /*
     * Converts the image and copies it to the image directory.
     */
    private Path handleImage(Path img, int resnumber) {
        assert FileUtil.control(img) && (resnumber > 0);
        Path ret = null;

        if (FileUtil.control(img)) {
            try {
                ret = Bitmap.convertToBMP(img, this.imageDir, "" + resnumber);
                if (!img.getFileName().toString().endsWith(Constants.DEFAULT_SKINIMAGE_TYPE)) {
                    FileUtil.copyFile(img, this.imageDir.resolve(resnumber + Constants.DEFAULT_INPUTIMAGE_TYPE), false);
                }
            } catch (IOException
                     | InterruptedException e) {
                Main.handleUnhandableProblem(e);
                ret = null;
            }
        }

        return ret;
    }

    /*
     * Deletes resource and input image.
     */
    private void deleteImage(int resnumber) {
        assert resnumber > 0;
        FileUtil.deleteFile(this.imageDir.resolve(resnumber + Constants.DEFAULT_INPUTIMAGE_TYPE));
        FileUtil.deleteFile(this.imageDir.resolve(resnumber + Constants.DEFAULT_SKINIMAGE_TYPE));
    }

    /**
     * Asks if the skin was changed.
     *
     * @return boolean
     */
    public boolean isChanged() {
        return this.somethingChanged;
    }

    protected void setChangestatus(boolean somethingIsChanged) {
        this.somethingChanged = somethingIsChanged;
    }

    private void somethingChanged() {
        this.somethingChanged = true;
    }

    /*
     * #####
     */
    private SkinPreviewVO skinPreview;

    /**
     * Skinfilename.
     *
     * @return filename
     */
    public String getSkinFilename() {
        return this.skinPreview.getFilename();
    }

    /**
     * Sets the skin name.
     *
     * @param name name string
     */
    public void setName(String name) {
        this.skinPreview.setName(name);
    }

    /**
     * Returns the skin name.
     *
     * @return skinname
     */
    public String getName() {
        return this.skinPreview.getName();
    }

    /**
     * Sets the author name.
     *
     * @param author author name
     */
    public void setAuthor(String author) {
        this.skinPreview.setAuthor(author);
    }

    /**
     * Returns the author name.
     *
     * @return author name
     */
    public String getAuthor() {
        return this.skinPreview.getAuthor();
    }

    /**
     * Sets the website adress.
     *
     * @param website webadress
     */
    public void setWebsite(String website) {
        this.skinPreview.setWebsite(website);
    }

    /**
     * Returns the web adress.
     *
     * @return website adress
     */
    public String getWebsite() {
        return this.skinPreview.getWebsite();
    }

    /**
     * Sets the preview image path.
     *
     * @param image image path of the preview.
     * @throws IOException thrown if invalid
     */
    public void setPreviewimage(Path image) throws IOException {
        this.skinPreview.setImage(image);
    }

    /**
     * Returns the preview image.
     *
     * @return the preview image path
     */
    public Path getPreviewimage() {
        return this.skinPreview.getImage();
    }

    /**
     * Saves the skinpreview at the given location.
     *
     * @param destination path where it should be saved.
     * @throws IOException if the path is invalid.
     */
    public void saveSkinPreview(Path destination) throws IOException {
        this.skinPreview.save(destination);
    }

    /*
     * ##################################################
     */
    /*
     * Begin of image changes.
     * 
     * No asserts after this line. That would take me an hour, again^x
     */
    /*
     * ##################################################
     */

    /*
     * ##################################################
     */
    // Background

    private Path imgBackground = null;

    private boolean backgroundImgChanged;

    public Path getBackground() {
        return this.imgBackground;
    }

    public void setBackground(Path img) throws IOException {
        if (FileUtil.control(img)) {
            FileUtil.copyFile(img, this.imageDir.resolve(Constants.SKIN_BG_NAME), false);
            this.backgroundImgChanged = true;
            this.imgBackground = this.imageDir.resolve(Constants.SKIN_BG_NAME);

        } else {
            this.imgBackground = null;
            FileUtil.deleteFile(this.imageDir.resolve(Constants.SKIN_BG_NAME));
            this.backgroundImgChanged = false;
        }

        somethingChanged();
    }

    public void setBackgroundWithoutHandling(Path img) {
        if (FileUtil.control(img)) {
            this.imgBackground = img;
            this.backgroundImgChanged = true;

        } else {
            this.imgBackground = null;
            this.backgroundImgChanged = false;
        }

        somethingChanged();
    }

    public boolean getBackgroundImgChanged() {
        return this.backgroundImgChanged;
    }

    /*
     * ##################################################
     */
    // Branding
    private BrandingVO brd;

    public BrandingVO getBranding() {
        if (this.brd == null) {
            setBranding(null, null, null);
        }

        return this.brd;
    }

    public void setBranding(Path small, Path medium, Path big) {
        Path s = handleImage(small, UIResNumbers.BRANDING_SMALL.getNum());
        Path m = handleImage(medium, UIResNumbers.BRANDING_MEDIUM.getNum());
        Path b = handleImage(big, UIResNumbers.BRANDING_BIG.getNum());

        s = s == null ? UIDefaultImagePaths.BRANDING_SMALL.getPath() : s;
        m = m == null ? UIDefaultImagePaths.BRANDING_MEDIUM.getPath() : m;
        b = b == null ? UIDefaultImagePaths.BRANDING_BIG.getPath() : b;

        this.brd = new BrandingVO(s, m, b);

        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Window

    private Path imgWindow = UIDefaultImagePaths.WINDOW.getPath();
    private boolean windowImgChanged;

    public Path getImgPath_Window() {
        return this.imgWindow;
    }

    public void setImgPath_Window(Path img) {
        this.imgWindow = handleImage(img, UIResNumbers.WINDOW.getNum());

        if (img != null) {
            this.windowImgChanged = true;

        } else {
            this.windowImgChanged = false;
            this.imgWindow = UIDefaultImagePaths.WINDOW.getPath();
            deleteImage(UIResNumbers.WINDOW.getNum());
        }
        somethingChanged();
    }

    public boolean getWindowImgChanged() {
        return this.windowImgChanged;
    }

    /*
     * ##################################################
     */
    // Password Field
    private Path imgPWField_Def = UIDefaultImagePaths.PWFIELD_DEF.getPath();
    private Path imgPWField_Dis = UIDefaultImagePaths.PWFIELD_DIS.getPath();
    private Path imgPWField_KFoc = UIDefaultImagePaths.PWFIELD_KFOC.getPath();
    private Path imgPWField_MFoc = UIDefaultImagePaths.PWFIELD_MFOC.getPath();

    public Path getImgPath_PWField(Imagetype type) {
        Path ret = null;

        switch (type) {
            case DEFAULT:
                ret = this.imgPWField_Def;
                break;

            case DISABLED:
                ret = this.imgPWField_Dis;
                break;

            case KEYFOCUS:
                ret = this.imgPWField_KFoc;
                break;

            case MOUSEFOCUS:
                ret = this.imgPWField_MFoc;
                break;

            default:
                throw new IllegalArgumentException();
        }

        return ret;
    }

    public void setImgPath_PWField(Path img, Imagetype type) {
        switch (type) {
            case DEFAULT:
                this.imgPWField_Def = handleImage(img, UIResNumbers.PWFIELD_DEF.getNum());
                if (this.imgPWField_Def == null) {
                    this.imgPWField_Def = UIDefaultImagePaths.PWFIELD_DEF.getPath();
                    deleteImage(UIResNumbers.PWFIELD_DEF.getNum());
                }
                break;

            case DISABLED:
                this.imgPWField_Dis = handleImage(img, UIResNumbers.PWFIELD_DIS.getNum());
                if (this.imgPWField_Dis == null) {
                    this.imgPWField_Dis = UIDefaultImagePaths.PWFIELD_DIS.getPath();
                    deleteImage(UIResNumbers.PWFIELD_DIS.getNum());
                }
                break;

            case KEYFOCUS:
                this.imgPWField_KFoc = handleImage(img, UIResNumbers.PWFIELD_KFOC.getNum());
                if (this.imgPWField_KFoc == null) {
                    this.imgPWField_KFoc = UIDefaultImagePaths.PWFIELD_KFOC.getPath();
                    deleteImage(UIResNumbers.PWFIELD_KFOC.getNum());
                }
                break;

            case MOUSEFOCUS:
                this.imgPWField_MFoc = handleImage(img, UIResNumbers.PWFIELD_MFOC.getNum());
                if (this.imgPWField_MFoc == null) {
                    this.imgPWField_MFoc = UIDefaultImagePaths.PWFIELD_MFOC.getPath();
                    deleteImage(UIResNumbers.PWFIELD_MFOC.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Slider
    private Path imgSlider_Def = UIDefaultImagePaths.SLIDER_BAR_DEF.getPath();
    private Path imgSlider_Foc = UIDefaultImagePaths.SLIDER_BAR_FOC.getPath();
    private Path imgSlider_Pre = UIDefaultImagePaths.SLIDER_BAR_PRE.getPath();

    public Path getImgPath_SliderBar(Imagetype type) {
        Path ret = null;

        switch (type) {
            case DEFAULT:
                ret = this.imgSlider_Def;
                break;

            case FOCUS:
                ret = this.imgSlider_Foc;
                break;

            case PRESSED:
                ret = this.imgSlider_Pre;
                break;

            default:
                throw new IllegalArgumentException();
        }

        return ret;
    }

    public void setImgPath_SliderBar(Path img, Imagetype type) {
        switch (type) {
            case DEFAULT:
                this.imgSlider_Def = handleImage(img, UIResNumbers.SLIDER_BAR_DEF.getNum());
                if (this.imgSlider_Def == null) {
                    this.imgSlider_Def = UIDefaultImagePaths.SLIDER_BAR_DEF.getPath();
                    deleteImage(UIResNumbers.SLIDER_BAR_DEF.getNum());
                }
                break;

            case FOCUS:
                this.imgSlider_Foc = handleImage(img, UIResNumbers.SLIDER_BAR_FOC.getNum());
                if (this.imgSlider_Foc == null) {
                    this.imgSlider_Foc = UIDefaultImagePaths.SLIDER_BAR_FOC.getPath();
                    deleteImage(UIResNumbers.SLIDER_BAR_FOC.getNum());
                }
                break;

            case PRESSED:
                this.imgSlider_Pre = handleImage(img, UIResNumbers.SLIDER_BAR_PRE.getNum());
                if (this.imgSlider_Pre == null) {
                    this.imgSlider_Pre = UIDefaultImagePaths.SLIDER_BAR_PRE.getPath();
                    deleteImage(UIResNumbers.SLIDER_BAR_PRE.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Slider Up Button
    private Path imgSliderArrowUp_Def = UIDefaultImagePaths.SLIDER_UP_DEF.getPath();
    private Path imgSliderArrowUp_Foc = UIDefaultImagePaths.SLIDER_UP_FOC.getPath();
    private Path imgSliderArrowUp_Pre = UIDefaultImagePaths.SLIDER_UP_PRE.getPath();

    public Path getImgPath_SliderArrowUp(Imagetype type) {
        Path ret = null;

        switch (type) {
            case DEFAULT:
                ret = this.imgSliderArrowUp_Def;
                break;

            case FOCUS:
                ret = this.imgSliderArrowUp_Foc;
                break;

            case PRESSED:
                ret = this.imgSliderArrowUp_Pre;
                break;

            default:
                throw new IllegalArgumentException();
        }

        return ret;
    }

    public void setImgPath_SliderArrowUp(Path img, Imagetype type) {
        switch (type) {
            case DEFAULT:
                this.imgSliderArrowUp_Def = handleImage(img, UIResNumbers.SLIDER_UP_DEF.getNum());
                if (this.imgSliderArrowUp_Def == null) {
                    this.imgSliderArrowUp_Def = UIDefaultImagePaths.SLIDER_UP_DEF.getPath();
                    deleteImage(UIResNumbers.SLIDER_UP_DEF.getNum());
                }
                break;

            case FOCUS:
                this.imgSliderArrowUp_Foc = handleImage(img, UIResNumbers.SLIDER_UP_FOC.getNum());
                if (this.imgSliderArrowUp_Foc == null) {
                    this.imgSliderArrowUp_Foc = UIDefaultImagePaths.SLIDER_UP_FOC.getPath();
                    deleteImage(UIResNumbers.SLIDER_UP_FOC.getNum());
                }
                break;

            case PRESSED:
                this.imgSliderArrowUp_Pre = handleImage(img, UIResNumbers.SLIDER_UP_PRE.getNum());
                if (this.imgSliderArrowUp_Pre == null) {
                    this.imgSliderArrowUp_Pre = UIDefaultImagePaths.SLIDER_UP_PRE.getPath();
                    deleteImage(UIResNumbers.SLIDER_UP_PRE.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Slider Down Button
    private Path imgSliderArrowDown_Def = UIDefaultImagePaths.SLIDER_DOWN_DEF.getPath();
    private Path imgSliderArrowDown_Foc = UIDefaultImagePaths.SLIDER_DOWN_FOC.getPath();
    private Path imgSliderArrowDown_Pre = UIDefaultImagePaths.SLIDER_DOWN_PRE.getPath();

    public Path getImgPath_SliderArrowDown(Imagetype type) {
        Path ret = null;

        switch (type) {
            case DEFAULT:
                ret = this.imgSliderArrowDown_Def;
                break;

            case FOCUS:
                ret = this.imgSliderArrowDown_Foc;
                break;

            case PRESSED:
                ret = this.imgSliderArrowDown_Pre;
                break;

            default:
                throw new IllegalArgumentException();
        }

        return ret;
    }

    public void setImgPath_SliderArrowDown(Path img, Imagetype type) {
        switch (type) {
            case DEFAULT:
                this.imgSliderArrowDown_Def = handleImage(img, UIResNumbers.SLIDER_DOWN_DEF.getNum());
                if (this.imgSliderArrowDown_Def == null) {
                    this.imgSliderArrowDown_Def = UIDefaultImagePaths.SLIDER_DOWN_DEF.getPath();
                    deleteImage(UIResNumbers.SLIDER_DOWN_DEF.getNum());
                }
                break;

            case FOCUS:
                this.imgSliderArrowDown_Foc = handleImage(img, UIResNumbers.SLIDER_DOWN_FOC.getNum());
                if (this.imgSliderArrowDown_Foc == null) {
                    this.imgSliderArrowDown_Foc = UIDefaultImagePaths.SLIDER_DOWN_FOC.getPath();
                    deleteImage(UIResNumbers.SLIDER_DOWN_FOC.getNum());
                }
                break;

            case PRESSED:
                this.imgSliderArrowDown_Pre = handleImage(img, UIResNumbers.SLIDER_DOWN_PRE.getNum());
                if (this.imgSliderArrowDown_Pre == null) {
                    this.imgSliderArrowDown_Pre = UIDefaultImagePaths.SLIDER_DOWN_PRE.getPath();
                    deleteImage(UIResNumbers.SLIDER_DOWN_PRE.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Slider Middle Button

    private Path imgSliderMidBtn_Def = UIDefaultImagePaths.SLIDER_MID_DEF.getPath();
    private Path imgSliderMidBtn_Foc = UIDefaultImagePaths.SLIDER_MID_FOC.getPath();
    private Path imgSliderMidBtn_Pre = UIDefaultImagePaths.SLIDER_MID_PRE.getPath();

    public Path getImgPath_SliderMidBtn(Imagetype type) {
        Path ret = null;

        switch (type) {
            case DEFAULT:
                ret = this.imgSliderMidBtn_Def;
                break;

            case FOCUS:
                ret = this.imgSliderMidBtn_Foc;
                break;

            case PRESSED:
                ret = this.imgSliderMidBtn_Pre;
                break;

            default:
                throw new IllegalArgumentException();
        }

        return ret;
    }

    public void setImgPath_SliderMidBtn(Path img, Imagetype type) {
        switch (type) {
            case DEFAULT:
                this.imgSliderMidBtn_Def = handleImage(img, UIResNumbers.SLIDER_MID_DEF.getNum());
                if (this.imgSliderMidBtn_Def == null) {
                    this.imgSliderMidBtn_Def = UIDefaultImagePaths.SLIDER_MID_DEF.getPath();
                    deleteImage(UIResNumbers.SLIDER_MID_DEF.getNum());
                }
                break;

            case FOCUS:
                this.imgSliderMidBtn_Foc = handleImage(img, UIResNumbers.SLIDER_MID_FOC.getNum());
                if (this.imgSliderMidBtn_Foc == null) {
                    this.imgSliderMidBtn_Foc = UIDefaultImagePaths.SLIDER_MID_FOC.getPath();
                    deleteImage(UIResNumbers.SLIDER_MID_FOC.getNum());
                }
                break;

            case PRESSED:
                this.imgSliderMidBtn_Pre = handleImage(img, UIResNumbers.SLIDER_MID_PRE.getNum());
                if (this.imgSliderMidBtn_Pre == null) {
                    this.imgSliderMidBtn_Pre = UIDefaultImagePaths.SLIDER_MID_PRE.getPath();
                    deleteImage(UIResNumbers.SLIDER_MID_PRE.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Ease of Access Symbol

    private Path imgAccessSym = UIDefaultImagePaths.ACCESSSYMBOL.getPath();

    public Path getImgPath_AccessSym() {
        return this.imgAccessSym;
    }

    public void setImgPath_AccessSym(Path img) {
        this.imgAccessSym = handleImage(img, UIResNumbers.ACCESSSYMBOL.getNum());
        if (this.imgAccessSym == null) {
            this.imgAccessSym = UIDefaultImagePaths.ACCESSSYMBOL.getPath();
            deleteImage(UIResNumbers.ACCESSSYMBOL.getNum());
        }

        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Shutdown Symbol

    private Path imgShutdownSym = UIDefaultImagePaths.SHUTDOWNSYMBOL.getPath();

    public Path getImgPath_ShutdownSym() {
        return this.imgShutdownSym;
    }

    public void setImgPath_ShutdownSym(Path img) {
        this.imgShutdownSym = handleImage(img, UIResNumbers.SHUTDOWNSYMBOL.getNum());
        if (this.imgShutdownSym == null) {
            this.imgShutdownSym = UIDefaultImagePaths.SHUTDOWNSYMBOL.getPath();
            deleteImage(UIResNumbers.SHUTDOWNSYMBOL.getNum());
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Shutdown Symbol with Update

    private Path imgShutdownUpdateSym = UIDefaultImagePaths.SHUTDOWNUPDATESYMBOL.getPath();

    public Path getImgPath_ShutdownUpdateSym() {
        return this.imgShutdownUpdateSym;
    }

    public void setImgPath_ShutdownUpdateSym(Path img) {
        this.imgShutdownUpdateSym = handleImage(img, UIResNumbers.SHUTDOWNUPDATESYMBOL.getNum());
        if (this.imgShutdownUpdateSym == null) {
            this.imgShutdownUpdateSym = UIDefaultImagePaths.SHUTDOWNUPDATESYMBOL.getPath();
            deleteImage(UIResNumbers.SHUTDOWNUPDATESYMBOL.getNum());
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Shutdown Menu Symbol

    private Path imgShutdownArrowSym = UIDefaultImagePaths.SHUTDOWNARROWSYMBOL.getPath();

    public Path getImgPath_ShutdownArrowSym() {
        return this.imgShutdownArrowSym;
    }

    public void setImgPath_ShutdownArrowSym(Path img) {
        this.imgShutdownArrowSym = handleImage(img, UIResNumbers.SHUTDOWNARROWSYMBOL.getNum());
        if (this.imgShutdownArrowSym == null) {
            this.imgShutdownArrowSym = UIDefaultImagePaths.SHUTDOWNARROWSYMBOL.getPath();
            deleteImage(UIResNumbers.SHUTDOWNARROWSYMBOL.getNum());
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Userlist

    private Path imgUserlist_MFoc = UIDefaultImagePaths.USERLIST_IMAGEFRAME_MFOC.getPath();
    private Path imgUserlist_Sel = UIDefaultImagePaths.USERLIST_IMAGEFRAME_SEL.getPath();
    private Path imgUserlist_FocSel = UIDefaultImagePaths.USERLIST_IMAGEFRAME_FOCSEL.getPath();
    private Path imgUserlist_Def = UIDefaultImagePaths.USERLIST_IMAGEFRAME_DEF.getPath();

    public Path getImgPath_UserlistImage(Imagetype type) {
        Path ret = null;
        switch (type) {
            case MOUSEFOCUS:
                ret = this.imgUserlist_MFoc;
                break;

            case SELECTED:
                ret = this.imgUserlist_Sel;
                break;

            case FOCUSSELECTED:
                ret = this.imgUserlist_FocSel;
                break;

            case DEFAULT:
                ret = this.imgUserlist_Def;
                break;

            default:
                throw new IllegalArgumentException();
        }

        return ret;
    }

    public void setImgPath_UserlistImage(Path img, Imagetype type) {
        switch (type) {
            case MOUSEFOCUS:
                this.imgUserlist_MFoc = handleImage(img, UIResNumbers.USERLIST_IMAGEFRAME_MFOC.getNum());
                if (this.imgUserlist_MFoc == null) {
                    this.imgUserlist_MFoc = UIDefaultImagePaths.USERLIST_IMAGEFRAME_MFOC.getPath();
                    deleteImage(UIResNumbers.USERLIST_IMAGEFRAME_MFOC.getNum());
                }
                break;

            case SELECTED:
                this.imgUserlist_Sel = handleImage(img, UIResNumbers.USERLIST_IMAGEFRAME_SEL.getNum());
                if (this.imgUserlist_Sel == null) {
                    this.imgUserlist_Sel = UIDefaultImagePaths.USERLIST_IMAGEFRAME_SEL.getPath();
                    deleteImage(UIResNumbers.USERLIST_IMAGEFRAME_SEL.getNum());
                }
                break;

            case FOCUSSELECTED:
                this.imgUserlist_FocSel = handleImage(img, UIResNumbers.USERLIST_IMAGEFRAME_FOCSEL.getNum());
                if (this.imgUserlist_FocSel == null) {
                    this.imgUserlist_FocSel = UIDefaultImagePaths.USERLIST_IMAGEFRAME_FOCSEL.getPath();
                    deleteImage(UIResNumbers.USERLIST_IMAGEFRAME_FOCSEL.getNum());
                }
                break;

            case DEFAULT:
                this.imgUserlist_Def = handleImage(img, UIResNumbers.USERLIST_IMAGEFRAME_DEF.getNum());
                if (this.imgUserlist_Def == null) {
                    this.imgUserlist_Def = UIDefaultImagePaths.USERLIST_IMAGEFRAME_DEF.getPath();
                    deleteImage(UIResNumbers.USERLIST_IMAGEFRAME_DEF.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Zoomed Usertile

    private Path imgUsertile_Def = UIDefaultImagePaths.USERTILE_IMAGEFRAME_DEF.getPath();

    public Path getImgPath_UsertileImage() {
        return this.imgUsertile_Def;
    }

    public void setImgPath_UsertileImage(Path img) {
        this.imgUsertile_Def = handleImage(img, UIResNumbers.USERTILE_IMAGEFRAME_DEF.getNum());
        if (this.imgUsertile_Def == null) {
            this.imgUsertile_Def = UIDefaultImagePaths.USERTILE_IMAGEFRAME_DEF.getPath();
            deleteImage(UIResNumbers.USERTILE_IMAGEFRAME_DEF.getNum());
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Command Button, little Arrow (STRG-ALT-ENTF Menu)

    private Path imgComArrow_KFoc = UIDefaultImagePaths.COMBTNARROW_KFOC.getPath();
    private Path imgComArrow_MFoc = UIDefaultImagePaths.COMBTNARROW_MFOC.getPath();
    private Path imgComArrow_Pre = UIDefaultImagePaths.COMBTNARROW_PRE.getPath();
    private Path imgComArrow_Def = UIDefaultImagePaths.COMBTNARROW_DEF.getPath();

    public Path getImgPath_CommandBtnArrow(Imagetype type) {
        Path ret = null;

        switch (type) {
            case KEYFOCUS:
                ret = this.imgComArrow_KFoc;
                break;

            case MOUSEFOCUS:
                ret = this.imgComArrow_MFoc;
                break;

            case PRESSED:
                ret = this.imgComArrow_Pre;
                break;

            case DEFAULT:
                ret = this.imgComArrow_Def;
                break;

            default:
                throw new IllegalArgumentException();
        }
        return ret;
    }

    public void setImgPath_CommandBtnArrow(Path img, Imagetype type) {
        switch (type) {
            case KEYFOCUS:
                this.imgComArrow_KFoc = handleImage(img, UIResNumbers.COMBTNARROW_KFOC.getNum());
                if (this.imgComArrow_KFoc == null) {
                    this.imgComArrow_KFoc = UIDefaultImagePaths.COMBTNARROW_KFOC.getPath();
                    deleteImage(UIResNumbers.COMBTNARROW_KFOC.getNum());
                }
                break;

            case MOUSEFOCUS:
                this.imgComArrow_MFoc = handleImage(img, UIResNumbers.COMBTNARROW_MFOC.getNum());
                if (this.imgComArrow_MFoc == null) {
                    this.imgComArrow_MFoc = UIDefaultImagePaths.COMBTNARROW_MFOC.getPath();
                    deleteImage(UIResNumbers.COMBTNARROW_MFOC.getNum());
                }
                break;

            case PRESSED:
                this.imgComArrow_Pre = handleImage(img, UIResNumbers.COMBTNARROW_PRE.getNum());
                if (this.imgComArrow_Pre == null) {
                    this.imgComArrow_Pre = UIDefaultImagePaths.COMBTNARROW_PRE.getPath();
                    deleteImage(UIResNumbers.COMBTNARROW_PRE.getNum());
                }
                break;

            case DEFAULT:
                this.imgComArrow_Def = handleImage(img, UIResNumbers.COMBTNARROW_DEF.getNum());
                if (this.imgComArrow_Def == null) {
                    this.imgComArrow_Def = UIDefaultImagePaths.COMBTNARROW_DEF.getPath();
                    deleteImage(UIResNumbers.COMBTNARROW_DEF.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Command Button (STRG-ALT-ENTF Menu)

    private Path imgComBtn_KFoc = UIDefaultImagePaths.COMBTN_KFOC.getPath();
    private Path imgComBtn_MFoc = UIDefaultImagePaths.COMBTN_MFOC.getPath();
    private Path imgComBtn_Pre = UIDefaultImagePaths.COMBTN_PRE.getPath();
    private Path imgComBtn_Def = UIDefaultImagePaths.COMBTN_DEF.getPath();

    private boolean imgComBtn_Def_addToLayout;

    public Path getImgPath_CommandBtn(Imagetype type) {
        Path ret = null;

        switch (type) {
            case KEYFOCUS:
                ret = this.imgComBtn_KFoc;
                break;

            case MOUSEFOCUS:
                ret = this.imgComBtn_MFoc;
                break;

            case PRESSED:
                ret = this.imgComBtn_Pre;
                break;

            case DEFAULT:
                ret = this.imgComBtn_Def;
                break;

            default:
                throw new IllegalArgumentException();
        }
        return ret;
    }

    public void setImgPath_CommandBtn(Path img, Imagetype type) {
        switch (type) {
            case KEYFOCUS:
                this.imgComBtn_KFoc = handleImage(img, UIResNumbers.COMBTN_KFOC.getNum());
                if (this.imgComBtn_KFoc == null) {
                    this.imgComBtn_KFoc = UIDefaultImagePaths.COMBTN_KFOC.getPath();
                    deleteImage(UIResNumbers.COMBTN_KFOC.getNum());
                }
                break;

            case MOUSEFOCUS:
                this.imgComBtn_MFoc = handleImage(img, UIResNumbers.COMBTN_MFOC.getNum());
                if (this.imgComBtn_MFoc == null) {
                    this.imgComBtn_MFoc = UIDefaultImagePaths.COMBTN_MFOC.getPath();
                    deleteImage(UIResNumbers.COMBTN_MFOC.getNum());
                }
                break;

            case PRESSED:
                this.imgComBtn_Pre = handleImage(img, UIResNumbers.COMBTN_PRE.getNum());
                if (this.imgComBtn_Pre == null) {
                    this.imgComBtn_Pre = UIDefaultImagePaths.COMBTN_PRE.getPath();
                    deleteImage(UIResNumbers.COMBTN_PRE.getNum());
                }
                break;

            case DEFAULT:
                this.imgComBtn_Def = handleImage(img, UIResNumbers.COMBTN_DEF.getNum());
                if (this.imgComBtn_Def == null) {
                    this.imgComBtn_Def = UIDefaultImagePaths.COMBTN_DEF.getPath();
                    deleteImage(UIResNumbers.COMBTN_DEF.getNum());
                }

                if (img != null) {
                    this.imgComBtn_Def_addToLayout = true;

                } else {
                    this.imgComBtn_Def_addToLayout = false;
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    public boolean getCommandBtnImgDefaultChanged() {
        return this.imgComBtn_Def_addToLayout;
    }

    /*
     * ##################################################
     */
    // Standard Button

    private Path imgStdBtn_KFoc = UIDefaultImagePaths.STDBTN_KFOC.getPath();
    private Path imgStdBtn_MFoc = UIDefaultImagePaths.STDBTN_MFOC.getPath();
    private Path imgStdBtn_KFocMFoc = UIDefaultImagePaths.STDBTN_KFOCMFOC.getPath();
    private Path imgStdBtn_Pre = UIDefaultImagePaths.STDBTN_PRE.getPath();
    private Path imgStdBtn_Def = UIDefaultImagePaths.STDBTN_DEF.getPath();

    public Path getImgPath_StandardBtn(Imagetype type) {
        Path ret = null;

        switch (type) {
            case KEYFOCUS:
                ret = this.imgStdBtn_KFoc;
                break;

            case MOUSEFOCUS:
                ret = this.imgStdBtn_MFoc;
                break;

            case MOUSEFOCUS_KEYFOCUS:
                ret = this.imgStdBtn_KFocMFoc;
                break;

            case PRESSED:
                ret = this.imgStdBtn_Pre;
                break;

            case DEFAULT:
                ret = this.imgStdBtn_Def;
                break;

            default:
                throw new IllegalArgumentException();
        }
        return ret;
    }

    public void setImgPath_StandardBtn(Path img, Imagetype type) {
        switch (type) {
            case KEYFOCUS:
                this.imgStdBtn_KFoc = handleImage(img, UIResNumbers.STDBTN_KFOC.getNum());
                if (this.imgStdBtn_KFoc == null) {
                    this.imgStdBtn_KFoc = UIDefaultImagePaths.STDBTN_KFOC.getPath();
                    deleteImage(UIResNumbers.STDBTN_KFOC.getNum());
                }
                break;

            case MOUSEFOCUS:
                this.imgStdBtn_MFoc = handleImage(img, UIResNumbers.STDBTN_MFOC.getNum());
                if (this.imgStdBtn_MFoc == null) {
                    this.imgStdBtn_MFoc = UIDefaultImagePaths.STDBTN_MFOC.getPath();
                    deleteImage(UIResNumbers.STDBTN_MFOC.getNum());
                }
                break;

            case MOUSEFOCUS_KEYFOCUS:
                this.imgStdBtn_KFocMFoc = handleImage(img, UIResNumbers.STDBTN_KFOCMFOC.getNum());
                if (this.imgStdBtn_KFocMFoc == null) {
                    this.imgStdBtn_KFocMFoc = UIDefaultImagePaths.STDBTN_KFOCMFOC.getPath();
                    deleteImage(UIResNumbers.STDBTN_KFOCMFOC.getNum());
                }
                break;

            case PRESSED:
                this.imgStdBtn_Pre = handleImage(img, UIResNumbers.STDBTN_PRE.getNum());
                if (this.imgStdBtn_Pre == null) {
                    this.imgStdBtn_Pre = UIDefaultImagePaths.STDBTN_PRE.getPath();
                    deleteImage(UIResNumbers.STDBTN_PRE.getNum());
                }
                break;

            case DEFAULT:
                this.imgStdBtn_Def = handleImage(img, UIResNumbers.STDBTN_DEF.getNum());
                if (this.imgStdBtn_Def == null) {
                    this.imgStdBtn_Def = UIDefaultImagePaths.STDBTN_DEF.getPath();
                    deleteImage(UIResNumbers.STDBTN_DEF.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Password Confirm Button

    private Path imgPWBtn_KFocMFoc = UIDefaultImagePaths.PWBTN_KFOCMFOC.getPath();
    private Path imgPWBtn_Pre = UIDefaultImagePaths.PWBTN_PRE.getPath();
    private Path imgPWBtn_Def = UIDefaultImagePaths.PWBTN_DEF.getPath();

    public Path getImgPath_PWBtn(Imagetype type) {
        Path ret = null;

        switch (type) {
            case MOUSEFOCUS_KEYFOCUS:
                ret = this.imgPWBtn_KFocMFoc;
                break;

            case PRESSED:
                ret = this.imgPWBtn_Pre;
                break;

            case DEFAULT:
                ret = this.imgPWBtn_Def;
                break;

            default:
                throw new IllegalArgumentException();
        }
        return ret;
    }

    public void setImgPath_PWBtn(Path img, Imagetype type) {
        switch (type) {
            case MOUSEFOCUS_KEYFOCUS: // HD simplified
                this.imgPWBtn_KFocMFoc = handleImage(img, UIResNumbers.PWBTN_KFOCMFOC.getNum());
                if (this.imgPWBtn_KFocMFoc == null) {
                    this.imgPWBtn_KFocMFoc = UIDefaultImagePaths.PWBTN_KFOCMFOC.getPath();
                    deleteImage(UIResNumbers.PWBTN_KFOCMFOC.getNum());
                }
                break;

            case PRESSED:
                this.imgPWBtn_Pre = handleImage(img, UIResNumbers.PWBTN_PRE.getNum());
                if (this.imgPWBtn_Pre == null) {
                    this.imgPWBtn_Pre = UIDefaultImagePaths.PWBTN_PRE.getPath();
                    deleteImage(UIResNumbers.PWBTN_PRE.getNum());
                }
                break;

            case DEFAULT:
                this.imgPWBtn_Def = handleImage(img, UIResNumbers.PWBTN_DEF.getNum());
                if (this.imgPWBtn_Def == null) {
                    this.imgPWBtn_Def = UIDefaultImagePaths.PWBTN_DEF.getPath();
                    deleteImage(UIResNumbers.PWBTN_DEF.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Shutdown Button

    private Path imgShutdownBtn_KFocMFoc = UIDefaultImagePaths.SHUTDOWN_KFOCMFOC.getPath();
    private Path imgShutdownBtn_KFoc = UIDefaultImagePaths.SHUTDOWN_KFOC.getPath();
    private Path imgShutdownBtn_MFoc = UIDefaultImagePaths.SHUTDOWN_MFOC.getPath();
    private Path imgShutdownBtn_Pre = UIDefaultImagePaths.SHUTDOWN_PRE.getPath();
    private Path imgShutdownBtn_Def = UIDefaultImagePaths.SHUTDOWN_DEF.getPath();

    public Path getImgPath_ShutdownBtn(Imagetype type) {
        Path ret = null;

        switch (type) {
            case MOUSEFOCUS_KEYFOCUS:
                ret = this.imgShutdownBtn_KFocMFoc;
                break;

            case KEYFOCUS:
                ret = this.imgShutdownBtn_KFoc;
                break;

            case MOUSEFOCUS:
                ret = this.imgShutdownBtn_MFoc;
                break;

            case PRESSED:
                ret = this.imgShutdownBtn_Pre;
                break;

            case DEFAULT:
                ret = this.imgShutdownBtn_Def;
                break;

            default:
                throw new IllegalArgumentException();
        }
        return ret;
    }

    public void setImgPath_ShutdownBtn(Path img, Imagetype type) {
        switch (type) {
            case MOUSEFOCUS_KEYFOCUS:
                this.imgShutdownBtn_KFocMFoc = handleImage(img, UIResNumbers.SHUTDOWN_KFOCMFOC.getNum());
                if (this.imgShutdownBtn_KFocMFoc == null) {
                    this.imgShutdownBtn_KFocMFoc = UIDefaultImagePaths.SHUTDOWN_KFOCMFOC.getPath();
                    deleteImage(UIResNumbers.SHUTDOWN_KFOCMFOC.getNum());
                }
                break;

            case KEYFOCUS:
                this.imgShutdownBtn_KFoc = handleImage(img, UIResNumbers.SHUTDOWN_KFOC.getNum());
                if (this.imgShutdownBtn_KFoc == null) {
                    this.imgShutdownBtn_KFoc = UIDefaultImagePaths.SHUTDOWN_KFOC.getPath();
                    deleteImage(UIResNumbers.SHUTDOWN_KFOC.getNum());
                }
                break;

            case MOUSEFOCUS:
                this.imgShutdownBtn_MFoc = handleImage(img, UIResNumbers.SHUTDOWN_MFOC.getNum());
                if (this.imgShutdownBtn_MFoc == null) {
                    this.imgShutdownBtn_MFoc = UIDefaultImagePaths.SHUTDOWN_MFOC.getPath();
                    deleteImage(UIResNumbers.SHUTDOWN_MFOC.getNum());
                }
                break;

            case PRESSED:
                this.imgShutdownBtn_Pre = handleImage(img, UIResNumbers.SHUTDOWN_PRE.getNum());
                if (this.imgShutdownBtn_Pre == null) {
                    this.imgShutdownBtn_Pre = UIDefaultImagePaths.SHUTDOWN_PRE.getPath();
                    deleteImage(UIResNumbers.SHUTDOWN_PRE.getNum());
                }
                break;

            case DEFAULT:
                this.imgShutdownBtn_Def = handleImage(img, UIResNumbers.SHUTDOWN_DEF.getNum());
                if (this.imgShutdownBtn_Def == null) {
                    this.imgShutdownBtn_Def = UIDefaultImagePaths.SHUTDOWN_DEF.getPath();
                    deleteImage(UIResNumbers.SHUTDOWN_DEF.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Shutdown Menu

    private Path imgShutdownMenu_KFocMFoc = UIDefaultImagePaths.SHUTDOWNMENU_KFOCMFOC.getPath();
    private Path imgShutdownMenu_KFoc = UIDefaultImagePaths.SHUTDOWNMENU_KFOC.getPath();
    private Path imgShutdownMenu_MFoc = UIDefaultImagePaths.SHUTDOWNMENU_MFOC.getPath();
    private Path imgShutdownMenu_Pre = UIDefaultImagePaths.SHUTDOWNMENU_PRE.getPath();
    private Path imgShutdownMenu_Def = UIDefaultImagePaths.SHUTDOWNMENU_DEF.getPath();

    public Path getImgPath_ShutdownMenu(Imagetype type) {
        Path ret = null;

        switch (type) {
            case MOUSEFOCUS_KEYFOCUS:
                ret = this.imgShutdownMenu_KFocMFoc;
                break;

            case KEYFOCUS:
                ret = this.imgShutdownMenu_KFoc;
                break;

            case MOUSEFOCUS:
                ret = this.imgShutdownMenu_MFoc;
                break;

            case PRESSED:
                ret = this.imgShutdownMenu_Pre;
                break;

            case DEFAULT:
                ret = this.imgShutdownMenu_Def;
                break;

            default:
                throw new IllegalArgumentException();
        }
        return ret;
    }

    public void setImgPath_ShutdownMenu(Path img, Imagetype type) {
        switch (type) {
            case MOUSEFOCUS_KEYFOCUS:
                this.imgShutdownMenu_KFocMFoc = handleImage(img, UIResNumbers.SHUTDOWNMENU_KFOCMFOC.getNum());
                if (this.imgShutdownMenu_KFocMFoc == null) {
                    this.imgShutdownMenu_KFocMFoc = UIDefaultImagePaths.SHUTDOWNMENU_KFOCMFOC.getPath();
                    deleteImage(UIResNumbers.SHUTDOWNMENU_KFOCMFOC.getNum());
                }
                break;

            case KEYFOCUS:
                this.imgShutdownMenu_KFoc = handleImage(img, UIResNumbers.SHUTDOWNMENU_KFOC.getNum());
                if (this.imgShutdownMenu_KFoc == null) {
                    this.imgShutdownMenu_KFoc = UIDefaultImagePaths.SHUTDOWNMENU_KFOC.getPath();
                    deleteImage(UIResNumbers.SHUTDOWNMENU_KFOC.getNum());
                }
                break;

            case MOUSEFOCUS:
                this.imgShutdownMenu_MFoc = handleImage(img, UIResNumbers.SHUTDOWNMENU_MFOC.getNum());
                if (this.imgShutdownMenu_MFoc == null) {
                    this.imgShutdownMenu_MFoc = UIDefaultImagePaths.SHUTDOWNMENU_MFOC.getPath();
                    deleteImage(UIResNumbers.SHUTDOWNMENU_MFOC.getNum());
                }
                break;

            case PRESSED:
                this.imgShutdownMenu_Pre = handleImage(img, UIResNumbers.SHUTDOWNMENU_PRE.getNum());
                if (this.imgShutdownMenu_Pre == null) {
                    this.imgShutdownMenu_Pre = UIDefaultImagePaths.SHUTDOWNMENU_PRE.getPath();
                    deleteImage(UIResNumbers.SHUTDOWNMENU_PRE.getNum());
                }
                break;

            case DEFAULT:
                this.imgShutdownMenu_Def = handleImage(img, UIResNumbers.SHUTDOWNMENU_DEF.getNum());
                if (this.imgShutdownMenu_Def == null) {
                    this.imgShutdownMenu_Def = UIDefaultImagePaths.SHUTDOWNMENU_DEF.getPath();
                    deleteImage(UIResNumbers.SHUTDOWNMENU_DEF.getNum());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    // Loading status bg

    private Path imgLoadingStatusBg = null;

    public Path getImgPathLoadingStatusBg() {
        return this.imgLoadingStatusBg;
    }

    public void setImgPathLoadingStatusBg(Path img) {
        this.imgLoadingStatusBg = handleImage(img, UIResNumbers.LOADINGSTATUS.getNum());
        if (this.imgLoadingStatusBg == null) {
            deleteImage(UIResNumbers.LOADINGSTATUS.getNum());
        }
    }

    /*
     * ##################################################
     */
    /*
     * End of bitmap changes. Begin layout changes.
     */
    /*
     * ##################################################
     */
    // Branding

    private Position branding_position = UIPositionInits.BRANDING.getPosition();

    public void setBrandingPosition(Position position) {
        if (position == null) {
            this.branding_position = UIPositionInits.BRANDING.getPosition();

        } else {
            if (!position.equals(Position.TOP) && !position.equals(Position.LEFT) && !position.equals(Position.RIGHT)
                    && !position.equals(Position.BOTTOM) && !position.equals(Position.NONE)) {
                IllegalArgumentException iae = new IllegalArgumentException(
                        "The position not valid! Valid positions: left, right, top, bottom, none.");
                Main.handleUnhandableProblem(iae);
            }

            this.branding_position = position;
        }
        somethingChanged();
    }

    public Position getBrandingPosition() {
        return this.branding_position;
    }

    /*
     * ##################################################
     */
    // Window Layout

    private boolean window_IsActive = false;
    private int window_Height = UISizeInits.WINDOW.getHeight();
    private int window_Width = UISizeInits.WINDOW.getWidth();
    private int[] window_Borderthickness = UIBorderthicknessInits.WINDOW.getBorderthickness();
    private int[] window_Padding = null;
    private Position window_Position = null;
    private Animation window_Animation = UIAnimationInits.WINDOW.getAnimation();
    private Animation window_InnerAnimation = UIAnimationInits.INNERWINDOW.getAnimation();

    public void setWindowActive(boolean active) {
        this.window_IsActive = active;
        somethingChanged();
    }

    public boolean isWindowActive() {
        return this.window_IsActive;
    }

    public boolean setWindowWidth(int width) {
        boolean ret = true;

        if (width < UISizeInits.WINDOW.getWidth()) {
            this.window_Width = UISizeInits.WINDOW.getWidth();
            ret = false;

        } else {
            this.window_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public int getWindow_Width() {
        return this.window_Width;
    }

    public boolean setWindowHeight(int height) {
        boolean ret = true;

        if (height < UISizeInits.WINDOW.getHeight()) {
            this.window_Height = UISizeInits.WINDOW.getHeight();
            ret = false;

        } else {
            this.window_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getWindow_Height() {
        return this.window_Height;
    }

    public void setWindowBorderthickness(int[] border) {
        if ((border == null) || (border.length != 4)) {
            this.window_Borderthickness = UIBorderthicknessInits.WINDOW.getBorderthickness();

        } else {
            this.window_Borderthickness = border;
        }
        somethingChanged();
    }

    public int[] getWindow_Borderthickness() {
        return this.window_Borderthickness;
    }

    public void setWindowPadding(int[] padding) {
        if ((padding != null) && (padding.length != 4)) {
            IllegalArgumentException iae = new IllegalArgumentException("The padding-array must have four elements!");
            Main.handleUnhandableProblem(iae);
        }

        this.window_Padding = padding;
        somethingChanged();
    }

    public int[] getWindow_Padding() {
        return this.window_Padding;
    }

    public Position getWindow_Position() {
        return this.window_Position;
    }

    public void setWindowPosition(Position position) {
        if (position.equals(Position.NONE)) {
            IllegalArgumentException iae = new IllegalArgumentException("Position none is not allowed!");
            Main.handleUnhandableProblem(iae);
        }

        this.window_Position = position;
        somethingChanged();
    }

    public Animation getWindow_Animation() {
        return this.window_Animation;
    }

    public void setWindowAnimation(Animation animation) {
        if (animation == null) {
            this.window_Animation = UIAnimationInits.WINDOW.getAnimation();

        } else {
            this.window_Animation = animation;
        }
        somethingChanged();
    }

    public void setWindowInnerAnimation(Animation animation) {
        if (animation == null) {
            this.window_InnerAnimation = UIAnimationInits.INNERWINDOW.getAnimation();

        } else {
            this.window_InnerAnimation = animation;
        }

        somethingChanged();
    }

    public Animation getWindow_InnerAnimation() {
        return this.window_InnerAnimation;
    }

    /*
     * ##################################################
     */
    // Buttons

    private int buttonAccess_Height = UISizeInits.ACC_BTN.getHeight();
    private int buttonAccess_Width = UISizeInits.ACC_BTN.getWidth();
    private Position buttonAccess_position = UIPositionInits.ACC_BTN.getPosition();

    public int getAccButtonHeight() {
        return this.buttonAccess_Height;
    }

    public boolean setAccButtonHeight(int height) {
        boolean ret = true;

        if (height <= 0) {
            this.buttonAccess_Height = UISizeInits.ACC_BTN.getHeight();
            ret = false;

        } else {
            this.buttonAccess_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getAccButtonWidth() {
        return this.buttonAccess_Width;
    }

    public boolean setAccButtonWidth(int width) {
        boolean ret = true;

        if (width <= 0) {
            this.buttonAccess_Width = UISizeInits.ACC_BTN.getWidth();
            ret = false;

        } else {
            this.buttonAccess_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public Position getAccButtonPosition() {
        return this.buttonAccess_position;
    }

    public void setAccButtonPosition(Position position) {
        if (position == null) {
            this.buttonAccess_position = UIPositionInits.ACC_BTN.getPosition();

        } else {
            if (!position.equals(Position.LEFT) && !position.equals(Position.RIGHT) && !position.equals(Position.NONE)) {
                IllegalArgumentException iae = new IllegalArgumentException("The position not valid! Valid positions: left, right, none.");
                Main.handleUnhandableProblem(iae);
            }

            this.buttonAccess_position = position;

            if (position.equals(Position.BOTTOMRIGHT) || position.equals(Position.NONE)) {
                this.wrapBtnAccInNewElement = false;
            }
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    private int buttonCom_MinHeight = UISizeInits.COM_BTN.getHeight();
    private int buttonCom_MinWidth = UISizeInits.COM_BTN.getWidth();
    private int[] buttonCom_Padding = UIPaddingInits.COM_BTN.getPadding();
    private int[] buttonCom_Margin = UIMarginInits.COM_BTN.getMargin();
    private boolean buttonCom_InvertArrowSide = false;
    private int[] buttonCom_Borderthickness = UIBorderthicknessInits.COM_BTN.getBorderthickness();
    private boolean buttonComSwitchVisible = true;
    private boolean buttonComLockVisible = true;
    private boolean buttonComLogOutVisible = true;
    private boolean buttonComPasswordVisible = true;
    private boolean buttonComTaskmanagerVisible = true;

    public void setCommandButtonVisibility(CommandButton btn, boolean visible) {
        if (btn == null) {
            throw new IllegalArgumentException();
        }

        switch (btn) {
            case SWITCH:
                this.buttonComSwitchVisible = visible;
                break;

            case LOCK:
                this.buttonComLockVisible = visible;
                break;

            case LOGOUT:
                this.buttonComLogOutVisible = visible;
                break;

            case PASSWORD:
                this.buttonComPasswordVisible = visible;
                break;

            case TASKMANAGER:
                this.buttonComTaskmanagerVisible = visible;
                break;

            default:
                break;
        }
    }

    public boolean getCommandButtonVisibility(CommandButton btn) {
        if (btn == null) {
            throw new IllegalArgumentException();
        }

        boolean ret = true;
        switch (btn) {
            case SWITCH:
                ret = this.buttonComSwitchVisible;
                break;

            case LOCK:
                ret = this.buttonComLockVisible;
                break;

            case LOGOUT:
                ret = this.buttonComLogOutVisible;
                break;

            case PASSWORD:
                ret = this.buttonComPasswordVisible;
                break;

            case TASKMANAGER:
                ret = this.buttonComTaskmanagerVisible;
                break;

            default:
                throw new IllegalArgumentException();
        }
        return ret;
    }

    public int getCommandButtonHeight() {
        return this.buttonCom_MinHeight;
    }

    public boolean setCommandButtonMinHeight(int minHeight) {
        boolean ret = true;
        if (minHeight <= 0) {
            this.buttonCom_MinHeight = UISizeInits.COM_BTN.getHeight();
            ret = false;

        } else {
            this.buttonCom_MinHeight = minHeight;
        }
        somethingChanged();
        return ret;
    }

    public int getCommandButtonWidth() {
        return this.buttonCom_MinWidth;
    }

    public boolean setCommandButtonMinWidth(int minWidth) {
        boolean ret = true;
        if (minWidth <= 0) {
            this.buttonCom_MinWidth = UISizeInits.COM_BTN.getWidth();
            ret = false;

        } else {
            this.buttonCom_MinWidth = minWidth;
        }
        somethingChanged();
        return ret;
    }

    public int[] getCommandButtonPadding() {
        return this.buttonCom_Padding;
    }

    public void setCommandButtonPadding(int[] padding) {
        if ((padding == null) || (padding.length != 4)) {
            this.buttonCom_Padding = UIPaddingInits.COM_BTN.getPadding();

        } else {
            this.buttonCom_Padding = padding;
        }
        somethingChanged();
    }

    public int[] getCommandButtonMargin() {
        return this.buttonCom_Margin;
    }

    public void setCommandButtonMargin(int[] margin) {
        if ((margin == null) || (margin.length != 4)) {
            this.buttonCom_Margin = UIMarginInits.COM_BTN.getMargin();

        } else {
            this.buttonCom_Margin = margin;
        }
        somethingChanged();
    }

    public void setCommandButtonArrowpositionOnRight(boolean rightSide) {
        this.buttonCom_InvertArrowSide = rightSide;
        somethingChanged();
    }

    public boolean getCommandButtonArrowpositionIsRight() {
        return this.buttonCom_InvertArrowSide;
    }

    public void setCommandButtonBorderthickness(int[] border) {
        if ((border == null) || (border.length != 4)) {
            this.buttonCom_Borderthickness = UIBorderthicknessInits.COM_BTN.getBorderthickness();

        } else {
            this.buttonCom_Borderthickness = border;
        }
        somethingChanged();
    }

    public int[] getCommandButtonBorderthickness() {
        return this.buttonCom_Borderthickness;
    }

    /*
     * ##################################################
     */
    private int buttonStd_Height = UISizeInits.STD_BTN.getHeight();
    private int buttonStd_Width = UISizeInits.STD_BTN.getWidth();
    private Position buttonStd_Position = UIPositionInits.STD_BTN.getPosition();
    private int[] buttonStd_Borderthickness = UIBorderthicknessInits.STD_BTN.getBorderthickness();
    private int[] buttonStd_Margin = UIMarginInits.STD_BTN.getMargin();
    private int[] buttonStd_Padding = UIPaddingInits.STD_BTN.getPadding();

    public int getStandardButtonHeight() {
        return this.buttonStd_Height;
    }

    public boolean setStandardButtonMinHeight(int height) {
        boolean ret = true;

        if (height <= 0) {
            this.buttonStd_Height = UISizeInits.STD_BTN.getHeight();
            ret = false;

        } else {
            this.buttonStd_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getStandardButtonWidth() {
        return this.buttonStd_Width;
    }

    public boolean setStandardButtonMinWidth(int width) {
        boolean ret = true;

        if (width <= 0) {
            this.buttonStd_Width = UISizeInits.STD_BTN.getWidth();
            ret = false;

        } else {
            this.buttonStd_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public Position getStandardButtonPosition() {
        return this.buttonStd_Position;
    }

    public void setStandardButtonPosition(Position position) {
        if (position == null) {
            this.buttonStd_Position = UIPositionInits.STD_BTN.getPosition();

        } else {
            if (!position.equals(Position.LEFT) && !position.equals(Position.RIGHT) && !position.equals(Position.CENTER)) {
                IllegalArgumentException iae = new IllegalArgumentException("The position not valid! Valid positions: left, right, center.");
                Main.handleUnhandableProblem(iae);
            }

            this.buttonStd_Position = position;
        }
        somethingChanged();
    }

    public void setStandardButtonBorderthickness(int[] border) {
        if ((border == null) || (border.length != 4)) {
            this.buttonStd_Borderthickness = UIBorderthicknessInits.STD_BTN.getBorderthickness();

        } else {
            this.buttonStd_Borderthickness = border;
        }
        somethingChanged();
    }

    public int[] getStandardButtonBorderthickness() {
        return this.buttonStd_Borderthickness;
    }

    public int[] getStandardButtonMargin() {
        return this.buttonStd_Margin;
    }

    public void setStandardButtonMargin(int[] margin) {
        if ((margin == null) || (margin.length != 4)) {
            this.buttonStd_Margin = UIMarginInits.STD_BTN.getMargin();

        } else {
            this.buttonStd_Margin = margin;
        }
        somethingChanged();
    }

    public int[] getStandardButtonPadding() {
        return this.buttonStd_Padding;
    }

    public void setStandardButtonPadding(int[] padding) {
        if ((padding == null) || (padding.length != 4)) {
            this.buttonStd_Padding = UIPaddingInits.STD_BTN.getPadding();

        } else {
            this.buttonStd_Padding = padding;
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    private int buttonPW_Height = UISizeInits.PW_BTN.getHeight();
    private int buttonPW_Width = UISizeInits.PW_BTN.getWidth();
    private int[] buttonPW_Borderthickness = UIBorderthicknessInits.PW_BTN.getBorderthickness();
    private int[] buttonPW_Margin = UIMarginInits.PW_BTN.getMargin();

    public int getPasswordButtonHeight() {
        return this.buttonPW_Height;
    }

    public boolean setPasswordButtonHeight(int height) {
        boolean ret = true;

        if (height < 0) {
            this.buttonPW_Height = UISizeInits.PW_BTN.getHeight();
            ret = false;

        } else {
            this.buttonPW_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getPasswordButtonWidth() {
        return this.buttonPW_Width;
    }

    public boolean setPasswordButtonWidth(int width) {
        boolean ret = true;

        if (width < 0) {
            this.buttonPW_Width = UISizeInits.PW_BTN.getWidth();
            ret = false;

        } else {
            this.buttonPW_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public void setPasswordButtonBorderthickness(int[] border) {
        if ((border == null) || (border.length != 4)) {
            this.buttonPW_Borderthickness = UIBorderthicknessInits.PW_BTN.getBorderthickness();

        } else {
            this.buttonPW_Borderthickness = border;
        }
        somethingChanged();
    }

    public int[] getPasswordButtonBorderthickness() {
        return this.buttonPW_Borderthickness;
    }

    public int[] getPasswordButtonMargin() {
        return this.buttonPW_Margin;
    }

    public void setPasswordButtonMargin(int[] margin) {
        if ((margin == null) || (margin.length != 4)) {
            this.buttonPW_Margin = UIMarginInits.PW_BTN.getMargin();

        } else {
            this.buttonPW_Margin = margin;
        }
        somethingChanged();
    }

    /*
     * ##################################################
     */
    private int buttonShutdown_Height = UISizeInits.SHD_BTN.getHeight();
    private int buttonShutdown_Width = UISizeInits.SHD_BTN.getWidth();
    private int[] buttonShutdown_Borderthickness = UIBorderthicknessInits.SHD_BTN.getBorderthickness();
    private int[] buttonShutdown_Margin = UIMarginInits.SHD_BTN.getMargin();
    private Position buttonShutdown_Position = UIPositionInits.SHD_BTN.getPosition();
    private String buttonShutdown_Content = null;

    public int getShutdownButtonHeight() {
        return this.buttonShutdown_Height;
    }

    public boolean setShutdownButtonHeight(int height) {
        boolean ret = true;

        if (height < 0) {
            this.buttonShutdown_Height = UISizeInits.SHD_BTN.getHeight();
            ret = false;

        } else {
            this.buttonShutdown_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getShutdownButtonWidth() {
        return this.buttonShutdown_Width;
    }

    public boolean setShutdownButtonWidth(int width) {
        boolean ret = true;

        if (width < 0) {
            this.buttonShutdown_Width = UISizeInits.SHD_BTN.getWidth();
            ret = false;

        } else {
            this.buttonShutdown_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public void setShutdownButtonBorderthickness(int[] border) {
        if ((border == null) || (border.length != 4)) {
            this.buttonShutdown_Borderthickness = UIBorderthicknessInits.SHD_BTN.getBorderthickness();

        } else {
            this.buttonShutdown_Borderthickness = border;
        }
        somethingChanged();
    }

    public int[] getShutdownButtonBorderthickness() {
        return this.buttonShutdown_Borderthickness;
    }

    public int[] getShutdownButtonMargin() {
        return this.buttonShutdown_Margin;
    }

    public void setShutdownButtonMargin(int[] margin) {
        if ((margin == null) || (margin.length != 4)) {
            this.buttonShutdown_Margin = UIMarginInits.SHD_BTN.getMargin();

        } else {
            this.buttonShutdown_Margin = margin;
        }
        somethingChanged();
    }

    public Position getShutdownButtonPosition() {
        return this.buttonShutdown_Position;
    }

    public void setShutdownButtonPosition(Position position) {
        if (position == null) {
            setShutdownframeBorderlayout(false);
            setWrapAccInNewElement(false);
            setOptionsbarHeight(UISizeInits.OPTIONSBAR.getHeight());
            this.buttonShutdown_Position = UIPositionInits.SHD_BTN.getPosition();

        } else {
            if (!position.equals(Position.LEFT) && !position.equals(Position.RIGHT) && !position.equals(Position.TOP)
                    && !position.equals(Position.BOTTOM)) {
                IllegalArgumentException iae = new IllegalArgumentException(
                        "The position not valid! Valid positions: left, right, top, bottom.");
                Main.handleUnhandableProblem(iae);
            }

            setShutdownframeBorderlayout(true);
            setWrapAccInNewElement(true);

            int fittingOptionsbarHeight = SkinConstants.OPTIONSBAR_HEIGHT_FIX;
            if (getOptionsbarHeight() <= fittingOptionsbarHeight) {
                setOptionsbarHeight(fittingOptionsbarHeight);
            }

            switch (position) {
                case LEFT:
                    setShutdownmenuButtonPosition(Position.RIGHT);
                    break;

                case RIGHT:
                    setShutdownmenuButtonPosition(Position.LEFT);
                    break;

                case TOP:
                    setShutdownmenuButtonPosition(Position.BOTTOM);
                    break;

                case BOTTOM:
                    setShutdownmenuButtonPosition(Position.TOP);
                    break;

                default:
                    throw new IllegalArgumentException();
            }

            this.buttonShutdown_Position = position;
        }
        somethingChanged();
    }

    public String getShutdownButtonContent() {
        return this.buttonShutdown_Content;
    }

    public void setShutdownButtonContent(String content) {
        this.buttonShutdown_Content = content;

        somethingChanged();
    }

    /*
     * ##################################################
     */
    private int buttonShutdownmenu_Height = UISizeInits.SHDMENU_BTN.getHeight();
    private int buttonShutdownmenu_Width = UISizeInits.SHDMENU_BTN.getWidth();
    private int[] buttonShutdownmenu_Borderthickness = UIBorderthicknessInits.SHDMENU_BTN.getBorderthickness();
    private int[] buttonShutdownmenu_Margin = UIMarginInits.SHDMENU_BTN.getMargin();
    private Position buttonShutdownmenu_Position = UIPositionInits.SHDMENU_BTN.getPosition();
    private String buttonShutdownmenu_Content = null;

    public int getShutdownmenuButtonHeight() {
        return this.buttonShutdownmenu_Height;
    }

    public boolean setShutdownmenuButtonHeight(int height) {
        boolean ret = true;

        if (height < 0) {
            this.buttonShutdownmenu_Height = UISizeInits.SHDMENU_BTN.getHeight();
            ret = false;

        } else {
            this.buttonShutdownmenu_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getShutdownmenuButtonWidth() {
        return this.buttonShutdownmenu_Width;
    }

    public boolean setShutdownmenuButtonWidth(int width) {
        boolean ret = true;

        if (width < 0) {
            this.buttonShutdownmenu_Width = UISizeInits.SHDMENU_BTN.getWidth();
            ret = false;

        } else {
            this.buttonShutdownmenu_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public void setShutdownmenuButtonBorderthickness(int[] border) {
        if ((border == null) || (border.length != 4)) {
            this.buttonShutdownmenu_Borderthickness = UIBorderthicknessInits.SHDMENU_BTN.getBorderthickness();

        } else {
            this.buttonShutdownmenu_Borderthickness = border;
        }
        somethingChanged();
    }

    public int[] getShutdownmenuButtonBorderthickness() {
        return this.buttonShutdownmenu_Borderthickness;
    }

    public int[] getShutdownmenuButtonMargin() {
        return this.buttonShutdownmenu_Margin;
    }

    public void setShutdownmenuButtonMargin(int[] margin) {
        if ((margin == null) || (margin.length != 4)) {
            this.buttonShutdownmenu_Margin = UIMarginInits.SHDMENU_BTN.getMargin();

        } else {
            this.buttonShutdownmenu_Margin = margin;
        }
        somethingChanged();
    }

    public Position getShutdownmenuButtonPosition() {
        return this.buttonShutdownmenu_Position;
    }

    public void setShutdownmenuButtonPosition(Position position) {
        if (position == null) {
            this.buttonShutdownmenu_Position = UIPositionInits.SHDMENU_BTN.getPosition();
            setShutdownframeBorderlayout(false);

        } else {
            if (!position.equals(Position.LEFT) && !position.equals(Position.RIGHT) && !position.equals(Position.TOP)
                    && !position.equals(Position.BOTTOM)) {
                IllegalArgumentException iae = new IllegalArgumentException(
                        "The position not valid! Valid positions: left, right, top, bottom.");
                Main.handleUnhandableProblem(iae);
            }

            setShutdownframeBorderlayout(true);

            this.buttonShutdownmenu_Position = position;
        }
        somethingChanged();
    }

    public String getShutdownmenuButtonContent() {
        return this.buttonShutdownmenu_Content;
    }

    public void setShutdownmenuButtonContent(String content) {
        this.buttonShutdownmenu_Content = content;

        somethingChanged();
    }

    // #####

    private boolean shdFrame_Borderlayout = false;
    private Position shdFrame_Position = UIPositionInits.SHDFRAME.getPosition();

    public void setShutdownframeBorderlayout(boolean active) {
        this.shdFrame_Borderlayout = active;
        somethingChanged();
    }

    public boolean getShutdownframeBorderlayout() {
        return this.shdFrame_Borderlayout;
    }

    public void setShutdownframePosition(Position position) {
        if (position == null) {
            this.shdFrame_Position = UIPositionInits.SHDFRAME.getPosition();

        } else {
            if (!position.equals(Position.LEFT) && !position.equals(Position.RIGHT) && !position.equals(Position.TOP)
                    && !position.equals(Position.BOTTOM)) {
                IllegalArgumentException iae = new IllegalArgumentException(
                        "The position not valid! Valid positions: left, right, top, bottom.");
                Main.handleUnhandableProblem(iae);
            }

            this.shdFrame_Position = position;
        }
        somethingChanged();
    }

    public Position getShutdownframePosition() {
        return this.shdFrame_Position;
    }

    /*
     * ##################################################
     */
    // Optionsbar
    private int optionsbar_Height = UISizeInits.OPTIONSBAR.getHeight();
    private boolean wrapBtnAccInNewElement = false;

    public boolean setOptionsbarHeight(int height) {
        boolean ret = true;

        if (height < 0) {
            this.optionsbar_Height = UISizeInits.OPTIONSBAR.getHeight();
            ret = false;

        } else {
            if (height > UISizeInits.OPTIONSBAR.getHeight()) {
                this.wrapBtnAccInNewElement = true;

            } else {
                this.wrapBtnAccInNewElement = false;
            }

            this.optionsbar_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getOptionsbarHeight() {
        return this.optionsbar_Height;
    }

    public void setWrapAccInNewElement(boolean wrapIt) {
        this.wrapBtnAccInNewElement = wrapIt;
        somethingChanged();
    }

    public boolean getWrapAccInNewElement() {
        return this.wrapBtnAccInNewElement;
    }

    /*
     * ##################################################
     */
    // Passwordfield

    private int pwfield_Height = UISizeInits.PWFIELD.getHeight();
    private int pwfield_Width = UISizeInits.PWFIELD.getWidth();
    private int[] pwfield_Borderthickness = UIBorderthicknessInits.PWFIELD.getBorderthickness();
    private int[] pwfield_Margin = UIMarginInits.PWFIELD.getMargin();

    public boolean setPWfieldHeight(int height) {
        boolean ret = true;

        if (height <= 10) {
            this.pwfield_Height = UISizeInits.PWFIELD.getHeight();
            ret = false;

        } else {
            this.pwfield_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getPWfieldHeight() {
        return this.pwfield_Height;
    }

    public boolean setPWfieldWidth(int width) {
        boolean ret = true;

        if (width <= 10) {
            this.pwfield_Width = UISizeInits.PWFIELD.getWidth();
            ret = false;

        } else {
            this.pwfield_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public int getPWfieldWidth() {
        return this.pwfield_Width;
    }

    public void setPWfieldBorderthickness(int[] border) {
        if ((border == null) || (border.length != 4)) {
            this.pwfield_Borderthickness = UIBorderthicknessInits.PWFIELD.getBorderthickness();

        } else {
            this.pwfield_Borderthickness = border;
        }
        somethingChanged();
    }

    public int[] getPWfieldBorderthickness() {
        return this.pwfield_Borderthickness;
    }

    public void setPWfieldMargin(int[] margin) {
        if ((margin == null) || (margin.length != 4)) {
            this.pwfield_Margin = UIMarginInits.PWFIELD.getMargin();

        } else {
            this.pwfield_Margin = margin;
        }
        somethingChanged();
    }

    public int[] getPWfieldMargin() {
        return this.pwfield_Margin;
    }

    /*
     * ##################################################
     */
    // Passwordarea
    private int passwordfieldUpshift = UIShiftInits.PWAREA_UP.getShift();
    private int passwordfieldDownshift = UIShiftInits.PWAREA_DOWN.getShift();
    private int passwordfieldRightshift = UIShiftInits.PWAREA_RIGHT.getShift();

    public void setPasswordfieldUpshift(int shift) {
        if (shift < 0) {
            this.passwordfieldUpshift = UIShiftInits.PWAREA_UP.getShift();
        } else {
            this.passwordfieldUpshift = shift;
        }
    }

    public int getPasswordfieldUpshift() {
        return this.passwordfieldUpshift;
    }

    public void setPasswordfieldDownshift(int shift) {
        if (shift < 0) {
            this.passwordfieldDownshift = UIShiftInits.PWAREA_DOWN.getShift();
        } else {
            this.passwordfieldDownshift = shift;
        }
    }

    public int getPasswordfieldDownshift() {
        return this.passwordfieldDownshift;
    }

    public void setPasswordfieldRightshift(int shift) {
        if (shift < 0) {
            this.passwordfieldRightshift = UIShiftInits.PWAREA_RIGHT.getShift();
        } else {
            this.passwordfieldRightshift = shift;
        }
    }

    public int getPasswordfieldRightshift() {
        return this.passwordfieldRightshift;
    }

    /*
     * ##################################################
     */
    private boolean combineShdAcc = false;
    private Position combineShdAcc_Position = UIPositionInits.COMBINEDACCSHD.getPosition();

    public void setCombinedShdAcc(boolean active) {
        this.combineShdAcc = active;
        somethingChanged();
    }

    public boolean getCombinedShdAcc() {
        return this.combineShdAcc;
    }

    public void setCombinedShdAcc_Position(Position position) {
        if (position == null) {
            this.combineShdAcc_Position = UIPositionInits.COMBINEDACCSHD.getPosition();

        } else {
            if (!position.equals(Position.LEFT) && !position.equals(Position.RIGHT) && !position.equals(Position.NONE)
                    && !position.equals(Position.TOP) && !position.equals(Position.BOTTOM)) {
                IllegalArgumentException iae = new IllegalArgumentException(
                        "The position not valid! Valid positions: left, right, top, bottom, none.");
                Main.handleUnhandableProblem(iae);
            }

            this.combineShdAcc_Position = position;
        }
        somethingChanged();
    }

    public Position getCombinedShdAccPosition() {
        return this.combineShdAcc_Position;
    }

    /*
     * ##################################################
     */
    // Locale Btn

    private boolean localeBtn_Visible = true;
    private Position localeBtn_Position = UIPositionInits.LOCALE_BTN.getPosition();
    private int[] localeBtn_Padding = UIPaddingInits.LOCALE_BTN.getPadding();

    public void setLocaleButtonVisibility(boolean visible) {
        this.localeBtn_Visible = visible;
        somethingChanged();
    }

    public boolean getLocaleButtonVisible() {
        return this.localeBtn_Visible;
    }

    public void setLocaleButtonPosition(Position position) {
        if (position == null) {
            this.localeBtn_Position = UIPositionInits.LOCALE_BTN.getPosition();

        } else {
            if (position.equals(Position.NONE)) {
                IllegalArgumentException iae = new IllegalArgumentException("Position none is not allowed!");
                Main.handleUnhandableProblem(iae);
            }

            this.localeBtn_Position = position;
        }
        somethingChanged();
    }

    public Position getLocaleButtonPosition() {
        return this.localeBtn_Position;
    }

    public void setLocaleButtonPadding(int[] padding) {
        if ((padding == null) || (padding.length != 4)) {
            this.localeBtn_Padding = UIPaddingInits.LOCALE_BTN.getPadding();

        } else {
            this.localeBtn_Padding = padding;
        }
        somethingChanged();
    }

    public int[] getLocaleButtonPadding() {
        return this.localeBtn_Padding;
    }

    /*
     * ##################################################
     */
    // SecurityOptions

    private Position secMenuPosition = UIPositionInits.SECURITYMENU.getPosition();
    private int[] secMenuPadding = null;

    public void setSecurityMenuPosition(Position position) {
        if (position == null) {
            this.secMenuPosition = UIPositionInits.SECURITYMENU.getPosition();

        } else {
            if (position.equals(Position.NONE)) {
                IllegalArgumentException iae = new IllegalArgumentException("Position none is not allowed!");
                Main.handleUnhandableProblem(iae);
            }

            this.secMenuPosition = position;
        }
        somethingChanged();
    }

    public Position getSecurityMenu_Position() {
        return this.secMenuPosition;
    }

    public void setSecurityMenuPadding(int[] padding) {
        if ((padding != null) && (padding.length != 4)) {
            IllegalArgumentException iae = new IllegalArgumentException("The padding-array must not be null and must have four elements!");
            Main.handleUnhandableProblem(iae);
        }

        this.secMenuPadding = padding;
        somethingChanged();
    }

    public int[] getSecurityMenu_Padding() {
        return this.secMenuPadding;
    }

    /*
     * ################################################## ##################################################
     */
    // Userlist

    private int userlist_Height = UISizeInits.USERLIST.getHeight();
    private boolean userlist_Vertical = false;
    private Position userlist_Position = UIPositionInits.USERLIST.getPosition();
    private int[] userlist_Padding = UIPaddingInits.USERLIST.getPadding();

    public boolean setUserlistHeight(int height) {
        boolean ret = true;

        if (height <= 0) {
            this.userlist_Height = UISizeInits.USERLIST.getHeight();
            ret = false;

        } else {
            this.userlist_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getUserlistHeight() {
        return this.userlist_Height;
    }

    public void setUserlistVertical(boolean vertical) {
        this.userlist_Vertical = vertical;
        setUserlistPosition(Position.BOTTOM);
        somethingChanged();
    }

    public boolean getUserlistVertical() {
        return this.userlist_Vertical;
    }

    public void setUserlistPosition(Position position) {
        if (position == null) {
            this.userlist_Position = UIPositionInits.USERLIST.getPosition();

        } else {
            if (position.equals(Position.NONE)) {
                IllegalArgumentException iae = new IllegalArgumentException("Position none is not allowed!");
                Main.handleUnhandableProblem(iae);
            }

            this.userlist_Position = position;
        }
        somethingChanged();
    }

    public Position getUserlistPosition() {
        return this.userlist_Position;
    }

    public void setUserlistPadding(int[] padding) {
        if ((padding == null) || (padding.length != 4)) {
            this.userlist_Padding = UIPaddingInits.USERLIST.getPadding();

        } else {
            this.userlist_Padding = padding;
        }
        somethingChanged();
    }

    public int[] getUserlistPadding() {
        return this.userlist_Padding;
    }

    /*
     * ####
     */
    // Userlist image changes

    private int userlistImg_Height = UISizeInits.USERLIST_IMAGE.getHeight();
    private int userlistImg_Width = UISizeInits.USERLIST_IMAGE.getWidth();
    private int userlistImgFrame_Height = UISizeInits.USERLIST_IMAGEFRAME.getHeight();
    private int userlistImgFrame_Width = UISizeInits.USERLIST_IMAGEFRAME.getWidth();
    private int userlistImgOverlay_Height = UISizeInits.USERLIST_IMAGEOVERLAY.getHeight();
    private int userlistImgOverlay_Width = UISizeInits.USERLIST_IMAGEOVERLAY.getWidth();
    private int[] userlistImg_Padding = UIPaddingInits.USERLIST_IMAGE.getPadding();

    public boolean setUserlistImageHeight(int height) {
        boolean ret = true;

        if (height <= 0) {
            this.userlistImg_Height = UISizeInits.USERLIST_IMAGE.getHeight();
            ret = false;

        } else {
            this.userlistImg_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getUserlistImageHeight() {
        return this.userlistImg_Height;
    }

    //
    public boolean setUserlistImageWidth(int width) {
        boolean ret = true;

        if (width <= 0) {
            this.userlistImg_Width = UISizeInits.USERLIST_IMAGE.getWidth();
            ret = false;

        } else {
            this.userlistImg_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public int getUserlistImageWidth() {
        return this.userlistImg_Width;
    }

    //
    public boolean setUserlistImageFrameHeight(int height) {
        boolean ret = true;

        if (height <= 0) {
            this.userlistImgFrame_Height = UISizeInits.USERLIST_IMAGEFRAME.getHeight();
            ret = false;

        } else {
            this.userlistImgFrame_Height = height;
        }

        setUserlistImageOverlay_Height(this.userlistImgFrame_Height);
        somethingChanged();

        return ret;
    }

    public int getUserlistImageFrameHeight() {
        return this.userlistImgFrame_Height;
    }

    //
    public boolean setUserlistImageFrameWidth(int width) {
        boolean ret = true;

        if (width <= 0) {
            this.userlistImgFrame_Width = UISizeInits.USERLIST_IMAGEFRAME.getWidth();
            ret = false;

        } else {
            this.userlistImgFrame_Width = width;
        }

        setUserlistImageOverlay_Width(this.userlistImgFrame_Width);
        somethingChanged();

        return ret;
    }

    public int getUserlistImageFrameWidth() {
        return this.userlistImgFrame_Width;
    }

    //
    public void setUserlistImageOverlay_Height(int height) {
        if (height <= 0) {
            this.userlistImgOverlay_Height = UISizeInits.USERLIST_IMAGEOVERLAY.getHeight();

        } else {
            this.userlistImgOverlay_Height = height;
        }
        somethingChanged();
    }

    public int getUserlistImageOverlay_Height() {
        return this.userlistImgOverlay_Height;
    }

    //
    public void setUserlistImageOverlay_Width(int width) {
        if (width <= 0) {
            this.userlistImgOverlay_Width = UISizeInits.USERLIST_IMAGEOVERLAY.getWidth();

        } else {
            this.userlistImgOverlay_Width = width;
        }
        somethingChanged();
    }

    public int getUserlistImageOverlay_Width() {
        return this.userlistImgOverlay_Width;
    }

    //
    public void setUserlistImagePadding(int[] padding) {
        if ((padding == null) || (padding.length != 4)) {
            this.userlistImg_Padding = UIPaddingInits.USERLIST_IMAGE.getPadding();

        } else {
            this.userlistImg_Padding = padding;
        }
        somethingChanged();
    }

    public int[] getUserlistImagePadding() {
        return this.userlistImg_Padding;
    }

    /*
     * ################################################## ##################################################
     */
    // Usertile

    private boolean usertileLayoutHorizontal = false;
    private Position usertilePosition = UIPositionInits.USERTILE.getPosition();
    private boolean pwAreaPositionOnRight = false;
    private boolean statusPositionOnRight = false;
    private Position usertileImagePosition = UIPositionInits.USERTILE_IMAGE.getPosition();

    public void setUsertileLayout(boolean horizontal) {
        this.usertileLayoutHorizontal = horizontal;
        somethingChanged();
    }

    public boolean getUsertileLayoutIsHorizontal() {
        return this.usertileLayoutHorizontal;
    }

    public void setUsertilePosition(Position position) {
        if (position == null) {
            this.usertilePosition = UIPositionInits.USERTILE.getPosition();

        } else {
            if (position.equals(Position.NONE)) {
                IllegalArgumentException iae = new IllegalArgumentException("Position none is not allowed!");
                Main.handleUnhandableProblem(iae);
            }

            this.usertilePosition = position;
        }
        somethingChanged();
    }

    public Position getUsertilePosition() {
        return this.usertilePosition;
    }

    public void setPWAreaPositionOnRightOfTexts(boolean rightSide) {
        this.pwAreaPositionOnRight = rightSide;
        somethingChanged();
    }

    public boolean getPWAreaPositionOnRightOfTexts() {
        return this.pwAreaPositionOnRight;
    }

    public void setStatusPositionOnRight(boolean rightSide) {
        this.statusPositionOnRight = rightSide;
        somethingChanged();
    }

    public boolean getStatusOnRightSide() {
        return this.statusPositionOnRight;
    }

    public void setUsertileImagePosition(Position position) {
        if (position == null) {
            this.usertileImagePosition = UIPositionInits.USERTILE_IMAGE.getPosition();

        } else {
            if (!position.equals(Position.LEFT) && !position.equals(Position.CENTER) && !position.equals(Position.RIGHT)) {
                IllegalArgumentException iae = new IllegalArgumentException("Position not allowed! Only Left, Center, Right!");
                Main.handleUnhandableProblem(iae);
            }

            this.usertileImagePosition = position;
        }
        somethingChanged();
    }

    public Position getUsertileImagePosition() {
        return this.usertileImagePosition;
    }

    /*
     * ####
     */
    // Usertile image changes

    private int usertileImage_Height = UISizeInits.USERTILE_IMAGE.getHeight();
    private int usertileImage_Width = UISizeInits.USERTILE_IMAGE.getWidth();
    private int usertileImageFrame_Height = UISizeInits.USERTILE_IMAGEFRAME.getHeight();
    private int usertileImageFrame_Width = UISizeInits.USERTILE_IMAGEFRAME.getWidth();
    private int usertileImageOverlay_Height = UISizeInits.USERTILE_IMAGEOVERLAY.getHeight();
    private int usertileImageOverlay_Width = UISizeInits.USERTILE_IMAGEOVERLAY.getWidth();
    private int[] usertileImage_Padding = UIPaddingInits.USERTILE_IMAGE.getPadding();

    public boolean setUsertileImageHeight(int height) {
        boolean ret = true;

        if (height <= 0) {
            this.usertileImage_Height = UISizeInits.USERTILE_IMAGE.getHeight();
            ret = false;

        } else {
            this.usertileImage_Height = height;
        }
        somethingChanged();

        return ret;
    }

    public int getUsertileImageHeight() {
        return this.usertileImage_Height;
    }

    //
    public boolean setUsertileImageWidth(int width) {
        boolean ret = true;

        if (width <= 0) {
            this.usertileImage_Width = UISizeInits.USERTILE_IMAGE.getWidth();
            ret = false;

        } else {
            this.usertileImage_Width = width;
        }
        somethingChanged();

        return ret;
    }

    public int getUsertileImageWidth() {
        return this.usertileImage_Width;
    }

    //
    public boolean setUsertileImageFrameHeight(int height) {
        boolean ret = true;

        if (height <= 0) {
            this.usertileImageFrame_Height = UISizeInits.USERTILE_IMAGEFRAME.getHeight();
            ret = false;

        } else {
            this.usertileImageFrame_Height = height;
        }

        setUsertileImageOverlay_Height(this.usertileImageFrame_Height);
        somethingChanged();

        return ret;
    }

    public int getUsertileImageFrameHeight() {
        return this.usertileImageFrame_Height;
    }

    //
    public boolean setUsertileImageFrameWidth(int width) {
        boolean ret = true;

        if (width <= 0) {
            this.usertileImageFrame_Width = UISizeInits.USERTILE_IMAGEFRAME.getWidth();
            ret = false;

        } else {
            this.usertileImageFrame_Width = width;
        }

        setUsertileImageOverlay_Width(this.usertileImageFrame_Width);
        somethingChanged();

        return ret;
    }

    public int getUsertileImageFrameWidth() {
        return this.usertileImageFrame_Width;
    }

    //
    public void setUsertileImageOverlay_Height(int height) {
        if (height <= 0) {
            this.usertileImageOverlay_Height = UISizeInits.USERTILE_IMAGEOVERLAY.getHeight();

        } else {
            this.usertileImageOverlay_Height = height;
        }
        somethingChanged();
    }

    public int getUsertileImageOverlay_Height() {
        return this.usertileImageOverlay_Height;
    }

    //
    public void setUsertileImageOverlay_Width(int width) {
        if (width <= 0) {
            this.usertileImageOverlay_Width = UISizeInits.USERTILE_IMAGEOVERLAY.getWidth();

        } else {
            this.usertileImageOverlay_Width = width;
        }
        somethingChanged();
    }

    public int getUsertileImageOverlay_Width() {
        return this.usertileImageOverlay_Width;
    }

    //
    public void setUsertileImagePadding(int[] padding) {
        if ((padding == null) || (padding.length != 4)) {
            this.usertileImage_Padding = UIPaddingInits.USERTILE_IMAGE.getPadding();

        } else {
            this.usertileImage_Padding = padding;
        }
        somethingChanged();
    }

    public int[] getUsertileImagePadding() {
        return this.usertileImage_Padding;
    }

    /*
     * ##################################################
     */
    // Shadowintensity

    private int shadowIntensity = SkinConstants.DEFVALUE_FONTSHADOW;

    public void setShadowIntensity(int intensity) {
        if (intensity < 0) {
            this.shadowIntensity = SkinConstants.DEFVALUE_FONTSHADOW;
        } else {
            this.shadowIntensity = intensity;
        }

        somethingChanged();
    }

    public int getShadowIntensity() {
        return this.shadowIntensity;
    }

    /*
     * ##################################################
     */
    // Loading status

    private int loadingStatusWidth;
    private int[] loadingStatusBorderthickness = null;

    public boolean setLoadingStatusWidth(int width) {
        boolean ret = true;

        if (width <= 0) {
            this.loadingStatusWidth = 0;
            ret = false;

        } else {
            this.loadingStatusWidth = width;
        }

        somethingChanged();
        return ret;
    }

    public int getLoadingStatusWidth() {
        return this.loadingStatusWidth;
    }

    public void setLoadingStatusBorderthickness(int[] border) {
        if ((border == null) || (border.length != 4)) {
            this.loadingStatusBorderthickness = null;

        } else {
            this.loadingStatusBorderthickness = border;
        }
        somethingChanged();
    }

    public int[] getLoadingStatusBorderthickness() {
        return this.loadingStatusBorderthickness;
    }

    // ++++++++++++++++#################++++++++++++++++
    /*
     * Fonts
     */
    /*
     * #################################################
     */
    // Shutdown Font (if text)
    private String shutdownFont = UIFontInits.SHDBTN.getFont();
    private int shutdownFontSize = UIFontInits.SHDBTN.getSize();
    private Fonttype shutdownFontType = UIFontInits.SHDBTN.getType();
    private Fontstyle shutdownFontStyle = UIFontInits.SHDBTN.getStyle();
    private int[] shutdownFontColor = UIFontInits.SHDBTN.getColor();

    public void setShutdownFont(String font) {
        if ((font == null) || font.equals("")) {
            this.shutdownFont = UIFontInits.SHDBTN.getFont();

        } else {
            this.shutdownFont = font;
        }
        somethingChanged();
    }

    public String getShutdownFont() {
        return this.shutdownFont;
    }

    public void setShutdownFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.shutdownFontSize = UIFontInits.SHDBTN.getSize();

        } else {
            this.shutdownFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getShutdownFontsize() {
        return this.shutdownFontSize;
    }

    public void setShutdownFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.shutdownFontType = UIFontInits.SHDBTN.getType();

        } else {
            this.shutdownFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getShutdownFonttype() {
        return this.shutdownFontType;
    }

    public void setShutdownFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.shutdownFontStyle = UIFontInits.SHDBTN.getStyle();

        } else {
            this.shutdownFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getShutdownFontstyle() {
        return this.shutdownFontStyle;
    }

    public void setShutdownFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.shutdownFontColor = UIFontInits.SHDBTN.getColor();

        } else {
            this.shutdownFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getShutdownFontcolor() {
        return this.shutdownFontColor;
    }

    /*
     * #################################################
     */
    // Shutdownmenu Font (if text)
    private String shutdownMenuFont = UIFontInits.SHDMENU.getFont();
    private int shutdownMenuFontSize = UIFontInits.SHDMENU.getSize();
    private Fonttype shutdownMenuFontType = UIFontInits.SHDMENU.getType();
    private Fontstyle shutdownMenuFontStyle = UIFontInits.SHDMENU.getStyle();
    private int[] shutdownMenuFontColor = UIFontInits.SHDMENU.getColor();

    public void setShutdownMenuFont(String font) {
        if ((font == null) || font.equals("")) {
            this.shutdownMenuFont = UIFontInits.SHDMENU.getFont();

        } else {
            this.shutdownMenuFont = font;
        }
        somethingChanged();
    }

    public String getShutdownMenuFont() {
        return this.shutdownMenuFont;
    }

    public void setShutdownMenuFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.shutdownMenuFontSize = UIFontInits.SHDMENU.getSize();

        } else {
            this.shutdownMenuFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getShutdownMenuFontsize() {
        return this.shutdownMenuFontSize;
    }

    public void setShutdownMenuFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.shutdownMenuFontType = UIFontInits.SHDMENU.getType();

        } else {
            this.shutdownMenuFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getShutdownMenuFonttype() {
        return this.shutdownMenuFontType;
    }

    public void setShutdownMenuFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.shutdownMenuFontStyle = UIFontInits.SHDMENU.getStyle();

        } else {
            this.shutdownMenuFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getShutdownMenuFontstyle() {
        return this.shutdownMenuFontStyle;
    }

    public void setShutdownMenuFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.shutdownMenuFontColor = UIFontInits.SHDMENU.getColor();

        } else {
            this.shutdownMenuFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getShutdownMenuFontcolor() {
        return this.shutdownMenuFontColor;
    }

    /*
     * #################################################
     */
    // Passwordfield Font
    private String pWFieldFont = UIFontInits.PWFIELD.getFont();
    private int pWFieldFontSize = UIFontInits.PWFIELD.getSize();
    private Fonttype pWFieldFontType = UIFontInits.PWFIELD.getType();
    private Fontstyle pWFieldFontStyle = UIFontInits.PWFIELD.getStyle();
    private int[] pWFieldFontColor = UIFontInits.PWFIELD.getColor();

    public void setPWFieldFont(String font) {
        if ((font == null) || font.equals("")) {
            this.pWFieldFont = UIFontInits.PWFIELD.getFont();

        } else {
            this.pWFieldFont = font;
        }
        somethingChanged();
    }

    public String getPWFieldFont() {
        return this.pWFieldFont;
    }

    public void setPWFieldFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.pWFieldFontSize = UIFontInits.PWFIELD.getSize();

        } else {
            this.pWFieldFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getPWFieldFontsize() {
        return this.pWFieldFontSize;
    }

    public void setPWFieldFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.pWFieldFontType = UIFontInits.PWFIELD.getType();

        } else {
            this.pWFieldFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getPWFieldFonttype() {
        return this.pWFieldFontType;
    }

    public void setPWFieldFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.pWFieldFontStyle = UIFontInits.PWFIELD.getStyle();

        } else {
            this.pWFieldFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getPWFieldFontstyle() {
        return this.pWFieldFontStyle;
    }

    public void setPWFieldFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.pWFieldFontColor = UIFontInits.PWFIELD.getColor();

        } else {
            this.pWFieldFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getPWFieldFontcolor() {
        return this.pWFieldFontColor;
    }

    /*
     * #################################################
     */
    // Reset Password Font
    private String pwResetFont = UIFontInits.PWRESET.getFont();
    private int pwResetFontSize = UIFontInits.PWRESET.getSize();
    private Fonttype pwResetFontType = UIFontInits.PWRESET.getType();
    private Fontstyle pwResetFontStyle = UIFontInits.PWRESET.getStyle();
    private int[] pwResetFontColor = UIFontInits.PWRESET.getColor();

    public void setPWResetFont(String font) {
        if ((font == null) || font.equals("")) {
            this.pwResetFont = UIFontInits.PWRESET.getFont();

        } else {
            this.pwResetFont = font;
        }
        somethingChanged();
    }

    public String getPWResetFont() {
        return this.pwResetFont;
    }

    public void setPWResetFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.pwResetFontSize = UIFontInits.PWRESET.getSize();

        } else {
            this.pwResetFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getPWResetFontsize() {
        return this.pwResetFontSize;
    }

    public void setPWResetFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.pwResetFontType = UIFontInits.PWRESET.getType();

        } else {
            this.pwResetFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getPWResetFonttype() {
        return this.pwResetFontType;
    }

    public void setPWResetFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.pwResetFontStyle = UIFontInits.PWRESET.getStyle();

        } else {
            this.pwResetFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getPWResetFontstyle() {
        return this.pwResetFontStyle;
    }

    public void setPWResetFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.pwResetFontColor = UIFontInits.PWRESET.getColor();

        } else {
            this.pwResetFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getPWResetFontcolor() {
        return this.pwResetFontColor;
    }

    /*
     * #################################################
     */
    // Loadingstatus Font (Please wait)
    private String loadingFont = UIFontInits.LOADINGSTATUS.getFont();
    private int loadingFontSize = UIFontInits.LOADINGSTATUS.getSize();
    private Fonttype loadingFontType = UIFontInits.LOADINGSTATUS.getType();
    private Fontstyle loadingFontStyle = UIFontInits.LOADINGSTATUS.getStyle();
    private int[] loadingFontColor = UIFontInits.LOADINGSTATUS.getColor();
    private Animation loadingAnimation = UIAnimationInits.LOADINGSTATUS.getAnimation();

    public void setLoadingStatusFont(String font) {
        if ((font == null) || font.equals("")) {
            this.loadingFont = UIFontInits.LOADINGSTATUS.getFont();

        } else {
            this.loadingFont = font;
        }
        somethingChanged();
    }

    public String getLoadingStatusFont() {
        return this.loadingFont;
    }

    public void setLoadingStatusFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.loadingFontSize = UIFontInits.LOADINGSTATUS.getSize();

        } else {
            this.loadingFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getLoadingStatusFontsize() {
        return this.loadingFontSize;
    }

    public void setLoadingStatusFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.loadingFontType = UIFontInits.LOADINGSTATUS.getType();

        } else {
            this.loadingFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getLoadingStatusFonttype() {
        return this.loadingFontType;
    }

    public void setLoadingStatusFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.loadingFontStyle = UIFontInits.LOADINGSTATUS.getStyle();

        } else {
            this.loadingFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getLoadingStatusFontstyle() {
        return this.loadingFontStyle;
    }

    public void setLoadingStatusFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.loadingFontColor = UIFontInits.LOADINGSTATUS.getColor();

        } else {
            this.loadingFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getLoadingStatusFontcolor() {
        return this.loadingFontColor;
    }

    public void setLoadingStatusAnimation(Animation animation) {
        if (animation == null) {
            this.loadingAnimation = UIAnimationInits.LOADINGSTATUS.getAnimation();

        } else {
            this.loadingAnimation = animation;
        }
        somethingChanged();
    }

    public Animation getLoadingStatusAnimation() {
        return this.loadingAnimation;
    }

    /*
     * ##################################################
     */
    // Short Message Font
    private String shortMessageFont = UIFontInits.SHORTMESSAGE.getFont();
    private int shortMessageFontSize = UIFontInits.SHORTMESSAGE.getSize();
    private Fonttype shortMessageFontType = UIFontInits.SHORTMESSAGE.getType();
    private Fontstyle shortMessageFontStyle = UIFontInits.SHORTMESSAGE.getStyle();
    private int[] shortMessageFontColor = UIFontInits.SHORTMESSAGE.getColor();

    public void setShortMessageFont(String font) {
        if ((font == null) || font.equals("")) {
            this.shortMessageFont = UIFontInits.SHORTMESSAGE.getFont();

        } else {
            this.shortMessageFont = font;
        }
        somethingChanged();
    }

    public String getShortMessageFont() {
        return this.shortMessageFont;
    }

    public void setShortMessageFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.shortMessageFontSize = UIFontInits.SHORTMESSAGE.getSize();

        } else {
            this.shortMessageFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getShortMessageFontsize() {
        return this.shortMessageFontSize;
    }

    public void setShortMessageFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.shortMessageFontType = UIFontInits.SHORTMESSAGE.getType();

        } else {
            this.shortMessageFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getShortMessageFonttype() {
        return this.shortMessageFontType;
    }

    public void setShortMessageFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.shortMessageFontStyle = UIFontInits.SHORTMESSAGE.getStyle();

        } else {
            this.shortMessageFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getShortMessageFontstyle() {
        return this.shortMessageFontStyle;
    }

    public void setShortMessageFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.shortMessageFontColor = UIFontInits.SHORTMESSAGE.getColor();

        } else {
            this.shortMessageFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getShortMessageFontcolor() {
        return this.shortMessageFontColor;
    }

    /*
     * ##################################################
     */
    // Command Button Font
    private String buttonComFont = UIFontInits.COMBUTTON.getFont();
    private int buttonComFontSize = UIFontInits.COMBUTTON.getSize();
    private Fonttype buttonComFontType = UIFontInits.COMBUTTON.getType();
    private Fontstyle buttonComFontStyle = UIFontInits.COMBUTTON.getStyle();
    private int[] buttonComFontColor = UIFontInits.COMBUTTON.getColor();

    public void setCommandButtonFont(String font) {
        if ((font == null) || font.equals("")) {
            this.buttonComFont = UIFontInits.COMBUTTON.getFont();

        } else {
            this.buttonComFont = font;
        }
        somethingChanged();
    }

    public String getCommandButtonFont() {
        return this.buttonComFont;
    }

    public void setCommandButtonFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.buttonComFontSize = UIFontInits.COMBUTTON.getSize();

        } else {
            this.buttonComFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getCommandButtonFontsize() {
        return this.buttonComFontSize;
    }

    public void setCommandButtonFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.buttonComFontType = UIFontInits.COMBUTTON.getType();

        } else {
            this.buttonComFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getCommandButtonFonttype() {
        return this.buttonComFontType;
    }

    public void setCommandButtonFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.buttonComFontStyle = UIFontInits.COMBUTTON.getStyle();

        } else {
            this.buttonComFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getCommandButtonFontstyle() {
        return this.buttonComFontStyle;
    }

    public void setCommandButtonFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.buttonComFontColor = UIFontInits.COMBUTTON.getColor();

        } else {
            this.buttonComFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getCommandButtonFontcolor() {
        return this.buttonComFontColor;
    }

    /*
     * ##################################################
     */
    // Standard/Generic Btn Font

    private String buttonStdFont = UIFontInits.STDBUTTON.getFont();
    private int buttonStdFontSize = UIFontInits.STDBUTTON.getSize();
    private Fonttype buttonStdFontType = UIFontInits.STDBUTTON.getType();
    private Fontstyle buttonStdFontStyle = UIFontInits.STDBUTTON.getStyle();
    private int[] buttonStdFontColor = UIFontInits.STDBUTTON.getColor();

    public void setBtn_Std_Font(String font) {
        if ((font == null) || font.equals("")) {
            this.buttonStdFont = UIFontInits.STDBUTTON.getFont();

        } else {
            this.buttonStdFont = font;
        }
        somethingChanged();
    }

    public String getBtn_Std_Font() {
        return this.buttonStdFont;
    }

    public void setBtn_Std_Fontsize(int fontsize) {
        if (fontsize <= 0) {
            this.buttonStdFontSize = UIFontInits.STDBUTTON.getSize();

        } else {
            this.buttonStdFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getBtn_Std_Fontsize() {
        return this.buttonStdFontSize;
    }

    public void setBtn_Std_Fonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.buttonStdFontType = UIFontInits.STDBUTTON.getType();

        } else {
            this.buttonStdFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getBtn_Std_Fonttype() {
        return this.buttonStdFontType;
    }

    public void setBtn_Std_Fontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.buttonStdFontStyle = UIFontInits.STDBUTTON.getStyle();

        } else {
            this.buttonStdFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getBtn_Std_Fontstyle() {
        return this.buttonStdFontStyle;
    }

    public void setBtn_Std_Fontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.buttonStdFontColor = UIFontInits.STDBUTTON.getColor();

        } else {
            this.buttonStdFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getBtn_Std_Fontcolor() {
        return this.buttonStdFontColor;
    }

    /*
     * ##################################################
     */
    // Statustext Unzoomed

    private String statustextFont = UIFontInits.STATUS.getFont();
    private int statustextFontSize = UIFontInits.STATUS.getSize();
    private Fonttype statustextFontType = UIFontInits.STATUS.getType();
    private Fontstyle statustextFontStyle = UIFontInits.STATUS.getStyle();
    private int[] statustextFontColor = UIFontInits.STATUS.getColor();

    public void setStatustextFont(String font) {
        if ((font == null) || font.equals("")) {
            this.statustextFont = UIFontInits.STATUS.getFont();

        } else {
            this.statustextFont = font;
        }
        somethingChanged();
    }

    public String getStatustextFont() {
        return this.statustextFont;
    }

    public void setStatustextFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.statustextFontSize = UIFontInits.STATUS.getSize();

        } else {
            this.statustextFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getStatustextFontsize() {
        return this.statustextFontSize;
    }

    public void setStatustextFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.statustextFontType = UIFontInits.STATUS.getType();

        } else {
            this.statustextFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getStatustextFonttype() {
        return this.statustextFontType;
    }

    public void setStatustextFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.statustextFontStyle = UIFontInits.STATUS.getStyle();

        } else {
            this.statustextFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getStatustextFontstyle() {
        return this.statustextFontStyle;
    }

    public void setStatustextFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.statustextFontColor = UIFontInits.STATUS.getColor();

        } else {
            this.statustextFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getStatustextFontcolor() {
        return this.statustextFontColor;
    }

    /*
     * ##################################################
     */
    // Statustext Zoomed

    private String statustextZoomedFont = UIFontInits.STATUSZOOMED.getFont();
    private int statustextZoomedFontSize = UIFontInits.STATUSZOOMED.getSize();
    private Fonttype statustextZoomedFontType = UIFontInits.STATUSZOOMED.getType();
    private Fontstyle statustextZoomedFontStyle = UIFontInits.STATUSZOOMED.getStyle();
    private int[] statustextZoomedFontColor = UIFontInits.STATUSZOOMED.getColor();

    public void setStatustextZoomedFont(String font) {
        if ((font == null) || font.equals("")) {
            this.statustextZoomedFont = UIFontInits.STATUSZOOMED.getFont();

        } else {
            this.statustextZoomedFont = font;
        }
        somethingChanged();
    }

    public String getStatustextZoomedFont() {
        return this.statustextZoomedFont;
    }

    public void setStatustextZoomedFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.statustextZoomedFontSize = UIFontInits.STATUSZOOMED.getSize();

        } else {
            this.statustextZoomedFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getStatustextZoomedFontsize() {
        return this.statustextZoomedFontSize;
    }

    public void setStatustextZoomedFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.statustextZoomedFontType = UIFontInits.STATUSZOOMED.getType();

        } else {
            this.statustextZoomedFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getStatustextZoomedFonttype() {
        return this.statustextZoomedFontType;
    }

    public void setStatustextZoomedFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.statustextZoomedFontStyle = UIFontInits.STATUSZOOMED.getStyle();

        } else {
            this.statustextZoomedFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getStatustextZoomedFontstyle() {
        return this.statustextZoomedFontStyle;
    }

    public void setStatustextZoomedFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.statustextZoomedFontColor = UIFontInits.STATUSZOOMED.getColor();

        } else {
            this.statustextZoomedFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getStatustextZoomedFontcolor() {
        return this.statustextZoomedFontColor;
    }

    /*
     * ##################################################
     */
    // Nametext Unzoomed

    private String nametextFont = UIFontInits.NAME.getFont();
    private int nametextFontSize = UIFontInits.NAME.getSize();
    private Fonttype nametextFontType = UIFontInits.NAME.getType();
    private Fontstyle nametextFontStyle = UIFontInits.NAME.getStyle();
    private int[] nametextFontColor = UIFontInits.NAME.getColor();

    public void setNametextFont(String font) {
        if ((font == null) || font.equals("")) {
            this.nametextFont = UIFontInits.NAME.getFont();

        } else {
            this.nametextFont = font;
        }
        somethingChanged();
    }

    public String getNametextFont() {
        return this.nametextFont;
    }

    public void setNametextFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.nametextFontSize = UIFontInits.NAME.getSize();

        } else {
            this.nametextFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getNametextFontsize() {
        return this.nametextFontSize;
    }

    public void setNametextFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.nametextFontType = UIFontInits.NAME.getType();

        } else {
            this.nametextFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getNametextFonttype() {
        return this.nametextFontType;
    }

    public void setNametextFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.nametextFontStyle = UIFontInits.NAME.getStyle();

        } else {
            this.nametextFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getNametextFontstyle() {
        return this.nametextFontStyle;
    }

    public void setNametextFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.nametextFontColor = UIFontInits.NAME.getColor();

        } else {
            this.nametextFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getNametextFontcolor() {
        return this.nametextFontColor;
    }

    /*
     * ##################################################
     */
    // Nametext Unzoomed
    /*
     * 
     */

    private String nametextZoomedFont = UIFontInits.NAMEZOOMED.getFont();
    private int nametextZoomedFontSize = UIFontInits.NAMEZOOMED.getSize();
    private Fonttype nametextZoomedFontType = UIFontInits.NAMEZOOMED.getType();
    private Fontstyle nametextZoomedFontStyle = UIFontInits.NAMEZOOMED.getStyle();
    private int[] nametextZoomedFontColor = UIFontInits.NAMEZOOMED.getColor();

    public void setNametextZoomedFont(String font) {
        if ((font == null) || font.equals("")) {
            this.nametextZoomedFont = UIFontInits.NAMEZOOMED.getFont();

        } else {
            this.nametextZoomedFont = font;
        }
        somethingChanged();
    }

    public String getNametextZoomedFont() {
        return this.nametextZoomedFont;
    }

    public void setNametextZoomedFontsize(int fontsize) {
        if (fontsize <= 0) {
            this.nametextZoomedFontSize = UIFontInits.NAMEZOOMED.getSize();

        } else {
            this.nametextZoomedFontSize = fontsize;
        }
        somethingChanged();
    }

    public int getNametextZoomedFontsize() {
        return this.nametextZoomedFontSize;
    }

    public void setNametextZoomedFonttype(Fonttype fonttype) {
        if (fonttype == null) {
            this.nametextZoomedFontType = UIFontInits.NAMEZOOMED.getType();

        } else {
            this.nametextZoomedFontType = fonttype;
        }
        somethingChanged();
    }

    public Fonttype getNametextZoomedFonttype() {
        return this.nametextZoomedFontType;
    }

    public void setNametextZoomedFontstyle(Fontstyle fontstyle) {
        if (fontstyle == null) {
            this.nametextZoomedFontStyle = UIFontInits.NAMEZOOMED.getStyle();

        } else {
            this.nametextZoomedFontStyle = fontstyle;
        }
        somethingChanged();
    }

    public Fontstyle getNametextZoomedFontstyle() {
        return this.nametextZoomedFontStyle;
    }

    public void setNametextZoomedFontcolor(int[] fontcolor) {
        if ((fontcolor == null) || (fontcolor.length != 4)) {
            this.nametextZoomedFontColor = UIFontInits.NAMEZOOMED.getColor();

        } else {
            this.nametextZoomedFontColor = fontcolor;
        }
        somethingChanged();
    }

    public int[] getNametextZoomedFontcolor() {
        return this.nametextZoomedFontColor;
    }

    /*
     * ##################################################
     */

    /*
     * End of layout changes.
     */
    /*
     * ##################################################
     */

    @Override
    public String toString() {
        // StringBuilder sb = new StringBuilder(this.skinPreview.toString());

        // sb.append("Some data:\n");
        // sb.append("buttonAccess_Height: " + buttonAccess_Height + " | ");
        // sb.append("buttonAccess_Width: " + buttonAccess_Width + " | ");
        // sb.append("buttonCom_MinHeight: " + buttonCom_MinHeight + " | ");
        // sb.append("buttonCom_MinWidth: " + buttonCom_MinWidth + " | ");
        // sb.append("buttonComFont: " + buttonComFont + " | ");
        // sb.append("buttonComFontSize: " + buttonComFontSize + " | ");
        // sb.append("buttonPW_Height: " + buttonPW_Height + " | ");
        // sb.append("buttonPW_Width: " + buttonPW_Width + " | ");
        // sb.append("buttonShutdown_Height: " + buttonShutdown_Height + " | ");
        // sb.append("buttonShutdown_Width: " + buttonShutdown_Width + " | ");
        // sb.append("buttonShutdownmenu_Height: " + buttonShutdownmenu_Height + " | ");
        // sb.append("buttonShutdownmenu_Width: " + buttonShutdownmenu_Width + " | ");
        // sb.append("buttonStd_Height: " + buttonStd_Height + " | ");
        // sb.append("buttonStd_Width: " + buttonStd_Width + " | ");
        // sb.append("buttonStdFont: " + buttonStdFont + " | ");
        // sb.append("buttonStdFontSize: " + buttonStdFontSize + " | ");
        // sb.append("nametextFont: " + nametextFont + " | ");
        // sb.append("nametextFontSize: " + nametextFontSize + " | ");
        // sb.append("nametextZoomedFont: " + nametextZoomedFont + " | ");
        // sb.append("nametextZoomedFontSize: " + nametextZoomedFontSize + " | ");
        // sb.append("optionsbar_Height: " + optionsbar_Height + " | ");
        // sb.append("pwfield_Height: " + pwfield_Height + " | ");
        // sb.append("pwfield_Width: " + pwfield_Width + " | ");
        // sb.append("shadowIntensity: " + shadowIntensity + " | ");
        // sb.append("statustextFont: " + statustextFont + " | ");
        // sb.append("statustextFontSize: " + statustextFontSize + " | ");
        // sb.append("statustextZoomedFont: " + statustextZoomedFont + " | ");
        // sb.append("statustextZoomedFontSize: " + statustextZoomedFontSize + " | ");
        // sb.append("userlist_Height: " + userlist_Height + " | ");
        // sb.append("userlistImg_Height: " + userlistImg_Height + " | ");
        // sb.append("userlistImg_Width: " + userlistImg_Width + " | ");
        // sb.append("userlistImgFrame_Height: " + userlistImgFrame_Height + " | ");
        // sb.append("userlistImgFrame_Width: " + userlistImgFrame_Width + " | ");
        // sb.append("usertileImageOverlay_Height: " + usertileImageOverlay_Height + " | ");
        // sb.append("usertileImageOverlay_Width: " + usertileImageOverlay_Width + " | ");
        // sb.append("windowHeight: " + windowHeight + " | ");
        // sb.append("windowWidth: " + windowWidth + "\n");

        return this.skinPreview.toString();
    }
}
