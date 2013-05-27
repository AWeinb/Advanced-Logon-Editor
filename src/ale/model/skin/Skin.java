/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import ale.Constants;
import ale.controller.Main;
import ale.model.uiFile.UIFile;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.skin <br/>
 * Class  : Skin <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>Skin</code> class represents the skin. It saves the changes and provides methods to handle a skin. <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class Skin implements ISkin {

    private SkinPropertiesVO props;
    private UIFile uifile;

    /**
     * @param resHackerScript path where the script can be saved.
     * @param authuiTmp path to the temporary authui file
     * @param uifiletextTmp path to the temporary uifile, which gets modified and then inserted in the authui tmp.
     * @param basebrdTmp the path to the temporary basebrd.dll
     */
    public Skin(Path resHackerScript, Path authuiTmp, Path uifiletextTmp, Path basebrdTmp) {
        if (!FileUtil.control(uifiletextTmp) || (resHackerScript == null) || !FileUtil.control(authuiTmp) || !FileUtil.control(basebrdTmp)) {
            IllegalArgumentException iae = new IllegalArgumentException("One or more paths are invalid!");
            Main.handleUnhandableProblem(iae);
        }

        this.uifile = new UIFile(uifiletextTmp, resHackerScript, authuiTmp, basebrdTmp);
    }

    @Override
    public String getName() {
        return this.props.getName();
    }

    @Override
    public void setName(String name) {
        this.props.setName(name);
    }

    @Override
    public String getFilename() {
        return this.props.getSkinFilename();
    }

    @Override
    public void setAuthor(String author) {
        this.props.setAuthor(author);
    }

    @Override
    public String getAuthor() {
        return this.props.getAuthor();
    }

    @Override
    public void setWebsite(String website) {
        this.props.setWebsite(website);
    }

    @Override
    public String getWebsite() {
        return this.props.getWebsite();
    }

    @Override
    public void setImage(Path image) throws IOException {
        this.props.setPreviewimage(image);
    }

    @Override
    public Path getImage() {
        return this.props.getPreviewimage();
    }

    @Override
    public boolean isSkinChanged() {
        return this.props.isChanged();
    }

    @Override
    public void setSkinChanged(boolean b) {
        this.props.setChangestatus(b);
    }

    @Override
    public void create(String name, String author, String website, Path img) throws IOException {
        if ((name == null) || name.equals("")) {
            IllegalArgumentException iae = new IllegalArgumentException("Name is null or empty!");
            Main.handleUnhandableProblem(iae);
        }

        // Overwrites any existing skin.
        Path skinPath = Constants.PROGRAM_TMPSKIN_PATH.resolve(name);
        if (Files.exists(skinPath, LinkOption.NOFOLLOW_LINKS)) {
            try {
                FileUtil.deleteDirectory(skinPath);
            } catch (IOException e) {
                Main.showProblemMessage(e.getMessage());
            }
        }

        FileUtil.createDirectory(skinPath);
        FileUtil.createDirectory(skinPath.resolve(Constants.SKIN_IMGFOLDER));

        img = !FileUtil.control(img) ? Constants.SKIN_PREVIEWIMAGE_DEFAULT : img;
        SkinPreviewVO preview = new SkinPreviewVO(name, name, author, website, img);
        this.props = new SkinPropertiesVO(skinPath.resolve(Constants.SKIN_IMGFOLDER), preview);
    }

    @Override
    public void load(Path skinFile) throws IOException {
        if (!FileUtil.control(skinFile)) {
            IllegalArgumentException iae = new IllegalArgumentException("Skindirectory is invalid!");
            Main.handleUnhandableProblem(iae);
        }

        this.props = ZipSkinLoader.load(skinFile);
    }

    @Override
    public void save(Path saveDir) throws IOException {
        if (this.props == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Call create or load before saving!");
            Main.handleUnhandableProblem(iae);
        }

        if (saveDir == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Savedirectory is invalid!");
            Main.handleUnhandableProblem(iae);
        }

        ZipSkinSaver.save(this, saveDir);
    }

    @Override
    public void save(Path saveDir, String newName) throws IOException {
        if (this.props == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Call create or load before saving!");
            Main.handleUnhandableProblem(iae);
        }

        if (saveDir == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Savedirectory is invalid!");
            Main.handleUnhandableProblem(iae);
        }

        ZipSkinSaver.save(this, saveDir, newName);
    }

    @Override
    public void applyToTempAuthui() throws IOException, InterruptedException {
        if (this.props == null) {
            IllegalArgumentException iae = new IllegalArgumentException("Call create or load before!");
            Main.handleUnhandableProblem(iae);
        }

        // Writes the properties in the temporary ui text file.
        this.uifile.applyUIFileInTempAuthui(this.props, this.uifile.readUIFile());
    }

    @Override
    public SkinPropertiesVO getProperties() {
        return this.props;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.props != null) {
            s = "Skin data:\n";
            s += "[" + super.toString() + "]\n";
            s += this.props.toString();

        } else {
            s = "Uninitialized skin object.";
        }

        return s;
    }
}
