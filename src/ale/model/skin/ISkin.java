/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.io.IOException;
import java.nio.file.Path;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.skin <br/>
 * Class  : ISkin <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ISkin</code> interface represents the skin. It defines methods to create, load, save or apply a skin.
 *  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public interface ISkin {

    /**
     * Creates a skin with name, author, website and previewimage.
     *
     * @param name name of the skin
     * @param author author
     * @param website website address
     * @param img path to the preview image
     * @throws IOException may be thrown if the image path is invalid
     */
    void create(String name, String author, String website, Path img) throws IOException;

    /**
     * Loads a skin from a path.
     *
     * @param skinfile path to the skin
     * @throws IOException thrown if the path is invalid
     */
    void load(Path skinfile) throws IOException;

    /**
     * Saves a skin to the given directory.
     *
     * @param saveDir the path to the directory in which the skin should be saved
     * @throws IOException thrown if the path is invalid
     */
    void save(Path saveDir) throws IOException;

    /**
     * Saves a skin to the given directory with a new name.
     *
     * @param saveDir the path to the directory in which the skin should be saved
     * @param newName the new name
     * @throws IOException thrown if the path is invalid
     */
    void save(Path saveDir, String newName) throws IOException;

    /**
     * Applies the properties to ui textfile and then to the temporary authui.dll.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    void applyToTempAuthui() throws IOException, InterruptedException;

    /**
     * Returns the settings object. It contains all changes which should be applied to the logon.
     *
     * @return the skin properties
     */
    SkinPropertiesVO getProperties();

    /**
     * Returns if the skin was changed.
     *
     * @return boolean
     */
    boolean isSkinChanged();

    /**
     * Sets the change status.
     *
     * @param b is changed
     */
    void setSkinChanged(boolean b);

    /**
     * Returns the skin name.
     *
     * @return the name
     */
    String getName();

    /**
     * Sets the skin name.
     *
     * @param name the name
     */
    void setName(String name);

    /**
     * Returns the image path.
     *
     * @return the path to the image
     */
    Path getImage();

    /**
     * Sets the image path for the preview.
     *
     * @param image image path
     * @throws IOException thrown if the image path is invalid
     */
    void setImage(Path image) throws IOException;

    /**
     * Returns the author name.
     *
     * @return author
     */
    String getAuthor();

    /**
     * Sets the author name.
     *
     * @param author author
     */
    void setAuthor(String author);

    /**
     * Returns the read-in filename from the skin.
     *
     * @return the filename
     */
    String getFilename();

    /**
     * Sets the web adress.
     *
     * @param website
     */
    void setWebsite(String website);

    /**
     * Returns the web adress.
     *
     * @return Webadress
     */
    String getWebsite();
}
