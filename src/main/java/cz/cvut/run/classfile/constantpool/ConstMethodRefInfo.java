package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstMethodRefInfo extends ConstantPoolElement {
	public ConstMethodRefInfo(){
		this.tag = Constants.TAG_METHODREF;
	}
}
