package eu.arrowhead.client.skeleton.provider.controller;

import eu.arrowhead.client.skeleton.provider.LocalConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.arrowhead.common.CommonConstants;

//import eu.arrowhead.client.skeleton.provider.LocalConstants;

import eu.arrowhead.client.skeleton.provider.LocalConstants;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ProviderController {
	
	//=================================================================================================
	// members

	//TODO: add your variables here

	//=================================================================================================
	// methods

	@PostMapping(LocalConstants.JAR_DEPLOY_URL)
	public String handleJarDeploy(@RequestParam("file")MultipartFile file) {
		File t = new File("/home/s7rul/tmptest.jar");
		try {
			file.transferTo(t);
			return "upploaded!";
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
			return "Failed";
		}
	}

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = CommonConstants.ECHO_URI)
	public String echoService() {
		return "Got it!";
	}
	
	//-------------------------------------------------------------------------------------------------
	//TODO: implement here your provider related REST end points
}
