package org.xyl.define;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
//import org.xyl.util.analyzer.AnalyzerUtils;
//import org.xyl.util.analyzer.SimpleSamewordContext2;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.analysis.MMSegTokenizer;

//�Զ���ִ���--�ӿ�
interface SamewordContext {
	public String[] getSamewords(String name);
}

//�Զ���ִ���--ʵ����
class SimpleSamewordContext implements SamewordContext {
	
	Map<String,String[]> maps = new HashMap<String,String[]>();
	public SimpleSamewordContext() {
		maps.put("�й�",new String[]{"�쳯","��½"});
		maps.put("��",new String[]{"��","��"});
	}

	//@Override
	public String[] getSamewords(String name) {
		return maps.get(name);
	}

}

//�Զ���ִ���--�̳���
public class MySameAnalyzer extends Analyzer {
	
	private SamewordContext samewordContext;
	
	public MySameAnalyzer(SamewordContext swc) {
		samewordContext = swc;
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		//Dictionary dic = Dictionary.getInstance("D:\\tools\\javaTools\\lucene\\mmseg4j-1.8.5\\data");
		Dictionary dic = Dictionary.getInstance("\\data");
		return new MySameTokenFilter(
				new MMSegTokenizer(new MaxWordSeg(dic), reader),samewordContext);
	}

}
/*
class TestMySameAnalyzer{
	
	@Test
	public void test05() {
		try {
			Analyzer a2 = new MySameAnalyzer(new SimpleSamewordContext2());
			String txt = "�������й�������ͨ��������ͨʦר";
			Directory dir = new RAMDirectory();
			IndexWriter writer = new IndexWriter(dir,new IndexWriterConfig(Version.LUCENE_35, a2));
			Document doc = new Document();
			doc.add(new Field("content",txt,Field.Store.YES,Field.Index.ANALYZED));
			writer.addDocument(doc);
			writer.close();
			IndexSearcher searcher = new IndexSearcher(IndexReader.open(dir));
			TopDocs tds = searcher.search(new TermQuery(new Term("content","��")),10);
			AnalyzerUtils.displayAllTokenInfo(txt, a2);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
*/

