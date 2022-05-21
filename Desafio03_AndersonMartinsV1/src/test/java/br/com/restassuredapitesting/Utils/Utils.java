package br.com.restassuredapitesting.Utils;

public class Utils {

    public static String getSchemaBasePath(String pack, String nameSchema) {
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitesting/tests/"
                + pack
                + "/schema/"
                + nameSchema
                + ".json";
    }

}
