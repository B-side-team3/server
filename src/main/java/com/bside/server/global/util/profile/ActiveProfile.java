package com.bside.server.global.util.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ActiveProfile {
    private static String profile;

    @Value("${spring.profiles.active}")
    private void setProfile(String profile) {
        ActiveProfile.profile = profile;
    }

    public static boolean isProd() {
        return profile.equals(Profile.PROD.getValue());
    }

    public static boolean isDev() {
        return profile.equals(Profile.DEV.getValue());
    }

    public static String get() {
        return profile;
    }
}
