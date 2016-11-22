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

import java.util.ArrayList;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class CommandParser {

	public CommandContainer parse(String rw, MessageReceivedEvent e) {
		ArrayList<String> split = new ArrayList<String>();
		String raw = rw;
		String beheaded = raw.replaceFirst(".", "");
		String[] splitBeheaded = beheaded.split(" ");
		for (String s : splitBeheaded)
			split.add(s);
		String invoke = split.get(0);
		String[] args = new String[split.size() - 1];
		split.subList(1, split.size()).toArray(args);

		return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, e);
	}

	public class CommandContainer {

		public final String raw;
		public final String beheaded;
		public final String[] splitBeheaded;
		public final String invoke;
		public final String[] args;
		public final MessageReceivedEvent event;

		public CommandContainer(String rw, String beheaded, String[] splitBeheaded, String invoke, String[] args,
				MessageReceivedEvent event) {
			this.raw = rw;
			this.beheaded = beheaded;
			this.splitBeheaded = splitBeheaded;
			this.invoke = invoke;
			this.args = args;
			this.event = event;

		}
	}

}
