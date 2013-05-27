/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import ale.Constants;
import ale.model.skin.SkinConstants.Animation;
import ale.model.skin.SkinConstants.CommandButton;
import ale.model.skin.SkinConstants.Fontstyle;
import ale.model.skin.SkinConstants.Fonttype;
import ale.model.skin.SkinConstants.Imagetype;
import ale.model.skin.SkinConstants.Layout;
import ale.model.skin.SkinConstants.Position;
import ale.model.skin.SkinConstants.UIResNumbers;
import ale.util.fileUtil.FileUtil;

final class SkinPropertiesReader {

    private SkinPropertiesReader() {
    }

    /*
     * Reads the saved skin properties from file.
     * 
     * The skinpath points to the location where the unpacked skin is saved.
     */
    protected static void read(SkinPropertiesVO props, Path source) throws IOException {
        assert (props != null) && FileUtil.control(source);
        readImages(source, props);
        readLayoutSettings(source, props);

        props.setChangestatus(false);
    }

    private static void readImages(Path path, SkinPropertiesVO props) {
        Path imgPath = path.getParent().resolve(Constants.SKIN_IMGFOLDER);

        /*
         * The first part reads the images. The images are saved with the correct resource number. If a certain image exist in the
         * image folder it gets inserted.
         */
        if (exists(imgPath.resolve(Constants.SKIN_BG_NAME))) {
            props.setBackgroundWithoutHandling(imgPath.resolve(Constants.SKIN_BG_NAME));
        }

        if (exists(getImage(imgPath, UIResNumbers.BRANDING_SMALL.getNum()))
                && exists(getImage(imgPath, UIResNumbers.BRANDING_MEDIUM.getNum()))
                && exists(getImage(imgPath, UIResNumbers.BRANDING_BIG.getNum()))) {
            props.setBranding(getImage(imgPath, UIResNumbers.BRANDING_SMALL.getNum()),
                    getImage(imgPath, UIResNumbers.BRANDING_MEDIUM.getNum()), getImage(imgPath, UIResNumbers.BRANDING_BIG.getNum()));
        } else {
            props.setBranding(null, null, null);
        }

        if (exists(getImage(imgPath, UIResNumbers.WINDOW.getNum()))) {
            props.setImgPath_Window(getImage(imgPath, UIResNumbers.WINDOW.getNum()));
        }
        if (exists(getImage(imgPath, UIResNumbers.PWFIELD_DEF.getNum()))) {
            props.setImgPath_PWField(getImage(imgPath, UIResNumbers.PWFIELD_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.PWFIELD_DIS.getNum()))) {
            props.setImgPath_PWField(getImage(imgPath, UIResNumbers.PWFIELD_DIS.getNum()), Imagetype.DISABLED);
        }
        if (exists(getImage(imgPath, UIResNumbers.PWFIELD_KFOC.getNum()))) {
            props.setImgPath_PWField(getImage(imgPath, UIResNumbers.PWFIELD_KFOC.getNum()), Imagetype.KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.PWFIELD_MFOC.getNum()))) {
            props.setImgPath_PWField(getImage(imgPath, UIResNumbers.PWFIELD_MFOC.getNum()), Imagetype.MOUSEFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_BAR_DEF.getNum()))) {
            props.setImgPath_SliderBar(getImage(imgPath, UIResNumbers.SLIDER_BAR_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_BAR_FOC.getNum()))) {
            props.setImgPath_SliderBar(getImage(imgPath, UIResNumbers.SLIDER_BAR_FOC.getNum()), Imagetype.FOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_BAR_PRE.getNum()))) {
            props.setImgPath_SliderBar(getImage(imgPath, UIResNumbers.SLIDER_BAR_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_UP_DEF.getNum()))) {
            props.setImgPath_SliderArrowUp(getImage(imgPath, UIResNumbers.SLIDER_UP_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_UP_FOC.getNum()))) {
            props.setImgPath_SliderArrowUp(getImage(imgPath, UIResNumbers.SLIDER_UP_FOC.getNum()), Imagetype.FOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_UP_PRE.getNum()))) {
            props.setImgPath_SliderArrowUp(getImage(imgPath, UIResNumbers.SLIDER_UP_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_DOWN_DEF.getNum()))) {
            props.setImgPath_SliderArrowDown(getImage(imgPath, UIResNumbers.SLIDER_DOWN_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_DOWN_FOC.getNum()))) {
            props.setImgPath_SliderArrowDown(getImage(imgPath, UIResNumbers.SLIDER_DOWN_FOC.getNum()), Imagetype.FOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_DOWN_PRE.getNum()))) {
            props.setImgPath_SliderArrowDown(getImage(imgPath, UIResNumbers.SLIDER_DOWN_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_MID_DEF.getNum()))) {
            props.setImgPath_SliderMidBtn(getImage(imgPath, UIResNumbers.SLIDER_MID_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_MID_FOC.getNum()))) {
            props.setImgPath_SliderMidBtn(getImage(imgPath, UIResNumbers.SLIDER_MID_FOC.getNum()), Imagetype.FOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SLIDER_MID_PRE.getNum()))) {
            props.setImgPath_SliderMidBtn(getImage(imgPath, UIResNumbers.SLIDER_MID_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.ACCESSSYMBOL.getNum()))) {
            props.setImgPath_AccessSym(getImage(imgPath, UIResNumbers.ACCESSSYMBOL.getNum()));
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWNSYMBOL.getNum()))) {
            props.setImgPath_ShutdownSym(getImage(imgPath, UIResNumbers.SHUTDOWNSYMBOL.getNum()));
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWNUPDATESYMBOL.getNum()))) {
            props.setImgPath_ShutdownUpdateSym(getImage(imgPath, UIResNumbers.SHUTDOWNUPDATESYMBOL.getNum()));
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWNARROWSYMBOL.getNum()))) {
            props.setImgPath_ShutdownArrowSym(getImage(imgPath, UIResNumbers.SHUTDOWNARROWSYMBOL.getNum()));
        }
        if (exists(getImage(imgPath, UIResNumbers.USERLIST_IMAGEFRAME_MFOC.getNum()))) {
            props.setImgPath_UserlistImage(getImage(imgPath, UIResNumbers.USERLIST_IMAGEFRAME_MFOC.getNum()), Imagetype.MOUSEFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.USERLIST_IMAGEFRAME_SEL.getNum()))) {
            props.setImgPath_UserlistImage(getImage(imgPath, UIResNumbers.USERLIST_IMAGEFRAME_SEL.getNum()), Imagetype.SELECTED);
        }
        if (exists(getImage(imgPath, UIResNumbers.USERLIST_IMAGEFRAME_FOCSEL.getNum()))) {
            props.setImgPath_UserlistImage(getImage(imgPath, UIResNumbers.USERLIST_IMAGEFRAME_FOCSEL.getNum()), Imagetype.FOCUSSELECTED);
        }
        if (exists(getImage(imgPath, UIResNumbers.USERLIST_IMAGEFRAME_DEF.getNum()))) {
            props.setImgPath_UserlistImage(getImage(imgPath, UIResNumbers.USERLIST_IMAGEFRAME_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.USERTILE_IMAGEFRAME_DEF.getNum()))) {
            props.setImgPath_UsertileImage(getImage(imgPath, UIResNumbers.USERTILE_IMAGEFRAME_DEF.getNum()));
        }
        if (exists(getImage(imgPath, UIResNumbers.COMBTNARROW_KFOC.getNum()))) {
            props.setImgPath_CommandBtnArrow(getImage(imgPath, UIResNumbers.COMBTNARROW_KFOC.getNum()), Imagetype.KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.COMBTNARROW_MFOC.getNum()))) {
            props.setImgPath_CommandBtnArrow(getImage(imgPath, UIResNumbers.COMBTNARROW_MFOC.getNum()), Imagetype.MOUSEFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.COMBTNARROW_PRE.getNum()))) {
            props.setImgPath_CommandBtnArrow(getImage(imgPath, UIResNumbers.COMBTNARROW_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.COMBTNARROW_DEF.getNum()))) {
            props.setImgPath_CommandBtnArrow(getImage(imgPath, UIResNumbers.COMBTNARROW_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.COMBTN_KFOC.getNum()))) {
            props.setImgPath_CommandBtn(getImage(imgPath, UIResNumbers.COMBTN_KFOC.getNum()), Imagetype.KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.COMBTN_MFOC.getNum()))) {
            props.setImgPath_CommandBtn(getImage(imgPath, UIResNumbers.COMBTN_MFOC.getNum()), Imagetype.MOUSEFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.COMBTN_PRE.getNum()))) {
            props.setImgPath_CommandBtn(getImage(imgPath, UIResNumbers.COMBTN_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.COMBTN_DEF.getNum()))) {
            props.setImgPath_CommandBtn(getImage(imgPath, UIResNumbers.COMBTN_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.STDBTN_KFOC.getNum()))) {
            props.setImgPath_StandardBtn(getImage(imgPath, UIResNumbers.STDBTN_KFOC.getNum()), Imagetype.KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.STDBTN_MFOC.getNum()))) {
            props.setImgPath_StandardBtn(getImage(imgPath, UIResNumbers.STDBTN_MFOC.getNum()), Imagetype.MOUSEFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.STDBTN_KFOCMFOC.getNum()))) {
            props.setImgPath_StandardBtn(getImage(imgPath, UIResNumbers.STDBTN_KFOCMFOC.getNum()), Imagetype.MOUSEFOCUS_KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.STDBTN_PRE.getNum()))) {
            props.setImgPath_StandardBtn(getImage(imgPath, UIResNumbers.STDBTN_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.STDBTN_DEF.getNum()))) {
            props.setImgPath_StandardBtn(getImage(imgPath, UIResNumbers.STDBTN_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.PWBTN_KFOCMFOC.getNum()))) {
            props.setImgPath_PWBtn(getImage(imgPath, UIResNumbers.PWBTN_KFOCMFOC.getNum()), Imagetype.MOUSEFOCUS_KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.PWBTN_PRE.getNum()))) {
            props.setImgPath_PWBtn(getImage(imgPath, UIResNumbers.PWBTN_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.PWBTN_DEF.getNum()))) {
            props.setImgPath_PWBtn(getImage(imgPath, UIResNumbers.PWBTN_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWN_KFOCMFOC.getNum()))) {
            props.setImgPath_ShutdownBtn(getImage(imgPath, UIResNumbers.SHUTDOWN_KFOCMFOC.getNum()), Imagetype.MOUSEFOCUS_KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWN_KFOC.getNum()))) {
            props.setImgPath_ShutdownBtn(getImage(imgPath, UIResNumbers.SHUTDOWN_KFOC.getNum()), Imagetype.KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWN_MFOC.getNum()))) {
            props.setImgPath_ShutdownBtn(getImage(imgPath, UIResNumbers.SHUTDOWN_MFOC.getNum()), Imagetype.MOUSEFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWN_PRE.getNum()))) {
            props.setImgPath_ShutdownBtn(getImage(imgPath, UIResNumbers.SHUTDOWN_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWN_DEF.getNum()))) {
            props.setImgPath_ShutdownBtn(getImage(imgPath, UIResNumbers.SHUTDOWN_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_KFOCMFOC.getNum()))) {
            props.setImgPath_ShutdownMenu(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_KFOCMFOC.getNum()), Imagetype.MOUSEFOCUS_KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_KFOC.getNum()))) {
            props.setImgPath_ShutdownMenu(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_KFOC.getNum()), Imagetype.KEYFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_MFOC.getNum()))) {
            props.setImgPath_ShutdownMenu(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_MFOC.getNum()), Imagetype.MOUSEFOCUS);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_PRE.getNum()))) {
            props.setImgPath_ShutdownMenu(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_PRE.getNum()), Imagetype.PRESSED);
        }
        if (exists(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_DEF.getNum()))) {
            props.setImgPath_ShutdownMenu(getImage(imgPath, UIResNumbers.SHUTDOWNMENU_DEF.getNum()), Imagetype.DEFAULT);
        }
        if (exists(getImage(imgPath, UIResNumbers.LOADINGSTATUS.getNum()))) {
            props.setImgPathLoadingStatusBg(getImage(imgPath, UIResNumbers.LOADINGSTATUS.getNum()));
        }
    }

    private static void readLayoutSettings(Path path, SkinPropertiesVO props) throws IOException {
        String line = null;
        String[] split = null;

        /*
         * The first part of the line gets transformed in an enum object to switch over it.
         */
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            while ((line = reader.readLine()) != null) {
                if (line.equals("") || (line.charAt(0) == SkinConstants.COMMENT)) {
                    continue;
                }

                split = line.split(SkinConstants.SPLIT);
                split = new String[] { split[0], split.length < 2 ? null : split[1] };

                if (split[1] == null) {
                    continue;
                }

                Layout layoutEnum = null;

                try {
                    layoutEnum = Layout.valueOf(split[0].trim().toUpperCase());

                } catch (IllegalArgumentException e) {
                    continue;
                }

                if (layoutEnum != null) {
                    switch (layoutEnum) {
                        case BRANDING_POSITION:
                            props.setBrandingPosition(getPosition(split[1]));
                            break;

                        case WINDOW_ACTIVE:
                            props.setWindowActive(Boolean.parseBoolean(split[1]));
                            break;

                        case WINDOW_HEIGHT:
                            props.setWindowHeight(parseInt(split[1]));
                            break;

                        case WINDOW_WIDTH:
                            props.setWindowWidth(parseInt(split[1]));
                            break;

                        case WINDOW_BORDERTHICKNESS:
                            props.setWindowBorderthickness(stringToArray_4(split[1]));
                            break;

                        case WINDOW_PADDING:
                            props.setWindowPadding(stringToArray_4(split[1]));
                            break;

                        case WINDOW_POSITION:
                            props.setWindowPosition(getPosition(split[1]));
                            break;

                        case WINDOW_ANIMATION:
                            props.setWindowAnimation(getAnimation(split[1]));
                            break;

                        case WINDOW_INNERANIMATION:
                            props.setWindowInnerAnimation(getAnimation(split[1]));
                            break;

                        //

                        case ACCBTN_HEIGHT:
                            props.setAccButtonHeight(parseInt(split[1]));
                            break;

                        case ACCBTN_WIDTH:
                            props.setAccButtonWidth(parseInt(split[1]));
                            break;

                        case ACCBTN_POSITION:
                            props.setAccButtonPosition(getPosition(split[1]));
                            break;

                        //

                        case COMBTN_MINHEIGHT:
                            props.setCommandButtonMinHeight(parseInt(split[1]));
                            break;

                        case COMBTN_MINWIDTH:
                            props.setCommandButtonMinWidth(parseInt(split[1]));
                            break;

                        case COMBTN_BORDERTHICKNESS:
                            props.setCommandButtonBorderthickness(stringToArray_4(split[1]));
                            break;

                        case COMBTN_PADDING:
                            props.setCommandButtonPadding(stringToArray_4(split[1]));
                            break;

                        case COMBTN_MARGIN:
                            props.setCommandButtonMargin(stringToArray_4(split[1]));
                            break;

                        case COMBTN_CONTENTALIGN:
                            props.setCommandButtonArrowpositionOnRight(Boolean.parseBoolean(split[1]));
                            break;

                        case COMBTN_BTNVISIBILITY_SWITCH:
                            props.setCommandButtonVisibility(CommandButton.SWITCH, Boolean.parseBoolean(split[1]));
                            break;

                        case COMBTN_BTNVISIBILITY_LOCK:
                            props.setCommandButtonVisibility(CommandButton.LOCK, Boolean.parseBoolean(split[1]));
                            break;

                        case COMBTN_BTNVISIBILITY_LOGOUT:
                            props.setCommandButtonVisibility(CommandButton.LOGOUT, Boolean.parseBoolean(split[1]));
                            break;

                        case COMBTN_BTNVISIBILITY_PASSWORD:
                            props.setCommandButtonVisibility(CommandButton.PASSWORD, Boolean.parseBoolean(split[1]));
                            break;

                        case COMBTN_BTNVISIBILITY_TASKMANAGER:
                            props.setCommandButtonVisibility(CommandButton.TASKMANAGER, Boolean.parseBoolean(split[1]));
                            break;

                        //

                        case STDBTN_HEIGHT:
                            props.setStandardButtonMinHeight(parseInt(split[1]));
                            break;

                        case STDBTN_WIDTH:
                            props.setStandardButtonMinWidth(parseInt(split[1]));
                            break;

                        case STDBTN_BORDERTHICKNESS:
                            props.setStandardButtonBorderthickness(stringToArray_4(split[1]));
                            break;

                        case STDBTN_POSITION:
                            props.setStandardButtonPosition(getPosition(split[1]));
                            break;

                        case STDBTN_MARGIN:
                            props.setStandardButtonMargin(stringToArray_4(split[1]));
                            break;

                        case STDBTN_PADDING:
                            props.setStandardButtonPadding(stringToArray_4(split[1]));
                            break;

                        //

                        case PWBTN_HEIGHT:
                            props.setPasswordButtonHeight(parseInt(split[1]));
                            break;

                        case PWBTN_WIDTH:
                            props.setPasswordButtonWidth(parseInt(split[1]));
                            break;

                        case PWBTN_BORDERTHICKNESS:
                            props.setPasswordButtonBorderthickness(stringToArray_4(split[1]));
                            break;

                        case PWBTN_MARGIN:
                            props.setPasswordButtonMargin(stringToArray_4(split[1]));
                            break;

                        //

                        case SHDFRAME_LAYOUT:
                            props.setShutdownframeBorderlayout(Boolean.parseBoolean(split[1]));
                            break;

                        case SHDFRAME_POSITION:
                            props.setShutdownframePosition(getPosition(split[1]));
                            break;

                        //

                        case SHDBTN_HEIGHT:
                            props.setShutdownButtonHeight(parseInt(split[1]));
                            break;

                        case SHDBTN_WIDTH:
                            props.setShutdownButtonWidth(parseInt(split[1]));
                            break;

                        case SHDBTN_BORDERTHICKNESS:
                            props.setShutdownButtonBorderthickness(stringToArray_4(split[1]));
                            break;

                        case SHDBTN_MARGIN:
                            props.setShutdownButtonMargin(stringToArray_4(split[1]));
                            break;

                        case SHDBTN_POSITION:
                            props.setShutdownButtonPosition(getPosition(split[1]));
                            break;

                        case SHDBTN_CONTENT:
                            props.setShutdownButtonContent(split[1]);
                            break;

                        //

                        case SHDMENU_HEIGHT:
                            props.setShutdownmenuButtonHeight(parseInt(split[1]));
                            break;

                        case SHDMENU_WIDTH:
                            props.setShutdownmenuButtonWidth(parseInt(split[1]));
                            break;

                        case SHDMENU_BORDERTHICKNESS:
                            props.setShutdownmenuButtonBorderthickness(stringToArray_4(split[1]));
                            break;

                        case SHDMENU_MARGIN:
                            props.setShutdownmenuButtonMargin(stringToArray_4(split[1]));
                            break;

                        case SHDMENU_POSITION:
                            props.setShutdownmenuButtonPosition(getPosition(split[1]));
                            break;

                        case SHDMENU_CONTENT:
                            props.setShutdownmenuButtonContent(split[1]);
                            break;

                        //

                        case ACCSHD_COMBINED:
                            props.setCombinedShdAcc(Boolean.parseBoolean(split[1]));
                            break;

                        case ACCSHD_POSITION:
                            props.setCombinedShdAcc_Position(getPosition(split[1]));
                            break;

                        //

                        case LOCALEBTN_VISIBILITY:
                            props.setLocaleButtonVisibility(Boolean.parseBoolean(split[1]));
                            break;

                        case LOCALEBTN_POSITION:
                            props.setLocaleButtonPosition(getPosition(split[1]));
                            break;

                        case LOCALEBTN_PADDING:
                            props.setLocaleButtonPadding(stringToArray_4(split[1]));
                            break;

                        //

                        case SECURITYOPTIONS_POSITION:
                            props.setSecurityMenuPosition(getPosition(split[1]));
                            break;

                        case SECURITYOPTIONS_PADDING:
                            props.setSecurityMenuPadding(stringToArray_4(split[1]));
                            break;

                        //

                        case OPTIONSBAR_HEIGHT:
                            props.setOptionsbarHeight(parseInt(split[1]));
                            break;

                        case ACC_FIX:
                            props.setWrapAccInNewElement(Boolean.parseBoolean(split[1]));
                            break;

                        //

                        case USERLIST_HEIGHT:
                            props.setUserlistHeight(parseInt(split[1]));
                            break;

                        case USERLIST_LAYOUT:
                            props.setUserlistVertical(Boolean.parseBoolean(split[1]));
                            break;

                        case USERLIST_POSITION:
                            props.setUserlistPosition(getPosition(split[1]));
                            break;

                        case USERLIST_PADDING:
                            props.setUserlistPadding(stringToArray_4(split[1]));
                            break;

                        //
                        case USERLIST_IMAGE_HEIGHT:
                            props.setUserlistImageHeight(parseInt(split[1]));
                            break;

                        case USERLIST_IMAGE_WIDTH:
                            props.setUserlistImageWidth(parseInt(split[1]));
                            break;

                        case USERLIST_IMAGE_PADDING:
                            props.setUserlistImagePadding(stringToArray_4(split[1]));
                            break;

                        case USERLIST_IMAGEFRAME_HEIGHT:
                            props.setUserlistImageFrameHeight(parseInt(split[1]));
                            break;

                        case USERLIST_IMAGEFRAME_WIDTH:
                            props.setUserlistImageFrameWidth(parseInt(split[1]));
                            break;

                        //

                        case USERTILE_LAYOUT:
                            props.setUsertileLayout(Boolean.parseBoolean(split[1]));
                            break;

                        case USERTILE_POSITION:
                            props.setUsertilePosition(getPosition(split[1]));
                            break;

                        case USERTILE_PWAREAONRIGHT:
                            props.setPWAreaPositionOnRightOfTexts(Boolean.parseBoolean(split[1]));
                            break;

                        case USERTILE_STATUSONRIGHT:
                            props.setStatusPositionOnRight(Boolean.parseBoolean(split[1]));
                            break;

                        case USERTILE_IMAGE_POSITION:
                            props.setUsertileImagePosition(getPosition(split[1]));
                            break;

                        //

                        case USERTILE_IMAGE_HEIGHT:
                            props.setUsertileImageHeight(parseInt(split[1]));
                            break;

                        case USERTILE_IMAGE_WIDTH:
                            props.setUsertileImageWidth(parseInt(split[1]));
                            break;

                        case USERTILE_IMAGE_PADDING:
                            props.setUsertileImagePadding(stringToArray_4(split[1]));
                            break;

                        case USERTILE_IMAGEFRAME_HEIGHT:
                            props.setUsertileImageFrameHeight(parseInt(split[1]));
                            break;

                        case USERTILE_IMAGEFRAME_WIDTH:
                            props.setUsertileImageFrameWidth(parseInt(split[1]));
                            break;

                        //

                        case PWFIELD_HEIGHT:
                            props.setPWfieldHeight(parseInt(split[1]));
                            break;

                        case PWFIELD_WIDTH:
                            props.setPWfieldWidth(parseInt(split[1]));
                            break;

                        case PWFIELD_BORDERTHICKNESS:
                            props.setPWfieldBorderthickness(stringToArray_4(split[1]));
                            break;

                        case PWFIELD_MARGIN:
                            props.setPWfieldMargin(stringToArray_4(split[1]));
                            break;

                        //

                        case PWAREA_UPSHIFT:
                            props.setPasswordfieldUpshift(parseInt(split[1]));
                            break;

                        case PWAREA_DOWNSHIFT:
                            props.setPasswordfieldDownshift(parseInt(split[1]));
                            break;

                        case PWAREA_RIGHTSHIFT:
                            props.setPasswordfieldRightshift(parseInt(split[1]));
                            break;

                        //

                        case LOADINGSTATUS_WIDTH:
                            props.setLoadingStatusWidth(parseInt(split[1]));
                            break;

                        case LOADINGSTATUS_BORDERTHICKNESS:
                            props.setLoadingStatusBorderthickness(stringToArray_4(split[1]));
                            break;

                        case LOADINGSTATUS_ANIMATION:
                            props.setLoadingStatusAnimation(getAnimation(split[1]));
                            break;
                            
                        case LOADINGSTATUS_RINGANIMVISIBILITY:
                            props.setLoadingStatusRinganimHidden(Boolean.parseBoolean(split[1]));
                            break;

                        //

                        case FONTSHADOW:
                            props.setShadowIntensity(parseInt(split[1]));
                            break;

                        // Fonts --->

                        case SHD_FONT:
                            props.setShutdownFont(split[1]);
                            break;

                        case SHD_FONTSIZE:
                            props.setShutdownFontsize(parseInt(split[1]));
                            break;

                        case SHD_FONTTYPE:
                            props.setShutdownFonttype(getFonttype(split[1]));
                            break;

                        case SHD_FONTSTYLE:
                            props.setShutdownFontstyle(getFontstyle(split[1]));
                            break;

                        case SHD_FOREGROUND:
                            props.setShutdownFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case SHDMENU_FONT:
                            props.setShutdownMenuFont(split[1]);
                            break;

                        case SHDMENU_FONTSIZE:
                            props.setShutdownMenuFontsize(parseInt(split[1]));
                            break;

                        case SHDMENU_FONTTYPE:
                            props.setShutdownMenuFonttype(getFonttype(split[1]));
                            break;

                        case SHDMENU_FONTSTYLE:
                            props.setShutdownMenuFontstyle(getFontstyle(split[1]));
                            break;

                        case SHDMENU_FOREGROUND:
                            props.setShutdownMenuFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case PWFIELD_FONT:
                            props.setPWFieldFont(split[1]);
                            break;

                        case PWFIELD_FONTSIZE:
                            props.setPWFieldFontsize(parseInt(split[1]));
                            break;

                        case PWFIELD_FONTTYPE:
                            props.setPWFieldFonttype(getFonttype(split[1]));
                            break;

                        case PWFIELD_FONTSTYLE:
                            props.setPWFieldFontstyle(getFontstyle(split[1]));
                            break;

                        case PWFIELD_FOREGROUND:
                            props.setPWFieldFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case RESETPW_FONT:
                            props.setPWResetFont(split[1]);
                            break;

                        case RESETPW_FONTSIZE:
                            props.setPWResetFontsize(parseInt(split[1]));
                            break;

                        case RESETPW_FONTTYPE:
                            props.setPWResetFonttype(getFonttype(split[1]));
                            break;

                        case RESETPW_FONTSTYLE:
                            props.setPWResetFontstyle(getFontstyle(split[1]));
                            break;

                        case RESETPW_FOREGROUND:
                            props.setPWResetFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case LOADINGSTATUS_FONT:
                            props.setLoadingStatusFont(split[1]);
                            break;

                        case LOADINGSTATUS_FONTSIZE:
                            props.setLoadingStatusFontsize(parseInt(split[1]));
                            break;

                        case LOADINGSTATUS_FONTTYPE:
                            props.setLoadingStatusFonttype(getFonttype(split[1]));
                            break;

                        case LOADINGSTATUS_FONTSTYLE:
                            props.setLoadingStatusFontstyle(getFontstyle(split[1]));
                            break;

                        case LOADINGSTATUS_FOREGROUND:
                            props.setLoadingStatusFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case SHORTMESSAGE_FONT:
                            props.setShortMessageFont(split[1]);
                            break;

                        case SHORTMESSAGE_FONTSIZE:
                            props.setShortMessageFontsize(parseInt(split[1]));
                            break;

                        case SHORTMESSAGE_FONTTYPE:
                            props.setShortMessageFonttype(getFonttype(split[1]));
                            break;

                        case SHORTMESSAGE_FONTSTYLE:
                            props.setShortMessageFontstyle(getFontstyle(split[1]));
                            break;

                        case SHORTMESSAGE_FOREGROUND:
                            props.setShortMessageFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case COMBTN_FONT:
                            props.setCommandButtonFont(split[1]);
                            break;

                        case COMBTN_FONTSIZE:
                            props.setCommandButtonFontsize(parseInt(split[1]));
                            break;

                        case COMBTN_FONTTYPE:
                            props.setCommandButtonFonttype(getFonttype(split[1]));
                            break;

                        case COMBTN_FONTSTYLE:
                            props.setCommandButtonFontstyle(getFontstyle(split[1]));
                            break;

                        case COMBTN_FOREGROUND:
                            props.setCommandButtonFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case STDBTN_FONT:
                            props.setBtn_Std_Font(split[1]);
                            break;

                        case STDBTN_FONTSIZE:
                            props.setBtn_Std_Fontsize(parseInt(split[1]));
                            break;

                        case STDBTN_FONTTYPE:
                            props.setBtn_Std_Fonttype(getFonttype(split[1]));
                            break;

                        case STDBTN_FONTSTYLE:
                            props.setBtn_Std_Fontstyle(getFontstyle(split[1]));
                            break;

                        case STDBTN_FOREGROUND:
                            props.setBtn_Std_Fontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case STATUSTEXT_FONT:
                            props.setStatustextFont(split[1]);
                            break;

                        case STATUSTEXT_FONTSIZE:
                            props.setStatustextFontsize(parseInt(split[1]));
                            break;

                        case STATUSTEXT_FONTTYPE:
                            props.setStatustextFonttype(getFonttype(split[1]));
                            break;

                        case STATUSTEXT_FONTSTYLE:
                            props.setStatustextFontstyle(getFontstyle(split[1]));
                            break;

                        case STATUSTEXT_FOREGROUND:
                            props.setStatustextFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case STATUSTEXTZOOMED_FONT:
                            props.setStatustextZoomedFont(split[1]);
                            break;

                        case STATUSTEXTZOOMED_FONTSIZE:
                            props.setStatustextZoomedFontsize(parseInt(split[1]));
                            break;

                        case STATUSTEXTZOOMED_FONTTYPE:
                            props.setStatustextZoomedFonttype(getFonttype(split[1]));
                            break;

                        case STATUSTEXTZOOMED_FONTSTYLE:
                            props.setStatustextZoomedFontstyle(getFontstyle(split[1]));
                            break;

                        case STATUSTEXTZOOMED_FOREGROUND:
                            props.setStatustextZoomedFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case NAMETEXT_FONT:
                            props.setNametextFont(split[1]);
                            break;

                        case NAMETEXT_FONTSIZE:
                            props.setNametextFontsize(parseInt(split[1]));
                            break;

                        case NAMETEXT_FONTTYPE:
                            props.setNametextFonttype(getFonttype(split[1]));
                            break;

                        case NAMETEXT_FONTSTYLE:
                            props.setNametextFontstyle(getFontstyle(split[1]));
                            break;

                        case NAMETEXT_FOREGROUND:
                            props.setNametextFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        case NAMETEXTZOOMED_FONT:
                            props.setNametextZoomedFont(split[1]);
                            break;

                        case NAMETEXTZOOMED_FONTSIZE:
                            props.setNametextZoomedFontsize(parseInt(split[1]));
                            break;

                        case NAMETEXTZOOMED_FONTTYPE:
                            props.setNametextZoomedFonttype(getFonttype(split[1]));
                            break;

                        case NAMETEXTZOOMED_FONTSTYLE:
                            props.setNametextZoomedFontstyle(getFontstyle(split[1]));
                            break;

                        case NAMETEXTZOOMED_FOREGROUND:
                            props.setNametextZoomedFontcolor(stringToArray_4(split[1]));
                            break;

                        //

                        default:
                            throw new IOException();
                    }
                }
            }

            reader.close();

        } catch (IOException e) {
            throw e;
        }
    }

    private static boolean exists(Path p) {
        return Files.exists(p, LinkOption.NOFOLLOW_LINKS);
    }

    private static Path getImage(Path dir, int resnum) {
        return dir.resolve(resnum + Constants.DEFAULT_SKINIMAGE_TYPE);
    }

    private static Position getPosition(String s) {
        Position ret = null;

        if ((s != null) && !s.equals("")) {
            try {
                ret = Position.valueOf(s.trim().toUpperCase());

            } catch (IllegalArgumentException e) {
                ret = null;
            }
        }
        return ret;
    }

    private static Animation getAnimation(String s) {
        Animation ret = null;

        if ((s != null) && !s.equals("")) {
            try {
                ret = Animation.valueOf(s.trim().toUpperCase());

            } catch (IllegalArgumentException e) {
                ret = null;
            }
        }
        return ret;
    }

    private static Fonttype getFonttype(String s) {
        Fonttype ret = null;

        if ((s != null) && !s.equals("")) {
            try {
                ret = Fonttype.valueOf(s.trim().toUpperCase());

            } catch (IllegalArgumentException e) {
                ret = null;
            }
        }
        return ret;
    }

    private static Fontstyle getFontstyle(String s) {
        Fontstyle ret = null;

        if ((s != null) && !s.equals("")) {
            try {
                ret = Fontstyle.valueOf(s.trim().toUpperCase());

            } catch (IllegalArgumentException e) {
                ret = null;
            }
        }
        return ret;
    }

    private static int parseInt(String s) {
        int val = -1;

        if (s != null) {
            try {
                val = Integer.parseInt(s);

            } catch (NumberFormatException e) {
                val = -1;
            }
        }

        return val;
    }

    private static int[] stringToArray_4(String s) {
        int[] ret = null;

        if ((s != null) && !s.equals("")) {
            String[] tmp = s.split(SkinConstants.SPLIT2);

            if (tmp.length == 4) {
                ret = new int[4];

                try {
                    for (int i = 0; i < tmp.length; i++) {
                        ret[i] = Integer.parseInt(tmp[i]);
                    }

                } catch (NumberFormatException e) {
                    ret = null;
                }
            }
        }

        return ret;
    }
}
