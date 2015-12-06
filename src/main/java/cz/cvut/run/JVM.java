package cz.cvut.run;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import cz.cvut.run.attributes.CodeAttribute;
import cz.cvut.run.classfile.Method;
import cz.cvut.run.constants.Constants;
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
    
    private static void execute(ClassFile cf) throws Exception{
    	Stack<Byte> stack = new Stack<Byte>();
    	Method init = cf.getInitMethod();
    	int codeIndex = cf.getCodeIndex();
    	byte[] initCode = init.getCode(codeIndex);
    	insertToStack(stack, initCode);
    	Method m = init;
    	while (!stack.isEmpty()){
    		byte instruction = stack.pop();
    		Utils.getInstructionName(instruction);
    		log.info("Instruction: " + Utils.getInstructionName(instruction));
    		
    		switch (instruction){
				case Constants.INSTRUCTION_aconst_null: {
					break;
				}
				case Constants.INSTRUCTION_aload: {
					break;
				}
				case Constants.INSTRUCTION_aload_0: {
					CodeAttribute ca = (CodeAttribute) m.getAttributes_info().get(0);
					System.out.println(ca.getAttributesCount());
					int indexName = ca.getAttributes().get(0).getAttributeNameIndex()-1;
					System.out.println(cf.getConstantPool().get(indexName).toString());
					System.out.println(Utils.getHexa(ca.getAttributes().get(0).getAttributeInfo()));
					System.out.println(Utils.getHexa(ca.getAttributes().get(1).getAttributeInfo()));
					//System.out.println(Utils.getHexa(m.getAttributes_info().get(0).getAttributeInfo()));
					break;
				}
				case Constants.INSTRUCTION_aload_1: {
					break;
				}
				case Constants.INSTRUCTION_aload_2: {
					break;
				}
				case Constants.INSTRUCTION_aload_3: {
					break;
				}
				case Constants.INSTRUCTION_areturn: {
					break;
				}
				case Constants.INSTRUCTION_arraylength: {
					break;
				}
				case Constants.INSTRUCTION_astore: {
					break;
				}
				case Constants.INSTRUCTION_astore_1: {
					break;
				}
				case Constants.INSTRUCTION_astore_2: {
					break;
				}
				case Constants.INSTRUCTION_astore_3: {
					break;
				}
				case Constants.INSTRUCTION_baload: {
					break;
				}
				case Constants.INSTRUCTION_bastore: {
					break;
				}
				case Constants.INSTRUCTION_bipush: {
					break;
				}
				case Constants.INSTRUCTION_dup: {
					break;
				}
				case Constants.INSTRUCTION_getfield: {
					break;
				}
				case Constants.INSTRUCTION_getstatic: {
					break;
				}
				case Constants.INSTRUCTION_goto: {
					break;
				}
				case Constants.INSTRUCTION_checkcast: {
					break;
				}
				case Constants.INSTRUCTION_i2c: {
					break;
				}
				case Constants.INSTRUCTION_iadd: {
					break;
				}
				case Constants.INSTRUCTION_iand: {
					break;
				}
				case Constants.INSTRUCTION_iconst_0: {
					break;
				}
				case Constants.INSTRUCTION_iconst_1: {
					break;
				}
				case Constants.INSTRUCTION_iconst_2: {
					break;
				}
				case Constants.INSTRUCTION_iconst_3: {
					break;
				}
				case Constants.INSTRUCTION_if_icmpge: {
					break;
				}
				case Constants.INSTRUCTION_if_icmpne: {
					break;
				}
				case Constants.INSTRUCTION_ifeq: {
					break;
				}
				case Constants.INSTRUCTION_ifle: {
					break;
				}
				case Constants.INSTRUCTION_iflt: {
					break;
				}
				case Constants.INSTRUCTION_ifne: {
					break;
				}
				case Constants.INSTRUCTION_ifnull: {
					break;
				}
				case Constants.INSTRUCTION_iinc: {
					break;
				}
				case Constants.INSTRUCTION_iload: {
					break;
				}
				case Constants.INSTRUCTION_iload_1: {
					break;
				}
				case Constants.INSTRUCTION_iload_2: {
					break;
				}
				case Constants.INSTRUCTION_iload_3: {
					break;
				}
				case Constants.INSTRUCTION_imul: {
					break;
				}
				case Constants.INSTRUCTION_invokespecial: {
					break;
				}
				case Constants.INSTRUCTION_invokestatic: {
					break;
				}
				case Constants.INSTRUCTION_invokevirtual: {
					break;
				}
				case Constants.INSTRUCTION_ireturn: {
					break;
				}
				case Constants.INSTRUCTION_ishl: {
					break;
				}
				case Constants.INSTRUCTION_istore: {
					break;
				}
				case Constants.INSTRUCTION_istore_2: {
					break;
				}
				case Constants.INSTRUCTION_istore_3: {
					break;
				}
				case Constants.INSTRUCTION_isub: {
					break;
				}
				case Constants.INSTRUCTION_ldc: {
					break;
				}
				case Constants.INSTRUCTION_new: {
					break;
				}
				case Constants.INSTRUCTION_newarray: {
					break;
				}
				case Constants.INSTRUCTION_pop: {
					break;
				}
				case Constants.INSTRUCTION_putfield: {
					break;
				}
				case Constants.INSTRUCTION_nop: {
					break;
				}
				case Constants.INSTRUCTION_return: {
					break;
				}
				default:{
					log.error("Unsupported instruction: " + Utils.getHexa(instruction));
				}
			}
    	}
    	
    	
    	
    }
    
    
    private static void insertToStack(Stack<Byte> stack, byte[] input){
    	for(int i=input.length-1; i>=0; i--){
    		stack.push(input[i]);
    	}
    }
    
    
    
    
    
}
