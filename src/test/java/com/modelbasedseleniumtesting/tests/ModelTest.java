package com.modelbasedseleniumtesting.tests;

import com.modelbasedseleniumtesting.model.HackerNewsModel;
import com.modelbasedseleniumtesting.secrets.SecretsFile.Secrets;
import org.graphwalker.core.condition.VertexCoverage;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.machine.Machine;
import org.graphwalker.core.machine.SimpleMachine;
import org.graphwalker.core.model.Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class ModelTest extends ExecutionContext {

    Secrets secrets;
    WebDriver driver;
    WebDriverWait wait;

    public void e_StartBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/hey/Downloads/chromedriver/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        this.secrets = new Secrets();

        driver.get("https://news.ycombinator.com/");
    }

    public void e_GoToLogin() {
        driver.findElement(By.linkText("login")).click();
    }

    public void e_SuccessfulLogin() {
        driver.findElement(By.xpath("(//input[@name='acct'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@name='acct'])[1]")).sendKeys(secrets.userName);
        driver.findElement(By.xpath("(//input[@name='pw'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@name='pw'])[1]")).sendKeys(secrets.password);
        driver.findElement(By.xpath("//input[@type='submit' and @value='login']")).click();
    }

    public void e_FailedLogin() {
        driver.findElement(By.xpath("(//input[@name='acct'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@name='acct'])[1]")).sendKeys("ThisAccountDoesntExist");
        driver.findElement(By.xpath("(//input[@name='pw'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@name='pw'])[1]")).sendKeys("ThisAccountDoesntExist");
        driver.findElement(By.xpath("//input[@type='submit' and @value='login']")).click();
    }

    public void e_SuccessfulLoginAfterFailure() {
        driver.findElement(By.xpath("(//input[@name='acct'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@name='acct'])[1]")).sendKeys(secrets.userName);
        driver.findElement(By.xpath("(//input[@name='pw'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@name='pw'])[1]")).sendKeys(secrets.password);
        driver.findElement(By.xpath("//input[@type='submit' and @value='login']")).click();
    }

    public void e_Logout() {
        driver.findElement(By.id("logout")).click();
    }

    public void v_Start() {
    }

    public void v_Homepage() {
//        Assert.assertEquals(this.driver.getTitle(),"Hacker News");
    }

    public void v_FailedLogin() {
//        Assert.assertTrue(this.driver.getPageSource().contains("Bad login."));
    }

    public void v_Login() {
//        Assert.assertFalse(this.driver.getPageSource().contains("Bad login."));
//        Assert.assertTrue(this.driver.getPageSource().contains("username:"));
    }

    public void v_HomePageLoggedIn() {
//        Assert.assertEquals(driver.getTitle(),"Hacker News");
//        Assert.assertNotNull(driver.findElements(By.id("logout")));
    }

    @Test
    public void fullCoverageTest() {
        HackerNewsModel hnModel = new HackerNewsModel();
        Model model = hnModel.generateModel();

        this.setModel(model.build());

        this.setPathGenerator(new RandomPath(new VertexCoverage(100)));

        setNextElement(model.getVertices().get(0));
        Machine machine = new SimpleMachine(this);

        while (machine.hasNextStep()) {
            machine.getNextStep();
        }
    }
}
