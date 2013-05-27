/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.view.gui.chooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ale.controller.Main;
import ale.model.skin.SkinPreviewVO;
import ale.view.gui.GUIConstants;
import ale.view.gui.util.CustomListCellRenderer;
import ale.view.gui.util.GUIStrings;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.view.gui.chooser <br/>
 * Class  : QuickChooser <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>QuickChooser</code> is the quick apply dialog which the program shows first.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 24.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class QuickChooser extends JFrame implements WindowListener {

    private static final long serialVersionUID = 1L;

    private String strLblBaseInfoName = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_INFO_NAME);
    private String strLblBaseInfoAuthor = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_INFO_AUTHOR);
    private String strLblBaseInfoWebsite = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_INFO_WEB);

    private int selectedIndex = 0;
    private DefaultListModel<String> listModel;
    private List<SkinPreviewVO> previewList;

    private JLabel lblPreviewLabel;
    private JLabel lblBaseInfoLabel;
    private JButton btnApplyButton;
    private JButton btnEditButton;
    private JButton btnDeleteButton;
    private JList<String> list;
    private JPanel listPanel;
    private JButton btnRefreshListButton;
    private JPanel previewPanel;
    private JLabel lblWarningLabel;
    private JButton btnNewButton;
    private QuickChooserMenuBar menubar;

    /**
     * Creates a new Quickchooser window.
     * 
     */
    public QuickChooser() {
        this.previewList = new LinkedList<>();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                QuickChooser.this.setTitle(GUIConstants.PROGRAM_TITLE);
                QuickChooser.this.setIconImage(GUIConstants.PROGRAM_ICON);
                QuickChooser.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                QuickChooser.this.setMinimumSize(GUIConstants.MIN_QUICKCHOOSER_DIM);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) ((screenSize.getWidth() * (100 - GUIConstants.CHOOSER_SIDEGAP_PERCENT)) / 100f);
                int height = (int) ((screenSize.getHeight() * (100 - GUIConstants.CHOOSER_SIDEGAP_PERCENT)) / 100f);
                QuickChooser.this.setSize(width, height);
                QuickChooser.this.setLocationRelativeTo(null);
                QuickChooser.this.getContentPane().setLayout(new BorderLayout());
                QuickChooser.this.addWindowListener(QuickChooser.this);
                QuickChooser.this.menubar = new QuickChooserMenuBar();
                QuickChooser.this.setJMenuBar(QuickChooser.this.menubar);

                create();
                updateList();
                setVisible(true);
                updateLocale();
            }
        });
    }

    /**
     * Updates the Strings of the chooser window.
     * 
     */
    public void updateLocale() {
        String tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_LISTTITLE);
        this.listPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3),
                BorderFactory.createTitledBorder(tmp)));
        tmp = GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_PREVIEWTITLE);
        this.previewPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0),
                BorderFactory.createTitledBorder(tmp)));
        this.lblPreviewLabel.setBorder(BorderFactory.createTitledBorder(""));

        this.btnRefreshListButton.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_REFRESHLIST));
        this.lblWarningLabel.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_WARNINGLABEL));
        this.btnApplyButton.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_APPLYBTN));
        this.btnEditButton.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_EDITBTN));
        this.btnDeleteButton.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_DELETEBTN));
        this.btnNewButton.setText(GUIStrings.keyToLocatedString(GUIStrings.KEY_QUICKCHOOSER_NEWBTN));

        this.menubar.updateLocale();
    }

    /**
     * Updates the entries of the skinlist.
     * 
     */
    public void updateList() {
        if (this.listModel == null) {
            throw new IllegalStateException();
        }

        this.listModel.clear();
        this.previewList.clear();
        this.repaint();

        List<SkinPreviewVO> tmp = Main.getAvailableSkins();
        for (SkinPreviewVO element : tmp) {
            if (element != null) {
                this.listModel.addElement(element.getName());
                this.previewList.add(element);
            }
        }

        if (QuickChooser.this.previewList.size() == 0) {
            QuickChooser.this.btnApplyButton.setEnabled(false);
            QuickChooser.this.btnDeleteButton.setEnabled(false);
            QuickChooser.this.btnEditButton.setEnabled(false);

        } else {
            QuickChooser.this.btnApplyButton.setEnabled(true);
            QuickChooser.this.btnDeleteButton.setEnabled(true);
            QuickChooser.this.btnEditButton.setEnabled(true);

            QuickChooser.this.list.setSelectedIndex(QuickChooser.this.selectedIndex);
        }
    }

    private SkinPreviewVO getSelectedListEntry() {
        SkinPreviewVO ret = null;

        if (this.previewList.size() > this.selectedIndex) {
            ret = this.previewList.get(this.selectedIndex);
        }

        return ret;
    }

    private void create() {
        JPanel basePanel = new JPanel();
        basePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        basePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        basePanel.setLayout(new BorderLayout());
        getContentPane().add(basePanel, BorderLayout.CENTER);

        {
            this.listPanel = new JPanel();
            this.listPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.listPanel.setLayout(new BorderLayout());
            basePanel.add(this.listPanel, BorderLayout.WEST);

            this.listModel = new DefaultListModel<>();
            CustomListCellRenderer renderer = new CustomListCellRenderer();
            renderer.setListCellBackground(GUIConstants.DEFAULT_BACKGROUND, Color.WHITE);

            this.list = new JList<String>(this.listModel);
            this.list.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.list.setCellRenderer(renderer);
            this.list.setBorder(BorderFactory.createEmptyBorder(3, 2, 3, 2));
            this.list.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent evt) {
                    if (evt.getValueIsAdjusting()) {
                        return;
                    }

                    if (QuickChooser.this.list.getSelectedIndex() >= 0) {
                        QuickChooser.this.selectedIndex = QuickChooser.this.list.getSelectedIndex();

                        if ((QuickChooser.this.selectedIndex < QuickChooser.this.previewList.size())) {
                            SkinPreviewVO skinprwTmp = QuickChooser.this.previewList.get(QuickChooser.this.selectedIndex);

                            try {
                                BufferedImage icon = ImageIO.read(skinprwTmp.getImage().toFile());
                                QuickChooser.this.lblPreviewLabel.setIcon(new ImageIcon(icon));
                            } catch (IOException e) {
                                QuickChooser.this.btnRefreshListButton.doClick();
                            }

                            QuickChooser.this.lblBaseInfoLabel.setForeground(Color.BLACK);
                            QuickChooser.this.lblBaseInfoLabel.setText(QuickChooser.this.strLblBaseInfoName + " " + skinprwTmp.getName()
                                    + "      " + QuickChooser.this.strLblBaseInfoAuthor + " "
                                    + skinprwTmp.getAuthor() + "      " + QuickChooser.this.strLblBaseInfoWebsite + " "
                                    + skinprwTmp.getWebsite() + "");
                        }
                        if (QuickChooser.this.previewList.size() <= 0) {
                            QuickChooser.this.lblPreviewLabel.setIcon(null);
                            QuickChooser.this.lblBaseInfoLabel.setVisible(false);
                        } else {
                            QuickChooser.this.lblBaseInfoLabel.setVisible(true);
                        }
                    }
                }
            });
            this.list.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        int idx = QuickChooser.this.list.locationToIndex(e.getPoint());
                        QuickChooser.this.list.setSelectedIndex(idx);

                        SkinPreviewVO skinprwTmp = QuickChooser.this.previewList.get(idx);
                        ListPopUpMenu menu = new ListPopUpMenu(skinprwTmp);
                        menu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(this.list);
            scrollPane.setBorder(null);
            scrollPane.setPreferredSize(new Dimension(150, this.list.getPreferredSize().height));
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.listPanel.add(scrollPane);

            JPanel listBottomPanel = new JPanel();
            listBottomPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            listBottomPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            listBottomPanel.setLayout(new BoxLayout(listBottomPanel, BoxLayout.X_AXIS));
            this.listPanel.add(listBottomPanel, BorderLayout.SOUTH);

            this.btnRefreshListButton = new JButton();
            this.btnRefreshListButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.btnRefreshListButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    QuickChooser.this.btnRefreshListButton.setEnabled(false);
                    updateList();

                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                ;
                            }
                            EventQueue.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (QuickChooser.this.btnRefreshListButton != null) {
                                        QuickChooser.this.btnRefreshListButton.setEnabled(true);
                                    }
                                }
                            });
                        }
                    }.start();
                }
            });
            listBottomPanel.add(this.btnRefreshListButton);
        }

        JPanel previewOptionsPanel = new JPanel();
        previewOptionsPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        previewOptionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
        previewOptionsPanel.setLayout(new BorderLayout());
        basePanel.add(previewOptionsPanel, BorderLayout.CENTER);

        this.previewPanel = new JPanel();
        this.previewPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        this.previewPanel.setLayout(new BorderLayout());
        previewOptionsPanel.add(this.previewPanel, BorderLayout.CENTER);

        {
            JPanel previewlabelPanel = new JPanel();
            previewlabelPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            previewlabelPanel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
            previewlabelPanel.setLayout(new BorderLayout());
            this.previewPanel.add(previewlabelPanel, BorderLayout.CENTER);

            this.lblPreviewLabel = new JLabel();
            this.lblPreviewLabel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.lblPreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.lblPreviewLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.lblPreviewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (!e.isPopupTrigger()) {
                        if (!Main.showWebsite(QuickChooser.this.previewList.get(QuickChooser.this.selectedIndex).getWebsite())) {
                            QuickChooser.this.lblBaseInfoLabel.setForeground(GUIConstants.WARNING_BG);
                        }
                    }
                }
            });
            previewlabelPanel.add(this.lblPreviewLabel);
        }
        {
            JPanel previewDescPanel = new JPanel();
            previewDescPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            previewDescPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 2, 0));
            previewDescPanel.setLayout(new BoxLayout(previewDescPanel, BoxLayout.Y_AXIS));
            this.previewPanel.add(previewDescPanel, BorderLayout.SOUTH);

            this.lblBaseInfoLabel = new JLabel();
            this.lblBaseInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            this.lblBaseInfoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.lblBaseInfoLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!Main.showWebsite(QuickChooser.this.previewList.get(QuickChooser.this.selectedIndex).getWebsite())) {
                        QuickChooser.this.lblBaseInfoLabel.setForeground(GUIConstants.WARNING_BG);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        SkinPreviewVO skinprwTmp = QuickChooser.this.previewList.get(QuickChooser.this.selectedIndex);
                        PopUpMenu menu = new PopUpMenu(skinprwTmp);
                        menu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });
            previewDescPanel.add(this.lblBaseInfoLabel);

            this.lblWarningLabel = new JLabel();
            this.lblWarningLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            this.lblWarningLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            previewDescPanel.add(this.lblWarningLabel);
        }

        JPanel OptionsBasePanel = new JPanel();
        OptionsBasePanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        OptionsBasePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        OptionsBasePanel.setLayout(new BorderLayout());
        previewOptionsPanel.add(OptionsBasePanel, BorderLayout.SOUTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        optionsPanel.setLayout(new BorderLayout());
        OptionsBasePanel.add(optionsPanel);

        {
            JPanel skinoptionsPanel = new JPanel();
            skinoptionsPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            optionsPanel.add(skinoptionsPanel, BorderLayout.CENTER);

            this.btnApplyButton = new JButton();
            this.btnApplyButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.btnApplyButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    SkinPreviewVO tmp = getSelectedListEntry();

                    if (tmp != null) {
                        Main.showApplySkinDialog(tmp.getFilename());
                    }
                }
            });
            skinoptionsPanel.add(this.btnApplyButton);

            this.btnEditButton = new JButton();
            this.btnEditButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.btnEditButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {

                    SkinPreviewVO tmp = getSelectedListEntry();

                    if (tmp != null) {
                        Main.showEditor(tmp.getFilename());
                    }
                }
            });
            skinoptionsPanel.add(this.btnEditButton);

            this.btnDeleteButton = new JButton();
            this.btnDeleteButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
            this.btnDeleteButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    SkinPreviewVO tmp = getSelectedListEntry();

                    if (tmp != null) {
                        Main.showDeleteSkinDialog(tmp.getFilename());
                    }
                }
            });
            skinoptionsPanel.add(this.btnDeleteButton);
        }

        JPanel miscSkinoptionsPanel = new JPanel();
        miscSkinoptionsPanel.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        optionsPanel.add(miscSkinoptionsPanel, BorderLayout.EAST);

        this.btnNewButton = new JButton();
        this.btnNewButton.setBackground(GUIConstants.DEFAULT_BACKGROUND);
        this.btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.showNewSkinDialog();
            }
        });
        miscSkinoptionsPanel.add(this.btnNewButton);
    }

    @Override
    public void dispose() {
        this.listModel = null;
        this.previewList = null;
        this.lblPreviewLabel = null;
        this.lblBaseInfoLabel = null;
        this.btnApplyButton = null;
        this.btnEditButton = null;
        this.btnDeleteButton = null;
        this.list = null;
        this.listPanel = null;
        this.btnRefreshListButton = null;
        this.previewPanel = null;
        this.lblWarningLabel = null;
        this.btnNewButton = null;

        if (this.menubar != null) {
            this.menubar.shutdown();
        }
        this.menubar = null;

        super.dispose();
    }

    /*
     * Windowlistener
     * 
     */

    @Override
    public void windowActivated(WindowEvent e) {
        ;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        ;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Main.shutdown();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        ;
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        ;
    }

    @Override
    public void windowIconified(WindowEvent e) {
        ;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        ;
    }
}
