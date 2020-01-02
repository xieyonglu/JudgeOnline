package org.xyl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
 
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NRTManager;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.function.FieldScoreQuery;
import org.apache.lucene.search.function.FieldScoreQuery.Type;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;

import org.springframework.stereotype.Service;
import org.xyl.idao.INoticeDao;
import org.xyl.index.LuceneContext;
import org.xyl.iservice.IIndexService;
import org.xyl.index.IndexUtil;
import org.xyl.util.PageBean;
import org.xyl.define.AnalyzerUtils;
import org.xyl.define.MyCustomScoreQuery;
import org.xyl.define.CustomParser;
import org.xyl.bean.Notice;
import org.xyl.bo.Index;
import org.xyl.bo.IndexField;

@Service("indexService")
public class IndexService implements IIndexService {
	
	private INoticeDao noticeDao;
	
	@Resource
	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	//@Override
	public void addIndex(IndexField field,boolean inDatabase) {
		try {
			
			if(inDatabase){
				//把数据保存到数据库中
			}
			
			Document doc = new Document();
			doc.add(new NumericField("noticeId",Field.Store.YES,true).setLongValue(field.getNoticeId()));
			doc.add(new Field("title",field.getTitle(),Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new Field("author",field.getAuthor(),Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new Field("content",field.getContent(),Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new NumericField("browser",Field.Store.YES,true).setIntValue(field.getBrowser()));
			doc.add(new NumericField("createDate",Field.Store.YES,true).setLongValue(field.getCreateDate().getTime()));
			
			LuceneContext.getInstance().getNRTManager().addDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//@Override
	public void deleteIndex(String id) {
		try {
			/*TempIndex ti = new TempIndex();
			ti.setDelete();
			///xx_xx
			ti.setId(Integer.parseInt(id));
			ti.setType(type);
			tempIndexDao.add(ti);*/
			LuceneContext.getInstance().getNRTManager().deleteDocuments(new Term("noticeId",id));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//@Override
	public void deleteAllIndex(){
		try {
			LuceneContext.getInstance().getNRTManager().deleteAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//@Override
	public void updateIndex(IndexField field) {
		try {
			/*TempIndex ti = new TempIndex();
			ti.setDelete();
			///xx_xx
			ti.setId(fields.getObjId());
			ti.setType(fields.getType());
			tempIndexDao.add(ti);*/
			NRTManager nrtMgr = LuceneContext.getInstance().getNRTManager();
			
			Document doc = new Document();
			doc.add(new NumericField("noticeId",Field.Store.YES,true).setLongValue(field.getNoticeId()));
			doc.add(new Field("title",field.getTitle(),Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new Field("author",field.getAuthor(),Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new Field("content",field.getContent(),Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new NumericField("browser",Field.Store.YES,true).setIntValue(field.getBrowser()));
			doc.add(new NumericField("createDate",Field.Store.YES,true).setLongValue(field.getCreateDate().getTime()));
			
			nrtMgr.updateDocument(new Term("noticeId",field.getNoticeId()+""), doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//@Override
	public void updateSetIndex() {
		/*List<TempIndex> tis = tempIndexDao.list("from TempIndex");
		for(TempIndex ti:tis) {
			if(ti.getType().equals(IndexUtil.MSG_TYPE)) {
				Message msg = messageDao.load(ti.getObjId());
				indexMessage(msg);
			}
		}*/
		LuceneContext.getInstance().commitIndex();
		//tempIndexDao.delAll();
	}
	
	//@Override
	public void commitIndex() {
		//tempIndexDao.delAll();
		LuceneContext.getInstance().commitIndex();
	}
	
	//@Override
	public void reConstructorIndex() {
		/**
		 * 将数据库中的所有对象取出，创建相应的IndexField完成索引的重构
		 */
		try {
			LuceneContext.getInstance().getNRTManager().deleteAll();
			List<Notice> notices = noticeDao.list("from Notice");
			indexMessages(notices);
			LuceneContext.getInstance().commitIndex();
			//tempIndexDao.delAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//@Override
	public PageBean<Index> findByIndex(String condition,int pageNumber,
			int pageSize) {
		if(condition==null) condition = "";
		IndexSearcher searcher = LuceneContext.getInstance().getSearcher();
		PageBean<Index> pageBean = new PageBean<Index>();
		List<Index> list = new ArrayList<Index>();
		try {

			//自定义CustomParser，防止关键字的输入是不符合条件的
			MultiFieldQueryParser parser = new CustomParser(LuceneContext.getInstance().getVersion(),
						new String[]{"title","content"}, LuceneContext.getInstance().getAnalyzer());
			String str=AnalyzerUtils.displayAllTokenInfo(condition, LuceneContext.getInstance().getAnalyzer());
			
			
			Query query = parser.parse(str);
			
			//1、创建一个评分域
			FieldScoreQuery fd = new FieldScoreQuery("author",Type.INT);
			//2、根据评分域和原有的Query创建自定义的Query对象
			MyCustomScoreQuery q = new MyCustomScoreQuery(query,fd);
			
			int allRow = searcher.search(q,Integer.MAX_VALUE).totalHits;
			int totalPage = PageBean.countTotalPage(pageSize, allRow);
			
			int length = pageSize;
			int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
			int offset = PageBean.countOffset(pageSize, currentPage);
			//TopDocs tds = searcher.searchAfter(this.getLastDoc(offset,searcher,q),q,length);
			TopDocs tds=searcher.search(q, Integer.MAX_VALUE,new Sort(
			        new SortField[]{//排序 false 升序 true降序
			        		SortField.FIELD_SCORE,//按评分降序
			        		new SortField("browser",SortField.INT),//按browser升序
			                new SortField("createDate",SortField.LONG, true)//按createDate降序
			        }
			 ));
			//int totalRecord = tds.totalHits;
			
			System.out.println(str+"=========="+tds.scoreDocs.length);
			
			/*BooleanClause.Occur [] occur = {BooleanClause.Occur.MUST};    
			 在此处的create参数中指定分页    
			TopScoreDocCollector collector = TopScoreDocCollector.create(offset+1, false);
			Query qq = MultiFieldQueryParser.parse(LuceneContext.getInstance().getVersion(), str, new String[]{"title","content"},occur, LuceneContext.getInstance().getAnalyzer());     
			 按照指定的查询条件(query) 将查询后的分页结果存放到collector 集合中    
			searcher.search(q, collector,new Sort(
			        new SortField[]{//排序 false 升序 true降序
			        		SortField.FIELD_SCORE,//按评分降序
			        		new SortField("browser",SortField.INT),//按browser升序
			                new SortField("createDate",SortField.LONG, true)//按createDate降序
			        }
			 ));    
			 下面得到的这个hits数组即为你想要的结果，循环这个数组即可得到每个搜索的结果
			ScoreDoc[] docs = collector.topDocs(offset,
					pageSize).scoreDocs;
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.out.println(hits.length+"-------****---------");*/
			
			//查询起始记录位置 
	        int begin = pageSize*(currentPage-1)<0?0:pageSize*(currentPage-1); 
	        //查询终止记录位置 
	        int end = Math.min(begin+pageSize,tds.scoreDocs.length); 
	        ScoreDoc[] s=tds.scoreDocs;
	       
	        for(int i=begin;i<end;i++){
				Document doc = searcher.doc(s[i].doc);
				Index index = new Index();
				
				index.setNoticeId(Long.parseLong(doc.get("noticeId")));
				index.setTitle(highligher(doc.get("title"),query,"title"));
				index.setAuthor(doc.get("author"));
				index.setBrowser(Integer.parseInt(doc.get("browser")));
				index.setCreateDate(new Date(Long.parseLong(doc.get("createDate"))));
				index.setContent(highligher(doc.get("content"),query,"content"));
				
				list.add(index);
				
				System.out.println("=====add===");
			}
			
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.init();
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			LuceneContext.getInstance().releaseSearcher(searcher);
		}
		return pageBean;
	}
	
	private String highligher(String text,Query query,String field) {
		try {
			QueryScorer scorer = new QueryScorer(query);
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
			Formatter formatter = new SimpleHTMLFormatter("<span style='color:red;'><b>","</b></span>");
			Highlighter lighter = new Highlighter(formatter,scorer);
			lighter.setTextFragmenter(fragmenter);
			String ht = lighter.getBestFragment(LuceneContext.getInstance().getAnalyzer(),
					field,text);
			if(ht==null) {
				if(text.length()>=20) {
					text = text.substring(0, 20);
					text=text+"....";
				}
				return text;
			}
			else return ht.trim();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	private ScoreDoc getLastDoc(int pageOffset,IndexSearcher searcher,Query query) {
		if(pageOffset<=0) return null;
		try {
			
			TopDocs tds = searcher.search(query,pageOffset,new Sort(
			        new SortField[]{//排序 false 升序 true降序
			        		SortField.FIELD_SCORE,//按评分降序
			        		new SortField("browser",SortField.INT),//按browser升序
			                new SortField("createDate",SortField.LONG, true)//按createDate降序
			        }
			 ));
			return tds.scoreDocs[pageOffset-1];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void indexMessages(List<Notice> notices) {
		for(Notice notice:notices) {
			indexMessage(notice);
		}
	}
	
	private void indexMessage(Notice notice) {
		IndexField field = IndexUtil.msg2IndexField(notice);
		this.addIndex(field,false);
	}

}


