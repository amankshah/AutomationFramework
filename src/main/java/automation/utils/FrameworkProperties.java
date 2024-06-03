package automation.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class FrameworkProperties {
    private String result="";
    private InputStream inputStream;

    public String getProperty(String key){



        try {
            Properties properties = new Properties();
            String propertiesFileName = Constants.PROP_FILE_NAME;

            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

            if (inputStream != null){
                properties.load(inputStream);
            }else{
                throw new FileNotFoundException(Constants.FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            }

        this.result= properties.getProperty(key);


        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return result;
    }
}
