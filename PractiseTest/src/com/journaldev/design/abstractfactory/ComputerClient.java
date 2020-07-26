package com.journaldev.design.abstractfactory;

public class ComputerClient {

	public static void main(String[] args) {
		
		Computer pc = new ComputerFactory(new PCFactory("2 GB","500 GB","2.4 GHz")).createComputer();
		
		Computer server = new ComputerFactory(new ServerFactory("2 GB","500 GB","2.4 GHz")).createComputer();
		
		Computer laptop = new ComputerFactory(new LaptopFactory()).createComputer();
	}

}
