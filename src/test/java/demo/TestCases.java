package demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.testng.Assert;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */

         @BeforeTest
        public void startBrowser()
        {
            System.setProperty("java.util.logging.config.file", "logging.properties");
    
            // NOT NEEDED FOR SELENIUM MANAGER
            // WebDriverManager.chromedriver().timeout(30).setup();
    
            ChromeOptions options = new ChromeOptions();
            LoggingPreferences logs = new LoggingPreferences();
    
            logs.enable(LogType.BROWSER, Level.ALL);
            logs.enable(LogType.DRIVER, Level.ALL);
            options.setCapability("goog:loggingPrefs", logs);
            options.addArguments("--remote-allow-origins=*");
    
            System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 
    
            driver = new ChromeDriver(options);
    
            driver.manage().window().maximize();
        }
    
    

  @Test
    public void testCase01() throws InterruptedException, IOException {
        Wrappers.goToUrlAndWait(driver, "https://www.scrapethissite.com/pages/");

        List<Map<String, Object>> teamDataList = new ArrayList<>();

        // Example data collection (replace with your actual logic)
        for (int i = 0; i < 5; i++) {
            Map<String, Object> teamData = new HashMap<>();
            teamData.put("Epoch Time of Scrape", Instant.now().toEpochMilli());
            teamData.put("Team Name", "Team " + i);
            teamData.put("Year", 2024);
            teamData.put("Win %", 0.35 + i * 0.05);
            teamDataList.add(teamData);
            System.out.println(teamData);

        }

        // Convert to JSON and write to file
        writeToJsonFile("hockey-team-data.json", teamDataList);
    }

    @Test
    public void testCase02() throws InterruptedException, IOException {
        Wrappers.goToUrlAndWait(driver, "https://www.scrapethissite.com/pages/");

        List<Map<String, Object>> oscarDataList = new ArrayList<>();

        // Example data collection (replace with your actual logic)
        for (int i = 0; i < 5; i++) {
            Map<String, Object> movieData = new HashMap<>();
            movieData.put("Epoch Time of Scrape", Instant.now().toEpochMilli());
            movieData.put("Year", 2024);
            movieData.put("Title", "Movie " + i);
            movieData.put("Nomination", 5 + i);
            movieData.put("Awards", 2 + i);
            movieData.put("isWinner", i == 0); // Example: First movie is the winner
            oscarDataList.add(movieData);
            System.out.println(movieData);
            System.out.println(oscarDataList);

        }

        // Convert to JSON and write to file
        writeToJsonFile("oscar-winner-data.json", oscarDataList);
    }

    // Method to write list of maps to JSON file
    private void writeToJsonFile(String fileName, List<Map<String, Object>> dataList) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write("[\n");
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> data = dataList.get(i);
            fileWriter.write("\t{\n");
            int j = 0;
            for (String key : data.keySet()) {
                fileWriter.write("\t\t\"" + key + "\": \"" + data.get(key) + "\"");
                if (j < data.size() - 1) {
                    fileWriter.write(",");
                }
                fileWriter.write("\n");
                j++;
            }
            fileWriter.write("\t}");
            if (i < dataList.size() - 1) {
                fileWriter.write(",");
            }
            fileWriter.write("\n");
        }
        fileWriter.write("]");
        fileWriter.close();
    
}
    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}