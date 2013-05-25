/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.io.IOException;
import java.nio.file.Path;

import ale.Constants;
import ale.util.fileUtil.FileUtil;

/*
 * class saves a skin to a zip archive.
 */
class ZipSkinSaver {

    private ZipSkinSaver() {
    }

    static void save(Skin skin, Path saveDir) throws IOException {
        assert skin != null;
        save(skin, saveDir, null);
    }

    static void save(Skin skin, Path saveDir, String name) throws IOException {
        Path skinPath;
        if (name == null) {
            skinPath = Constants.PROGRAM_TMPSKIN_PATH.resolve(skin.getFilename());

        } else {
            skinPath = Constants.PROGRAM_TMPSKIN_PATH.resolve(name);
        }

        FileUtil.createDirectory(skinPath);
        SkinPropertiesWriter.write(skin.getProperties(), skinPath.resolve(Constants.SKIN_PROPFILE));
        skin.getProperties().saveSkinPreview(skinPath.resolve(Constants.SKIN_PREVIEWFILE));
        assert FileUtil.control(skinPath.resolve(Constants.SKIN_PROPFILE));
        assert FileUtil.control(skinPath.resolve(Constants.SKIN_PREVIEWFILE));

        Path tmp = Constants.PROGRAM_TMPSKIN_PATH.resolve(skin.getFilename());
        if (FileUtil.controlDirectory(tmp.resolve(Constants.SKIN_IMGFOLDER))) {
            FileUtil.copyDirectory(tmp.resolve(Constants.SKIN_IMGFOLDER), skinPath);
        }

        if (FileUtil.control(tmp.resolve(Constants.SKIN_PREVIEWIMAGE))) {
            FileUtil.copyFile(tmp.resolve(Constants.SKIN_PREVIEWIMAGE), skinPath.resolve(Constants.SKIN_PREVIEWIMAGE), false);
        }

        FileUtil.createDirectory(saveDir);
        FileUtil.zipDirectoryTo(skinPath, saveDir, Constants.SKINFILE_SUFFIX);
    }
}
