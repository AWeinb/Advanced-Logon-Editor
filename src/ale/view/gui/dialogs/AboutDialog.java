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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ale.view.gui.GUIConstants;
import ale.view.gui.util.GUIStrings;
import ale.view.gui.util.VerticalLayout;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.dialogs <br/>
 * Class  : AboutDialog <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>AboutDialog</code> shows infos about the program.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 15.05.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public class AboutDialog extends Dialog {

    private static final long serialVersionUID = 1L;

    protected JPanel basePanel = new JPanel();

    /**
     * Creates the dialog and shows it.
     */
    public AboutDialog() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AboutDialog.this.setSize(new Dimension(400, 650));
                AboutDialog.this.setTitle(GUIConstants.PROGRAM_TITLE);
                AboutDialog.this.setIconImage(GUIConstants.PROGRAM_ICON);
                AboutDialog.this.setLocationRelativeTo(null);
                AboutDialog.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                AboutDialog.this.setModalityType(ModalityType.APPLICATION_MODAL);
                AboutDialog.this.getContentPane().setLayout(new BorderLayout());
                AboutDialog.this.basePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                AboutDialog.this.basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                AboutDialog.this.basePanel.setLayout(new BorderLayout());
                AboutDialog.this.getContentPane().add(AboutDialog.this.basePanel, BorderLayout.CENTER);

                create();

                AboutDialog.this.setVisible(true);
            }
        });
    }

    private void create() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        mainPanel.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollpane = new JScrollPane(mainPanel);

        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_ABOUTDIALOG_TITLE);
        scrollpane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(tmp),
                BorderFactory.createEmptyBorder(5, 5, 0, 5)));
        scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        scrollpane.getVerticalScrollBar().setUnitIncrement(12);
        this.basePanel.add(scrollpane);

        {
            JPanel header = new JPanel();
            header.setLayout(new BorderLayout());
            header.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            mainPanel.add(header, BorderLayout.NORTH);

            {
                JLabel icon = new JLabel();
                icon.setIcon(new ImageIcon(GUIConstants.PROGRAM_ICON_BIG));
                icon.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                header.add(icon, BorderLayout.WEST);

                JPanel basicInfo = new JPanel();
                basicInfo.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));
                basicInfo.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                basicInfo.setBorder(BorderFactory.createEmptyBorder(25, 10, 5, 5));
                header.add(basicInfo, BorderLayout.CENTER);

                JLabel basicInfoLineOne = new JLabel();
                basicInfoLineOne.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                basicInfoLineOne.setText(GUIConstants.PROGRAM_ABOUT_L1);
                basicInfo.add(basicInfoLineOne);

                JLabel basicInfoLineTwo = new JLabel();
                basicInfoLineTwo.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                basicInfoLineTwo.setText(GUIConstants.PROGRAM_ABOUT_L2);
                basicInfo.add(basicInfoLineTwo);

                JLabel basicInfoLineThree = new JLabel();
                basicInfoLineThree.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                basicInfoLineThree.setText(GUIConstants.PROGRAM_ABOUT_L3);
                basicInfo.add(basicInfoLineThree);

                JLabel basicInfoLineFour = new JLabel();
                basicInfoLineFour.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                basicInfoLineFour.setText(GUIConstants.PROGRAM_ABOUT_L4);
                basicInfo.add(basicInfoLineFour);
            }

            JPanel body = new JPanel();
            body.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            body.setLayout(new BorderLayout());
            mainPanel.add(body, BorderLayout.CENTER);

            {
                JTextPane about = new JTextPane();
                about.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                about.setBorder(BorderFactory.createEmptyBorder(25, 5, 5, 5));
                about.setEditable(false);
                about.setText(GUIConstants.PROGRAM_ABOUT);
                body.add(about, BorderLayout.NORTH);

                StyledDocument doc = about.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                StyleConstants.setBold(center, true);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);

                JTextPane copyright = new JTextPane();
                copyright.setBackground(GUIConstants.DEFAULT_BACKGROUND);
                copyright.setBorder(BorderFactory.createEmptyBorder(25, 5, 25, 5));
                copyright.setEditable(false);
                copyright.setText(GUIConstants.PROGRAM_RIGHTS);
                body.add(copyright, BorderLayout.SOUTH);

                doc = copyright.getStyledDocument();
                StyleConstants.setBold(center, false);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        {
            tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_ABOUTDIALOG_OK);
            JButton okButton = new JButton(tmp);
            okButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            okButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            buttonPanel.add(okButton);
        }
    }
}
