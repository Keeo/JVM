package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstIntegerInfo extends ConstantPoolElement {
	public ConstIntegerInfo(){
		this.tag = Constants.TAG_INTEGER;
	}
}
