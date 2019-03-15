package Selenium.WebDriver;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class AppTest {
	WebDriver driver;

	@Before
	public void InitTestCase() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32(1)\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.get("http://maiden.vn/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		// close pop-up
		WebElement Popup = this.driver.findElement(By.cssSelector("a.close-tannm-modal b"));
		Popup.click();
		Thread.sleep(2000);
	}

	@After
	public void EndTestCase() {
		this.driver.quit();
	}

	@Test
	public void login_empty()  {
		// click dang nhap
		// de trong cac gia tri
		// Given: user dang o frm login
		WebElement Login = this.driver.findElement(By.cssSelector("div.header-user"));
		Login.click();
		// When
		WebElement btnDangnhap = this.driver.findElement(By.cssSelector("button.btn-signin"));
		btnDangnhap.click();
		String tooltip = this.driver.findElement(By.cssSelector("input.error")).getAttribute("data-original-title");
		// Then
		System.out.println(tooltip);
		Assert.assertEquals(tooltip, "Tên đăng nhâp/Email không thể để trống.");

		// //nhap user ko ton tai
		// WebElement txtusername = this.driver.findElement(By.)
		//

	}
	public void login_user_notexit () {
		
	}
}
