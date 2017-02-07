package com.shopclues.library;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BaseLib3 implements SauceOnDemandSessionIdProvider{
	static public String sDirPath=System.getProperty("user.dir");
	public static String sTestDataFile = sDirPath + "\\TestData.xlsx";
	public static String sConfigFile = sDirPath + "\\Capabilities.Properties";
	public static String sAppPath = sDirPath + "\\Shopclues.apk";
	public DesiredCapabilities capabilities;
	String[] sData=null;
	public static AndroidDriver driver;
	final private String USERNAME = System.getenv("SAUCE_USERNAME");
	final private String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(USERNAME,ACCESS_KEY);

	public String sessionId;
	
	@Parameters({"deviceId","version","data"})
	@BeforeTest()
	public void setCapability(String deviceId, String version, String data) throws MalformedURLException
	{
	
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion",version );		
		capabilities.setCapability("deviceName", deviceId);
		capabilities.setCapability("deviceType","phone");
		capabilities.setCapability("app", GenericLib.getCongigValue(sConfigFile, "APP"));
		capabilities.setCapability("appiumVersion", GenericLib.getCongigValue(sConfigFile, "APPIUMVERSION"));
		capabilities.setCapability("deviceOrientation", "portrait");
		capabilities.setCapability("name","Shopclues Parallel Test Run");
		capabilities.setCapability("newCommandTimeout", 75);
//		driver = new AndroidDriver( new URL("http://127.0.0.1:"+sData[2]+"/wd/hub"), capabilities);
			
		driver = new AndroidDriver( new URL("http://" + authentication.getUsername() + ":"+ authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		sessionId = driver.getSessionId().toString();
	}

	@AfterTest
	public void tearDown(){
		driver.quit();
	}
		
	public String getSessionId() {
		return sessionId;
	}	
}
