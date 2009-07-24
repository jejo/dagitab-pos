package com.dagitab.pos.connection;

import com.dagitab.pos.forms.MainWindow;
import com.dagitab.pos.util.ServerPropertyHandler;
import com.dagitab.pos.util.StorePropertyHandler;


public class ManualConnect extends Thread {
	
	private MainWindow form;
	
	public ManualConnect(MainWindow form){
		this.form = form;
	}
	
	@Override
	public void run(){
		ClientConnect cc = new ClientConnect(form);
		cc.connect(ServerPropertyHandler.getServerIP(), 
				   ServerPropertyHandler.getServerPort(), 
				   Integer.parseInt(StorePropertyHandler.getStoreNo()));
	}
}
