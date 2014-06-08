import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Indexer {
	
	public Indexer(String sdir) throws IOException{
		Directory dir = FSDirectory.open(new File(sdir));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_48);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
		writer = new IndexWriter(dir, config);
		
	}
	private IndexWriter writer;
	
	public void indexString(String id, String content) throws IOException{
		
		Document d = new Document();
		d.add(new StringField("docId",id,Field.Store.YES));
		d.add(new TextField("content",content,Field.Store.YES));
		writer.addDocument(d);
	}
	public void close() throws IOException{
		writer.close();
	}
	
	public static void main(String[] args){
		try {
			Indexer indexer = new Indexer("./index");
			
			indexer.indexString("1234","I come from China.");
			indexer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
