package Selenium.WebDriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
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
		WebElement the1stproduct = this.driver.findElement(By.cssSelector("#search-site > ul > li:nth-child(2) > a"));
		the1stproduct.click();
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement btnBuy = this.driver.findElement(By.cssSelector("div.area_order a.buy_now"));
		btnBuy.click();
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void Address() {
		// check Khi thay đổi thành phố trong địa chỉ nhận hang thì phải chắc chắn các
		// quận huyện phải chuyển tương ứng

		// tick vao dia chi nhan hang
		// kiem tra xem da tick dc chua
		WebElement CheckboxDiachinhanhang = this.driver.findElement(By.cssSelector("div.address i.iconmobile-opt"));
		CheckboxDiachinhanhang.click();
		boolean status = CheckboxDiachinhanhang.isSelected();
		if (status = true) {
			// chọn thành phố bất kỳ
			List<WebElement> lstCity = this.driver.findElements(By.cssSelector("div.area_address div.listcity.layer div.scroll a.n1"));
			for (int i = 0; i < lstCity.size(); i++) {
				Random rnd = new Random();
				int RndCity = rnd.nextInt(lstCity.size());
				System.out.println(RndCity);
				// list quan huyen
				WebElement District = this.driver.findElement(By.cssSelector("div.area_address div.listdist.layer div.scroll"));
				Select oSelect = new Select(District);
				List<WebElement> lstDist = this.driver.findElements(By.cssSelector("div.area_address div.listdist.layer div.scroll a.n1"));
				// check list district thay doi theo city
					int RndDist = rnd.nextInt(lstDist.size());
					WebElement DistElement = lstDist.get(RndDist);
					String Dist_name = this.driver.findElement(By.cssSelector(DistElement).

		}

	}

}
}
