package com.community.tsinghua.helper;


import com.parse.ParseInstallation;

/**
 * Created by LG on 2015-11-25.
 */
public class ParseUtils {

    public static void subscribeWithEmail(String email) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();

        installation.put("email", email);

        installation.saveInBackground();
    }
}
