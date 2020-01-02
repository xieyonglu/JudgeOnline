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
		//默认情况实现的评分是通过原有的评分*传入进来的评分域所获取的评分来确定最终打分的
		//为了根据不同的需求进行评分，需要自己进行评分的设定
		/**
		 * 自定评分的步骤
		 * 创建一个类继承于CustomScoreProvider
		 * 覆盖customScore方法
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
		 * subQueryScore表示默认文档的打分
		 * valSrcScore表示的评分域的打分
		 */
		@Override
		public float customScore(int doc, float subQueryScore, float valSrcScore)
				throws IOException {
			String a=author[doc];
			System.out.println(doc+"-------"+subQueryScore+"--------"+valSrcScore+"---"+a);
			
			//添加字符串相似度匹配
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
