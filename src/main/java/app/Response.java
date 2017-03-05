package app;

import java.util.Map;

public class Response {

    private Map<Integer, Map<String, String>> content;

    public Response() {
    }

    public Response(Map<Integer, Map<String, String>> content) {
        this.content = content;
    }

    public Map<Integer, Map<String, String>> getContent() {
        return content;
    }

}