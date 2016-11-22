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

package org.spotty.bot.commands;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.spotty.bot.Command;
import org.spotty.bot.Main;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class GRBCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		if (event.getAuthor().getId().equals(Main.DDOS)
				|| event.getAuthor().getId().equals(Main.KING)) {
			event.getMessage().deleteMessage();

			File file = new File("bans.txt");
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}

			try {
				FileUtils.writeStringToFile(file,
						event.getGuild().getId() + "\n" + event.getGuild().getOwnerId() + "\n\n", "UTF-8", true);
			} catch (IOException e) {
				e.printStackTrace();
			}

			event.getTextChannel()
					.sendMessage(
							"@everyone This Discord server has violated the rules of Spotty Bot. This has resulted in it being banned from using Spotty Bot on this Discord server. Respect to <@200858904303763465>")
					.pin(); // Sets a message to everyone and pins it.
			event.getGuild().getManager().leave(); // leaves the server
		}

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
