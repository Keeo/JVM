package fit.cvut.run.test;

import org.junit.Test;

import cz.cvut.run.JVM;

public class ApplicationTest {
	private static final String TEST_CLASSES_PATH = "\\target\\test-classes\\fit\\cvut\\run\\test";
	
	private static final String DELIMITER = "\\";
	
	private static final String TestClassFile001 = DELIMITER + "TestClassFile001.class";
	
    @Test
    public void testMain() throws Exception { // spuštìní prázdné aplikace projde bez vyjímky
        JVM.main(null);
    }
    
    @Test(expected=java.lang.Exception.class)
    public void testDirectory() throws Exception{ // Spuštìní s odkazem na adresáø
    	JVM.main(new String[] {new java.io.File( "." ).getCanonicalPath()+TEST_CLASSES_PATH});
    }
    
    @Test(expected=java.lang.Exception.class)
    public void testWrongPath() throws Exception{ // Spuštìní s odkazem na neplatnou cestu
    	JVM.main(new String[] {"wrong path"});
    }
    
    @Test()
    public void testSimpleRightPath() throws Exception{ // Jednoduché puštìní s odkazem na platnou cestu
    	JVM.main(new String[] {new java.io.File( "." ).getCanonicalPath()+TEST_CLASSES_PATH + TestClassFile001});
    }
}
