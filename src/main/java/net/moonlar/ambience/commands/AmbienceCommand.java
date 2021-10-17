package net.moonlar.ambience.commands;

import net.moonlar.ambience.MoonlarAmbience;
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

    if(args.length == 0) {
      plugin.sendVersion(sender);
      return true;
    }

    return true;
  }


}
