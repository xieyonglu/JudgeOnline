package org.xyl.define;

import java.io.IOException;
import java.util.Date;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.function.CustomScoreProvider;
import org.apache.lucene.search.function.CustomScoreQuery;
import org.apache.lucene.search.function.ValueSourceQuery;

public class MyCustomScoreQuery extends CustomScoreQuery {
	
	public MyCustomScoreQuery(Query subQuery, ValueSourceQuery valSrcQuery) {
		super(subQuery, valSrcQuery);
	}
	
	@Override
	protected CustomScoreProvider getCustomScoreProvider(IndexReader reader)
			throws IOException {
		//Ĭ�����ʵ�ֵ�������ͨ��ԭ�е�����*�������������������ȡ��������ȷ�����մ�ֵ�
		//Ϊ�˸��ݲ�ͬ������������֣���Ҫ�Լ��������ֵ��趨
		/**
		 * �Զ����ֵĲ���
		 * ����һ����̳���CustomScoreProvider
		 * ����customScore����
		 */
		return new MyCustomScoreProvider(reader);
	}
	
	private class MyCustomScoreProvider extends CustomScoreProvider {

		private int[] browser;
		private String[] author;
		private Date[] createDate;
		
		public MyCustomScoreProvider(IndexReader reader) {
			super(reader);
			try{
				browser = FieldCache.DEFAULT.getInts(reader, "browser");
				author=FieldCache.DEFAULT.getStrings(reader, "author");
				//createDate=FieldCache.DEFAULT.getLongs(reader, "createDate");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		/**
		 * subQueryScore��ʾĬ���ĵ��Ĵ��
		 * valSrcScore��ʾ��������Ĵ��
		 */
		@Override
		public float customScore(int doc, float subQueryScore, float valSrcScore)
				throws IOException {
			String a=author[doc];
			System.out.println(doc+"-------"+subQueryScore+"--------"+valSrcScore+"---"+a);
			
			//����ַ������ƶ�ƥ��
			//FuzzyQuery query=new FuzzyQuery(new Term("name","mask",0.4f,0));
			//System.out.println(query.getPrefixLength());
			//System.out.println(query.getMinSimilarity());
			//TopDocs tds=searcher.search(query,num);
			
			if(a.equals("tking")){
				return subQueryScore*10;
			}else{
				return subQueryScore;
			}
		}
		
	}
	
}
