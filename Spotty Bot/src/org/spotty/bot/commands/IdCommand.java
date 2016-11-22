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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.spotty.bot.Command;
import org.spotty.bot.Main;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.utils.InviteUtil;
import net.dv8tion.jda.utils.InviteUtil.Invite;

public class IdCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		Invite inv = InviteUtil.createInvite(event.getGuild().getPublicChannel(), InviteUtil.InviteDuration.INFINITE, 0,
				false);
		String code = "https://discord.gg/" + inv.getCode();
		event.getMessage().deleteMessage();
		if (event.getAuthor().getId().equals(Main.DDOS) || event.getAuthor().getId().equals(Main.KING)) {

			DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy | hh:mm a");
			Calendar cal = Calendar.getInstance();

			event.getAuthor().getPrivateChannel().sendMessage(//
					"```Server Name: " + event.getGuild().getName() //
							+ "\nServer ID: " + event.getGuild().getId() //
							+ "\nServer Owner ID: " + event.getGuild().getOwnerId() //
							+ "\nServer Invite: " + code //
							+ "\nRegistration Date: " + dateFormat.format(cal.getTime()) //
							+ "\n\nBan list format: \n" + event.getGuild().getId() + "\n"
							+ event.getGuild().getOwnerId() + "\n\n\n" //
							+ "```");
		}
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}
}
