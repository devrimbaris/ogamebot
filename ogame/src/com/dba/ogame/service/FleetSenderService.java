package com.dba.ogame.service;

import java.io.IOException;

import com.dba.ogame.job.FleetSendJob;
import com.dba.ogame.model.Planet;
import com.dba.ogame.webforms.FleetSendForm2;
import com.dba.ogame.webforms.FleetSendForm3;
import com.dba.ogame.webforms.FleetSendFormLast;
import com.dba.ogame.webforms.GenericGetForm;

public class FleetSenderService extends AbstractService {
	
	public void execute() throws IOException, BuildingQueueFullException {
	
		FleetSendJob fsJob=(FleetSendJob)getJob();
		Planet target= (Planet) getPlanetRepository().get(fsJob.getPlanetId());
		Planet source= getPlanet();
		int intMetal=0;
		int intKristal=0;
		int intDeuterium=0;
		int remain=4900;
		
		//once deut sonra kristal en son metal oncelikli
		intDeuterium=source.getResourceStatus().getIntDeuterium() > remain   ?
				remain : source.getResourceStatus().getIntDeuterium();
		remain-=intDeuterium;
		
		intKristal=source.getResourceStatus().getIntKristal() > remain   ?
				remain : source.getResourceStatus().getIntKristal();
		remain-=intKristal;
		
		intMetal=source.getResourceStatus().getIntMetal() > remain   ?
				remain : source.getResourceStatus().getIntMetal();
		remain-=intMetal;
		
		//cok bos yer kaldiysa yollamayi iptal et
		
		if (remain > 100)
			return;
		
		String gfS=decorateUrl(getReader().getProperty("url.fleetsend1"));
		GenericGetForm getform=
			new GenericGetForm(gfS,getClient());
		getform.execute();
		String fs2S=decorateUrl(getReader().getProperty("url.fleetsend2"));
		FleetSendForm2 fs2=new FleetSendForm2(fs2S,getClient());
		fs2.execute();
		String fs3S=decorateUrl(getReader().getProperty("url.fleetsend3"));
		FleetSendForm3 fs3=new FleetSendForm3(fs3S,getClient(),source,target);
		fs3.execute();
		String fsLast=decorateUrl(getReader().getProperty("url.fleetsendLast"));
		FleetSendFormLast form=
			new FleetSendFormLast(fsLast,
					getClient(),
					source,
					target,
					"3",
					""+intMetal,
					""+intKristal,
					""+intDeuterium
					);
		form.execute();
		System.out.println("FleetSenderService:" +
				source +"->" +
				target +
				"(Metal:"+ intMetal+
				"Kristal:" +intKristal+
				"Deuterium:"+intDeuterium+")"
				);

	}

	private String decorateUrl(String sablon){
		String rootUrl=getReader().getProperty("login.siteurl");
		sablon=sablon.replaceAll("\\{\\$login\\.siteurl\\}",rootUrl);
		sablon=sablon.replaceAll("\\{\\$sessionId\\}",getSessionId());
		return sablon;
	}
}
