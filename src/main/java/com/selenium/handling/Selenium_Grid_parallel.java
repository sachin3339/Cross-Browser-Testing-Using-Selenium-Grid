package com.selenium.handling;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Selenium_Grid_parallel {
	WebDriver driver;
    String nodeURL;
    
    @Parameters({"Port"})
    @BeforeMethod()
    public void setUp(String Port) throws MalformedURLException
    {           
        if(Port.equalsIgnoreCase("4546"))
        {
            nodeURL = " http://192.168.43.74:4546/wd/hub";
            System.out.println("Chrome Browser Initiated");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();            
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.WINDOWS);
            
            driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
            
            driver.get("https://www.google.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        
        else
            if(Port.equalsIgnoreCase("4545"))
            {
                nodeURL = "http://192.168.43.74:4545/wd/hub";
                System.out.println("Firefox Browser Initiated");
                
                driver = new RemoteWebDriver(new URL(nodeURL),new FirefoxOptions());   
                
                driver.get("https://www.google.com/");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        
        else
            
        if(Port.equalsIgnoreCase("4547"))
        {
            nodeURL = "http://192.168.43.74:4547/wd/hub";
            System.out.println("Internet Browser Initiated");
            DesiredCapabilities capabilities2 = DesiredCapabilities.internetExplorer();
            capabilities2.setBrowserName("internet explorer");
            capabilities2.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities2.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            capabilities2.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities2.setCapability("ignoreProtectedModeSettings", true);
            capabilities2.setCapability("nativeEvents", false);
            capabilities2.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
            capabilities2.setCapability(InternetExplorerDriver.LOG_LEVEL, "DEBUG");
             
            
            capabilities2.setPlatform(Platform.WINDOWS);
            
            driver = new RemoteWebDriver(new URL(nodeURL),capabilities2);
            
            driver.get("https://www.google.com/");
            driver.manage().window().maximize();    
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
    
    @Test
    public void GoogleSearch() throws InterruptedException
    {
    	String expectedTitle = "Google";
    	String actualTitle = "";
        try
        {
        
        actualTitle = driver.getTitle();
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
        }
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
 
    
    @AfterMethod()
    public void tearDown()
    {
            driver.quit();
            System.out.println("Browser Closed");
    }

}
