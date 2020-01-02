package org.xyl.define;

import java.io.File;
import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.Version;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

public class MyStopAnalyzer extends MMSegAnalyzer/*Analyzer*/ {
	
	@SuppressWarnings("rawtypes")
	private Set stops;
	
	private String[] self_stop_words={
			"a", "an", "and", "are", "as", "at", "be", "but", "by",
			"for", "if", "in", "into", "is", "it",
			"no", "not", "of", "on", "or", "such",
			"that", "the", "their", "then", "there", "these",
			"they", "this", "to", "was", "will", "with",
			"very" ,"I","also","like","��","��","��","��","��","��"
	};
	
	@SuppressWarnings("unchecked")
	public MyStopAnalyzer(String[] sws) {
		//���Զ����ַ�������ת��ΪSet
		stops = StopFilter.makeStopSet(Version.LUCENE_35, sws, true);
		//��ԭ�е�ͣ�ôʼ��뵽���ڵ�ͣ�ô�
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}
	
	@SuppressWarnings("unchecked")
	public MyStopAnalyzer(File file){
		super(file);
		//this(self_stop_words);
		stops = StopFilter.makeStopSet(Version.LUCENE_35, self_stop_words, true);
		stops.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}
	
	public MyStopAnalyzer() {
		//��ȡԭ�е�ͣ�ô�
		stops = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		
		//Ϊ����ִ����趨��������Tokenizer
		return new StopFilter(Version.LUCENE_35,
			   new LowerCaseFilter(Version.LUCENE_35, 
			   new LetterTokenizer(Version.LUCENE_35,reader)), stops);
	}

}



