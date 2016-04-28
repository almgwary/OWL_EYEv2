/**
 * 
 */
package com.owl_eye.messageSystem;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * Mar 31, 2016
 * @author Almgwary
 * RemoteFire
 * FireBaseService.java
 **/
public class FireBaseService {
	
	private MessageSystem myMessageSystem ;
	private Firebase sendPort,  receivePort ;
	private ChildEventListener listenerSendPort , listenerReceivePort;

	
	/**
	 * initialize listeners  listenerSendPort, listenerReceivePort
	 * @param myMessageSystem : message system which i serve 
	 * @param sendPort : fireBase reference to send on it 
	 * @param receivePort : fireBase reference to listen receiving message on it on it
	 */
	public FireBaseService(MessageSystem myMessageSystem ,Firebase sendPort, Firebase receivePort ) {
		this.sendPort = sendPort;
		this.receivePort = receivePort;
		this.myMessageSystem = myMessageSystem;
		createLisners();
		runPortListenrs();
		
		
		
	}
	
	
	/**
	 * @param message : message to be sent
	 */
	public void send(Message message){
		sendPort.push().setValue(message);
	}
	
	/**
	 * initialize listenerReceivePort object  
	 * 	invoke myMessageSystem when receiving message on receivePort
	 * 	onChildAdded handle that received message is successfully mapped to Message.class and remove it  else discard message
	 * 
	 * initialize listenerSendPort object and 
	 * invoke myMessageSystem when message 
	 * 			1. successfully  sent : call myMessageSystem.onSuccessfullyMessageSent();
	 * 			2. Successfully delivered : call myMessageSystem.onMessageDeliverd();
	 *
	 */
	private void  createLisners(){
		
		listenerSendPort = new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {
				
				
				Message message = null ;
				try {
					message =  dataSnapshot.getValue(Message.class);
				} catch (Exception e) {
					System.err.println("FireBaseService.class ERROR ON Paresing deliverd MESSAGE : it will be discarded");
				}
				// Successfully parsed invoke myMessageSystem and remove it form the port
				if (message !=null) {
					 
					myMessageSystem.onMessageDeliverd(message);
					
				}
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				Message message = null ;
				try {
					message =  dataSnapshot.getValue(Message.class);
				} catch (Exception e) {
					System.err.println("FireBaseService.class ERROR ON Paresing sent MESSAGE : it will be discarded");
				}
				// Successfully parsed invoke myMessageSystem and remove it form the port
				if (message !=null) {
					 
					myMessageSystem.onSuccessfullyMessageSent(message);
					
				}
				
				
			}
			
			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		};

		
		listenerReceivePort = new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				Message message = null ;
				try {
					message =  dataSnapshot.getValue(Message.class);
				} catch (Exception e) {
					System.err.println("FireBaseService.class ERROR ON Paresing Recived MESSAGE : it will be discarded");
				}
				
				// Successfully parsed invoke myMessageSystem and remove it form the port
				if (message !=null) {
					receivePort.child(dataSnapshot.getKey()).removeValue();
					myMessageSystem.onRecievingMessage(message);
					
				}
				
			}
			
			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	/**
	 *  attached listenerReceivePort to receivePort
	 *  attached listenerSendPort to sendPort
	 * 
	 */
	private void runPortListenrs(){
		System.err.println("runSendPortListenrs");
		receivePort.addChildEventListener(listenerReceivePort);
		sendPort.addChildEventListener(listenerSendPort);
	}
	
	
	 
	
	
	/**
	 * remove listeners from receive and send
	 */
	public void off(){
		receivePort.removeEventListener(listenerReceivePort);
		receivePort.removeEventListener(listenerSendPort);
	}
	
	/**
	 * Enable listeners from receive and send
	 */
	public void on(){
		runPortListenrs();
	}
	
	
}
