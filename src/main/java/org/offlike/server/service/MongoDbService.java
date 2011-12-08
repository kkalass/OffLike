package org.offlike.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
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

	static String dateTimeToString(DateTime dt) {
		return dt == null ? null : dt.toString();
	}
	
	static DateTime stringToDateTime(String v) {
		return v == null ? null : DateTime.parse(v);
	}
	
	private void setDateTime(DBObject obj, String property, DateTime value) {
		if (value == null) {
			obj.removeField(property);
			return;
		}
		String stringValue = dateTimeToString(value);
		obj.put(property, stringValue);
	}
	
	@CheckForNull
	private DateTime readDateTime(@Nonnull DBObject obj, @Nonnull String property) {
		if (!obj.containsField(property)){
			return null;
		} 
		String v = obj.get(property).toString();
		return stringToDateTime(v);
	}
	
	@CheckForNull
	private String readString(@Nonnull DBObject obj, @Nonnull String property) {
		return obj.containsField(property) ? obj.get(property).toString() : null;
	}

	@CheckForNull
	private Double readDouble(@Nonnull DBObject obj, @Nonnull String property) {
		return obj.containsField(property) ? (Double)obj.get(property) : null;
	}
	
	@CheckForNull
	private Integer readInteger(@Nonnull DBObject obj, @Nonnull String property) {
		return obj.containsField(property) ? (Integer)obj.get(property) : null;
	}
	
	public User findUserByUsername(String username) {
		DBCollection allUsers = database.getCollection("users");
		DBObject query = new BasicDBObject("username", username);

		DBObject found = allUsers.findOne(query);
		return createUserFromDbObject(found);
	}

	public User createUser(String username) {
		DateTime createdAt = new DateTime();
		BasicDBObject user = new BasicDBObject();
		user.put("username", username);
		setDateTime(user, "createdAt", createdAt);
		
		DBCollection users = database.getCollection("users");
		users.insert(user);

		String userId =  ((ObjectId) user.get("_id")).toString();
		return new User(userId, username, createdAt);
	}
	
	public void createCampaign(Campaign campaign) {
		DateTime createdAt = new DateTime();
		BasicDBObject camp = new BasicDBObject();
		camp.put("title", campaign.getTitle());
		setDateTime(camp, "createdAt", createdAt);
		camp.put("description", campaign.getDescription());
		camp.put("externalLink", campaign.getExternalLink());
		camp.put("ownerUserId", campaign.getOwnerUserId());
		DBCollection campaigns = database.getCollection("campaigns");
		campaigns.insert(camp);

		campaign.setId(((ObjectId) camp.get("_id")).toString());
	}

	public void createQrCode(Campaign campaign, QrCode qrCode) {
		DateTime createdAt = new DateTime();
		BasicDBObject dbQrCode = new BasicDBObject();
		dbQrCode.put("campaignId", campaign.getId());
		dbQrCode.put("counter", qrCode.getCounter());
		setDateTime(dbQrCode, "createdAt", createdAt);
		
		DBCollection dbQrCodes = database.getCollection("qrCodes");
		dbQrCodes.insert(dbQrCode);

		qrCode.setId(((ObjectId) dbQrCode.get("_id")).toString());
	}
	
	
	public void activateQrCode(String qrCodeId, double latitude, double longitude, int acurracy, String registeredByUserId) {
		DBCollection allQrCodes = database.getCollection("qrCodes");
		DBObject query = new BasicDBObject("_id", new ObjectId(qrCodeId));
		
		DateTime registeredAt = new DateTime();
		DBObject found = allQrCodes.findOne(query);
		found.put("longitude", longitude);
		found.put("latitude", latitude);
		found.put("accuracy", acurracy);
		setDateTime(found, "registeredAt", registeredAt);
		found.put("registeredBy", registeredByUserId);
		
		allQrCodes.update(query, found);
		
	}

	public QrCode findQrCodeById(String qrCodeId) {
		DBCollection allQrCodes = database.getCollection("qrCodes");
		DBObject query = new BasicDBObject("_id", new ObjectId(qrCodeId));

		DBObject found = allQrCodes.findOne(query);
		return createQrCodesFromDbObject(found);
	}

	public List<Campaign> findAllCampaigns() {

		DBCollection dbCampaigns = database.getCollection("campaigns");
		return createCampaignsFromDBObjects(dbCampaigns.find());
	}


	private List<Campaign> createCampaignsFromDBObjects(DBCursor cursor) {
		ArrayList<Campaign> allCampaigns = new ArrayList<Campaign>();
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


	public Collection<Campaign> findCampaignsByOwnerId(String ownerId) {
		DBCollection allCampaigns = database.getCollection("campaigns");
		return createCampaignsFromDBObjects(allCampaigns.find(new BasicDBObject("ownerUserId", ownerId)));
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

	public void associateCampaignWithOwnerId(String campaignId, String ownerId) {
		DBCollection allCampaigns = database.getCollection("campaigns");
		DBObject query = new BasicDBObject("_id", new ObjectId(campaignId));
		
		DBObject found = allCampaigns.findOne(query);
		
		found.put("ownerUserId", ownerId);
		setDateTime(found, "ownerUserIdUpdatedAt", new DateTime());
		
		allCampaigns.update(query, found);
	}

	private Campaign createCampaignFromDbObject(DBObject found) {
		Campaign campaign = new Campaign();
		campaign.setId(found.get("_id").toString());
		campaign.setTitle(found.get("title").toString());
		campaign.setOwnerUserId(readString(found, "ownerUserId"));
		campaign.setDescription(readString(found, "description"));
		campaign.setExternalLink(readString(found, "externalLink"));
		campaign.setCreatedAt(readDateTime(found, "createdAt"));
		return campaign;
	}

	private User createUserFromDbObject(DBObject found) {
		if (found == null) {
			return null;
		}
		String username = found.get("username").toString();
		String userId = found.get("_id").toString();
		DateTime createdAt = readDateTime(found, "createdAt");
		return new User(userId, username, createdAt);
	}
	
	private QrCode createQrCodesFromDbObject(DBObject found) {
		QrCode qrCode = new QrCode();
		qrCode.setId(found.get("_id").toString());
		qrCode.setCampaignId(found.get("campaignId").toString());
		qrCode.setCounter((Integer) found.get("counter"));
		qrCode.setLatitude(readDouble(found, "latitude"));
		qrCode.setLongitude(readDouble(found, "longitude"));
		qrCode.setAccuracy(readInteger(found, "accuracy"));
		qrCode.setCreatedAt(readDateTime(found, "createdAt"));
		qrCode.setRegisteredAt(readDateTime(found, "registeredAt"));
		qrCode.setRegisteredByUserId(readString(found, "registeredBy"));
		return qrCode;
	}

}
