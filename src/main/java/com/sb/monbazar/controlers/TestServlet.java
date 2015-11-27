/**
 * Copyright 2014-2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//[START all]
package com.sb.monbazar.controlers;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.common.base.Strings;
import com.googlecode.objectify.ObjectifyService;
import com.sb.monbazar.core.model.Item;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		List<Item> items = ofy().load().type(Item.class).limit(5).list();
		if (items.isEmpty()) {
			ofy().save().entity(new Item().title("Constantine - Dangerous habbits")).now();
			ofy().save().entity(new Item().title("Hamilton - pandora tome 3")).now();
			items = ofy().load().type(Item.class).limit(5).list();
		}
		
        resp.setContentType("text/plain");
        String text = "";
        for (Item item : items) {
			text += item.getTitle() + "\n";
		}
        
        resp.getWriter().println("items : <br>" + text);
	}
}
