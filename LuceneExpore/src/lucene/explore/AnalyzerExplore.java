package lucene.explore;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.Version;

public class AnalyzerExplore {
	private static final String[] examples = {

	"The quick brown fox jumped over the lazy dogs",

	"XY&Z Corporation - xyz@example.com"

	};

	public static final Analyzer[] ANALYZERS = new Analyzer[] {

		new WhitespaceAnalyzer(Version.LUCENE_48),
	
		new SimpleAnalyzer(Version.LUCENE_48),
	
		new StopAnalyzer(Version.LUCENE_48),
	
		new StandardAnalyzer(Version.LUCENE_48)

	};

	public static void main(String[] args) throws IOException {

		// Use the embedded example strings, unless

		// command line arguments are specified, then use those.

		String[] strings = examples;

		if (args.length > 0) {

			strings = args;

		}

		for (int i = 0; i < strings.length; i++) {

			analyze(strings[i]);

		}

	}

	public static void analyze(String text) throws IOException {

		System.out.println("Analyzing \"" + text + "\"");

		for (int i = 0; i < ANALYZERS.length; i++) {

			Analyzer analyzer = ANALYZERS[i];

			String name = analyzer.getClass().getName();

			name = name.substring(name.lastIndexOf(".") + 1);

			System.out.println(" " + name + ":");

			System.out.print(" ");

			displayTokens(analyzer, text);

			System.out.println("\n");

		}

	}

	public static void displayTokens(Analyzer analyzer, String text)
			throws IOException {

		TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));

		displayTokens(stream);
		stream.end();
		stream.close();

	}

	public static void displayTokens(TokenStream tokenStream) throws IOException {
		
		/**
		 *   3.0 code ====Deprecated
		 *  TokenStream tokenStream = analyzer.tokenStream(fieldName, reader);
			OffsetAttribute offsetAttribute = tokenStream.getAttribute(OffsetAttribute.class);
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
     
			while (tokenStream.incrementToken()) {
    			int startOffset = offsetAttribute.startOffset();
    			int endOffset = offsetAttribute.endOffset();
    			String term = termAttribute.term();
			}
		 */

		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		PositionIncrementAttribute positionAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);
		TypeAttribute typeAttr = tokenStream.addAttribute(TypeAttribute.class);
		CharTermAttribute charTerm = tokenStream.addAttribute(CharTermAttribute.class);
		int position = 0;
		//Must call reset before incrementToken
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			int increment = positionAttribute.getPositionIncrement();
			position+=increment;
		    System.out.printf("Position: %d, Start: %d, End: %d, Type: %s, Term: %s ;\n", position, offsetAttribute.startOffset(), offsetAttribute.endOffset(), typeAttr.type(), charTerm.toString());
		}

	}
}
