package MainTests;

import Elements.BlockWithGoods;
import Elements.ProducerPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 06.07.2015.
 */
public class TestsForToshiba {
    WebDriver driver;
    String baseUrl;
    List<WebElement> listWithElements;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        baseUrl = "http://rozetka.com.ua/notebooks/c80004/filter/";
        driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void Test() throws  Exception{
        ProducerPage Toshiba;
        BlockWithGoods forToshiba;
        Toshiba = new ProducerPage(driver);
        forToshiba = new BlockWithGoods(driver);
        Toshiba.getToshiba();
        String pageTitle = forToshiba.getPageTitle();
        System.out.println(pageTitle);
        Wait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".c-cols-inner-l>h1")));
        Assert.assertTrue(pageTitle.contains("Toshiba"));
    }

    @Test
    public void TestContainsElement() throws Exception{
        ProducerPage Toshiba = new ProducerPage(driver);
        BlockWithGoods forToshiba = new BlockWithGoods(driver);
        Toshiba.getToshiba();
        listWithElements = forToshiba.getNamesOfElements();
        List<String> listWithTitles = forToshiba.getTextsOfElements(listWithElements);
        for(String li : listWithTitles) {
            Assert.assertTrue(li.contains("Toshiba"));
        }
    }

    @Test
    public void TestCountOfElements() throws Exception {
        ProducerPage Toshiba = new ProducerPage(driver);
        BlockWithGoods forToshiba = new BlockWithGoods(driver);
        int resultCountOfElements = Toshiba.getToshibaNumber();
        Toshiba.getToshiba();
        listWithElements = forToshiba.getNamesOfElements();
        Assert.assertTrue(listWithElements.size() == resultCountOfElements);
    }

    @Test
    public void TestCompateCountWithTitle() {
        ProducerPage Toshiba = new ProducerPage(driver);
        BlockWithGoods forToshiba = new BlockWithGoods(driver);
        Toshiba.getToshiba();
        int resultCountOfElements = forToshiba.getNumberOfCountElements();
        listWithElements = forToshiba.getNamesOfElements();
        Assert.assertTrue(listWithElements.size() == resultCountOfElements);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
