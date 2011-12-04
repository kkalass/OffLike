package org.offlike.server.service;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.offlike.server.CampaignController;
import static org.offlike.server.CampaignController.*;
import org.offlike.server.data.Campaign;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.MongoException;

public class CampaignControllerTest {


	private MongoDbService mongoDbService;
	private CampaignController campaignController;

	@Before
	public void init() throws UnknownHostException, MongoException, PolicyException{
		MongoDbServiceTest dbServiceTest = new MongoDbServiceTest();
		dbServiceTest.setup();
		mongoDbService = dbServiceTest.mongoDbService;
		
		campaignController = new CampaignController();
		campaignController.setDbService(mongoDbService);
		
		Policy policy = Policy.getInstance(ClassLoader.getSystemResourceAsStream("antisamy-slashdot-1.4.4.xml"));
		campaignController.setPolicy(policy);
		
		QrCodeService qrCodeService = new QrCodeService(mongoDbService);
		campaignController.setQrCodeService(qrCodeService);
	}
	
	@Test
	public void testCreateNotValidCampaign(){
		ModelAndView createCampaign = campaignController.createCampaign("<script>alert(1);</script>", "<script>alert(1);</script>", "<script>alert(1);</script>");
		Assert.assertEquals("createCampaign", createCampaign.getViewName() );
		Assert.assertNotNull(createCampaign.getModelMap().get("errorMap"));
		
		createCampaign = campaignController.createCampaign("<script>alert(1);</script>", "<script>alert(1);</script>", "http://url.is.com");
		Assert.assertEquals("createCampaign",createCampaign.getViewName());
		Assert.assertNotNull(createCampaign.getModelMap().get("errorMap"));
		
		createCampaign = campaignController.createCampaign("<script>alert(1);</script>Title", "<script>alert(1);</script>What", "http://url.is.com");
		Assert.assertTrue(createCampaign.getViewName().startsWith("redirect:campaign"));
		Assert.assertNull(createCampaign.getModelMap().get("errorMap"));
		//Campaign campaign = (Campaign) createCampaign.getModelMap().get("campaign");
		//Assert.assertEquals("Title", campaign.getTitle());
	}

	@Test
	public void testCreateVAlidMapDownload10QRCodes() throws MalformedURLException{
		ModelAndView createCampaign = campaignController.createCampaign("Great Title", "Super Description", null);
		Assert.assertTrue(createCampaign.getViewName().startsWith("redirect:"+CAMPAIGN_PAGE) );
		Assert.assertNull(createCampaign.getModelMap().get("errorMap"));
	//	Campaign campaign = (Campaign) createCampaign.getModelMap().get(CAMPAIGN_FIELD);
	//	assertNotNull( campaign.getId());
//		
		//FIXME get id from redirect
		
//		ModelAndView campaign2 = campaignController.getCampaign(campaign.getId());
//		assertNotNull(campaign2); 
//		assertNotNull(campaign2.getModelMap().get(CAMPAIGN_FIELD));
//		campaign = (Campaign) createCampaign.getModelMap().get(CAMPAIGN_FIELD);
//		assertEquals("Great Title", campaign.getTitle());
//		assertEquals("Super Description", campaign.getDescription());
//		
//		ModelAndView createQrCodes = campaignController.createQrCodes(campaign.getId(), 10);
//		assertNotNull(createQrCodes);
//		List<String> qrCodeList = (List<String>) createQrCodes.getModelMap().get(QR_CODE_LIST);
//		assertEquals(10, qrCodeList.size());
//		String usrEncodedString = qrCodeList.get(3);
//		URL url = new URL(usrEncodedString);
//		assertNotNull(url);
	}

	
}
