package cz.cvut.run.attributes;

import java.util.ArrayList;

import cz.cvut.run.classfile.Attribute;
import cz.cvut.run.utils.Utils;

public class LocalVariableTableAttribute extends Attribute{
	private int localVariableTableLength;
	private ArrayList<LocalVariable> localVariables = new ArrayList<LocalVariable>();
	
	public LocalVariableTableAttribute(byte[] attributeNameIndex, byte[] attributeLength) {
		super(attributeNameIndex, attributeLength);
		
	}
	
	@Override
	public void setAttributeInfo(byte[] attributeInfo) {
		System.out.println(Utils.getHexa(attributeInfo));
		int p =0;
		this.localVariableTableLength = Utils.parseByteToInt(new byte[]{attributeInfo[p++], attributeInfo[p++]});
		for(int i=0; i<this.localVariableTableLength; i++){
			byte[] tmp = new byte[10];
			for(int j=0; j<10;j++){
				tmp[j] = attributeInfo[p++];
			}
			localVariables.add(new LocalVariable(tmp));
		}
	}
	
	
	private class LocalVariable{
		private byte[] startPc = new byte[2];
		private byte[] length = new byte[2];
		private byte[] nameIndex = new byte[2];
		private byte[] descriptionIndex = new byte[2];
		private byte[] index = new byte[2];
		
		LocalVariable(byte[] input){
			this.startPc[0] = input[0];
			this.startPc[1] = input[1];
			
			this.length[0] = input[2];
			this.length[1] = input[3];
		
			this.nameIndex[0] = input[4];
			this.nameIndex[1] = input[5];
			
			this.descriptionIndex[0] = input[6];
			this.descriptionIndex[1] = input[7];
			
			this.index[0] = input[8];
			this.index[1] = input[9];
		}
		
		public byte[] getStartPc() {
			return startPc;
		}

		public byte[] getLength() {
			return length;
		}

		public byte[] getNameIndex() {
			return nameIndex;
		}

		public byte[] getDescriptionIndex() {
			return descriptionIndex;
		}

		public byte[] getIndex() {
			return index;
		}		
		
	}
}
