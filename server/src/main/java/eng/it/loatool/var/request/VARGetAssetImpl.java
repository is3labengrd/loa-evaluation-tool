package eng.it.loatool.var.request;

import eng.it.util.PropertyManager;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class VARGetAssetImpl {

    public static String getProductPlanningIntance (String modelName) throws IOException {
    	String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
        final String uri = BASE_URL + "/assets/"+ modelName+"/attributes/productionVolume";

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        return result;


    }
}

