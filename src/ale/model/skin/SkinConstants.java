/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.nio.file.Path;

import ale.Constants;

public final class SkinConstants {

    public static final String SPLIT = " = ";
    public static final String SPLIT2 = ";";
    public static final char COMMENT = '#';
    public static final String DEFAULTSUFFIX = Constants.DEFAULT_SKINIMAGE_TYPE;
    public static final int OPTIONSBAR_HEIGHT_FIX = 150;

    public enum CommandButton {
        LOCK, SWITCH, LOGOUT, PASSWORD, TASKMANAGER;
    }

    /*
     * defaults
     */
    public enum UIFontInits {

        STATUS(9, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        STATUSZOOMED(9, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        NAME(9, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        NAMEZOOMED(18, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        STDBUTTON(11, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        COMBUTTON(11, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        SHORTMESSAGE(9, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        LOADINGSTATUS(18, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        PWRESET(9, Fonttype.NORMAL, Fontstyle.SHADOWUNDERLINE, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        PWFIELD(9, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 100, 100, 100 }),
        SHDBTN(9, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 }),
        SHDMENU(9, Fonttype.NORMAL, Fontstyle.SHADOW, "Segoe UI", new int[] { 255, 255, 255, 255 });

        private final int size;
        private final Fonttype type;
        private final Fontstyle style;
        private final String font;
        private final int[] color;

        private UIFontInits(int size, Fonttype type, Fontstyle style, String font, int[] color) {
            this.size = size;
            this.type = type;
            this.style = style;
            this.font = font;
            this.color = color;
        }

        public int getSize() {
            return this.size;
        }

        public Fonttype getType() {
            return this.type;
        }

        public Fontstyle getStyle() {
            return this.style;
        }

        public String getFont() {
            return this.font;
        }

        public int[] getColor() {
            return this.color;
        }
    }

    public enum UIShiftInits {
        PWAREA_UP(0),
        PWAREA_DOWN(0),
        PWAREA_RIGHT(0);

        private int shift;

        private UIShiftInits(int shift) {
            this.shift = shift;
        }

        public int getShift() {
            return this.shift;
        }
    }

    public enum UIBorderthicknessInits {

        WINDOW(0, 0, 0, 0),
        COM_BTN(5, 5, 5, 4),
        STD_BTN(4, 3, 4, 3),
        PW_BTN(1, 1, 1, 1),
        SHD_BTN(4, 1, 4, 1),
        SHDMENU_BTN(4, 3, 4, 3),
        PWFIELD(4, 4, 4, 4);

        private int[] borderthickness;

        private UIBorderthicknessInits(int l, int t, int r, int b) {
            this.borderthickness = new int[] { l, t, r, b };
        }

        public int[] getBorderthickness() {
            return new int[] { this.borderthickness[0], this.borderthickness[1], this.borderthickness[2],
                    this.borderthickness[3] };
        }
    }

    public enum UISizeInits {

        ACC_BTN(28, 38),
        COM_BTN(26, 190),
        STD_BTN(28, 93),
        PW_BTN(30, 30),
        SHD_BTN(28, 38),
        SHDMENU_BTN(28, 20),
        WINDOW(300, 300),
        OPTIONSBAR(96, -1),
        USERLIST(475, -1),
        USERLIST_IMAGE(46, 46),
        USERTILE_IMAGE(126, 126),
        USERLIST_IMAGEFRAME(80, 80),
        USERTILE_IMAGEFRAME(190, 190),
        USERLIST_IMAGEOVERLAY(80, 80),
        USERTILE_IMAGEOVERLAY(190, 190),
        PWFIELD(25, 225);

        private final int height;
        private final int width;

        private UISizeInits(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }
    }

    public enum UIPositionInits {

        SECURITYMENU(Position.BOTTOM),
        LOCALE_BTN(Position.TOPLEFT),
        ACC_BTN(Position.LEFT),
        STD_BTN(Position.CENTER),
        BRANDING(Position.BOTTOM),
        COMBINEDACCSHD(Position.RIGHT),
        SHD_BTN(Position.LEFT),
        SHDMENU_BTN(Position.RIGHT),
        SHDFRAME(Position.RIGHT),
        USERLIST(Position.BOTTOM),
        USERTILE(Position.CENTER),
        USERTILE_IMAGE(Position.CENTER);

        private final Position pos;

        private UIPositionInits(Position pos) {
            this.pos = pos;
        }

        public Position getPosition() {
            return this.pos;
        }
    }

    public enum UIPaddingInits {

        LOCALE_BTN(5, 5, 5, 5),
        COM_BTN(20, 0, 0, 1),
        USERLIST(12, 0, 0, 0),
        USERTILE_IMAGE(32, 32, 32, 32),
        USERLIST_IMAGE(16, 16, 16, 16),
        STD_BTN(0, 10, 0, 0);

        private final int[] padding;

        private UIPaddingInits(int l, int t, int r, int b) {
            this.padding = new int[] { l, t, r, b };
        }

        public int[] getPadding() {
            return new int[] { this.padding[0], this.padding[1], this.padding[2], this.padding[3] };
        }
    }

    public enum UIMarginInits {

        COM_BTN(0, 0, 0, 6),
        PW_BTN(4, 0, 0, 0),
        SHD_BTN(0, 0, 0, 0),
        SHDMENU_BTN(0, 0, 0, 0),
        PWFIELD(0, 3, 0, 3),
        STD_BTN(0, 30, 0, 5);

        private final int[] margin;

        private UIMarginInits(int l, int t, int r, int b) {
            this.margin = new int[] { l, t, r, b };
        }

        public int[] getMargin() {
            return new int[] { this.margin[0], this.margin[1], this.margin[2], this.margin[3] };
        }
    }

    public enum UIAnimationInits {
        WINDOW(Animation.ORIGINAL),
        INNERWINDOW(Animation.ORIGINAL),
        LOADINGSTATUS(Animation.ORIGINAL);

        private Animation ani;

        private UIAnimationInits(Animation ani) {
            this.ani = ani;
        }

        public Animation getAnimation() {
            return this.ani;
        }
    }

    public static final int DEFVALUE_FONTSHADOW = 75;

    /*
     * 
     */
    public enum UIResNumbers {

        UIFILE_ONE(12400),
        UIFILE_TWO(12401),
        UIFILE_THREE(12402),
        BRANDING_SMALL(120),
        BRANDING_MEDIUM(1120),
        BRANDING_BIG(2120),
        PWFIELD_DIS(11000),
        PWFIELD_KFOC(11001),
        PWFIELD_MFOC(11002),
        PWFIELD_DEF(11003),
        SLIDER_BAR_DEF(12201),
        SLIDER_BAR_FOC(12202),
        SLIDER_BAR_PRE(12203),
        SLIDER_UP_DEF(12204),
        SLIDER_UP_FOC(12205),
        SLIDER_UP_PRE(12206),
        SLIDER_DOWN_DEF(12207),
        SLIDER_DOWN_FOC(12208),
        SLIDER_DOWN_PRE(12209),
        SLIDER_MID_DEF(12210),
        SLIDER_MID_FOC(12211),
        SLIDER_MID_PRE(12212),
        ACCESSSYMBOL(12213),
        SHUTDOWNSYMBOL(12215),
        SHUTDOWNUPDATESYMBOL(12216),
        SHUTDOWNARROWSYMBOL(12217),
        USERLIST_IMAGEFRAME_MFOC(12218),
        USERLIST_IMAGEFRAME_SEL(12219),
        USERLIST_IMAGEFRAME_FOCSEL(12220),
        USERLIST_IMAGEFRAME_DEF(12222),
        USERTILE_IMAGEFRAME_DEF(12223),
        COMBTNARROW_KFOC(12224),
        COMBTNARROW_MFOC(12225),
        COMBTNARROW_PRE(12226),
        COMBTNARROW_DEF(12227),
        COMBTN_KFOC(12228),
        COMBTN_MFOC(12229),
        COMBTN_PRE(12230),
        COMBTN_DEF(1),
        STDBTN_KFOC(12259),
        STDBTN_MFOC(12260),
        STDBTN_KFOCMFOC(12261),
        STDBTN_PRE(12262),
        STDBTN_DEF(12263),
        PWBTN_KFOCMFOC(12286),
        PWBTN_PRE(12287),
        PWBTN_DEF(12288),
        SHUTDOWN_KFOCMFOC(12292),
        SHUTDOWN_KFOC(12293),
        SHUTDOWN_MFOC(12294),
        SHUTDOWN_PRE(12295),
        SHUTDOWN_DEF(12296),
        SHUTDOWNMENU_KFOCMFOC(12298),
        SHUTDOWNMENU_KFOC(12299),
        SHUTDOWNMENU_MFOC(12300),
        SHUTDOWNMENU_PRE(12301),
        SHUTDOWNMENU_DEF(12302),
        WINDOW(2),
        LOADINGSTATUS(3);

        private int nr;

        private UIResNumbers(int resnumber) {
            this.nr = resnumber;
        }

        public int getNum() {
            return this.nr;
        }
    }

    public enum UIDefaultImagePaths {

        BACKGROUND_PREVIEW(Constants.EDITOR_DEFAULT_BACKGROUNDIMAGE),
        WINDOW(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.WINDOW.getNum() + DEFAULTSUFFIX)),
        BRANDING_SMALL(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.BRANDING_SMALL.getNum() + DEFAULTSUFFIX)),
        BRANDING_MEDIUM(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.BRANDING_MEDIUM.getNum() + DEFAULTSUFFIX)),
        BRANDING_BIG(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.BRANDING_BIG.getNum() + DEFAULTSUFFIX)),
        STDBTN_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.STDBTN_DEF.getNum() + DEFAULTSUFFIX)),
        STDBTN_KFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.STDBTN_KFOC.getNum() + DEFAULTSUFFIX)),
        STDBTN_MFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.STDBTN_MFOC.getNum() + DEFAULTSUFFIX)),
        STDBTN_KFOCMFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.STDBTN_KFOCMFOC.getNum() + DEFAULTSUFFIX)),
        STDBTN_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.STDBTN_PRE.getNum() + DEFAULTSUFFIX)),
        PWFIELD_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.PWFIELD_DEF.getNum() + DEFAULTSUFFIX)),
        PWFIELD_DIS(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.PWFIELD_DIS.getNum() + DEFAULTSUFFIX)),
        PWFIELD_KFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.PWFIELD_KFOC.getNum() + DEFAULTSUFFIX)),
        PWFIELD_MFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.PWFIELD_MFOC.getNum() + DEFAULTSUFFIX)),
        SLIDER_BAR_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_BAR_DEF.getNum() + DEFAULTSUFFIX)),
        SLIDER_BAR_FOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_BAR_FOC.getNum() + DEFAULTSUFFIX)),
        SLIDER_BAR_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_BAR_PRE.getNum() + DEFAULTSUFFIX)),
        SLIDER_UP_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_UP_DEF.getNum() + DEFAULTSUFFIX)),
        SLIDER_UP_FOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_UP_FOC.getNum() + DEFAULTSUFFIX)),
        SLIDER_UP_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_UP_PRE.getNum() + DEFAULTSUFFIX)),
        SLIDER_DOWN_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_DOWN_DEF.getNum() + DEFAULTSUFFIX)),
        SLIDER_DOWN_FOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_DOWN_FOC.getNum() + DEFAULTSUFFIX)),
        SLIDER_DOWN_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_DOWN_PRE.getNum() + DEFAULTSUFFIX)),
        SLIDER_MID_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_MID_DEF.getNum() + DEFAULTSUFFIX)),
        SLIDER_MID_FOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_MID_FOC.getNum() + DEFAULTSUFFIX)),
        SLIDER_MID_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SLIDER_MID_PRE.getNum() + DEFAULTSUFFIX)),
        ACCESSSYMBOL(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.ACCESSSYMBOL.getNum() + DEFAULTSUFFIX)),
        SHUTDOWNSYMBOL(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWNSYMBOL.getNum() + DEFAULTSUFFIX)),
        SHUTDOWNUPDATESYMBOL(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWNUPDATESYMBOL.getNum() + DEFAULTSUFFIX)),
        SHUTDOWNARROWSYMBOL(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWNARROWSYMBOL.getNum() + DEFAULTSUFFIX)),
        USERLIST_IMAGEFRAME_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.USERLIST_IMAGEFRAME_DEF.getNum() + DEFAULTSUFFIX)),
        USERLIST_IMAGEFRAME_MFOC(Constants.PROGRAM_WORKBASE_IMG_PATH
                .resolve(UIResNumbers.USERLIST_IMAGEFRAME_MFOC.getNum() + DEFAULTSUFFIX)),
        USERLIST_IMAGEFRAME_SEL(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.USERLIST_IMAGEFRAME_SEL.getNum() + DEFAULTSUFFIX)),
        USERLIST_IMAGEFRAME_FOCSEL(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.USERLIST_IMAGEFRAME_FOCSEL.getNum()
                + DEFAULTSUFFIX)),
        USERTILE_IMAGEFRAME_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.USERTILE_IMAGEFRAME_DEF.getNum() + DEFAULTSUFFIX)),
        COMBTNARROW_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.COMBTNARROW_DEF.getNum() + DEFAULTSUFFIX)),
        COMBTNARROW_MFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.COMBTNARROW_MFOC.getNum() + DEFAULTSUFFIX)),
        COMBTNARROW_KFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.COMBTNARROW_KFOC.getNum() + DEFAULTSUFFIX)),
        COMBTNARROW_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.COMBTNARROW_PRE.getNum() + DEFAULTSUFFIX)),
        COMBTN_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.COMBTN_DEF.getNum() + DEFAULTSUFFIX)),
        COMBTN_MFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.COMBTN_MFOC.getNum() + DEFAULTSUFFIX)),
        COMBTN_KFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.COMBTN_KFOC.getNum() + DEFAULTSUFFIX)),
        COMBTN_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.COMBTN_PRE.getNum() + DEFAULTSUFFIX)),
        PWBTN_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.PWBTN_DEF.getNum() + DEFAULTSUFFIX)),
        PWBTN_KFOCMFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.PWBTN_KFOCMFOC.getNum() + DEFAULTSUFFIX)),
        PWBTN_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.PWBTN_PRE.getNum() + DEFAULTSUFFIX)),
        SHUTDOWN_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWN_DEF.getNum() + DEFAULTSUFFIX)),
        SHUTDOWN_MFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWN_MFOC.getNum() + DEFAULTSUFFIX)),
        SHUTDOWN_KFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWN_KFOC.getNum() + DEFAULTSUFFIX)),
        SHUTDOWN_KFOCMFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWN_KFOCMFOC.getNum() + DEFAULTSUFFIX)),
        SHUTDOWN_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWN_PRE.getNum() + DEFAULTSUFFIX)),
        SHUTDOWNMENU_DEF(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWNMENU_DEF.getNum() + DEFAULTSUFFIX)),
        SHUTDOWNMENU_MFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWNMENU_MFOC.getNum() + DEFAULTSUFFIX)),
        SHUTDOWNMENU_KFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWNMENU_KFOC.getNum() + DEFAULTSUFFIX)),
        SHUTDOWNMENU_KFOCMFOC(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWNMENU_KFOCMFOC.getNum() + DEFAULTSUFFIX)),
        SHUTDOWNMENU_PRE(Constants.PROGRAM_WORKBASE_IMG_PATH.resolve(UIResNumbers.SHUTDOWNMENU_PRE.getNum() + DEFAULTSUFFIX));

        private Path path;

        private UIDefaultImagePaths(Path path) {
            this.path = path;
        }

        public Path getPath() {
            return this.path;
        }
    }

    enum Comment {

        MAIN("This file contains all layout changes. Please do not edit!"),
        SKININFO("Basic skininfos"),
        IMG_BRD("Branding"),
        IMG_PWFIELD("Passwordfield"),
        IMG_SLIDER("Slider"),
        IMG_SYMBOL("Symbols"),
        IMG_USERTILE("Usertile"),
        IMG_COMMANDBTN("Button-Command"),
        IMG_STANDARDBTN("Button-Standard/Generic"),
        IMG_SHUTDOWNBTN("Button-Shutdown"),
        IMG_WINDOW("Button-Subwindow"),
        IMG_BACKGROUND("Button-Backgroundimage"),

        LAYOUT("Layoutproperties"),
        LYT_BRANDING("Branding"),
        LYT_WINDOW("Subwindow"),
        LYT_BUTTONS("Buttons .."),
        LYT_BUTTONS_ACC("Accessibility"),
        LYT_BUTTONS_COM("Command"),
        LYT_BUTTONS_STD("Standard/Generic"),
        LYT_BUTTONS_PW("Password"),
        LYT_BUTTONS_SHD("Shutdown"),
        LYT_BUTTONS_SHDMENU("Shutdownmenu"),
        LYT_BUTTONS_LOCALE("Locale"),
        LYT_SECURITYOPTIONS("Securitymenu/Ctrl-Alt-Del"),
        LYT_OPTIONSBAR("Optionsbar"),
        LYT_USERLIST("Userlist"),
        LYT_USERTILE("Usertile"),
        LYT_USERTILE_IMAGE("Usertile(image)"),
        LYT_USERLIST_IMAGE("Userlist(image)"),
        LYT_PWFIELD("Passwordfield"),
        LYT_SHIFTS("Shiftingproperties of the passwordarea"),
        LYT_LOADINGSTATUS("Loading blend in"),
        LYT_FONTSHADOW("Fontshadow"),
        LYT_FONT_SHD("Font-Shutdown"),
        LYT_FONT_SHDMENU("Font-Shutdownmenu"),
        LYT_FONT_PW("Font-Passwordfield"),
        LYT_FONT_RESETPW("Font-Reset Password Button"),
        LYT_FONT_LOADINGSTATUS("Font-Loadingstatus"),
        LYT_FONT_SHORTMSG("Font-short message"),
        LYT_FONT_COM("Font-Command"),
        LYT_FONT_STD("Font-Standard/Generic"),
        LYT_STATUSFONT("Font-Status"),
        LYT_STATUSFONT_Z("Font-Status zoomed /Usertile"),
        LYT_NAMEFONT("Font-Name"),
        LYT_NAMEFONT_Z("Font-Name zoomed /Usertile");

        private String com;

        private Comment(String comment) {
            this.com = comment;
        }

        public String getComment() {
            return this.com;
        }

        @Override
        public String toString() {
            return this.com;
        }
    }

    public enum Imagetype {
        DEFAULT,
        KEYFOCUS,
        MOUSEFOCUS,
        MOUSEFOCUS_KEYFOCUS,
        FOCUS,
        PRESSED,
        DISABLED,
        SELECTED,
        FOCUSSELECTED;
    }

    public enum Fonttype {
        NORMAL("normal"), BOLD("bold");

        private String s;

        Fonttype(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return this.s;
        }
    }

    public enum Fontstyle {
        NONE("none"),
        SHADOW("shadow"),
        UNDERLINE("underline"),
        SHADOWUNDERLINE("shadow|underline");

        private String s;

        Fontstyle(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return this.s;
        }
    }

    public enum Position {
        TOPLEFT,
        TOP,
        TOPRIGHT,
        LEFT,
        CENTER,
        RIGHT,
        BOTTOMLEFT,
        BOTTOM,
        BOTTOMRIGHT,
        NONE;
    }

    public enum Animation {// XXX

        RECTANGLE_H_FAST("rectangleh|s|fast"),
        RECTANGLE_V_FAST("rectanglev|s|fast"),
        RECTANGLE_H_SLOW("rectangleh|s|slow"),
        RECTANGLE_V_SLOW("rectanglev|s|slow"),
        ORIGINAL(null);

        private String s;

        Animation(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return this.s;
        }
    }

    public enum Info {

        SKIN_NAME("Skinname"),
        SKIN_PREVIEW("Skinpreviewimage"),
        SKIN_AUTHOR("Skinauthor"),
        SKIN_WEBSITE("Skinwebsite");

        private String id;

        private Info(String id) {
            this.id = id;
        }

        public String getID() {
            return this.id;
        }
    }

    enum Layout {
        BACKGROUND,

        WINDOW_ACTIVE,
        WINDOW_HEIGHT,
        WINDOW_WIDTH,
        WINDOW_BORDERTHICKNESS,
        WINDOW_PADDING,
        WINDOW_POSITION,
        WINDOW_ANIMATION,
        WINDOW_INNERANIMATION,

        SECURITYOPTIONS_POSITION,
        SECURITYOPTIONS_PADDING,

        LOCALEBTN_VISIBILITY,
        LOCALEBTN_POSITION,
        LOCALEBTN_PADDING,

        ACCBTN_HEIGHT,
        ACCBTN_WIDTH,
        ACCBTN_POSITION,

        COMBTN_MINHEIGHT,
        COMBTN_MINWIDTH,
        COMBTN_PADDING,
        COMBTN_MARGIN,
        COMBTN_BORDERTHICKNESS,
        COMBTN_CONTENTALIGN,
        COMBTN_BTNVISIBILITY_SWITCH,
        COMBTN_BTNVISIBILITY_LOCK,
        COMBTN_BTNVISIBILITY_LOGOUT,
        COMBTN_BTNVISIBILITY_PASSWORD,
        COMBTN_BTNVISIBILITY_TASKMANAGER,
        COMBTN_FONT,
        COMBTN_FONTSIZE,
        COMBTN_FONTTYPE,
        COMBTN_FONTSTYLE,
        COMBTN_FOREGROUND,

        STDBTN_HEIGHT,
        STDBTN_WIDTH,
        STDBTN_POSITION,
        STDBTN_BORDERTHICKNESS,
        STDBTN_FONT,
        STDBTN_FONTSIZE,
        STDBTN_FONTTYPE,
        STDBTN_FONTSTYLE,
        STDBTN_FOREGROUND,
        STDBTN_MARGIN,
        STDBTN_PADDING,

        PWBTN_HEIGHT,
        PWBTN_WIDTH,
        PWBTN_BORDERTHICKNESS,
        PWBTN_MARGIN,

        SHDFRAME_LAYOUT,
        SHDFRAME_POSITION,

        SHDBTN_HEIGHT,
        SHDBTN_WIDTH,
        SHDBTN_BORDERTHICKNESS,
        SHDBTN_MARGIN,
        SHDBTN_POSITION,
        SHDBTN_CONTENT,

        SHDMENU_HEIGHT,
        SHDMENU_WIDTH,
        SHDMENU_BORDERTHICKNESS,
        SHDMENU_MARGIN,
        SHDMENU_POSITION,
        SHDMENU_CONTENT,

        ACCSHD_COMBINED,
        ACCSHD_POSITION,

        ACC_FIX,

        FONTSHADOW,

        BRANDING_POSITION,

        OPTIONSBAR_HEIGHT,

        USERLIST_HEIGHT,
        USERLIST_LAYOUT,
        USERLIST_POSITION,
        USERLIST_PADDING,

        USERLIST_IMAGE_HEIGHT,
        USERLIST_IMAGE_WIDTH,
        USERLIST_IMAGEFRAME_HEIGHT,
        USERLIST_IMAGEFRAME_WIDTH,
        USERLIST_IMAGE_PADDING,

        USERTILE_LAYOUT,
        USERTILE_POSITION,
        USERTILE_PWAREAONRIGHT,
        USERTILE_STATUSONRIGHT,
        USERTILE_IMAGE_POSITION,

        USERTILE_IMAGE_HEIGHT,
        USERTILE_IMAGE_WIDTH,
        USERTILE_IMAGEFRAME_HEIGHT,
        USERTILE_IMAGEFRAME_WIDTH,
        USERTILE_IMAGE_PADDING,

        PWFIELD_HEIGHT,
        PWFIELD_WIDTH,
        PWFIELD_BORDERTHICKNESS,
        PWFIELD_MARGIN,

        PWAREA_UPSHIFT,
        PWAREA_DOWNSHIFT,
        PWAREA_RIGHTSHIFT,

        LOADINGSTATUS_WIDTH,
        LOADINGSTATUS_BORDERTHICKNESS,
        LOADINGSTATUS_ANIMATION,
        LOADINGSTATUS_RINGANIMVISIBILITY,

        SHD_FONT,
        SHD_FONTSIZE,
        SHD_FONTTYPE,
        SHD_FONTSTYLE,
        SHD_FOREGROUND,

        SHDMENU_FONT,
        SHDMENU_FONTSIZE,
        SHDMENU_FONTTYPE,
        SHDMENU_FONTSTYLE,
        SHDMENU_FOREGROUND,

        PWFIELD_FONT,
        PWFIELD_FONTSIZE,
        PWFIELD_FONTTYPE,
        PWFIELD_FONTSTYLE,
        PWFIELD_FOREGROUND,

        RESETPW_FONT,
        RESETPW_FONTSIZE,
        RESETPW_FONTTYPE,
        RESETPW_FONTSTYLE,
        RESETPW_FOREGROUND,

        LOADINGSTATUS_FONT,
        LOADINGSTATUS_FONTSIZE,
        LOADINGSTATUS_FONTTYPE,
        LOADINGSTATUS_FONTSTYLE,
        LOADINGSTATUS_FOREGROUND,

        SHORTMESSAGE_FONT,
        SHORTMESSAGE_FONTSIZE,
        SHORTMESSAGE_FONTTYPE,
        SHORTMESSAGE_FONTSTYLE,
        SHORTMESSAGE_FOREGROUND,

        STATUSTEXT_FONT,
        STATUSTEXT_FONTSIZE,
        STATUSTEXT_FONTTYPE,
        STATUSTEXT_FONTSTYLE,
        STATUSTEXT_FOREGROUND,

        STATUSTEXTZOOMED_FONT,
        STATUSTEXTZOOMED_FONTSIZE,
        STATUSTEXTZOOMED_FONTTYPE,
        STATUSTEXTZOOMED_FONTSTYLE,
        STATUSTEXTZOOMED_FOREGROUND,

        NAMETEXT_FONT,
        NAMETEXT_FONTSIZE,
        NAMETEXT_FONTTYPE,
        NAMETEXT_FONTSTYLE,
        NAMETEXT_FOREGROUND,

        NAMETEXTZOOMED_FONT,
        NAMETEXTZOOMED_FONTSIZE,
        NAMETEXTZOOMED_FONTTYPE,
        NAMETEXTZOOMED_FONTSTYLE,
        NAMETEXTZOOMED_FOREGROUND;
    }

    enum Images {
        BRD_SMALL,
        BRD_MEDIUM,
        BRD_BIG,
        IMG_PW_DEF,
        IMG_PW_DIS,
        IMG_PW_KFOC,
        IMG_PW_MFOC,
        IMG_SLIDERBAR_DEF,
        IMG_SLIDERBAR_FOC,
        IMG_SLIDERBAR_PRE,
        IMG_SLIDERMID_DEF,
        IMG_SLIDERMID_FOC,
        IMG_SLIDERMID_PRE,
        IMG_SLIDERUP_DEF,
        IMG_SLIDERUP_FOC,
        IMG_SLIDERUP_PRE,
        IMG_SLIDERDOWN_DEF,
        IMG_SLIDERDOWN_FOC,
        IMG_SLIDERDOWN_PRE,
        IMG_SYM_ACCESS,
        IMG_SYM_SHUTDOWN,
        IMG_SYM_SHUTDOWNUPDATE,
        IMG_SYM_SHUTDOWNMENU,
        IMG_USERTILE_MFOC,
        IMG_USERTILE_SEL,
        IMG_USERTILE_FOCSEL,
        IMG_USERTILE_DEF,
        IMG_USERTILE_ZOOMED,
        IMG_COMBTNARROW_KFOC,
        IMG_COMBTNARROW_MFOC,
        IMG_COMBTNARROW_PRE,
        IMG_COMBTNARROW_DEF,
        IMG_COMBTN_KFOC,
        IMG_COMBTN_MFOC,
        IMG_COMBTN_PRE,
        IMG_COMBTN_DEF,
        IMG_STDBTN_KFOC,
        IMG_STDBTN_MFOC,
        IMG_STDBTN_KFOCMFOC,
        IMG_STDBTN_PRE,
        IMG_STDBTN_DEF,
        IMG_PWBTN_KFOCMFOC,
        IMG_PWBTN_PRE,
        IMG_PWBTN_DEF,
        IMG_SHUTDOWNBTN_KFOCMFOC,
        IMG_SHUTDOWNBTN_KFOC,
        IMG_SHUTDOWNBTN_MFOC,
        IMG_SHUTDOWNBTN_PRE,
        IMG_SHUTDOWNBTN_DEF,
        IMG_SHUTDOWNMENU_KFOCMFOC,
        IMG_SHUTDOWNMENU_KFOC,
        IMG_SHUTDOWNMENU_MFOC,
        IMG_SHUTDOWNMENU_PRE,
        IMG_SHUTDOWNMENU_DEF,
        IMG_WINDOW,
        IMG_BACKGROUND;
    }

    enum SkinPreview {
        NAME,
        AUTHOR,
        WEBSITE;
    }
}
