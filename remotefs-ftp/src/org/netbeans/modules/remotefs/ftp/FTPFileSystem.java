/* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
/*
/* Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
/*
/* The contents of this file are subject to the terms of either the GNU
/* General Public License Version 2 only ("GPL") or the Common
/* Development and Distribution License("CDDL") (collectively, the
/* "License"). You may not use this file except in compliance with the
/* License. You can obtain a copy of the License at
/* http://www.netbeans.org/cddl-gplv2.html
/* or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
/* specific language governing permissions and limitations under the
/* License.  When distributing the software, include this License Header
/* Notice in each file and include the License file at
/* nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
/* particular file as subject to the "Classpath" exception as provided
/* by Sun in the GPL Version 2 section of the License file that
/* accompanied this code. If applicable, add the following below the
/* License Header, with the fields enclosed by brackets [] replaced by
/* your own identifying information:
/* "Portions Copyrighted [year] [name of copyright owner]"
/*
/* Contributor(s):
 *
 * The Original Software is RemoteFS. The Initial Developer of the Original
/* Software is Libor Martinek. Portions created by Libor Martinek are
 * Copyright (C) 2000. All Rights Reserved.
/*
/* If you wish your version of this file to be governed by only the CDDL
/* or only the GPL Version 2, indicate your decision by adding
/* "[Contributor] elects to include this software in this distribution
/* under the [CDDL or GPL Version 2] license." If you do not indicate a
/* single choice of license, a recipient has the option to distribute
/* your version of this file under either the CDDL, the GPL Version 2 or
/* to extend the choice of license to its licensees as provided above.
/* However, if you add GPL Version 2 code and therefore, elected the GPL
/* Version 2 license, then the option applies only if the new code is
/* made subject to such option by the copyright holder.
 *
 * Contributor(s): Libor Martinek.
 */
package org.netbeans.modules.remotefs.ftp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.netbeans.modules.remotefs.api.LogInfo;
import org.netbeans.modules.remotefs.api.RemoteClient;
import org.netbeans.modules.remotefs.api.RemoteFileSystem;
import org.netbeans.modules.remotefs.ftp.client.FTPClient;
import org.netbeans.modules.remotefs.ftp.client.FTPLogInfo;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/** FTP FIleSystem class
 * @author Libor Martinek
 * @version 1.0
 */
public class FTPFileSystem extends RemoteFileSystem implements FTPClient.Reconnect {

    static final long serialVersionUID = -981665601872580022L;
    private static final boolean DEBUG = true;
    /** Name of temporary directoty (if user doesn't entry own one) */
    private static final String FTPWORK;
    /** Whether user already entered cache directory. */
    private boolean enteredcachedir = false;
    /** Global FTP FileSystem settings */
    private FTPSettings ftpsettings = FTPSettings.getDefault();
    static final String CACHE_FOLDER_NAME = "ftpcache";


