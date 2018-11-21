package eng.it.loatool.var.request;

import eng.it.util.PropertyManager;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class VARInstance {


    public static void createInstance(String body) throws IOException {
    	String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
        final String uri = BASE_URL + "/v2/orion/assetbymodel";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(body,headers);
        String answer = restTemplate.postForObject(uri, entity, String.class);
        System.out.println(answer);


    }

    public static void updateInstance(String body) throws IOException {
    	String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
        final String uri = BASE_URL + "/orion/assetbymodel";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(body,headers);
        restTemplate.put(uri,entity);

    }

    public static String getInstance(String modelName) throws IOException {
    	String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
        final String uri = BASE_URL + "/models/" + modelName;

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        return result;


    }

}
