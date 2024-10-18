package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.utils.TwfException;


public class Table extends WebPage{
	
	public WebDriver driver;


	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void verifyjob(Map<String,String>Data) throws IOException, TwfException
	{
		driver=DriverFactory.getDriver();
	String	text ="Dhoni@gmail.com";
		ArrayList<WebElement> links = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[@class='ui-grid-canvas']/div/div/div[1]"));
	        for(WebElement ele : links){
	        	
	        	String name=ele.getText();
	        System.out.println(name);
	      Assert.assertEquals("Dhoni@gmail.com",text);
	        }
	      for(int i=1;i<=100;i++)
	      {
	    
	    	  driver.findElement(By.xpath("//button[@class='ui-grid-pager-next']")).click();
	    	  
	    	  ArrayList<WebElement> link2 = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[@class='ui-grid-canvas']/div/div/div[1]"));
		        for(WebElement ele1 : link2){
		        	
		        	String name1=ele1.getText();
		        System.out.println(name1);
		     //   Assert.assertEquals("Dhoni@gmail.com",text);
		        
		        if(ele1.getText().equals("Dhoni@gmail.com"))
		        {
		        	System.out.println("Element present"); 	
		        	driver.findElement(By.xpath("//*[@class='fa fa-pencil']")).click();
		       WebElement email=driver.findElement(By.xpath("//*[@class='ui-grid-canvas']/div/div/div[1]"));
		      email.sendKeys("MsDhoni@gmail.com");
		      driver.findElement(By.xpath("//*[@class='fa fa-pencil']")).click();
		      
		       
		        	
		        }
	    	   
	      }
	      }
	   	  
               
	      }
	}
               

               
               
