/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import ale.Constants;
import ale.Constants.Error;
import ale.controller.Main;
import ale.controller.Settings;
import ale.controller.SystemInformation;
import ale.model.skin.Skin;
import ale.model.skin.SkinPreviewVO;
import ale.model.skin.ZipSkinLoader;
import ale.model.uiFile.UIBackground;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model <br/>
 * Class  : IModel <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>IModel</code> class represents the ... model of the program. It handles all around the skin management. <br/>
 * The Model is the interface to methods which are used to implement the logic of the program.<br/> It contains the algorithm to
 * change the skin and uifile.<br/> To use it, it has to be initialized. This method will return an error code if something went
 * wrong. After that it will create some temporary files and changes some settings, which the shutdown method will undo(clean up).
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 22.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class Model implements IModel {

    private Path authuiSystemfilePath;
    private Path basebrdSystemfilePath;

    /**
     * After an instance was created you should call initialize.
     */
    public Model() {
    }

    /**
     * Initializes the model and returns if an error occured. It controls if all files needed are available and cleans the workspace.
     * The security of the systemfiles gets lowered to work with them.
     *
     * @return error
     */
    @Override
    public Error initialize() {
        Error ret = null;

        if ((ret == null) && !FileUtil.controlDirectory(Constants.PROGRAM_WORKBASE_PATH)) {
            ret = Error.WORKBASE_MISSING;
        }

        if ((ret == null) && !controlBasefiles()) { // Counts the available files. Not that safe but better than nothing.
            ret = Error.WORKBASE_CHANGED;
        }

        if ((ret == null) && !FileUtil.control(Constants.RESHACKER_PATH)) {
            ret = Error.RESHACKER_MISSING;
        }

        if (ret == null) {
            this.authuiSystemfilePath = SystemInformation.getSystemfile_Authui();
            this.basebrdSystemfilePath = SystemInformation.getSystemfile_BaseBrd();

            if (!FileUtil.control(this.authuiSystemfilePath) || !FileUtil.control(this.basebrdSystemfilePath)) {
                ret = Error.WRONG_SYSFILE_PATH;
            }
        }

        if (ret == null) {
            ret = removeProtection();
        }

        if (ret == null) {
            ret = cleanModel();
        }

        if (ret != null) {
            LOGGER.debug("Initialisation return code: " + ret);

        } else {
            LOGGER.debug("Initialisation completed without error!");
        }

        return ret;
    }

    /**
     * Deletes the temporary files and resets the systemfile protection.
     * 
     */
    @Override
    public void shutdown() {
        FileUtil.setProtection(this.authuiSystemfilePath, true);
        FileUtil.setProtection(this.basebrdSystemfilePath, true);

        if (Files.exists(Constants.PROGRAM_TMP_PATH, LinkOption.NOFOLLOW_LINKS)) { // and deletes the temporary files.
            try {
                FileUtil.deleteDirectory(Constants.PROGRAM_TMP_PATH);
            } catch (IOException e) {
                ; // Nothing i can do here. If they are still there then they will be deleted if the program restarts.
            }
        }
    }

    @Override
    public Skin newSkin(String skinname, String author, String web, Path img) {
        if ((skinname == null) || skinname.equals("")) {
            IllegalArgumentException iae = new IllegalArgumentException("Name is null or empty!");
            Main.handleUnhandableProblem(iae);
        }

        Error err = cleanModel(); // Delete all files which are from the old skin.

        Skin skin = null;
        if (err == null) {
            skin = new Skin(Constants.RESHACKER_SCRIPT_PATH, Constants.SYSTEMFILE_AUTHUI_TMPPATH, Constants.UITEXT_TMP,
                    Constants.SYSTEMFILE_BASEBRD_TMPPATH);
            try {
                skin.create(skinname, author, web, img);

                {
                    LOGGER.debug("Skin created!");
                    LOGGER.debug(skin.toString());
                }

            } catch (IOException e) {
                LOGGER.error(e);
                skin = null;
            }
        }

        return skin;
    }

    @Override
    public Skin loadSkin(Path skinPath) {
        if (!FileUtil.control(skinPath)) {
            IllegalArgumentException iae = new IllegalArgumentException("The skindirectory is not existing!");
            Main.handleUnhandableProblem(iae);
        }
        cleanModel();

        Skin skin = null;
        skin = new Skin(Constants.RESHACKER_SCRIPT_PATH, Constants.SYSTEMFILE_AUTHUI_TMPPATH, Constants.UITEXT_TMP,
                Constants.SYSTEMFILE_BASEBRD_TMPPATH);
        try {
            skin.load(skinPath);

            {
                LOGGER.debug("Skin loaded!");
                LOGGER.debug(skin.toString());
            }

        } catch (IOException e) {
            Main.handleUnhandableProblem(e);
        }

        return skin;
    }

    @Override
    public boolean deleteSkin(Path skinPath) {
        if (!FileUtil.control(skinPath)) {
            IllegalArgumentException iae = new IllegalArgumentException("The skin is not existing!");
            Main.handleUnhandableProblem(iae);
        }

        boolean ret = FileUtil.deleteFile(skinPath);

        if (ret) {
            LOGGER.debug("Skin deleted!");
            LOGGER.debug(skinPath.toString());
        }

        return ret;
    }

    @Override
    public boolean saveSkinToDefaultDirectory(Skin skin) {
        if (skin == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Invalid skin!");
            Main.handleUnhandableProblem(iae);
        }

        boolean ret = true;
        try {
            skin.save(Constants.PROGRAM_SKINS_PATH);

        } catch (IOException e) {
            Main.handleUnhandableProblem(e);
            ret = false;
        }

        if (ret) {
            skin.setSkinChanged(false); // If the skin was saved, the change status is reseted.

            LOGGER.debug("Skin saved!");
            LOGGER.debug(skin);
        }

        return ret;
    }

    @Override
    public boolean saveAsSkinToDefaultDirectory(Skin skin, String newName) {
        if (skin == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Invalid skin!");
            LOGGER.error(iae.getMessage(), iae);

            throw iae;
        }

        if ((newName == null) || newName.equals("")) {
            IllegalArgumentException iae = new IllegalArgumentException("New name is invalid!");
            LOGGER.error(iae.getMessage(), iae);

            throw iae;
        }

        boolean ret = true;
        try {
            skin.setName(newName);
            skin.save(Constants.PROGRAM_SKINS_PATH, newName);

        } catch (IOException e) {
            LOGGER.error(e);
            ret = false;
        }

        if (ret) {
            skin.setSkinChanged(false);

            LOGGER.debug("Skin saved!");
            LOGGER.debug(skin);
        }

        return ret;
    }

    @Override
    public boolean applySkin(Path skinPath) {
        boolean ret = true;
        Skin tmp;

        if (FileUtil.control(skinPath)) {
            tmp = loadSkin(skinPath);
            ret = applySkin(tmp);
            tmp.shutdown();
            createTemporaryData(); // Dont delete temp. There may be a skin opened.

        } else {
            ret = false;
        }

        return ret;
    }

    @Override
    public boolean applySkin(Skin skin) {
        if (skin == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Invalid skin!");
            LOGGER.error(iae.getMessage(), iae);

            throw iae;
        }

        boolean ret = false;

        if (Settings.backUpCreated()) { // Before something in the system gets changed the backup has to be created.
            if (Files.exists(Constants.SYSTEMFILE_BASEBRD_TMPPATH, LinkOption.NOFOLLOW_LINKS)
                    && Files.exists(Constants.SYSTEMFILE_AUTHUI_TMPPATH, LinkOption.NOFOLLOW_LINKS)) {
                try { // Apply, Kill explorer to get free access to the systemfiles and copy authui and basebrd.
                    skin.applyToTempAuthui();
                } catch (IOException
                        | InterruptedException e) {
                    Main.handleUnhandableProblem(e);
                }
                ret = FileUtil.setWindowsExplorerActive(false);
                if (ret) {
                    copyToSystemHelper(Constants.SYSTEMFILE_AUTHUI_TMPPATH, Constants.SYSTEMFILE_BASEBRD_TMPPATH);
                }
                FileUtil.setWindowsExplorerActive(true);
            }
        }

        if (ret) {
            LOGGER.trace("Skin applied!");
            LOGGER.trace(skin);

        } else {
            LOGGER.trace("Skin not applied!");
        }

        return ret;
    }

    @Override
    public boolean applyBackup() {
        if (!Files.exists(Constants.SYSTEMFILE_AUTHUI_BAKPATH, LinkOption.NOFOLLOW_LINKS)
                || !Files.exists(Constants.SYSTEMFILE_BASEBRD_BAKPATH, LinkOption.NOFOLLOW_LINKS)) {
            return false;
        }

        boolean ret = true;

        try { //Copy all backup files in the system.
            ret = FileUtil.setWindowsExplorerActive(false);
            if (ret) {
                copyToSystemHelper(Constants.SYSTEMFILE_AUTHUI_BAKPATH, Constants.SYSTEMFILE_BASEBRD_BAKPATH);
            }
            FileUtil.setWindowsExplorerActive(true);
            UIBackground.disableBackgrounds(); // The logon background is not saved in the dll files.
        } catch (IOException
                | InterruptedException e) {
            ret = false;
        }

        return ret;
    }

    // Careful, Mud ahead.
    private void copyToSystemHelper(Path authui, Path basebrd) {
        int MAXCOUNT = 10;
        int SLEEP = 250;

        boolean b;
        int c = 0;

        do { // Thats the first and only time that im controlling a program with exceptions, i swear! :]
            try {
                FileUtil.copyFile(authui, this.authuiSystemfilePath, true);
                FileUtil.copyFile(basebrd, this.basebrdSystemfilePath, true);
                b = true;
            } catch (IOException e) {
                b = false;
                try {
                    Thread.sleep(SLEEP); //TODO Better solution
                } catch (InterruptedException e1) {
                    ;
                }
                LOGGER.error(e);
                LOGGER.error((c + 1) + ". Try");
            }
            c++;
        } while (!b && (c <= MAXCOUNT));

        if (!b && (c > MAXCOUNT)) {
            Main.handleUnhandableProblem("java.nio.file.AccessDeniedException: C:\\Windows\\System32\\authui.dll occured!",
                    "Apply helper method in model. It was not possible to copy the dll file.");
        }
    }

    @Override
    public List<SkinPreviewVO> getAvailableSkins() {
        cleanModel();

        List<SkinPreviewVO> previews = new LinkedList<SkinPreviewVO>();
        try (DirectoryStream<Path> dir = Files.newDirectoryStream(Constants.PROGRAM_SKINS_PATH)) {
            for (Path path : dir) {
                if (path.getFileName().toString().endsWith(Constants.SKINFILE_SUFFIX)) {
                    Path previewPath = ZipSkinLoader.unpackFileFromSkin(path); // unpack and read the preview file.
                    if (previewPath != null) {
                        previews.add(new SkinPreviewVO(previewPath));

                    } else {
                        continue;
                    }
                }
            }
            dir.close();

        } catch (IOException e) {
            Main.handleUnhandableProblem(e);
        }

        return previews;
    }

    /*
     * Deletes all temporary files and recreates the directory structure.
     */
    private Error cleanModel() {
        try {
            FileUtil.deleteDirectory(Constants.PROGRAM_TMP_PATH);
        } catch (IOException e) {
            LOGGER.warn("Dir opened by user?", e);
        }

        return createTemporaryData();
    }

    private static boolean controlBasefiles() {
        boolean ret = FileUtil.controlDirectory(Constants.PROGRAM_WORKBASE_IMG_PATH);

        if (ret) {
            ret = FileUtil.control(Constants.EDITOR_DEFAULT_BACKGROUNDIMAGE);
        }
        if (ret) {
            ret = FileUtil.control(Constants.EDITOR_USERIMG);
        }
        if (ret) {
            ret = FileUtil.control(Constants.EDITOR_GUESTIMG);
        }
        if (ret) {
            ret = FileUtil.control(Constants.SKIN_PREVIEWIMAGE_DEFAULT);
        }
        if (ret) {
            ret = FileUtil.control(Constants.UITEXT_WORKBASE);
        }
        if (ret) {
            int count = 0;

            try {
                DirectoryStream<Path> dir = Files.newDirectoryStream(Constants.PROGRAM_WORKBASE_IMG_PATH);

                for (@SuppressWarnings("unused")
                Path p : dir) {
                    count++;
                }
                dir.close();

            } catch (IOException e) {
                Main.handleUnhandableProblem(e);
            }

            if (count < Constants.PROGRAM_WORKBASE_IMG_COUNT) {
                ret = false;
            }
        }

        return ret;
    }

    private Error removeProtection() {
        Error ret = null;

        try {
            FileUtil.createDirectory(Constants.PROGRAM_BACKUP_PATH);
            if (!FileUtil.setProtection(this.authuiSystemfilePath, false) || // Make the systemfiles replaceable.
                    !FileUtil.setProtection(this.basebrdSystemfilePath, false)) {
                ret = Error.PROTECTION_ERROR;
            }
        } catch (IOException e) {
            ret = Error.PROTECTION_ERROR;
        }

        return ret;
    }

    private Constants.Error createTemporaryData() {
        {
            LOGGER.debug("Creating Temporary Data!");
        }

        Constants.Error ret = null;
        ret = createBackUpFiles();

        if (ret == null) {
            try {
                FileUtil.createDirectory(Constants.PROGRAM_TMP_PATH);
                FileUtil.createDirectory(Constants.PROGRAM_TMPSKIN_PATH);
                FileUtil.createDirectory(Constants.PROGRAM_SKINS_PATH);

                FileUtil.copyFile(Constants.SYSTEMFILE_AUTHUI_BAKPATH, Constants.SYSTEMFILE_AUTHUI_TMPPATH, true);
                FileUtil.copyFile(Constants.SYSTEMFILE_BASEBRD_BAKPATH, Constants.SYSTEMFILE_BASEBRD_TMPPATH, true);

                if (Files.exists(Constants.SYSTEMFILE_AUTHUI_TMPPATH, LinkOption.NOFOLLOW_LINKS)
                        && Files.exists(Constants.SYSTEMFILE_BASEBRD_TMPPATH, LinkOption.NOFOLLOW_LINKS)) {

                    FileUtil.copyFile(Constants.UITEXT_WORKBASE, Constants.UITEXT_TMP, false);
                    if (!FileUtil.control(Constants.UITEXT_TMP)) {
                        ret = Error.COPIED_UIFILE_NOT_AVAILABLE;
                    }
                }
            } catch (IOException e) {
                ret = Error.TEMP_CREATION_ERROR;
            }

        }
        return ret;
    }

    private Constants.Error createBackUpFiles() {
        Constants.Error ret = null;

        try { // Copy the unchanged systemfile in the backup directory. If the files already exist but the program has no info about it, it
            // saves a second copy and changes the name of the existing file to a name with date.

            if (!Files.exists(Constants.SYSTEMFILE_AUTHUI_BAKPATH, LinkOption.NOFOLLOW_LINKS)) {
                FileUtil.copyFile(this.authuiSystemfilePath, Constants.SYSTEMFILE_AUTHUI_BAKPATH, true);
            } else {
                if (!Settings.backUpCreated()) {
                    String tmp = new java.util.Date().toString();
                    tmp = tmp.replace(":", "-");
                    String tmp2 = Constants.SYSTEMFILE_AUTHUI_BAKPATH.getFileName().toString().replace(".dll", "_" + tmp + ".dll");

                    FileUtil.moveFile(Constants.SYSTEMFILE_AUTHUI_BAKPATH, Constants.PROGRAM_BACKUP_PATH.resolve(tmp2));
                    FileUtil.copyFile(this.authuiSystemfilePath, Constants.SYSTEMFILE_AUTHUI_BAKPATH, true);
                }
            }

            if (!Files.exists(Constants.SYSTEMFILE_BASEBRD_BAKPATH, LinkOption.NOFOLLOW_LINKS)) {
                FileUtil.copyFile(this.basebrdSystemfilePath, Constants.SYSTEMFILE_BASEBRD_BAKPATH, true);
            } else {
                if (!Settings.backUpCreated()) {
                    String tmp = new java.util.Date().toString();
                    tmp = tmp.replace(":", "-");
                    String tmp2 = Constants.SYSTEMFILE_BASEBRD_BAKPATH.getFileName().toString()
                            .replace(".dll", "_" + tmp + ".dll");

                    FileUtil.moveFile(Constants.SYSTEMFILE_BASEBRD_BAKPATH, Constants.PROGRAM_BACKUP_PATH.resolve(tmp2));
                    FileUtil.copyFile(this.basebrdSystemfilePath, Constants.SYSTEMFILE_BASEBRD_BAKPATH, true);
                }
            }
        } catch (IOException e) {
            ret = Error.BACKUP_CREATION_ERROR;
        }

        // control if the backup is really created.
        if (!Files.exists(Constants.SYSTEMFILE_AUTHUI_BAKPATH, LinkOption.NOFOLLOW_LINKS)
                || !Files.exists(Constants.SYSTEMFILE_BASEBRD_BAKPATH, LinkOption.NOFOLLOW_LINKS)) {
            ret = Error.BACKUP_CREATION_ERROR;
            Settings.setBackUpCreated(false);

        } else {
            Settings.setBackUpCreated(true);
        }

        return ret;
    }
}
