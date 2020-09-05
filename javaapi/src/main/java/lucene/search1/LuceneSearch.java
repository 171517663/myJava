package lucene.search1;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

public class LuceneSearch {

    public static void doSearch(String indexDir , String queryStr) throws IOException, ParseException, InvalidTokenOffsetsException {
        Directory directory = FSDirectory.open(Paths.get(indexDir));
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser("tcontent",analyzer);
        Query query = parser.parse(queryStr);

        //IndexReader reader1 = searcher.getIndexReader();

        long startTime = System.currentTimeMillis();
        TopDocs docs = searcher.search(query,10);

        System.out.println("查找"+queryStr+"所用时间："+(System.currentTimeMillis()-startTime));
        System.out.println("查询到"+docs.totalHits+"条记录");


        //遍历查询结果
        for(ScoreDoc scoreDoc : docs.scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            String tcontent = doc.get("tcontent");
            if(tcontent != null){
                TokenStream tokenStream =  analyzer.tokenStream("tcontent", new StringReader(tcontent));
                //String summary = highlighter.getBestFragment(tokenStream, tcontent);
                //System.out.println(summary);
            }
        }
        reader.close();
    }

    public static void main(String[] args){
        String indexDir = "D:\\lucene";
        String q = "内容"; //查询这个字符串
        try {
            doSearch(indexDir, q);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



