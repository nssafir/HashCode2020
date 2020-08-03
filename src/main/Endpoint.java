import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Endpoint {
  public List<Request> requests;
  public Map<Cache, int> connections;
  public int identifier;
  public int datacenterLatency;
  
  public Endpoint(int identifier, int datacenterLatency) {
    requests = new ArrayList<>();
    connections = new HashMap<>();
    this.identifier = identifier;
    this.datacenterLatency = datacenterLatency;
  }

  public void addRequest(Request r) {
    requests.add(r);
  }
  
  public void addConnection(int latency, Cache c) {
    connections.put(c, latency);
  }

}
