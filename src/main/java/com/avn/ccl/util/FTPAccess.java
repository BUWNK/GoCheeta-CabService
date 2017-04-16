/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author : Roshen Dilshan
 * @Document : FTPClient
 * @Created on : Oct 7, 2015, 4:58:40 PM
 */
public class FTPAccess {

    public boolean sendFile(File file, String host, String username, String password, String port, String remoteLocation, String todayDirectory) throws Exception {
        boolean status = false;
        JSch jsch;
        Session session;
        Channel channel;
        ChannelSftp channelSftp = null;
        InputStream inputStream = null;
        try {
            jsch = new JSch();
            session = jsch.getSession(username, host, Integer.valueOf(port));
            session.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            session.setConfig(properties);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(remoteLocation);
            boolean isDirectoryExists = false;
            for (Object object : channelSftp.ls(remoteLocation)) {
                if (object.toString().contains(todayDirectory)) {
                    isDirectoryExists = true;
                    break;
                }
            }
            if (!isDirectoryExists) {
                channelSftp.mkdir(todayDirectory);
            }
            channelSftp.cd(todayDirectory);
            inputStream = new FileInputStream(file);
            channelSftp.put(inputStream, file.getName());
            channelSftp.disconnect();
            channel.disconnect();
            session.disconnect();
            status = true;
        } catch (IOException | NumberFormatException ioe) {
            throw ioe;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (channelSftp != null) {
                try {
                    channelSftp.exit();
                } catch (Exception e) {
                }
            }
        }
        return status;
    }

}
