/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.util.fileUtil;

import java.io.IOException;
import java.nio.file.Path;

import ale.Constants;
import ale.controller.ProgramLauncher;
import ale.controller.SystemInformation;

/*
 * Class adds admin rights to a file and allows full access or resets it to the previous state. Works only if the program is run as admin.
 */
final class WindowsFileProtection {

    // takeown /f "%windir%\system32\authui.dll" /A
    private static final String TAKEOWN_CMD_1 = "takeown /f \"";
    private static final String TAKEOWN_CMD_2 = "\" /A";
    // icacls "%windir%\System32\authui.dll" /save "d:\ssss"
    private static final String SAVEACL_CMD_1 = "icacls \"";
    private static final String SAVEACL_CMD_2 = "\" /save \"";
    private static final String SAVEACL_CMD_3 = "\"";
    // icacls "%windir%\System32\authui.dll" /grant *S-1-5-32-544:F
    private static final String SET_FULL_ADMINRIGHTS_CMD_1 = "icacls \"";
    private static final String SET_FULL_ADMINRIGHTS_CMD_2 = "\" /grant *S-1-5-32-544:F";
    // icacls "%windir%\System32" /restore "d:\ssss"
    // restore cmd needs no filename, only dir! Is saved in backup file.
    private static final String RESTOREACL_CMD_1 = "icacls \"";
    private static final String RESTOREACL_CMD_2 = "\" /restore \"";
    private static final String RESTOREACL_CMD_3 = "\"";
    private static final String AND = " && ";

    private static Path acl = Constants.ACL_BACKUP_PATH;
    private static final String ERROR_IDENTIFIER = null;

    private WindowsFileProtection() {
    }

    protected static boolean removeProtection(Path file) {
        assert FileUtil.control(file);
        boolean ret = false;

        String cmdTmp = SAVEACL_CMD_1 + file.toString() + SAVEACL_CMD_2 + acl + file.getFileName().toString().replaceAll("\\.", "")
                + SAVEACL_CMD_3;
        cmdTmp += AND + TAKEOWN_CMD_1 + file.toString() + TAKEOWN_CMD_2;
        cmdTmp += AND + SET_FULL_ADMINRIGHTS_CMD_1 + file.toString() + SET_FULL_ADMINRIGHTS_CMD_2;

        try {
            ret = ProgramLauncher.start(SystemInformation.getCMD(), cmdTmp, true, ERROR_IDENTIFIER);
        } catch (InterruptedException
                 | IOException e) {
            ret = false;
        }

        return ret;
    }

    protected static boolean resetProtection(Path file) {
        assert FileUtil.control(file);
        boolean ret = false;

        String cmdTmp = RESTOREACL_CMD_1 + file.getParent().toString() + RESTOREACL_CMD_2 + acl
                + file.getFileName().toString().replaceAll("\\.", "") + RESTOREACL_CMD_3;
        try {
            ret = ProgramLauncher.start(SystemInformation.getCMD(), cmdTmp, true, ERROR_IDENTIFIER);
        } catch (InterruptedException
                 | IOException e) {
            ret = false;
        }

        return ret;
    }
}
