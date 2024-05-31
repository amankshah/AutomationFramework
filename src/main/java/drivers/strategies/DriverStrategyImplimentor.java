package drivers.strategies;

import utils.Constants;

import java.util.Locale;

public class DriverStrategyImplimentor {
    public static DriverStrategy chooseStrategy(String strategy) {
        switch (strategy.toLowerCase()) {
            case Constants.FIREFOX:
                return new Firefox();
            case Constants.CHROME:
                return new Chrome();
            case Constants.EDGE:
                return new Edge();
            case Constants.PHANTOMJS:
                return new PhantomJs();
            default:
                return null ;
        }
    }
}
