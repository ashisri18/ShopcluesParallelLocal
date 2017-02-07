package com.shopclues.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import com.shopclues.library.BaseLib;
import com.shopclues.po.HomePO;

public class PlaceOrderTest2 extends BaseLib{

	HomePO homePo = null;
	
	@Test(priority=1,enabled=true)
	public void bestCategoryOffers() throws InterruptedException{
		homePo = new HomePO(driver);
		
	//	homePo.scrollToExact("Best Category Offers");
//		homePo.scrollToExact("Action Footwear");
		scroll("Lifestyle");
	}
	public void scroll(String text) throws InterruptedException{
		Dimension size = driver.manage().window().getSize();
		int startx = (int) (size.width/2);
		int starty = (int) (size.height*0.80);
		int endy = (int) (size.height*0.20);
		Thread.sleep(5000);
		try{
			String str=driver.findElement(By.xpath("//android.widget.TextView[@text='"+text+"']")).getText();
			System.out.println(str);
			
		}catch(Exception e){
			driver.swipe(startx, starty, startx, endy, 2000);
			Thread.sleep(5000);
		}
		Thread.sleep(1000);	
	}
}
