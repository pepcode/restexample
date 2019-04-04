package org.pepcode.example.restexample;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class App extends Application {
	
	public App() {}
	
	@Override
	  public Set<Object> getSingletons() {
	    HashSet<Object> set = new HashSet<Object>();
	    set.add(new GreetingResource());
	    return set;
	  }
	

}
