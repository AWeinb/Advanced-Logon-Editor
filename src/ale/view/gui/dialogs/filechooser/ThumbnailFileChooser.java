package ale.view.gui.dialogs.filechooser;

import java.awt.Component;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs.filechooser <br/>
 * Class  : ThumbnailFileChooser <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ThumbnailFileChooser</code> class contains a special jFilechooser which shows small thumbnails of images.  <br/>
 * 
 * @author BoffinbraiN (http://stackoverflow.com/questions/4096433/making-jfilechooser-show-image-thumbnails) (last visited: 05.2013)
 * @author (modified)
 * Memory problems..
 * 
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 16.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class ThumbnailFileChooser extends JFileChooser {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** All preview icons will be this width and height */
    private static final int ICON_WIDTH = 180;

    //    private static final int ICON_HEIGHT = 100;

    //    /** This blank icon will be used while previews are loading */
    //    private static final Image LOADING_IMAGE = new BufferedImage(ICON_WIDTH, ICON_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    //
    //    /** Edit this to determine what file types will be previewed. */
    //    private final Pattern imageFilePattern = Pattern.compile(".+?\\.(png|jpe?g|gif|tiff?)$", Pattern.CASE_INSENSITIVE);
    //
    //    /** Use a weak hash map to cache images until the next garbage collection (saves memory) */
    //    private Map<File, ImageIcon> imageCache = new WeakHashMap<File, ImageIcon>();
    //
    //    private static final int MEM_MIN = 100000000;

    /**
     * 
     */
    public ThumbnailFileChooser() {
        super();

        fixComponents(this);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private Component fixComponents(Component component) {
        if (component instanceof JList<?>) {
            final JList<?> list = (JList<?>) component;
            list.setFixedCellWidth(ICON_WIDTH);
            return null;

        } else if (component instanceof JComboBox<?>) {
            JComboBox<?> cb = (JComboBox<?>) component;
            cb.setBorder(null);
            cb.setBackground(cb.getParent().getBackground());
            return null;

        } else if (component instanceof Container) {
            Component[] components = ((Container) component).getComponents();
            for (Component c : components) {
                Component childComponent = fixComponents(c);

                if (childComponent != null) {
                    return childComponent;
                }
            }
        }
        return null;
    }

    //    private class ThumbnailView extends FileView {
    //
    //        ThumbnailView() {
    //        }
    //
    //        @Override
    //        public Icon getIcon(File file) {
    //            if (file.isDirectory()) {
    //                return null;
    //            }
    //
    //            if (!imageFilePattern.matcher(file.getName()).matches()) {
    //                return null;
    //            }
    //
    //            // Our cache makes browsing back and forth lightning-fast! :D
    //            synchronized (imageCache) {
    //                ImageIcon icon = imageCache.get(file);
    //
    //                if (icon == null) {
    //                    // Create a new icon with the default image
    //                    icon = new ImageIcon(LOADING_IMAGE);
    //                    // Add to the cache
    //                    imageCache.put(file, icon);
    //                    // Submit a new task to load the image and update the icon
    //                    Main.executeThreads(new ThumbnailIconLoader(icon, file));
    //                }
    //
    //                return icon;
    //            }
    //        }
    //    }
    //
    //    private class ThumbnailIconLoader implements Runnable {
    //        private final ImageIcon icon;
    //        private final File file;
    //
    //        public ThumbnailIconLoader(ImageIcon icon, File file) {
    //            this.icon = icon;
    //            this.file = file;
    //        }
    //
    //        @Override
    //        public void run() {
    //            BufferedImage img = new BufferedImage(ICON_WIDTH, ICON_HEIGHT, BufferedImage.TYPE_INT_RGB);
    //            Graphics2D g2 = (Graphics2D) img.getGraphics().create();
    //            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    //            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    //            g2.setColor(Color.WHITE);
    //            g2.fillRect(0, 0, ICON_WIDTH, ICON_HEIGHT);
    //            g2.setColor(Color.LIGHT_GRAY);
    //            g2.fillRect(7, 7, ICON_WIDTH - 10, ICON_HEIGHT - 25);
    //            g2.setColor(Color.BLACK);
    //            g2.drawString(file.getName(), 15, ICON_HEIGHT - 5);
    //
    //            BufferedImage detail = null;
    //            if (Runtime.getRuntime().freeMemory() > MEM_MIN) {
    //                ImageReader reader = ImageIO.getImageReadersBySuffix(file.getName().split("\\.")[1]).next();
    //                try (FileInputStream fis = new FileInputStream(file)) {
    //                    try (ImageInputStream iis = ImageIO.createImageInputStream(fis)) {
    //                        reader.setInput(iis);
    //                        ImageReadParam param = reader.getDefaultReadParam();
    //                        int mult = 3;
    //                        param.setSourceRegion(new Rectangle(0, 0, ICON_WIDTH * mult, ICON_HEIGHT * mult));
    //                        detail = reader.read(0, param);
    //                    }
    //                    fis.close();
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //
    //            }
    //
    //            if (detail != null) {
    //                g2.drawImage(detail, 5, 5, ICON_WIDTH - 10, ICON_HEIGHT - 25, null);
    //            }
    //            icon.setImage(img);
    //
    //            // Repaint the dialog so we see the new icon.
    //            SwingUtilities.invokeLater(new Runnable() {
    //                @Override
    //                public void run() {
    //                    repaint();
    //                }
    //            });
    //            try {
    //                finalize();
    //            } catch (Throwable e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    }
}