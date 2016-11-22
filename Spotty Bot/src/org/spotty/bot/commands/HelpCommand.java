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

public class HelpCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		event.getAuthor().getPrivateChannel()
				.sendMessage("This is the list of commands you can use: ```"
						+ ".owner - Shows the owner of the Discord Server.\n" //
						+ ".cat - Posts a random cat in the chat.\n" //
						+ ".rip - Posts a ripme.xyz link.\n" //
						+ ".name - Tells you your username, and your nickname.\n" //
						+ ".hi - Says hi to you.\n" //
						+ ".bye - Says bye to you.\n" //
						+ ".invite - Sends you an invite link to the discord server it is run on.\n" //
						+ ".lmgtfy - Posts a lmgtfy.com link.\n" //
						+ ".purge - Purges messages from chat or from a specific user```"); //
		event.getAuthor().getPrivateChannel().sendMessage("https://discord.gg/UeYAd5h");

		event.getChannel().sendMessage("Help has arrived!");
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
