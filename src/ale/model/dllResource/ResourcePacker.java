/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.dllResource;

import java.io.IOException;
import java.nio.file.Path;

import ale.controller.Main;
import ale.controller.ProgramLauncher;
import ale.util.fileUtil.FileUtil;

final class ResourcePacker {

    private Path reshacker;
    private static final String CMD_ADDWITHSCRIPT = " -script ";
    private static final String ERROR_IDENTIFIER = "Error";

    protected ResourcePacker(Path reshacker) {
        assert FileUtil.control(reshacker);
        this.reshacker = reshacker;
    }

    protected boolean write(Script script) throws IOException, InterruptedException {
        if (script == null) {
            IllegalArgumentException iae = new IllegalArgumentException("The script object is null!");
            Main.handleUnhandableProblem(iae);
        }

        assert FileUtil.control(script.scriptPath());
        String cmd = CMD_ADDWITHSCRIPT + script.scriptPath(); // Runs the resource hacker with the commandline argument cmd
        return ProgramLauncher.start(this.reshacker.toString(), cmd, true, ERROR_IDENTIFIER);
    }
}
