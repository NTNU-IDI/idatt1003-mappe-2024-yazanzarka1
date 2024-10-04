package org.ntnu.application.commands;

import org.ntnu.console.Command;
import org.ntnu.console.CommandRegistry;

public class SwitchMenuCommand implements Command {

	private final String targetMenu;
	private final CommandRegistry commandRegistry;
	public SwitchMenuCommand(String targetMenu, CommandRegistry commandRegistry) {
		this.targetMenu = targetMenu;
		this.commandRegistry = commandRegistry;
	}

	@Override
	public Boolean execute() {
		commandRegistry.switchContext(targetMenu);
		return false;
	}

	@Override
	public String getDescription() {
		return String.format("Go to %s", targetMenu );
	}
}
