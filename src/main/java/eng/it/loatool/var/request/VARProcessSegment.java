package eng.it.loatool.var.request;

import eng.it.util.PropertyManager;
import eng.it.util.SARProperty;
import org.springframework.web.client.RestTemplate;




public class VARProcessSegment {
	
	public static String getProcessSegment()
	{
		final String BASE_URL = PropertyManager.getProperty(SARProperty.BASE_URL);
	    final String uri = BASE_URL + "/assets?className=ProcessSegment&retrieveForChildren=true";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	     
	    return result;
	}
	

}
