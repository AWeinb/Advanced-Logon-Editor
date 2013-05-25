/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.util.fileUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

final class FileWriter {

    private FileWriter() {
    }

    protected static boolean writeFile(Path file, List<String> lines, boolean append) {
        assert (file != null) && (lines != null) && (lines.size() > 0);
        OpenOption[] options;
        Charset charset = Charset.defaultCharset();

        if (append) {
            options = new OpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.APPEND, StandardOpenOption.CREATE };
        } else {
            options = new OpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING };
        }

        try (BufferedWriter writer = Files.newBufferedWriter(file, charset, options)) {
            String separator = System.getProperty("line.separator");

            if ((lines == null) || (lines.size() <= 0)) {
                writer.write("");

            } else {
                for (String line : lines) {
                    writer.write(line + separator);
                }
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            return false;
        }

        assert FileUtil.control(file);
        return true;
    }
}
