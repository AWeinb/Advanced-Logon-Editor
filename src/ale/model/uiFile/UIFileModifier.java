/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.uiFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import ale.model.dllResource.DLLResource;
import ale.model.dllResource.ResourceVO;
import ale.model.dllResource.ResourceVO.ResourceType;
import ale.model.skin.BrandingVO;
import ale.model.skin.BrandingVO.Brandingsize;
import ale.model.skin.SkinConstants;
import ale.model.skin.SkinConstants.CommandButton;
import ale.model.skin.SkinConstants.Imagetype;
import ale.model.skin.SkinConstants.UIAnimationInits;
import ale.model.skin.SkinConstants.UIBorderthicknessInits;
import ale.model.skin.SkinConstants.UIFontInits;
import ale.model.skin.SkinConstants.UIMarginInits;
import ale.model.skin.SkinConstants.UIPaddingInits;
import ale.model.skin.SkinConstants.UIPositionInits;
import ale.model.skin.SkinConstants.UIResNumbers;
import ale.model.skin.SkinConstants.UIShiftInits;
import ale.model.skin.SkinConstants.UISizeInits;
import ale.model.skin.SkinPropertiesVO;
import ale.model.uiFile.UIFileConstants.UILayout;
import ale.model.uiFile.UIFileConstants.UIPositions;
import ale.model.uiFile.UIFileConstants.UIPositions.Usertile;
import ale.model.uiFile.UIFileConstants.UIRegex;

/*
 * Modifies the UI text file before it is inserted in the authui tmp.
 */
class UIFileModifier {

    private DLLResource resource;
    private SkinPropertiesVO props;

    private Path authuiTmp;
    private Path basebrdTmp;

    protected UIFileModifier(Path script, Path authuiTmp, Path basebrdTmp) {
        this.authuiTmp = authuiTmp;
        this.basebrdTmp = basebrdTmp;

        this.resource = new DLLResource(script);
    }

    protected DLLResource modifyUIFile(SkinPropertiesVO properties, UIFileTextVO uifile) throws IOException, InterruptedException {
        assert (properties != null) && (uifile != null);
        this.props = properties;

        applyBranding();
        applyBackground();
        applyImages();
        applyLayout(uifile);

        return this.resource;
    }

    private static void addImgPath(List<ResourceVO> list, Path img) {
        if (img != null) {
            int num = Integer.parseInt(img.getFileName().toString().split("\\.")[0]);
            list.add(new ResourceVO(img, ResourceType.BITMAP, num));
        }
    }

    private static String intArToString(int[] ar) {
        String tmp = "\"" + UIFileConstants.UIRegex.RECT + "(";
        tmp += ar[0];
        tmp += UIRegex.RP + ",";
        tmp += ar[1];
        tmp += UIRegex.RP + ",";
        tmp += ar[2];
        tmp += UIRegex.RP + ",";
        tmp += ar[3];
        tmp += UIRegex.RP + ")\"";
        return tmp;
    }

    private static String intArToColor(int[] ar) {
        assert ar.length == 4;

        String tmp = UIRegex.ARGB + "(";
        for (int i = 0; i < ar.length; i++) {
            tmp += ar[i];

            if (i != (ar.length - 1)) {
                tmp += ",";
            }
        }
        tmp += ")";
        return tmp;
    }

    private void applyBranding() throws IOException, InterruptedException {
        BrandingVO brd = this.props.getBranding();
        assert brd != null;

        List<ResourceVO> res = new LinkedList<ResourceVO>();
        res.add(new ResourceVO(brd.getImage(Brandingsize.SMALL), ResourceType.BITMAP, UIResNumbers.BRANDING_SMALL.getNum()));
        res.add(new ResourceVO(brd.getImage(Brandingsize.MEDIUM), ResourceType.BITMAP, UIResNumbers.BRANDING_MEDIUM.getNum()));
        res.add(new ResourceVO(brd.getImage(Brandingsize.LARGE), ResourceType.BITMAP, UIResNumbers.BRANDING_BIG.getNum()));

        this.resource.addResources(this.basebrdTmp, res);
    }

    private void applyBackground() throws IOException, InterruptedException {
        if (this.props.getBackgroundImgChanged()) {
            UIBackground.setBackground(this.props.getBackground());
            assert this.props.getBackground() != null;
        } else {
            UIBackground.disableBackgrounds();
        }
    }

