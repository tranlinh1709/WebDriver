package Selenium.WebDriver;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import Selenium.WebDriver.AppTest;

public class AddToCartTest {
	/*
	 * Thực hiện các testcase sau bằng Selenium 1. Thực hiện tìm kiếm một số sản
	 * phẩm trong hệ thống maiden.vn với từ khóa "giấy" 2. Thực hiện mua một sản
	 * phẩm bất kỳ trên kết quả tìm kiếm, chắc chắn rằng AddToCart popup sẽ hiện lên
	 * với thông tin sản phẩm đã chọn mua. 3. Tiến hành mua hàng và xác nhận sản
	 * phẩm đã mua với số lượng tương ứng tồn tại trong giỏ hàng 4. Trong trường hợp
	 * người dùng mua 1 sản phẩm 02 lần thì chắc chắn rằng số lượng của sản phẩm này
	 * trong giỏ hàng sẽ được cộng dồn
	 */
	WebDriver driver;

	@Before
	public void InitTestCase() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32(1)\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.get("http://maiden.vn/");
		driver.manage().window().maximize();
		// Cos 2 loai wait
		// Implicit wait: cho theo khoang thoi gian toi da dinh truoc
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Expicit wait: cho co dk
		// WebDriverWait wait = new WebDriverWait(this.driver,10);
		// WebElement el =
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
		// close pop-up
		WebElement Popup = this.driver.findElement(By.cssSelector("a.close-tannm-modal b"));
		Popup.click();
		Thread.sleep(2000);
	}

	@After
	@Ignore
	public void EndTestCase() {
		this.driver.quit();
	}

	@BeforeMethod
	public void Search() {
		WebElement txtSearchInput = this.driver.findElement(By.cssSelector("input.form-control"));
		txtSearchInput.sendKeys("giấy");
		WebElement btnSearch = this.driver.findElement(By.cssSelector("button.btn-search"));
		btnSearch.sendKeys(Keys.ENTER);
	}

	@Test
	@Ignore
	public void SearchProduct() throws InterruptedException {
		// Thực hiện tìm kiếm một số sản phẩm trong hệ thống maiden.vn * với từ khóa
		// "giấy"
		// Given: user da access vao Maiden.vn
		// When: tìm kiếm một số sản phẩm trong hệ thống maiden.vn * với từ khóa "giấy"
		// Then: kết quả trả về các sản phẩm lien quan keyword
		// check ten ngau nhien ten 1 san pham
		String[] arrProduct = { "div.product-list-container  ul  li:nth-child(1)  a  div.product-name",
				"div.product-list-container  ul  li:nth-child(2)  a  div.product-name",
				"div.product-list-container  ul  li:nth-child(3)  a  div.product-name",
				"div.product-list-container  ul  li:nth-child(4)  a  div.product-name",
				"div.product-list-container  ul  li:nth-child(5)  a  div.product-name" };
		Random random = new Random();
		int randomProduct = random.nextInt(arrProduct.length);
		String Product = arrProduct[randomProduct];
		System.out.println(Product);
		String Product_name = this.driver.findElement(By.cssSelector(Product)).getText();
		System.out.println(Product_name);
		// kiem tra xem tu khoa giấy có ở trong tên sản phẩm ko
		boolean isContance = Product_name.contains("giấy");
		if (isContance = true) {
			System.out.println("Có tìm kiếm được sản phẩm giấy");
		}
	}

	@Test
	public void BuyProduct() throws InterruptedException {
		// 2. Thực hiện mua một sản phẩm bất kỳ trên kết quả tìm kiếm, chắc chắn rằng
		// AddToCart popup sẽ hiện lên
		// với thông tin sản phẩm đã chọn mua.
		// Given: User tìm kiếm với từ khoá giấy
		// When: add sp vào cart
		String[] arrProduct = { "div.product-list-container  ul  li:nth-child(1)",
				"div.product-list-container  ul  li:nth-child(2)", "div.product-list-container  ul  li:nth-child(3)",
				"div.product-list-container  ul  li:nth-child(4)", "div.product-list-container  ul  li:nth-child(5)" };
		Random random = new Random();
		int randomProduct = random.nextInt(arrProduct.length);
		String Product = arrProduct[randomProduct];
		System.out.println(Product);
		WebElement Product_WanttoBuy = this.driver.findElement(By.cssSelector(Product));
		Actions action = new Actions(this.driver);
		action.moveToElement(Product_WanttoBuy).perform();
		// Add to cart
		Product_WanttoBuy.findElement(By.cssSelector("button.add-to-cart")).click();
	}

	@Test
	public void Buy_Proceduce() throws InterruptedException {
		// 3. Tiến hành mua hàng và xác nhận sản
		// * phẩm đã mua với số lượng tương ứng tồn tại trong giỏ hàng
		// bấm mua 1 sp bất kỳ
		String[] arrProduct = { "div.product-list-container  ul  li:nth-child(1)",
				"div.product-list-container  ul  li:nth-child(2)", "div.product-list-container  ul  li:nth-child(3)",
				"div.product-list-container  ul  li:nth-child(4)", "div.product-list-container  ul  li:nth-child(5)" };
		Random random = new Random();
		int randomProduct = random.nextInt(arrProduct.length);
		String Product = arrProduct[randomProduct];
		System.out.println(Product);
		WebElement Product_WanttoBuy = this.driver.findElement(By.cssSelector(Product));
		// di vao detail san pham
		Product_WanttoBuy.click();
		Thread.sleep(2000);
		String CurrentURL = this.driver.getCurrentUrl();
		// check xem vao detail
		System.out.println(CurrentURL);
		// input so luong can mua
		WebElement inputAmount = this.driver.findElement(By.cssSelector("input#txtAmount"));
		inputAmount.sendKeys("5");
		// add vao gio hang
		WebElement btn_AddtoCart = this.driver.findElement(By.cssSelector("mybtn-purchase"));
		btn_AddtoCart.click();
		Thread.sleep(2000);
		// check so luong trong gio hang
		String qty_input = this.driver.findElement(By.cssSelector("input.qty-input")).getAttribute("value");
		// so sanh
		Assert.assertEquals(qty_input, "5");
	}

	public void Accumulate() throws InterruptedException {
		// 4. Trong trường hợp
		// * người dùng mua 1 sản phẩm 02 lần thì chắc chắn rằng số lượng của sản phẩm
		// này
		// * trong giỏ hàng sẽ được cộng dồn
		String[] arrProduct = { "div.product-list-container  ul  li:nth-child(1)",
				"div.product-list-container  ul  li:nth-child(2)", "div.product-list-container  ul  li:nth-child(3)",
				"div.product-list-container  ul  li:nth-child(4)", "div.product-list-container  ul  li:nth-child(5)" };
		Random random = new Random();
		int randomProduct = random.nextInt(arrProduct.length);
		String Product = arrProduct[randomProduct];
		System.out.println(Product);
		WebElement Product_WanttoBuy = this.driver.findElement(By.cssSelector(Product));
		// di vao detail san pham
		Product_WanttoBuy.click();
		Thread.sleep(2000);
		String CurrentURL = this.driver.getCurrentUrl();
		// check xem vao detail
		System.out.println(CurrentURL);
		// input so luong can mua
		WebElement inputAmount = this.driver.findElement(By.cssSelector("input#txtAmount"));
		inputAmount.sendKeys("5");
		// add vao gio hang
		WebElement btn_AddtoCart = this.driver.findElement(By.cssSelector("mybtn-purchase"));
		btn_AddtoCart.click();
		Thread.sleep(2000);
		WebElement btnBack = this.driver.findElement(By.cssSelector("button.btn-back-to-list"));
		Actions action = new Actions(this.driver);
		action.moveToElement(Product_WanttoBuy).perform();
		// Add to cart
		Product_WanttoBuy.findElement(By.cssSelector("button.add-to-cart")).click();
		inputAmount.sendKeys("7");
		btn_AddtoCart.click();

	}

}
