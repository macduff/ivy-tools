package com.nurflugel.common.ui;

import static com.nurflugel.common.ui.Util.centerApp;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.nurflugel.ivybrowser.AppPreferences;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
import static javax.swing.KeyStroke.getKeyStroke;

public class FindMultiplePreferencesItemsDialog extends JDialog
{
  public static final String EMPTY_STRING     = "";
  private JPanel             contentPane;
  private JButton            buttonOK;
  private JButton            buttonCancel;
  private JComboBox          comboBox;
  private JPanel             borderTitlePanel;
  private AppPreferences     appPreferences;
  private String             keyBase;
  private List<String>       locations;
  private boolean            isOK;

  public FindMultiplePreferencesItemsDialog(AppPreferences appPreferences, String borderTitle, String keyBase)
  {
    this.appPreferences = appPreferences;
    this.keyBase        = keyBase;

    // EtchedBorder border = (EtchedBorder) borderTitlePanel.getBorder();
    // TitledBorder newBorder = BorderFactory.createTitledBorder(border, borderTitle);
    //
    // borderTitlePanel.setBorder(newBorder);
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);
    addListeners();
    locations = new ArrayList<String>();

    for (int i = 0; i < 10; i++)
    {
      String value = appPreferences.getIndexedProperty(keyBase, i);

      if (value.equalsIgnoreCase(EMPTY_STRING))
      {
        break;
      }
      else
      {
        locations.add(value);
      }
    }

    String[]      locationArray = locations.toArray(new String[locations.size()]);
    ComboBoxModel model         = new DefaultComboBoxModel(locationArray);

    comboBox.setModel(model);
    pack();
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
    buttonCancel.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          onCancel();
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
      }, getKeyStroke(VK_ESCAPE, 0), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
  }

  private void onOK()
  {
    isOK = true;

    Object item     = comboBox.getSelectedItem();
    String location = (String) item;

    if (locations.contains(location))
    {
      locations.remove(location);
    }

    // put this at the top of the list so the most recent value always shows first
    locations.add(0, location);
    appPreferences.saveIndexedProperties(keyBase, locations);
    dispose();
  }

  private void onCancel()
  {
    // add your code here if necessary
    isOK = false;
    dispose();
  }

  // -------------------------- OTHER METHODS --------------------------
  public String getRepositoryLocation()
  {
    return (String) comboBox.getSelectedItem();
  }

  public boolean isOk()
  {
    return isOK;
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
    contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));

    final JPanel panel1 = new JPanel();

    panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
    contentPane.add(panel1,
                    new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));

    final Spacer spacer1 = new Spacer();

    panel1.add(spacer1,
               new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW,
                                   1, null, null, null, 0, false));

    final JPanel panel2 = new JPanel();

    panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
    panel1.add(panel2,
               new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                   GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                   GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    buttonOK = new JButton();
    buttonOK.setText("OK");
    panel2.add(buttonOK,
               new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                                   GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
                                   null, null, null, 0, false));
    buttonCancel = new JButton();
    buttonCancel.setText("Cancel");
    panel2.add(buttonCancel,
               new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                                   GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
                                   null, null, null, 0, false));
    borderTitlePanel = new JPanel();
    borderTitlePanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
    contentPane.add(borderTitlePanel,
                    new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    borderTitlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Specify repository location"));

    final JLabel label1 = new JLabel();

    label1.setHorizontalAlignment(0);
    label1.setHorizontalTextPosition(10);
    label1.setText("Pick an existing location from the dropdown, or ");
    borderTitlePanel.add(label1,
                         new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                                             GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

    final Spacer spacer2 = new Spacer();

    borderTitlePanel.add(spacer2,
                         new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1,
                                             GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));

    final JLabel label2 = new JLabel();

    label2.setHorizontalAlignment(0);
    label2.setText("enter a new location and click \"OK\"");
    borderTitlePanel.add(label2,
                         new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                                             GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    comboBox = new JComboBox();
    comboBox.setEditable(true);

    final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();

    comboBox.setModel(defaultComboBoxModel1);
    borderTitlePanel.add(comboBox,
                         new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                                             GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
  }

  /** @noinspection  ALL */
  public JComponent $$$getRootComponent$$$()
  {
    return contentPane;
  }
}
