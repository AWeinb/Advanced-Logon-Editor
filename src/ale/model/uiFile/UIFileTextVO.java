/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.uiFile;

import java.util.ArrayList;
import java.util.List;

import ale.controller.Main;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.uiFile <br/>
 * Class  : UIFileTextVO <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>UIFileTextVO</code> class represents the uifile text.   <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class UIFileTextVO {

    private ArrayList<String> uiFileLines;
    private int initalCapacity = 1020;

    /**
     * Constructor
     */
    public UIFileTextVO() {
        this.uiFileLines = new ArrayList<String>(this.initalCapacity);
    }

    /**
     * The line count of the uitext.
     *
     *
     * @return line count.
     */
    protected int getUIFileSize() {
        return this.uiFileLines.size();
    }

    /**
     * Adds a line to the ui line list.
     *
     *
     * @param line string
     */
    protected void addLine(String line) {
        if (line == null) {
            IllegalArgumentException iae = new IllegalArgumentException("The line must not be null!");
            Main.handleUnhandableProblem(iae);
        }

        this.uiFileLines.add(line);
    }

    /**
     * Sets the whole list.
     *
     *
     * @param lines List
     */
    protected void setLineList(List<String> lines) {
        if ((lines == null) || (lines.size() == 0)) {
            IllegalArgumentException iae = new IllegalArgumentException("The list must not be null or without entries!");
            Main.handleUnhandableProblem(iae);
        }

        this.uiFileLines = new ArrayList<String>(lines);
    }

    /**
     * Returns the line with index x.
     *
     *
     * @param index int
     * @return the line
     */
    protected String getLine(int index) {
        if ((index < 0) || (index > this.uiFileLines.size())) {
            IllegalArgumentException iae = new IllegalArgumentException("The index must not be sub zero!");
            Main.handleUnhandableProblem(iae);
        }

        return this.uiFileLines.get(index);
    }

    /**
     * Sets the line at index x.
     *
     *
     * @param line String
     * @param index int
     */
    protected void setLine(String line, int index) {
        if (index < this.uiFileLines.size()) {
            this.uiFileLines.set(index, line);

        } else {
            addLine(line);
        }
    }
}
