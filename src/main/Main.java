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

import java.util.Scanner; 
import java.util.ArrayList; 

public final class Main {

  private final int videoAmt;
  private final int endpointAmt;
  private final int requestAmt;
  private final int cacheAmt;
  private final int cacheCapacity;

  public static void main(String args[]) {
    ArrayList<Video> videos = new ArrayList<Video>();
    ArrayList<Endpoint> endpoints = new ArrayList<Endpoint>();
    Scanner s = new Scanner(System.in); 
    videoAmt = s.nextInt(); 
    endpointAmt = s.nextInt(); 
    requestAmt = s.nextInt(); 
    cacheAmt = s.nextInt(); 
    cacheCapacity = s.nextInt(); 

    for(int i = 0; i < videoAmt; i++){
      videos.add(new Video(i, s.nextInt()))
    }

    int endpointCounter = 0;
    for(int i = 0; i < endpointAmt; i++){
      int datacenterLatency = s.nextInt();
      Endpoint temp = new Endpoint(endpointCounter, datacenterLatency);
      int cache = s.nextInt();
      if(endpointCounter < endpointAmt){
        endpointCounter++;
      }

      for(int i = 0; i < cache; i++){
        
      }
    }


    
  }
}