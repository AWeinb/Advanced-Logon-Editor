/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import ale.controller.Main;
import ale.util.fileUtil.FileUtil;

/*
 * Class creates a bitmap image that can be used in windows dll files. To understand how it works you should search for the
 * Defination of the bitmap image online.
 * For the program it would be best to implement a parallel algorithm... maybe later.
 */
final class Bitmap extends AbstractBitmap {

    private static int bfSize = 0; // To set in program: Filesize in bytes
    private static int biWidth = 0; // To set in program: Pixels w
    private static int biHeight = 0; // To set in program: Pixels h

    private static Path image;

    private Bitmap() {
    }

    // newName is only in this program useful.
    static Path convertToBMP(Path imagepath, Path destination, String newName) throws IOException, InterruptedException {
        if (!FileUtil.control(imagepath) || (destination == null)) {
            throw new IllegalArgumentException();
        }

        Path ret;
        if (!imagepath.getFileName().toString().endsWith("bmp")) {
            assert FileUtil.control(imagepath);
            Bitmap.image = imagepath;
            if ((newName != null) && !newName.equals("")) {
                ret = destination.resolve(newName + ".bmp");
            } else {
                ret = destination.resolve(imagepath.getFileName() + ".bmp");
            }
            write(ret);

        } else {
            ret = imagepath;
        }

        return ret;
    }

    private static void write(Path path) throws IOException, InterruptedException {
        int value;
        int width;
        int height;
        byte rgba[] = new byte[4];
        int bitmap[];
        PixelGrabber pg;
        BufferedImage img = null;

        try {
            img = ImageIO.read(image.toFile());
            image = path;

        } catch (IOException e) {
            IllegalStateException ise = new IllegalStateException("An exception occured while converting!");
            Main.handleUnhandableProblem(ise);
        }

        width = img.getWidth();
        height = img.getHeight();
        bitmap = new int[width * height];

        pg = new PixelGrabber(img, 0, 0, width, height, bitmap, 0, width);
        pg.grabPixels();

        bfSize = ((width * height) * 4) + BITMAPFILEHEADER_SIZE + BITMAPINFOHEADER_SIZE;
        biWidth = width;
        biHeight = height;

        if (!Files.exists(image, LinkOption.NOFOLLOW_LINKS)) {
            Files.createFile(image);
            assert Files.exists(image);
        }

        try (OutputStream fo = new FileOutputStream(image.toFile())) {
            {
                fo.write(AbstractBitmap.bfType);
                fo.write(intToDWord(bfSize));
                fo.write(intToDWord(AbstractBitmap.bfReserved));
                fo.write(intToDWord(AbstractBitmap.bfOffBits));
            }
            {
                fo.write(intToDWord(AbstractBitmap.biSize));
                fo.write(intToDWord(biWidth));
                fo.write(intToDWord(biHeight));
                fo.write(intToWord(AbstractBitmap.biPlanes));
                fo.write(intToWord(AbstractBitmap.biBitCount));
                fo.write(intToDWord(AbstractBitmap.biCompression));
                fo.write(intToDWord(AbstractBitmap.biSizeImage));
                fo.write(intToDWord(AbstractBitmap.biXPelsPerMeter));
                fo.write(intToDWord(AbstractBitmap.biYPelsPerMeter));
                fo.write(intToDWord(AbstractBitmap.biClrUsed));
                fo.write(intToDWord(AbstractBitmap.biClrImportant));
            }
            {
                for (int col = biHeight - 1; col >= 0; col--) {
                    for (int row = 0; row < biWidth; row++) {
                        value = bitmap[(col * biWidth) + row];
                        rgba[0] = (byte) (value & 0xFF);
                        rgba[1] = (byte) ((value >> 8) & 0xFF);
                        rgba[2] = (byte) ((value >> 16) & 0xFF);
                        rgba[3] = (byte) ((value >> 24) & 0xFF);
                        fo.write(rgba);
                    }
                }
            }

            fo.flush();
            fo.close();

        } catch (IOException e) {
            throw e;
        }
    }
}
