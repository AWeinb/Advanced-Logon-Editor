/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.uiFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

final class UIFileWriter {

    private UIFileWriter() {
    }

    protected static void writeUIFile(UIFileTextVO uifile, Path destination) throws IOException {
        assert (uifile != null) && (destination != null);
        Charset charset = Charset.defaultCharset();
        OpenOption[] options = new OpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING };

        try (BufferedWriter writer = Files.newBufferedWriter(destination, charset, options)) {
            for (int i = 0; i < uifile.getUIFileSize(); i++) {
                writer.write(uifile.getLine(i));
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            throw e;
        }
    }
}
