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
package org.netbeans.modules.remotefs.api;

import java.beans.PropertyChangeSupport;
import java.util.Properties;
import org.openide.nodes.Node;

/** Interface for storing login information. Usually username, pasword, etc.
 *
 * @author  Michal Hlavac
 * @version 1.0
 */
public abstract class LogInfo implements java.io.Serializable {

    public static final String PROP_NAME = "name";
    public static final String PROP_PROTOCOL = "protocol";
    public static final String PROP_INSTANCE_CLASS = "instanceClass";
    public static final String PROP_HOST = "host";
    public static final String PROP_PORT = "port";
    protected Properties data;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LogInfo() {
        data = new Properties();
    }

    protected LogInfo(Properties data) {
        this.data = data;
    }

    /**
     * Get hostname
     * @return hostname
     */
    public String getHost() {
        return data.getProperty(PROP_HOST);
    }

    /**
     * Set hostname
     * @param host hostname
     */
    public void setHost(String host) {
        setProperty(PROP_HOST, host);
    }

    /**
     * Get port nubmer
     * @return port number
     */
    public Integer getPort() {
        String value = data.getProperty(PROP_PORT);
        return value != null ? Integer.valueOf(value) : null;
    }

    /**
     * Set port number
     * @param port port nubmer
     */
    public void setPort(Integer port) {
        setProperty(PROP_PORT, port.toString());
    }

    /** Return human redable description of this LogInfo
     * @return 
     */
    public abstract String getDisplayName();

    public final String getProtocol() {
        return data.getProperty(PROP_PROTOCOL);
    }

    public abstract Node.Property[] getNodeProperties(RemoteFileSystem fs);

    public abstract RemoteFileSystem createFileSystem();

    public final Properties getProperties() {
        return data;
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public final void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public final void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public final void setProperty(String key, String value) {
        String oldValue = data.getProperty(key);
        if (value == null) {
            data.remove(key);
        } else {
            data.setProperty(key, value);
        }
        propertyChangeSupport.firePropertyChange(key, oldValue, value);
    }
}