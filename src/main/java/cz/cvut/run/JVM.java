package cz.cvut.run;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
               
            }
            execute(classes.get(0).getClassFile());
        } else {
            log.error("Please specify Class files in argumets!");
        }


        log.debug("================ END of JVM ================");
    }
    
    private static void execute(ClassFile cf) throws Exception{
    	Stack<Byte> stack = new Stack<Byte>();
    	LinkedList<Byte> byteCode = new LinkedList<Byte>();
    	Method init = cf.getInitMethod();
    	Method main = cf.getMainMethod();
    	int codeIndex = cf.getCodeIndex();
    	
    	byte[] initCode = init.getCode(codeIndex);
    	byte[] mainCode = main.getCode(codeIndex);
    	
    	for(int i=0; i<initCode.length; i++){
    		byteCode.add(initCode[i]);
    	}
    	for(int i=0; i<mainCode.length; i++){
    		byteCode.add(mainCode[i]);
    	}
    	int pc = 0;
    	while (!byteCode.isEmpty()){
    		byte instruction = byteCode.get(pc);
    		pc++;
    		Utils.getInstructionName(instruction);
    		log.info("Instruction: " + Utils.getInstructionName(instruction));
    		
    		switch (instruction){
				case Constants.INSTRUCTION_aconst_null: {
					// neber zadne atributy z bytecode
					break;
				}
				case Constants.INSTRUCTION_aload: {
					byte index = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_aload_0: {
					// nebere zadne atributy z bytecode
					
					
					/*CodeAttribute ca = (CodeAttribute) m.getAttributes_info().get(0);
					System.out.println(ca.getAttributesCount());
					int indexName = ca.getAttributes().get(0).getAttributeNameIndex()-1;
					int indexName2 = ca.getAttributes().get(1).getAttributeNameIndex()-1;
					System.out.println(Utils.getHexa(initCode));
					System.out.println(cf.getConstantPool().get(indexName2).toString());
					System.out.println(Utils.getHexa(ca.getAttributes().get(0).getAttributeInfo()));
					System.out.println(Utils.getHexa(ca.getAttributes().get(1).getAttributeInfo()));
					//System.out.println(Utils.getHexa(m.getAttributes_info().get(0).getAttributeInfo()));*/
					break;
				}
				case Constants.INSTRUCTION_aload_1: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_aload_2: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_aload_3: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_areturn: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_arraylength: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_astore: {
					byte index = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_astore_1: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_astore_2: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_astore_3: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_baload: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_bastore: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_bipush: {
					byte _byte = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_dup: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_getfield: {
					byte index1 = byteCode.get(++pc);
					byte index2 = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_getstatic: {
					byte index1 = byteCode.get(++pc);
					byte index2 = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_goto: {
					byte branchbyte1 = byteCode.get(++pc);
					byte branchbyte2 = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_checkcast: {
					byte index1 = byteCode.get(++pc);
					byte index2 = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_i2c: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				
				case Constants.INSTRUCTION_iadd: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_iand: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_iconst_0: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_iconst_1: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_iconst_2: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_iconst_3: {
					// nebere zadne atributy z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_if_icmpge: {
					byte branchbyte1 = byteCode.get(++pc);
					byte branchbyte2 = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_if_icmpne: {
					byte branchbyte1 = byteCode.get(++pc);
					byte branchbyte2 = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_ifeq: {
					byte branchbyte1 = byteCode.get(++pc);
					byte branchbyte2 = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_ifle: {
					byte branchbyte1 = byteCode.get(++pc);
					byte branchbyte2 = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_iflt: {
					byte branchbyte1 = byteCode.get(++pc);
					byte branchbyte2 = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_ifne: {
					byte branchbyte1 = byteCode.get(++pc);
					byte branchbyte2 = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_ifnull: {
					byte branchbyte1 = byteCode.get(++pc);
					byte branchbyte2 = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_iinc: {
					byte index = byteCode.get(++pc);
					byte _const = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_iload: {
					byte index = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_iload_1: {
					// neni treba nic brat z bytecode
					break;
				}
				case Constants.INSTRUCTION_iload_2: {
					// neni treba nic brat z bytecode
					break;
				}
				case Constants.INSTRUCTION_iload_3: {
					// neni treba nic brat z bytecode
					break;
				}
				case Constants.INSTRUCTION_imul: {
					// neni treba nic brat z bytecode
					break;
				}
				case Constants.INSTRUCTION_invokespecial: {
					byte arg0 = byteCode.get(++pc);
					byte arg1 = byteCode.get(++pc);
					log.info("invokespecial args: " + Utils.getHexa(arg0) + " " + Utils.getHexa(arg1));
					Instructions.invokeSpecial(stack, arg0, arg1, cf);
					break;
				}
				case Constants.INSTRUCTION_invokestatic: {
					byte index1 = byteCode.get(++pc);
					byte index2 = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_invokevirtual: {
					byte index1 = byteCode.get(++pc);
					byte index2 = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_ireturn: {
					// neni nic treba brat z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_ishl: {
					// neni nic treba brat z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_istore: {
					byte index = byteCode.get(++pc);
					
					break;
				}
				case Constants.INSTRUCTION_istore_2: {
					// neni nic treba brat z bytecode
					break;
				}
				case Constants.INSTRUCTION_istore_3: {
					// neni nic treba brat z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_isub: {
					// neni nic treba brat z bytecode
					break;
				}
				case Constants.INSTRUCTION_ldc: {
					byte index = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_new: {
					byte index1 = byteCode.get(++pc);
					byte index2 = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_newarray: {
					byte type = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_pop: {
					// neni nic treba brat z bytecode
					break;
				}
				case Constants.INSTRUCTION_putfield: {
					byte index1 = byteCode.get(++pc);
					byte index2 = byteCode.get(++pc);
					break;
				}
				case Constants.INSTRUCTION_nop: {
					// neni nic treba brat z bytecode
					
					break;
				}
				case Constants.INSTRUCTION_return: {
					// neni nic treba brat z bytecode
					stack.clear();
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
