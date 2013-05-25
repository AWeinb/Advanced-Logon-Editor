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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ale.Constants;
import ale.model.skin.SkinConstants.SkinPreview;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.skin <br/>
 * Class  : SkinPreviewVO <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>SkinPreviewVO</code> class contains the basic skin informations. It provides methods to write them to file and to read them.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class SkinPreviewVO {

    private String filename;
    private String name;
    private Path imagePath;
    private String author;
    private String website;

    protected SkinPreviewVO(String filename, String name, String author, String website, Path image) {
        this.filename = filename;
        this.name = name;
        this.author = author;
        this.website = website;
        this.imagePath = image;
    }

    /**
     * Loads/creates the skinpreview from given path.
     * 
     * @param skinPreviewPath the path to the preview file
     */
    public SkinPreviewVO(Path skinPreviewPath) {
        load(skinPreviewPath);
    }

    /**
     * Returns the filename of the in the directory found skin file.
     *
     * @return filename
     */
    public String getFilename() {
        return this.filename;
    }

    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the skin. This name may not be the same as the filename, though it was that way created.
     *
     * @return the name of the skin or null if the name is empty.
     */
    public String getName() {
        return this.name == null ? "" : this.name;
    }

    /**
     * Returns the name of the author.
     *
     * @return the author name
     */
    public String getAuthor() {
        return this.author == null ? "" : this.author;
    }

    protected void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the adress of the skin website.
     *
     * @return website adress
     */
    public String getWebsite() {
        return this.website == null ? "" : this.website;
    }

    protected void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Returns the path to the preview image.
     *
     * @return path to the image
     */
    public Path getImage() {
        return this.imagePath;
    }

    protected void setImage(Path image) throws IOException {
        if (FileUtil.control(image)) {
            if (image.toAbsolutePath().getParent()
                    .compareTo(Constants.PROGRAM_TMPSKIN_PATH.resolve(this.name).resolve(Constants.SKIN_PREVIEWIMAGE).toAbsolutePath()) != 0) {
                FileUtil.copyFile(image, Constants.PROGRAM_TMPSKIN_PATH.resolve(this.name).resolve(Constants.SKIN_PREVIEWIMAGE), false);
            }
            this.imagePath = Constants.PROGRAM_TMPSKIN_PATH.resolve(this.name).resolve(Constants.SKIN_PREVIEWIMAGE);
        }
    }

    /*
     * Writes the skin preview file.
     */
    protected void save(Path path) {
        LinkedList<String> strings = new LinkedList<>();

        String separator = System.getProperty("line.separator");
        String entry = this.name + separator;
        strings.add(SkinPreview.NAME.name() + SkinConstants.SPLIT + entry);

        entry = this.author == null ? separator : this.author + separator;
        strings.add(SkinPreview.AUTHOR.name() + SkinConstants.SPLIT + entry);

        entry = this.website == null ? separator : this.website + separator;
        strings.add(SkinPreview.WEBSITE.name() + SkinConstants.SPLIT + entry);

        FileUtil.writeTextFile(path, strings, false);
        assert FileUtil.control(path);
    }

    /*
     * Loads the skin preview file.
     */
    private void load(Path path) {
        assert FileUtil.control(path);
        this.filename = path.getParent().getFileName().toString();

        String line;
        String[] split;
        SkinPreview tmp;

        List<String> strings = FileUtil.readTextFile(path);

        Iterator<String> iter = strings.iterator();
        while (iter.hasNext()) {
            line = iter.next();
            split = line.split(SkinConstants.SPLIT);
            split = new String[] { split[0], split.length < 2 ? null : split[1] };

            try {
                tmp = SkinPreview.valueOf(split[0].trim().toUpperCase());

            } catch (IllegalArgumentException e) {
                continue;
            }

            if (split[1] != null) {
                switch (tmp) {
                    case NAME:
                        this.name = split[1].equals("") ? null : split[1];
                        break;

                    case AUTHOR:
                        this.author = split[1].equals("") ? null : split[1];
                        break;

                    case WEBSITE:
                        this.website = split[1].equals("") ? null : split[1];
                        break;

                    default:
                        break;
                }
            }
        }

        this.imagePath = Files.exists(path.getParent().resolve(Constants.SKIN_PREVIEWIMAGE), LinkOption.NOFOLLOW_LINKS) ? path.getParent()
                .resolve(Constants.SKIN_PREVIEWIMAGE) : Constants.SKIN_PREVIEWIMAGE_DEFAULT;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "|| Author: " + this.author + "|| Website: " + this.website + "|| Image: " + this.imagePath;
    }
}
