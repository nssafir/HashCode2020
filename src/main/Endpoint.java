import java.util.ArrayList;
import java.util.List;

public class Endpoint {
  public List<Request> requests;
  
  public Endpoint() {
    requests = new ArrayList<>();
  }

  public void addRequest(Request r) {
    requests.add(r);
  }
}