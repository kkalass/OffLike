package org.offlike.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.offlike.server.data.Campaign;
import org.offlike.server.data.Poster;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDbService {

	private DB db;
	
	@Autowired
	public MongoDbService(DB db) {
		this.db = db;
	}
	
	public int countCampaigns() {
		DBCollection allCampaigns = db.getCollection("campaigns");
		DBCursor cursor = allCampaigns.find();
		return cursor.size();
	}

	public void createCampaign(Campaign campaign) {
		BasicDBObject camp = new BasicDBObject();
		camp.put("title", campaign.getTitle());

		DBCollection campaigns = db.getCollection("campaigns");
		campaigns.insert(camp);

		campaign.setId(((ObjectId) camp.get("_id")).toString());
	}

	public List<Campaign> findAllCampaigns() {
		ArrayList<Campaign> allCampaigns = new ArrayList<Campaign>();

		DBCollection dbCampaigns = db.getCollection("campaigns");
		DBCursor cursor = dbCampaigns.find();
		while (cursor.hasNext()) {
			DBObject dbObject = (DBObject) cursor.next();
			Campaign campaign = new Campaign();
			campaign.setTitle((String) dbObject.get("title"));
			campaign.setId(((ObjectId) dbObject.get("_id")).toString());
			allCampaigns.add(campaign);
		}
		return allCampaigns;
	}

	public void createPoster(Campaign campaign) {
		Poster poster = new Poster();

		// store in db
	}

	private void printObjects(DB db) {
		System.out.println("aktueller Datenbestand:");

		Set<String> colls = db.getCollectionNames();

		System.out.println("-------------------------------");
		for (String s : colls) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		//new MongoDbService().createPoster(new Campaign());
	}
}
