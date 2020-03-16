package ru.voothi.webapp;

import java.io.*;

public class Config {
    private static final Config INSTANCE = new Config();
    private static final File PROPS = new File("./config/resumes.properties");

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config " + PROPS);
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }
}
