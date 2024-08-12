package com.dbUtil;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import com.sqlFunctions.ProfessorDBFunction;
import com.sqlFunctions.SecretaryDBFunction;
import com.sqlFunctions.StudentDBFunction;

//validate user data with the db
public class DBAccount {

	private SecretaryDBFunction secretarydb;
	private ProfessorDBFunction professordb;
	private StudentDBFunction studentdb;
	
	public DBAccount() {
		DBConnection.connectToDB();
		secretarydb = new SecretaryDBFunction();
		professordb = new ProfessorDBFunction();
		studentdb = new StudentDBFunction();
	}

	public boolean loginUsernameCheck(String username)
	{
		Boolean answer = false;
		
		String registNumber = username;	//temp variable to keep username untouchable
		char ch = username.charAt(0);	//Keep the first char which is the role
		int regNum = 0;
		registNumber = registNumber.substring(1);
		regNum = Integer.valueOf(registNumber);

		switch(ch) {
			case 'S':
				answer = secretarydb.checkSecretaryAccount(regNum);
				break;
			case 'P':
				answer = professordb.checkProfessorAccount(regNum);
				break;
			case 'F':
				answer = studentdb.checkStudentAccount(regNum);
			    break;
			default:
				answer = false;
		}
		
		return answer;
	}
	
	public String getSalt(String username)
	{
		String salt = null;
		String registNumber = username;	//temp variable to keep username untouchable
		char ch = username.charAt(0);	//Keep the first char which is the role
		int regNum = 0;
		registNumber = registNumber.substring(1);
		regNum = Integer.valueOf(registNumber);

		switch(ch) {
			case 'S':
				salt = secretarydb.getSaltSecretaryAccount(regNum);
				break;
			case 'P':
				salt = professordb.getSaltProfessorAccount(regNum);
				break;
			case 'F':
				salt = studentdb.getSaltStudentAccount(regNum);
			    break;
			default:
				salt = null;
		}
		return salt;
	}
	
	public boolean passwordCheck(String username, String password)
	{
		boolean answer = false;
		
		String registNumber = username;	//temp variable to keep username untouchable
		char ch = username.charAt(0);	//Keep the first char which is the role
		int regNum = 0;
		registNumber = registNumber.substring(1);
		regNum = Integer.valueOf(registNumber);
		
		switch(ch) {
		case 'S':
			answer = secretarydb.signInSecretary(regNum, password);
			break;
		case 'P':
			answer = professordb.signInProfessor(regNum, password);
			break;
		case 'F':
			answer = studentdb.signInStudent(regNum, password);
		    break;
		default:
			answer = false;
		}
	
		return answer;
		
	}
	
	public String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++)
		{
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
			{
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	public String getAlphaNumericString (int n)
	{
		// chose a Character random from this String 
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		// create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n);
		
        for (int i = 0; i < n; i++)
        {
        	// generate a random number between 
            // 0 to AlphaNumericString variable length 
        	int index = (int)(AlphaNumericString.length() * Math.random());
        	// add Character one by one in end of string builder
        	sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
	}
}