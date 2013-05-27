/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.util;

import java.util.ResourceBundle;

import ale.controller.Main;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui <br/>
 * Class  : GUIStrings <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>GUIStrings</code> class contains methods to handle the strings of the gui. It gets the strings from a locale resourcebundle.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 24.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class GUIStrings {

    private static ResourceBundle locale;

    private GUIStrings() {
    }

    /**
     * Sets the current locale resources.
     *
     * @param localebundle locale resources
     */
    public static void initLocale(ResourceBundle localebundle) {
        if (localebundle == null) {
            throw new IllegalArgumentException();
        }

        GUIStrings.locale = localebundle;
    }

    /**
     * Returns the current locale resource bundle.
     *
     * @return locale resources
     */
    public static ResourceBundle getCurrentLocale() {
        return locale;
    }

    /**
     * Transforms a key to a localized string.
     *
     * @param key A string which identfies a line in the locale resources.
     * @return the localized string
     */
    public static String keyToLocatedString(String key) {
        String ret = null;

        try {
            ret = locale.getString(key);

        } catch (Exception e) {
            Main.showProblemMessage("The locale resourceBundle is corrupted!");
            ret = key;
        }

        return ret;
    }

    public static final String KEY_FIRSTSTARTDIALOG_TITLE = "dialog-firstStart-title";
    public static final String KEY_FIRSTSTARTDIALOG_MSGHEAD = "dialog-firstStart-messageHead";
    public static final String KEY_FIRSTSTARTDIALOG_MSG = "dialog-firstStart-message";
    public static final String KEY_FIRSTSTARTDIALOG_OK = "dialog-firstStart-ok";

    public static final String KEY_ABOUTDIALOG_TITLE = "dialog-about-title";
    public static final String KEY_ABOUTDIALOG_OK = "dialog-about-ok";

    public static final String KEY_AFTERAPPLYDIALOG_TITLE = "dialog-afterApply-title";
    public static final String KEY_AFTERAPPLYDIALOG_MSG = "dialog-afterApply-message";
    public static final String KEY_AFTERAPPLYDIALOG_HINT1 = "dialog-afterApply-hint1";
    public static final String KEY_AFTERAPPLYDIALOG_HINT2 = "dialog-afterApply-hint2";
    public static final String KEY_AFTERAPPLYDIALOG_BACKUPHINT = "dialog-afterApply-backupHint";
    public static final String KEY_AFTERAPPLYDIALOG_BACKUP = "dialog-afterApply-applyBackup";
    public static final String KEY_AFTERAPPLYDIALOG_OK = "dialog-afterApply-ok";
    public static final String KEY_AFTERAPPLYDIALOG_BACKUPAPPLIED = "dialog-afterApply-backupApplied";

    public static final String KEY_APPLYDIALOG_TITLE = "dialog-apply-title";
    public static final String KEY_APPLYDIALOG_MSG = "dialog-apply-message";
    public static final String KEY_APPLYDIALOG_OK = "dialog-apply-ok";
    public static final String KEY_APPLYDIALOG_CANCEL = "dialog-apply-cancel";

    public static final String KEY_DELETEDIALOG_TITLE = "dialog-delete-title";
    public static final String KEY_DELETEDIALOG_MSG = "dialog-delete-message";
    public static final String KEY_DELETEDIALOG_OK = "dialog-delete-ok";
    public static final String KEY_DELETEDIALOG_CANCEL = "dialog-delete-cancel";

    public static final String KEY_ERRORDIALOG_TITLE = "dialog-error-title";
    public static final String KEY_ERRORDIALOG_MSG = "dialog-error-message";
    public static final String KEY_ERRORDIALOG_OK = "dialog-error-ok";

    public static final String KEY_NEWSKINDIALOG_TITLE = "dialog-newSkin-title";
    public static final String KEY_NEWSKINDIALOG_MSG = "dialog-newSkin-message";
    public static final String KEY_NEWSKINDIALOG_NAME = "dialog-newSkin-name";
    public static final String KEY_NEWSKINDIALOG_AUTHOR = "dialog-newSkin-author";
    public static final String KEY_NEWSKINDIALOG_WEB = "dialog-newSkin-website";
    public static final String KEY_NEWSKINDIALOG_IMAGE = "dialog-newSkin-image";
    public static final String KEY_NEWSKINDIALOG_OK = "dialog-newSkin-ok";
    public static final String KEY_NEWSKINDIALOG_CANCEL = "dialog-newSkin-cancel";
    public static final String KEY_NEWSKINDIALOG_REPLACE = "dialog-newSkin-replace";
    public static final String KEY_NEWSKINDIALOG_EXISTS = "dialog-newSkin-nameExists";
    public static final String KEY_NEWSKINDIALOG_FCTITLE = "dialog-newSkin-chooserTitle";

    public static final String KEY_QUITCONFIRMDIALOG_TITLE = "dialog-quitConfirm-title";
    public static final String KEY_QUITCONFIRMDIALOG_MSG = "dialog-quitConfirm-message";
    public static final String KEY_QUITCONFIRMDIALOG_SAVE = "dialog-quitConfirm-save";
    public static final String KEY_QUITCONFIRMDIALOG_SKIP = "dialog-quitConfirm-discard";
    public static final String KEY_QUITCONFIRMDIALOG_CANCEL = "dialog-quitConfirm-cancel";

    public static final String KEY_SETTINGSDIALOG_TITLE = "dialog-settings-title";
    public static final String KEY_SETTINGSDIALOG_LANGCB = "dialog-settings-lang";
    public static final String KEY_SETTINGSDIALOG_BGSCALE = "dialog-settings-scale";
    public static final String KEY_SETTINGSDIALOG_OK = "dialog-settings-ok";
    public static final String KEY_SETTINGSDIALOG_CANCEL = "dialog-settings-cancel";

    public static final String KEY_SAVEASDIALOG_TITLE = "dialog-saveAs-title";
    public static final String KEY_SAVEASDIALOG_MSG = "dialog-saveAs-message";
    public static final String KEY_SAVEASDIALOG_NAME = "dialog-saveAs-name";
    public static final String KEY_SAVEASDIALOG_REPLACE = "dialog-saveAs-replace";
    public static final String KEY_SAVEASDIALOG_EXISTS = "dialog-saveAs-nameExists";
    public static final String KEY_SAVEASDIALOG_OK = "dialog-saveAs-ok";
    public static final String KEY_SAVEASDIALOG_CANCEL = "dialog-saveAs-cancel";
    public static final String KEY_SAVEASDIALOG_OPENAFTERSAVE = "dialog-saveAs-openNow";

    public static final String KEY_RENAMEDIALOG_TITLE = "dialog-rename-title";
    public static final String KEY_RENAMEDIALOG_MSG = "dialog-rename-message";
    public static final String KEY_RENAMEDIALOG_OK = "dialog-rename-ok";
    public static final String KEY_RENAMEDIALOG_CANCEL = "dialog-rename-cancel";

    public static final String KEY_QUICKCHOOSER_LISTTITLE = "chooser-listtitle";
    public static final String KEY_QUICKCHOOSER_REFRESHLIST = "chooser-refresh";
    public static final String KEY_QUICKCHOOSER_PREVIEWTITLE = "chooser-previewtitle";
    public static final String KEY_QUICKCHOOSER_INFO = "chooser-info";
    public static final String KEY_QUICKCHOOSER_INFO_NAME = "chooser-info-name";
    public static final String KEY_QUICKCHOOSER_INFO_AUTHOR = "chooser-info-author";
    public static final String KEY_QUICKCHOOSER_INFO_WEB = "chooser-info-website";
    public static final String KEY_QUICKCHOOSER_WARNINGLABEL = "chooser-warning";
    public static final String KEY_QUICKCHOOSER_APPLYBTN = "chooser-apply";
    public static final String KEY_QUICKCHOOSER_EDITBTN = "chooser-edit";
    public static final String KEY_QUICKCHOOSER_DELETEBTN = "chooser-delete";
    public static final String KEY_QUICKCHOOSER_RENAMEBTN = "chooser-rename";
    public static final String KEY_QUICKCHOOSER_NEWBTN = "chooser-new";
    public static final String KEY_QUICKCHOOSER_COPYNAME = "chooser-copy-name";
    public static final String KEY_QUICKCHOOSER_COPYAUTHOR = "chooser-copy-author";
    public static final String KEY_QUICKCHOOSER_COPYWEBSITE = "chooser-copy-website";

    public static final String KEY_EDITOR_MENUHINT = "editor-menuHint";
    public static final String KEY_EDITOR_COMBOBOXHINT = "editor-comboboxHint";
    public static final String KEY_EDITOR_COMBOBOXHINTCONTENT = "editor-comboboxHint-contains";
    public static final String KEY_EDITOR_LEFTINFO = "editor-info-left";
    public static final String KEY_EDITOR_RIGHTINFO = "editor-info-right";
    public static final String KEY_EDITOR_GENCHANGESINFO = "editor-info-genChangesInfo";
    public static final String KEY_EDITOR_GENCHANGESBTN = "editor-btn-genChanges";
    public static final String KEY_EDITOR_BTNCHANGESINFO = "editor-info-btnChangesInfo";
    public static final String KEY_EDITOR_BTNCHANGESBTN = "editor-btn-btnChanges";
    public static final String KEY_EDITOR_USERLISTCHANGESINFO = "editor-info-userlistChanges";
    public static final String KEY_EDITOR_USERLISTCHANGESBTN = "editor-btn-userlistChanges";
    public static final String KEY_EDITOR_USERTILECHANGESINFO = "editor-info-usertileChanges";
    public static final String KEY_EDITOR_USERTILECHANGESBTN = "editor-btn-usertileChanges";
    public static final String KEY_EDITOR_FONTCHANGESINFO = "editor-info-fontChanges";
    public static final String KEY_EDITOR_FONTCHANGESBTN = "editor-btn-fontChanges";
    public static final String KEY_EDITOR_UDATELOCALE = "editor-localeUpdateMsg";
    public static final String KEY_EDITOR_SWITCHPREVIEWS = "editor-info-previewBtns";
    public static final String KEY_EDITOR_TOGGLEUSERLIST = "editor-btn-userlistPreview";
    public static final String KEY_EDITOR_TOGGLEUSERTILE = "editor-btn-usertilePreview";
    public static final String KEY_EDITOR_TOGGLESECURITYMENU = "editor-btn-secMenuPreview";
    public static final String KEY_EDITOR_PREVIEWADJUST = "editor-previewAdjusting";
    public static final String KEY_EDITOR_SCALEBACKGROUND = "editor-btn-scalePreviewBackground";
    public static final String KEY_EDITOR_SHOWHIDEMENU = "editor-btn-showHideMenu";
    public static final String KEY_EDITOR_DEFAULTCBOPTION = "editor-comboboxDefault";
    public static final String KEY_EDITOR_FIELDTITLE_IMAGE = "editor-fieldTitle-image";
    public static final String KEY_EDITOR_TOGGLETRANSPARENT = "editor-btn-imageTransparent";
    public static final String KEY_EDITOR_RESET = "editor-btn-imageReset";
    public static final String KEY_EDITOR_IMGFOCUS = "editor-imageFocus";
    public static final String KEY_EDITOR_IMGDEFAULT = "editor-imageDefault";
    public static final String KEY_EDITOR_IMGDISABLED = "editor-imageDisabled";
    public static final String KEY_EDITOR_IMGPRESSED = "editor-imagePressed";
    public static final String KEY_EDITOR_IMGSELECTED = "editor-imageSelected";
    public static final String KEY_EDITOR_FIELDTITLE_POSITION = "editor-fieldTitle-position";
    public static final String KEY_EDITOR_POS_TOPLEFT = "editor-position-topleft";
    public static final String KEY_EDITOR_POS_TOP = "editor-position-top";
    public static final String KEY_EDITOR_POS_TOPRIGHT = "editor-position-topright";
    public static final String KEY_EDITOR_POS_LEFT = "editor-position-left";
    public static final String KEY_EDITOR_POS_CENTER = "editor-position-center";
    public static final String KEY_EDITOR_POS_RIGHT = "editor-position-right";
    public static final String KEY_EDITOR_POS_BOTTOMLEFT = "editor-position-bottomleft";
    public static final String KEY_EDITOR_POS_BOTTOM = "editor-position-bottom";
    public static final String KEY_EDITOR_POS_BOTTOMRIGHT = "editor-position-bottomright";
    public static final String KEY_EDITOR_FIELDTITLE_HIDE = "editor-fieldTitle-hide";
    public static final String KEY_EDITOR_HIDE = "editor-hide-btnLabel";
    public static final String KEY_EDITOR_FIELDTITLE_SIZE = "editor-fieldTitle-size";
    public static final String KEY_EDITOR_MINSIZE = "editor-minimumSize";
    public static final String KEY_EDITOR_WIDTH = "editor-size-width";
    public static final String KEY_EDITOR_HEIGHT = "editor-size-height";
    public static final String KEY_EDITOR_FIELDTITLE_BORDER = "editor-fieldTitle-border";
    public static final String KEY_EDITOR_BORDERTOOLTIP = "editor-border-tooltip";
    public static final String KEY_EDITOR_FONTSIZE = "editor-gen-size";
    public static final String KEY_EDITOR_FIELDTITLE_PADDING = "editor-fieldTitle-padding";
    public static final String KEY_EDITOR_PADDINGTOOLTIP = "editor-padding-tooltip";
    public static final String KEY_EDITOR_FIELDTITLE_MARGIN = "editor-fieldTitle-margin";
    public static final String KEY_EDITOR_MARGINTOOLTIP = "editor-margin-tooltip";
    public static final String KEY_EDITOR_ACCSYM = "editor-accessSym-image";
    public static final String KEY_EDITOR_COMSYM = "editor-comSym-image";
    public static final String KEY_EDITOR_FIELDTITLE_COMSYM_R = "editor-comSym-position";
    public static final String KEY_EDITOR_COMSYM_R = "editor-comSym-onRight";
    public static final String KEY_EDITOR_FIELDTITLE_CONTENT = "editor-fieldTitle-content";
    public static final String KEY_EDITOR_FIELDTITLE_FONT = "editor-fieldTitle-font";
    public static final String KEY_EDITOR_FONTCOLOR = "editor-fontcolor";
    public static final String KEY_EDITOR_FONTBOLD = "editor-fontbold";
    public static final String KEY_EDITOR_FONTUNDERLINE = "editor-fontunderline";
    public static final String KEY_EDITOR_FONTSHADOW = "editor-fontshadow";
    public static final String KEY_EDITOR_FONTSHADOWINTENSITY = "editor-fontshadowIntensity";
    public static final String KEY_EDITOR_FIELDTITLE_ULISTVERT = "editor-fieldTitle-userlistVertical";
    public static final String KEY_EDITOR_USERLISTVERT_BTN = "editor-userlistVerticalBtn";
    public static final String KEY_EDITOR_USERLIST_STATUSFONT = "editor-userlistStatustextFont";
    public static final String KEY_EDITOR_USERLIST_NAMEFONT = "editor-userlistNametextFont";
    public static final String KEY_EDITOR_SLIDERBTN = "editor-sliderbtn";
    public static final String KEY_EDITOR_SLIDERBAR = "editor-sliderbar";
    public static final String KEY_EDITOR_SLIDERUP = "editor-sliderup";
    public static final String KEY_EDITOR_SLIDERDOWN = "editor-sliderdown";
    public static final String KEY_EDITOR_USERIMG_OVERLAY = "editor-userimageOverlay";
    public static final String KEY_EDITOR_ACTIVATEWINDOW = "editor-windowActive";
    public static final String KEY_EDITOR_FIELDTITLE_ACTIVATEWINDOW = "editor-fieldTitle-windowActive";
    public static final String KEY_EDITOR_WINDOW_INNERANIM = "editor-window-innerAnim";
    public static final String KEY_EDITOR_FIELDTITLE_UTILEHOR = "editor-fieldTitle-usertileAlign";
    public static final String KEY_EDITOR_UTILE_HOR = "editor-usertileTextAlignHorizontal";
    public static final String KEY_EDITOR_FIELDTITLE_UTILE_PWRIGHT = "editor-fieldTitle-passwordfieldPosition";
    public static final String KEY_EDITOR_UTILE_PWRIGHT = "editor-passwordfieldPositionLabel";
    public static final String KEY_EDITOR_FIELDTITLE_UTILE_STATUSRIGHT = "editor-fieldTitle-statustextPosition";
    public static final String KEY_EDITOR_UTILE_STATUSRIGHT = "editor-statustextPositionLabel";
    public static final String KEY_EDITOR_ULISTPADDING_HINT = "editor-userlistpadding-hint";
    public static final String KEY_EDITOR_FIELDTITLE_COMBINEACCSHD = "editor-fieldTitle-combineAccShd";
    public static final String KEY_EDITOR_COMBINEACCSHD = "editor-combineAccShdLabel";
    public static final String KEY_EDITOR_FIELDTITLE_COMBINEACCSHD_POS = "editor-fieldTitle-combinedAccShdPos";
    public static final String KEY_EDITOR_SHDSYM = "editor-shdsym";
    public static final String KEY_EDITOR_SHDUPDATESYM = "editor-shdsym-update";
    public static final String KEY_EDITOR_SHDARROWSYM = "editor-shd-arrowsym";
    public static final String KEY_EDITOR_FIELDTITLE_ANIM = "editor-fieldTitle-animation";
    public static final String KEY_EDITOR_PWAREA_UPSHIFT = "editor-pwarea-upshift";
    public static final String KEY_EDITOR_PWAREA_DOWNSHIFT = "editor-pwarea-downshift";
    public static final String KEY_EDITOR_PWAREA_RIGHTSHIFT = "editor-pwarea-rightshift";
    public static final String KEY_EDITOR_PWAREA_SHIFTAMOUNT = "editor-pwarea-shiftamount";
    public static final String KEY_EDITOR_ANIM_H = "editor-animation-horizontal";
    public static final String KEY_EDITOR_ANIM_V = "editor-animation-vertical";
    public static final String KEY_EDITOR_ANIM_SLOW = "editor-animation-slow";
    public static final String KEY_EDITOR_ANIM_FAST = "editor-animation-fast";
    public static final String KEY_EDITOR_ANIM_NONE = "editor-animation-original";
    public static final String KEY_EDITOR_CMDBTN_LOCK = "editor-commandbtn-lock";
    public static final String KEY_EDITOR_CMDBTN_SWITCH = "editor-commandbtn-switch";
    public static final String KEY_EDITOR_CMDBTN_LOGOUT = "editor-commandbtn-logOut";
    public static final String KEY_EDITOR_CMDBTN_PASSWD = "editor-commandbtn-password";
    public static final String KEY_EDITOR_CMDBTN_TASKMAN = "editor-commandbtn-taskmanager";

    public static final String KEY_EDITOR_GENCHANGESOPT_1 = "editor-genOpt1";
    public static final String KEY_EDITOR_GENCHANGESOPT_2 = "editor-genOpt2";
    public static final String KEY_EDITOR_GENCHANGESOPT_3 = "editor-genOpt3";
    public static final String KEY_EDITOR_GENCHANGESOPT_4 = "editor-genOpt4";
    public static final String KEY_EDITOR_GENCHANGESOPT_5 = "editor-genOpt5";
    public static final String KEY_EDITOR_GENCHANGESOPT_6 = "editor-genOpt6";
    public static final String KEY_EDITOR_GENCHANGESOPT_7 = "editor-genOpt7";

    public static final String KEY_EDITOR_BTNCHANGESOPT_1 = "editor-btnOpt1";
    public static final String KEY_EDITOR_BTNCHANGESOPT_2 = "editor-btnOpt2";
    public static final String KEY_EDITOR_BTNCHANGESOPT_3 = "editor-btnOpt3";
    public static final String KEY_EDITOR_BTNCHANGESOPT_4 = "editor-btnOpt4";
    public static final String KEY_EDITOR_BTNCHANGESOPT_5 = "editor-btnOpt5";
    public static final String KEY_EDITOR_BTNCHANGESOPT_6 = "editor-btnOpt6";
    public static final String KEY_EDITOR_BTNCHANGESOPT_7 = "editor-btnOpt7";
    public static final String KEY_EDITOR_BTNCHANGESOPT_8 = "editor-btnOpt8";
    public static final String KEY_EDITOR_BTNCHANGESOPT_9 = "editor-btnOpt9";

    public static final String KEY_EDITOR_USERLISTCHANGESOPT_1 = "editor-usrlistOpt1";
    public static final String KEY_EDITOR_USERLISTCHANGESOPT_2 = "editor-usrlistOpt2";
    public static final String KEY_EDITOR_USERLISTCHANGESOPT_3 = "editor-usrlistOpt3";

    public static final String KEY_EDITOR_USERTILECHANGESOPT_1 = "editor-usrtileOpt1";
    public static final String KEY_EDITOR_USERTILECHANGESOPT_2 = "editor-usrtileOpt2";
    public static final String KEY_EDITOR_USERTILECHANGESOPT_3 = "editor-usrtileOpt3";
    public static final String KEY_EDITOR_USERTILECHANGESOPT_4 = "editor-usrtileOpt4";

    public static final String KEY_EDITOR_FONTCHANGESOPT_1 = "editor-fontOpt1";
    public static final String KEY_EDITOR_FONTCHANGESOPT_2 = "editor-fontOpt2";
    public static final String KEY_EDITOR_FONTCHANGESOPT_3 = "editor-fontOpt3";
    public static final String KEY_EDITOR_FONTCHANGESOPT_4 = "editor-fontOpt4";
    public static final String KEY_EDITOR_FONTCHANGESOPT_5 = "editor-fontOpt5";
    public static final String KEY_EDITOR_FONTCHANGESOPT_6 = "editor-fontOpt6";
    public static final String KEY_EDITOR_FONTCHANGESOPT_7 = "editor-fontOpt7";
    public static final String KEY_EDITOR_FONTCHANGESOPT_8 = "editor-fontOpt8";
    public static final String KEY_EDITOR_FONTCHANGESOPT_9 = "editor-fontOpt9";
    public static final String KEY_EDITOR_FONTCHANGESOPT_10 = "editor-fontOpt10";
    public static final String KEY_EDITOR_FONTCHANGESOPT_11 = "editor-fontOpt11";
    public static final String KEY_EDITOR_FONTCHANGESOPT_12 = "editor-fontOpt12";
    public static final String KEY_EDITOR_FONTCHANGESOPT_13 = "editor-fontOpt13";
    public static final String KEY_EDITOR_FONTCHANGESOPT_14 = "editor-fontOpt14";

    public static final String KEY_EDITOR_MENUBAR_NEW = "editor-menubar-new";
    public static final String KEY_EDITOR_MENUBAR_SAVE = "editor-menubar-save";
    public static final String KEY_EDITOR_MENUBAR_SAVEAS = "editor-menubar-saveAs";
    public static final String KEY_EDITOR_MENUBAR_APPLY = "editor-menubar-apply";
    public static final String KEY_EDITOR_MENUBAR_TOCHOOSER = "editor-menubar-openChooser";

    public static final String KEY_MENUBAR_FILE = "menubar-file";
    public static final String KEY_MENUBAR_EXIT = "menubar-exit";
    public static final String KEY_MENUBAR_SETTINGS = "menubar-settings";
    public static final String KEY_MENUBAR_HELP = "menubar-help";
    public static final String KEY_MENUBAR_DOCS = "menubar-documentation";
    public static final String KEY_MENUBAR_BACKUP = "menubar-applyBackup";
    public static final String KEY_MENUBAR_WEB = "menubar-website";
    public static final String KEY_MENUBAR_ABOUT = "menubar-about";

    public static final String KEY_EDITOR_IMAGECHOOSERTITLE = "filechooser-title";
}
