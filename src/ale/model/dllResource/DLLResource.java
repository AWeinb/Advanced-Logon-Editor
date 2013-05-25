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

import ale.controller.Main;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.dllResource <br/>
 * Class  : DLLResource <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>DLLResource</code> class contains methods to work with dll files based on the resource hacker. <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 22.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class DLLResource implements IDLLResource {

    private ResourcePacker packer;
    private Script script;

    /**
     * Constructor. Initializes the object.
     * 
     * @param script the path to the location where a script can be saved.
     */
    public DLLResource(Path script) {
        if (script == null) {
            IllegalArgumentException iae = new IllegalArgumentException("The scriptfile path must not be null!");
            Main.handleUnhandableProblem(iae);
        }

        this.packer = new ResourcePacker(RESOURCE_HACKER);
        this.script = new Script(script);
    }

    @Override
    public boolean addResources(Path tmpSystemfile, List<ResourceVO> resource) throws IOException, InterruptedException {
        if (!FileUtil.control(tmpSystemfile) || (resource == null) || (resource.size() <= 0)) {
            IllegalArgumentException iae = new IllegalArgumentException("The Systemfile is null/invalid or there are no resources!");
            Main.handleUnhandableProblem(iae);
        }

        Script s = this.script.createAddScript(tmpSystemfile, resource, RESOURCE_HACKER_LOG);
        assert FileUtil.control(s.scriptPath());

        if (s != null) {
            return this.packer.write(s);
        }
        return false;
    }
}
