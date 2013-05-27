/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.controller;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import ale.Constants;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.controller <br/>
 * Class  : Settings <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>Settings</code> class contains the program settings and methods to save and load them. <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 21.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class Settings {

    private static Properties prop;

    private static boolean initialized;

    private static boolean backUp;
    private static boolean skipFirstStartDialog;
    private static boolean scaleEditorBg;
    private static Language lang;
    private static Path filechooserBasepath;

    private static final String PROPERTIES_DESCRIPTION = "Settings. Do not change!";
    private static final String PROPERTIES_BACKUP = "Setting-backupCreated";
    private static final String PROPERTIES_FIRSTSTART = "Setting-skipfirstStartDialog";
    private static final String PROPERTIES_SCALEEDITORBG = "Setting-scaleEditorBg";
    private static final String PROPERTIES_LANG = "Setting-languageCode";
    private static final String PROPERTIES_FCPATH = "Setting-fcbasepath";

    private static final boolean PROPERTIES_INIT_BACKUP = false;
    private static final boolean PROPERTIES_INIT_FIRSTSTART = false;
    private static final boolean PROPERTIES_INIT_SCALEEDITORBG = true;
    private static final Language PROPERTIES_INIT_LANG = LanguageUtil.getLanguage("en", "US");
    private static final Path PROPERTIES_INIT_FCPATH = SystemInformation.getOSPath().getParent();

    private Settings() {
    }

    /**
     * This method indicates if the backup is created.
     *
     * @return true if the backup is created.
     */
    public static boolean backUpCreated() {
        return backUp;
    }

    /**
     * Sets the backup status.
     *
     * @param created backup status boolean.
     */
    public static void setBackUpCreated(boolean created) {
        backUp = created;
    }

    /**
     * This method indicates if the startup dialog should be skipped.
     *
     * @return if the dialog should be skipped.
     */
    public static boolean skipFirstStartDialog() {
        return skipFirstStartDialog;
    }

    /**
     * Sets if the startup dialog should be skipped.
     *
     * @param skip boolean
     */
    public static void setSkipFirstStartDialog(boolean skip) {
        skipFirstStartDialog = skip;
    }

    /**
     * Indicates if the background in the editor is scaled.
     *
     * @return boolean
     */
    public static boolean getEditorBgScaled() {
        return scaleEditorBg;
    }

    /**
     * Sets if the editor background should be scaled.
     *
     * @param scale boolean
     */
    public static void scaleEditorBackground(boolean scale) {
        scaleEditorBg = scale;
    }

    /**
     * Returns a list with available languages.
     *
     * @return A list of language objects.
     * @throws IOException If the language properties file is not available.
     */
    public static List<Language> getLanguageList() throws IOException {
        return LanguageUtil.getLanguages(Constants.PROGRAM_LANGPROP_PATH);
    }

    /**
     * Returns the current language.
     *
     * @return The current language object.
     */
    public static Language getLocaleLanguage() {
        return lang;
    }

    /**
     * Sets the current language.
     *
     * @param lang Language
     */
    public static void setLocaleLanguage(Language lang) {
        Settings.lang = lang;
    }

    /**
     * Returns the path which is used as the default path in the filechooser.
     *
     * @return Path
     */
    public static Path getFilechooserBasePath() {
        return filechooserBasepath;
    }

    /**
     * Sets the base path for the filechooser.
     *
     * @param path The new path.
     */
    public static void setFilechooserBasepath(Path path) {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            Settings.filechooserBasepath = path;
        } else {
            Settings.filechooserBasepath = path.getParent();
        }
    }

    // ######################################################################

    /**
     * Loads the settings from the given file path.
     *
     * @param settings path
     */
    public static void loadProperties(Path settings) {
        prop = new Properties();

        if (FileUtil.control(settings)) {
            try (FileReader reader = new FileReader(settings.toString())) {
                prop.load(reader);

                {
                    backUp = Boolean.parseBoolean(prop.getProperty(PROPERTIES_BACKUP));
                    skipFirstStartDialog = Boolean.parseBoolean(prop.getProperty(PROPERTIES_FIRSTSTART));
                    scaleEditorBg = Boolean.parseBoolean(prop.getProperty(PROPERTIES_SCALEEDITORBG));
                    filechooserBasepath = Paths.get(prop.getProperty(PROPERTIES_FCPATH));

                    String tmp = prop.getProperty(PROPERTIES_LANG);
                    if (tmp != null) {
                        String[] tmpAr = prop.getProperty(PROPERTIES_LANG).split("\\s");
                        if (tmpAr.length == 2) {
                            lang = LanguageUtil.getLanguage(tmpAr[0], tmpAr[1]);
                        }
                    }
                }
                initialized = true;

            } catch (IOException
                     | NullPointerException e) {
                Main.showProblemMessage(e.getMessage());
                initNew();
            }

        } else {
            initNew();
        }
    }

    /**
     * Saves the settings to a given location.
     *
     * @param settings path
     * @return boolean
     */
    public static boolean saveProperties(Path settings) {
        boolean ret = true;

        if (initialized) {
            if (Files.exists(settings.getParent(), LinkOption.NOFOLLOW_LINKS)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(settings.toString()))) {
                    prop = new Properties();

                    {
                        prop.setProperty(PROPERTIES_BACKUP, "" + backUp);
                        prop.setProperty(PROPERTIES_FIRSTSTART, "" + skipFirstStartDialog);
                        prop.setProperty(PROPERTIES_SCALEEDITORBG, "" + scaleEditorBg);
                        prop.setProperty(PROPERTIES_LANG, "" + lang.getLanguageString() + " " + lang.getCountryString());
                        prop.setProperty(PROPERTIES_FCPATH, "" + filechooserBasepath);
                    }
                    prop.store(writer, PROPERTIES_DESCRIPTION);

                } catch (IOException e) {
                    Main.showProblemMessage(e.getMessage());
                }
            } else {
                ret = false;
            }
        }

        return ret;
    }

    private static void initNew() {
        Settings.backUp = PROPERTIES_INIT_BACKUP;
        Settings.skipFirstStartDialog = PROPERTIES_INIT_FIRSTSTART;
        Settings.scaleEditorBg = PROPERTIES_INIT_SCALEEDITORBG;
        Settings.lang = PROPERTIES_INIT_LANG;
        Settings.filechooserBasepath = PROPERTIES_INIT_FCPATH;

        initialized = true;
    }
}
