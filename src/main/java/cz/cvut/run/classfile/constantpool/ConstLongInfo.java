package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstLongInfo extends ConstantPoolElement {
	public ConstLongInfo(){
		this.tag = Constants.TAG_LONG;
	}
}
