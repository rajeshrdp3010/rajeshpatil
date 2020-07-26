package com.journaldev.design.abstractfactory;

public class ComputerFactory {
	private ComputerAbstractFactory caf = null;
    public ComputerFactory(ComputerAbstractFactory caf) {
        this.caf = caf;
    }
	public Computer createComputer() {
		return caf.createComputer();
	}
}