    private void applyImages() throws IOException, InterruptedException {
        List<ResourceVO> res = new LinkedList<ResourceVO>();
        Path tmp;

        tmp = this.props.getImgPath_Window();
        addImgPath(res, tmp);

        {
            tmp = this.props.getImgPath_PWField(Imagetype.DISABLED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_PWField(Imagetype.KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_PWField(Imagetype.MOUSEFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_PWField(Imagetype.DEFAULT);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_AccessSym();
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownArrowSym();
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownSym();
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownUpdateSym();
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_SliderArrowDown(Imagetype.DEFAULT);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_SliderArrowDown(Imagetype.FOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_SliderArrowDown(Imagetype.PRESSED);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_SliderArrowUp(Imagetype.DEFAULT);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_SliderArrowUp(Imagetype.FOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_SliderArrowUp(Imagetype.PRESSED);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_SliderBar(Imagetype.DEFAULT);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_SliderBar(Imagetype.FOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_SliderBar(Imagetype.PRESSED);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_SliderMidBtn(Imagetype.DEFAULT);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_SliderMidBtn(Imagetype.FOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_SliderMidBtn(Imagetype.PRESSED);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_UserlistImage(Imagetype.MOUSEFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_UserlistImage(Imagetype.SELECTED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_UserlistImage(Imagetype.FOCUSSELECTED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_UserlistImage(Imagetype.DEFAULT);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_UsertileImage();
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_CommandBtnArrow(Imagetype.KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_CommandBtnArrow(Imagetype.MOUSEFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_CommandBtnArrow(Imagetype.PRESSED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_CommandBtnArrow(Imagetype.DEFAULT);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_CommandBtn(Imagetype.KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_CommandBtn(Imagetype.MOUSEFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_CommandBtn(Imagetype.PRESSED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_CommandBtn(Imagetype.DEFAULT);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_StandardBtn(Imagetype.KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_StandardBtn(Imagetype.MOUSEFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_StandardBtn(Imagetype.MOUSEFOCUS_KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_StandardBtn(Imagetype.PRESSED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_StandardBtn(Imagetype.DEFAULT);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_PWBtn(Imagetype.MOUSEFOCUS_KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_PWBtn(Imagetype.PRESSED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_PWBtn(Imagetype.DEFAULT);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_ShutdownBtn(Imagetype.MOUSEFOCUS_KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownBtn(Imagetype.KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownBtn(Imagetype.MOUSEFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownBtn(Imagetype.PRESSED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownBtn(Imagetype.DEFAULT);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPath_ShutdownMenu(Imagetype.MOUSEFOCUS_KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownMenu(Imagetype.KEYFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownMenu(Imagetype.MOUSEFOCUS);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownMenu(Imagetype.PRESSED);
            addImgPath(res, tmp);

            tmp = this.props.getImgPath_ShutdownMenu(Imagetype.DEFAULT);
            addImgPath(res, tmp);
        }

        {
            tmp = this.props.getImgPathLoadingStatusBg();
            addImgPath(res, tmp);
        }

        this.resource.addResources(this.authuiTmp, res);
    }

    /*
     * Method replaces some lines with changed lines.
     */
    private void applyLayout(UIFileTextVO ui) {
        assert (ui != null) && (ui.getUIFileSize() > 0);
        String line;
        String replace = null;
        String tmp = null;

        // Window (Usertile backgr.)
        {
            // Only if window is visible/ active
            if (this.props.isWindowActive()) {
                line = ui.getLine(UILayout.WINDOW_MISC.getLine() - 1);
                tmp = UILayout.WINDOW_MISC.getRegex();
                tmp += UIRegex.HEIGHT + "\"" + this.props.getWindow_Height() + UIRegex.RP + "\"";
                tmp += UIRegex.WIDTH + "\"" + this.props.getWindow_Width() + UIRegex.RP + "\"";
                // Border
                if (this.props.getWindow_Borderthickness() != UIBorderthicknessInits.WINDOW.getBorderthickness()) {
                    tmp += UIRegex.BORDERTHICKNESS + intArToString(this.props.getWindow_Borderthickness());
                }
                // Padding
                if (this.props.getWindow_Padding() != null) {
                    tmp += UIRegex.PADDING + intArToString(this.props.getWindow_Padding());
                }
                // Background
                if (this.props.getWindowImgChanged()) {
                    tmp += UIRegex.BACKGROUND.toString().replaceFirst(UIRegex.REPLACE_HELPER.toString(), UIResNumbers.WINDOW.getNum() + "");
                }
                // Animation
                if (this.props.getWindow_Animation() != UIAnimationInits.WINDOW.getAnimation()) {
                    tmp += UIRegex.ANIMATION + "\"" + this.props.getWindow_Animation() + "\"";
                }
                // All(Some) in one apply
                replace = line.replaceFirst(UILayout.WINDOW_MISC.getRegex(), tmp);
                ui.setLine(replace, UILayout.WINDOW_MISC.getLine() - 1);

                // Inner Animation : Button in window
                if (this.props.getWindow_InnerAnimation() != UIAnimationInits.INNERWINDOW.getAnimation()) {
                    tmp = UIRegex.ANIMATION + "\"" + this.props.getWindow_InnerAnimation() + "\""
                            + UILayout.WINDOW_INNERANIMATION.getRegex();

                    line = ui.getLine(UILayout.WINDOW_INNERANIMATION.getLine() - 1);
                    replace = line.replaceFirst(UILayout.WINDOW_INNERANIMATION.getRegex(), tmp);
                    ui.setLine(replace, UILayout.WINDOW_INNERANIMATION.getLine() - 1);
                }
                // Position
                if (this.props.getWindow_Position() != null) {
                    line = ui.getLine(UILayout.WINDOW_POSITION.getLine() - 1);
                    String p = UIPositions.Window.valueOf(this.props.getWindow_Position().name()).getString();
                    replace = line.replaceFirst(UILayout.WINDOW_POSITION.getRegex(), p);
                    ui.setLine(replace, UILayout.WINDOW_POSITION.getLine() - 1);
                }
            }
        }

        // Strg-Alt-Entf Menu
        {
            // Position
            if (this.props.getSecurityMenu_Position() != UIPositionInits.SECURITYMENU.getPosition()) {
                String p = UIPositions.SecurityOptions.valueOf(this.props.getSecurityMenu_Position().name()).getString();
                tmp = "\"" + UIRegex.VERTFLOWLAYOUT + p + "\"";

                // Padding A
                if (this.props.getSecurityMenu_Padding() != null) {
                    tmp += " " + UIRegex.PADDING + intArToString(this.props.getSecurityMenu_Padding());
                }

                line = ui.getLine(UILayout.SECURITYMENU_POSITION.getLine() - 1);
                replace = line.replaceFirst(UILayout.SECURITYMENU_POSITION.getRegex(), tmp);
                ui.setLine(replace, UILayout.SECURITYMENU_POSITION.getLine() - 1);

            } else {
                // Padding B
                if (this.props.getSecurityMenu_Padding() != null) {
                    tmp = UILayout.SECURITYMENU_POSITION.getRegex() + " " + UIRegex.PADDING
                            + intArToString(this.props.getSecurityMenu_Padding());

                    line = ui.getLine(UILayout.SECURITYMENU_POSITION.getLine() - 1);
                    replace = line.replaceFirst(UILayout.SECURITYMENU_POSITION.getRegex(), tmp);
                    ui.setLine(replace, UILayout.SECURITYMENU_POSITION.getLine() - 1);
                }
            }
        }

        // Locale Button
        if (!this.props.getLocaleButtonVisible()) {
            // hide
            line = ui.getLine(UILayout.LOCALEBTN_MISC.getLine() - 1);
            replace = line.replaceFirst(UIRegex.LINEEND.toString(), UIRegex.VISIBILITY.toString());
            replace += "\"" + this.props.getLocaleButtonVisible() + "\"" + UILayout.LOCALEBTN_MISC.getRegex();

            ui.setLine(replace, UILayout.LOCALEBTN_MISC.getLine() - 1);

        } else {
            // Position
            if (this.props.getLocaleButtonPosition() != UIPositionInits.LOCALE_BTN.getPosition()) {
                tmp = UIPositions.LanguageButton.valueOf(this.props.getLocaleButtonPosition().name()).getString();

                line = ui.getLine(UILayout.LOCALEBTN_POSITION.getLine() - 1);
                replace = line.replaceFirst(UILayout.LOCALEBTN_POSITION.getRegex(), tmp);
                ui.setLine(replace, UILayout.LOCALEBTN_POSITION.getLine() - 1);
            }
            // Padding
            if (this.props.getLocaleButtonPadding() != UIPaddingInits.LOCALE_BTN.getPadding()) {
                tmp = UIRegex.PADDING + intArToString(this.props.getLocaleButtonPadding());

                line = ui.getLine(UILayout.LOCALEBTN_PADDING.getLine() - 1);
                replace = line.replaceFirst(UILayout.LOCALEBTN_PADDING.getRegex(), tmp);
                ui.setLine(replace, UILayout.LOCALEBTN_PADDING.getLine() - 1);
            }
        }

        // Accessibility Button
        {
            // Height
            if (this.props.getAccButtonHeight() != UISizeInits.ACC_BTN.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getAccButtonHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.ACCBTN_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.ACCBTN_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.ACCBTN_HEIGHT.getLine() - 1);
            }
            // Width
            if (this.props.getAccButtonWidth() != UISizeInits.ACC_BTN.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getAccButtonWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.ACCBTN_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.ACCBTN_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.ACCBTN_WIDTH.getLine() - 1);
            }
            // Position
            if (this.props.getAccButtonPosition() != UIPositionInits.ACC_BTN.getPosition()) {
                tmp = UIRegex.LAYOUTPOS + "\"" + this.props.getAccButtonPosition().toString().toLowerCase() + "\"";

                line = ui.getLine(UILayout.ACCBTN_POSITION.getLine() - 1);
                replace = line.replaceFirst(UILayout.ACCBTN_POSITION.getRegex(), tmp);
                ui.setLine(replace, UILayout.ACCBTN_POSITION.getLine() - 1);
            }
        }

        // Command Button
        {
            if ((this.props.getCommandButtonHeight() != UISizeInits.COM_BTN.getHeight())
                    || (this.props.getCommandButtonWidth() != UISizeInits.COM_BTN.getWidth())) {
                tmp = UIRegex.MINSIZE + "(" + this.props.getCommandButtonWidth() + UIRegex.RP + "," + this.props.getCommandButtonHeight()
                        + UIRegex.RP + ")" + "\"";

                line = ui.getLine(UILayout.COMBTN_MINHEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.COMBTN_MINHEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.COMBTN_MINHEIGHT.getLine() - 1);
            }

            if (this.props.getCommandButtonPadding() != UIPaddingInits.COM_BTN.getPadding()) {
                tmp = UIRegex.PADDING + intArToString(this.props.getCommandButtonPadding());

                line = ui.getLine(UILayout.COMBTN_PADDING.getLine() - 1);
                replace = line.replaceFirst(UILayout.COMBTN_PADDING.getRegex(), tmp);
                ui.setLine(replace, UILayout.COMBTN_PADDING.getLine() - 1);
            }

            if (this.props.getCommandButtonBorderthickness() != UIBorderthicknessInits.COM_BTN.getBorderthickness()) {
                tmp = UIRegex.BORDERTHICKNESS + intArToString(this.props.getCommandButtonBorderthickness());

                line = ui.getLine(UILayout.COMBTN_BORDERTHICKNESS_1.getLine() - 1);
                replace = line.replaceFirst(UILayout.COMBTN_BORDERTHICKNESS_1.getRegex(), tmp);
                ui.setLine(replace, UILayout.COMBTN_BORDERTHICKNESS_1.getLine() - 1);

                line = ui.getLine(UILayout.COMBTN_BORDERTHICKNESS_2.getLine() - 1);
                replace = line.replaceFirst(UILayout.COMBTN_BORDERTHICKNESS_2.getRegex(), tmp);
                ui.setLine(replace, UILayout.COMBTN_BORDERTHICKNESS_2.getLine() - 1);

                line = ui.getLine(UILayout.COMBTN_BORDERTHICKNESS_3.getLine() - 1);
                replace = line.replaceFirst(UILayout.COMBTN_BORDERTHICKNESS_3.getRegex(), tmp);
                ui.setLine(replace, UILayout.COMBTN_BORDERTHICKNESS_3.getLine() - 1);
            }

            if (this.props.getCommandBtnImgDefaultChanged()) {
                if (this.props.getCommandButtonMargin() != UIMarginInits.COM_BTN.getMargin()) {
                    tmp = UIRegex.MARGIN
                            + intArToString(this.props.getCommandButtonMargin())
                            + UIRegex.BACKGROUND.toString().replaceFirst(UIRegex.REPLACE_HELPER.toString(),
                                    UIResNumbers.COMBTN_DEF.getNum() + "");

                } else {
                    tmp = UILayout.COMBTN_MARGIN.getRegex()
                            + UIRegex.BACKGROUND.toString().replaceFirst(UIRegex.REPLACE_HELPER.toString(),
                                    UIResNumbers.COMBTN_DEF.getNum() + "");
                }

                for (int element : UIFileConstants.LINENRS_COMBTN_MARGIN) {
                    line = ui.getLine(element - 1);
                    replace = line.replaceFirst(UILayout.COMBTN_MARGIN.getRegex(), tmp);
                    ui.setLine(replace, element - 1);
                }
            } else {
                if (this.props.getCommandButtonMargin() != UIMarginInits.COM_BTN.getMargin()) {
                    tmp = UIRegex.MARGIN + intArToString(this.props.getCommandButtonMargin());

                    for (int element : UIFileConstants.LINENRS_COMBTN_MARGIN) {
                        line = ui.getLine(element - 1);
                        replace = line.replaceFirst(UILayout.COMBTN_MARGIN.getRegex(), tmp);
                        ui.setLine(replace, element - 1);
                    }
                }
            }

            if (this.props.getCommandButtonArrowpositionIsRight()) {
                tmp = UIRegex.CONTENTALIGN + UIFileConstants.REPLACE_COMBTN;

                line = ui.getLine(UILayout.COMBTN_CONTENTALIGN.getLine() - 1);
                replace = line.replaceFirst(UILayout.COMBTN_CONTENTALIGN.getRegex(), tmp);
                ui.setLine(replace, UILayout.COMBTN_CONTENTALIGN.getLine() - 1);
            }

            {
                // Single Command Button Visibility
                if (!this.props.getCommandButtonVisibility(CommandButton.LOCK)
                        || !this.props.getCommandButtonVisibility(CommandButton.LOGOUT)
                        || !this.props.getCommandButtonVisibility(CommandButton.PASSWORD)
                        || !this.props.getCommandButtonVisibility(CommandButton.SWITCH)
                        || !this.props.getCommandButtonVisibility(CommandButton.TASKMANAGER)) {

                    {
                        if (!this.props.getCommandButtonVisibility(CommandButton.TASKMANAGER)) {
                            tmp = UILayout.COMBTN_BTNVISIBILITY_5.getRegex();
                            tmp += UIRegex.ELEMENT_END.toString() + UIRegex.ELEMENT;
                        } else {
                            tmp = UILayout.COMBTN_BTNVISIBILITY_5.getRegex();
                            tmp += UIRegex.ELEMENT_END.toString() + UIFileConstants.REPLACE_COMBTNVISBILITY;
                        }
                        line = ui.getLine(UILayout.COMBTN_BTNVISIBILITY_5.getLine() - 1);
                        replace = line.replaceFirst(UILayout.COMBTN_BTNVISIBILITY_5.getRegex(), tmp);
                        ui.setLine(replace, UILayout.COMBTN_BTNVISIBILITY_5.getLine() - 1);
                    }
                    {
                        if (!this.props.getCommandButtonVisibility(CommandButton.PASSWORD)) {
                            tmp = UILayout.COMBTN_BTNVISIBILITY_4.getRegex();
                            tmp += UIRegex.ELEMENT_END.toString() + UIRegex.ELEMENT;
                        } else {
                            tmp = UILayout.COMBTN_BTNVISIBILITY_4.getRegex();
                            tmp += UIRegex.ELEMENT_END.toString() + UIFileConstants.REPLACE_COMBTNVISBILITY;
                        }
                        line = ui.getLine(UILayout.COMBTN_BTNVISIBILITY_4.getLine() - 1);
                        replace = line.replaceFirst(UILayout.COMBTN_BTNVISIBILITY_4.getRegex(), tmp);
                        ui.setLine(replace, UILayout.COMBTN_BTNVISIBILITY_4.getLine() - 1);
                    }
                    {
                        if (!this.props.getCommandButtonVisibility(CommandButton.LOGOUT)) {
                            tmp = UILayout.COMBTN_BTNVISIBILITY_3.getRegex();
                            tmp += UIRegex.ELEMENT_END.toString() + UIRegex.ELEMENT;
                        } else {
                            tmp = UILayout.COMBTN_BTNVISIBILITY_3.getRegex();
                            tmp += UIRegex.ELEMENT_END.toString() + UIFileConstants.REPLACE_COMBTNVISBILITY;
                        }
                        line = ui.getLine(UILayout.COMBTN_BTNVISIBILITY_3.getLine() - 1);
                        replace = line.replaceFirst(UILayout.COMBTN_BTNVISIBILITY_3.getRegex(), tmp);
                        ui.setLine(replace, UILayout.COMBTN_BTNVISIBILITY_3.getLine() - 1);
                    }
                    {
                        if (!this.props.getCommandButtonVisibility(CommandButton.SWITCH)) {
                            tmp = UILayout.COMBTN_BTNVISIBILITY_2.getRegex();
                            tmp += UIRegex.ELEMENT_END.toString() + UIRegex.ELEMENT;
                        } else {
                            tmp = UILayout.COMBTN_BTNVISIBILITY_2.getRegex();
                            tmp += UIRegex.ELEMENT_END.toString() + UIFileConstants.REPLACE_COMBTNVISBILITY;
                        }
                        line = ui.getLine(UILayout.COMBTN_BTNVISIBILITY_2.getLine() - 1);
                        replace = line.replaceFirst(UILayout.COMBTN_BTNVISIBILITY_2.getRegex(), tmp);
                        ui.setLine(replace, UILayout.COMBTN_BTNVISIBILITY_2.getLine() - 1);
                    }
                    if (!this.props.getCommandButtonVisibility(CommandButton.LOCK)) {
                        tmp = UIRegex.ELEMENT.toString();
                        line = ui.getLine(UILayout.COMBTN_BTNVISIBILITY_1.getLine() - 1);
                        replace = tmp;
                        ui.setLine(replace, UILayout.COMBTN_BTNVISIBILITY_1.getLine() - 1);
                    }
                }
            }

            { // Font
                tmp = UIRegex.FONT + "\"";
                tmp += this.props.getCommandButtonFontsize() + ";";
                tmp += this.props.getCommandButtonFonttype().toString() + ";";
                tmp += this.props.getCommandButtonFontstyle().toString() + ";";
                tmp += this.props.getCommandButtonFont();
                tmp += "\"" + UIRegex.FONTCOLOR + "\"";
                tmp += intArToColor(this.props.getCommandButtonFontcolor()) + "\"";

                line = ui.getLine(UILayout.COMBTN_FONT.getLine() - 1);
                replace = line.replaceFirst(UILayout.COMBTN_FONT.getRegex(), tmp);
                ui.setLine(replace, UILayout.COMBTN_FONT.getLine() - 1);
            }
        }

        // Standard Button
        {
            if ((this.props.getStandardButtonHeight() != UISizeInits.STD_BTN.getHeight())
                    && (this.props.getStandardButtonWidth() != UISizeInits.STD_BTN.getWidth())) {
                tmp = UIRegex.MINSIZE + "(" + this.props.getStandardButtonWidth() + UIRegex.RP + "," + this.props.getStandardButtonHeight()
                        + UIRegex.RP + ")" + "\"";

                line = ui.getLine(UILayout.STDBTN_MINSIZE.getLine() - 1);
                replace = line.replaceFirst(UILayout.STDBTN_MINSIZE.getRegex(), tmp);
                ui.setLine(replace, UILayout.STDBTN_MINSIZE.getLine() - 1);
            }

            if (this.props.getStandardButtonBorderthickness() != UIBorderthicknessInits.STD_BTN.getBorderthickness()) {
                tmp = UIRegex.BORDERTHICKNESS + intArToString(this.props.getStandardButtonBorderthickness());

                line = ui.getLine(UILayout.STDBTN_BORDERTHICKNESS.getLine() - 1);
                replace = line.replaceFirst(UILayout.STDBTN_BORDERTHICKNESS.getRegex(), tmp);
                ui.setLine(replace, UILayout.STDBTN_BORDERTHICKNESS.getLine() - 1);
            }

            { // Font
                tmp = UIRegex.FONT + "\"";
                tmp += this.props.getBtn_Std_Fontsize() + ";";
                tmp += this.props.getBtn_Std_Fonttype().toString() + ";";
                tmp += this.props.getBtn_Std_Fontstyle().toString() + ";";
                tmp += this.props.getBtn_Std_Font();
                tmp += "\"";

                line = ui.getLine(UILayout.STDBTN_FONT.getLine() - 1);
                replace = line.replaceFirst(UILayout.STDBTN_FONT.getRegex(), tmp);
                ui.setLine(replace, UILayout.STDBTN_FONT.getLine() - 1);
            }

            if (this.props.getBtn_Std_Fontcolor() != UIFontInits.STDBUTTON.getColor()) {
                tmp = UIRegex.FONTCOLOR + "\"" + intArToColor(this.props.getBtn_Std_Fontcolor()) + "\"";

                line = ui.getLine(UILayout.STDBTN_FOREGROUND.getLine() - 1);
                replace = line.replaceFirst(UILayout.STDBTN_FOREGROUND.getRegex(), tmp);
                ui.setLine(replace, UILayout.STDBTN_FOREGROUND.getLine() - 1);
            }

            if (this.props.getStandardButtonPosition() != UIPositionInits.STD_BTN.getPosition()) {
                tmp = UIPositions.StandardButton.valueOf(this.props.getStandardButtonPosition().name()).getString();

                line = ui.getLine(UILayout.STDBTN_POSITION.getLine() - 1);
                replace = line.replaceFirst(UILayout.STDBTN_POSITION.getRegex(), tmp);
                ui.setLine(replace, UILayout.STDBTN_POSITION.getLine() - 1);
            }

            if (this.props.getStandardButtonMargin() != UIMarginInits.STD_BTN.getMargin()) {
                tmp = UIRegex.MARGIN + intArToString(this.props.getStandardButtonMargin());

                line = ui.getLine(UILayout.STDBTN_MARGIN.getLine() - 1);
                replace = line.replaceFirst(UILayout.STDBTN_MARGIN.getRegex(), tmp);
                ui.setLine(replace, UILayout.STDBTN_MARGIN.getLine() - 1);
            }

            if (this.props.getStandardButtonPadding() != UIPaddingInits.STD_BTN.getPadding()) {
                tmp = UIRegex.PADDING + intArToString(this.props.getStandardButtonPadding());

                line = ui.getLine(UILayout.STDBTN_PADDING.getLine() - 1);
                replace = line.replaceFirst(UILayout.STDBTN_PADDING.getRegex(), tmp);
                ui.setLine(replace, UILayout.STDBTN_PADDING.getLine() - 1);
            }
        }

        // Password Button
        {
            if (this.props.getPasswordButtonHeight() != UISizeInits.PW_BTN.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getPasswordButtonHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.PWBTN_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWBTN_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWBTN_HEIGHT.getLine() - 1);
            }

            if (this.props.getPasswordButtonWidth() != UISizeInits.PW_BTN.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getPasswordButtonWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.PWBTN_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWBTN_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWBTN_WIDTH.getLine() - 1);
            }

            if (this.props.getPasswordButtonBorderthickness() != UIBorderthicknessInits.PW_BTN.getBorderthickness()) {
                tmp = UIRegex.BORDERTHICKNESS + intArToString(this.props.getPasswordButtonBorderthickness());

                line = ui.getLine(UILayout.PWBTN_BORDERTHICKNESS.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWBTN_BORDERTHICKNESS.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWBTN_BORDERTHICKNESS.getLine() - 1);
            }

            if (this.props.getPasswordButtonMargin() != UIMarginInits.PW_BTN.getMargin()) {
                tmp = UIRegex.MARGIN + intArToString(this.props.getPasswordButtonMargin());

                line = ui.getLine(UILayout.PWBTN_MARGIN.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWBTN_MARGIN.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWBTN_MARGIN.getLine() - 1);
            }
        }

        // Shutdown frame
        {
            if (this.props.getShutdownframeBorderlayout()) {
                line = ui.getLine(UILayout.SHDFRAME_LAYOUT.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDFRAME_LAYOUT.getRegex(), UIFileConstants.REPLACE_SHDFRAME_P);
                ui.setLine(replace, UILayout.SHDFRAME_LAYOUT.getLine() - 1);
            }

            if (this.props.getShutdownframePosition() != UIPositionInits.SHDFRAME.getPosition()) {
                tmp = UIRegex.LAYOUTPOS + "\"" + this.props.getShutdownframePosition().toString().toLowerCase() + "\"";

                line = ui.getLine(UILayout.SHDFRAME_POSITION.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDFRAME_POSITION.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDFRAME_POSITION.getLine() - 1);
            }
        }

        // Shutdown Button
        {
            if (this.props.getShutdownButtonHeight() != UISizeInits.SHD_BTN.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getShutdownButtonHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.SHDBTN_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDBTN_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDBTN_HEIGHT.getLine() - 1);
            }

            if (this.props.getShutdownButtonWidth() != UISizeInits.SHD_BTN.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getShutdownButtonWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.SHDBTN_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDBTN_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDBTN_WIDTH.getLine() - 1);
            }

            if (this.props.getShutdownButtonBorderthickness() != UIBorderthicknessInits.SHD_BTN.getBorderthickness()) {
                tmp = UIRegex.BORDERTHICKNESS + intArToString(this.props.getShutdownButtonBorderthickness());

                line = ui.getLine(UILayout.SHDBTN_BORDERTHICKNESS.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDBTN_BORDERTHICKNESS.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDBTN_BORDERTHICKNESS.getLine() - 1);
            }

            if (this.props.getShutdownButtonMargin() != UIMarginInits.SHD_BTN.getMargin()) {
                tmp = UIRegex.MARGIN + intArToString(this.props.getShutdownButtonMargin());

                line = ui.getLine(UILayout.SHDBTN_MARGIN.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDBTN_MARGIN.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDBTN_MARGIN.getLine() - 1);
            }

            if (this.props.getShutdownButtonContent() != null) {
                line = ui.getLine(UILayout.SHDBTN_CONTENT.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDBTN_CONTENT.getRegex(), this.props.getShutdownButtonContent());
                ui.setLine(replace, UILayout.SHDBTN_CONTENT.getLine() - 1);

                { // Font
                    tmp = UIRegex.FONT + "\"";
                    tmp += this.props.getShutdownFontsize() + ";";
                    tmp += this.props.getShutdownFonttype().toString() + ";";
                    tmp += this.props.getShutdownFontstyle().toString() + ";";
                    tmp += this.props.getShutdownFont();
                    tmp += "\"" + UIRegex.FONTCOLOR + "\"";
                    tmp += intArToColor(this.props.getShutdownFontcolor()) + "\"";

                    line = ui.getLine(UILayout.SHDBTN_FONT.getLine() - 1);
                    line += " " + tmp;
                    ui.setLine(line, UILayout.SHDBTN_FONT.getLine() - 1);
                }
            }

            if ((this.props.getShutdownButtonPosition() != UIPositionInits.SHD_BTN.getPosition())
                    || (this.props.getShutdownmenuButtonPosition() != UIPositionInits.SHDMENU_BTN.getPosition())) {
                tmp = UIRegex.LAYOUTPOS + "\"" + this.props.getShutdownButtonPosition().toString().toLowerCase() + "\""
                        + UILayout.SHDBTN_POSITION.getRegex();

                line = ui.getLine(UILayout.SHDBTN_POSITION.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDBTN_POSITION.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDBTN_POSITION.getLine() - 1);
            }
        }

        // Shutdown menu
        {
            if (this.props.getShutdownmenuButtonHeight() != UISizeInits.SHDMENU_BTN.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getShutdownmenuButtonHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.SHDMENU_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDMENU_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDMENU_HEIGHT.getLine() - 1);
            }

            if (this.props.getShutdownmenuButtonWidth() != UISizeInits.SHDMENU_BTN.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getShutdownmenuButtonWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.SHDMENU_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDMENU_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDMENU_WIDTH.getLine() - 1);
            }

            if (this.props.getShutdownmenuButtonBorderthickness() != UIBorderthicknessInits.SHDMENU_BTN.getBorderthickness()) {
                tmp = UIRegex.BORDERTHICKNESS + intArToString(this.props.getShutdownmenuButtonBorderthickness());

                line = ui.getLine(UILayout.SHDMENU_BORDERTHICKNESS.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDMENU_BORDERTHICKNESS.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDMENU_BORDERTHICKNESS.getLine() - 1);
            }

            if (this.props.getShutdownmenuButtonMargin() != UIMarginInits.SHDMENU_BTN.getMargin()) {
                tmp = UIRegex.MARGIN + intArToString(this.props.getShutdownmenuButtonMargin());

                line = ui.getLine(UILayout.SHDMENU_MARGIN.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDMENU_MARGIN.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDMENU_MARGIN.getLine() - 1);
            }

            if (this.props.getShutdownmenuButtonContent() != null) {
                line = ui.getLine(UILayout.SHDMENU_CONTENT.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDMENU_CONTENT.getRegex(), this.props.getShutdownmenuButtonContent());
                ui.setLine(replace, UILayout.SHDMENU_CONTENT.getLine() - 1);

                { // Font
                    tmp = UIRegex.FONT + "\"";
                    tmp += this.props.getShutdownMenuFontsize() + ";";
                    tmp += this.props.getShutdownMenuFonttype().toString() + ";";
                    tmp += this.props.getShutdownMenuFontstyle().toString() + ";";
                    tmp += this.props.getShutdownMenuFont();
                    tmp += "\"" + UIRegex.FONTCOLOR + "\"";
                    tmp += intArToColor(this.props.getShutdownMenuFontcolor()) + "\"";

                    line = ui.getLine(UILayout.SHDMENU_FONT.getLine() - 1);
                    line += " " + tmp;
                    ui.setLine(line, UILayout.SHDMENU_FONT.getLine() - 1);

                    line = ui.getLine(UILayout.SHDMENU_FONT_FIX.getLine() - 1);
                    replace = line.replaceFirst(UILayout.SHDMENU_FONT_FIX.getRegex(), "");
                    ui.setLine(replace, UILayout.SHDMENU_FONT_FIX.getLine() - 1);
                }
            }

            if ((this.props.getShutdownButtonPosition() != UIPositionInits.SHD_BTN.getPosition())
                    || (this.props.getShutdownmenuButtonPosition() != UIPositionInits.SHDMENU_BTN.getPosition())) {
                tmp = UIRegex.LAYOUTPOS + "\"" + this.props.getShutdownmenuButtonPosition().toString().toLowerCase() + "\""
                        + UILayout.SHDMENU_POSITION.getRegex();

                line = ui.getLine(UILayout.SHDMENU_POSITION.getLine() - 1);
                replace = line.replaceFirst(UILayout.SHDMENU_POSITION.getRegex(), tmp);
                ui.setLine(replace, UILayout.SHDMENU_POSITION.getLine() - 1);
            }
        }

        // Combine AccessibilityBtn + Shutdown in one element
        if (this.props.getCombinedShdAcc()) {
            line = ui.getLine(UILayout.ACCSHD_COMBINED.getLine() - 1);
            tmp = ui.getLine(UILayout.ACCSHD_COMBINED.getLine());
            ui.setLine(line, UILayout.ACCSHD_COMBINED.getLine());
            line = ui.getLine(UILayout.ACCSHD_COMBINED.getLine() + 1);
            ui.setLine(tmp, UILayout.ACCSHD_COMBINED.getLine() + 1);

            tmp = UIRegex.LAYOUTPOS + "\"" + this.props.getCombinedShdAccPosition().toString().toLowerCase() + "\"";
            replace = line.replaceFirst(UILayout.ACCSHD_POSITION.getRegex(), tmp);
            ui.setLine(replace, UILayout.ACCSHD_POSITION.getLine() - 1);
        }

        // Wrap AccessibilityBtn in new element (to avoid y-scaling). Inactive if
        // ShdBtn and AccBtn combined.
        if (this.props.getWrapAccInNewElement() && !this.props.getCombinedShdAcc()) {
            tmp = UIFileConstants.ACCBUTTON_FIX_REPLACE_1 + UIFileConstants.ACCBUTTON_FIX_REGEX_1;

            line = ui.getLine(UIFileConstants.LINENR_ACCBUTTON_FIX_1 - 1);
            replace = line.replaceFirst(UIFileConstants.ACCBUTTON_FIX_REGEX_1, tmp);
            ui.setLine(replace, UIFileConstants.LINENR_ACCBUTTON_FIX_1 - 1);

            tmp = UIFileConstants.ACCBUTTON_FIX_REPLACE_2 + UIFileConstants.ACCBUTTON_FIX_REGEX_2;

            line = ui.getLine(UIFileConstants.LINENR_ACCBUTTON_FIX_2 - 1);
            replace = line.replaceFirst(UIFileConstants.ACCBUTTON_FIX_REGEX_2, tmp);
            ui.setLine(replace, UIFileConstants.LINENR_ACCBUTTON_FIX_2 - 1);
        }

        // Branding
        if (this.props.getBrandingPosition() != UIPositionInits.BRANDING.getPosition()) {
            tmp = UIRegex.LAYOUTPOS + "\"" + this.props.getBrandingPosition().toString().toLowerCase() + "\"" + UIRegex.LINEEND;

            line = ui.getLine(UILayout.BRANDING_POSITION.getLine() - 1);
            replace = line.replaceFirst(UILayout.BRANDING_POSITION.getRegex(), tmp);
            ui.setLine(replace, UILayout.BRANDING_POSITION.getLine() - 1);
        }

        // Optionsbar (Contains Acc + Shd)
        if (this.props.getOptionsbarHeight() != UISizeInits.OPTIONSBAR.getHeight()) {
            tmp = UIRegex.HEIGHT + "\"" + this.props.getOptionsbarHeight() + UIRegex.RP + "\"";

            line = ui.getLine(UILayout.OPTIONSBAR_HEIGHT.getLine() - 1);
            replace = line.replaceFirst(UILayout.OPTIONSBAR_HEIGHT.getRegex(), tmp);
            ui.setLine(replace, UILayout.OPTIONSBAR_HEIGHT.getLine() - 1);
        }

        // Userlist
        {
            if (this.props.getUserlistHeight() != UISizeInits.USERLIST.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getUserlistHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERLIST_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_HEIGHT.getLine() - 1);
            }

            if (this.props.getUserlistVertical()) {
                line = ui.getLine(UILayout.USERLIST_LAYOUT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_LAYOUT.getRegex(), UIRegex.VERTFLOWLAYOUT.toString());
                ui.setLine(replace, UILayout.USERLIST_LAYOUT.getLine() - 1);
            }

            if (this.props.getUserlistVertical()) {
                tmp = UIPositions.Userlist.valueOf(this.props.getUserlistPosition().name()).getStringVertical();

            } else {
                tmp = UIPositions.Userlist.valueOf(this.props.getUserlistPosition().name()).getStringHorizontal();
            }

            line = ui.getLine(UILayout.USERLIST_POSITION.getLine() - 1);
            replace = line.replaceFirst(UILayout.USERLIST_POSITION.getRegex(), tmp);
            ui.setLine(replace, UILayout.USERLIST_POSITION.getLine() - 1);

            if (this.props.getUserlistPadding() != UIPaddingInits.USERLIST.getPadding()) {
                tmp = UIRegex.PADDING + intArToString(this.props.getUserlistPadding());

                line = ui.getLine(UILayout.USERLIST_PADDING.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_PADDING.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_PADDING.getLine() - 1);
            }
        }

        // Userlist Image
        {
            if (this.props.getUserlistImageHeight() != UISizeInits.USERLIST_IMAGE.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getUserlistImageHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERLIST_IMAGE_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_IMAGE_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_IMAGE_HEIGHT.getLine() - 1);
            }

            if (this.props.getUserlistImageWidth() != UISizeInits.USERLIST_IMAGE.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getUserlistImageWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERLIST_IMAGE_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_IMAGE_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_IMAGE_WIDTH.getLine() - 1);
            }

            if (this.props.getUserlistImagePadding() != UIPaddingInits.USERLIST_IMAGE.getPadding()) {
                tmp = UIRegex.PADDING + intArToString(this.props.getUserlistImagePadding());

                line = ui.getLine(UILayout.USERLIST_IMAGE_PADDING.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_IMAGE_PADDING.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_IMAGE_PADDING.getLine() - 1);
            }

            if (this.props.getUserlistImageFrameHeight() != UISizeInits.USERLIST_IMAGEFRAME.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getUserlistImageFrameHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERLIST_IMAGEFRAME_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_IMAGEFRAME_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_IMAGEFRAME_HEIGHT.getLine() - 1);
            }

            if (this.props.getUserlistImageFrameWidth() != UISizeInits.USERLIST_IMAGEFRAME.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getUserlistImageFrameWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERLIST_IMAGEFRAME_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_IMAGEFRAME_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_IMAGEFRAME_WIDTH.getLine() - 1);
            }

            if (this.props.getUserlistImageOverlay_Height() != UISizeInits.USERLIST_IMAGEOVERLAY.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getUserlistImageOverlay_Height() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERLIST_IMAGEOVERLAY_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_IMAGEOVERLAY_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_IMAGEOVERLAY_HEIGHT.getLine() - 1);
            }

            if (this.props.getUserlistImageOverlay_Width() != UISizeInits.USERLIST_IMAGEOVERLAY.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getUserlistImageOverlay_Width() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERLIST_IMAGEOVERLAY_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERLIST_IMAGEOVERLAY_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERLIST_IMAGEOVERLAY_WIDTH.getLine() - 1);
            }
        }

        // Usertile
        {
            if (this.props.getUsertileLayoutIsHorizontal()) {
                line = ui.getLine(UILayout.USERTILE_LAYOUT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_LAYOUT.getRegex(), UIFileConstants.REPLACE_USERTILE_LAYOUT);
                ui.setLine(replace, UILayout.USERTILE_LAYOUT.getLine() - 1);

                if (this.props.getUsertilePosition() != UIPositionInits.USERTILE.getPosition()) {
                    tmp = (UIPositions.Usertile.valueOf(this.props.getUsertilePosition().name())).getStringVertical();

                    line = ui.getLine(UILayout.USERTILE_POSITION.getLine() - 1);
                    replace = line.replaceFirst(UILayout.USERTILE_POSITION.getRegex(), tmp);
                    ui.setLine(replace, UILayout.USERTILE_POSITION.getLine() - 1);
                }

            } else {
                tmp = (UIPositions.Usertile.valueOf(this.props.getUsertilePosition().name())).getStringHorizontal();

                String i = Usertile.CENTER_PA.getStringPictureAlign();
                switch (this.props.getUsertileImagePosition()) {
                    case LEFT:
                        i = Usertile.LEFT_PA.getStringPictureAlign();
                        break;

                    case CENTER:
                        i = Usertile.CENTER_PA.getStringPictureAlign();
                        break;

                    case RIGHT:
                        i = Usertile.RIGHT_PA.getStringPictureAlign();
                        break;

                    default:
                        break;
                }
                tmp = tmp.replaceFirst(UIFileConstants.USERTILE_IMAGEPOSITION_CHAR, i);

                line = ui.getLine(UILayout.USERTILE_POSITION.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_POSITION.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERTILE_POSITION.getLine() - 1);
            }

            if (this.props.getPWAreaPositionOnRightOfTexts()) {
                line = ui.getLine(UILayout.USERTILE_PWAREAONRIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_PWAREAONRIGHT.getRegex(), UIFileConstants.REPLACE_PWAREA_LAYOUT);
                ui.setLine(replace, UILayout.USERTILE_PWAREAONRIGHT.getLine() - 1);
            }

            if (this.props.getStatusOnRightSide()) {
                line = ui.getLine(UILayout.USERTILE_STATUSONRIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_STATUSONRIGHT.getRegex(), UIFileConstants.REPLACE_PWSTATUS_LAYOUT);
                ui.setLine(replace, UILayout.USERTILE_STATUSONRIGHT.getLine() - 1);
            }
        }
        // Usertile Image
        {
            if (this.props.getUsertileImageHeight() != UISizeInits.USERTILE_IMAGE.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getUsertileImageHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERTILE_IMAGE_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_IMAGE_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERTILE_IMAGE_HEIGHT.getLine() - 1);
            }

            if (this.props.getUsertileImageWidth() != UISizeInits.USERTILE_IMAGE.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getUsertileImageWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERTILE_IMAGE_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_IMAGE_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERTILE_IMAGE_WIDTH.getLine() - 1);
            }

            if (this.props.getUsertileImagePadding() != UIPaddingInits.USERTILE_IMAGE.getPadding()) {
                tmp = UIRegex.PADDING + intArToString(this.props.getUsertileImagePadding());

                line = ui.getLine(UILayout.USERTILE_IMAGE_PADDING.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_IMAGE_PADDING.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERTILE_IMAGE_PADDING.getLine() - 1);
            }

            if (this.props.getUsertileImageFrameHeight() != UISizeInits.USERTILE_IMAGEFRAME.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getUsertileImageFrameHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERTILE_IMAGEFRAME_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_IMAGEFRAME_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERTILE_IMAGEFRAME_HEIGHT.getLine() - 1);
            }

            if (this.props.getUsertileImageFrameWidth() != UISizeInits.USERTILE_IMAGEFRAME.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getUsertileImageFrameWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERTILE_IMAGEFRAME_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_IMAGEFRAME_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERTILE_IMAGEFRAME_WIDTH.getLine() - 1);
            }

            if (this.props.getUsertileImageOverlay_Height() != UISizeInits.USERTILE_IMAGEOVERLAY.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getUsertileImageOverlay_Height() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERTILE_IMAGEOVERLAY_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_IMAGEOVERLAY_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERTILE_IMAGEOVERLAY_HEIGHT.getLine() - 1);
            }

            if (this.props.getUsertileImageOverlay_Width() != UISizeInits.USERTILE_IMAGEOVERLAY.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getUsertileImageOverlay_Width() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.USERTILE_IMAGEOVERLAY_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.USERTILE_IMAGEOVERLAY_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.USERTILE_IMAGEOVERLAY_WIDTH.getLine() - 1);
            }
        }

        // Passwordfield
        {
            if (this.props.getPWfieldHeight() != UISizeInits.PWFIELD.getHeight()) {
                tmp = UIRegex.HEIGHT + "\"" + this.props.getPWfieldHeight() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.PWFIELD_HEIGHT.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWFIELD_HEIGHT.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWFIELD_HEIGHT.getLine() - 1);
            }

            if (this.props.getPWfieldWidth() != UISizeInits.PWFIELD.getWidth()) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getPWfieldWidth() + UIRegex.RP + "\"";

                line = ui.getLine(UILayout.PWFIELD_WIDTH.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWFIELD_WIDTH.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWFIELD_WIDTH.getLine() - 1);
            }

            if (this.props.getPWfieldBorderthickness() != UIBorderthicknessInits.PWFIELD.getBorderthickness()) {
                tmp = UIRegex.BORDERTHICKNESS + intArToString(this.props.getPWfieldBorderthickness());

                line = ui.getLine(UILayout.PWFIELD_BORDERTHICKNESS.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWFIELD_BORDERTHICKNESS.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWFIELD_BORDERTHICKNESS.getLine() - 1);
            }

            if (this.props.getPWfieldMargin() != UIMarginInits.PWFIELD.getMargin()) {
                tmp = UIRegex.MARGIN + intArToString(this.props.getPWfieldMargin());

                line = ui.getLine(UILayout.PWFIELD_MARGIN.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWFIELD_MARGIN.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWFIELD_MARGIN.getLine() - 1);
            }

            if (this.props.getPasswordfieldDownshift() != UIShiftInits.PWAREA_DOWN.getShift()) {
                tmp = UILayout.PWAREA_DOWNSHIFT.getRegex()
                        + UIFileConstants.PWAREA_HEIGHT_SHIFT.replaceFirst(UIRegex.REPLACE_HELPER.toString(),
                                this.props.getPasswordfieldDownshift() + "");

                line = ui.getLine(UILayout.PWAREA_DOWNSHIFT.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWAREA_DOWNSHIFT.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWAREA_DOWNSHIFT.getLine() - 1);
            }

            if (this.props.getPasswordfieldUpshift() != UIShiftInits.PWAREA_UP.getShift()) {
                tmp = UILayout.PWAREA_UPSHIFT.getRegex()
                        + UIFileConstants.PWAREA_HEIGHT_SHIFT.replaceFirst(UIRegex.REPLACE_HELPER.toString(),
                                this.props.getPasswordfieldUpshift() + "");

                line = ui.getLine(UILayout.PWAREA_UPSHIFT.getLine() - 1);
                replace = line.replaceFirst(UILayout.PWAREA_UPSHIFT.getRegex(), tmp);
                ui.setLine(replace, UILayout.PWAREA_UPSHIFT.getLine() - 1);
            }

            if (this.props.getUsertileLayoutIsHorizontal()) {
                if (this.props.getPasswordfieldRightshift() != UIShiftInits.PWAREA_RIGHT.getShift()) {
                    tmp = UILayout.PWAREA_RIGHTSHIFT.getRegex()
                            + UIFileConstants.PWAREA_WIDTH_SHIFT.replaceFirst(UIRegex.REPLACE_HELPER.toString(),
                                    this.props.getPasswordfieldRightshift() + "");

                    line = ui.getLine(UILayout.PWAREA_RIGHTSHIFT.getLine() - 1);
                    replace = line.replaceFirst(UILayout.PWAREA_RIGHTSHIFT.getRegex(), tmp);
                    ui.setLine(replace, UILayout.PWAREA_RIGHTSHIFT.getLine() - 1);
                }
            }
        }

        // Loading status animation
        if (this.props.getLoadingStatusAnimation() != UIAnimationInits.LOADINGSTATUS.getAnimation()) {
            tmp = UIRegex.ANIMATION + "\"" + this.props.getLoadingStatusAnimation() + "\"" + UILayout.LOADINGSTATUS_ANIMATION.getRegex();

            line = ui.getLine(UILayout.LOADINGSTATUS_ANIMATION.getLine() - 1);
            replace = line.replaceFirst(UILayout.LOADINGSTATUS_ANIMATION.getRegex(), tmp);
            ui.setLine(replace, UILayout.LOADINGSTATUS_ANIMATION.getLine() - 1);
        }

        {
            tmp = null;

            if (this.props.getLoadingStatusWidth() != 0) {
                tmp = UIRegex.WIDTH + "\"" + this.props.getLoadingStatusWidth() + "\"";
            }

            if (this.props.getLoadingStatusBorderthickness() != null) {
                tmp += UIRegex.BORDERTHICKNESS + intArToString(this.props.getLoadingStatusBorderthickness());
            }

            if (this.props.getImgPathLoadingStatusBg() != null) {
                tmp += UIRegex.BACKGROUND.toString().replaceFirst(UIRegex.REPLACE_HELPER.toString(),
                        UIResNumbers.LOADINGSTATUS.getNum() + "");
            }

            if (tmp != null) {
                tmp += UILayout.LOADINGSTATUS_MISC.getRegex();
                line = ui.getLine(UILayout.LOADINGSTATUS_MISC.getLine() - 1);
                replace = line.replaceFirst(UILayout.LOADINGSTATUS_MISC.getRegex(), tmp);
                ui.setLine(replace, UILayout.LOADINGSTATUS_MISC.getLine() - 1);
            }
        }

        // Fontshadow
        if (this.props.getShadowIntensity() != SkinConstants.DEFVALUE_FONTSHADOW) {
            String IntensityReplace = UIRegex.FONTSHADOW + "\"" + this.props.getShadowIntensity() + "\"";

            for (int element : UIFileConstants.LINENRS_FONTSHADOW) {
                line = ui.getLine(element - 1);
                replace = line.replaceFirst(UILayout.FONTSHADOW.getRegex(), IntensityReplace);
                ui.setLine(replace, element - 1);
            }
        }

        /*
         * Fonts: Shortmessage, Loadingstatus, Passwordfield, Passwordreset, Status, Name. # Shutdown, Commandbtn,
         * Standardbtn are above.
         */
        // Shortmessage font
        { // Font
            tmp = UIRegex.FONT + "\"";
            tmp += this.props.getShortMessageFontsize() + ";";
            tmp += this.props.getShortMessageFonttype().toString() + ";";
            tmp += this.props.getShortMessageFontstyle().toString() + ";";
            tmp += this.props.getShortMessageFont();
            tmp += "\"" + UIRegex.FONTCOLOR + "\"";
            tmp += intArToColor(this.props.getShortMessageFontcolor()) + "\"";

            line = ui.getLine(UILayout.SHORTMESSAGE_FONT.getLine() - 1);
            replace = line.replaceFirst(UILayout.SHORTMESSAGE_FONT.getRegex(), tmp);
            ui.setLine(replace, UILayout.SHORTMESSAGE_FONT.getLine() - 1);
        }

        // Loading/status font
        { // Font
            tmp = UIRegex.FONT + "\"";
            tmp += this.props.getLoadingStatusFontsize() + ";";
            tmp += this.props.getLoadingStatusFonttype().toString() + ";";
            tmp += this.props.getLoadingStatusFontstyle().toString() + ";";
            tmp += this.props.getLoadingStatusFont() + "\"";

            line = ui.getLine(UILayout.LOADINGSTATUS_FONT.getLine() - 1);
            replace = line.replaceFirst(UILayout.LOADINGSTATUS_FONT.getRegex(), tmp);
            ui.setLine(replace, UILayout.LOADINGSTATUS_FONT.getLine() - 1);
        }
        if (this.props.getLoadingStatusFontcolor() != UIFontInits.LOADINGSTATUS.getColor()) {
            tmp = UIRegex.FONTCOLOR + "\"";
            tmp += intArToColor(this.props.getLoadingStatusFontcolor()) + "\"";

            line = ui.getLine(UILayout.LOADINGSTATUS_FOREGROUND.getLine() - 1);
            replace = line.replaceFirst(UILayout.LOADINGSTATUS_FOREGROUND.getRegex(), tmp);
            ui.setLine(replace, UILayout.LOADINGSTATUS_FOREGROUND.getLine() - 1);
        }

        // Password reset link font
        { // Font
            tmp = UIRegex.FONT + "\"";
            tmp += this.props.getPWResetFontsize() + ";";
            tmp += this.props.getPWResetFonttype().toString() + ";";
            tmp += this.props.getPWResetFontstyle().toString() + ";";
            tmp += this.props.getPWResetFont();
            tmp += "\"" + UIRegex.FONTCOLOR + "\"";
            tmp += intArToColor(this.props.getPWResetFontcolor()) + "\"";

            line = ui.getLine(UILayout.RESETPW_FONT.getLine() - 1);
            replace = line.replaceFirst(UILayout.RESETPW_FONT.getRegex(), tmp);
            ui.setLine(replace, UILayout.RESETPW_FONT.getLine() - 1);
        }

        // Passwordfield font
        { // Font
            tmp = UIRegex.FONT + "\"";
            tmp += this.props.getPWFieldFontsize() + ";";
            tmp += this.props.getPWFieldFonttype().toString() + ";";
            tmp += this.props.getPWFieldFontstyle().toString() + ";";
            tmp += this.props.getPWFieldFont();
            tmp += "\"" + UIRegex.FONTCOLOR + "\"";
            tmp += intArToColor(this.props.getPWFieldFontcolor()) + "\"";

            line = ui.getLine(UILayout.PWFIELD_FONT.getLine() - 1);
            replace = line.replaceFirst(UILayout.PWFIELD_FONT.getRegex(), tmp);
            ui.setLine(replace, UILayout.PWFIELD_FONT.getLine() - 1);
        }

        // Statustext of userlist
        { // Font
            tmp = UIRegex.FONT + "\"";
            tmp += this.props.getStatustextFontsize() + ";";
            tmp += this.props.getStatustextFonttype().toString() + ";";
            tmp += this.props.getStatustextFontstyle().toString() + ";";
            tmp += this.props.getStatustextFont();
            tmp += "\"";
            tmp += UIRegex.FONTCOLOR + "\"" + intArToColor(this.props.getStatustextFontcolor()) + "\"";

            line = ui.getLine(UILayout.STATUSTEXT_FONT.getLine() - 1);
            replace = line.replaceFirst(UILayout.STATUSTEXT_FONT.getRegex(), tmp);
            ui.setLine(replace, UILayout.STATUSTEXT_FONT.getLine() - 1);
        }

        // Statustext of zoomed usertile
        { // Font
            tmp = UIRegex.FONT + "\"";
            tmp += this.props.getStatustextZoomedFontsize() + ";";
            tmp += this.props.getStatustextZoomedFonttype().toString() + ";";
            tmp += this.props.getStatustextZoomedFontstyle().toString() + ";";
            tmp += this.props.getStatustextZoomedFont();
            tmp += "\"";
            tmp += UIRegex.FONTCOLOR + "\"" + intArToColor(this.props.getStatustextZoomedFontcolor()) + "\"";

            line = ui.getLine(UILayout.STATUSTEXTZOOMED_FONT.getLine() - 1);
            replace = line.replaceFirst(UILayout.STATUSTEXTZOOMED_FONT.getRegex(), tmp);
            ui.setLine(replace, UILayout.STATUSTEXTZOOMED_FONT.getLine() - 1);
        }

        // Nametext of userlist
        { // Font
            tmp = UIRegex.FONT + "\"";
            tmp += this.props.getNametextFontsize() + ";";
            tmp += this.props.getNametextFonttype().toString() + ";";
            tmp += this.props.getNametextFontstyle().toString() + ";";
            tmp += this.props.getNametextFont();
            tmp += "\"";
            tmp += UIRegex.FONTCOLOR + "\"" + intArToColor(this.props.getNametextFontcolor()) + "\"";

            line = ui.getLine(UILayout.NAMETEXT_FONT.getLine() - 1);
            replace = line.replaceFirst(UILayout.NAMETEXT_FONT.getRegex(), tmp);
            ui.setLine(replace, UILayout.NAMETEXT_FONT.getLine() - 1);
        }

        // Nametext of zoomed usertile
        { // Font
            tmp = UIRegex.FONT + "\"";
            tmp += this.props.getNametextZoomedFontsize() + ";";
            tmp += this.props.getNametextZoomedFonttype().toString() + ";";
            tmp += this.props.getNametextZoomedFontstyle().toString() + ";";
            tmp += this.props.getNametextZoomedFont();
            tmp += "\"";
            tmp += UIRegex.FONTCOLOR + "\"" + intArToColor(this.props.getNametextZoomedFontcolor()) + "\"";

            line = ui.getLine(UILayout.NAMETEXTZOOMED_FONT.getLine() - 1);
            replace = line.replaceFirst(UILayout.NAMETEXTZOOMED_FONT.getRegex(), tmp);
            ui.setLine(replace, UILayout.NAMETEXTZOOMED_FONT.getLine() - 1);
        }
    }
}
