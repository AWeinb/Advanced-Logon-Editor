/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import ale.Constants;
import ale.controller.Main;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui <br/>
 * Class  : GUIConstants <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>GUIConstants</code> class contains Strings or Numbers which are important for the gui.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 24.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class GUIConstants {

    public static final Dimension MIN_QUICKCHOOSER_DIM = new Dimension(900, 500);
    public static final Dimension MIN_EDITOR_DIM = new Dimension(1024, 600);
    public static final int CHOOSER_SIDEGAP_PERCENT = 40;
    public static final int EDITOR_SIDEGAP_PERCENT = 30;

    public static final int DEFAULT_MENUITEM_WIDTH = 150;
    public static final int DEFAULT_FIELD_WIDTH = 310;
    public static final Dimension DEFAULT_BUTTON_DIM = new Dimension(180, 25);
    public static final Dimension DEFAULT_COMBOBOX_DIM = new Dimension(180, 25);
    public static final Dimension DEFAULT_TAPPEDPANE_DIM = new Dimension(DEFAULT_FIELD_WIDTH, 130);
    public static final Dimension DEFAULT_FILECHOOSER_DIM = new Dimension(900, 500);

    public static final Color DEFAULT_BACKGROUND = Color.WHITE;
    public static final Font DEFAULT_MESSAGE_FONT = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font DEFAULT_HEADING_FONT = new Font("Segoe UI", Font.ITALIC, 14);
    public static final Font DEFAULT_BUTTON_FONT = new Font("Segoe UI", Font.ITALIC, 12);

    public static final Color WARNING_BG = new Color(240, 200, 140);

    public static final String DEFAULT_LOCALE = "EN";
    public static final int REPAINTTICK = 500;

    public static final Image PROGRAM_ICON = Toolkit.getDefaultToolkit().getImage(Constants.PROGRAM_PATH.resolve("icon.gif").toString());
    public static final Image PROGRAM_ICON_BIG = Toolkit.getDefaultToolkit().getImage(Constants.PROGRAM_PATH.resolve("ale.png").toString());
    public static final String PROGRAM_TITLE = "W7 Advanced Logon Editor (Ver. " + Main.VERSION + ")";
    public static final String PROGRAM_ABOUT_L1 = "Advanced Logon Editor\u00a9";
    public static final String PROGRAM_ABOUT_L2 = "Version: " + Main.VERSION;
    public static final String PROGRAM_ABOUT_L3 = "License: The best!";
    public static final String PROGRAM_ABOUT_L4 = "Warranty/ Garantie: NO!";
    public static final String PROGRAM_ABOUT = "Thanks to all who contributed to ALE!"
            + System.getProperty("line.separator")
            + System.getProperty("line.separator")
            + "Developers:"
            + System.getProperty("line.separator")
            + "A. Weinberger (ALE)" + System.getProperty("line.separator")
            + "Angus Johnson (Resource Hacker)" + System.getProperty("line.separator")
            + "Apache Software Foundation (Log4J)" + System.getProperty("line.separator")
            + "Carlo Pelliccia (jUnique)" + System.getProperty("line.separator")
            + "Jeremy Wood (A custom Colorpicker)" + System.getProperty("line.separator")
            + "Colin Mummery (A custom vertical layout)" + System.getProperty("line.separator")
            + System.getProperty("line.separator")
            + System.getProperty("line.separator")
            + "Testers" + System.getProperty("line.separator")
            + "Not enough yet :/" + System.getProperty("line.separator");
    public static final String PROGRAM_RIGHTS = "Copyright \u00a9 2013 A. Weinberger, All rights reserved."
            + System.getProperty("line.separator")
            + "Please do not redistribute without authorization!" + System.getProperty("line.separator")
            + "Please visit Art-of-AxP.tk for more info."
            + System.getProperty("line.separator") + System.getProperty("line.separator")
            + "This program is distributed in the hope that it will be useful," + System.getProperty("line.separator")
            + "but WITHOUT ANY WARRANTY!" + System.getProperty("line.separator")
            + " Without even the implied warranty of MERCHANTABILITY" + System.getProperty("line.separator")
            + "or FITNESS FOR A PARTICULAR PURPOSE.";

    public enum RightMenu {
        GEN_CHANGES_MENU(7),
        BTN_CHANGES_MENU(9),
        USERLIST_CHANGES_MENU(3),
        USERTILE_CHANGES_MENU(4),
        FONT_MENU(14);

        private int menuCount;

        private RightMenu(int menuCount) {
            this.menuCount = menuCount;
        }

        public int getMenuCount() {
            return this.menuCount;
        }
    }
}
