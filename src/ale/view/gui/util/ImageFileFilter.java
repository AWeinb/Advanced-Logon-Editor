/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui <br/>
 * Class  : ImageFileFilter <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ImageFileFilter</code> class is a simple imagefilter implementation.<br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 24.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class ImageFileFilter extends FileFilter {

    private String filter;

    /**
     * Constructor
     * 
     * @param filter Takes the filter string. ie the suffix of a file.
     */
    public ImageFileFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(this.filter) || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "*" + this.filter;
    }
}
