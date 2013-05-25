/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.dllResource;

import java.nio.file.Path;

import ale.controller.Main;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.dllResource <br/>
 * Class  : ResourceVO <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ResourceVO</code> class is a value object to store resource data.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 22.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class ResourceVO {

    private Path path;
    private ResourceType type;
    private int resourceNumber;

    /**
     * Constructor which takes the path to the resource, the type of the resource and the number.
     * 
     * @param path path to the resource
     * @param type type of the resource
     * @param resourceNumber number
     */
    public ResourceVO(Path path, ResourceType type, int resourceNumber) {
        if (!FileUtil.control(path) || (type == null) || (resourceNumber <= 0)) {
            IllegalArgumentException iae = new IllegalArgumentException(
                    "Non of the parameters should be null, also the resourcenumber have to be bigger than zero!");
            Main.handleUnhandableProblem(iae);
        }

        this.path = path;
        this.type = type;
        this.resourceNumber = resourceNumber;
    }

    protected Path getPath() {
        return this.path;
    }

    protected ResourceType getType() {
        return this.type;
    }

    protected int getResourceNumber() {
        assert this.resourceNumber > 0;
        return this.resourceNumber;
    }

    /**
     * ------------------------------------------------- <br/>
     * Package: ale.model.dllResource <br/>
     * Class  : ResourceType <br/>
     * ---------------------------                       <br/>
     *                                                   <br/>
     * The <code>ResourceType</code> enum is used to handle the resource type of a resource.   <br/>
     *                                                   <br/>
     *                                                   <br/>
     * Last edited: 22.04.2013 <br/>
     * ------------------------------------------------- <br/>
     */
    public enum ResourceType {

        /**
         * Bitmap image resource
         */
        BITMAP("Bitmap"),
        /**
         * UIfile text resource
         */
        UIFILE("UIFile");

        private final String value;

        ResourceType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
