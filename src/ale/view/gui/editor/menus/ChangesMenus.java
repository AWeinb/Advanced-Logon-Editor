/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.menus;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ale.model.skin.SkinPropertiesVO;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;

public abstract class ChangesMenus {

    protected static SkinPropertiesVO skin;

    protected static String strImgFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_IMAGE);
    protected static String strPosFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_POSITION);
    protected static String strHideFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_HIDE);
    protected static String strSizeFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_SIZE);
    protected static String strBorderFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_BORDER);
    protected static String strPaddingFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_PADDING);
    protected static String strMarginFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_MARGIN);
    protected static String strContentFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_CONTENT);
    protected static String strFontFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_FONT);
    protected static String strUserlistVTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_ULISTVERT);
    protected static String strComSymbolRightTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_COMSYM_R);
    protected static String strActivateWindowTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_ACTIVATEWINDOW);
    protected static String strUsertileHorizontalFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_UTILEHOR);
    protected static String strUsertilePWRightOfTextsFieldTitle = GUIStrings
            .keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_UTILE_PWRIGHT);
    protected static String strUsertileStatusOnRightFieldTitle = GUIStrings
            .keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_UTILE_STATUSRIGHT);
    protected static String strCombineAccShdTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_COMBINEACCSHD);
    protected static String strCombinedAccShdPosTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_COMBINEACCSHD_POS);
    protected static String strAnimationFieldTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FIELDTITLE_ANIM);

    protected static String strImageFocus = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_IMGFOCUS);
    protected static String strImageDefault = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_IMGDEFAULT);
    protected static String strImageDisabled = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_IMGDISABLED);
    protected static String strImagePressed = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_IMGPRESSED);
    protected static String strImageSelected = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_IMGSELECTED);

    protected static String strHide = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_HIDE);
    protected static String strMinSize = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MINSIZE);
    protected static String strAccessSym = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_ACCSYM);
    protected static String strComSym = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_COMSYM);
    protected static String strComSymRight = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_COMSYM_R);
    protected static String strUserlistVBtn = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERLISTVERT_BTN);
    protected static String strUserimageOverlay = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERIMG_OVERLAY);
    protected static String strWindowInnerAnimation = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_WINDOW_INNERANIM);
    protected static String strWindowActiveLabel = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_ACTIVATEWINDOW);
    protected static String strUsertileHorizontalLabel = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_UTILE_HOR);
    protected static String strUsertilePWRightOfTextLabel = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_UTILE_PWRIGHT);
    protected static String strUsertileStatusOnRightLabel = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_UTILE_STATUSRIGHT);
    protected static String strCombineAccShdLabel = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_COMBINEACCSHD);
    protected static String strShdSym = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SHDSYM);
    protected static String strShdUpdateSym = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SHDUPDATESYM);
    protected static String strShdArrowSym = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SHDARROWSYM);
    protected static String strPWAreaUpShift = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_PWAREA_UPSHIFT);
    protected static String strPWAreaDownShift = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_PWAREA_DOWNSHIFT);
    protected static String strPWAreaRightShift = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_PWAREA_RIGHTSHIFT);
    protected static String strPWAreaShiftamount = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_PWAREA_SHIFTAMOUNT);
    protected static String strUserlistPaddingHint = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_ULISTPADDING_HINT);

    protected static String strSliderBtn = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SLIDERBTN);
    protected static String strSliderBar = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SLIDERBAR);
    protected static String strSliderDown = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SLIDERDOWN);
    protected static String strSliderUp = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_SLIDERUP);

    protected static String strNumfieldHeight = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_HEIGHT);
    protected static String strNumfieldWidth = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_WIDTH);

    protected static String strFontshadow = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTSHADOW);
    protected static String strFontshadowIntensity = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTSHADOWINTENSITY);

    protected static String strCommandBtn_lock = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_CMDBTN_LOCK);
    protected static String strCommandBtn_logout = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_CMDBTN_LOGOUT);
    protected static String strCommandBtn_passwd = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_CMDBTN_PASSWD);
    protected static String strCommandBtn_switch = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_CMDBTN_SWITCH);
    protected static String strCommandBtn_taskman = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_CMDBTN_TASKMAN);

    protected static String strLoadingRinganim = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_RINGANIM);

    protected ChangesMenus() {
    }

    public static void initialize(Color bg, SkinPropertiesVO skin) {
        ChangesMenus.skin = skin;

        ButtonChangesMenu.initialize(bg);
        FontChangesMenu.initialize(bg);
        GeneralChangesMenu.initialize(bg);
        UserlistChangesMenu.initialize(bg);
        UsertileChangesMenu.initialize(bg);

        while (!ButtonChangesMenu.isInitialized() && !FontChangesMenu.isInitialized() && !GeneralChangesMenu.isInitialized()
                && !UserlistChangesMenu.isInitialized() && !UsertileChangesMenu.isInitialized()) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected static JTabbedPane createTabbedPane(Color bg, String title, JPanel... jPanels) {
        JTabbedPane tabPane = null;

        if (jPanels != null) {
            tabPane = new JTabbedPane();
            tabPane.setBackground(bg);
            tabPane.setPreferredSize(GUIConstants.DEFAULT_TAPPEDPANE_DIM);
            tabPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(bg), title));

            for (JPanel p : jPanels) {
                tabPane.add(p);
            }
        }

        return tabPane;
    }
}
