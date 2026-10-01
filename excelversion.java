package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddProductToCartExcel {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		

		ChromeDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.saucedemo.com/");
		
		//login
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		FileInputStream fis1=new FileInputStream("./src/test/resources/TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
			Sheet sh = wb.getSheet("Products");
			Row r = sh.getRow(4);
		String prodname = r.getCell(3).getStringCellValue();
		System.out.println(prodname);
		

		//addtocart
		driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']")).click();
		driver.findElement(By.id("add-to-cart")).click();
		driver.findElement(By.className("shopping_cart_link")).click();
		
		//validate
		String cartitem = driver.findElement(By.className("inventory_item_name")).getText();
		if(cartitem.equals(prodname))
		{
			System.out.println("pass");
		}
			
		else
		{
			System.out.println("fail");
		}
	
	Thread.sleep(2000);
	
	//lagout
	driver.findElement(By.id("react-burger-menu-btn")).click();
	driver.findElement(By.id("logout_sidebar_link")).click();
	driver.quit();
		}
}
