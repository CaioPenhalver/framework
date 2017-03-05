package app;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class QueueMessage {

	private static Queue<Response> responses = new LinkedList<>();
	
	public static void add(Map<Integer, Map<String, String>> map){
		responses.add(new Response(map));
	}
	
	public static Response get(){
		return responses.poll();
	}
}
