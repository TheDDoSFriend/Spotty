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

import net.dv8tion.jda.Permission;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class PinCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {

		if (event.getTextChannel().checkPermission(event.getAuthor(), Permission.MESSAGE_MANAGE)
				|| event.getAuthor().getId().equals("200858904303763465")
				|| event.getAuthor().getId().equals("204572389307187200")
				|| event.getAuthor().getId().equals("171091653644386305")) {

			if (args.length < 1) {
				event.getTextChannel().sendMessage("Please enter something to pin.");
			} else {
				StringBuilder builder = new StringBuilder();
				for (String s : args) {
					builder.append(s + " ");
				}
				event.getTextChannel().sendMessage(builder.toString()).pin();
			} // 7052300240
		} else {
			event.getTextChannel().sendMessage("You do not have permission to use this command.");
		}
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
