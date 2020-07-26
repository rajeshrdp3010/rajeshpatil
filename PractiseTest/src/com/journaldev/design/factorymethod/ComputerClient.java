package com.journaldev.design.factorymethod;


public class ComputerClient {

	public static void main(String[] args) {
		String computerType  = args[0];
		if ("laptop".equals(computerType)) {
			ComputerAbstractFactory laptopFactory  = new LaptopFactory();
			Computer laptop = laptopFactory.createComputer();
		} else if ("pc".equals(computerType)) {
			ComputerAbstractFactory pcFactory  = new PCFactory("32GB", "1TB", "AMD Ryzen 7 2700X");
			Computer pc = pcFactory.createComputer();
		}
		
	}

}
