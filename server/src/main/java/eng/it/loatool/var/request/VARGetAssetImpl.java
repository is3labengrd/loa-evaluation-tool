package eng.it.loatool.var.request;

import eng.it.util.PropertyManager;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class VARGetAssetImpl {

    public static String getProductPlanningIntance (String modelName) throws IOException {
        PropertyManager prop = new PropertyManager();
        final String BASE_URL = prop.getPropValues("base.url");
        final String uri = BASE_URL + "/assets/"+ modelName+"/attributes/productionVolume";

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        return result;


    }
}

