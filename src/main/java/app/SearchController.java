package app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

	@Autowired
	private ApplicationContext appContext;

    @MessageMapping("/search")
    @SendTo("/find/text")
    public Response search(Query query) throws Exception {
    	System.out.println("<<<<<<<<<<<<<<<<---------------->>>>>>>>>>>>>>");
    	JmsTemplate jmsTemplate = appContext.getBean(JmsTemplate.class);
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("word", query.getWord());
    	jmsTemplate.convertAndSend("indexSearch", map);
    	ExecutorService service = null;
    	Future<Response> response = null;
    	try {
    		service = Executors.newSingleThreadExecutor();
    		response = service.submit(() -> {
    			Response res = null;
    			do {
    				res = QueueMessage.get();
    			} while(res == null);
    			Thread.sleep(2000);
    			System.out.println("<<<<<<<<<<<<<<<<---------------->>>>>>>>>>>>>>");
    			return res;
    		});
    	} catch(Exception e){
    		e.printStackTrace();
    	} finally {
    		if(service != null) service.shutdown();
    	}
    	while(!response.isDone());
    		
        return response.get();
    }

}