package utils;

import java.net.URL;

public class ResourcesPaths {
    public static final String pathOfLogo = getSource("/images/logo.png");
    public static final String pathOfSearchIcon = getSource("/images/searchicon.png");
    public static final String pathOfChartIcon = getSource("/images/charticon.png");
    public static final String pathOfMenuIcon = getSource("/images/menu.png");
    public static final String pathOfHomeIcon = getSource("/images/home.png");
    public static final String pathOfHomeBackground = getSource("/images/homebackground.png");
    public static final String pathOfStyleSheet = getSource("/style/styles.css");

    private static String getSource(String resourcePath) {
        URL resourceUrl = ResourcesPaths.class.getResource(resourcePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        return resourceUrl.toExternalForm(); // Trả về dạng URL phù hợp với JavaFX Image
    }
}
