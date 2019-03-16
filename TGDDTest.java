package Selenium.WebDriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TGDDTest {
	WebDriver driver;

	@Before
	public void InitTestCase() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32(1)\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.get("http://thegioididong.com/");
		driver.manage().window().maximize();
		// search 1 san pham voi keyword bat ky
		WebElement txtSearch = this.driver.findElement(By.cssSelector("input.topinput"));
		txtSearch.sendKeys("samsung s10");
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.wrap-suggestion")));
		// chon sản phẩm đầu tiên trong suggestion
		WebElement the1stproduct = this.driver.findElement(By.cssSelector("#search-site  ul  li:nth-child(2)  a"));
		the1stproduct.click();
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement btnBuy = this.driver.findElement(By.cssSelector("div.area_order a.buy_now"));
		btnBuy.click();
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	@Ignore
	public void Address() {
		// check Khi thay đổi thành phố trong địa chỉ nhận hang thì phải chắc chắn các
		// quận huyện phải chuyển tương ứng

		// click vao dia chi nhan hang
		// kiem tra xem da tick dc chua
		WebElement CheckboxDiachinhanhang = this.driver.findElement(By.cssSelector("div.address i.iconmobile-opt"));
		CheckboxDiachinhanhang.click();
		boolean status = CheckboxDiachinhanhang.isSelected();
		if (status == true) {
			// chọn thành phố
			// open dropdown
			WebDriverWait wait = new WebDriverWait(this.driver, 10);
			WebElement el21 = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.area_address")));
			WebElement dropdown = this.driver.findElement(By.cssSelector("div.firstaddress div.city"));
			Select oSelect = new Select(dropdown);
			WebElement el2 = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("div.firstaddress div.listcity.layer")));
			// select random city
			List<WebElement> lstCity = this.driver
					.findElements(By.cssSelector("div.area_address div.listcity.layer div.scroll a.n1"));
			Random rnd = new Random();
			int RndCity = rnd.nextInt(lstCity.size());
			System.out.println(RndCity);
			WebElement Ecity = lstCity.get(RndCity);
			String City_name = Ecity.getText();
			// type random city to search box
			WebElement txtSearch = this.driver.findElement(
					By.cssSelector("div.area_address  div.firstaddress  div.listcity.layer  input[type=\"text\"]"));
			txtSearch.sendKeys(City_name);

			// list quan huyen
			for (int i = 0; i < lstCity.size(); i++) {
				WebElement District = this.driver.findElement(By.cssSelector("div.firstaddress div.dist"));
				Select oSelect2 = new Select(District);
				List<WebElement> lstDist = oSelect2.getOptions();
				System.out.println(lstDist.size());
				// check list district thay doi theo city
				if (lstDist.get(i) != lstDist.get(i + 1)) {
					System.out.println("Danh sach quan huyen co thay doi theo thanh pho");

				}
			}

		}

	}

	@Test
	// Khi chọn option "Cà thẻ khi nhận hang" thì nút "Thanh Toán Online" sẽ biến
	// mất chỉ còn "Thanh toán khi nhận hang"
	public void ThanhToan() {
		WebElement CheckboxDiachinhanhang = this.driver.findElement(By.cssSelector("div.address i.iconmobile-opt"));
		CheckboxDiachinhanhang.click();
		boolean status = CheckboxDiachinhanhang.isSelected();
		if (status == true) {
			WebElement Cathe = this.driver.findElement(By.cssSelector("label.cathe"));
			Cathe.click();
			Boolean status_cathe = Cathe.isSelected();
			if (status_cathe == true) {
				WebElement payonline = this.driver.findElement(By.cssSelector("div.payonline"));
				String isDisplay = payonline.getAttribute("style");
				Assert.assertEquals(isDisplay, "");

			}
		}

	}

	@Test
	// Khi option "Xuất hóa đơn công ty" được chọn thì sẽ yêu cầu nhập thông tin về
	// công ty nhận hóa đơn
	public void Hoadon() {
		WebElement CheckboxDiachinhanhang = this.driver.findElement(By.cssSelector("div.address i.iconmobile-opt"));
		CheckboxDiachinhanhang.click();
		boolean status = CheckboxDiachinhanhang.isSelected();
		if (status == true) {
			WebElement vat = this.driver.findElement(By.className("label.vat"));
			vat.click();
			Boolean status_vat = vat.isSelected();
			if (status_vat == true) {
		}
	}
}
}
