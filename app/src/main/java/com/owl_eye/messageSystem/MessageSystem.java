package com.owl_eye.messageSystem;

import com.owl_eye.controller.InternalController;
import com.firebase.client.Firebase;

 

/**
 * Mar 31, 2016
 * @author Almgwary
 * RemoteFire
 * MessageSystem.java
 **/


/**
 * problem on Off and ON NOT working 
 * 
 * */
public class MessageSystem {
	
	 FireBaseService myFireBaseService ;
	/**
	 * Initialize FireBase object with this parameters
	 * 
	 * @param sendPort : fireBase reference to send on it 
	 * @param receivePort : fireBase reference to listen receiving message on it on it
	 */
	public MessageSystem(Firebase sendPort, Firebase receivePort){
		myFireBaseService= new FireBaseService(this, sendPort, receivePort); 
	}	
	
	/**
	 * @param message : message to be sent
	 */
	public void send(Message message){
		myFireBaseService.send(message);
	}
	
	/**
	 * FairBase call this when successfully sending message
	 */
	public void onSuccessfullyMessageSent(Message message){
		System.out.println("MessageSystem.onSuccessfullyMessageSent$ "+message.toString());
	}
	
	
	/**
	 * FairBase call this when successfully deliver message to other device
	 */
	public void onMessageDeliverd(Message message){
		System.out.println("MessageSystem.onMessageDeliverd$ "+message.toString());
	}
	
	
	/**
	 * FairBase call this when other device send new message
	 */
	public void onRecievingMessage(Message m){
		System.out.println("MessageSystem.onRecievingMessage$ "+m.toString());
		InternalController.onReciveingMessage(m);
	}
	
	
	/**
	 * remove listeners
	 */
	public void off(){
		myFireBaseService.off();
		System.err.println("OFF");
	}
	
	
	/**
	 * reEnable listeners
	 */
	public void on(){
		myFireBaseService.on();
		System.err.println("ON");
		
	}
	
	
	

}
