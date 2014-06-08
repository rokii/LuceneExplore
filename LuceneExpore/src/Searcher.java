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
            //1.����Directory  
            Directory dir = FSDirectory.open(new File("./index"));  
            //2.����IndexReader  
            IndexReader reader = DirectoryReader.open(dir) ;  
            //3.����IndexReader����IndexSearcher  
            IndexSearcher searcher = new IndexSearcher(reader) ;  
            //4.����������Query  
            //����parser��ȷ������������,�ڶ���������ʾ��������  
            QueryParser parser = new QueryParser(Version.LUCENE_48,"content", new StandardAnalyzer(Version.LUCENE_48)) ;  
            //����query����ʾ�������а���'Directory'���ĵ�  
            Query query = parser.parse("China") ;  
            //5.����search��������TopDocs��Ҫ���÷�������  
            TopDocs docs = searcher.search(query, 10) ;  
            //6.����TopDocs��ȡScoreDoc  
            for(ScoreDoc doc: docs.scoreDocs){  
                //7.����searcher��scoredoc��ȡ�����Document����  
                Document document = searcher.doc(doc.doc) ;  
                //8.����Document�����ȡ��Ҫ������  
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
