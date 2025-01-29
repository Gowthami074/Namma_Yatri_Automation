package TestReport;

import java.io.File;
import java.io.IOException;

public class AppReport {

	public void generateReport() {
		new Thread(() -> {
			try {
				System.out.println("Serving Allure Report...");
				String command = "/usr/local/bin/allure serve allure-results --clean";
				Runtime.getRuntime().exec(command);
				System.out.println("Generating Allure Report...");
				String generateCommand = "/usr/local/bin/allure generate --single-file allure-results --clean";
				Process generateProcess = Runtime.getRuntime().exec(generateCommand);
				generateProcess.waitFor();  // Wait for the report generation to complete
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Failed to serve Allure report.");
			}
		}).start();
	}

	public void clearExistingJSONFiles() {
		System.out.println("Cleaning allure-results directory...");
		File resultsDir = new File("allure-results"); // Path to your allure-results directory
		if (resultsDir.exists()) {
			for (File file : resultsDir.listFiles()) {
				if (!file.isDirectory()) {
					file.delete(); // Delete files
				}
			}
		}
		System.out.println("allure-results directory cleaned.");
	}

}
