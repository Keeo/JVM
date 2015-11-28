package cz.cvut.run;

import java.io.File;
import java.util.ArrayList;

public class JVM {
    private static ArrayList<ClassLoader> classes = new ArrayList<ClassLoader>();
    
	public static void main(String[] args) throws Exception {
        System.out.println("=============== START of JVM  ==============");
        if(args != null && args.length > 0){
        	for(int i=0; i< args.length; i++){
        		classes.add(new ClassLoader(new File(args[i])));
        	}
        }else{
        	System.out.println("Please specify Class files in argumets!");
        }
        
        
        
        
        System.out.println("================ END of JVM ================");
    }
}
