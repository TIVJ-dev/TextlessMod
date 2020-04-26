package io.github.tivj.textless;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class ToggleCommand extends CommandBase {
    public static boolean textEnabled = true;

    @Override
    public String getCommandName() {
        return "texttoggle";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/texttoggle";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        textEnabled =! textEnabled;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
