package TestReport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import base.BaseClass;

public class AppReport {

	public void generateReport() {
		List<String> udids = new ArrayList<>();
		String commandToFind = "allure";
		String commandPath = BaseClass.findCommandPath(commandToFind);
		System.out.println(commandToFind.toUpperCase() + " Path: " + (commandPath != null ? commandPath : commandToFind + " not found"));
		new Thread(() -> {
			try {
				System.out.println("Serving Allure Report...");
				String servecommand = commandPath+" serve allure-results";
				String generatecommand = commandPath+" generate --single-file allure-results";
				Runtime.getRuntime().exec(servecommand);
				Runtime.getRuntime().exec(generatecommand);
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
