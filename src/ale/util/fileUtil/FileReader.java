/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.util.fileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

final class FileReader {

    private FileReader() {
    }

    protected static List<String> readFile(Path file) {
        assert FileUtil.control(file);
        List<String> sb = new LinkedList<>();
        String line = null;
        Charset charset = Charset.defaultCharset();

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            while ((line = reader.readLine()) != null) {
                sb.add(line);
            }
            reader.close();

        } catch (IOException e) {
            return null;
        }

        return sb;
    }
}
