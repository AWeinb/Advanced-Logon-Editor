/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.uiFile;

final class UIFileConstants {

    static final int[] LINENRS_COMBTN_MARGIN = { 21, 24, 27, 30, 33 };

    static final String ACCBUTTON_FIX_REGEX_1 = "<button";
    static final String ACCBUTTON_FIX_REGEX_2 = "<element";
    static final String ACCBUTTON_FIX_REPLACE_1 = "<element layout=\"flowlayout\\(0,1,2,2\\)\" layoutpos=\"left\">";
    static final String ACCBUTTON_FIX_REPLACE_2 = "</element>";
    static final int LINENR_ACCBUTTON_FIX_1 = 89;
    static final int LINENR_ACCBUTTON_FIX_2 = 91;

    static final int[] LINENRS_FONTSHADOW = { 42, 49, 57, 63, 195, 199, 525, 538, 547, 569, 725, 742, 748, 754,
            760, 767, 801, 969 };

    static final String REPLACE_COMBTN = "\"bottomright\"";
    static final String REPLACE_SHDFRAME_P = "borderlayout()";
    static final String REPLACE_USERTILE_LAYOUT = "flowlayout";
    static final String REPLACE_PWAREA_LAYOUT = "flowlayout\\(0,0,2\\)";
    static final String REPLACE_PWSTATUS_LAYOUT = "flowlayout\\(0,0,2\\)";
    static final String REPLACE_COMBTNVISBILITY = "<element layout=\"verticalflowlayout(0,0,0,1)\" sheet=\"CommandLinks\" padding=\"rect(0rp, 4rp, 0rp, 4rp)\">";

    static final String PWAREA_HEIGHT_SHIFT = "<element height=\"%rp\"/>";
    static final String PWAREA_WIDTH_SHIFT = "<element width=\"%rp\"/>";

    static final String USERTILE_IMAGEPOSITION_CHAR = "%";

    static final class UIPositions {

        private UIPositions() {
        }

        enum Window {

            TOPLEFT("(0,0,0,0)"),
            TOP("(0,2,0,0)"),
            TOPRIGHT("(0,1,0,0)"),
            LEFT("(0,0,0,2)"),
            CENTER("(0,2,0,2)"),
            RIGHT("(0,1,0,2)"),
            BOTTOMLEFT("(0,0,0,1)"),
            BOTTOM("(0,2,0,1)"),
            BOTTOMRIGHT("(0,1,0,1)");

            private final String string;

            private Window(String string) {
                this.string = string;
            }

            String getString() {
                return this.string;
            }
        }

        enum SecurityOptions {

            TOPLEFT("(1,0,2,0)"),
            TOP("(1,2,2,0)"),
            TOPRIGHT("(1,1,2,0)"),
            LEFT("(1,0,2,2)"),
            CENTER("(1,2,2,2)"),
            RIGHT("(1,1,2,2)"),
            BOTTOMLEFT("(1,0,2,1)"),
            BOTTOM("(1,2,2,1)"),
            BOTTOMRIGHT("(1,1,2,1)");

            private final String string;

            private SecurityOptions(String string) {
                this.string = string;
            }

            String getString() {
                return this.string;
            }
        }

        enum LanguageButton {

            TOPLEFT("(0,0,0,0)"),
            TOP("(0,0,2,0)"),
            TOPRIGHT("(0,0,1,0)"),
            LEFT("(0,2,0,0)"),
            CENTER("(0,2,2,0)"),
            RIGHT("(0,2,1,0)"),
            BOTTOMLEFT("(0,1,0,0)"),
            BOTTOM("(0,1,2,0)"),
            BOTTOMRIGHT("(0,1,1,0)");

            private final String string;

            private LanguageButton(String string) {
                this.string = string;
            }

            String getString() {
                return this.string;
            }
        }

        enum StandardButton {

            LEFT("(0,1,0,2)"),
            CENTER("(0,1,2,2)"),
            RIGHT("(0,1,1,2)");

            private final String string;

            private StandardButton(String string) {
                this.string = string;
            }

            String getString() {
                return this.string;
            }
        }

        enum Userlist {

