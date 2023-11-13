package com.inetum.appliBibliotheque.utils;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

public class AppUtils {
	
	/**
	 * Permet d'encrypter
	 * @param word
	 * @return Le mot avec l'initiale en majuscule
	 */
	public static String capitalizeWord(String word) {
		if(word.isEmpty() || word == null) {
			return word;
		}
		else {
			return word.substring(0,1).toUpperCase() + word.substring(1);
		}
	}
	
	public static String hashPassword(String mdp) {
		return BCrypt.hashpw(mdp, BCrypt.gensalt());
	}
	
	/**
	 * Permet de vérifier
	 * @param mdp
	 * @param mdpHache
	 * @return
	 */
	public static boolean checkPassword(String mdp, String mdpHache) {
		return BCrypt.checkpw(mdp, mdpHache); 
	}
	
	
	//pour faire des opérations sur les dates
	public static Date ajouterJours(Date date, Integer jours) {
		LocalDate localDate = asLocalDate(date);
		localDate=localDate.plusDays(jours);
		return asDate(localDate);
	}
	
	
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
}
