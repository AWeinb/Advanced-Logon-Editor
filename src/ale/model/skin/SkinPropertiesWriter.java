/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import ale.model.skin.SkinConstants.CommandButton;
import ale.model.skin.SkinConstants.Comment;
import ale.model.skin.SkinConstants.Layout;
import ale.util.fileUtil.FileUtil;

/*
 * Writes the layout properties to a text file.
 */
final class SkinPropertiesWriter {

    private SkinPropertiesWriter() {
    }

    /*
     * Writes only the layout changes in a file.
     */
    protected static void write(SkinPropertiesVO properties, Path destinationPath) throws IOException {
        assert (properties != null) && (destinationPath != null);
        String is = SkinConstants.SPLIT;
        String sep = System.getProperty("line.separator");
        String tmp;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath.toString()))) {
            writer.append(Comment.MAIN + sep);

            writer.append(sep + Comment.LAYOUT + sep);

            writer.append(sep + Comment.LYT_BRANDING + sep);
            tmp = properties.getBrandingPosition() == null ? "" : properties.getBrandingPosition().name();
            writer.append(Layout.BRANDING_POSITION + is + tmp + sep);

            writer.append(sep + Comment.LYT_WINDOW + sep);
            tmp = properties.isWindowActive() + "";
            writer.append(Layout.WINDOW_ACTIVE + is + tmp + sep);
            tmp = properties.getWindow_Height() + "";
            writer.append(Layout.WINDOW_HEIGHT + is + tmp + sep);
            tmp = properties.getWindow_Width() + "";
            writer.append(Layout.WINDOW_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getWindow_Borderthickness());
            writer.append(Layout.WINDOW_BORDERTHICKNESS + is + tmp + sep);
            tmp = arrayToString_4(properties.getWindow_Padding());
            writer.append(Layout.WINDOW_PADDING + is + tmp + sep);
            tmp = properties.getWindow_Position() == null ? "" : properties.getWindow_Position().name();
            writer.append(Layout.WINDOW_POSITION + is + tmp + sep);
            tmp = properties.getWindow_Animation() == null ? "" : properties.getWindow_Animation().name();
            writer.append(Layout.WINDOW_ANIMATION + is + tmp + sep);

            writer.append(sep + Comment.LYT_BUTTONS + sep);
            writer.append(Comment.LYT_BUTTONS_ACC + sep);
            tmp = properties.getAccButtonHeight() + "";
            writer.append(Layout.ACCBTN_HEIGHT + is + tmp + sep);
            tmp = properties.getAccButtonWidth() + "";
            writer.append(Layout.ACCBTN_WIDTH + is + tmp + sep);
            tmp = properties.getAccButtonPosition() == null ? "" : properties.getAccButtonPosition().name();
            writer.append(Layout.ACCBTN_POSITION + is + tmp + sep);

            writer.append(sep + Comment.LYT_BUTTONS_COM + sep);
            tmp = properties.getCommandButtonHeight() + "";
            writer.append(Layout.COMBTN_MINHEIGHT + is + tmp + sep);
            tmp = properties.getCommandButtonWidth() + "";
            writer.append(Layout.COMBTN_MINWIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getCommandButtonBorderthickness());
            writer.append(Layout.COMBTN_BORDERTHICKNESS + is + tmp + sep);
            tmp = arrayToString_4(properties.getCommandButtonPadding());
            writer.append(Layout.COMBTN_PADDING + is + tmp + sep);
            tmp = arrayToString_4(properties.getCommandButtonMargin());
            writer.append(Layout.COMBTN_MARGIN + is + tmp + sep);
            writer.append(Layout.COMBTN_CONTENTALIGN + is + properties.getCommandButtonArrowpositionIsRight() + sep);
            writer.append(Layout.COMBTN_BTNVISIBILITY_SWITCH + is + properties.getCommandButtonVisibility(CommandButton.SWITCH) + sep);
            writer.append(Layout.COMBTN_BTNVISIBILITY_LOCK + is + properties.getCommandButtonVisibility(CommandButton.LOCK) + sep);
            writer.append(Layout.COMBTN_BTNVISIBILITY_LOGOUT + is + properties.getCommandButtonVisibility(CommandButton.LOGOUT) + sep);
            writer.append(Layout.COMBTN_BTNVISIBILITY_PASSWORD + is + properties.getCommandButtonVisibility(CommandButton.PASSWORD) + sep);
            writer.append(Layout.COMBTN_BTNVISIBILITY_TASKMANAGER + is + properties.getCommandButtonVisibility(CommandButton.TASKMANAGER)
                    + sep);

            writer.append(sep + Comment.LYT_BUTTONS_STD + sep);
            tmp = properties.getStandardButtonHeight() + "";
            writer.append(Layout.STDBTN_HEIGHT + is + tmp + sep);
            tmp = properties.getStandardButtonWidth() + "";
            writer.append(Layout.STDBTN_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getStandardButtonBorderthickness());
            writer.append(Layout.STDBTN_BORDERTHICKNESS + is + tmp + sep);
            tmp = properties.getStandardButtonPosition() == null ? "" : properties.getStandardButtonPosition() + "";
            writer.append(Layout.STDBTN_POSITION + is + tmp + sep);
            tmp = arrayToString_4(properties.getStandardButtonMargin());
            writer.append(Layout.STDBTN_MARGIN + is + tmp + sep);
            tmp = arrayToString_4(properties.getStandardButtonPadding());
            writer.append(Layout.STDBTN_PADDING + is + tmp + sep);

            writer.append(sep + Comment.LYT_BUTTONS_PW + sep);
            tmp = properties.getPasswordButtonHeight() + "";
            writer.append(Layout.PWBTN_HEIGHT + is + tmp + sep);
            tmp = properties.getPasswordButtonWidth() + "";
            writer.append(Layout.PWBTN_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getPasswordButtonBorderthickness());
            writer.append(Layout.PWBTN_BORDERTHICKNESS + is + tmp + sep);
            tmp = arrayToString_4(properties.getPasswordButtonMargin());
            writer.append(Layout.PWBTN_MARGIN + is + tmp + sep);

            writer.append(sep + Comment.LYT_BUTTONS_SHD + sep);
            tmp = properties.getShutdownButtonHeight() + "";
            writer.append(Layout.SHDBTN_HEIGHT + is + tmp + sep);
            tmp = properties.getShutdownButtonWidth() + "";
            writer.append(Layout.SHDBTN_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getShutdownButtonBorderthickness());
            writer.append(Layout.SHDBTN_BORDERTHICKNESS + is + tmp + sep);
            tmp = arrayToString_4(properties.getShutdownButtonMargin());
            writer.append(Layout.SHDBTN_MARGIN + is + tmp + sep);
            tmp = properties.getShutdownButtonPosition() == null ? "" : properties.getShutdownButtonPosition() + "";
            writer.append(Layout.SHDBTN_POSITION + is + tmp + sep);
            tmp = properties.getShutdownButtonContent() == null ? "" : properties.getShutdownButtonContent() + "";
            writer.append(Layout.SHDBTN_CONTENT + is + tmp + sep);

            writer.append(sep + Comment.LYT_BUTTONS_SHDMENU + sep);
            tmp = properties.getShutdownmenuButtonHeight() + "";
            writer.append(Layout.SHDMENU_HEIGHT + is + tmp + sep);
            tmp = properties.getShutdownmenuButtonWidth() + "";
            writer.append(Layout.SHDMENU_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getShutdownmenuButtonBorderthickness());
            writer.append(Layout.SHDMENU_BORDERTHICKNESS + is + tmp + sep);
            tmp = arrayToString_4(properties.getShutdownmenuButtonMargin());
            writer.append(Layout.SHDMENU_MARGIN + is + tmp + sep);
            tmp = properties.getShutdownmenuButtonPosition() == null ? "" : properties.getShutdownmenuButtonPosition() + "";
            writer.append(Layout.SHDMENU_POSITION + is + tmp + sep);
            tmp = properties.getShutdownmenuButtonContent() == null ? "" : properties.getShutdownmenuButtonContent() + "";
            writer.append(Layout.SHDMENU_CONTENT + is + tmp + sep);

            writer.append(Layout.SHDFRAME_LAYOUT + is + properties.getShutdownframeBorderlayout() + sep);
            tmp = properties.getShutdownframePosition() == null ? "" : properties.getShutdownframePosition() + "";
            writer.append(Layout.SHDFRAME_POSITION + is + tmp + sep);

            writer.append(Layout.ACCSHD_COMBINED + is + properties.getCombinedShdAcc() + sep);
            tmp = properties.getCombinedShdAccPosition() == null ? "" : properties.getCombinedShdAccPosition() + "";
            writer.append(Layout.ACCSHD_POSITION + is + tmp + sep);

            writer.append(sep + Comment.LYT_BUTTONS_LOCALE + sep);
            writer.append(Layout.LOCALEBTN_VISIBILITY + is + properties.getLocaleButtonVisible() + sep);
            tmp = properties.getLocaleButtonPosition() == null ? "" : properties.getLocaleButtonPosition().name();
            writer.append(Layout.LOCALEBTN_POSITION + is + tmp + sep);
            tmp = arrayToString_4(properties.getLocaleButtonPadding());
            writer.append(Layout.LOCALEBTN_PADDING + is + tmp + sep);

            writer.append(sep + Comment.LYT_SECURITYOPTIONS + sep);
            tmp = properties.getSecurityMenu_Position() == null ? "" : properties.getSecurityMenu_Position().name();
            writer.append(Layout.SECURITYOPTIONS_POSITION + is + tmp + sep);
            tmp = arrayToString_4(properties.getSecurityMenu_Padding());
            writer.append(Layout.SECURITYOPTIONS_PADDING + is + tmp + sep);
            tmp = properties.getWindow_InnerAnimation() == null ? "" : properties.getWindow_InnerAnimation().name();
            writer.append(Layout.WINDOW_INNERANIMATION + is + tmp + sep);

            writer.append(sep + Comment.LYT_OPTIONSBAR + sep);
            tmp = properties.getOptionsbarHeight() + "";
            writer.append(Layout.OPTIONSBAR_HEIGHT + is + tmp + sep);
            writer.append(Layout.ACC_FIX + is + properties.getWrapAccInNewElement() + sep);

            writer.append(sep + Comment.LYT_USERLIST + sep);
            tmp = properties.getUserlistHeight() + "";
            writer.append(Layout.USERLIST_HEIGHT + is + tmp + sep);
            writer.append(Layout.USERLIST_LAYOUT + is + properties.getUserlistVertical() + sep);
            tmp = properties.getUserlistPosition() == null ? "" : properties.getUserlistPosition().name();
            writer.append(Layout.USERLIST_POSITION + is + tmp + sep);
            tmp = arrayToString_4(properties.getUserlistPadding());
            writer.append(Layout.USERLIST_PADDING + is + tmp + sep);

            writer.append(sep + Comment.LYT_USERLIST_IMAGE + sep);
            tmp = properties.getUserlistImageHeight() + "";
            writer.append(Layout.USERLIST_IMAGE_HEIGHT + is + tmp + sep);
            tmp = properties.getUserlistImageWidth() + "";
            writer.append(Layout.USERLIST_IMAGE_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getUserlistImagePadding());
            writer.append(Layout.USERLIST_IMAGE_PADDING + is + tmp + sep);
            tmp = properties.getUserlistImageFrameHeight() + "";
            writer.append(Layout.USERLIST_IMAGEFRAME_HEIGHT + is + tmp + sep);
            tmp = properties.getUserlistImageFrameWidth() + "";
            writer.append(Layout.USERLIST_IMAGEFRAME_WIDTH + is + tmp + sep);

            writer.append(sep + Comment.LYT_USERTILE + sep);
            tmp = properties.getUsertileLayoutIsHorizontal() + "";
            writer.append(Layout.USERTILE_LAYOUT + is + tmp + sep);
            tmp = properties.getUsertilePosition() == null ? "" : properties.getUsertilePosition().name();
            writer.append(Layout.USERTILE_POSITION + is + tmp + sep);
            writer.append(Layout.USERTILE_PWAREAONRIGHT + is + properties.getPWAreaPositionOnRightOfTexts() + sep);
            writer.append(Layout.USERTILE_STATUSONRIGHT + is + properties.getStatusOnRightSide() + sep);
            tmp = properties.getUsertileImagePosition() == null ? "" : properties.getUsertileImagePosition().name();
            writer.append(Layout.USERTILE_IMAGE_POSITION + is + tmp + sep);

            writer.append(sep + Comment.LYT_USERTILE_IMAGE + sep);
            tmp = properties.getUsertileImageHeight() + "";
            writer.append(Layout.USERTILE_IMAGE_HEIGHT + is + tmp + sep);
            tmp = properties.getUsertileImageWidth() + "";
            writer.append(Layout.USERTILE_IMAGE_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getUsertileImagePadding());
            writer.append(Layout.USERTILE_IMAGE_PADDING + is + tmp + sep);
            tmp = properties.getUsertileImageFrameHeight() + "";
            writer.append(Layout.USERTILE_IMAGEFRAME_HEIGHT + is + tmp + sep);
            tmp = properties.getUsertileImageFrameWidth() + "";
            writer.append(Layout.USERTILE_IMAGEFRAME_WIDTH + is + tmp + sep);

            writer.append(sep + Comment.LYT_PWFIELD + sep);
            tmp = properties.getPWfieldHeight() + "";
            writer.append(Layout.PWFIELD_HEIGHT + is + tmp + sep);
            tmp = properties.getPWfieldWidth() + "";
            writer.append(Layout.PWFIELD_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getPWfieldBorderthickness());
            writer.append(Layout.PWFIELD_BORDERTHICKNESS + is + tmp + sep);
            tmp = arrayToString_4(properties.getPWfieldMargin());
            writer.append(Layout.PWFIELD_MARGIN + is + tmp + sep);

            writer.append(sep + Comment.LYT_SHIFTS + sep);
            tmp = properties.getPasswordfieldUpshift() + "";
            writer.append(Layout.PWAREA_UPSHIFT + is + tmp + sep);
            tmp = properties.getPasswordfieldDownshift() + "";
            writer.append(Layout.PWAREA_DOWNSHIFT + is + tmp + sep);
            tmp = properties.getPasswordfieldRightshift() + "";
            writer.append(Layout.PWAREA_RIGHTSHIFT + is + tmp + sep);

            //

            writer.append(sep + Comment.LYT_LOADINGSTATUS + sep);
            tmp = properties.getLoadingStatusWidth() + "";
            writer.append(Layout.LOADINGSTATUS_WIDTH + is + tmp + sep);
            tmp = arrayToString_4(properties.getLoadingStatusBorderthickness());
            writer.append(Layout.LOADINGSTATUS_BORDERTHICKNESS + is + tmp + sep);
            tmp = properties.getLoadingStatusAnimation() == null ? "" : properties.getLoadingStatusAnimation().name();
            writer.append(Layout.LOADINGSTATUS_ANIMATION + is + tmp + sep);

            //

            writer.append(sep + Comment.LYT_FONTSHADOW + sep);
            tmp = properties.getShadowIntensity() + "";
            writer.append(Layout.FONTSHADOW + is + tmp + sep);

            // fonts -->

            writer.append(sep + Comment.LYT_FONT_SHD + sep);
            tmp = properties.getShutdownFont() == null ? "" : properties.getShutdownFont();
            writer.append(Layout.SHD_FONT + is + tmp + sep);
            tmp = properties.getShutdownFontsize() + "";
            writer.append(Layout.SHD_FONTSIZE + is + tmp + sep);
            tmp = properties.getShutdownFonttype() == null ? "" : properties.getShutdownFonttype().name();
            writer.append(Layout.SHD_FONTTYPE + is + tmp + sep);
            tmp = properties.getShutdownFontstyle() == null ? "" : properties.getShutdownFontstyle().name();
            writer.append(Layout.SHD_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getShutdownFontcolor());
            writer.append(Layout.SHD_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_FONT_SHDMENU + sep);
            tmp = properties.getShutdownMenuFont() == null ? "" : properties.getShutdownMenuFont();
            writer.append(Layout.SHDMENU_FONT + is + tmp + sep);
            tmp = properties.getShutdownMenuFontsize() + "";
            writer.append(Layout.SHDMENU_FONTSIZE + is + tmp + sep);
            tmp = properties.getShutdownMenuFonttype() == null ? "" : properties.getShutdownMenuFonttype().name();
            writer.append(Layout.SHDMENU_FONTTYPE + is + tmp + sep);
            tmp = properties.getShutdownMenuFontstyle() == null ? "" : properties.getShutdownMenuFontstyle().name();
            writer.append(Layout.SHDMENU_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getShutdownMenuFontcolor());
            writer.append(Layout.SHDMENU_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_FONT_PW + sep);
            tmp = properties.getPWFieldFont() == null ? "" : properties.getPWFieldFont();
            writer.append(Layout.PWFIELD_FONT + is + tmp + sep);
            tmp = properties.getPWFieldFontsize() + "";
            writer.append(Layout.PWFIELD_FONTSIZE + is + tmp + sep);
            tmp = properties.getPWFieldFonttype() == null ? "" : properties.getPWFieldFonttype().name();
            writer.append(Layout.PWFIELD_FONTTYPE + is + tmp + sep);
            tmp = properties.getPWFieldFontstyle() == null ? "" : properties.getPWFieldFontstyle().name();
            writer.append(Layout.PWFIELD_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getPWFieldFontcolor());
            writer.append(Layout.PWFIELD_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_FONT_RESETPW + sep);
            tmp = properties.getPWResetFont() == null ? "" : properties.getPWResetFont();
            writer.append(Layout.RESETPW_FONT + is + tmp + sep);
            tmp = properties.getPWResetFontsize() + "";
            writer.append(Layout.RESETPW_FONTSIZE + is + tmp + sep);
            tmp = properties.getPWResetFonttype() == null ? "" : properties.getPWResetFonttype().name();
            writer.append(Layout.RESETPW_FONTTYPE + is + tmp + sep);
            tmp = properties.getPWResetFontstyle() == null ? "" : properties.getPWResetFontstyle().name();
            writer.append(Layout.RESETPW_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getPWResetFontcolor());
            writer.append(Layout.RESETPW_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_FONT_LOADINGSTATUS + sep);
            tmp = properties.getLoadingStatusFont() == null ? "" : properties.getLoadingStatusFont();
            writer.append(Layout.LOADINGSTATUS_FONT + is + tmp + sep);
            tmp = properties.getLoadingStatusFontsize() + "";
            writer.append(Layout.LOADINGSTATUS_FONTSIZE + is + tmp + sep);
            tmp = properties.getLoadingStatusFonttype() == null ? "" : properties.getLoadingStatusFonttype().name();
            writer.append(Layout.LOADINGSTATUS_FONTTYPE + is + tmp + sep);
            tmp = properties.getLoadingStatusFontstyle() == null ? "" : properties.getLoadingStatusFontstyle().name();
            writer.append(Layout.LOADINGSTATUS_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getLoadingStatusFontcolor());
            writer.append(Layout.LOADINGSTATUS_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_FONT_SHORTMSG + sep);
            tmp = properties.getShortMessageFont() == null ? "" : properties.getShortMessageFont();
            writer.append(Layout.SHORTMESSAGE_FONT + is + tmp + sep);
            tmp = properties.getShortMessageFontsize() + "";
            writer.append(Layout.SHORTMESSAGE_FONTSIZE + is + tmp + sep);
            tmp = properties.getShortMessageFonttype() == null ? "" : properties.getShortMessageFonttype().name();
            writer.append(Layout.SHORTMESSAGE_FONTTYPE + is + tmp + sep);
            tmp = properties.getShortMessageFontstyle() == null ? "" : properties.getShortMessageFontstyle().name();
            writer.append(Layout.SHORTMESSAGE_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getShortMessageFontcolor());
            writer.append(Layout.SHORTMESSAGE_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_FONT_COM + sep);
            tmp = properties.getCommandButtonFont() == null ? "" : properties.getCommandButtonFont();
            writer.append(Layout.COMBTN_FONT + is + tmp + sep);
            tmp = properties.getCommandButtonFontsize() + "";
            writer.append(Layout.COMBTN_FONTSIZE + is + tmp + sep);
            tmp = properties.getCommandButtonFonttype() == null ? "" : properties.getCommandButtonFonttype().name();
            writer.append(Layout.COMBTN_FONTTYPE + is + tmp + sep);
            tmp = properties.getCommandButtonFontstyle() == null ? "" : properties.getCommandButtonFontstyle().name();
            writer.append(Layout.COMBTN_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getCommandButtonFontcolor());
            writer.append(Layout.COMBTN_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_FONT_STD + sep);
            tmp = properties.getBtn_Std_Font() == null ? "" : properties.getBtn_Std_Font();
            writer.append(Layout.STDBTN_FONT + is + tmp + sep);
            tmp = properties.getBtn_Std_Fontsize() + "";
            writer.append(Layout.STDBTN_FONTSIZE + is + tmp + sep);
            tmp = properties.getBtn_Std_Fonttype() == null ? "" : properties.getBtn_Std_Fonttype().name();
            writer.append(Layout.STDBTN_FONTTYPE + is + tmp + sep);
            tmp = properties.getBtn_Std_Fontstyle() == null ? "" : properties.getBtn_Std_Fontstyle().name();
            writer.append(Layout.STDBTN_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getBtn_Std_Fontcolor());
            writer.append(Layout.STDBTN_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_STATUSFONT + sep);
            tmp = properties.getStatustextFont() == null ? "" : properties.getStatustextFont();
            writer.append(Layout.STATUSTEXT_FONT + is + tmp + sep);
            tmp = properties.getStatustextFontsize() + "";
            writer.append(Layout.STATUSTEXT_FONTSIZE + is + tmp + sep);
            tmp = properties.getStatustextFonttype() == null ? "" : properties.getStatustextFonttype().name();
            writer.append(Layout.STATUSTEXT_FONTTYPE + is + tmp + sep);
            tmp = properties.getStatustextFontstyle() == null ? "" : properties.getStatustextFontstyle().name();
            writer.append(Layout.STATUSTEXT_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getStatustextFontcolor());
            writer.append(Layout.STATUSTEXT_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_STATUSFONT_Z + sep);
            tmp = properties.getStatustextZoomedFont() == null ? "" : properties.getStatustextZoomedFont();
            writer.append(Layout.STATUSTEXTZOOMED_FONT + is + tmp + sep);
            tmp = properties.getStatustextZoomedFontsize() + "";
            writer.append(Layout.STATUSTEXTZOOMED_FONTSIZE + is + tmp + sep);
            tmp = properties.getStatustextZoomedFonttype() == null ? "" : properties.getStatustextZoomedFonttype().name();
            writer.append(Layout.STATUSTEXTZOOMED_FONTTYPE + is + tmp + sep);
            tmp = properties.getStatustextZoomedFontstyle() == null ? "" : properties.getStatustextZoomedFontstyle().name();
            writer.append(Layout.STATUSTEXTZOOMED_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getStatustextZoomedFontcolor());
            writer.append(Layout.STATUSTEXTZOOMED_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_NAMEFONT + sep);
            tmp = properties.getNametextFont() == null ? "" : properties.getNametextFont();
            writer.append(Layout.NAMETEXT_FONT + is + tmp + sep);
            tmp = properties.getNametextFontsize() + "";
            writer.append(Layout.NAMETEXT_FONTSIZE + is + tmp + sep);
            tmp = properties.getNametextFonttype() == null ? "" : properties.getNametextFonttype().name();
            writer.append(Layout.NAMETEXT_FONTTYPE + is + tmp + sep);
            tmp = properties.getNametextFontstyle() == null ? "" : properties.getNametextFontstyle().name();
            writer.append(Layout.NAMETEXT_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getNametextFontcolor());
            writer.append(Layout.NAMETEXT_FOREGROUND + is + tmp + sep);

            writer.append(sep + Comment.LYT_NAMEFONT_Z + sep);
            tmp = properties.getNametextZoomedFont() == null ? "" : properties.getNametextZoomedFont();
            writer.append(Layout.NAMETEXTZOOMED_FONT + is + tmp + sep);
            tmp = properties.getNametextZoomedFontsize() + "";
            writer.append(Layout.NAMETEXTZOOMED_FONTSIZE + is + tmp + sep);
            tmp = properties.getNametextZoomedFonttype() == null ? "" : properties.getNametextZoomedFonttype().name();
            writer.append(Layout.NAMETEXTZOOMED_FONTTYPE + is + tmp + sep);
            tmp = properties.getNametextZoomedFontstyle() == null ? "" : properties.getNametextZoomedFontstyle().name();
            writer.append(Layout.NAMETEXTZOOMED_FONTSTYLE + is + tmp + sep);
            tmp = arrayToString_4(properties.getNametextZoomedFontcolor());
            writer.append(Layout.NAMETEXTZOOMED_FOREGROUND + is + tmp + sep);

            writer.flush();
            writer.close();

        } catch (IOException e) {
            throw e;
        }
        assert FileUtil.control(destinationPath);
    }

    private static String arrayToString_4(int[] a) {
        String ret;

        if (a == null) {
            ret = "";

        } else {
            ret = a[0] + SkinConstants.SPLIT2 + a[1] + SkinConstants.SPLIT2 + a[2] + SkinConstants.SPLIT2 + a[3] + SkinConstants.SPLIT2;
        }

        return ret;
    }
}
