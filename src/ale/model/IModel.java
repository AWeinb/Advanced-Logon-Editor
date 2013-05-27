/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model;

import java.nio.file.Path;
import java.util.List;

import org.apache.log4j.Logger;

import ale.Constants;
import ale.controller.Main;
import ale.model.skin.Skin;
import ale.model.skin.SkinPreviewVO;

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
public interface IModel {

    /**
     * 
     */
    static final Logger LOGGER = Main.getLogger();

    /**
     * Initializes the model and returns if an error occured. It controls if all files needed are available and cleans the workspace for a
     * clean start.
     *
     * @return error
     */
    public Constants.Error initialize();

    /**
     * Deletes the temporary files and resets any changes made to the system.
     * 
     */
    public void shutdown();

    /**
     * Creates and returns a new skin object.
     *
     * @param skinname name of the skin
     * @param author name of the author
     * @param web adress of a website
     * @param img path to a previewimage
     * @return the new skin object
     */
    Skin newSkin(String skinname, String author, String web, Path img);

    /**
     * Loads a skin from given path.
     *
     * @param skinPath the path to the skin file
     * @return the skin object
     */
    public Skin loadSkin(Path skinPath);

    /**
     * Deletes a skin from given path.
     *
     * @param skinPath the path to the skin
     * @return boolean
     */
    public boolean deleteSkin(Path skinPath);

    /**
     * Saves the skin to a predefined directory.
     *
     * @param skin the skin object
     * @return boolean
     */
    boolean saveSkinToDefaultDirectory(Skin skin);

    /**
     * Saves the skin to a predefined directory but lets you change the skinname/filename.
     *
     * @param skin the skin object
     * @param newName the new name
     * @return boolean
     */
    boolean saveAsSkinToDefaultDirectory(Skin skin, String newName);

    /**
     * Applies a skin which is saved at the given path location.
     *
     * @param skinPath path to the skin
     * @return boolean
     */
    public boolean applySkin(Path skinPath);

    /**
     * Applies a skin object to the system.
     *
     * @param skin the skin object
     * @return boolean
     */
    public boolean applySkin(Skin skin);

    /**
     * Returns the basic information about the available skins, such as name or image.
     *
     * @return a list of skinpreview objects.
     */
    List<SkinPreviewVO> getAvailableSkins();

    /**
     * Applies the backup to the systemfiles and reverts all changes.
     *
     * @return boolean
     */
    boolean applyBackup();
}
