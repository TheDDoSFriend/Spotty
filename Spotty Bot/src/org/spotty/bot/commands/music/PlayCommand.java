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

package org.spotty.bot.commands.music;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.spotty.bot.Command;

import net.dv8tion.jda.entities.VoiceChannel;
import net.dv8tion.jda.entities.VoiceStatus;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class PlayCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		/** Music channel checks/in voice channel checks */
		boolean musicChanFound = false;
		VoiceChannel vc = null;
		for (VoiceChannel v : event.getGuild().getVoiceChannels()) {
			if (v.getName().equalsIgnoreCase("music")) {
				musicChanFound = true;
				vc = v;
			}
		}
		if (musicChanFound == false) /**
										 * If music channel is not found make
										 * it.(subject to change with config
										 * update)
										 */
			event.getGuild().createVoiceChannel("Music");
		;
		VoiceStatus status = event.getGuild().getVoiceStatusOfUser(event.getAuthor());
		if (status.getChannel() == null) {
			event.getTextChannel().sendMessage("Please join the Music voice channel to use this command.");
			return;
		} else if (!status.getChannel().getName().equalsIgnoreCase(
				"music")) { /** Checks if voice channel is not Music */
			event.getTextChannel().sendMessage("Please join the Music voice channel to use this command.");
			return;
		} else {
			event.getJDA().getAudioManager(event.getGuild())
					.openAudioConnection(vc); /** Opens audio connection */
		}

		if (args.length > 0) {
			File file = new File("queue.txt");
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}

			try {
				FileUtils.writeStringToFile(file,
						event.getGuild().getId() + "\n" + event.getGuild().getOwnerId() + "\n\n", "UTF-8", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			event.getTextChannel().sendMessage("Your request has been added to the queue.");
		} else {
			event.getTextChannel()
					.sendMessage("Please enter a title or link to play it (example: .play My Way ft Drake).");
		}
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
