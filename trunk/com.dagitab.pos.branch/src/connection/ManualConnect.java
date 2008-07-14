package connection;

import util.ServerPropertyHandler;
import util.StorePropertyHandler;
import forms.MainWindow;

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
