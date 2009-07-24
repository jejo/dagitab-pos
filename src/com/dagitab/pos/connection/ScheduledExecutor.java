package com.dagitab.pos.connection;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import com.dagitab.pos.forms.MainWindow;
import com.dagitab.pos.util.ServerPropertyHandler;
import com.dagitab.pos.util.StorePropertyHandler;



public class ScheduledExecutor
{
	ObjectInputStream in;
	ObjectOutputStream out;
	MainWindow form;
	public ScheduledExecutor(MainWindow form){
		this.form = form;
		
		
	}
	public static void main(String[] args)
	{
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable() {
			
			public void run(){
				try{
//					ClientConnect cs = new ClientConnect();
//					cs.connect("192.168.0.100", "8888", "", "");
				}
				catch(Exception e)
				{
					
				}
			}
			
		},2, 3600, TimeUnit.SECONDS);
			
	}	
	
	public void connect(){
		
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleAtFixedRate(new Runnable() {
				
				public void run(){
					try{
//						form.jLabel3.setText("Connecting");
						ClientConnect cc = new ClientConnect(form);
						cc.connect(ServerPropertyHandler.getServerIP(), 
									   ServerPropertyHandler.getServerPort(), 
									   Integer.parseInt(StorePropertyHandler.getStoreNo()));

					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, 
								"Cannot connect to server. " +
								"Please try again or contact network administrator.", 
								"Warning", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			},2, 3600, TimeUnit.SECONDS);


	}

}


