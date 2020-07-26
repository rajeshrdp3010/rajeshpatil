package com.journaldev.design.singleton;
// Java code to explain effect of Reflection
// on Singleton property
 
import java.lang.reflect.Constructor;
 
               
// Singleton class
class ConstructorSingleton 
{
	 // public instance initialized when loading the class
    public static ConstructorSingleton instance = new ConstructorSingleton();
     
    private ConstructorSingleton() 
    {
        // private constructor
    }
    
}
 
public class ConstructorSingletonTest 
{
 
    public static void main(String[] args)
    {
    	ConstructorSingleton instance1 = ConstructorSingleton.instance;
    	ConstructorSingleton instance2 = null;
        try
        {
            Constructor[] constructors = 
            		ConstructorSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) 
            {
                // Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instance2 = (ConstructorSingleton) constructor.newInstance();
                break;
            }
        }
     
        catch (Exception e) 
        {
            e.printStackTrace();
        }
         
    System.out.println("instance1.hashCode():- "
                                      + instance1.hashCode());
    System.out.println("instance2.hashCode():- "
                                      + instance2.hashCode());
    }
}