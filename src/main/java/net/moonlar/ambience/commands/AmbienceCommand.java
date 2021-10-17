package net.moonlar.ambience.commands;

import net.moonlar.ambience.MoonlarAmbience;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AmbienceCommand implements CommandExecutor {

  private final MoonlarAmbience plugin;

  public AmbienceCommand(MoonlarAmbience plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if(args.length == 0 || !sender.hasPermission("moonlar.ambience.admin")) {
      plugin.sendVersion(sender);
      return true;
    }

    if(args[0].equalsIgnoreCase("reload")) {
      plugin.reload();
      sender.sendMessage(ChatColor.GREEN + "Configurações recarregadas.");
      return true;
    }

    sender.sendMessage(ChatColor.RED + "Sub-comando inválido!");
    return true;
  }
}
