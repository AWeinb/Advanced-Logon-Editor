/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.controller <br/>
 * Class  : ProgramLauncher <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ProgramLauncher</code> class contains methods to run an external program and to get the programoutput afterwards.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 21.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class ProgramLauncher {

    private static String output;
    private static final Logger LOGGER = Main.getLogger();

    private ProgramLauncher() {
    }

    /**
     * This method starts the program with the commandline argument.
     *
     * @param programpath The path to the executable.
     * @param command The commandline argument for the program.
     * @param printOutput Sets if strings of the executed program should be read.
     * @param possibleErrorString Method searchs the output for this string and returns false if there.
     * @return Returns true if successful.
     * @throws IOException If the executable path is invalid.
     * @throws InterruptedException If the new process is interrupted.
     */
    public static boolean start(String programpath, String command, boolean printOutput, String possibleErrorString) throws IOException,
            InterruptedException {
        if (command == null) {
            command = "";
        }

        boolean ret = true;
        output = "";

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(programpath + command);

        if (printOutput) {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()))) {
                String line = null;

                while ((line = input.readLine()) != null) {
                    LOGGER.debug(programpath + "> " + line);
                    output += line;

                    if ((possibleErrorString != null) && !possibleErrorString.equals("") && line.contains(possibleErrorString)) {
                        ret = false;
                    }
                }
                input.close();

            } catch (IOException e) {
                throw e;
            }
        }

        LOGGER.debug("External program exited with status: " + pr.waitFor());
        return ret;
    }

    /**
     * This method returns the complete output of the last runned external program.
     *
     * @return Returns the output as string.
     */
    public static String getOutput() {
        return output;
    }
}
