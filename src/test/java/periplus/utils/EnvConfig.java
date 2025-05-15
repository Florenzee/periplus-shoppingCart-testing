package periplus.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getEmail() {
        return dotenv.get("EMAIL");
    }

    public static String getPassword() {
        return dotenv.get("PASSWORD");
    }
}
