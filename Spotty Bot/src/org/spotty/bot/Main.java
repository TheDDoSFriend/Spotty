/** 
 * Copyright 2016 TheDDoSFriend
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
*/

package org.spotty.bot;

import java.util.HashMap;
import org.spotty.bot.commands.BanCommand;
import org.spotty.bot.commands.ByeCommand;
import org.spotty.bot.commands.CatCommand;
import org.spotty.bot.commands.GRBCommand;
import org.spotty.bot.commands.HelpCommand;
import org.spotty.bot.commands.HiCommand;
import org.spotty.bot.commands.IdCommand;
import org.spotty.bot.commands.InviteCommand;
import org.spotty.bot.commands.LMGTFYCommand;
import org.spotty.bot.commands.MathCommand;
import org.spotty.bot.commands.NameCommand;
import org.spotty.bot.commands.OwnerCommand;
import org.spotty.bot.commands.PinCommand;
import org.spotty.bot.commands.PurgeCommand;
import org.spotty.bot.commands.RIPCommand;
import org.spotty.bot.commands.ServerInfoCommand;
import org.spotty.bot.commands.music.PlayCommand;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

public class Main {

	private static final String DISCORD_TOKEN = "MjI2NTM5MzMxMzQ5Nzc0MzM3.Cr5FMA.Cm5f67Sx-qzq4JD7yFXDY-aYta0";
	public static final String DDOS = "200858904303763465";
	public static final String KING = "204572389307187200";

	private static JDA jda;
	public static final CommandParser parser = new CommandParser();

	public static HashMap<String, Command> commands = new HashMap<String, Command>();

	public static void main(String[] args) {
		try {
			jda = new JDABuilder().setBulkDeleteSplittingEnabled(false).addListener(new BotListener())
					.setBotToken(DISCORD_TOKEN).buildBlocking();
			jda.setAutoReconnect(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		commands.put("help", new HelpCommand());
		commands.put("rip", new RIPCommand());
		commands.put("name", new NameCommand());
		commands.put("purge", new PurgeCommand());
		commands.put("pin", new PinCommand());
		commands.put("lmgtfy", new LMGTFYCommand());
		commands.put("hi", new HiCommand());
		commands.put("bye", new ByeCommand());
		commands.put("cat", new CatCommand());
		commands.put("invite", new InviteCommand());
		commands.put("owner", new OwnerCommand());
		commands.put("grb", new GRBCommand());
		commands.put("play", new PlayCommand());
		commands.put("serverinfo", new ServerInfoCommand()); //
		commands.put("ban", new BanCommand()); //
		commands.put("math", new MathCommand()); //
		commands.put("id", new IdCommand()); //

	}

	public static void handleCommand(CommandParser.CommandContainer cmd) {
		if (commands.containsKey(cmd.invoke)) {
			boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

			if (safe) {
				commands.get(cmd.invoke).action(cmd.args, cmd.event);
				commands.get(cmd.invoke).executed(safe, cmd.event);
			} else {
				commands.get(cmd.invoke).executed(safe, cmd.event);
			}
		}
	}

}