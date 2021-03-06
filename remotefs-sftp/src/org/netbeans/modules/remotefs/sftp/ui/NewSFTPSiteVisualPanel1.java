/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewSFTPConnectionPanel.java
 *
 * Created on 1.9.2008, 22:59:23
 */
package org.netbeans.modules.remotefs.sftp.ui;

import org.netbeans.modules.remotefs.sftp.client.SFTPClient;
import org.netbeans.modules.remotefs.sftp.client.SFTPLogInfo;
import org.netbeans.modules.remotefs.sftp.resources.Bundle;
import org.openide.util.NbBundle;

/**
 *
 * @author hlavki
 */
public class NewSFTPSiteVisualPanel1 extends javax.swing.JPanel {

    public static final String PROP_LOG_INFO = "logInfo";
    private static final long serialVersionUID = 1L;
    private static final String PROP_NAME = "NewSFTPSiteVisualPanel1.name";
    private SFTPLogInfo logInfo;

    /** Creates new form NewSFTPConnectionPanel */
    public NewSFTPSiteVisualPanel1() {
        logInfo = new SFTPLogInfo();
        initComponents();
        hostTextField.setText(getHost());
        portSpinner.setValue(getPort());
        userNameTextField.setText(getUserName());
        keyFileTextField.setText(getKeyFile());
        rootFolderTextField.setText(getRootFolder());
        setDisplayName();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connNameLabel = new javax.swing.JLabel();
        connNameTextField = new javax.swing.JTextField();
        hostLabel = new javax.swing.JLabel();
        hostTextField = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        portSpinner = new javax.swing.JSpinner();
        userNameLabel = new javax.swing.JLabel();
        userNameTextField = new javax.swing.JTextField();
        keyFileLabel = new javax.swing.JLabel();
        keyFileTextField = new javax.swing.JTextField();
        browseKeyFileButton = new javax.swing.JButton();
        rootFolderLabel = new javax.swing.JLabel();
        rootFolderTextField = new javax.swing.JTextField();

        connNameLabel.setText(org.openide.util.NbBundle.getMessage(Bundle.class, "NewSFTPSiteVisualPanel1.connNameLabel.text")); // NOI18N

        connNameTextField.setEditable(false);

        hostLabel.setText(org.openide.util.NbBundle.getMessage(Bundle.class, "NewSFTPSiteVisualPanel1.hostLabel.text")); // NOI18N

        hostTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                hostTextFieldCaretUpdate(evt);
            }
        });

        portLabel.setText(org.openide.util.NbBundle.getMessage(Bundle.class, "NewSFTPSiteVisualPanel1.portLabel.text")); // NOI18N

        portSpinner.setValue(SFTPClient.DEFAULT_PORT);
        portSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                portSpinnerStateChanged(evt);
            }
        });

        userNameLabel.setText(org.openide.util.NbBundle.getMessage(Bundle.class, "NewSFTPSiteVisualPanel1.userNameLabel.text")); // NOI18N

        userNameTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                userNameTextFieldCaretUpdate(evt);
            }
        });

        keyFileLabel.setText(org.openide.util.NbBundle.getMessage(Bundle.class, "NewSFTPSiteVisualPanel1.keyFileLabel.text")); // NOI18N

        browseKeyFileButton.setText("...");
        browseKeyFileButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        browseKeyFileButton.setPreferredSize(new java.awt.Dimension(19, 19));

        rootFolderLabel.setText(org.openide.util.NbBundle.getMessage(Bundle.class, "NewSFTPSiteVisualPanel1.rootFolderLabel.text")); // NOI18N

        rootFolderTextField.setToolTipText(org.openide.util.NbBundle.getMessage(Bundle.class, "NewSFTPConnectionVisualPanel1.rootFolderTestLabel.toolTipText")); // NOI18N
        rootFolderTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                rootFolderTextFieldCaretUpdate(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, rootFolderLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, keyFileLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, userNameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, portLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, hostLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, connNameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, connNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, hostTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, portSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(userNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(keyFileTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(browseKeyFileButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(rootFolderTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(connNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(connNameLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(hostLabel)
                    .add(hostTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(portLabel)
                    .add(portSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(userNameLabel)
                    .add(userNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(keyFileLabel)
                    .add(browseKeyFileButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(keyFileTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rootFolderLabel)
                    .add(rootFolderTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hostTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_hostTextFieldCaretUpdate
        setHost(hostTextField.getText());
        setDisplayName();
    }//GEN-LAST:event_hostTextFieldCaretUpdate

    private void portSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_portSpinnerStateChanged
        setPort((Integer) portSpinner.getValue());
        setDisplayName();
    }//GEN-LAST:event_portSpinnerStateChanged

    private void userNameTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_userNameTextFieldCaretUpdate
        setUserName(userNameTextField.getText());
        setDisplayName();
    }//GEN-LAST:event_userNameTextFieldCaretUpdate

    private void rootFolderTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_rootFolderTextFieldCaretUpdate
        setRootFolder(rootFolderTextField.getText());
        setDisplayName();
}//GEN-LAST:event_rootFolderTextFieldCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseKeyFileButton;
    private javax.swing.JLabel connNameLabel;
    private javax.swing.JTextField connNameTextField;
    private javax.swing.JLabel hostLabel;
    private javax.swing.JTextField hostTextField;
    private javax.swing.JLabel keyFileLabel;
    private javax.swing.JTextField keyFileTextField;
    private javax.swing.JLabel portLabel;
    private javax.swing.JSpinner portSpinner;
    private javax.swing.JLabel rootFolderLabel;
    private javax.swing.JTextField rootFolderTextField;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables

    protected String getHost() {
        return logInfo.getHost();
    }

    protected void setHost(String host) {
        logInfo.setHost(host);
    }

    protected Integer getPort() {
        return logInfo.getPort();
    }

    protected void setPort(Integer port) {
        logInfo.setPort(port);
    }

    protected String getUserName() {
        return logInfo.getUser();
    }

    protected void setUserName(String userName) {
        logInfo.setUser(userName);
    }

    protected String getKeyFile() {
        return logInfo.getKeyFile();
    }

    protected void setKeyFile(String keyFile) {
        logInfo.setKeyFile(keyFile);
    }

    protected String getRootFolder() {
        return logInfo.getRootFolder();
    }

    protected void setRootFolder(String rootFolder) {
        logInfo.setRootFolder(rootFolder);
    }

    SFTPLogInfo getLogInfo() {
        return logInfo;
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(Bundle.class, PROP_NAME);
    }

    private void setDisplayName() {
        connNameTextField.setText(logInfo.getDisplayName());
    }
}
