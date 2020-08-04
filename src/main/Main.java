// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package main;

import java.util.Scanner; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.List;
import java.util.Collections;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;



public final class Main {

  private int videoAmt;
  private int endpointAmt;
  private int requestAmt;
  private int cacheAmt;
  private int cacheCapacity;

  private ArrayList<Video> videos = new ArrayList<Video>();
  private ArrayList<Endpoint> endpoints = new ArrayList<Endpoint>();
  private ArrayList<Cache> caches = new ArrayList<Cache>();
  private ArrayList<Request> requests = new ArrayList<Request>();

  private Map<Cache, List<Video>> allCaches = new HashMap<>();

  public static void main(String args[]) {
    String filename = args[0];
    Main main = new Main();

    System.out.println("arg: " + filename); //test
    main.parseData(filename);
    System.out.println("End of parse function"); //test
    main.printObjects();
  }

  public void calculate() {
    // calculation function
    // sort requests descending by size (maybe put in priority queue - need to make request class comparable by #)
    PriorityQueue<Request> rQueue = new PriorityQueue<>(Collections.reverseOrder());
    for (Request r : requests) {
        Video v = r.video;
        Map<Cache, Integer> caches = r.endpoint.connections; // caches connected to the endpoint of the request
        // get the cache with the lowest latency and the space available for v
        Cache minCache = null;
        int minLatency = Integer.MAX_VALUE;
        for (Map.Entry<Cache, Integer> entry : caches.entrySet()) { 
            if (minLatency > entry.getValue() 
                && entry.getKey().memoryRemaining >= v.size) {
                minCache = entry.getKey();
            }
        }
        // add that video to the cache server   (decreasing its available size)
        minCache.addVideo(v);
        // add cache and video to allCaches
        List<Video> cacheVideos = 
                    allCaches.getOrDefault(minCache, new ArrayList<>());
        cacheVideos.add(v);
        allCaches.put(minCache, cacheVideos);         
    }
  }

  public void printObjects() {
    for (Video v : videos) {
      System.out.println("Video " + v.identifier);
      System.out.println("  Size: " + v.size);
    }
    for (Endpoint e : endpoints) {
      System.out.println("Endpoint " +  e.identifier);
      System.out.println("  Datacenter latency: " + e.datacenterLatency);
      System.out.print("  Requests for videos ");
      for (Request r : e.requests) {
        System.out.print(r.identifier);       
      }
      System.out.println("");
      System.out.print("  Cache/latency connections of: ");
      for (Cache c : e.connections.keySet()){
        System.out.print(c.identifier + "," + e.connections.get(c) + " ");
      }
      System.out.println("");
    }
    for (Cache c : caches) {
      System.out.println("Cache " +  c.identifier);
      System.out.println("  Memory Remaining : " + c.memoryRemaining);
      System.out.print("  Contains videos ");
      for (Video v : c.videos) {
        System.out.print(v.identifier + " ");
      }
      System.out.println("");
    }
    for (Request r : requests) {
      System.out.println("Request " +  r.identifier);
      System.out.println("  Video is " + r.video.identifier);
      System.out.println("  Number is " + r.number);
      System.out.println("  Endpoint is " + r.endpoint.identifier);
    }
  }

  public void parseData(String filename) {
    Scanner s;
    try {
      InputStream input = getClass().getResourceAsStream(filename);
      s = new Scanner(input); // this doesn't work :(
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
    videoAmt = s.nextInt(); 
    endpointAmt = s.nextInt(); 
    requestAmt = s.nextInt(); 
    cacheAmt = s.nextInt(); 
    cacheCapacity = s.nextInt();
    System.out.println(videoAmt+" "+endpointAmt+" "+requestAmt+" "+cacheAmt+" "+cacheCapacity); // test
    // Create caches.
    for (int identifier = 0; identifier < cacheAmt; identifier++) {
      caches.add(new Cache(cacheCapacity, identifier));
    }
    // Create videos.
    for(int i = 0; i < videoAmt; i++){
      videos.add(new Video(i, s.nextInt()));
    }
    // Create endpoints.
    for(int i = 0; i < endpointAmt; i++){
      int datacenterLatency = s.nextInt();
      Endpoint temp = new Endpoint(i, datacenterLatency);
      int cachesToEndpoint = s.nextInt();
      for (int j = 0; j < cachesToEndpoint; j++){
        Cache cacheToAdd = caches.get(s.nextInt());
        int cacheLatency = s.nextInt();
        temp.connections.put(cacheToAdd, cacheLatency);
      }
      endpoints.add(temp);
    }
    // Create requests.
    for (int i = 0; i < requestAmt; i++) {
      int videoNum = s.nextInt();
      int endpointNum = s.nextInt();
      int numRequests = s.nextInt();
      Video requestedVideo = videos.get(videoNum);
      Endpoint fromEndpoint = endpoints.get(endpointNum);
      Request r = new Request(i, requestedVideo, numRequests, fromEndpoint);
      fromEndpoint.addRequest(r);
      requests.add(r);
    }
    s.close();
  }
}
