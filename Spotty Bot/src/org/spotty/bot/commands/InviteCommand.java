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
import net.dv8tion.jda.utils.InviteUtil;
import net.dv8tion.jda.utils.InviteUtil.Invite;

public class InviteCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		Invite inv = InviteUtil.createInvite(event.getGuild().getPublicChannel(), InviteUtil.InviteDuration.INFINITE, 0,
				false);
		String code = "https://discord.gg/" + inv.getCode();
		event.getTextChannel().sendMessage("Use the link " + code + " to invite your friends to this server!");
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
