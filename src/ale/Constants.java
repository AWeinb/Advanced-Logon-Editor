/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ------------------------------------------------- <br/>
 * Package: ale <br/>
 * Class  : Constants <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>Constants</code> class contains important constants. Most of them are Paths and Filenames.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 21.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class Constants {

    private Constants() {
    }

    /** Basepath of the program. Needed to test the program in the IDE. */
    public static final Path PROGRAM_PATH = Paths.get(""); //generated-program

    /*
     * ########################################################################
     */

    // Helper used in this class as fileseparator char.
    private static final String s = System.getProperty("file.separator");

    /** Programdatapath. Basepath for programfiles. */
    public static final Path PROGRAM_DATA_PATH = PROGRAM_PATH.resolve("data" + s);

    /** Path to binary files. */
    public static final Path PROGRAM_BIN_PATH = PROGRAM_PATH.resolve("bin" + s);

    /** Path to basic files which are needed to create a skin. */
    public static final Path PROGRAM_WORKBASE_PATH = PROGRAM_DATA_PATH.resolve("base" + s);

    /** Path to basic images needed for a skin. */
    public static final Path PROGRAM_WORKBASE_IMG_PATH = PROGRAM_WORKBASE_PATH.resolve("img" + s);

    /** Workbase image count. To control if the workbase was modified the images are counted. */
    public static final int PROGRAM_WORKBASE_IMG_COUNT = 128;

    /** Path to temporary files. */
    public static final Path PROGRAM_TMP_PATH = PROGRAM_DATA_PATH.resolve("temp" + s);

    /** Path to the backup files. */
    public static final Path PROGRAM_BACKUP_PATH = PROGRAM_DATA_PATH.resolve("backup" + s);

    /** Path to skin files. */
    public static final Path PROGRAM_SKINS_PATH = PROGRAM_PATH.resolve("skins" + s);

    /** Path to the temporary skin file. */
    public static final Path PROGRAM_TMPSKIN_PATH = PROGRAM_TMP_PATH.resolve("skins" + s);

    /** Path to the program docs. */
    public static final Path PROGRAM_DOCS_PATH = PROGRAM_PATH.resolve("Documentation.pdf");

    /** Path to the locale files. */
    public static final Path PROGRAM_LOCALE_PATH = PROGRAM_DATA_PATH.resolve("locale");

    /** Path to the locale list file. It contains a list of available locales. */
    public static final Path PROGRAM_LANGPROP_PATH = PROGRAM_LOCALE_PATH.resolve("lang.properties");

    /** Locale filename. */
    public static final String PROGRAM_I18N = "locale";

    /** Locale fileending. */
    public static final String PROGRAM_I18N_SUFFIX = ".lang";

    /** Name of the skin image folder. */
    public static final String SKIN_IMGFOLDER = "images" + s;

    /** Filename of the default skin background. */
    public static final String SKIN_BG_NAME = "backgroundDefault.jpg";

    /** Filename of the main skin file which contains the changes. */
    public static final String SKIN_PROPFILE = ".skin";

    /** Filename of the skin preview file. */
    public static final String SKIN_PREVIEWFILE = ".preview";

    /** Filename of a completely transparent image. */
    public static final Path SKIN_TRANSPARENT_BG = PROGRAM_WORKBASE_PATH.resolve("transparent.png");

    /** Filename of the skin preview image. */
    public static final String SKIN_PREVIEWIMAGE = "preview.png";

    /** Fallback path to an image for the skinpreview. */
    public static final Path SKIN_PREVIEWIMAGE_DEFAULT = PROGRAM_WORKBASE_PATH.resolve("previewDefault.png");

    /** Maximum count of user input chars.  */
    public static final int SKIN_INPUT_MAXCHARS = 50;

    /** Skin names have to match the regex. */
    public static final String SKIN_NAME_REGEX = "([a-zA-Z0-9]+(_|-|\\s)?)+";

    /**  */
    public static final String SKIN_NUMBER_REGEX = "[1-9]++[0-9]*";

    /** Image used as default background in the editor if the user has not chosen another background for the skin. */
    public static final Path EDITOR_DEFAULT_BACKGROUNDIMAGE = Constants.PROGRAM_WORKBASE_PATH.resolve("background.png");

    /** Image used in the userlist preview in the editor. */
    public static final Path EDITOR_USERIMG = PROGRAM_WORKBASE_PATH.resolve("user.png");

    /** Image used in the userlist preview in the editor. */
    public static final Path EDITOR_GUESTIMG = PROGRAM_WORKBASE_PATH.resolve("guest.png");

    /** Path to the settings file. */
    public static final Path SETTINGS_PATH = PROGRAM_DATA_PATH.resolve(".settings");

    /** Path to the resource hacker. */
    public static final Path RESHACKER_PATH = PROGRAM_BIN_PATH.resolve("ResHacker.exe");

    /** Path to the resource hacker logfile. */
    public static final Path RESHACKER_LOG_PATH = PROGRAM_BIN_PATH.resolve(".log");

    /** Path to the resource hacker script. */
    public static final Path RESHACKER_SCRIPT_PATH = PROGRAM_TMP_PATH.resolve(".script");

    /** Resource hacker Uifile ID. */
    public static final String RESHACKER_UIFILE = "UIFILE";

    /** Uifile one resource number. */
    public static final int RESHACKER_UIFILE1_RESNUM = 12400;

    /** Uifile two resource number. */
    public static final int RESHACKER_UIFILE2_RESNUM = 12401;

    /** Uifile three resource number. */
    public static final int RESHACKER_UIFILE3_RESNUM = 12402;

    /** Unpacked textfile. Uifile one. */
    public static final Path UITEXT_TMP = PROGRAM_TMP_PATH.resolve("uifileTmp.txt");

    /** Path to a unchanged copy of the authui.dll uifile. */
    public static final Path UITEXT_WORKBASE = PROGRAM_WORKBASE_PATH.resolve(".uifile");

    /** Path to the temporary authui.dll. */
    public static final Path SYSTEMFILE_AUTHUI_TMPPATH = PROGRAM_TMP_PATH.resolve("authui_tmp.dll");

    /** Path to the temporary basebrd.dll */
    public static final Path SYSTEMFILE_BASEBRD_TMPPATH = PROGRAM_TMP_PATH.resolve("basebrd_tmp.dll");

    /** Path to the authui.dll backup. */
    public static final Path SYSTEMFILE_AUTHUI_BAKPATH = PROGRAM_BACKUP_PATH.resolve("authui_bak.dll");

    /** Path to the basebrd.dll backup. */
    public static final Path SYSTEMFILE_BASEBRD_BAKPATH = PROGRAM_BACKUP_PATH.resolve("basebrd_bak.dll");

    /** Path to acl backup path. */
    public static final Path ACL_BACKUP_PATH = PROGRAM_BACKUP_PATH.resolve("aclBackup");

    /** The suffix of the images used in the systemfiles. */
    public static final String DEFAULT_SKINIMAGE_TYPE = ".bmp";

    /** The suffix of the images used as input. */
    public static final String DEFAULT_INPUTIMAGE_TYPE = ".png";

    /** The suffix of the background image. */
    public static final String DEFAULT_BACKGROUND_TYPE = ".jpg";

    /** Filename suffix of a skinfile. */
    public static final String SKINFILE_SUFFIX = ".skinfile";

    /** Error enums used to control if errors occured and to indentify them. They help to prevent damage to the os. If one of the errors occures, it is serious. */
    public enum Error {
        /** The systemfiles were not found. */
        WRONG_SYSFILE_PATH,
        /** Error while trying to clean the temporary files. */
        CLEAN_MODEL_ERROR,
        /** The workbase files are missing. */
        WORKBASE_MISSING,
        /** The operatingsystem is not windows 7. */
        WRONG_OS,
        /** The workbase files were changed. */
        WORKBASE_CHANGED,
        /** The protection of the systemfiles could not be removed. */
        PROTECTION_ERROR,
        /** The resource hacker binary is missing. */
        RESHACKER_MISSING,
        /** The temporary files could not be created properly. */
        TEMP_CREATION_ERROR,
        /** The backup could not be created. */
        BACKUP_CREATION_ERROR,
        /** Error while extracting resources. */
        COPIED_UIFILE_NOT_AVAILABLE;
    }
}
