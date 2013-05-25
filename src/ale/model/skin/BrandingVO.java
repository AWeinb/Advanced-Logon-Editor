/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.nio.file.Path;

import ale.controller.Main;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.skin <br/>
 * Class  : BrandingVO <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>BrandingVO</code> class contains the information about the windows branding. That are the imagepaths.<br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 22.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class BrandingVO {

    private Path imgSmall;
    private Path imgMedium;
    private Path imgLarge;

    BrandingVO(Path small, Path medium, Path large) {
        if ((small == null) || (medium == null) || (large == null)) {
            IllegalArgumentException iae = new IllegalArgumentException("One or more bitmaps are null!");
            Main.handleUnhandableProblem(iae);
        }

        this.imgSmall = small;
        this.imgMedium = medium;
        this.imgLarge = large;
    }

    void setImage(Path image, Brandingsize size) {
        switch (size) {
            case SMALL:
                this.imgSmall = image;
                break;

            case MEDIUM:
                this.imgMedium = image;
                break;

            case LARGE:
                this.imgLarge = image;
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the fitting image.
     *
     * @param size size of the image
     * @return path of the image
     */
    public Path getImage(Brandingsize size) {
        Path ret = null;

        switch (size) {
            case SMALL:
                ret = this.imgSmall;
                break;

            case MEDIUM:
                ret = this.imgMedium;
                break;

            case LARGE:
                ret = this.imgLarge;
                break;

            default:
                throw new IllegalArgumentException();
        }

        return ret;
    }

    /**
     * ------------------------------------------------- <br/>
     * Package: ale.model.skin <br/>
     * Class  : Brandingsize <br/>
     * ---------------------------                       <br/>
     *                                                   <br/>
     * The <code>Brandingsize</code> represents a size of a branding image. There are three different sizes used in the basebrd.dll file. <br/>
     *                                                   <br/>
     *                                                   <br/>
     * Last edited: 22.04.2013 <br/>
     * ------------------------------------------------- <br/>
     */
    public enum Brandingsize {

        /** small size */
        SMALL,
        /** medium size */
        MEDIUM,
        /** large size */
        LARGE;
    }
}