    static {
        /* BUGFIX for issue #123552
         * We need a default cache dir. 
         * The default is "ftpcache" in the filesystem's root. Must be created if it doesn't exist.
         */        
        FileObject fo = FileUtil.getConfigFile(CACHE_FOLDER_NAME);
        if (fo == null) {
            try {
                fo = FileUtil.getConfigRoot().createFolder(CACHE_FOLDER_NAME);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FTPWORK = fo.getName();
    }

    /** 
     * Constructor.
     */
    public FTPFileSystem() {
        this(new FTPLogInfo());
    }

    /**
     * Constructor.
     * @param info
     */
    public FTPFileSystem(FTPLogInfo logInfo) {
        super(logInfo);
        setRefreshTime(getFTPSettings().getRefreshTime());
        startDir = logInfo.getRootFolder();
        getFTPSettings().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent event) {
                ftpSettingsChanged(event);
            }
        });
    }

    /** Called when FTPSettings changed
     * @param event 
     */
    protected void ftpSettingsChanged(PropertyChangeEvent event) {
        // bugfix #18714, according to the general contract of PropertyChangeEvent, 
        // property name may be null if multiple properties have been changed.
        if (event.getPropertyName() == null) {
            return;
        }
        if (event.getPropertyName().equals(FTPSettings.PROP_PASSIVE_MODE)) {
            if (client != null) {
                ((FTPClient) client).setPassiveMode(((Boolean) (event.getNewValue())).booleanValue());
            }
        }
        if (event.getPropertyName().equals(FTPSettings.PROP_REFRESH_TIME)) {
            setRefreshTime(((Integer) (event.getNewValue())).intValue());
        }
    }

    /** Get FTPSettings object
     * @return 
     */
    private FTPSettings getFTPSettings() {
        if (ftpsettings == null) {
            System.out.println("FTPSETTTNGS NULL");
            ftpsettings = FTPSettings.getDefault();
        }
        return ftpsettings;
    }

    /**
     * @return
     */
    private String computeSystemName() {
        //System.out.println("FTPFileSystem.prepareSystemName");
        return logInfo.getDisplayName();// + ((startdir != null && startdir.startsWith("/")) ? "" : "/") + startdir;
    }

    //****************************************************************************
    /** Set cache directory
     * @param r 
     * @throws java.beans.PropertyVetoException 
     * @exception IOException
     */
    public void setCache(FileObject cacheDir) throws PropertyVetoException, IOException {
//        if (cacheDir == null) {
//            throw new IOException("Cache root directory can't be null");
//        }
//        if (!cacheDir.exists()) {
//            if (!cacheDir.mkdirs()) {
//                throw new IOException("Cache root directory can't be created");
//            }
//        } else if (!cacheDir.isDirectory()) {
//            throw new IOException("Cache root is not director");
//        }
//        if (!cacheDir.canWrite() || !cacheDir.canRead()) {
//            throw new IOException("Can't read from or write to cache directory");
//        }
//        cacheDir = cacheDir;
//        enteredcachedir = true;
//        firePropertyChange("cache", null, cacheDir); // NOI18N
    }

    /** Get server name.
     * @return Value of property server.
     */
    public String getServer() {
        return ((FTPLogInfo) logInfo).getHost();
    }

    /** Set server name.
     * @param server New value of property server.
     * @throws PropertyVetoException
     */
    public void setServer(String server) throws java.beans.PropertyVetoException {
        ((FTPLogInfo) logInfo).setHost(server);
        propChanged();
    }

    /** Get the number of port.
     * @return Value of property port.
     */
    public int getPort() {
        return ((FTPLogInfo) logInfo).getPort();
    }

    /** Set port number.
     * @param port New value of property port.
     * @throws PropertyVetoException
     */
    public void setPort(int port) throws java.beans.PropertyVetoException {
        ((FTPLogInfo) logInfo).setPort(port);
        propChanged();
    }

    /** Get user name.
     * @return Value of property username.
     */
    public String getUsername() {
        return ((FTPLogInfo) logInfo).getUser();
    }

    /** Set user name.
     * @param username New value of property username.
     * @throws java.beans.PropertyVetoException 
     */
    public void setUsername(String username) throws PropertyVetoException {
        ((FTPLogInfo) logInfo).setUser(username);
        propChanged();
    }

    /** Get password.
     * @return Value of property password.
     */
    public String getPassword() {
        return ((FTPLogInfo) logInfo).getPassword();
    }

    /** Set password.
     * @param password
     * @throws java.beans.PropertyVetoException 
     */
    public void setPassword(String password) throws PropertyVetoException {
        ((FTPLogInfo) logInfo).setPassword(password);
        propChanged();
    }

    /** Get starting directory.
     * @return Value of property startdir.
     */
    public String getStartDir() {
        return startDir;
    }

    /** Set starting directory.
     * @param startdir New value of property startdir.
     */
    public void setStartDir(String startDir) {
        String newStartDir = startDir;
        if (startDir == null || startDir.equals("/") || startDir.equals("")) {
            newStartDir = "/";
        } else {
            if (!startDir.startsWith("/") || !startDir.startsWith(".")) {
                newStartDir = "/" + startDir;
            }
            if (newStartDir.endsWith("/")) {
                newStartDir = newStartDir.substring(0, newStartDir.length() - 1);
            }
        }
        this.startDir = newStartDir;
        ((FTPLogInfo) logInfo).setRootFolder(newStartDir);
        removeClient();
    }

    /** Called when some parameter was changed. If connection is established, it must be reconnected. */
    private void propChanged() throws PropertyVetoException {
        removeClient();
        if (isConnected()) {
            connectOnBackground(true);
        /*        post(new java.lang.Runnable() {
        public void run() {
        setConnected(true);  
        getRoot().refresh();
        }
        }
        );
         */
        }
    }

    /** Creates FTPClient and sets its parameters
     * @throws java.io.IOException 
     */
    public RemoteClient createClient(LogInfo loginfo, FileObject cache) throws IOException {
        FTPClient lClient = new FTPClient((FTPLogInfo) loginfo);
        lClient.setReconnect(this);
        lClient.setPassiveMode(getFTPSettings().isPassiveMode());
        return lClient;
    }

    /** Human presentable name 
     * @return
     */
    public String getDisplayName() {
        return computeSystemName();
    }

    /** Test whether filesystem is ready to write. If no, throws exception
     * @throws java.io.IOException 
     */
    protected void isReadyToModify() throws IOException {
        if (client == null || rootFile == null) {
            throw new IOException("Connection to server " + getServer() + " isn't established");
        }
        if (!isConnected() && !isOfflineChanges()) {
            throw new IOException("Modification in offline mode are not allowed");
        }
    }

    /** Test whether filesystem is ready to read. If no, throws exception
     * @throws java.io.IOException 
     */
    protected void isReadyToRead() throws IOException {
        if (client == null || rootFile == null) {
            throw new IOException("Connection to server " + getServer() + " isn't established");
        }
    }

    /** Test whether filesystem is ready. */
    protected boolean isReady() {
        if (client == null || rootFile == null) {
            return false;
        } else {
            return true;
        }
    }

    protected int disconnectDialog(String server) {
        return FTPDialogs.disconnect(server);
    }

    protected boolean connectDialog(String server) {
        return FTPDialogs.connect(server);
    }

    protected void startdirNotFound(String startdir, String server) {
        FTPDialogs.startdirNotFound(startdir, server);
    }

    protected void errorConnect(String error) {
        FTPDialogs.errorConnect(error);
    }

    public void notifyIncorrectPassword() {
        FTPDialogs.incorrectPassword(getServer());
    }

    public boolean notifyIncorrectCache(java.io.File newcache) {
        return FTPDialogs.incorrectCache(getCache().getPath(), newcache.getPath(), getServer());
    }

    public boolean notifyReconnect(String mess) {
        Object obj = DialogDisplayer.getDefault().notify(new NotifyDescriptor("Connection to server " + getServer() + " lost: " + mess + "\nReconnect?",
                "Question", NotifyDescriptor.YES_NO_OPTION, NotifyDescriptor.QUESTION_MESSAGE, null, null));
        if (obj == NotifyDescriptor.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public int notifyWhichFile(String path, Date file1, long size1, Date file2, long size2) {
        int which = file1.before(file2) ? 0 : 1;
        if (!getFTPSettings().isAskWhichFile()) {
            return which;
        }
        Object ops[] = new String[2];
        ops[0] = "From Cache";
        ops[1] = "From FTPServer";
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
        javax.swing.JPanel textpanel = new javax.swing.JPanel(new java.awt.GridLayout(0, 1));
        textpanel.add(new javax.swing.JLabel("Both files in FTP server and in cache exist."));
        textpanel.add(new javax.swing.JLabel(computeSystemName().substring(0, computeSystemName().length() - 1) + path + ", size " + size1 + " bytes, last modified " + file1.toString()));
        textpanel.add(new javax.swing.JLabel(getCache().getPath() + path.replace('/', File.separatorChar) + ", size " + size2 + " bytes, last modified " + file2.toString()));
        textpanel.add(new javax.swing.JLabel("File in " + (which == 0 ? "cache" : "FTP server") + " seems to be newer. Which one do you want to use?"));
        textpanel.add(new javax.swing.JLabel("Attention: second one will be deleted."));
        panel.add(textpanel, java.awt.BorderLayout.NORTH);
        javax.swing.JCheckBox chbox = new javax.swing.JCheckBox("Don't ask again. Always use newer file");
        chbox.setSelected(false);
        panel.add(chbox);
        Object obj = DialogDisplayer.getDefault().notify(new NotifyDescriptor(panel,
                "Question", NotifyDescriptor.YES_NO_OPTION, NotifyDescriptor.QUESTION_MESSAGE, ops, ops[which]));
        if (chbox.isSelected()) {
            getFTPSettings().setAskWhichFile(false);
        }
        if (obj == ops[0]) {
            return 0;
        } else {
            return 1;
        }
    }

    public int notifyBothFilesChanged(String path, Date file1, long size1, Date file2, long size2) {
        Object ops[] = new String[2];
        ops[0] = "From Cache";
        ops[1] = "From FTPServer";
        int which = file1.before(file2) ? 0 : 1;
        //TODO: better message (branch, merge ...)
        Object obj = DialogDisplayer.getDefault().notify(new NotifyDescriptor(
                "Both files in FTP server and in cache were modified. It means that two diffrent version of this file exist.\n" +
                computeSystemName().substring(0, computeSystemName().length() - 1) + path + ", size " + size1 + " bytes, last modified " + file1.toString() + "\n" +
                getCache().getPath() + path.replace('/', File.separatorChar) + ", size " + size2 + " bytes, last modified " + file2.toString() + "\n" +
                "File in " + (which == 0 ? "cache" : "FTP server") + " seems to be newer. Which one do you want to use?\n" +
                "Attention: second one will be deleted!",
                "Files changed", NotifyDescriptor.YES_NO_OPTION, NotifyDescriptor.WARNING_MESSAGE, ops, ops[which]));
        if (obj == ops[0]) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isRefreshServer() {
        return getFTPSettings().isRefreshServer();
    }

    public boolean isScanCache() {
        return getFTPSettings().isScanCache();
    }

    public boolean isAlwaysRefresh() {
        return getFTPSettings().isAlwaysRefresh();
    }

    public void setAlwaysRefresh(boolean alwaysRefresh) {
        getFTPSettings().setAlwaysRefresh(alwaysRefresh);
    }

    public boolean isDownloadServerChangedFile() {
        return getFTPSettings().isDownloadServerChangedFile();
    }

    public boolean isOfflineChanges() {
        return getFTPSettings().isOfflineChanges();
    }

    public boolean notifyServerChanged(String path, Date file1, long size1, Date file2, long size2) {
        if (!getFTPSettings().isAskServerChangedFile()) {
            return true;
        } // I agree
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
        javax.swing.JPanel textpanel = new javax.swing.JPanel(new java.awt.GridLayout(0, 1));
        textpanel.add(new javax.swing.JLabel("I detected that the file in FTP server has been changed."));
        textpanel.add(new javax.swing.JLabel(computeSystemName().substring(0, computeSystemName().length() - 1) + path + ", size " + size1 + " bytes, last modified " + file1.toString()));
        textpanel.add(new javax.swing.JLabel(getCache().getPath().replace('/', File.separatorChar) + path + ", size " + size2 + " bytes, last modified " + file2.toString()));
        textpanel.add(new javax.swing.JLabel("I will use this new file from server and delete the file in cache. Do you agree?"));
        textpanel.add(new javax.swing.JLabel("If you say No, the file from cache will be upload to server over changed one."));
        panel.add(textpanel, java.awt.BorderLayout.NORTH);
        javax.swing.JCheckBox chbox = new javax.swing.JCheckBox("Don't ask again. Always use new file from server");
        chbox.setSelected(false);
        panel.add(chbox);
        Object obj = DialogDisplayer.getDefault().notify(new NotifyDescriptor(panel,
                "Question", NotifyDescriptor.YES_NO_OPTION, NotifyDescriptor.QUESTION_MESSAGE, null, NotifyDescriptor.YES_OPTION));
        if (chbox.isSelected()) {
            getFTPSettings().setAskServerChangedFile(false);
        }
        if (obj == NotifyDescriptor.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public boolean notifyCacheExtDelete(String path, boolean isDir) {
        if (!getFTPSettings().isAskCacheExternalDelete()) {
            return getFTPSettings().isCacheExternalDelete();
        }
        Object ops[] = new String[4];
        ops[0] = "Yes";
        ops[1] = "No";
        ops[2] = "Yes for All";
        ops[3] = "No for All";
        Object obj = DialogDisplayer.getDefault().notify(new NotifyDescriptor(
                (isDir ? "The directory " + path + " in cache was delete externally.\nDo you want to the delete directory and all the subdirectories also from server?\n" : "The file " + path + " in cache was delete externaly.\nDo you want to delete the file also from server?\n"),
                "External deletion", NotifyDescriptor.YES_NO_OPTION, NotifyDescriptor.QUESTION_MESSAGE, ops, ops[1]));
        if (obj == ops[2]) {
            getFTPSettings().setAskCacheExternalDelete(false);
            getFTPSettings().setCacheExternalDelete(true);
        }
        if (obj == ops[3]) {
            getFTPSettings().setAskCacheExternalDelete(false);
            getFTPSettings().setCacheExternalDelete(false);
        }
        if (obj == ops[0] || obj == ops[2]) {
            return true;
        } else {
            return false;
        }
    }

    public boolean notifyServerExtDelete(String path, boolean isDir) {
        if (!getFTPSettings().isAskServerExternalDelete()) {
            return getFTPSettings().isServerExternalDelete();
        }
        Object ops[] = new String[4];
        ops[0] = "Yes";
        ops[1] = "No";
        ops[2] = "Yes for All";
        ops[3] = "No for All";
        Object obj = DialogDisplayer.getDefault().notify(new NotifyDescriptor(
                (isDir ? "The directory " + path + " on server was delete externally.\nDo you want to delete the directory and all the subdirectories also from cache?\n" : "The file " + path + " on server was delete externally.\nDo you want to delete the file also from cache?\n"),
                "External deletion", NotifyDescriptor.YES_NO_OPTION, NotifyDescriptor.QUESTION_MESSAGE, ops, ops[1]));
        if (obj == ops[2]) {
            getFTPSettings().setAskServerExternalDelete(false);
            getFTPSettings().setServerExternalDelete(true);
        }
        if (obj == ops[3]) {
            getFTPSettings().setAskServerExternalDelete(false);
            getFTPSettings().setServerExternalDelete(false);
        }
        if (obj == ops[0] || obj == ops[2]) {
            return true;
        } else {
            return false;
        }
    }

    public void fileChanged(String path) {
        FileObject fo = findResource(path);
        if (fo != null) {
            fo.refresh();
        }
    }

    public void notifyException(Exception e) {
        Exceptions.printStackTrace(e);
    }
} 
