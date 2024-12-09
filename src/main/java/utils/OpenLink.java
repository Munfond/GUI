package utils;

import java.io.IOException;
import java.net.URI;

import java.awt.Desktop;

public class OpenLink {
    public static void openLinkInBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI.create(url));
            } else {
                AlertUtils.showError("Error", "Desktop is not supported on this platform.");
            }
        } catch (IOException e) {
            AlertUtils.showError("Error", "Failed to open the URL: " + e.getMessage());
        }
    }
}
