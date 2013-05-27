/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.uiFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;

import ale.Constants;
import ale.controller.Main;
import ale.model.dllResource.DLLResource;
import ale.model.dllResource.ResourceVO;
import ale.model.dllResource.ResourceVO.ResourceType;
import ale.model.skin.SkinPropertiesVO;
import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.model.uiFile <br/>
 * Class  : IUIFile <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>IUIFile</code> class represents an ui text file. It contains methods to read a uifile, to write it and to apply it to
 * an dll file.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class UIFile implements IUIFile {

    private UIFileModifier mod;
    private DLLResource resource;

    private Path authuiTmp;
    private Path uitextfilepath;

    /**
     * Constructor.
     * 
     * @param uitextfile The path to the textfile which contains the unchanged uifile as base.
     * @param script the path where the apply script can be saved.
     * @param authuiTmp the path to a temporary authui dll file.
     * @param basebrdTmp the path to a temporary basebrd dll file.
     */
    public UIFile(Path uitextfile, Path script, Path authuiTmp, Path basebrdTmp) {
        if (!FileUtil.control(uitextfile)) {
            IllegalArgumentException iae = new IllegalArgumentException("The uitextfile must not be null!");
            Main.handleUnhandableProblem(iae);
        }

        this.authuiTmp = authuiTmp;
        this.uitextfilepath = uitextfile;
        this.mod = new UIFileModifier(script, authuiTmp, basebrdTmp);
    }

    @Override
    public UIFileTextVO readUIFile() {
        UIFileTextVO uiFile = new UIFileTextVO();

        assert FileUtil.control(this.uitextfilepath);
        UIFileReader.readUIFile(uiFile, this.uitextfilepath);
        return uiFile;
    }

    @Override
    public void writeUIFile(UIFileTextVO uifile) throws IOException {
        if (uifile == null) {
            IllegalArgumentException iae = new IllegalArgumentException("The uiFile must not be null!");
            Main.handleUnhandableProblem(iae);
        }

        UIFileWriter.writeUIFile(uifile, this.uitextfilepath);
    }

    @Override
    public void applyUIFileInTempAuthui(SkinPropertiesVO props, final UIFileTextVO uifile) throws IOException, InterruptedException {
        if ((props == null) || (uifile == null)) {
            IllegalArgumentException iae = new IllegalArgumentException("The props/uiFile must not be null!");
            Main.handleUnhandableProblem(iae);
        }

        this.resource = this.mod.modifyUIFile(props, uifile);
        writeUIFile(uifile);
        @SuppressWarnings("serial")
        LinkedList<ResourceVO> res = new LinkedList<ResourceVO>() {
            {
                add(new ResourceVO(UIFile.this.uitextfilepath, ResourceType.UIFILE, Constants.RESHACKER_UIFILE1_RESNUM));
            }
        };

        this.resource.addResources(this.authuiTmp, res);
    }
}
