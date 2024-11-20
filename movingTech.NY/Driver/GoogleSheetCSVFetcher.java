package Driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GoogleSheetCSVFetcher {
	

		    public static void main(String[] args) throws InterruptedException {
		    	googleSheetCSVFeature();
}

		    	// Add URLs for each tab
	public static void googleSheetCSVFeature() throws InterruptedException {
		
	
		        Map<String, String> sheetUrls = new LinkedHashMap<>();
		        sheetUrls.put("NY_BLR_DROP", "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=0&single=true&output=csv");
		        sheetUrls.put("NY_BLR_PICK", "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=660481894&single=true&output=csv");
		        sheetUrls.put("NY_BLR_CUG_DROP", "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=593925876&single=true&output=csv");
		        sheetUrls.put("NY_BLR_CUG_PIC", "https://docs.google.com/spreadsheets/d/e/2PACX-1vQ5Aq30i4qBOSt5WGPzkEQEBSNWXxQqNNRG9RS1pmHn-XXdOs68XCjTDQK3it69Vy-DVININg3U91Jg/pub?gid=1436592205&single=true&output=csv");

		        // Iterate through the tabs
		        for (Map.Entry<String, String> entry : sheetUrls.entrySet()) {
		            String tabName = entry.getKey();
		            String url = entry.getValue();
		            System.out.println("Reading data from tab: " + tabName);
		            fetchAndPrintCSVData(url);
		        }
		    }

		    public static void fetchAndPrintCSVData(String sheetUrl) {
		        try {
		            URL url = new URL(sheetUrl);
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            connection.setRequestMethod("GET");

		            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		            String line;

		            while ((line = in.readLine()) != null) {
		            	if (line.trim().isEmpty()) continue;
		                String[] row = line.split(",");
		                if (row.length >= 3 && isNumeric(row[0]) && isNumeric(row[1])) {
		                    double latitude = Double.parseDouble(row[0].trim());
		                    double longitude = Double.parseDouble(row[1].trim());
		                    String altitude = row[2].trim();

		                    simulateLocation(latitude, longitude, altitude);
		                } else {
		                    System.out.println("Skipping invalid or incomplete data row: " + line);
		                }
		            }
		            in.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    public static boolean isNumeric(String str) {
		        return str.matches("-?\\d+(\\.\\d+)?");
		    }
		    public static void simulateLocation(double latitude, double longitude, String altitude) {
		        System.out.println("Simulating location:   at [" + latitude + ", " + longitude + "," + altitude +"]");
		    }
		

		
	}


