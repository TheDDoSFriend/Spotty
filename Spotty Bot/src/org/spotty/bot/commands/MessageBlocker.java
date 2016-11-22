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

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class MessageBlocker implements Command {

	File file = new File("message_bans.txt");

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		if (event.getMessage().getMentionedUsers().size() == 0) {
			event.getTextChannel().sendMessage("Please mention a user you would like to ban from messaging.");
		} else {
			try {
				FileUtils.writeStringToFile(file,
						event.getGuild().getId() + ":" + event.getMessage().getMentionedUsers().get(0) + "\n\n",
						"UTF-8", true);
			} catch (IOException e) {
				event.getTextChannel().sendMessage("Failed to ban user from messaging.");
				e.printStackTrace();
			}
		}
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
