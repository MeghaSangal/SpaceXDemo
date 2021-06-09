package test.api.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import test.api.model.Space;
import static test.testSuiteBase.SuiteBase.*;

public class SpaceClient {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        mapper.configure(JsonParser.Feature.ALLOW_MISSING_VALUES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static Response getLatestLaunchDetails(String key) {
        Response response = null;
        try {
            response = RestHelper.getRes(Endpoints.BASE_PATH + key);
        } catch (Exception e) {
            Logger.error("Unable to fetch response in getLatestLaunchDetails() :" + e.getMessage(), e);
        }
        return response;
    }
    public static Space getSpaceData(Response response) {
        Space space = null;
        try {
            Logger.info("Mapping the response to the relevant class and fetching required values.");
            space = mapper.readValue(response.getBody().asString(), Space.class);

        } catch (Exception e) {
            Logger.error("Unable to parse Space response:" + e.getMessage(), e);
        }
        return space;
    }
}