            TOPLEFT("(0,0,0,0)", "(0,0,0,0)"),
            TOP("(0,0,0,2)", "(0,2,0,0)"),
            TOPRIGHT("(0,0,0,1)", "(0,1,0,0)"),
            LEFT("(0,2,0,0)", "(0,0,0,2)"),
            CENTER("(0,2,0,2)", "(0,2,0,2)"),
            RIGHT("(0,2,0,1)", "(0,1,0,2)"),
            BOTTOMLEFT("(0,1,0,0)", "(0,0,0,1)"),
            BOTTOM("(1,1,0,0)", "(0,2,0,1)"),
            BOTTOMRIGHT("(0,1,0,1)", "(0,1,0,1)");

            private final String stringH;
            private final String stringV;

            private Userlist(String stringH, String stringV) {
                this.stringH = stringH;
                this.stringV = stringV;
            }

            String getStringHorizontal() {
                return this.stringH;
            }

            String getStringVertical() {
                return this.stringV;
            }
        }

        enum Usertile {

            TOPLEFT("(0,0,0,0)", "(0,0," + USERTILE_IMAGEPOSITION_CHAR + ",0)"),
            TOP("(0,0,2,0)", "(0,2," + USERTILE_IMAGEPOSITION_CHAR + ",0)"),
            TOPRIGHT("(0,0,1,0)", "(0,1," + USERTILE_IMAGEPOSITION_CHAR + ",0)"),
            LEFT("(0,0,0,2)", "(0,0," + USERTILE_IMAGEPOSITION_CHAR + ",2)"),
            CENTER("(0,0,2,2)", "(0,2," + USERTILE_IMAGEPOSITION_CHAR + ",2)"),
            RIGHT("(0,0,1,2)", "(0,1," + USERTILE_IMAGEPOSITION_CHAR + ",2)"),
            BOTTOMLEFT("(0,0,0,1)", "(0,0," + USERTILE_IMAGEPOSITION_CHAR + ",1)"),
            BOTTOM("(0,0,2,1)", "(0,2," + USERTILE_IMAGEPOSITION_CHAR + ",1)"),
            BOTTOMRIGHT("(0,0,1,1)", "(0,1," + USERTILE_IMAGEPOSITION_CHAR + ",1)"),

            LEFT_PA("0"),
            CENTER_PA("2"),
            RIGHT_PA("1");

            private String stringV;
            private String stringH;
            private String pictureAlign;

            private Usertile(final String stringV, final String stringH) {
                this.stringV = stringV;
                this.stringH = stringH;
            }

            private Usertile(final String pictureAlign) {
                this.pictureAlign = pictureAlign;
            }

            String getStringVertical() {
                return this.stringV;
            }

            String getStringHorizontal() {
                return this.stringH;
            }

            String getStringPictureAlign() {
                return this.pictureAlign;
            }
        }
    }

    enum UILayout {
        WINDOW_MISC("id=\"atom\\(Window\\)\"", 15),
        WINDOW_POSITION("\\(1,2,2,2\\)", 9),

        SECURITYMENU_POSITION("\"verticalflowlayout\\(1,2,2,1\\)\"", 19),
        WINDOW_INNERANIMATION(">", 16),

        LOCALEBTN_MISC(">", 5),
        LOCALEBTN_POSITION("\\(0, 0, 0, 0\\)", 5),
        LOCALEBTN_PADDING("padding=\"rect\\(5rp,5rp,5rp,5rp\\)\"", 5),

        ACCBTN_HEIGHT("height\\s=\\s\"28rp\"", 246),
        ACCBTN_WIDTH("width\\s=\\s\"38rp\"", 245),
        ACCBTN_POSITION("layoutpos=\"left\"", 89),

        COMBTN_MINHEIGHT("minsize=\"size\\(190rp,26rp\\)\"", 964),
        COMBTN_MINWIDTH(null, 964),
        COMBTN_PADDING("padding=\"rect\\(20rp,0rp,0rp,1rp\\)\"", 971),
        COMBTN_MARGIN(" margin=\"rect\\(0rp, 0rp, 0rp, 6rp\\)\"", -1),
        COMBTN_CONTENTALIGN("contentalign=\"bottomleft\"", 959),
        COMBTN_BTNVISIBILITY_1(
                "<element layout=\"verticalflowlayout\\(0,0,0,1\\)\" sheet=\"CommandLinks\" padding=\"rect\\(0rp, 9rp, 0rp, 9rp\\)\">", 20),
        COMBTN_BTNVISIBILITY_2("</button>", 23),
        COMBTN_BTNVISIBILITY_3("</button>", 26),
        COMBTN_BTNVISIBILITY_4("</button>", 29),
        COMBTN_BTNVISIBILITY_5("</button>", 32),
        COMBTN_FONT("font = \"resstr\\(11802\\)\"", 969),
        COMBTN_BORDERTHICKNESS_1("borderthickness=\"rect\\(5,5,5,4\\)\"", 978),
        COMBTN_BORDERTHICKNESS_2("borderthickness=\"rect\\(5,5,5,4\\)\"", 988),
        COMBTN_BORDERTHICKNESS_3("borderthickness=\"rect\\(5,5,5,4\\)\"", 998),

