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
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

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

  public static void main(String args[]) {
    String filename = args[0];
    Main main = new Main();

    main.parseData(filename);
  }

  public void parseData(String filename) {
    Scanner s;InputStream is = Main.class.getResourceAsStream(filename); // aghh
    try {
      
      s = new Scanner(is); // this doesn't work :(
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    videoAmt = s.nextInt(); 
    endpointAmt = s.nextInt(); 
    requestAmt = s.nextInt(); 
    cacheAmt = s.nextInt(); 
    cacheCapacity = s.nextInt();

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
      requests.add(r);
    }
    s.close();
  }
}
