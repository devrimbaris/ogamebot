package com.dba.ogame;

import java.io.IOException;

import com.dba.ogame.util.PropertiesReader;


public class Launcher  {

	private Overlord overlord;
	private int MissionRunInterval=900000;

	public Launcher(Overlord overlord) {
		this.overlord = overlord;
		PropertiesReader reader;
		try {
			reader = new PropertiesReader("application.properties");
			MissionRunInterval=Integer.parseInt(reader.getProperty("settings.cycleperiod"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		try {
			Overlord overlord=new Overlord();
			Launcher launcher=new Launcher(overlord);
			launcher.go();
			
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void go() throws InterruptedException {
		while (true) {
			try {
				
				overlord.run();
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			finally{
				Thread.sleep(MissionRunInterval);
			}
		}
	}

}