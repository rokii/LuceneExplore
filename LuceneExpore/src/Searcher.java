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
            //1.创建Directory  
            Directory dir = FSDirectory.open(new File("./index"));  
            //2.创建IndexReader  
            IndexReader reader = DirectoryReader.open(dir) ;  
            //3.根据IndexReader创建IndexSearcher  
            IndexSearcher searcher = new IndexSearcher(reader) ;  
            //4.创建搜索的Query  
            //创建parser来确定搜索的内容,第二个参数表示搜索的域  
            QueryParser parser = new QueryParser(Version.LUCENE_48,"content", new StandardAnalyzer(Version.LUCENE_48)) ;  
            //创建query，表示搜索域中包含'Directory'的文档  
            Query query = parser.parse("China") ;  
            //5.根据search搜索返回TopDocs，要设置返回条数  
            TopDocs docs = searcher.search(query, 10) ;  
            //6.根据TopDocs获取ScoreDoc  
            for(ScoreDoc doc: docs.scoreDocs){  
                //7.根据searcher和scoredoc获取具体的Document对象  
                Document document = searcher.doc(doc.doc) ;  
                //8.根据Document对象获取需要的内容  
                System.out.println(document.get("docId")+"["+document.get("content")+"]");  
            }  
            //9.关闭reader  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }catch (ParseException e) {  
            e.printStackTrace();  
        } 

	}

}
