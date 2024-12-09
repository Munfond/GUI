package model;

import java.io.File;
import java.util.ArrayList;

public class CsvFileNameReader {
	
    public static ArrayList<String> getFileName() {
    	
    	ArrayList<String> hashtags = new ArrayList<>();
    	
        String folderPath = "src/main/resources/data";
        
        File folder = new File(folderPath);
        
        
        if (folder.exists() && folder.isDirectory()) {
            
            File[] csvFiles = folder.listFiles((dir, name) -> name.endsWith(".csv"));
           
            if (csvFiles != null) {
                for (File file : csvFiles) {
                	String fileName = file.getName();
                    String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
                    
                    hashtags.add(nameWithoutExtension);
                }
            } else {
                System.out.println("Không tìm thấy file CSV nào.");
            }
        } else {
            System.out.println("Thư mục không tồn tại hoặc không hợp lệ.");
        }
        return hashtags;
    }
}

