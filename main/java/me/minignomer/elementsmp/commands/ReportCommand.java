package me.minignomer.elementsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("§eTo report a §lPLAYER:§r§9§n https://docs.google.com/forms/d/e/1FAIpQLSfwFcPT9xWq2HGJuk_GAx56I8SojWJ7XwYyEIMv6CLpyoO1eA/viewform");
        sender.sendMessage("§eTo report a §lBUG:§r§9§n https://docs.google.com/forms/d/e/1FAIpQLScrpww-DOvwuKaNdQJNSN59PjCMJt05aX_qEOJS1GmQ8T4B4g/viewform");
        return true;
    }
}
