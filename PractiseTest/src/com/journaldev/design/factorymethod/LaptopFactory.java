package com.journaldev.design.factorymethod;

public class LaptopFactory extends ComputerAbstractFactory {

	@Override
	public Computer createComputer() {
		return new Laptop();
	}

}
