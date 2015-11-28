package cz.cvut.run.utils;

public class Utils {

	public static String getString(byte[] input){
		if (input == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(input.length);
		for (int i=0; i<input.length; i++){
			sb.append(input[i] + " ");
		}
		return sb.toString();
	}
}
