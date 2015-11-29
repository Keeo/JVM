package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstUtf8Info extends ConstantPoolElement {
	public ConstUtf8Info(){
		this.tag = Constants.TAG_UTF8;
	}
}
