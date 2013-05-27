/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.model.skin;

/*
 * This class contains basic functionallity for a bitmap class.
 */
abstract class AbstractBitmap {

    protected static final int BITMAPFILEHEADER_SIZE = 14;
    protected static final int BITMAPINFOHEADER_SIZE = 40;

    protected static final byte bfType[] = { 'B', 'M' };
    protected static final int bfReserved = 0;
    protected static final int bfOffBits = BITMAPFILEHEADER_SIZE + BITMAPINFOHEADER_SIZE;

    protected static final int biSize = BITMAPINFOHEADER_SIZE;
    protected static final int biPlanes = 1;
    protected static final int biBitCount = 32;
    protected static final int biCompression = 0;
    protected static final int biSizeImage = 0;
    protected static final int biXPelsPerMeter = 3937;
    protected static final int biYPelsPerMeter = 3937;
    protected static final int biClrUsed = 0;
    protected static final int biClrImportant = 0;

    AbstractBitmap() {
    }

    protected static byte[] intToWord(int parValue) {
        byte retValue[] = new byte[2];

        retValue[0] = (byte) (parValue & 0x00FF);
        retValue[1] = (byte) ((parValue >> 8) & 0x00FF);

        return retValue;
    }

    protected static byte[] intToDWord(int parValue) {
        byte retValue[] = new byte[4];

        retValue[0] = (byte) (parValue & 0x00FF);
        retValue[1] = (byte) ((parValue >> 8) & 0x000000FF);
        retValue[2] = (byte) ((parValue >> 16) & 0x000000FF);
        retValue[3] = (byte) ((parValue >> 24) & 0x000000FF);

        return retValue;
    }
}
