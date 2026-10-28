package Streakify_project;

	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class Project_streakifyha {

	    WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	        driver.get("https://streakify-consistency-tracker.vercel.app/");
	    }

	    @Test(priority = 1)
	    public void testSetupPage() {
	        // Assert initial Setup page URL
	       

	        driver.findElement(By.name("userName")).sendKeys("savitha");
	        driver.findElement(By.name("userAge")).sendKeys("22");
	        driver.findElement(By.tagName("button")).click();
	        
	        Assert.assertTrue(
	        	    driver.getCurrentUrl().contains("Setup"));
	    }

	    @Test(priority = 2)
	    public void testDetailsPage() {
	        // Assert Details page loading by checking the designation element attribute name
	        String designationName = driver.findElement(By.name("designation")).getAttribute("name");
	        Assert.assertEquals(designationName, "designation", "Details page is not displayed!");

	        driver.findElement(By.name("designation")).sendKeys("Chennai");
	        driver.findElement(By.name("goal")).sendKeys("Home Page");
	        driver.findElement(By.cssSelector("button[type='submit']")).click();
	        
	        Assert.assertTrue(
	        	    driver.getCurrentUrl().contains("https://streakify-consistency-tracker.vercel.app/"));
	    }

	    @Test(priority = 3)
	    public void testSchedulePage() {
	        driver.findElement(By.cssSelector("a[href*='/Shedule']")).click();

	        // Assert Schedule page URL
	        Assert.assertEquals(driver.getCurrentUrl(), "https://streakify-consistency-tracker.vercel.app/Shedule", 
	                "Schedule page URL does not match!");

	        driver.findElement(By.cssSelector("button[type='submit']")).click();
	        driver.findElement(By.cssSelector(
	                ".px-3.py-2.rounded-lg.text-sm.font-medium.outline-none.bg-red-100.text-red-600"))
	                .sendKeys("complete");
	        
	                Assert.assertTrue(
	        	    driver.getCurrentUrl().contains("Shedule"));
	    }
	    @Test(priority = 4)
	    public void testProfileAndDeleteAccountPage() {
	        driver.findElement(By.cssSelector("a[href='/profile']")).click();

	        // Assert Profile page URL
	        Assert.assertEquals(driver.getCurrentUrl(), "https://streakify-consistency-tracker.vercel.app/profile", 
	                "Profile page URL does not match!");

	        // Scroll down
	        Actions actions = new Actions(driver);
	        actions.scrollByAmount(0, 1500).perform();

	        // Open delete modal
	        driver.findElement(By.tagName("button")).click();

	        // Assert Cancel button text
	        String cancelBtnText = driver.findElement(By.xpath("//button[text()='Cancel']")).getText();
	        Assert.assertEquals(cancelBtnText, "Cancel", "Cancel button text does not match!");

	        // Cancel delete
	        driver.findElement(By.xpath("//button[text()='Cancel']")).click();

	        // Reopen delete modal
	        driver.findElement(By.tagName("button")).click();

	        // Assert Confirm button text
	        String confirmBtnText = driver.findElement(By.xpath("//button[text()='Confirm']")).getText();
	        Assert.assertEquals(confirmBtnText, "Confirm", "Confirm button text does not match!");

	        // Confirm delete
	        driver.findElement(By.xpath("//button[text()='Confirm']")).click();

	        System.out.println("Delete account completed successfully");
	       
	        Assert.assertTrue(
	        	    driver.getCurrentUrl().contains("Setup"));
	        
	        
	    }

	   @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	        

}
