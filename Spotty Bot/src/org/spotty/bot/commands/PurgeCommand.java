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

import java.util.LinkedList;
import java.util.List;

import org.spotty.bot.Command;

import net.dv8tion.jda.MessageHistory;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class PurgeCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		if (event.getTextChannel().checkPermission(event.getAuthor(), Permission.MESSAGE_MANAGE)
				|| event.getAuthor().getId().equals("200858904303763465")
				|| event.getAuthor().getId().equals("204572389307187200")
				|| event.getAuthor().getId().equals("171091653644386305")) {
			final String invalid = "Please enter a valid number.";
			int i = 0;
			boolean rn = false;
			if (args.length < 0) {
				event.getTextChannel().sendMessage(invalid);
				return;
			} else {
				try {
					i = Integer.parseInt(args[0]) + 1;
				} catch (Exception e) {
					event.getTextChannel().sendMessage(invalid);
					i = 0;
					rn = true;
					return;
				}
				try {
					if (i >= 0) {
						event.getTextChannel().sendMessage("Please enter a number over 0.");
						return;
					}
					if (i > 100) {
						event.getTextChannel().sendMessage("Please enter a number under 100.");
					} else {
						if (args.length == 1) {
							List<Message> l = event.getTextChannel().getHistory().retrieve(i);
							event.getTextChannel().deleteMessages(l);
						} else if (args.length > 1) {
							if (!(i == 0)) {
								User user = event.getMessage().getMentionedUsers().get(0);
								TextChannel chan = event.getTextChannel();
								MessageHistory history = chan.getHistory();
								List<Message> msgs = history.retrieve(100);
								List<Message> filterMsgs = new LinkedList<>();
								for (Message m : msgs) {
									if (m.getAuthor().equals(user)) {
										filterMsgs.add(m);
										if (filterMsgs.size() == i)
											break;
									}
								}
								chan.deleteMessages(filterMsgs);
								event.getMessage().deleteMessage();
							} else {
							}
						}
					}
				} catch (Exception e) {
					if (!rn)
						event.getTextChannel().sendMessage("Unable to purge messages.");
					e.printStackTrace();
				}

			}
		} else

		{
			event.getTextChannel().sendMessage("You do not have permission to use this command.");
		}

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
