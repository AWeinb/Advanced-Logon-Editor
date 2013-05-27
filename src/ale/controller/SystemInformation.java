/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.controller;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import it.sauronsoftware.junique.MessageHandler;

import java.nio.file.Path;
import java.nio.file.Paths;

import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.controller <br/>
 * Class  : SystemInformation <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>SystemInformation</code> class contains methods which provide informations about the OS.
 * The most complex part to understand is how x86 and x64 are different and the consequences of that.
 * The most methods here control which bitness the os has and react properly. There are different paths
 * for the authui file, the background directory and for the path to the commandline in windows.
 * The cmd.exe is used to manage the filepermissions etc. <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 21.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class SystemInformation {

    /*
     * In the filesystem view i am only working in the system32 directory. The 64 bit windows 7 has an implemented
     * redirector for a path with system32 in it if the program is 32bit. I have to use the original system32 which is
     * possible with the buzzword Sysnative. For the most part it works as it should. The only non-logic part comes with the
     * background where I have to try with the 64bit cmd first. Maybe it is fixable. XXX
     */

    private static final String WIN7_REGEX = "Windows 7";
    private static final String WIN7_FOLDERNAME = "Windows" + System.getProperty("file.separator");
    private static final String CMD_x64 = "\\Sysnative\\cmd.exe /c ";
    private static final String CMD_x64_wO_c = "\\Sysnative\\cmd.exe";
    private static final String CMD_x86 = "\\System32\\cmd.exe /c ";
    private static final String SYSTEM_BG_DIR_x64 = "Sysnative/oobe/info/backgrounds/";
    private static final String SYSTEM_BG_DIR_x86 = "System32/oobe/info/backgrounds/";
    private static final String SYSTEMFILE_AUTHUI_x64 = "Sysnative/authui.dll";
    private static final String SYSTEMFILE_AUTHUI_x86 = "System32/authui.dll";
    private static final String SYSTEMFILE_BASEBRD = "Branding/Basebrd/basebrd.dll";
    private static final String APP_ID = "AdvancedLogonEditor-ale.controller.Main";
    private static String os;
    private static String osPath;

    private SystemInformation() {

    }

    /**
     * Returns the path to the authui.dll systemfile.
     *
     * @return path
     */
    public static Path getSystemfile_Authui() {
        Path ret = SystemInformation.getOSPath().resolve(SYSTEMFILE_AUTHUI_x86);

        if (!isX86() && FileUtil.control(SystemInformation.getOSPath().resolve(SYSTEMFILE_AUTHUI_x64))) {
            ret = SystemInformation.getOSPath().resolve(SYSTEMFILE_AUTHUI_x64);
        }

        return ret;
    }

    /**
     * Returns the path to the basebrd.dll systemfile.
     *
     * @return path
     */
    public static Path getSystemfile_BaseBrd() {
        return SystemInformation.getOSPath().resolve(SYSTEMFILE_BASEBRD);
    }

    /**
     * Returns the dir where the logon backgrounds are saved.
     *
     * @return path to logon dir
     */
    public static Path getBackgroundDirectory() {
        Path ret = null;

        // Setting depends on the authui location. It is not possible to control if the directory is
        // already there.
        if (!isX86() && FileUtil.control(SystemInformation.getOSPath().resolve(SYSTEMFILE_AUTHUI_x64))) {
            ret = SystemInformation.getOSPath().resolve(SYSTEM_BG_DIR_x64);
        } else {
            ret = SystemInformation.getOSPath().resolve(SYSTEM_BG_DIR_x86);
        }

        return ret;
    }

    /**
     * Returns the commandline path. Adds /c. So its not a valid path.
     * 
     * @return %windir%/Sysnative||System32/cmd.exe /c
     */
    public static String getCMD() {
        String ret = SystemInformation.getOSPath() + CMD_x86;

        if (!isX86() && FileUtil.control(SystemInformation.getOSPath().resolve(CMD_x64_wO_c))) {
            ret = SystemInformation.getOSPath() + CMD_x64;
        }

        return ret;
    }

    /**
     * Method to get the 64bit commandline path. For Le dirty workaround.
     *
     * @return the path for the 64bit commandline. Adds /c. So its not a valid path.
     */
    public static String getCMD64() {
        return SystemInformation.getOSPath() + CMD_x64;
    }

    /**
     * Returns the path to the OS root directory.
     *
     * @return path
     */
    public static Path getOSPath() {
        if (osPath == null) {
            osPath = System.getProperty("user.home").substring(0, 3) + WIN7_FOLDERNAME;
        }

        return Paths.get(osPath);
    }

    /**
     * Asks if an instance of this program is already running.
     *
     * @param handler The messagehandler to handle the messages between the instances.
     * @return boolean
     */
    public static boolean isAlreadyRunning(MessageHandler handler) {
        boolean alreadyRunning;
        try {
            JUnique.acquireLock(APP_ID, handler);
            alreadyRunning = false;
        } catch (AlreadyLockedException e) {
            alreadyRunning = true;
        }

        return alreadyRunning;
    }

    /**
     * Wakes the other instance.
     *
     * @return An answer string.
     */
    public static String wakeUpOtherOne() {
        return JUnique.sendMessage(APP_ID, "");
    }

    /**
     * Releases the lock in the jvm.
     * 
     */
    public static void releaseJVMLock() {
        JUnique.releaseLock(APP_ID);
    }

    /**
     * Tests if the current OS is windows 7.
     *
     * @return boolean
     */
    public static boolean isWin7() {
        if (os == null) {
            getOS();
        }

        return (os.contains(WIN7_REGEX));
    }

    private static String getOS() {
        if (os == null) {
            os = System.getProperty("os.name");
        }

        return os;
    }

    private static boolean isX86() {
        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
        String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");

        boolean isX86;
        if (arch.endsWith("64") || ((wow64Arch != null) && wow64Arch.endsWith("64"))) {
            isX86 = false;
        } else {
            isX86 = true;
        }

        return isX86;
    }
}
