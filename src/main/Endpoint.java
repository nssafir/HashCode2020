import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Endpoint {
  public List<Request> requests;
  public Map<Cache, int> connections;
  
  public Endpoint() {
    requests = new ArrayList<>();
    connections = new HashMap<>();
  }

  public void addRequest(Request r) {
    requests.add(r);
  }
  
  public void addConnection(Cache c, int latency) {
    connections.put(c, latency);
}
