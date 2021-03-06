/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.modules.remotefs.sftp;

import java.awt.Image;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.netbeans.modules.remotefs.api.LogInfo;
import org.netbeans.modules.remotefs.api.RemoteFileSystemInfo;
import org.netbeans.modules.remotefs.sftp.client.SFTPLogInfo;
import org.netbeans.modules.remotefs.sftp.resources.Bundle;
import org.netbeans.modules.remotefs.sftp.ui.NewSFTPSiteWizardAction;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/**
 *
 * @author hlavki
 */
public class SFTPFileSystemInfo implements RemoteFileSystemInfo {

    private static final String PROP_DISPLAY_NAME = "FileSystemInfo.displayName";
    private static final String ICON_PATH = "org/netbeans/modules/remotefs/sftp/resources/globe-sextant-16x16.png";
    private static final Set<String> SUPPORTED_PROTOCOLS = new HashSet<String>(Arrays.asList(new String[]{"sftp", "scp"}));

    public String getDisplayName() {
        return NbBundle.getMessage(Bundle.class, PROP_DISPLAY_NAME);
    }

    public Image getIcon() {
        return ImageUtilities.loadImage(ICON_PATH);
    }

    public HelpCtx getHelp() {
        return null;
    }

    public void createConnection() {
        CallableSystemAction action = NewSFTPSiteWizardAction.getInstance();
        action.performAction();
    }

    public Set<String> getSupportedProtocols() {
        return SUPPORTED_PROTOCOLS;
    }

    public LogInfo createLogInfo(Properties data) {
        return new SFTPLogInfo(data);
    }
}
