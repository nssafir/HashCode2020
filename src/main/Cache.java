import java.util.ArrayList;
import java.util.List;

public class Cache {

  public int memoryRemaining;
  public List<Video> videos;

  public Cache(int x) {
    memoryRemaining = x;
    videos = new ArrayList<>();
  }

  public void addVideo(Video v) {
    videos.add(v);
    memoryRemaining -= v.size;
  }
}
