/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.util.fileUtil;

import java.io.IOException;

import ale.controller.ProgramLauncher;
import ale.controller.SystemInformation;

/*
 * Class uses the windows cmd to end or start the windows explorer.
 */
final class WindowsExplorer {

    private static final String EXPLORER_TASKKILL = "taskkill /f /IM explorer.exe";
    private static final String EXPLORER_START1 = "start \"\" ";
    private static final String EXPLORER_START2 = "\\explorer.exe";
    private static final int SHD_SLEEP = 250;

    private WindowsExplorer() {
    }

    protected static boolean shutdown() throws IOException, InterruptedException {
        boolean ret = false;

        ret = ProgramLauncher.start(SystemInformation.getCMD(), EXPLORER_TASKKILL, false, null);
        Thread.sleep(SHD_SLEEP);

        return ret;
    }

    protected static boolean start() throws IOException, InterruptedException {
        boolean ret = false;

        ret = ProgramLauncher.start(SystemInformation.getCMD(), EXPLORER_START1 + SystemInformation.getOSPath() + EXPLORER_START2, false,
                null);
        Thread.sleep(SHD_SLEEP);

        return ret;
    }
}
