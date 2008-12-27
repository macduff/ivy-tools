package com.nurflugel.ivytracker;

import com.nurflugel.ivytracker.domain.IvyFile;

import com.nurflugel.ivybrowser.ui.BuilderMainFrame;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.tree.TreeModel;


public class ShowProjectsDialog extends JDialog
{

    /** Use serialVersionUID for interoperability. */
    private static final long serialVersionUID = -4630003093188980293L;
    private JPanel  contentPane;
    private JButton buttonOK;
    private JTree   projectsTree;

    public ShowProjectsDialog(List<IvyFile> projectIvyFiles, Map<String, IvyFile> ivyFilesMap)
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        addListeners();

        populateTree(projectIvyFiles, ivyFilesMap);
        pack();
        setSize(1000, 800);
        BuilderMainFrame.centerApp(this);
    }

    private void populateTree(List<IvyFile> projectIvyFiles, Map<String, IvyFile> ivyFilesMap)
    {
        TreeModel treeModel = new IvyProjectTreeModel(projectIvyFiles, ivyFilesMap);
        projectsTree.setModel(treeModel);
    }

    private void addListeners()
    {
        buttonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    onOK();
                }
            });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e)
                {
                    onCancel();
                }
            });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    onCancel();
                }
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK()
    {

        // add your code here
        dispose();
    }

    private void onCancel()
    {

        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args)
    {
        ShowProjectsDialog dialog = new ShowProjectsDialog(new ArrayList<IvyFile>(), new HashMap<String, IvyFile>());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    {

        // GUI initializer generated by IntelliJ IDEA GUI Designer
        // >>> IMPORTANT!! <<<
        // DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT edit this method OR call it in your code!
     *
     * @noinspection  ALL
     */
    private void $$$setupUI$$$()
    {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());

        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));

        GridBagConstraints gbc;
        gbc         = new GridBagConstraints();
        gbc.gridx   = 0;
        gbc.gridy   = 1;
        gbc.weightx = 1.0;
        gbc.fill    = GridBagConstraints.BOTH;
        contentPane.add(panel1, gbc);

        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        panel1.add(panel2, BorderLayout.EAST);
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, BorderLayout.CENTER);

        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        gbc         = new GridBagConstraints();
        gbc.gridx   = 0;
        gbc.gridy   = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill    = GridBagConstraints.BOTH;
        contentPane.add(panel3, gbc);

        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, BorderLayout.CENTER);
        projectsTree = new JTree();
        scrollPane1.setViewportView(projectsTree);
    }

    /**
     * @noinspection  ALL
     */
    public JComponent $$$getRootComponent$$$() { return contentPane; }
}
