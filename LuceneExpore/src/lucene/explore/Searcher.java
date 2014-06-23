package lucene.explore;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Searcher {

	public static void main(String[] args) {
		try {  
            Directory dir = FSDirectory.open(new File("./index"));  
            IndexReader reader = DirectoryReader.open(dir) ;  
            IndexSearcher searcher = new IndexSearcher(reader) ;  
            QueryParser parser = new QueryParser(Version.LUCENE_48,"content", new StandardAnalyzer(Version.LUCENE_48)) ;
            
            // +java +maven == java AND maven;   java maven == java OR maven
            // title:java -subject:sports == title:java AND NOT subject:sports
            Query query = parser.parse("China") ;  
            TopDocs docs = searcher.search(query, 10) ;  
            for(ScoreDoc doc: docs.scoreDocs){  
                Document document = searcher.doc(doc.doc) ;  
                System.out.println(document.get("docId")+"["+document.get("content")+"]");  
            }  
            //9.�ر�reader  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }catch (ParseException e) {  
            e.printStackTrace();  
        } 

	}

}
