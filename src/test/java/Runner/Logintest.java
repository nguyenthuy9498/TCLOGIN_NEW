package Runner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Logintest {
    WebDriver driver;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\Desktop\\chromedriver_win32\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }
    //TC Login thanh cong
    @Test
    public void TC1(){
        Login("standard_user","secret_sauce");
        WebElement card = driver.findElement(By.id("shopping_cart_container"));
        Assert.assertTrue(card.isDisplayed());
    }
    // login user va pass sai
    @Test
    public void TC2(){
        Login("ahha","ajs");
        WebElement errorlabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",errorlabel.getText());
    }
    // login user đúng pass sai
    @Test
    public void TC3(){
        Login("standard_user","ajs");
        WebElement errorlabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",errorlabel.getText());
    }
    // login user sai pass đúng
    @Test
    public void TC4(){
        Login("ahha","secret_sauce");
        WebElement errorlabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",errorlabel.getText());
    }
    // login ko nhap username
    @Test
    public void TC5(){
        Login("","secret_sauce");
        WebElement errorlabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username is required",errorlabel.getText());
    }
    // login ko nhap pass
    @Test
    public void TC6(){
        Login("standard_user","");
        WebElement errorlabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Password is required",errorlabel.getText());
    }
    //username khong trim dau
    @Test
    public void TC7(){
        Login("    standard_user","secret_sauce");
        WebElement errorlabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",errorlabel.getText());
    }
    //username khong trim cuoi
    @Test
    public void TC8(){
        Login("standard_user    ","secret_sauce");
        WebElement errorlabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",errorlabel.getText());
    }
    @After
    public void teardown(){
        driver.quit();
    }
    public void Login(String Username,String pass){
        WebElement txtUsername = driver.findElement(By.id("user-name"));
        WebElement txtPass = driver.findElement(By.id("password"));
        WebElement txtButton = driver.findElement(By.id("login-button"));
        txtUsername.sendKeys(Username);
        txtPass.sendKeys(pass);
        txtButton.click();
    }
}
