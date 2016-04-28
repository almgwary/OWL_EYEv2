package com.owl_eye.controller;

 


import com.owl_eye.messageSystem.Message;
import com.owl_eye.messageSystem.MessageSystem;
import com.owl_eye.messageSystem.Task;
import com.firebase.client.Firebase;



//import java.io.UncheckedIOException;
//import java.util.Base64;
//import java.util.Scanner;
//import java.awt.image.BufferedImage;
//import java.awt.image.RenderedImage;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;


//import application.Page2Controller;
//import messageSystem.Message;
//import messageSystem.MessageSystem;
//import messageSystem.Task;


/**
 * @author elmegwary
 *
 */
public class InternalController {


	static MessageSystem messageSystem =  null ;
	static Firebase ref = new Firebase("https://0007.firebaseio.com/OWL");
	//static Page2Controller page2Controller ;
	
	
	public static void start (String token/*,Page2Controller c*/) {
		//page2Controller = c;
		
		messageSystem  =  new MessageSystem(ref.child(token).child("master"), ref.child(token).child("slave"));
		sentOrder(Task.START);
				
	}
	
	public static void sentOrder(Task t){
		Message m = new Message() ;

		m.setTask(t);
		messageSystem.send(m);
	}
	public static void onReciveingMessage(Message m){
		System.out.println("Reciving Message");
		//BufferedImage image = base64StringToImg(m.getPhoto());
		
		//page2Controller.recivingPhoto(image);
 
		 
	}
	
	
	

	 /*public static String imgToBase64String(final RenderedImage img, final String formatName) {
		    final ByteArrayOutputStream os = new ByteArrayOutputStream();
		    try {
		        ImageIO.write(img, formatName, Base64.getEncoder().wrap(os));
		        return os.toString(StandardCharsets.ISO_8859_1.name());
		    } catch (final IOException ioe) {
		        throw new UncheckedIOException(ioe);
		    }
		}

	 public static BufferedImage base64StringToImg(final String base64String) {
		    try {
		        return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
		    } catch (final IOException ioe) {
		        throw new UncheckedIOException(ioe);
		    }
		}
		*/

}
