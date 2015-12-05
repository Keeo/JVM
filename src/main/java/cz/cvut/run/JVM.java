package cz.cvut.run;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import cz.cvut.run.attributes.CodeAttribute;
import cz.cvut.run.utils.Utils;

import org.apache.log4j.Logger;


public class JVM {
    private static ArrayList<ClassLoader> classes = new ArrayList<ClassLoader>();
    private static final Logger log = Logger.getLogger(JVM.class);

    public static void main(String[] args) throws Exception {
        log.debug("=============== START of JVM  ==============");
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                classes.add(new ClassLoader(new File(args[i])));
                execute(classes.get(i).getClassFile());
            }
        } else {
            log.error("Please specify Class files in argumets!");
        }


        log.debug("================ END of JVM ================");
    }
    
    private static void execute(ClassFile cf){
    	Stack<Object> stack = new Stack<Object>();
    	for (int i=0; i<cf.getMethodsCount(); i++){
    		int index = cf.getMethods().get(i).getName_index();
    		String methodName = getStringFromConstantPool(cf, index);
    		if (methodName.equals("<init>")){
    			for(int j=0; j<cf.getMethods().get(i).getAttributesCount(); j++){
    				int indexName = cf.getMethods().get(i).getAttributes_info().get(j).getAttributeNameIndex();
    				if (getStringFromConstantPool(cf, indexName).equals("Code")){
    					CodeAttribute ca = (CodeAttribute) cf.getMethods().get(i).getAttributes_info().get(j);
    					insertToStack(stack, ca.getCode());
    					break;
    				}
    			}
    			
    			break;
    		}
    	}
    	while (!stack.isEmpty()){
    		Object o = stack.pop();
    		
    	
    	}
    	
    	
    	
    }
    
    private static String getStringFromConstantPool(ClassFile cf, int index){
    	return cf.getConstantPool().get(index-1).toString();
    }
    
    
    private static void insertToStack(Stack<Object> stack, byte[] input){
    	for(int i=input.length-1; i>=0; i--){
    		stack.push(input[i]);
    	}
    }
    
    
    
    
    
}
