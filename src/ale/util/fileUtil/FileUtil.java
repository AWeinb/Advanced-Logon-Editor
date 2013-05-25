/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.util.fileUtil;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import ale.Constants;
import ale.controller.Main;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.util.fileUtil <br/>
 * Class  : FileUtil <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>FileUtil</code> class contains helpermethods to handle files.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 23.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class FileUtil {

    private static final int BYTE = 262144;
    private static final int MAXWIDTH = 2000;

    /**
     * Method controls if a file exists.
     *
     * @param p path to the file
     * @return boolean
     */
    public static boolean control(Path p) {
        return ((p != null) && Files.exists(p, LinkOption.NOFOLLOW_LINKS));
    }

    /**
     * Method controls if a file exists and if it is a directory.
     *
     * @param p path to the directory
     * @return boolean
     */
    public static boolean controlDirectory(Path p) {
        return ((p != null) && Files.exists(p, LinkOption.NOFOLLOW_LINKS) && Files.isDirectory(p, LinkOption.NOFOLLOW_LINKS));
    }

    /**
     * Zips a directory and saves it at a given destination.
     *
     * @param dir the path to the directory that should be packed
     * @param destDir the destination
     * @param suffix the suffix of the new file
     * @return boolean
     * @throws IOException -
     */
    public static boolean zipDirectoryTo(Path dir, Path destDir, String suffix) throws IOException {
        if (!controlDirectory(dir) || !controlDirectory(destDir)) {
            return false;
        }

        DirectoryZip.zipDirectory(dir, destDir, suffix);
        return true;
    }

    /**
     * Unpacks the zip to a given location.
     *
     * @param zip the zip file
     * @param dir the destination directory
     * @return boolean
     * @throws IOException -
     */
    public static boolean unzipDirectoryTo(Path zip, Path dir) throws IOException {
        if (!control(zip) || !controlDirectory(dir)) {
            return false;
        }

        return DirectoryZip.unpackZipTo(zip, dir);
    }

    /**
     * Unpacks a single file from a zipfile.
     *
     * @param zip the zip file
     * @param filename the name of the file in the zip
     * @param destDirectory the destination directory
     * @return boolean
     * @throws IOException -
     */
    public static boolean unzipFileFromZip(Path zip, String filename, Path destDirectory) throws IOException {
        if (!control(zip)) {
            return false;
        }

        return DirectoryZip.unpackFileFromZip(zip, filename, destDirectory);
    }

    /**
     * Reads a textfile and returns a list with the lines.
     *
     * @param file The textfile
     * @return the list of lines
     */
    public static List<String> readTextFile(Path file) {
        if (!FileUtil.control(file)) {
            IllegalArgumentException iae = new IllegalArgumentException("The filepath is null or points to an invalid location!");
            Main.handleUnhandableProblem(iae);
        }

        return FileReader.readFile(file);
    }

    /**
     * Writes the list into a textfile. It is possible to add lines to an existing file.
     *
     * @param file the path to the textfile
     * @param lines the lines to write
     * @param append if the lines should be appended it the file exists.
     * @return boolean
     */
    public static boolean writeTextFile(Path file, List<String> lines, boolean append) {
        if ((file == null) || Files.isDirectory(file, LinkOption.NOFOLLOW_LINKS)) {
            return false;
        }

        return FileWriter.writeFile(file, lines, append);
    }

    /**
     * Deletes a file.
     *
     * @param file the path to the file
     * @return boolean
     */
    public static boolean deleteFile(Path file) {
        boolean ret = false;

        if ((file != null) && Files.exists(file, LinkOption.NOFOLLOW_LINKS)) {
            try {
                if (file.toFile().canWrite()) {
                    Files.delete(file);
                }
                ret = !Files.exists(file, LinkOption.NOFOLLOW_LINKS);

            } catch (IOException e) {
                IllegalStateException ise = new IllegalStateException("An IO exception occured while deleting file '" + file + "'");
                Main.handleUnhandableProblem(ise);
            }
        }

        return ret;
    }

    /**
     * Moves a file to a destination.
     *
     * @param file the path to the file to move
     * @param destination the destination path
     * @throws IOException thrown if a path is invalid
     */
    public static void moveFile(Path file, Path destination) throws IOException {
        if ((file == null) || !Files.exists(file, LinkOption.NOFOLLOW_LINKS) || (destination == null)) {
            IllegalArgumentException iae = new IllegalArgumentException("The filepath is null or points to an invalid location! " + file);
            Main.handleUnhandableProblem(iae);
        }

        Files.move(file, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Copies a file with or without its file attributes.
     *
     * @param file the file path
     * @param destination the destination path
     * @param withAttributs with or without file attributes
     * @throws IOException if a path is invalid
     */
    public static void copyFile(Path file, Path destination, boolean withAttributs) throws IOException {
        if ((file == null) || !Files.exists(file, LinkOption.NOFOLLOW_LINKS) || (destination == null)) {
            IllegalArgumentException iae = new IllegalArgumentException("The file is null or points to an invalid location!");
            Main.handleUnhandableProblem(iae);
        }

        if (withAttributs) {
            Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

        } else {
            Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Creates a directory and if needed its parent directories.
     *
     * @param directory path of the directory
     * @throws IOException -
     */
    public static void createDirectory(Path directory) throws IOException {
        if (directory == null) {
            IllegalArgumentException iae = new IllegalArgumentException("The directorypath is null or points to an invalid location!");
            Main.handleUnhandableProblem(iae);
        }

        Files.createDirectories(directory);
    }

    /**
     * Deletes a directory.
     *
     * @param directory the path of the directory
     * @throws IOException -
     */
    public static void deleteDirectory(Path directory) throws IOException {
        if (!FileUtil.controlDirectory(directory)) {
            return;
        }

        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toFile().canWrite()) {
                    Files.delete(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    Main.handleUnhandableProblem(exc);
                }

                if (dir.toFile().canWrite()) {
                    Files.delete(dir);
                }

                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Copies a directory to a destination.
     *
     * @param source the path to the source directory
     * @param dest the path to the destination
     * @throws IOException -
     */
    public static void copyDirectory(Path source, final Path dest) throws IOException {
        if ((source == null) || (dest == null) || !Files.exists(source, LinkOption.NOFOLLOW_LINKS)
                || !Files.isDirectory(source, LinkOption.NOFOLLOW_LINKS)) {
            throw new InvalidParameterException();
        }

        Files.walkFileTree(source, new FileVisitor<Path>() {
            Path tmp = dest;

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                this.tmp = this.tmp.resolve(dir.getFileName());
                if (!Files.exists(this.tmp, LinkOption.NOFOLLOW_LINKS)) {
                    Files.createDirectory(this.tmp);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (this.tmp.getFileName().equals(file.getParent().getFileName())) {
                    Files.copy(file, this.tmp.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(file, this.tmp.getParent().resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.TERMINATE;
            }
        });
    }

    /**
     * Deactivates or resets the file protection of a windows file.
     *
     * @param file the path to the file
     * @param activated boolean
     * @return boolean
     */
    public static boolean setProtection(Path file, boolean activated) {
        boolean ret = false;

        if (!FileUtil.control(file)) {
            return ret;
        }

        if (activated) {
            ret = WindowsFileProtection.resetProtection(file);

        } else {
            ret = WindowsFileProtection.removeProtection(file);
        }

        return ret;
    }

    /**
     * Starts or ends the windows explorer.
     *
     * @param active boolean
     * @return boolean
     */
    public static boolean setWindowsExplorerActive(boolean active) {
        boolean ret = false;

        if (active) {
            try {
                ret = WindowsExplorer.start();
            } catch (IOException
                     | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            try {
                ret = WindowsExplorer.shutdown();
            } catch (IOException
                     | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return ret;
    }

    /**
     * Scales image to 256kb
     *
     * @param img Imagepath
     * @throws IOException -
     */
    public static void scaleImageTo256kb(Path img) throws IOException {
        File imgFile = img.toFile();
        if (imgFile.length() <= BYTE) {
            return;
        }

        File newFile = Constants.PROGRAM_TMP_PATH.resolve(img.getFileName()).toFile();

        BufferedImage i = ImageIO.read(imgFile);
        if (i.getWidth() > MAXWIDTH) {
            BufferedImage j = new BufferedImage(MAXWIDTH, (int) (i.getHeight() * ((float) MAXWIDTH / i.getWidth())), i.getType());
            Graphics2D g2 = (Graphics2D) j.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(i, 0, 0, j.getWidth(), j.getHeight(), null);
            ImageIO.write(j, "jpg", imgFile);
        }

        float quality = 1f;
        float eps = 0.2f;
        int c = 0;

        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = iter.next();
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        FileImageOutputStream output;
        IIOImage image;

        do {
            c++;
            FileUtil.deleteFile(newFile.toPath());
            iwp.setCompressionQuality(quality);
            output = new FileImageOutputStream(newFile);
            writer.setOutput(output);
            image = new IIOImage(ImageIO.read(imgFile), null, null);
            writer.write(null, image, iwp);
            writer.reset();
            output.flush();
            output.close();
            quality -= eps / c;

        } while ((newFile.length() > BYTE) && (quality > 0.1));

        FileUtil.deleteFile(img);
        FileUtil.moveFile(newFile.toPath(), img);
    }
}
