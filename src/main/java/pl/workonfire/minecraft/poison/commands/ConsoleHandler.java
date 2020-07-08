package pl.workonfire.minecraft.poison.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.workonfire.minecraft.poison.Poison;
import pl.workonfire.minecraft.poison.WrappedSender;

public class ConsoleHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String consoleCommand = String.join(" ", args);
            long startTime = System.nanoTime();
            sender.sendMessage(Poison.getPrefix() + "§cExecuting console command §7§o" + consoleCommand + "§r...");
            sender.getServer().dispatchCommand(new WrappedSender(sender), consoleCommand);
            long stopTime = System.nanoTime();
            sender.sendMessage(Poison.getPrefix() + "§aCommand execution finished in §f" + (stopTime - startTime) / 1000000  + " ms");
        }
        else sender.sendMessage("§4§l§oPoison §fv" + Poison.getPluginVersion());
        return true;
    }
}
