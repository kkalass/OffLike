package org.offlike.server.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.offlike.server.data.Campaign;
import org.offlike.server.data.QrCode;
import org.offlike.server.data.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * FIXME Split up monolithic MongoDBService
 *
 */
public class MongoDbService {

	
	private final DB database;

	@Autowired
	public MongoDbService(DB database) {
		this.database = database;
	}

	public int countCampaigns() {
		DBCollection allCampaigns = database.getCollection("campaigns");
		DBCursor cursor = allCampaigns.find();
		return cursor.size();
	}


	public User findUserByUsername(String username) {
		DBCollection allUsers = database.getCollection("users");
		DBObject query = new BasicDBObject("username", username);

		DBObject found = allUsers.findOne(query);
		return createUserFromDbObject(found);
	}

	public User createUser(String username) {
		BasicDBObject user = new BasicDBObject();
		user.put("username", username);

		DBCollection users = database.getCollection("users");
		users.insert(user);

		String userId =  ((ObjectId) user.get("_id")).toString();
		return new User(userId, username);
	}
	
	public void createCampaign(Campaign campaign) {
		BasicDBObject camp = new BasicDBObject();
		camp.put("title", campaign.getTitle());
		camp.put("description", campaign.getDescription());
		camp.put("externalLink", campaign.getExternalLink());
		camp.put("ownerUserId", campaign.getOwnerUserId());
		DBCollection campaigns = database.getCollection("campaigns");
		campaigns.insert(camp);

		campaign.setId(((ObjectId) camp.get("_id")).toString());
	}

	public void createQrCode(Campaign campaign, QrCode qrCode) {
		BasicDBObject dbQrCode = new BasicDBObject();
		dbQrCode.put("campaignId", campaign.getId());
		dbQrCode.put("counter", qrCode.getCounter());

		DBCollection dbQrCodes = database.getCollection("qrCodes");
		dbQrCodes.insert(dbQrCode);

		qrCode.setId(((ObjectId) dbQrCode.get("_id")).toString());
	}
	
	
	public void activateQrCode(String qrCodeId, double latitude, double longitude, int acurracy) {
		DBCollection allQrCodes = database.getCollection("qrCodes");
		DBObject query = new BasicDBObject("_id", new ObjectId(qrCodeId));
		
		DBObject found = allQrCodes.findOne(query);
		System.out.println("found: " + found);
		found.put("longitude", longitude);
		found.put("latitude", latitude);
		found.put("accuracy", acurracy);
		
		allQrCodes.update(query, found);
		
		DBObject newFound = allQrCodes.findOne(query);
		System.out.println("updated " + newFound);
	}

	public QrCode findQrCodeById(String qrCodeId) {
		DBCollection allQrCodes = database.getCollection("qrCodes");
		DBObject query = new BasicDBObject("_id", new ObjectId(qrCodeId));

		DBObject found = allQrCodes.findOne(query);
		return createQrCodesFromDbObject(found);
	}

	public List<Campaign> findAllCampaigns() {
		ArrayList<Campaign> allCampaigns = new ArrayList<Campaign>();

		DBCollection dbCampaigns = database.getCollection("campaigns");
		DBCursor cursor = dbCampaigns.find();
		while (cursor.hasNext()) {
			DBObject dbObject = (DBObject) cursor.next();
			Campaign campaign = createCampaignFromDbObject(dbObject);
			allCampaigns.add(campaign);
		}
		return allCampaigns;
	}

	public Campaign findCampaignById(String campaignId) {
		DBCollection allCampaigns = database.getCollection("campaigns");
		DBObject query = new BasicDBObject("_id", new ObjectId(campaignId));

		DBObject found = allCampaigns.findOne(query);
		return createCampaignFromDbObject(found);
	}

	public List<QrCode> findQrCodesForCampaign(String campaignId) {
		ArrayList<QrCode> qrCodes = new ArrayList<QrCode>();

		DBCollection allQrCodes = database.getCollection("qrCodes");
		DBObject query = new BasicDBObject("campaignId", campaignId);
		DBCursor found = allQrCodes.find(query);
		while (found.hasNext()) {
			DBObject dbObject = (DBObject) found.next();
			qrCodes.add(createQrCodesFromDbObject(dbObject));
		}
		return qrCodes;
	}

	private Campaign createCampaignFromDbObject(DBObject found) {
		Campaign campaign = new Campaign();
		campaign.setId(found.get("_id").toString());
		campaign.setTitle(found.get("title").toString());
		String ownerUserId = found.containsField("ownerUserId") ? found.get(
				"ownerUserId").toString() : null;
		campaign.setOwnerUserId(ownerUserId);
		String description = found.containsField("description") ? found.get(
				"description").toString() : null;
		campaign.setDescription(description);
		String externalLink = found.containsField("externalLink") ? found.get(
				"externalLink").toString() : null;
		campaign.setExternalLink(externalLink);
		return campaign;
	}
	
	private User createUserFromDbObject(DBObject found) {
		if (found == null) {
			return null;
		}
		String username = found.get("username").toString();
		String userId = found.get("_id").toString();
		return new User(userId, username);
	}
	
	private QrCode createQrCodesFromDbObject(DBObject found) {
		QrCode qrCode = new QrCode();
		qrCode.setId(found.get("_id").toString());
		qrCode.setCampaignId(found.get("campaignId").toString());
		qrCode.setCounter((Integer) found.get("counter"));
		
		Double latitude = found.containsField("latitude") ? (Double) found.get(
				"latitude") : null;
		qrCode.setLatitude(latitude);
		
		Double longitude = found.containsField("longitude") ? (Double) found.get(
				"longitude") : null;
		qrCode.setLongitude(longitude);
		
		Integer accuracy = found.containsField("accuracy") ? (Integer) found.get(
				"accuracy") : null;
		qrCode.setAccuracy(accuracy);
		return qrCode;
	}

}
