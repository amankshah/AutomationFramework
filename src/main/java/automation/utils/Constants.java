package automation.utils;

public class Constants {
    public static final String PROP_FILE_NAME = "framework.properties";
    public static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "property file " + PROP_FILE_NAME + " not found in the classpath";

    public static final String CHROME = "chrome";
    public static final String EDGE = "edge";
    public static final String FIREFOX = "firefox";
    public static final String PHANTOMJS = "phantomjs";

    public static final long EXPLICIT_WAIT_TIME = 10;

    public static final String BROWSER = "browser";
    public static final String SCREENSHOT_FOLDER = "screenshots\\";

    //Project Constants
    //Homepage Url
    public static final String URL = "https://bitheap.tech/";

    //Homepage Elements
        //Login Page
            public static final String USERNAME="username";
            public static final String PASSWORD="password";
            public static final String DISPLAY_NAME ="display_name";

        //Shop Page
            public static final long CART_QUANTITY=1;

        //Checkout Page

            //Address Details
                public static final String FIRST_NAME="AMAN";
                public static final String LAST_NAME="SHAH";
                public static final String COMPANY="Bitheap";
                public static final String ADDRESS="123 Main Street";
                public static final String ADDRESS_2="Suite 100";
                public static final String CITY="Chandigarh";
                public static final String STATE="Chandigarh";
                public static final String POSTCODE="161001";
                public static final String COUNTRY="India";
                public static final String EMAIL="testtopro@gmail.com";
                public static final String PHONE="9999999999";

            //Order Details

            public static final String ORDER_STATUS="Order received";



}
