/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.uiFile;

import java.nio.file.Path;

import ale.util.fileUtil.FileUtil;

final class UIFileReader {

    private UIFileReader() {
    }

    protected static void readUIFile(UIFileTextVO uiFile, Path uifilepath) {
        assert (uiFile != null) && FileUtil.control(uifilepath);
        uiFile.setLineList(FileUtil.readTextFile(uifilepath));
    }
}
