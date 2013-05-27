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

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.skin <br/>
 * Class  : ZipSkinLoader <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ZipSkinLoader</code> class is used to load a skin which is saved in a zip archive. The class provides a method to load a skin to
 * a properties object and a method to unpack one file.<br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class ZipSkinLoader {

    private ZipSkinLoader() {
    }

    static SkinPropertiesVO load(Path skinfile) throws IOException {
        assert FileUtil.control(skinfile);
        String name = skinfile.getFileName().toString().split("\\.")[0];
        Path newSkinPath = Constants.PROGRAM_TMPSKIN_PATH.resolve(name);

        FileUtil.deleteDirectory(newSkinPath);
        FileUtil.createDirectory(newSkinPath);
        FileUtil.createDirectory(newSkinPath.resolve(Constants.SKIN_IMGFOLDER));

        FileUtil.unzipDirectoryTo(skinfile, newSkinPath);

        SkinPreviewVO preview = new SkinPreviewVO(newSkinPath.resolve(Constants.SKIN_PREVIEWFILE));
        SkinPropertiesVO tmp = new SkinPropertiesVO(newSkinPath.resolve(Constants.SKIN_IMGFOLDER), preview);

        SkinPropertiesReader.read(tmp, newSkinPath.resolve(Constants.SKIN_PROPFILE));
        assert FileUtil.control(newSkinPath.resolve(Constants.SKIN_PROPFILE));

        return tmp;
    }

    /**
     * Unpacks a single file from the skin zip archive. It's used to extract the preview file to read it.
     *
     * @param skinfile The path to the skin
     * @return Path to the extracted file
     * @throws IOException if the skin path is invalid.
     */
    public static Path unpackFileFromSkin(Path skinfile) throws IOException {
        assert FileUtil.control(skinfile);
        Path ret = null;

        FileUtil.unzipFileFromZip(skinfile, Constants.SKIN_PREVIEWFILE, Constants.PROGRAM_TMPSKIN_PATH);

        Path previewPath = Constants.PROGRAM_TMPSKIN_PATH.resolve(Constants.SKIN_PREVIEWFILE);
        if (FileUtil.control(previewPath)) {
            String name = skinfile.getFileName().toString().split("\\.")[0];
            Path dirPath = Constants.PROGRAM_TMPSKIN_PATH.resolve(name);
            FileUtil.createDirectory(dirPath);
            FileUtil.moveFile(previewPath, dirPath.resolve(Constants.SKIN_PREVIEWFILE));
            previewPath = dirPath.resolve(Constants.SKIN_PREVIEWFILE);

            FileUtil.unzipFileFromZip(skinfile, Constants.SKIN_PREVIEWIMAGE, Constants.PROGRAM_TMPSKIN_PATH);
            Path imgPath = Constants.PROGRAM_TMPSKIN_PATH.resolve(Constants.SKIN_PREVIEWIMAGE);
            if (FileUtil.control(imgPath)) {
                FileUtil.moveFile(imgPath, dirPath.resolve(Constants.SKIN_PREVIEWIMAGE));
            }

            ret = previewPath;
        }

        return ret;
    }
}
