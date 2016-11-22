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

import org.spotty.bot.Command;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class NameCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		String nick;
		boolean nonick = false;
		nick = event.getAuthorNick();
		try {
			if (nick.equals("null")) {
				nonick = true;
			}
		} catch (Exception e) {
			nonick = true;
		}
		if (!nonick) {
			event.getTextChannel()
					.sendMessage("Your username is: " + event.getAuthor().getUsername() + ", and your nickname is " + nick + "!");
		} else {
			event.getTextChannel()
					.sendMessage("Your username is: " + event.getAuthor().getUsername() + ", and you do not have a nickname!");
		}
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
