package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstDoubleInfo extends ConstantPoolElement {
	public ConstDoubleInfo(){
		this.tag = Constants.TAG_DOUBLE;
	}
}
