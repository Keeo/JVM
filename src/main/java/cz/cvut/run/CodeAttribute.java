package cz.cvut.run;

import cz.cvut.run.utils.Utils;

public class CodeAttribute {
	
	private byte[] max_stack = new byte[2];
	public int maxStack;
	
	private byte[] max_locals = new byte[2];
	public int maxLocals;
	
	private byte[] code_length = new byte[4];
	public int codeLength;
	public byte[] code;
	
	private byte[] exception_table_length = new byte[2];
	public int exceptionTableLength;
	public Object[] exception_table;
	
	private byte[] attributes_count = new byte[2];
	public int attributesCount;
	
	public byte[] attributes;
	
	
	/*
    u2 attributes_count;
    attribute_info* attributes;//[attributes_count];
	*/
	
	CodeAttribute(byte[] input){
		
		max_stack[0] = input[6];
		max_stack[1] = input[7];
		maxStack = Utils.parseByteToInt(max_stack);
		
		max_locals[0] = input[8];
		max_locals[1] = input[9];
		maxLocals = Utils.parseByteToInt(max_locals);
		
		code_length[0] = input[10];
		code_length[1] = input[11];
		code_length[2] = input[12];
		code_length[3] = input[13];
		codeLength = Utils.parseByteToInt(code_length);
		code = new byte[codeLength];
		int p = 13;
		for(; p<codeLength+13; p++){
			code[p-13] = input[p];
		}
		
		exceptionTableLength = Utils.parseByteToInt(exception_table_length);
		for(int i=0; i<exceptionTableLength; i++){
			p=p+i*8;
			byte[] tmp = new byte[8];
			tmp[0] = input[p];
			tmp[1] = input[p + 1];
			tmp[2] = input[p + 2];
			tmp[3] = input[p + 3];
			tmp[4] = input[p + 4];
			tmp[5] = input[p + 5];
			tmp[6] = input[p + 6];
			tmp[7] = input[p + 7];							
			exception_table[i] = new ExceptionTable(tmp);
		}
		p +=8;
		attributes_count[0] = input[p];
		attributes_count[1] = input[++p];
		attributesCount = Utils.parseByteToInt(attributes_count);
		attributes = new byte[input.length - p];
		for(int i=0; i<attributesCount; i++){
			attributes[i] = input[p++];
		}
		
	}
	
	private class ExceptionTable{
		private byte[] start_pc = new byte[2];
		private byte[] end_pc = new byte[2];
		private byte[] handler_pc = new byte[2];
		private byte[] catch_type = new byte[2];
		
		ExceptionTable(byte[] input){
			start_pc[0] = input[0];
			start_pc[1] = input[1];
			end_pc[0] = input[2];
			end_pc[1] = input[3];
			handler_pc[0] = input[4];
			handler_pc[1] = input[5];
			catch_type[0] = input[6];
			catch_type[1] = input[7];
		}
	}
	
	
}
