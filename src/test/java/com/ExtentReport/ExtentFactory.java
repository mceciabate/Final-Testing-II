package com.ExtentReport;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {

    public static ExtentReports getInstance() {
        ExtentReports extentReports = new ExtentReports();
        extentReports.setSystemInfo("Examen Final Testing II", "María Cecilia Abate");
        extentReports.setSystemInfo("Selenium version", "4.0");
        extentReports.setSystemInfo("SO", "Windows 10");
        return extentReports;
    }
}
