package org.xyl.define;

import java.io.IOException;
import java.util.Stack;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.AttributeSource;
import org.junit.Test;

public class MySameTokenFilter extends TokenFilter {
	
	private CharTermAttribute cta = null;
	private PositionIncrementAttribute pia = null;
	private AttributeSource.State current;
	private Stack<String> sames = null;
	private SamewordContext samewordContext;

	protected MySameTokenFilter(TokenStream input,SamewordContext samewordContext) {
		super(input);
		cta = this.addAttribute(CharTermAttribute.class);
		pia = this.addAttribute(PositionIncrementAttribute.class);
		sames = new Stack<String>();
		this.samewordContext = samewordContext;
	}

	@Override
	public boolean incrementToken() throws IOException {
		if(sames.size()>0) {
			//��Ԫ�س�ջ�����һ�ȡ���ͬ���
			String str = sames.pop();
			//��ԭ״̬
			restoreState(current);
			cta.setEmpty();
			cta.append(str);
			//����λ��0
			pia.setPositionIncrement(0);
			return true;
		}
		
		if(!this.input.incrementToken()) return false;
		
		if(addSames(cta.toString())) {
			//�����ͬ��ʽ���ǰ״̬�ȱ���
			current = captureState();
		}
		return true;
	}
	
	private boolean addSames(String name) {
		String[] sws = samewordContext.getSamewords(name);
		if(sws!=null) {
			for(String str:sws) {
				sames.push(str);
			}
			return true;
		}
		return false;
	}
	
	
	//------------------------------------------------------------------------------------------
	@Test
	public void test02() {
		//ʹ��TermRangeFilter���з�Χ��������
		//1.��   2.��ʼλ��  3.����λ��   4.�Ƿ������ʼλ��    5.�Ƿ��������λ��
		Filter tr = new TermRangeFilter("filename", "java.hhh","java.she",true, true);
		//ʹ��NumericRangeFilter���й���
		tr = NumericRangeFilter.newIntRange("size",500,900,true,true);
		//����ͨ��һ��Query(QueryWrapperFilter)���й���
		tr = new QueryWrapperFilter(new WildcardQuery(new Term("filename","*.txt")));
		
		//st.searcherByFilter("java", tr);
		//tds = searcher.search(query,filter,50);
	}

}


