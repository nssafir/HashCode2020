import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Endpoint {
  public List<Request> requests;
  public Map<Cache, int> connections;
  public int identifier;
  
  public Endpoint(int identifier) {
    requests = new ArrayList<>();
    connections = new HashMap<>();
    this.identifier = identifier;
  }

  public void addRequest(Request r) {
    requests.add(r);
  }
  
  public void addConnection(Cache c, int latency) {
    connections.put(c, latency);
}
