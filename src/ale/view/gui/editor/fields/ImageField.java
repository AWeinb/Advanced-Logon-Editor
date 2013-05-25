/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.editor.fields;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ale.Constants;
import ale.view.gui.GUIConstants;
import ale.view.gui.dialogs.FileChooserDialog;
import ale.view.gui.util.ImageFileFilter;
import ale.view.gui.util.VerticalLayout;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.editor.fields <br/>
 * Class  : ImageField <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>ImageField</code> class represents the image menu field. It is used in the right-side menu of the editor.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 16.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public abstract class ImageField extends Field {

    private static final long serialVersionUID = 1L;
    private static final Dimension PATHFIELD_DIM = new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH - 30, 20);
    private static final Dimension BUTTON_DIM = new Dimension(15, 21);
    private Dimension preferredDim = new Dimension(GUIConstants.DEFAULT_FIELD_WIDTH, 85);
    private String fcdFilter = Constants.DEFAULT_INPUTIMAGE_TYPE;

    private JTextField path;

    /**
     * @param initialValue The path which should be shown at first.
     * @param bg the background of the panel.
     * @param addTransparentBtn if true a special button is shown which selects a transparent image.
     */
    public ImageField(Path initialValue, Color bg, boolean addTransparentBtn) {
        String initialValue_str = "";

        if (initialValue != null) {
            initialValue_str = initialValue.toString();
        }

        if (bg == null) {
            bg = Color.WHITE;
        }

        this.preferredDim = new Dimension(this.preferredDim.width - 15, this.preferredDim.height);

        create(initialValue_str, bg, null, addTransparentBtn);
    }

    /**
     * @param initialValue The path which should be shown at first.
     * @param bg the background of the panel.
     * @param fieldTitle titlestring
     * @param addTransparentBtn if true a special button is shown which selects a transparent image.
     */
    public ImageField(Path initialValue, Color bg, String fieldTitle, boolean addTransparentBtn) {
        String initialValue_str = "";

        if (initialValue != null) {
            initialValue_str = initialValue.toString();
        }

        if (bg == null) {
            bg = Color.WHITE;
        }

        if (fieldTitle == null) {
            fieldTitle = "";
        }

        create(initialValue_str, bg, fieldTitle, addTransparentBtn);
    }

    /**
     * Gets called if a file was chosen.
     *
     * @param input the chosen file.
     */
    public abstract void onFileChosen(File input);

    /**
     * Sets the filefilter for the filechooserdialog. The filterobject is intern created.
     *
     * @param filter The filter-string like ".png"
     */
    public void setImageFileFilter(String filter) {
        if (filter != null) {
            this.fcdFilter = filter;
        }
    }

    /**
     * Updates the pathfield and resets the textcolor, if needed.
     *
     * @param text the new text.
     * @param colorReset boolean
     */
    public void updatePathField(Path text, boolean colorReset) {
        String replace = text == null ? "" : text.toString();
        this.path.setText(replace);
        this.path.setToolTipText(replace);

        if (colorReset) {
            this.path.setForeground(Color.BLACK);
        }
    }

    private void create(String initialValue, Color bg, String title, boolean addTransparentBtn) {
        setPreferredSize(this.preferredDim);
        if (title != null) {
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(title),
                    BorderFactory.createEmptyBorder(5, 0, 0, 0)));
        } else {
            setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        }

        setLayout(new VerticalLayout(5, VerticalLayout.CENTER));
        setBackground(bg);

        this.path = new JTextField();
        this.path.setPreferredSize(PATHFIELD_DIM);
        this.path.setEditable(false);
        this.path.setFocusable(false);
        this.path.setText(initialValue);
        this.path.setToolTipText(initialValue);
        this.path.setBackground(Color.LIGHT_GRAY.brighter());
        this.add(this.path);

        JPanel panel = new JPanel();
        panel.setBackground(bg);
        this.add(panel);

        JButton choose = new JButton("...");
        choose.setMinimumSize(BUTTON_DIM);
        choose.setBackground(bg);
        choose.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileChooserDialog(Field.strFileChooserTitle, new ImageFileFilter(ImageField.this.fcdFilter), false) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onApprove(File file) {
                        onFileChosen(file);
                    }

                    @Override
                    public void onCancel() {
                        ;
                    }
                };
            }
        });
        panel.add(choose);

        if (addTransparentBtn) {
            JButton toggleTransparent = new JButton(Field.strToggleTransparent);
            toggleTransparent.setMinimumSize(BUTTON_DIM);
            toggleTransparent.setBackground(bg);
            toggleTransparent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onFileChosen(Constants.SKIN_TRANSPARENT_BG.toFile());
                }
            });
            panel.add(toggleTransparent);
        }

        JButton reset = new JButton(Field.strReset);
        reset.setMinimumSize(BUTTON_DIM);
        reset.setBackground(bg);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFileChosen(null);
            }
        });
        panel.add(reset);
    }
}