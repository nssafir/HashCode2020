package main;

public class Request implements Comparable<Request>{
  public int identifier;
  public Video video;
  public int number;
  public Endpoint endpoint;

  public Request(int identifier, Video video, int number, Endpoint endpoint) {
    this.identifier = identifier;
    this.video = video;
    this.number = number;
    this.endpoint = endpoint;
  } 

  @Override 
  public int compareTo(Request o) {
    Integer number = this.number;
    Integer otherNumber = o.number;
    return number.compareTo(otherNumber);
  }
}
