/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.dllResource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import ale.Constants;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.dllResource <br/>
 * Class  : IDLLResource <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>IDLLResource</code> interface is used to access dll files. It is based on the resource hacker. <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 22.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public interface IDLLResource {

    /** Path to the resource hacker */
    static final Path RESOURCE_HACKER = Constants.RESHACKER_PATH;

    /** Path to the resource hacker logfile */
    static final Path RESOURCE_HACKER_LOG = Constants.RESHACKER_LOG_PATH;

    /**
     * Method to add a resource to a dll file.
     *
     * @param dllfile the path to the dll file
     * @param resource the resource to add to the file
     * @return boolean
     * @throws IOException may be thrown if the file is not valid
     * @throws InterruptedException -
     */
    boolean addResources(Path dllfile, List<ResourceVO> resource) throws IOException, InterruptedException;
}
