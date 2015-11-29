package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstClassInfo extends ConstantPoolElement {

	public ConstClassInfo(){
		this.tag = Constants.TAG_CLASS;
	}
}
