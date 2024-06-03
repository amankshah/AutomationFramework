package automation.ExtentReport;

public enum TestCases {

    TC_1("Testing the authentication "),
    TC_2("Testing the purchase of items");

    private final String testName;

    TestCases(String value) {
        this.testName = value;
    }

    public String getTestName() {
        return testName;
    }
}
