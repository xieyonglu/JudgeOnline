package org.xyl.define;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class CustomFilter {

	public void searchByCustomFilter() {
		try {
			Directory directory = FSDirectory.open(new File("d:/lucene/files/"));
			IndexSearcher searcher = new IndexSearcher(IndexReader.open(directory));
			Query q = new TermQuery(new Term("content","java"));
			TopDocs tds = null;
			tds = searcher.search(q, new MyIDFilter(new FilterAccessor() {
				//@Override
				public String[] values() {
					return new String[]{"Ant.she","Ant.txt","java.txt"};
				}
				//@Override
				public boolean set() {
					return true;
				}
				//@Override
				public String getField() {
					return "filename";
				}
			}),200);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for(ScoreDoc sd:tds.scoreDocs) {
				Document d = searcher.doc(sd.doc);
				System.out.println(sd.doc+":("+sd.score+")" +
						"["+d.get("filename")+"¡¾"+d.get("path")+"¡¿--->"+
						d.get("size")+"------------>"+d.get("id"));
			}
			searcher.close();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



