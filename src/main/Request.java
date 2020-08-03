public class Request {
  public Video video;
  public int number;
  public Endpoint endpoint;

  public Request(Video video, int number, Endpoint endpoint) {
    this.video = video;
    this.number = number;
    this.endpoint = endpoint;
  } 
}
