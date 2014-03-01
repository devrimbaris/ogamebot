package com.dba.ogame.webforms;
import org.apache.commons.httpclient.HttpClient;

public class LoginForm extends AbstractForm{

	private String login;
	private String pass;
	private String universe;
	private String tm;
	private String sessionId;
	
	public LoginForm(String url,HttpClient client,String login, String pass, String universe) {
		super(url,client);
		setLogin(login);
		setPass(pass);
		setUniverse(universe);
		setSessionId("");
		
	}

	protected void createFormParameters() {
		getFormParameters().put("login",getLogin());
		getFormParameters().put("pass",getPass());
		getFormParameters().put("Uni",getUniverse());
		getFormParameters().put("Abschicken","Giris");
		
	}
	
	protected void setFormReturnParameters() {
		setTm((String)getFormReturnValues().get("tm"));
		setLogin((String)getFormReturnValues().get("login"));
		setPass((String)getFormReturnValues().get("pass"));
		setSessionId(parseSessionId(getResponse()));
	}
	
	private String  parseSessionId(String response) {
		String id="";
		int start=response.indexOf("URL=../../game/index.php?session=");
		int finish=response.indexOf(">",start);
		if(start > 0 && finish >0 && finish > start)
			id=response.substring(start+33,finish-1);
		return id;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTm() {
		return tm;
	}
	private void setTm(String tm) {
		this.tm=tm;
	}
	public String getUniverse() {
		return universe;
	}
	private void setUniverse(String universe) {
		this.universe = universe;
	}


	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
