package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstFieldRefInfo extends ConstantPoolElement {
	public ConstFieldRefInfo(){
		this.tag = Constants.TAG_FIELDREF;
	}
}
