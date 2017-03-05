package app;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "indexSearch")
    public void receiveMessage(Map<String, String> map) {
    	Listener listener = new ListenerImpl();
		Dispatcher.instancia().adicionar(listener);
		Map<Integer, Map<String, String>> mapResponse = new HashMap<>();
		mapResponse = Dispatcher.instancia().DispararEvento(new Buscador(map.get("word")));
        QueueMessage.add(mapResponse);
    }

}