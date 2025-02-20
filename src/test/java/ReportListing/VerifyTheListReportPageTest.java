package ReportListing;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class VerifyTheListReportPageTest extends BrowserSetUp {

    private final Logger logger = LogManager.getLogger(VerifyTheListReportPageTest.class);

    public WebDriver driver;
    public WebDriverWait wait;
    int time=30;
    Properties props;
    private CommonMethods cms;
    private VerifyTheListReportPage verifyTheListReportPage;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        cms=new CommonMethods(driver);
        Property.readReportListingProperties();
        verifyTheListReportPage = new VerifyTheListReportPage(driver);
    }

    //Verify the presence of Sigtuple logo snd PBS
    @Test(priority = 1 ,enabled = true)
    public void verifySigLogoAndPBS() {
        Assert.assertTrue(verifyTheListReportPage.uIOfSigtuplelogoAndPbs());
        logger.info(" Sigtuple logo and PBS is Present on the page");
    }

    //Verify the kebaba menu icon
    @Test(priority = 2 ,enabled = true)
    public void verifyTheKebabMenuIcon(){
        Assert.assertTrue(verifyTheListReportPage.uIOfKebab());
        logger.info("Kebab menu is Displayed on the app ");
    }

    //verify the Search ,filter and check box of my report
    @Test(priority = 3 ,enabled = true)
    public void verifytheSearchAndFilterAndCheckbox(){
        Assert.assertTrue(verifyTheListReportPage.uIOfSearchFieldAndFilterIconAndClearreport());
        logger.info(" Search textbox and Filter is Present on the page");
    }

    //verify The Functionality of My report CheckBox
    @Test(priority = 4 ,enabled = true)
    public void verifyTheFunctionalityOfTheMyReportCheckBox(){
        Assert.assertTrue(verifyTheListReportPage.uIOfMyReportCheckBox());
        logger.info("My report Check box  is Present on the page");
    }
    //verify the functionality of my report check box
    @Test(priority = 5 , enabled = true)
    public void verifyfunctionalityofmyreportcheckbox() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.myReportCheckBoxFunctionality());
        logger.info(" Verify the functionality of the my report check box");
    }


    //Verify the content of header
    @Test(priority = 6 ,enabled = true)
    public void verityHeader(){
       String listReportHeader= verifyTheListReportPage.uIOfHeaderofListReport();
        Assert.assertEquals(listReportHeader,"Slide Id Scan date Status Assigned to Additional info");
        logger.info("Header of the Report listing is displayed and verified");

    }

    // verify the search text field
    @Test(priority = 7 ,enabled = true)
    public void verifyTheFunctionalityOfSearchField() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.sampleSaerchingBySlideId());
        logger.info("Functionality of search text field is working fine");

    }

    // verify the Functionality of filter icon
    @Test(priority = 8 ,enabled = true)
    public void verifyTheFunctionalityOfFilterIcon(){
        Assert.assertTrue(verifyTheListReportPage.filterIcon());
        logger.info("Functionality of Filter icon is working Fine");

    }

    //verify the same slide id on additional info tab
    @Test(priority = 9 ,enabled = true)
    public void verifyTheSameSlideIdonAdditionalInfo() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.additionalInfoDetails());
        logger.info("Functionality Additional info icon is enable and same slide id is Displyed");
    }

    //Verify the  presence of filter option
    @Test(priority = 14 ,enabled = true)
    public void verifyThePresenceOfFilterOption() throws InterruptedException {
        Assert.assertEquals(verifyTheListReportPage.filterOption(),"Scan date\nAssigned to\nStatus\nDevice ID\nProduct Version\n");
        logger.info("Verify the Filter Options");
    }

    //verify the functionality of Reset button after selecting any option
    @Test(priority = 12 ,enabled = true)
    public  void verifyTheResetButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.resetbutton());
        logger.info("Verify the functionality of the Rest Button");
    }

    //verify the functionality of Apply button after selecting any option
    @Test(priority = 11 ,enabled = true)
    public  void verifyTheApplytButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.applybutton());
        logger.info("Verify the functionality of the apply Button");
    }
    //verify the functionality of clear filter report
    @Test(priority = 12 ,enabled = true)
    public  void verifyTheClearFilterButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.clearfilteroption());
        logger.info("Verify the functionality of the apply Button");
    }

    //verify the functionality of clear filter report
    @Test(priority = 13 ,enabled = true)
    public  void verifyTheFilterCloseButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.filterclose());
        logger.info("Verify the functionality of the Close Button");
    }

    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 15 ,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.bookmarkSavedStatus());
        logger.info("Verify the functionality of the Bookmark Button");
    }
    //verify the functionality of bookmark for multiple reports
    @Test(priority = 16 ,enabled = true)
    public  void verifyTheFunctionalityOfMultipleBookmarkButtonForSaved() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkSavedStatus());
        logger.info("Verify the functionality of the all Bookmark Button");
    }

    //verify the functionality of bookmark for multiple reports
    @Test(priority = 17 ,enabled = true)
    public  void verifyTheFunctionalityOfMultipleBookmarkButtonForRemoved() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkRemovedStatus());
        logger.info("Verify the functionality of the all Bookmark Button");
    }


    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 18 ,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkTextfield() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.bookmarkComment());
        logger.info("Verify the functionality of the Bookmark textfield ");
    }

    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 19 ,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkTextfieldForMultipleReport() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkComment());
        logger.info("Verify the functionality of the Bookmark textfield For multiple Report ");
    }

    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 20 ,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkTextfieldForMultipleReportUpdate() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkCommentUpdate());
        logger.info("Verify the functionality of the Bookmark textfield For multiple Report");
    }

    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 21,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkTextfieldForMultipleReportDelete() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkCommentDelete());
        logger.info("Verify the functionality of the Bookmark textfield For multiple Report");
    }


    //verify the functionality of bookmark for multiple reports
    @Test(priority = 22 ,enabled = true)
    public  void verifyTheFunctionalityOfMultipleBookmarkButtonForSavedInReviewedStatus() throws InterruptedException {
        cms.selectSpecificStatus("Reviewed");
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkSavedStatus());
        logger.info("Verify the functionality of the all Bookmark Button");
    }

    //verify the functionality of bookmark for multiple reports
    @Test(priority = 23 ,enabled = true)
    public  void verifyTheFunctionalityOfMultipleBookmarkButtonForRemovedInReviewedStatus() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkRemovedStatus());
        logger.info("Verify the functionality of the all Bookmark Button");
    }


    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 23 ,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkTextfieldForMultipleReportInReviewedStatus() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkComment());
        logger.info("Verify the functionality of the Bookmark textfield For multiple Report ");
    }

    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 24 ,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkTextfieldForMultipleReportUpdateInReviewedStatus() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkCommentUpdate());
        logger.info("Verify the functionality of the Bookmark textfield For multiple Report");
    }

    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 25,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkTextfieldForMultipleReportDeleteInReviewedStatus() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.multipleBookmarkCommentDelete());
        logger.info("Verify the functionality of the Bookmark textfield For multiple Report");
    }

    //Verification of the presence of the time zone option and the configured time zone.
    @Test(priority = 26,enabled = true)
    public  void verifyThePresenceOfTimezoneOption() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.PresenceOfTimezoneOption());
        logger.info("Verify the presence of the time zone option");
    }

    @Test(priority = 27,enabled = true)
    public  void verifyThePresenceOfTimezoneOptionInPreparingPage() throws InterruptedException {
        cms.selectSpecificStatus("Preparing");
        Assert.assertTrue(verifyTheListReportPage.PresenceOfTimezoneOption());
        logger.info("Verify the presence of the time zone option");
    }


    @Test(priority = 28,enabled = true)
    public  void verifyThePresenceOfTimezoneOptionInReviewedPage() throws InterruptedException {
        cms.selectSpecificStatus("Reviewed");
        Assert.assertTrue(verifyTheListReportPage.PresenceOfTimezoneOption());
        logger.info("Verify the presence of the time zone option");
    }

    //Verify the functionality of the Edit CTA
    @Test(priority = 29,enabled = true)
    public  void verifyTheFunctionalityOfEditCTA() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.FunctionalityOfEditCTA("(UTC+03:00) Africa/Asmara"));
        logger.info("Verify the functionality of the Edit CTA");
    }
    @Test(priority = 30,enabled = true)
    public  void verifyTheFunctionalityOfEditCTAInPreparingPage() throws InterruptedException {
        cms.selectSpecificStatus("Preparing");
        Assert.assertTrue(verifyTheListReportPage.FunctionalityOfEditCTA("(UTC+01:00) Africa/Bangui"));
        logger.info("Verify the functionality of the Edit CTA");
    }
    @Test(priority = 31,enabled = true)
    public  void verifyTheFunctionalityOfEditCTAInReviewedPage() throws InterruptedException {
        cms.selectSpecificStatus("Reviewed");
        Assert.assertTrue(verifyTheListReportPage.FunctionalityOfEditCTA("(UTC+03:00) Africa/Asmera"));
        logger.info("Verify the functionality of the Edit CTA");
    }

    //Verification of User Viewing Scanned on time within the Configured Time zone set on the reporting.
    @Test(priority = 31,enabled = true)
    public  void verifyTheConfigureTimeZoneIsSetOnTheReporting() throws InterruptedException, FileNotFoundException {
        String csvFilePath = "/Users/manjunathcg/Downloads/AS76-Automation-Testing-main/timezones.csv";

        try (Reader in = new FileReader(csvFilePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);

            for (CSVRecord record : records) {
                String timeZone = record.get("TimeZone");

                String expectedResult = "";
                String description = "";

                if (record.size() > 1) {
                    expectedResult = record.get("ExpectedResult");
                    description = record.get("Description");
                }

                logger.info("Testing timezone: " + timeZone + " Expected: " + expectedResult + " Desc: " + description);

                boolean result = verifyTheListReportPage.ConfigureTimeZoneIsSetOnTheReporting(timeZone);

                //Assert.assertTrue(result, "Timezone test failed for: " + timeZone + ". Description: " + description);

                logger.info("Timezone test " + (result ? "passed" : "failed") + ": " + timeZone);
            }
        } catch (IOException e) {
            logger.error("Error reading CSV file: " + e.getMessage()); // Log the error
            Assert.fail("CSV file could not be read: " + e.getMessage());
        }
    }










    //TimeZone
    @Test(priority = 26,enabled = true)
    public void verifyTheChangeOfTimezome() throws InterruptedException {
        List<String> allTimeZones = Arrays.asList(
                "(UTC+00:00) Africa/Abidjan",
                "(UTC+00:00) Africa/Accra",
                "(UTC+03:00) Africa/Addis_Ababa",
                "(UTC+01:00) Africa/Algiers",
                "(UTC+03:00) Africa/Asmara",
                "(UTC+03:00) Africa/Asmera",
                "(UTC+00:00) Africa/Bamako",
                "(UTC+01:00) Africa/Bangui",
                "(UTC+00:00) Africa/Banjul",
                "(UTC+00:00) Africa/Bissau",
                "(UTC+02:00) Africa/Blantyre",
                "(UTC+01:00) Africa/Brazzaville",
                "(UTC+02:00) Africa/Bujumbura",
                "(UTC+02:00) Africa/Cairo",
                "(UTC+01:00) Africa/Casablanca",
                "(UTC+01:00) Africa/Ceuta",
                "(UTC+00:00) Africa/Conakry",
                "(UTC+00:00) Africa/Dakar",
                "(UTC+03:00) Africa/Dar_es_Salaam",
                "(UTC+03:00) Africa/Djibouti",
                "(UTC+01:00) Africa/Douala",
                "(UTC+01:00) Africa/El_Aaiun",
                "(UTC+00:00) Africa/Freetown",
                "(UTC+02:00) Africa/Gaborone",
                "(UTC+02:00) Africa/Harare",
                "(UTC+02:00) Africa/Johannesburg",
                "(UTC+02:00) Africa/Juba",
                "(UTC+03:00) Africa/Kampala",
                "(UTC+02:00) Africa/Khartoum",
                "(UTC+02:00) Africa/Kigali",
                "(UTC+01:00) Africa/Kinshasa",
                "(UTC+01:00) Africa/Lagos",
                "(UTC+01:00) Africa/Libreville",
                "(UTC+00:00) Africa/Lome",
                "(UTC+01:00) Africa/Luanda",
                "(UTC+02:00) Africa/Lubumbashi",
                "(UTC+02:00) Africa/Lusaka",
                "(UTC+01:00) Africa/Malabo",
                "(UTC+02:00) Africa/Maputo",
                "(UTC+02:00) Africa/Maseru",
                "(UTC+02:00) Africa/Mbabane",
                "(UTC+03:00) Africa/Mogadishu",
                "(UTC+00:00) Africa/Monrovia",
                "(UTC+03:00) Africa/Nairobi",
                "(UTC+01:00) Africa/Ndjamena",
                "(UTC+01:00) Africa/Niamey",
                "(UTC+00:00) Africa/Nouakchott",
                "(UTC+00:00) Africa/Ouagadougou",
                "(UTC+01:00) Africa/Porto-Novo",
                "(UTC+00:00) Africa/Sao_Tome",
                "(UTC+00:00) Africa/Timbuktu",
                "(UTC+02:00) Africa/Tripoli",
                "(UTC+01:00) Africa/Tunis",
                "(UTC+02:00) Africa/Windhoek",
                "(UTC-10:00) America/Adak",
                "(UTC-09:00) America/Anchorage",
                "(UTC-04:00) America/Anguilla",
                "(UTC-04:00) America/Antigua",
                "(UTC-03:00) America/Araguaina",
                "(UTC-03:00) America/Argentina/Buenos_Aires",
                "(UTC-03:00) America/Argentina/Catamarca",
                "(UTC-03:00) America/Argentina/ComodRivadavia",
                "(UTC-03:00) America/Argentina/Cordoba",
                "(UTC-03:00) America/Argentina/Jujuy",
                "(UTC-03:00) America/Argentina/La_Rioja",
                "(UTC-03:00) America/Argentina/Mendoza",
                "(UTC-03:00) America/Argentina/Rio_Gallegos",
                "(UTC-03:00) America/Argentina/Salta",
                "(UTC-03:00) America/Argentina/San_Juan",
                "(UTC-03:00) America/Argentina/San_Luis",
                "(UTC-03:00) America/Argentina/Tucuman",
                "(UTC-03:00) America/Argentina/Ushuaia",
                "(UTC-04:00) America/Aruba",
                "(UTC-03:00) America/Asuncion",
                "(UTC-05:00) America/Atikokan",
                "(UTC-10:00) America/Atka",
                "(UTC-03:00) America/Bahia",
                "(UTC-06:00) America/Bahia_Banderas",
                "(UTC-04:00) America/Barbados",
                "(UTC-03:00) America/Belem",
                "(UTC-06:00) America/Belize",
                "(UTC-04:00) America/Blanc-Sablon",
                "(UTC-04:00) America/Boa_Vista",
                "(UTC-05:00) America/Bogota",
                "(UTC-07:00) America/Boise",
                "(UTC-03:00) America/Buenos_Aires",
                "(UTC-07:00) America/Cambridge_Bay",
                "(UTC-04:00) America/Campo_Grande",
                "(UTC-05:00) America/Cancun",
                "(UTC-04:00) America/Caracas",
                "(UTC-03:00) America/Catamarca",
                "(UTC-03:00) America/Cayenne",
                "(UTC-05:00) America/Cayman",
                "(UTC-06:00) America/Chicago",
                "(UTC-06:00) America/Chihuahua",
                "(UTC-07:00) America/Ciudad_Juarez",
                "(UTC-05:00) America/Coral_Harbour",
                "(UTC-03:00) America/Cordoba",
                "(UTC-06:00) America/Costa_Rica",
                "(UTC-07:00) America/Creston",
                "(UTC-04:00) America/Cuiaba",
                "(UTC-04:00) America/Curacao",
                "(UTC+00:00) America/Danmarkshavn",
                "(UTC-07:00) America/Dawson",
                "(UTC-07:00) America/Dawson_Creek",
                "(UTC-07:00) America/Denver",
                "(UTC-05:00) America/Detroit",
                "(UTC-04:00) America/Dominica",
                "(UTC-07:00) America/Edmonton",
                "(UTC-05:00) America/Eirunepe",
                "(UTC-06:00) America/El_Salvador",
                "(UTC-08:00) America/Ensenada",
                "(UTC-07:00) America/Fort_Nelson",
                "(UTC-05:00) America/Fort_Wayne",
                "(UTC-03:00) America/Fortaleza",
                "(UTC-04:00) America/Glace_Bay",
                "(UTC-02:00) America/Godthab",
                "(UTC-04:00) America/Goose_Bay",
                "(UTC-05:00) America/Grand_Turk",
                "(UTC-04:00) America/Grenada",
                "(UTC-04:00) America/Guadeloupe",
                "(UTC-06:00) America/Guatemala",
                "(UTC-05:00) America/Guayaquil",
                "(UTC-04:00) America/Guyana",
                "(UTC-04:00) America/Halifax",
                "(UTC-05:00) America/Havana",
                "(UTC-07:00) America/Hermosillo",
                "(UTC-05:00) America/Indiana/Indianapolis",
                "(UTC-06:00) America/Indiana/Knox",
                "(UTC-05:00) America/Indiana/Marengo",
                "(UTC-05:00) America/Indiana/Petersburg",
                "(UTC-06:00) America/Indiana/Tell_City",
                "(UTC-05:00) America/Indiana/Vevay",
                "(UTC-05:00) America/Indiana/Vincennes",
                "(UTC-05:00) America/Indiana/Winamac",
                "(UTC-05:00) America/Indianapolis",
                "(UTC-07:00) America/Inuvik",
                "(UTC-05:00) America/Iqaluit",
                "(UTC-05:00) America/Jamaica",
                "(UTC-03:00) America/Jujuy",
                "(UTC-09:00) America/Juneau",
                "(UTC-05:00) America/Kentucky/Louisville",
                "(UTC-05:00) America/Kentucky/Monticello",
                "(UTC-06:00) America/Knox_IN",
                "(UTC-04:00) America/Kralendijk",
                "(UTC-04:00) America/La_Paz",
                "(UTC-05:00) America/Lima",
                "(UTC-08:00) America/Los_Angeles",
                "(UTC-05:00) America/Louisville",
                "(UTC-04:00) America/Lower_Princes",
                "(UTC-03:00) America/Maceio",
                "(UTC-06:00) America/Managua",
                "(UTC-04:00) America/Manaus",
                "(UTC-04:00) America/Marigot",
                "(UTC-04:00) America/Martinique",
                "(UTC-06:00) America/Matamoros",
                "(UTC-07:00) America/Mazatlan",
                "(UTC-03:00) America/Mendoza",
                "(UTC-06:00) America/Menominee",
                "(UTC-06:00) America/Merida",
                "(UTC-09:00) America/Metlakatla",
                "(UTC-06:00) America/Mexico_City",
                "(UTC-03:00) America/Miquelon",
                "(UTC-04:00) America/Moncton",
                "(UTC-06:00) America/Monterrey",
                "(UTC-03:00) America/Montevideo",
                "(UTC-05:00) America/Montreal",
                "(UTC-04:00) America/Montserrat",
                "(UTC-05:00) America/Nassau",
                "(UTC-05:00) America/New_York",
                "(UTC-05:00) America/Nipigon",
                "(UTC-09:00) America/Nome",
                "(UTC-02:00) America/Noronha",
                "(UTC-06:00) America/North_Dakota/Beulah",
                "(UTC-06:00) America/North_Dakota/Center",
                "(UTC-06:00) America/North_Dakota/New_Salem",
                "(UTC-02:00) America/Nuuk",
                "(UTC-06:00) America/Ojinaga",
                "(UTC-05:00) America/Panama",
                "(UTC-05:00) America/Pangnirtung",
                "(UTC-03:00) America/Paramaribo",
                "(UTC-07:00) America/Phoenix",
                "(UTC-05:00) America/Port-au-Prince",
                "(UTC-04:00) America/Port_of_Spain",
                "(UTC-05:00) America/Porto_Acre",
                "(UTC-04:00) America/Porto_Velho",
                "(UTC-04:00) America/Puerto_Rico",
                "(UTC-03:00) America/Punta_Arenas",
                "(UTC-06:00) America/Rainy_River",
                "(UTC-06:00) America/Rankin_Inlet",
                "(UTC-03:00) America/Recife",
                "(UTC-06:00) America/Regina",
                "(UTC-06:00) America/Resolute",
                "(UTC-05:00) America/Rio_Branco",
                "(UTC-03:00) America/Rosario",
                "(UTC-08:00) America/Santa_Isabel",
                "(UTC-03:00) America/Santarem",
                "(UTC-03:00) America/Santiago",
                "(UTC-04:00) America/Santo_Domingo",
                "(UTC-03:00) America/Sao_Paulo",
                "(UTC-02:00) America/Scoresbysund",
                "(UTC-07:00) America/Shiprock",
                "(UTC-09:00) America/Sitka",
                "(UTC-04:00) America/St_Barthelemy",
                "(UTC-03:30) America/St_Johns",
                "(UTC-04:00) America/St_Kitts",
                "(UTC-04:00) America/St_Lucia",
                "(UTC-04:00) America/St_Thomas",
                "(UTC-04:00) America/St_Vincent",
                "(UTC-06:00) America/Swift_Current",
                "(UTC-06:00) America/Tegucigalpa",
                "(UTC-04:00) America/Thule",
                "(UTC-05:00) America/Thunder_Bay",
                "(UTC-08:00) America/Tijuana",
                "(UTC-05:00) America/Toronto",
                "(UTC-04:00) America/Tortola",
                "(UTC-08:00) America/Vancouver",
                "(UTC-04:00) America/Virgin",
                "(UTC-07:00) America/Whitehorse",
                "(UTC-06:00) America/Winnipeg",
                "(UTC-09:00) America/Yakutat",
                "(UTC-07:00) America/Yellowknife",
                "(UTC+08:00) Antarctica/Casey",
                "(UTC+07:00) Antarctica/Davis",
                "(UTC+10:00) Antarctica/DumontDUrville",
                "(UTC+11:00) Antarctica/Macquarie",
                "(UTC+05:00) Antarctica/Mawson",
                "(UTC+13:00) Antarctica/McMurdo",
                "(UTC-03:00) Antarctica/Palmer",
                "(UTC-03:00) Antarctica/Rothera",
                "(UTC+13:00) Antarctica/South_Pole",
                "(UTC+03:00) Antarctica/Syowa",
                "(UTC+00:00) Antarctica/Troll",
                "(UTC+05:00) Antarctica/Vostok",
                "(UTC+01:00) Arctic/Longyearbyen",
                "(UTC+03:00) Asia/Aden",
                "(UTC+05:00) Asia/Almaty",
                "(UTC+03:00) Asia/Amman",
                "(UTC+12:00) Asia/Anadyr",
                "(UTC+05:00) Asia/Aqtau",
                "(UTC+05:00) Asia/Aqtobe",
                "(UTC+05:00) Asia/Ashgabat",
                "(UTC+05:00) Asia/Ashkhabad",
                "(UTC+05:00) Asia/Atyrau",
                "(UTC+03:00) Asia/Baghdad",
                "(UTC+03:00) Asia/Bahrain",
                "(UTC+04:00) Asia/Baku",
                "(UTC+07:00) Asia/Bangkok",
                "(UTC+07:00) Asia/Barnaul",
                "(UTC+02:00) Asia/Beirut",
                "(UTC+06:00) Asia/Bishkek",
                "(UTC+08:00) Asia/Brunei",
                "(UTC+05:30) Asia/Calcutta",
                "(UTC+09:00) Asia/Chita",
                "(UTC+08:00) Asia/Choibalsan",
                "(UTC+08:00) Asia/Chongqing",
                "(UTC+08:00) Asia/Chungking",
                "(UTC+05:30) Asia/Colombo",
                "(UTC+06:00) Asia/Dacca",
                "(UTC+03:00) Asia/Damascus",
                "(UTC+06:00) Asia/Dhaka",
                "(UTC+09:00) Asia/Dili",
                "(UTC+04:00) Asia/Dubai",
                "(UTC+05:00) Asia/Dushanbe",
                "(UTC+02:00) Asia/Famagusta",
                "(UTC+02:00) Asia/Gaza",
                "(UTC+08:00) Asia/Harbin",
                "(UTC+02:00) Asia/Hebron",
                "(UTC+07:00) Asia/Ho_Chi_Minh",
                "(UTC+08:00) Asia/Hong_Kong",
                "(UTC+07:00) Asia/Hovd",
                "(UTC+08:00) Asia/Irkutsk",
                "(UTC+03:00) Asia/Istanbul",
                "(UTC+07:00) Asia/Jakarta",
                "(UTC+09:00) Asia/Jayapura",
                "(UTC+02:00) Asia/Jerusalem",
                "(UTC+04:30) Asia/Kabul",
                "(UTC+12:00) Asia/Kamchatka",
                "(UTC+05:00) Asia/Karachi",
                "(UTC+06:00) Asia/Kashgar",
                "(UTC+05:45) Asia/Kathmandu",
                "(UTC+05:45) Asia/Katmandu",
                "(UTC+09:00) Asia/Khandyga",
                "(UTC+05:30) Asia/Kolkata",
                "(UTC+07:00) Asia/Krasnoyarsk",
                "(UTC+08:00) Asia/Kuala_Lumpur",
                "(UTC+08:00) Asia/Kuching",
                "(UTC+03:00) Asia/Kuwait",
                "(UTC+08:00) Asia/Macao",
                "(UTC+08:00) Asia/Macau",
                "(UTC+11:00) Asia/Magadan",
                "(UTC+08:00) Asia/Makassar",
                "(UTC+08:00) Asia/Manila",
                "(UTC+04:00) Asia/Muscat",
                "(UTC+02:00) Asia/Nicosia",
                "(UTC+07:00) Asia/Novokuznetsk",
                "(UTC+07:00) Asia/Novosibirsk",
                "(UTC+06:00) Asia/Omsk",
                "(UTC+05:00) Asia/Oral",
                "(UTC+07:00) Asia/Phnom_Penh",
                "(UTC+07:00) Asia/Pontianak",
                "(UTC+09:00) Asia/Pyongyang",
                "(UTC+03:00) Asia/Qatar",
                "(UTC+05:00) Asia/Qostanay",
                "(UTC+05:00) Asia/Qyzylorda",
                "(UTC+06:30) Asia/Rangoon",
                "(UTC+03:00) Asia/Riyadh",
                "(UTC+07:00) Asia/Saigon",
                "(UTC+11:00) Asia/Sakhalin",
                "(UTC+05:00) Asia/Samarkand",
                "(UTC+09:00) Asia/Seoul",
                "(UTC+08:00) Asia/Shanghai",
                "(UTC+08:00) Asia/Singapore",
                "(UTC+11:00) Asia/Srednekolymsk",
                "(UTC+08:00) Asia/Taipei",
                "(UTC+05:00) Asia/Tashkent",
                "(UTC+04:00) Asia/Tbilisi",
                "(UTC+03:30) Asia/Tehran",
                "(UTC+02:00) Asia/Tel_Aviv",
                "(UTC+06:00) Asia/Thimbu",
                "(UTC+06:00) Asia/Thimphu",
                "(UTC+09:00) Asia/Tokyo",
                "(UTC+07:00) Asia/Tomsk",
                "(UTC+08:00) Asia/Ujung_Pandang",
                "(UTC+08:00) Asia/Ulaanbaatar",
                "(UTC+08:00) Asia/Ulan_Bator",
                "(UTC+06:00) Asia/Urumqi",
                "(UTC+10:00) Asia/Ust-Nera",
                "(UTC+07:00) Asia/Vientiane",
                "(UTC+10:00) Asia/Vladivostok",
                "(UTC+09:00) Asia/Yakutsk",
                "(UTC+06:30) Asia/Yangon",
                "(UTC+05:00) Asia/Yekaterinburg",
                "(UTC+04:00) Asia/Yerevan",
                "(UTC-01:00) Atlantic/Azores",
                "(UTC-04:00) Atlantic/Bermuda",
                "(UTC+00:00) Atlantic/Canary",
                "(UTC-01:00) Atlantic/Cape_Verde",
                "(UTC+00:00) Atlantic/Faeroe",
                "(UTC+00:00) Atlantic/Faroe",
                "(UTC+01:00) Atlantic/Jan_Mayen",
                "(UTC+00:00) Atlantic/Madeira",
                "(UTC+00:00) Atlantic/Reykjavik",
                "(UTC-02:00) Atlantic/South_Georgia",
                "(UTC+00:00) Atlantic/St_Helena",
                "(UTC-03:00) Atlantic/Stanley",
                "(UTC+11:00) Australia/ACT",
                "(UTC+10:30) Australia/Adelaide",
                "(UTC+10:00) Australia/Brisbane",
                "(UTC+10:30) Australia/Broken_Hill",
                "(UTC+11:00) Australia/Canberra",
                "(UTC+11:00) Australia/Currie",
                "(UTC+09:30) Australia/Darwin",
                "(UTC+08:45) Australia/Eucla",
                "(UTC+11:00) Australia/Hobart",
                "(UTC+11:00) Australia/LHI",
                "(UTC+10:00) Australia/Lindeman",
                "(UTC+11:00) Australia/Lord_Howe",
                "(UTC+11:00) Australia/Melbourne",
                "(UTC+11:00) Australia/NSW",
                "(UTC+09:30) Australia/North",
                "(UTC+08:00) Australia/Perth",
                "(UTC+10:00) Australia/Queensland",
                "(UTC+10:30) Australia/South",
                "(UTC+11:00) Australia/Sydney",
                "(UTC+11:00) Australia/Tasmania",
                "(UTC+11:00) Australia/Victoria",
                "(UTC+08:00) Australia/West",
                "(UTC+10:30) Australia/Yancowinna",
                "(UTC-05:00) Brazil/Acre",
                "(UTC-02:00) Brazil/DeNoronha",
                "(UTC-03:00) Brazil/East",
                "(UTC-04:00) Brazil/West",
                "(UTC+01:00) CET",
                "(UTC-06:00) CST6CDT",
                "(UTC-04:00) Canada/Atlantic",
                "(UTC-06:00) Canada/Central",
                "(UTC-05:00) Canada/Eastern",
                "(UTC-07:00) Canada/Mountain",
                "(UTC-03:30) Canada/Newfoundland",
                "(UTC-08:00) Canada/Pacific",
                "(UTC-06:00) Canada/Saskatchewan",
                "(UTC-07:00) Canada/Yukon",
                "(UTC-03:00) Chile/Continental",
                "(UTC-05:00) Chile/EasterIsland",
                "(UTC-05:00) Cuba",
                "(UTC+02:00) EET",
                "(UTC-05:00) EST",
                "(UTC-05:00) EST5EDT",
                "(UTC+02:00) Egypt",
                "(UTC+00:00) Eire",
                "(UTC+00:00) Etc/GMT",
                "(UTC+00:00) Etc/GMT+0",
                "(UTC-01:00) Etc/GMT+1",
                "(UTC-10:00) Etc/GMT+10",
                "(UTC-11:00) Etc/GMT+11",
                "(UTC-12:00) Etc/GMT+12",
                "(UTC-02:00) Etc/GMT+2",
                "(UTC-03:00) Etc/GMT+3",
                "(UTC-04:00) Etc/GMT+4",
                "(UTC-05:00) Etc/GMT+5",
                "(UTC-06:00) Etc/GMT+6",
                "(UTC-07:00) Etc/GMT+7",
                "(UTC-08:00) Etc/GMT+8",
                "(UTC-09:00) Etc/GMT+9",
                "(UTC+00:00) Etc/GMT-0",
                "(UTC+01:00) Etc/GMT-1",
                "(UTC+10:00) Etc/GMT-10",
                "(UTC+11:00) Etc/GMT-11",
                "(UTC+12:00) Etc/GMT-12",
                "(UTC+13:00) Etc/GMT-13",
                "(UTC+14:00) Etc/GMT-14",
                "(UTC+02:00) Etc/GMT-2",
                "(UTC+03:00) Etc/GMT-3",
                "(UTC+04:00) Etc/GMT-4",
                "(UTC+05:00) Etc/GMT-5",
                "(UTC+06:00) Etc/GMT-6",
                "(UTC+07:00) Etc/GMT-7",
                "(UTC+08:00) Etc/GMT-8",
                "(UTC+09:00) Etc/GMT-9",
                "(UTC+00:00) Etc/GMT0",
                "(UTC+00:00) Etc/Greenwich",
                "(UTC+00:00) Etc/UCT",
                "(UTC+00:00) Etc/UTC",
                "(UTC+00:00) Etc/Universal",
                "(UTC+00:00) Etc/Zulu",
                "(UTC+01:00) Europe/Amsterdam",
                "(UTC+01:00) Europe/Andorra",
                "(UTC+04:00) Europe/Astrakhan",
                "(UTC+02:00) Europe/Athens",
                "(UTC+00:00) Europe/Belfast",
                "(UTC+01:00) Europe/Belgrade",
                "(UTC+01:00) Europe/Berlin",
                "(UTC+01:00) Europe/Bratislava",
                "(UTC+01:00) Europe/Brussels",
                "(UTC+02:00) Europe/Bucharest",
                "(UTC+01:00) Europe/Budapest",
                "(UTC+01:00) Europe/Busingen",
                "(UTC+02:00) Europe/Chisinau",
                "(UTC+01:00) Europe/Copenhagen",
                "(UTC+00:00) Europe/Dublin",
                "(UTC+01:00) Europe/Gibraltar",
                "(UTC+00:00) Europe/Guernsey",
                "(UTC+02:00) Europe/Helsinki",
                "(UTC+00:00) Europe/Isle_of_Man",
                "(UTC+03:00) Europe/Istanbul",
                "(UTC+00:00) Europe/Jersey",
                "(UTC+02:00) Europe/Kaliningrad",
                "(UTC+02:00) Europe/Kiev",
                "(UTC+03:00) Europe/Kirov",
                "(UTC+02:00) Europe/Kyiv",
                "(UTC+00:00) Europe/Lisbon",
                "(UTC+01:00) Europe/Ljubljana",
                "(UTC+00:00) Europe/London",
                "(UTC+01:00) Europe/Luxembourg",
                "(UTC+01:00) Europe/Madrid",
                "(UTC+01:00) Europe/Malta",
                "(UTC+02:00) Europe/Mariehamn",
                "(UTC+03:00) Europe/Minsk",
                "(UTC+01:00) Europe/Monaco",
                "(UTC+03:00) Europe/Moscow",
                "(UTC+02:00) Europe/Nicosia",
                "(UTC+01:00) Europe/Oslo",
                "(UTC+01:00) Europe/Paris",
                "(UTC+01:00) Europe/Podgorica",
                "(UTC+01:00) Europe/Prague",
                "(UTC+02:00) Europe/Riga",
                "(UTC+01:00) Europe/Rome",
                "(UTC+04:00) Europe/Samara",
                "(UTC+01:00) Europe/San_Marino",
                "(UTC+01:00) Europe/Sarajevo",
                "(UTC+04:00) Europe/Saratov",
                "(UTC+03:00) Europe/Simferopol",
                "(UTC+01:00) Europe/Skopje",
                "(UTC+02:00) Europe/Sofia",
                "(UTC+01:00) Europe/Stockholm",
                "(UTC+02:00) Europe/Tallinn",
                "(UTC+01:00) Europe/Tirane",
                "(UTC+02:00) Europe/Tiraspol",
                "(UTC+04:00) Europe/Ulyanovsk",
                "(UTC+02:00) Europe/Uzhgorod",
                "(UTC+01:00) Europe/Vaduz",
                "(UTC+01:00) Europe/Vatican",
                "(UTC+01:00) Europe/Vienna",
                "(UTC+02:00) Europe/Vilnius",
                "(UTC+03:00) Europe/Volgograd",
                "(UTC+01:00) Europe/Warsaw",
                "(UTC+01:00) Europe/Zagreb",
                "(UTC+02:00) Europe/Zaporozhye",
                "(UTC+01:00) Europe/Zurich",
                "(UTC+00:00) GB",
                "(UTC+00:00) GB-Eire",
                "(UTC+00:00) GMT",
                "(UTC+00:00) GMT+0",
                "(UTC+00:00) GMT-0",
                "(UTC+00:00) GMT0",
                "(UTC+00:00) Greenwich",
                "(UTC-10:00) HST",
                "(UTC+08:00) Hongkong",
                "(UTC+00:00) Iceland",
                "(UTC+03:00) Indian/Antananarivo",
                "(UTC+06:00) Indian/Chagos",
                "(UTC+07:00) Indian/Christmas",
                "(UTC+06:30) Indian/Cocos",
                "(UTC+03:00) Indian/Comoro",
                "(UTC+05:00) Indian/Kerguelen",
                "(UTC+04:00) Indian/Mahe",
                "(UTC+05:00) Indian/Maldives",
                "(UTC+04:00) Indian/Mauritius",
                "(UTC+03:00) Indian/Mayotte",
                "(UTC+04:00) Indian/Reunion",
                "(UTC+03:30) Iran",
                "(UTC+02:00) Israel",
                "(UTC-05:00) Jamaica",
                "(UTC+09:00) Japan",
                "(UTC+12:00) Kwajalein",
                "(UTC+02:00) Libya",
                "(UTC+01:00) MET",
                "(UTC-07:00) MST",
                "(UTC-07:00) MST7MDT",
                "(UTC-08:00) Mexico/BajaNorte",
                "(UTC-07:00) Mexico/BajaSur",
                "(UTC-06:00) Mexico/General",
                "(UTC+13:00) NZ",
                "(UTC+13:45) NZ-CHAT",
                "(UTC-07:00) Navajo",
                "(UTC+08:00) PRC",
                "(UTC-08:00) PST8PDT",
                "(UTC+13:00) Pacific/Apia",
                "(UTC+13:00) Pacific/Auckland",
                "(UTC+11:00) Pacific/Bougainville",
                "(UTC+13:45) Pacific/Chatham",
                "(UTC+10:00) Pacific/Chuuk",
                "(UTC-05:00) Pacific/Easter",
                "(UTC+11:00) Pacific/Efate",
                "(UTC+13:00) Pacific/Enderbury",
                "(UTC+13:00) Pacific/Fakaofo",
                "(UTC+12:00) Pacific/Fiji",
                "(UTC+12:00) Pacific/Funafuti",
                "(UTC-06:00) Pacific/Galapagos",
                "(UTC-09:00) Pacific/Gambier",
                "(UTC+11:00) Pacific/Guadalcanal",
                "(UTC+10:00) Pacific/Guam",
                "(UTC-10:00) Pacific/Honolulu",
                "(UTC-10:00) Pacific/Johnston",
                "(UTC+13:00) Pacific/Kanton",
                "(UTC+14:00) Pacific/Kiritimati",
                "(UTC+11:00) Pacific/Kosrae",
                "(UTC+12:00) Pacific/Kwajalein",
                "(UTC+12:00) Pacific/Majuro",
                "(UTC-09:30) Pacific/Marquesas",
                "(UTC-11:00) Pacific/Midway",
                "(UTC+12:00) Pacific/Nauru",
                "(UTC-11:00) Pacific/Niue",
                "(UTC+12:00) Pacific/Norfolk",
                "(UTC+11:00) Pacific/Noumea",
                "(UTC-11:00) Pacific/Pago_Pago",
                "(UTC+09:00) Pacific/Palau",
                "(UTC-08:00) Pacific/Pitcairn",
                "(UTC+11:00) Pacific/Pohnpei",
                "(UTC+11:00) Pacific/Ponape",
                "(UTC+10:00) Pacific/Port_Moresby",
                "(UTC-10:00) Pacific/Rarotonga",
                "(UTC+10:00) Pacific/Saipan",
                "(UTC-11:00) Pacific/Samoa",
                "(UTC-10:00) Pacific/Tahiti",
                "(UTC+12:00) Pacific/Tarawa",
                "(UTC+13:00) Pacific/Tongatapu",
                "(UTC+10:00) Pacific/Truk",
                "(UTC+12:00) Pacific/Wake",
                "(UTC+12:00) Pacific/Wallis",
                "(UTC+10:00) Pacific/Yap",
                "(UTC+01:00) Poland",
                "(UTC+00:00) Portugal",
                "(UTC+08:00) ROC",
                "(UTC+09:00) ROK",
                "(UTC+08:00) Singapore",
                "(UTC+03:00) Turkey",
                "(UTC+00:00) UCT",
                "(UTC-09:00) US/Alaska",
                "(UTC-10:00) US/Aleutian",
                "(UTC-07:00) US/Arizona",
                "(UTC-06:00) US/Central",
                "(UTC-05:00) US/East-Indiana",
                "(UTC-05:00) US/Eastern",
                "(UTC-10:00) US/Hawaii",
                "(UTC-06:00) US/Indiana-Starke",
                "(UTC-05:00) US/Michigan",
                "(UTC-07:00) US/Mountain",
                "(UTC-08:00) US/Pacific",
                "(UTC-11:00) US/Samoa",
                "(UTC+00:00) UTC",
                "(UTC+00:00) Universal",
                "(UTC+03:00) W-SU",
                "(UTC+00:00) WET",
                "(UTC+00:00) Zulu");
        for (String timeZone : allTimeZones) {
            // System.out.println(timeZone);
            Assert.assertTrue(verifyTheListReportPage.TimezoneComparison(timeZone));
            logger.info("Successfully change the time zone");
        }
    }











    //verify the assign and reassign of reviewer on list report page
    @Test(priority = 17 , enabled = false)
    public void verifyTheAssignReviewer() throws InterruptedException {
        Assert.assertEquals(verifyTheListReportPage.assignreport(),props.getProperty("reviewer1"));
        logger.info("Functionality of assign is working ");
    }

    //verify the assign and reassign of reviewer on list report page
    @Test(priority = 18 , enabled = true)
    public void verifyTheReassignReviewer() throws InterruptedException {
        Assert.assertEquals(verifyTheListReportPage.reassignreport(),props.getProperty("reviewer2"));
        logger.info("Functionality of assign is working ");
    }













}
