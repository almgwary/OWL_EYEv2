package com.owl_eye.controller;

 


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;
import com.owl_eye.MasterActivity;
import com.owl_eye.messageSystem.Message;
import com.owl_eye.messageSystem.MessageSystem;
import com.owl_eye.messageSystem.Task;
import com.firebase.client.Firebase;

import java.io.IOException;


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
	static CallBack callBack ;;
	
	
	public static void start (final String token,CallBack c) {
		callBack = c;


		ref.authAnonymously(new Firebase.AuthResultHandler() {

			@Override
			public void onAuthenticationError(FirebaseError arg0) {
				System.err.println("not Aouthinticaated: "+ arg0.toString());
				try {
					throw new Exception() ;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onAuthenticated(AuthData arg0) {
				System.out.println("Aouthinticaated: "+ arg0.toString());
				messageSystem  =  new MessageSystem(ref.child(token).child("master"), ref.child(token).child("slave"));
				sentOrder(Task.START);



			}
		});
//		messageSystem  =  new MessageSystem(ref.child(token).child("master"), ref.child(token).child("slave"));
//		sentOrder(Task.START);
				
	}
	
	public static void sentOrder(Task t){
		Message m = new Message() ;

		m.setTask(t);
		messageSystem.send(m);
	}




	public static void onReciveingMessage(Message m){
		System.out.println("Reciving Message");

		byte[] imageInByte = null ;

		try {
			imageInByte = com.firebase.client.utilities.Base64.decode(m.getImage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Bitmap bitmap = BitmapFactory.decodeByteArray(imageInByte, 0, imageInByte.length);


 		callBack.onRecivePhoto(bitmap);
		 
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
