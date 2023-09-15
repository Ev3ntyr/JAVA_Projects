package org.enchere.eni.m.messages;

import java.util.ResourceBundle;

public class MessageReader {

	private static ResourceBundle rb;

	static {
		// récupère le fichier qui contient les messages d'erreur
		rb = ResourceBundle.getBundle("org.enchere.eni.m.messages.error_messages");
	}

	/**
	 * Renvoie une message d'erreur en fonction de son code.
	 * 
	 * @param code Le code d'erreur.
	 * @return Le message correspondant.
	 */
	public static String getErrorMessage(int code) {
		String message = "";

		try {
			if (rb != null) {
				message = rb.getString(String.valueOf(code));
			} else {
				message = "Error during the reading of the file error_message";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Unknown error";
		}

		return message;
	}

}
