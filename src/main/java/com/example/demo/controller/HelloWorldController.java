package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("helloWorld")
public class HelloWorldController {

  HashMap<Integer, String> cache = new HashMap<>();

  @GetMapping("/hello")
  public CompletionStage<String> getHello(){
    return CompletableFuture.completedFuture("hello, world");
  }

  @GetMapping("/hello2")
  public String getHello2(){
    return "hello, world2";
  }

  @GetMapping("/error")
  public String getError(){
    return "error, world";
  }

  @GetMapping("/v1/isPrime/{n}")
  public String isPrime(@PathVariable Integer n){
    //check if n is prime number
    System.out.println(" isPrime request received for "+n);
    boolean response = isItPrime(n);
    if(response){
      return n+" is prime";
    }else{
      return n+" is not prime";
    }

  }
  @GetMapping("/v2/isPrime/{n}")
  public String isPrime2(@PathVariable Integer n){
    //check if n is prime number
    System.out.println(" isPrime request received for "+n);
    boolean response = isItPrime2(n);
    if(response){
      return n+" is prime";
    }else{
      return n+" is not prime";
    }

  }

  @GetMapping("/v3/isPrime/{n}")
  public String isPrime3(@PathVariable Integer n){
    //check if n is prime number
    System.out.println(" isPrime request received for "+n);
    if(cache.containsKey(n))return cache.get(n);
    else {
      boolean response = isItPrime2(n);
      if (response) {
        cache.put(n, n + " is prime");
        return n + " is prime";
      } else {
        cache.put(n, n + " is not prime");
        return n + " is not prime";
      }
    }

  }

  private boolean isItPrime(int n){//primality check on n
    if(n<=1)return false;
    for(int i=2;i<n;++i){//O(N)
      if (n%i==0)return false;
    }
    return true;
  }

  private boolean isItPrime2(int n){//primality check on n
    if(n<=1 || n%2==0)return false;
    int i=2;
    while(i*i<=n){//O(sqrt(N))
      if(n%i==0)return false;
      i+=1;
    }
    return true;
  }

//right we have an api to check if n is prime number
  //goal is to optimise this api i.e reduce the processing time at backend
  //use sieve

  //we can use caching, to improve the latency

}
