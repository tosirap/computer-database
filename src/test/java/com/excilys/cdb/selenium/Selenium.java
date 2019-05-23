package com.excilys.cdb.selenium;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.excilys.cdb.configSpring.AppConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class Selenium {

	private WebDriver driver;
	private final String BASE_URL = "http://localhost:8080/computer-database/";

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@After
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testBoutonPcParPage() {
		driver.get(BASE_URL);

		WebElement pagination = driver.findElement(By.xpath("//ul[@class='pagination']"));

		List<String> realValues = pagination.findElements(By.xpath("li")).stream().map(WebElement::getText)
				.collect(Collectors.toList());
		List<String> expectedValues = Arrays.asList("«", "1", "2", "3", "4", "5", "»");

		assertEquals(expectedValues, realValues);
	}
}
