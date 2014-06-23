package test;

import java.io.IOException;

import lucene.explore.NearRealTimeTest;
import org.junit.Test; 

public class MyTest {

	public static void main(String[] args) {

		
	}
	
	@Test  
    public void test() throws IOException {  
        //fail("Not yet implemented");  
        NearRealTimeTest nearRealTimeTest=new NearRealTimeTest();  
        nearRealTimeTest.nearRealTime();  
    } 

}
