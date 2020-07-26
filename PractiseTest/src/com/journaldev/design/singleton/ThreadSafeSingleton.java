package com.journaldev.design.singleton;

public class ThreadSafeSingleton {

	 // public instance initialized when loading the class
		private volatile static ThreadSafeSingleton instance;
	     
	    private ThreadSafeSingleton() 
	    {
	        // private constructor
	    }
	    
	    public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){
	        if(instance == null){
	            synchronized (ThreadSafeSingleton.class) {
	                if(instance == null){
	                    instance = new ThreadSafeSingleton();
	                }
	            }
	        }
	        return instance;
	    }
}