        STDBTN_MINSIZE("minsize=\"size\\(93rp,28rp\\)\"", 207),
        STDBTN_FONT("font = \"resstr\\(11802\\)\"", 199),
        STDBTN_FOREGROUND("foreground = \"white\"", 199),
        STDBTN_POSITION("\\(0,1,2,2\\)", 67),
        STDBTN_PADDING("padding=\"rect\\(0rp, 10rp, 0rp, 0rp\\)\"", 67),
        STDBTN_MARGIN("margin=\"rect\\(0rp, 30rp, 0rp, 5rp\\)\"", 67),
        STDBTN_BORDERTHICKNESS("borderthickness = \"rect\\(4rp, 3rp, 4rp, 3rp\\)\"", 209),

        PWBTN_HEIGHT("height=\"30rp\"", 113),
        PWBTN_WIDTH("width=\"30rp\"", 113),
        PWBTN_MARGIN("margin = \"rect\\(4rp, 0rp, 0rp, 0rp\\)\"", 809),
        PWBTN_BORDERTHICKNESS("borderthickness = \"rect\\(1rp, 1rp, 1rp, 1rp\\)\"", 812),

        SHDFRAME_LAYOUT("flowlayout\\(\\)", 94),
        SHDFRAME_POSITION("layoutpos=\"right\"", 91),

        SHDBTN_HEIGHT("height\\s=\\s\"28rp\"", 382),
        SHDBTN_WIDTH("width\\s=\\s\"38rp\"", 381),
        SHDBTN_MARGIN("margin=\"rect\\(0,0,0,0\\)\"", 386),
        SHDBTN_POSITION("/>", 95),
        SHDBTN_BORDERTHICKNESS("borderthickness = \"rect\\(4rp, 1rp, 4rp, 1rp\\)\"", 383),
        SHDBTN_CONTENT("resbmp\\(12215,2,-1,0,0,1,1\\)", 385),
        SHDBTN_FONT(null, 389),

        SHDMENU_HEIGHT("height = \"28rp\"", 431),
        SHDMENU_WIDTH("width = \"20rp\"", 430),
        SHDMENU_MARGIN("margin=\"rect\\(0,0,0,0\\)\"", 435),
        SHDMENU_POSITION("/>", 96),
        SHDMENU_BORDERTHICKNESS("borderthickness = \"rect\\(4rp, 3rp, 4rp, 3rp\\)\"", 434),
        SHDMENU_CONTENT("resbmp\\(12217,2,-1,0,0,1,1\\)", 432),
        SHDMENU_FONT(null, 437),
        SHDMENU_FONT_FIX("shortcut=\"115\"", 96),

        ACCSHD_COMBINED(null, 89),
        ACCSHD_POSITION("layoutpos=\"right\"", 89),

        ACC_FIX(null, -1),

        BRANDING_POSITION(">", 85),

        OPTIONSBAR_HEIGHT("height=\"96rp\"", 84),

        USERLIST_HEIGHT("height=\"475rp\"", 115),
        USERLIST_LAYOUT("flowlayout", 117),
        USERLIST_POSITION("\\(1,1,0,0\\)", 117),
        USERLIST_PADDING("padding = \"rect\\(12rp, 0, 0, 0\\)\"", 117),

        USERLIST_IMAGE_HEIGHT("height = \"48rp\"", 717),
        USERLIST_IMAGE_WIDTH("width = \"48rp\"", 716),
        USERLIST_IMAGEFRAME_HEIGHT("height = \"80rp\"", 634),
        USERLIST_IMAGEFRAME_WIDTH("width = \"80rp\"", 633),
        USERLIST_IMAGEOVERLAY_HEIGHT("height = \"80rp\"", 667),
        USERLIST_IMAGEOVERLAY_WIDTH("width = \"80rp\"", 666),
        USERLIST_IMAGE_PADDING("padding=\"rect\\(16rp, 16rp, 16rp, 16rp\\)\"", 646),

