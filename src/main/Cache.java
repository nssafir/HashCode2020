import java.util.ArrayList;
import java.util.List;

public class Cache {

  public int memoryRemaining;
  public List<Video> videos;
  public int identifier;

  public Cache(int memory, int identifier) {
    memoryRemaining = memory;
    videos = new ArrayList<>();
    this.identifier = identifier;
  }

  public void addVideo(Video v) {
    videos.add(v);
    memoryRemaining -= v.size;
  }
}
