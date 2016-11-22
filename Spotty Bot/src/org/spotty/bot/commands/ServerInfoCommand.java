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

public class ServerInfoCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		TextChannel t = event.getTextChannel();
		t.sendMessage( //
				"This information was requested by " + event.getAuthor().getAsMention() + "\n" + //
						"Server name: **" + event.getGuild().getName() + "**\n" + //
						"Server region: **" + event.getGuild().getRegion().getKey() + "**\n" + //
						"Server owner: **" + event.getGuild().getOwner().getUsername() + "**\n" + //
						"This server has **" + event.getGuild().getUsers().size() + "** members.\n" + //
						"This server has ** + " + "**\n" +
						"Server icon: " + event.getGuild().getIconUrl()
						); //are u there??????????????		
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
