/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.uiFile;

import java.io.IOException;

import ale.model.skin.SkinPropertiesVO;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.uiFile <br/>
 * Class  : IUIFile <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>IUIFile</code> interface represents an ui text file. It contains methods to read a uifile, to write it and to apply it to
 * an dll file.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public interface IUIFile {

    /**
     * Reads the uifile.
     *
     * @return the created uifile
     */
    UIFileTextVO readUIFile();

    /**
     * Writes an uifile at the in the uifile saved location.
     *
     * @param uifile
     * @throws IOException
     */
    void writeUIFile(UIFileTextVO uifile) throws IOException;

    /**
     * Applies the properties to an uifile.
     *
     * @param props
     * @param uifile
     * @throws IOException
     * @throws InterruptedException
     */
    void applyUIFileInTempAuthui(SkinPropertiesVO props, UIFileTextVO uifile) throws IOException, InterruptedException;
}
