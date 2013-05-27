/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import ale.controller.Settings;
import ale.view.gui.GUIConstants;
import ale.view.gui.dialogs.filechooser.ThumbnailFileChooser;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : FileChooserDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>FileChooserDialog</code> class contains a crappy filechooser.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public abstract class FileChooserDialog extends Dialog {

    private static final long serialVersionUID = 1L;
    private final JPanel basePanel = new JPanel();
    private ThumbnailFileChooser chooser;

    /**
     * @param title the title of the dialog.
     * @param filter FileFilter
     * @param directoryOnly only directories are shown
     */
    public FileChooserDialog(final String title, final FileFilter filter, final boolean directoryOnly) {
        Boolean old = UIManager.getBoolean("FileChooser.readOnly");
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        this.chooser = new ThumbnailFileChooser();
        UIManager.put("FileChooser.readOnly", old);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileChooserDialog.this.setSize(new Dimension(850, 500));
                FileChooserDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                FileChooserDialog.this.setTitle(title);
                FileChooserDialog.this.setLocationRelativeTo(null);
                FileChooserDialog.this.getContentPane().setLayout(new BorderLayout());
                FileChooserDialog.this.basePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                FileChooserDialog.this.basePanel.setLayout(new BorderLayout());
                FileChooserDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                FileChooserDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                FileChooserDialog.this.getContentPane().add(FileChooserDialog.this.basePanel, BorderLayout.CENTER);

                create(filter, directoryOnly);

                FileChooserDialog.this.setVisible(true);
            }
        });
    }

    /**
     * @param file the chosen file
     */
    public abstract void onApprove(File file);

    /**
     *
     * 
     */
    public abstract void onCancel();

    private void create(FileFilter filter, boolean directoryOnly) {
        this.chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        this.chooser.setFileHidingEnabled(true);
        this.chooser.setAcceptAllFileFilterUsed(false);
        this.basePanel.add(this.chooser, BorderLayout.CENTER);

        if (filter != null) {
            this.chooser.setFileFilter(filter);
        }

        if (directoryOnly) {
            this.chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }

        this.chooser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser jFileChooser = (JFileChooser) event.getSource();
                String command = event.getActionCommand();

                if (command.equals(JFileChooser.APPROVE_SELECTION)) {
                    File selectedFile = jFileChooser.getSelectedFile();
                    Settings.setFilechooserBasepath(selectedFile.toPath());
                    onApprove(selectedFile);
                    dispose();

                } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
                    onCancel();
                    dispose();
                }
            }
        });

        if (Settings.getFilechooserBasePath() != null) {
            this.chooser.setCurrentDirectory(Settings.getFilechooserBasePath().toFile());
        }
    }
}