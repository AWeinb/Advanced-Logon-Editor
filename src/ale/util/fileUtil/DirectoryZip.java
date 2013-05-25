/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.util.fileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

final class DirectoryZip {

    private static String suffix = ".zip";
    private static final int BUFFERSIZE = 4096;

    private static int prefixLength;
    private static ZipOutputStream zipOut;
    private static byte[] ioBuffer = new byte[BUFFERSIZE];

    private DirectoryZip() {
    }

    static void zipDirectory(Path zipdir, Path destDirectory, String zipSuffix) throws IOException {
        assert FileUtil.controlDirectory(zipdir) && (destDirectory != null);
        if (zipSuffix != null) {
            suffix = zipSuffix;
        } else {
            suffix = ".zip";
        }

        prefixLength = zipdir.toString().lastIndexOf("/") + 1;
        zipOut = new ZipOutputStream(new FileOutputStream(destDirectory.resolve(zipdir.getFileName() + suffix).toFile()));
        createZipFrom(zipdir.toFile());
        zipOut.close();
    }

    private static void createZipFrom(final File dir) throws IOException {
        Files.walkFileTree(dir.toPath(), new FileVisitor<Path>() {
            Path tmp = dir.toPath();

            @Override
            public FileVisitResult postVisitDirectory(Path dir1, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir1, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                FileInputStream in = new FileInputStream(file.toString());
                zipOut.putNextEntry(new ZipEntry(file.subpath(this.tmp.getNameCount(), file.getNameCount()).toString()
                        .substring(prefixLength)));

                int bytesRead;
                while ((bytesRead = in.read(ioBuffer)) > 0) {
                    zipOut.write(ioBuffer, 0, bytesRead);
                }

                zipOut.closeEntry();
                in.close();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.TERMINATE;
            }
        });
    }

    static boolean unpackZipTo(Path zipfile, Path destDirectory) throws IOException {
        boolean ret = true;

        byte[] bytebuffer = new byte[BUFFERSIZE];
        ZipInputStream zipinputstream = new ZipInputStream(new FileInputStream(zipfile.toFile()));

        ZipEntry zipentry;
        while ((zipentry = zipinputstream.getNextEntry()) != null) {
            Path newFile = destDirectory.resolve(zipentry.getName());

            if (!Files.exists(newFile.getParent(), LinkOption.NOFOLLOW_LINKS)) {
                Files.createDirectories(newFile.getParent());
            }

            if (!Files.isDirectory(newFile, LinkOption.NOFOLLOW_LINKS)) {
                FileOutputStream fileoutputstream = new FileOutputStream(newFile.toFile());

                int bytes;
                while ((bytes = zipinputstream.read(bytebuffer)) > -1) {
                    fileoutputstream.write(bytebuffer, 0, bytes);
                }
                fileoutputstream.close();
            }
            zipinputstream.closeEntry();
        }

        zipinputstream.close();

        return ret;
    }

    public static boolean unpackFileFromZip(Path zipfile, String filename, Path destDirectory) throws IOException {
        boolean ret = true;

        byte[] bytebuffer = new byte[BUFFERSIZE];
        ZipInputStream zipinputstream = new ZipInputStream(new FileInputStream(zipfile.toFile()));

        ZipEntry zipentry;
        while ((zipentry = zipinputstream.getNextEntry()) != null) {
            if (zipentry.getName().equals(filename)) {
                Path newFile = destDirectory.resolve(zipentry.getName());
                FileOutputStream fileoutputstream = new FileOutputStream(newFile.toFile());

                int bytes;
                while ((bytes = zipinputstream.read(bytebuffer)) > -1) {
                    fileoutputstream.write(bytebuffer, 0, bytes);
                }

                fileoutputstream.close();
                zipinputstream.closeEntry();
                zipinputstream.close();

                return ret;
            }

            zipinputstream.closeEntry();
        }

        zipinputstream.close();

        return ret;
    }
}