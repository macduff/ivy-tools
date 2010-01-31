package com.nurflugel.ivytracker;

import com.nurflugel.ivybrowser.domain.IvyPackage;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.event.*;
import java.util.Map;

import static com.nurflugel.common.ui.Util.centerApp;

public class FindUsingProjectsDialog extends JDialog
{
  /** Use serialVersionUID for interoperability. */
  private static final long serialVersionUID = -1713437647264279958L;
  private JPanel            contentPane;
  private JButton           buttonOK;
  private JTree             projectsTree;

  public FindUsingProjectsDialog(IvyPackage ivyFile, Map<String, IvyPackage> ivyFilesMap)
  {
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);
    addListeners();
    populateTree(ivyFile, ivyFilesMap);
    pack();
    setSize(600, 600);
    centerApp(this);
  }

  private void addListeners()
  {
    buttonOK.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          onOK();
        }
      });

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter()
      {
        @Override
        public void windowClosing(WindowEvent e)
        {
          onCancel();
        }
      });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(new ActionListener()
      {
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

  private void populateTree(IvyPackage ivyFile, Map<String, IvyPackage> ivyFilesMap)
  {
    TreeModel treeModel = new FindUsingProjectsTreeModel(ivyFile, ivyFilesMap);

    projectsTree.setModel(treeModel);
  }
}