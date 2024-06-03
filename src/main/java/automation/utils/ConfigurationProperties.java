package automation.utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("framework.properties")
public class ConfigurationProperties {

    @Value("${browser}")
    private String browser;

    @Value("${url}")
    private String url;
    @Value("${password}")
    private String password;

    @Value("${signinuser}")
    private String signInUser;

    public String getBrowser() {
        return browser;
    }

    public String getUrl() {
        return url;
    }



    public String getPassword() {
        return password;
    }


    public String getSignInUser() {
        return signInUser;
    }
}
