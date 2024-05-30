public class DriverStrategyImplimentor {
    public static DriverStrategy chooseStrategy(String strategy) {
        switch (strategy.toLowerCase()) {
            case "chrome":
                return new Chrome();
            case "firefox":
                return new Firefox();
            case "phantomjs":
                return new PhantomJs();
            default:
                return null;
        }
    }
}
