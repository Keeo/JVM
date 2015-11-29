package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstFloatInfo extends ConstantPoolElement {
	public ConstFloatInfo(){
		this.tag = Constants.TAG_FLOAT;
	}
}