        USERTILE_LAYOUT("verticalflowlayout", 120),
        USERTILE_POSITION("\\(0,2,2\\)", 120),
        USERTILE_PWAREAONRIGHT("verticalflowlayout\\(0,0,2\\)", 127),
        USERTILE_STATUSONRIGHT("verticalflowlayout\\(0,0,2\\)", 128),

        USERTILE_IMAGE_HEIGHT("height = \"126rp\"", 712),
        USERTILE_IMAGE_WIDTH("width = \"126rp\"", 711),
        USERTILE_IMAGEFRAME_HEIGHT("height = \"190rp\"", 628),
        USERTILE_IMAGEFRAME_WIDTH("width = \"190rp\"", 627),
        USERTILE_IMAGEOVERLAY_HEIGHT("height = \"190rp\"", 654),
        USERTILE_IMAGEOVERLAY_WIDTH("width = \"190rp\"", 653),
        USERTILE_IMAGE_PADDING("padding=\"rect\\(32rp, 32rp, 32rp, 32rp\\)\"", 641),

        PWFIELD_HEIGHT("height = \"25rp\"", 766),
        PWFIELD_WIDTH("width = \"225rp\"", 765),
        PWFIELD_MARGIN("margin = \"rect\\(0rp, 3rp, 0rp, 3rp\\)\"", 768),
        PWFIELD_BORDERTHICKNESS("borderthickness = \"rect\\(4, 4, 4, 4\\)\"", 770),

        PWAREA_UPSHIFT("</element>", 137),
        PWAREA_DOWNSHIFT(">", 127),
        PWAREA_RIGHTSHIFT("</ZoomableElement>", 126),

        FONTSHADOW("shadowintensity\\s*=\\s*\"\\d*\"", -1),

        LOADINGSTATUS_ANIMATION(">", 77),
        LOADINGSTATUS_RINGANIM_VISIBILITY("width=\"20rp\"", 78),
        LOADINGSTATUS_MISC("/>", 79),

        SHORTMESSAGE_FONT("font=\"resstr\\(11800\\)\"", 57),
        LOADINGSTATUS_FONT("font = \"resstr\\(11804\\)\"", 525),
        LOADINGSTATUS_FOREGROUND("foreground = \"white\"", 523),
        RESETPW_FONT("font = \"resstr\\(11801\\)\"", 725),
        PWFIELD_FONT("font = \"resstr\\(11800\\)\"", 767),
        STATUSTEXT_FONT("font = \"resstr\\(11800\\)\"", 742),
        STATUSTEXTZOOMED_FONT("font = \"resstr\\(11800\\)\"", 748),
        NAMETEXT_FONT("font = \"resstr\\(11800\\)\"", 754),
        NAMETEXTZOOMED_FONT("font = \"resstr\\(11804\\)\"", 760);

        private final String regex;
        private final int nr;

        UILayout(String regex, int linenumber) {
            this.regex = regex;
            this.nr = linenumber;
        }

        String getRegex() {
            return this.regex;
        }

        int getLine() {
            return this.nr;
        }
    }

    enum UIRegex {

        RECT("rect"),
        ARGB("argb"),
        RP("rp"),
        HEIGHT(" height="),
        WIDTH(" width="),
        MINSIZE(" minsize=\"size"),
        FONT(" font="),
        FONTCOLOR(" foreground="),
        BORDERTHICKNESS(" borderthickness="),
        BORDERSTYLE(" borderstyle="),
        BORDERCOLOR(" bordercolor="),
        PADDING(" padding="),
        MARGIN(" margin="),
        FLOWLAYOUT(" flowlayout"),
        VERTFLOWLAYOUT(" verticalflowlayout"),
        LAYOUTPOS(" layoutpos="),
        VISIBILITY(" visible="),
        FONTSHADOW(" shadowintensity="),
        BACKGROUND(" background = \"resbmp\\(\\%,7,-1,0,0,1,1\\)\""),
        CONTENTALIGN(" contentalign="),
        ANIMATION(" animation="),
        REPLACE_HELPER("\\%"),
        LINEEND(">"),
        ELEMENT("<element>"),
        ELEMENT_END("</element>");

        private String regex;

        private UIRegex(String regex) {
            this.regex = regex;
        }

        @Override
        public String toString() {
            return this.regex;
        }
    }
}
