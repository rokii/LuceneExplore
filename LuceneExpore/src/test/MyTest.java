package test;

import java.io.IOException;

import lucene.explore.AnalyzerExplore;
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
	
	@Test
	public void testAnalyzer() throws IOException {
		final String[] examples = {

			"The quick brown fox jumped over the lazy dogs",

			"XY&Z Corporation - xyz@example.com"

			};
		for (int i = 0; i < examples.length; i++) {

			AnalyzerExplore.analyze(examples[i]);

		}
	}

}
