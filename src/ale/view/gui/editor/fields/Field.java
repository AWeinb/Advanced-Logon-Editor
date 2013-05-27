/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.fields;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ale.Constants;
import ale.view.gui.util.GUIStrings;

abstract class Field extends JPanel {

    protected static String strFileChooserTitle = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_IMAGECHOOSERTITLE);
    protected static String strToggleTransparent = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_TOGGLETRANSPARENT);
    protected static String strReset = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_RESET);
    protected static String strHide = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_HIDE);

    protected static String[] strPosTooltip = { GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_TOPLEFT),
            GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_TOP),
            GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_TOPRIGHT),
            GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_LEFT), GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_CENTER),
            GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_RIGHT),
            GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_BOTTOMLEFT),
            GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_BOTTOM),
            GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_POS_BOTTOMRIGHT) };
    protected static String strWidthTooltip = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_WIDTH);
    protected static String strHeightTooltip = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_HEIGHT);
    protected static String strBorderTooltip = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_BORDERTOOLTIP);
    protected static String strPaddingTooltip = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_PADDINGTOOLTIP);
    protected static String strMarginTooltip = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_MARGINTOOLTIP);

    protected static String strFontsizeLabel = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTSIZE);
    protected static String strFontColorBtn = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTCOLOR);
    protected static String strBoldBtn = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTBOLD);
    protected static String strUnderlineBtn = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTUNDERLINE);
    protected static String strShadowBtn = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_FONTSHADOW);

    protected static String strUserlistFontStatus = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERLIST_STATUSFONT);
    protected static String strUserlistFontName = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_USERLIST_NAMEFONT);
    protected static String strAnimationHorizontal = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_ANIM_H);
    protected static String strAnimationVertical = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_ANIM_V);
    protected static String strAnimationSlow = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_ANIM_SLOW);
    protected static String strAnimationFast = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_ANIM_FAST);
    protected static String strAnimationOriginal = GUIStrings.keyToLocatedString(GUIStrings.KEY_EDITOR_ANIM_NONE);

    private static final long serialVersionUID = 1L;
    public static final int FAILURE = -1;

    protected boolean controlSizeInput(JTextField textfield) {
        boolean ret = true;
        String tmp = textfield.getText();

        if (!tmp.matches(Constants.SKIN_NUMBER_REGEX)) {
            ret = false;
        }

        return ret;
    }

    protected boolean controlSizeInputLength(JTextField textfield, int maxLength) {
        boolean ret = true;
        String tmp = textfield.getText();

        if (tmp.length() > maxLength) {
            textfield.setText(tmp.substring(0, maxLength));
            ret = false;
        }

        return ret;
    }

    protected boolean controlThicknessInput(JTextField textfield, int maxLength, boolean negativeAllowed) {
        boolean ret = true;
        String tmp = textfield.getText();

        if (negativeAllowed && tmp.matches("-")) {
            ret = false;

        } else if ((!negativeAllowed && !tmp.matches("[0-9]++")) || (negativeAllowed && !tmp.matches("-?[0-9]++"))) {
            textfield.setText("");
            ret = false;

        } else {
            if (tmp.length() > maxLength) {
                textfield.setText(tmp.substring(0, maxLength));
                ret = false;
            }
        }

        return ret;
    }

    protected int parseInt(String input) {
        int i;

        if (input == null) {
            i = FAILURE;
        } else {
            try {
                i = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                i = FAILURE;
            }
        }

        return i;
    }
}
