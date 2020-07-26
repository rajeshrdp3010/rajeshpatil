package com.journaldev.design.factorymethod;

public abstract class ComputerAbstractFactory {
	protected abstract Computer createComputer();
	
	public void assemble() {
		Computer computer = createComputer();
	}
}
