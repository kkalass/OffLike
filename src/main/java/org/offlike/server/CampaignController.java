package org.offlike.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Patch;

import org.offlike.server.data.Campaign;
import org.offlike.server.service.MongoDbService;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;

@Controller
public class CampaignController {

	public static Logger log = LoggerFactory
			.getLogger(CampaignController.class);

	@Autowired
	Policy policy;

	@Autowired
	MongoDbService dbService;

	/**
	 * Shows a new campaign page
	 * 
	 * @return
	 */
	@RequestMapping("/newCampaign")
	public ModelAndView newCampaign() {
		return new ModelAndView("newCampaign");
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/createCampaign")
	public ModelAndView createCampaign(
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam(value = "externalLink", required = false) String externalLink) {

		Map<String, String> errorMap = new HashMap<String, String>();

		String cleanTitle = cleanUpString(title);
		if (cleanTitle == null) {
			errorMap.put("title", "Dirty input!");
		}
		String cleanDescription = cleanUpString(description);
		if (cleanDescription == null) {
			errorMap.put("description", "Dirty input!");
		}
		if (externalLink != null && !isUrlValid(externalLink)) {
			errorMap.put("refererUrl", "Not valid");
		}

		Campaign campaign = new Campaign();
		campaign.setDescription(cleanDescription);
		campaign.setExternalLink(externalLink);

		if (errorMap.isEmpty()) {

			dbService.createCampaign(campaign);
			return getCampaign(campaign.getId());
		}

		return new ModelAndView("newCampaign", ImmutableMap.of("errorMap",
				errorMap));
	}

	@RequestMapping("/campaign/{id}")
	public ModelAndView getCampaign(@PathVariable String id) {
		if (true)
		return new ModelAndView("campaign");
		
		if (!isIdValid(id)) {
			return errorPage("Id is not valid");
		}

		Campaign camp = dbService.findCampaignById(id);

		if (camp==null){
			return errorPage("No campaign with that id!");
		}
		return new ModelAndView("campaign", ImmutableMap.of("campaign",
				camp));
	}

	//TODO make errorPage
	private ModelAndView errorPage(String error) {
		return new ModelAndView("errorPage", ImmutableMap.of("error",
				error));
	}

	private boolean isIdValid(String id) {
		if (id != null && id.matches("[0-9a-f]{24}")) {
			return true;
		}
		return false;
	}

	private boolean isUrlValid(String refererUrl) {

		try {
			new URL(refererUrl);
		} catch (MalformedURLException e) {

			return false;
		}

		return true;
	}

	private String cleanUpString(String dirtyInput) {

		AntiSamy as = new AntiSamy();
		try {

			return as.scan(dirtyInput, policy, AntiSamy.SAX).getCleanHTML();

		} catch (ScanException e) {
			e.printStackTrace();
			log.error(dirtyInput + " is not a valid input!", e);
			return null;
		} catch (PolicyException e) {
			e.printStackTrace();
			log.error(dirtyInput + " applies not for the policy!", e);
			return null;
		}

	}
}
