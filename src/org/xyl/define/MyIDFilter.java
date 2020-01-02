package org.xyl.define;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.Filter;
import org.apache.lucene.util.OpenBitSet;

interface FilterAccessor {

	public String[] values();
	
	public String getField();
	
	public boolean set();
}


public class MyIDFilter extends Filter {
	
	private FilterAccessor accessor;
	
	public MyIDFilter(FilterAccessor accessor) {
		this.accessor = accessor;
	}

	@Override
	public DocIdSet getDocIdSet(IndexReader reader) throws IOException {
		//����һ��bit,Ĭ�����е�Ԫ�ض���0
		OpenBitSet obs = new OpenBitSet(reader.maxDoc());
		if(accessor.set()) {
			set(reader,obs);
		} else {
			clear(reader, obs);
		}
		return obs;
	}
	
	private void set(IndexReader reader,OpenBitSet obs) {
		try {
			int[] docs = new int[1];
			int [] freqs = new int[1];
			//��ȡid���ڵ�doc��λ�ã����ҽ�������Ϊ0
			for(String delId:accessor.values()) {
				//��ȡTermDocs
				TermDocs tds = reader.termDocs(new Term(accessor.getField(),delId));
				//�����ѯ�����Ķ����λ�ô洢��docs�У����ֵ�Ƶ�ʴ洢��freqs�У����ػ�ȡ������
				int count = tds.read(docs, freqs);
				if(count==1) {
					obs.set(docs[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void clear(IndexReader reader,OpenBitSet obs) {
		try {
			//�Ȱ�Ԫ������
			obs.set(0,reader.maxDoc());
			int[] docs = new int[1];
			int [] freqs = new int[1];
			//��ȡid���ڵ�doc��λ�ã����ҽ�������Ϊ0
			for(String delId:accessor.values()) {
				//��ȡTermDocs
				TermDocs tds = reader.termDocs(new Term(accessor.getField(),delId));
				//�����ѯ�����Ķ����λ�ô洢��docs�У����ֵ�Ƶ�ʴ洢��freqs�У����ػ�ȡ������
				int count = tds.read(docs, freqs);
				if(count==1) {
					//�����λ�õ�Ԫ��ɾ��
					obs.clear(docs[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


