package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties configFile;//declaring the configFile

    //static block makes the configuration (password, email, address, phone) or the Properties file loaded first thing
    //in the memory so when we execute any test those properties they will be already in the memory to be executed
    //that's why we need to put them in the static block like below because it is first thing or code to be executed.
    //we don't need to call this method because it will uploaded automatically in the memory before the main method.
    static {
        //first we need to know the path of the file (configuration.properties) so right click on it, copy path
        //reference, then Path From Content Root, click on it to copy it then paste it like below
        String path = "src/test/resources/configuration.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);//to load the file
            configFile = new Properties();//here we are instantiating the configFile object
            configFile.load(fileInputStream);//here we are uploading the file
            fileInputStream.close();//close the stream

        //those catches just requirement from the code above
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method will return the value of the key that was passed as a parameter from the property file
     * @param key the key from the property file
     * @return
     */
    //in this method you can pass the key which is (baseUrl) from the file (configuration.properties) and once the
    //configFile uploaded will get the the URL from file (configuration.properties)
    public static String getPropertyValue(String key){
        return configFile.getProperty(key);
    }


}
