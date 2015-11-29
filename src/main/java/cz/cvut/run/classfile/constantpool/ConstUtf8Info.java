package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;
import cz.cvut.run.utils.Utils;

public class ConstUtf8Info extends ConstantPoolElement {
	private int length;
	private byte[] bytes;
	
	public ConstUtf8Info(byte[] length, byte[] bytes){
		this.tag = Constants.TAG_UTF8;
		this.length = Utils.parseByteToInt(length);
		this.bytes = bytes;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public byte[] getBytes(){
		return this.bytes;
	}
	
}
