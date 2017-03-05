package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Dispatcher {
	
	private static Dispatcher instance;
    private Set<Listener> listeners;
 
    private Dispatcher() {
        this.listeners = new HashSet<Listener>();
    }
 
    public static Dispatcher instancia() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }
 
    public void adicionar(Listener listener) {
        this.listeners.add(listener);
    }
 
    public Map<Integer, Map<String, String>> DispararEvento(Buscador busca) {
        Iterator<Listener> iterator = listeners.iterator();
        Map<Integer, Map<String, String>> map = new HashMap<>();
        int i = 0;
        while (iterator.hasNext()) {
            Listener listener = (Listener) iterator.next();
            map.put(i++, listener.buscaSolicitada(busca));
        }
        return map;
    }

}
