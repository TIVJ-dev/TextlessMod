package io.github.tivj.textless;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class ToggleCommand extends CommandBase {
    public static boolean textEnabled = true;

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    /**
     * Gets the name of the command
     */
    @Override
    public String getName() {
        return "texttoggle";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender
     */
    @Override
    public String getUsage(ICommandSender sender) {
        return "/texttoggle";
    }

    /**
     * Callback for when the command is executed
     *
     * @param server
     * @param sender
     * @param args
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        textEnabled =! textEnabled;
    }
}
