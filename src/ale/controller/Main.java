/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.controller;

import it.sauronsoftware.junique.MessageHandler;

import java.awt.Desktop;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import ale.Constants;
import ale.Constants.Error;
import ale.model.Model;
import ale.model.skin.Skin;
import ale.model.skin.SkinPreviewVO;
import ale.util.fileUtil.FileUtil;
import ale.view.gui.chooser.QuickChooser;
import ale.view.gui.dialogs.AboutDialog;
import ale.view.gui.dialogs.AfterApplySkinDialog;
import ale.view.gui.dialogs.ApplySkinDialog;
import ale.view.gui.dialogs.DeleteSkinDialog;
import ale.view.gui.dialogs.ErrorDialog;
import ale.view.gui.dialogs.FirstStartDialog;
import ale.view.gui.dialogs.NewSkinDialog;
import ale.view.gui.dialogs.QuitConfirmDialog;
import ale.view.gui.dialogs.RenameDialog;
import ale.view.gui.dialogs.SaveAsDialog;
import ale.view.gui.dialogs.SettingsDialog;
import ale.view.gui.editor.Editor;
import ale.view.gui.util.GUIStrings;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.controller <br/>
 * Class  : Main <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>Main</code> class contains the control structur and is the head of the program flow.   <br/>
 * Short info: All filename params whithout suffix. Change the suffix in the constants.
 * 
 * See PROGRAM_PATH in Constants before testing.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 25.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class Main {

    /** Latest Version */
    public static final String VERSION = "1.03 beta";
    /** Latest Build date */
    public static final String LASTBUILD = "23.05.2013";
    private static final URI WEBSITE = URI.create("http://www.art-of-axp.tk/");

    private static Logger LOGGER = getLogger();
    private static final String LOGGER_NAME = "all.class";
    private static final String LOGGER_FILE = "log.html";
    private static final Level LOG_LEVEL = Level.WARN;

    private static final String BACKUP_CMD = "backup";

    private static Model model;
    private static ExecutorService exct;
    private static QuickChooser chooser;
    private static Editor editor;

    /**
     * Main. Start of the program.
     *
     * @param args Commands: backup
     */
    public static void main(String[] args) {
        try { // Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            LOGGER.warn("Exception while trying to set the L&F!", e);
        }

        if (!SystemInformation.isWin7()) {
            showMessage("This program works only on Windows 7!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        { // Unique Instance Block
            MessageHandler handler = new MessageHandler() {
                String ret = "";

                @Override
                public String handle(String arg0) {
                    try {
                        if (editor != null) {
                            editor.toFront();
                            editor.repaint();
                            editor.setExtendedState(Frame.NORMAL);

                        } else if (chooser != null) {
                            chooser.toFront();
                            chooser.repaint();
                            chooser.setExtendedState(Frame.NORMAL);

                        } else {
                            this.ret = null;
                            executeThreads(new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        ;
                                    }
                                    LOGGER.fatal("Deamon killed!");
                                    SystemInformation.releaseJVMLock();
                                    System.exit(1);
                                }
                            });
                        }
                    } catch (Exception e) {
                        ;
                    }

                    return this.ret;
                }
            };
            if (SystemInformation.isAlreadyRunning(handler)) {
                if (SystemInformation.wakeUpOtherOne() != null) {
                    return;
                }
            }
        }

        { // CMD Block
            if ((args != null) && (args.length > 0) && args[0].equals(BACKUP_CMD)) {
                System.out.println("Advanced Logon Editor Copyright (C) 2013 A. Weinberger");
                System.out.println("This program comes with ABSOLUTELY NO WARRANTY!");
                System.out.println("Please wait...");

                Settings.loadProperties(Constants.SETTINGS_PATH);
                model = new Model();
                Error err = model.initialize();

                if (err == null) {
                    if (Settings.backUpCreated()) {
                        if (model.applyBackup()) {
                            System.out.println("Backup applied!");
                        } else {
                            System.out.println("Backup NOT applied! An error occured!");
                        }

                    } else {
                        System.out.println("No Backup available! First Use?\nWould you like to recreate the logonscreen? y/n");
                        try {
                            String tmp = "";
                            byte buffer[] = new byte[10];

                            int i = System.in.read(buffer, 0, 10);
                            tmp = new String(buffer, 0, i);
                            if (tmp.trim().toLowerCase().equals("y")) {
                                Skin newSkin = model.newSkin("backup", null, null, null);
                                model.applySkin(newSkin);
                            }

                        } catch (IOException e) {
                            System.out.println("Error: " + e);
                        }
                    }
                    return;
                }
                System.out.println("An error occurred: " + err);
            }
        }

        // -->
        Settings.loadProperties(Constants.SETTINGS_PATH);
        updateLocale(Settings.getLocaleLanguage());

        if (!Settings.skipFirstStartDialog()) {
            showFirstStartDialog();

        } else {
            resume();
        }
    }

    /**
     * The Firststartdialog calls this method after showing a message.
     */
    public static void resume() {
        Settings.setSkipFirstStartDialog(true);
        Settings.saveProperties(Constants.SETTINGS_PATH);
        model = new Model();
        Error err = model.initialize();

        if (err == null) {
            showQuickChooser();

        } else {
            handleUnhandableProblem(err + "", "Main.resume");
        }
    }

    /**
     * Ends the program.
     * 
     */
    public static void shutdown() {
        Settings.saveProperties(Constants.SETTINGS_PATH);

        if (model != null) {
            model.shutdown();
            model = null;
        }

        if (exct != null) {
            exct.shutdown();
            exct = null;
        }

        if (chooser != null) {
            chooser.dispose();
            chooser = null;
        }

        if (editor != null) {
            editor.dispose();
            editor = null;
        }

        SystemInformation.releaseJVMLock();
    }

    /*
     * ##############################################################################
     */

    /**
     * Creates a new Quickchooser window.
     * 
     */
    public static void showQuickChooser() {
        chooser = new QuickChooser();
        editor = null;
    }

    /**
     * Creates a new Editor window.
     *
     * @param skinFilename The skin to load and show in the editor
     */
    public static void showEditor(String skinFilename) {
        Path skinPath;
        if (!skinFilename.endsWith(Constants.SKINFILE_SUFFIX)) {
            skinPath = Constants.PROGRAM_SKINS_PATH.resolve(skinFilename + Constants.SKINFILE_SUFFIX);
        } else {
            skinPath = Constants.PROGRAM_SKINS_PATH.resolve(skinFilename);
        }

        if ((skinFilename != null) && !skinFilename.equals("") && FileUtil.control(skinPath) && (model != null)) {
            Skin skin = model.loadSkin(skinPath);

            if (skin != null) {
                if (editor != null) {
                    editor.dispose();
                    editor = null;
                }
                if (chooser != null) {
                    chooser.dispose();
                    chooser = null;
                }

                editor = new Editor(skin);
            } else {
                handleUnhandableProblem("Main.showEditor", "Skin load failed!");
            }
        } else {
            throw new InvalidParameterException();
        }
    }

    /**
     * Shows a the firststart dialog.
     * 
     */
    @SuppressWarnings("unused")
    public static void showFirstStartDialog() {
        new FirstStartDialog();
    }

    /**
     * Shows a the newSkinDialog.
     * 
     */
    @SuppressWarnings("unused")
    public static void showNewSkinDialog() {
        new NewSkinDialog();
    }

    /**
     * Shows the DeleteSkinDialog.
     *
     * @param filename skin to delete.
     */
    @SuppressWarnings("unused")
    public static void showDeleteSkinDialog(String filename) {
        new DeleteSkinDialog(filename);
    }

    /**
     * Shows the showRenameSkinDialog.
     *
     * @param filename skin to rename.
     */
    @SuppressWarnings("unused")
    public static void showRenameSkinDialog(String filename) {
        new RenameDialog(filename);
    }

    /**
     * Shows the apply skin dialog.
     *
     * @param filename skinfilename.
     */
    @SuppressWarnings("unused")
    public static void showApplySkinDialog(String filename) {
        new ApplySkinDialog(filename);
    }

    /**
     * Shows a dialog with infos about the program.
     * 
     */
    @SuppressWarnings("unused")
    public static void showAboutDialog() {
        new AboutDialog();
    }

    /**
     * Shows the settings menu.
     * 
     */
    @SuppressWarnings("unused")
    public static void showSettingsDialog() {
        new SettingsDialog();
    }

    /**
     * Shows the save-as menu.
     * @param skin skin
     * 
     */
    @SuppressWarnings("unused")
    public static void showSaveAsDialog(Skin skin) {
        new SaveAsDialog(skin);
    }

    /**
     * Shows a dialog which asks if the user wants to continue without saving.
     * 
     * @param skin skin, in order to save it
     * @param openNewSkin if the user switches from one skin to a new one.
     * @param openChooser if the user wants to open the chooser.
     */
    @SuppressWarnings("unused")
    public static void showQuitConfirmationDialog(Skin skin, boolean openNewSkin, boolean openChooser) {
        new QuitConfirmDialog(skin, editor, openChooser, openChooser);
    }

    /**
     * Opens the help docs.
     * 
     */
    public static void showHelpDocs() {
        if (FileUtil.control(Constants.PROGRAM_DOCS_PATH)) {
            try {
                Desktop.getDesktop().open(Constants.PROGRAM_DOCS_PATH.toFile());

            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }

    /**
     * Opens the homepage.
     * 
     */
    public static void showWebsite() {
        try {
            Desktop.getDesktop().browse(WEBSITE);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Opens a specific website.
     *
     * @param url url
     * @return boolean
     */
    public static boolean showWebsite(String url) {
        boolean ret = false;

        if ((url != null) && !url.equals("")) {
            try {
                URI uri = new URI(url);
                Desktop.getDesktop().browse(uri);
                ret = true;

            } catch (IOException
                    | URISyntaxException e) {
                LOGGER.error(e);
            }
        }
        return ret;
    }

    /*
     * ##############################################################################
     */

    /**
     * Shows an editor window with a new skin
     *
     * @param strings name, author, website, image
     */
    public static void newSkin(String... strings) {
        if (strings.length != 4) {
            throw new IllegalArgumentException();
        }

        if ((strings != null) && (model != null)) {
            Skin newSkin = model.newSkin(strings[0], strings[1], strings[2], strings[3] == null ? null : Paths.get(strings[3]));

            if (newSkin != null) {
                editor = new Editor(newSkin);
            } else {
                showProblemMessage("Main.newSkin: Can not create new Skin!");
                return;
            }

            if (chooser != null) {
                chooser.dispose();
            }
            chooser = null;

        } else {
            throw new InvalidParameterException();
        }
    }

    /**
     * Saves and shows the apply dialog.
     *
     * @param filename filename of the skin.
     */
    @SuppressWarnings("unused")
    public static void applySkin(String filename) {
        Path skinPath;
        if (!filename.endsWith(Constants.SKINFILE_SUFFIX)) {
            skinPath = Constants.PROGRAM_SKINS_PATH.resolve(filename + Constants.SKINFILE_SUFFIX);
        } else {
            skinPath = Constants.PROGRAM_SKINS_PATH.resolve(filename);
        }

        if (model.applySkin(skinPath)) {
            new AfterApplySkinDialog();
        } else {
            showProblemMessage("Main.applySkin: Skin was not applied!");
        }
    }

    /**
     * Deletes a skin
     *
     * @param filename filename of the skin.
     */
    public static void deleteSkin(String filename) {
        Path skinPath;
        if (!filename.endsWith(Constants.SKINFILE_SUFFIX)) {
            skinPath = Constants.PROGRAM_SKINS_PATH.resolve(filename + Constants.SKINFILE_SUFFIX);
        } else {
            skinPath = Constants.PROGRAM_SKINS_PATH.resolve(filename);
        }

        if (model.deleteSkin(skinPath)) {
            chooser.updateList();
        } else {
            showProblemMessage("Main.deleteSkin: Skin was not deleted!");
        }
    }

    /**
     * Saves a skin to the skin dir.
     *
     * @param skin skinobject
     */
    public static void save(Skin skin) {
        if ((skin != null)) {
            if (!model.saveSkinToDefaultDirectory(skin)) {
                showProblemMessage("Main.save: Skin was not saved!");
            }
        } else {
            throw new InvalidParameterException();
        }
    }

    /**
     * Saves with new name.
     *
     * @param skin skin
     * @param newName new name
     */
    public static void saveAs(Skin skin, String newName) {
        if ((skin != null) && (model != null) && (newName != null) && !newName.equals("")) {
            if (!model.saveAsSkinToDefaultDirectory(skin, newName)) {
                showProblemMessage("Main.saveAs: Skin was not saved!");
            }
        } else {
            throw new InvalidParameterException();
        }
    }

    /**
     * Renames a skin. ie saves with new name and deletes old.
     *
     * @param filename filename of the skin.
     * @param newName new name
     */
    public static void renameSkin(String filename, String newName) {
        if ((newName != null) && !newName.equals("") && !filename.equals(newName)) {
            Path skinPath;
            if (!filename.endsWith(Constants.SKINFILE_SUFFIX)) {
                skinPath = Constants.PROGRAM_SKINS_PATH.resolve(filename + Constants.SKINFILE_SUFFIX);
            } else {
                skinPath = Constants.PROGRAM_SKINS_PATH.resolve(filename);
            }

            saveAs(model.loadSkin(skinPath), newName);
            deleteSkin(filename);
        }
    }

    /**
     * Applies the backup
     * 
     * @return boolean
     */
    public static boolean applyBackup() {
        return model.applyBackup();
    }

    /*
     * ##############################################################################
     */

    /**
     * Updates the locale of the program.
     *
     * @param language language
     */
    public static void updateLocale(Language language) {
        String l = Settings.getLocaleLanguage().getLanguageString();
        String c = Settings.getLocaleLanguage().getCountryString();
        Locale currentLocale = new Locale(l, c);

        PropertyResourceBundle localeRes = null;
        try (FileInputStream fis = new FileInputStream(Constants.PROGRAM_LOCALE_PATH.resolve(
                Constants.PROGRAM_I18N + "_" + currentLocale.getLanguage() + "_" + currentLocale.getCountry()
                + Constants.PROGRAM_I18N_SUFFIX).toFile())) {
            localeRes = new PropertyResourceBundle(fis);
            fis.close();

        } catch (IOException e) {
            LOGGER.warn(e);
        }

        if (localeRes != null) {
            GUIStrings.initLocale(localeRes);

            if (chooser != null) {
                chooser.updateLocale();
                chooser.repaint();
            }

            if (editor != null) {
                editor.updateLocale();
                editor.repaint();
            }
        } else {
            handleUnhandableProblem("Error while reading locale file!", "updateLocale()");
        }
    }

    /**
     * Returns the infos about the available skins.
     *
     * @return a list of preview objects.
     */
    public static List<SkinPreviewVO> getAvailableSkins() {
        return model.getAvailableSkins();
    }

    /*
     * ##############################################################################
     */

    /**
     *
     *
     * @param err error string
     * @param where hint where it occured
     */
    @SuppressWarnings("unused")
    public static void handleUnhandableProblem(String err, String where) {
        handleUnhandableProblem();

        new ErrorDialog(err + "\n" + where);
        LOGGER.error(err + "-in-" + where);
    }

    /**
     *
     *
     * @param err exception
     */
    @SuppressWarnings("unused")
    public static void handleUnhandableProblem(Throwable err) {
        handleUnhandableProblem();

        if ((err.getStackTrace() != null) && (err.getStackTrace().length >= 2)) {
            new ErrorDialog(err.getMessage() + "\n" + err.getStackTrace()[0] + "\n" + err.getStackTrace()[1]);
        } else {
            new ErrorDialog(err.getMessage());
        }
        LOGGER.error(err);
        for (StackTraceElement e : err.getStackTrace()) {
            LOGGER.error(e);
        }
    }

    private static void handleUnhandableProblem() {
        shutdown();
    }

    /**
     * Shows a message dialog.
     * @param msg message
     *
     */
    public static void showProblemMessage(String msg) {
        showMessage(msg, JOptionPane.WARNING_MESSAGE);
        LOGGER.error(msg);
    }

    private static void showMessage(String message, int icon) {
        JOptionPane.showMessageDialog(null, message, "", icon);
    }

    /*
     * ##############################################################################
     */

    /**
     * Executes runnable objects.
     *
     * @param runnables runnables to execute
     */
    public static void executeThreads(Runnable... runnables) {
        if (exct == null) {
            exct = Executors.newCachedThreadPool();
        }

        for (Runnable r : runnables) {
            exct.execute(r);
        }
    }

    /**
     *
     *
     * @return the logger obj
     */
    public static Logger getLogger() {
        if (LOGGER == null) {
            LOGGER = Logger.getLogger(LOGGER_NAME);
            final SimpleLayout layout = new SimpleLayout();
            LOGGER.addAppender(new ConsoleAppender(layout));

            final HTMLLayout layout2 = new HTMLLayout();
            try {
                LOGGER.addAppender(new FileAppender(layout2, Constants.PROGRAM_PATH.resolve(LOGGER_FILE).toString(), true));

            } catch (IOException e) {
                LOGGER.warn("Can't create FileAppender for " + Constants.PROGRAM_PATH.resolve(LOGGER_FILE).toFile().getAbsolutePath(), e);
            }
        }

        if (LOGGER != null) {
            LOGGER.setLevel(LOG_LEVEL);
        }

        return LOGGER;
    }
}
