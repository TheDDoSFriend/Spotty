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

import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class MathCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		TextChannel chan = event.getTextChannel();
		try {
			if (args.length == 0) {
				chan.sendMessage("Please input your question.");
				return;
			} else {
				boolean bullshitfuck1 = false;
				boolean bullshitfuck2 = false;
				boolean bullshitfuck3 = false;
				boolean bullshitfuck4 = false;
				boolean bullshitfuck5 = false;

				if (args[0].split("+").length == 2) {
					bullshitfuck1 = true;
					// Adding
					try {
						long res1 = Long.parseLong(args[0]);
						long res2 = Long.parseLong(args[1]);
						long result = res1 + res2;
						event.getTextChannel().sendMessage("Answer: " + result);
					} catch (Exception e) {
						event.getTextChannel()
								.sendMessage("Failed to complete command. CAUSE YOU INPUTED IT WRONG YOU SPOONERON");
						e.printStackTrace();
						return;
					}
				} else if (args[0].split("-").length == 2) {
					bullshitfuck2 = true;
					// Removing

				} else if (args[0].split("*").length == 2) {
					bullshitfuck3 = true;
					// Multiplication

				} else if (args[0].split("/").length == 2) {
					bullshitfuck4 = true;
					// Division

				} else if (args[0].split("%").length == 2) {
					bullshitfuck5 = true;
					// Modding

				}

				if (!bullshitfuck1 && !bullshitfuck2 && !bullshitfuck3 && !bullshitfuck4 && !bullshitfuck5) {
					event.getTextChannel().sendMessage("Enter a question cunt");
				}
			}
		} catch (Exception e) {
			chan.sendMessage("Unable to complete command.");
			e.printStackTrace();
		}
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
