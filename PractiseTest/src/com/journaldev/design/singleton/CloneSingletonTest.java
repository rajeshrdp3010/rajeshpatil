package com.journaldev.design.singleton;
// JAVA code to explain overcome 
// cloning issue with singleton
class SuperClass implements Cloneable {
	int i = 10;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

// Singleton class
class CloneSingleton extends SuperClass {
	// public instance initialized when loading the class
	public static CloneSingleton instance = new CloneSingleton();

	private CloneSingleton() {
		// private constructor
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return instance;
	}
}

public class CloneSingletonTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		CloneSingleton instance1 = CloneSingleton.instance;
		CloneSingleton instance2 = (CloneSingleton) instance1.clone();
		System.out.println("instance1 hashCode:- " + instance1.hashCode());
		System.out.println("instance2 hashCode:- " + instance2.hashCode());
	}
}
