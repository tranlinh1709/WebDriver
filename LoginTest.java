package Selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class LoginTest {
	WebDriver driver;

	@Before
	public void InitTestCase() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32(1)\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.get("http://testmaster.vn/admin");
		driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void LoginWrong() {
		WebElement InputEmail = this.driver.findElement(By.cssSelector("input[type=email]"));
		InputEmail.sendKeys("linh@gmail.com");
		WebElement InputPassword = this.driver.findElement(By.cssSelector("input[type=password]"));
		InputPassword.sendKeys("12356");
		WebElement btnLogin = this.driver.findElement(By.cssSelector("button.btn-login"));
		btnLogin.click();
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=popover]")));
		String msg = this.driver.findElement(By.cssSelector("div.body-message")).getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Tên đăng nhập hoặc Mật khẩu không đúng.\nChú ý: Mật khẩu có độ dài >6 ký tự và chỉ chứa chữa hoa, chữ thường và số");

	}
	public void LoginOK() {
		
	}
}
