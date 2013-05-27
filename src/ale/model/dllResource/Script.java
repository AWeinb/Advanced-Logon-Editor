/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.dllResource;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ale.util.fileUtil.FileUtil;

/*
 * Class that represents a resource hacker script to easily add resources to a dll file.
 */
class Script {

    private Path scriptPath;

    protected Script(Path scriptPath) {
        assert scriptPath != null;

        this.scriptPath = scriptPath;
    }

    protected Path scriptPath() {
        return this.scriptPath;
    }

    private final String newLine = System.getProperty("line.separator");
    private final String RES_SCRIPTHEADER1 = "[FILENAMES]" + this.newLine + "Exe    = ";
    private final String RES_SCRIPTHEADER2 = "SaveAs = ";
    private final String RES_SCRIPTHEADER3 = "Log    = ";
    private final String RES_SCRIPTHEADER4 = "[COMMANDS]" + this.newLine;
    private final String CMD_ADDOVERWRITE = " -addoverwrite ";

    /*
     * Creates a script for the resource hacker. With this it is possible to add some resources in one call.
     */
    protected Script createAddScript(Path resourcefile, List<ResourceVO> resources, Path resHackerLog) {
        assert (resourcefile != null) && (resHackerLog != null);
        assert (resources != null) && !resources.isEmpty();

        List<String> sb = new LinkedList<>();
        Iterator<ResourceVO> i = resources.listIterator(0);

        sb.add(this.RES_SCRIPTHEADER1 + resourcefile + this.newLine);
        sb.add(this.RES_SCRIPTHEADER2 + resourcefile + this.newLine);
        sb.add(this.RES_SCRIPTHEADER3 + resHackerLog + this.newLine + this.newLine);
        sb.add(this.RES_SCRIPTHEADER4);

        while (i.hasNext()) {
            ResourceVO tmp = i.next();
            sb.add(this.CMD_ADDOVERWRITE + tmp.getPath() + ", " + tmp.getType() + ", " + tmp.getResourceNumber() + "," + this.newLine);
        }

        if (!FileUtil.writeTextFile(this.scriptPath, sb, false)) {
            return null;
        }

        assert FileUtil.control(this.scriptPath);
        return new Script(this.scriptPath);
    }
}
