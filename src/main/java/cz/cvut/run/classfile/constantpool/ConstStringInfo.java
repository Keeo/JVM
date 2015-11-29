package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstStringInfo extends ConstantPoolElement {
	public ConstStringInfo(){
		this.tag = Constants.TAG_STRING;
	}
}
