/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.uiFile;

import java.io.IOException;
import java.nio.file.Path;

import ale.Constants;
import ale.controller.ProgramLauncher;
import ale.controller.SystemInformation;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.uiFile <br/>
 * Class  : UIBackground <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>UIBackground</code> class contains methods to enable and set the logon background. The background is not saved in the authui. <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class UIBackground {

    private static final String ENABLE_BG_CMD = "REG ADD HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Authentication\\LogonUI\\Background /v OEMBackground /t REG_DWORD /d 1 /f";
    private static final String DISABLE_BG_CMD = "REG ADD HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Authentication\\LogonUI\\Background /v OEMBackground /t REG_DWORD /d 0 /f";

    private UIBackground() {
    }

    /*
     * Sets the background and enables it.
     */
    protected static void setBackground(Path img) throws IOException, InterruptedException {
        if (FileUtil.control(img)) {
            FileUtil.deleteDirectory(SystemInformation.getBackgroundDirectory());
            FileUtil.createDirectory(SystemInformation.getBackgroundDirectory());

            FileUtil.scaleImageTo256kb(img);
            FileUtil.copyFile(img, SystemInformation.getBackgroundDirectory().resolve(Constants.SKIN_BG_NAME), false);

            enableBackgrounds();
        } else {
            disableBackgrounds();
        }
    }

    private static void enableBackgrounds() throws IOException, InterruptedException {
        try { // Once in a time there were a bug. This bug had it that the authui was correctly applied but not the background. XXX
            ProgramLauncher.start(SystemInformation.getCMD64(), ENABLE_BG_CMD, true, null); // So, le me went and controlled the program with exceptions.
        } catch (IOException
                 | InterruptedException e) {
            ProgramLauncher.start(SystemInformation.getCMD(), ENABLE_BG_CMD, true, null); // Now I am in hell because I did evil.
        }
    }

    /**
     * Method disables the background change and so resets the changes.
     *
     * @throws IOException -
     * @throws InterruptedException -
     */
    public static void disableBackgrounds() throws IOException, InterruptedException {
        try { // Ok, like written above, thats not "the best"! ((((But it works)))) XXX
            ProgramLauncher.start(SystemInformation.getCMD64(), DISABLE_BG_CMD, true, null);
        } catch (IOException
                 | InterruptedException e) {
            ProgramLauncher.start(SystemInformation.getCMD(), DISABLE_BG_CMD, true, null);
        }
    }
}
