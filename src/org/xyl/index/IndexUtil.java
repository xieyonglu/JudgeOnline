package org.xyl.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.xyl.bean.Notice;
import org.xyl.bo.IndexField;

public class IndexUtil {
	public static final String MSG_TYPE = "Notice";
	public static final String ATTACHMENT_TYPE = "Attachment";
	
	public final static int ADD_OP = 1;
	public final static int DEL_OP = 2;
	public final static int UPDATE_OP = 3;
	
	public static List<String> indexAttachType = null;
	static {
		indexAttachType = new ArrayList<String>();
		indexAttachType.add("doc");
		indexAttachType.add("docx");
		indexAttachType.add("pdf");
		indexAttachType.add("txt");
	}
	
	/**
	 * 把附件的内容添加到field的content中
	 * @param fieldContents
	 * @param att
	 */
	/*
	public static void attach2Index(List<String> fieldContents,List<String> atths,Attachment att) {
		try {
			String filename = att.getNewName();
			if(IndexUtil.indexAttachType.contains(FilenameUtils.getExtension(filename))) {
				fieldContents.add(file2String(att.getNewName()));
				atths.add(att.getNewName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}
	
	public static String file2String(String name) throws IOException, TikaException {
		String realPath = SystemContext.getRealPath();
		String path = realPath+"/upload/"+name;
		return new Tika().parseToString(new File(path));
	}
	*/
	public static IndexField msg2IndexField(Notice notice) {
		IndexField field = new IndexField();
		field.setNoticeId(notice.getNoticeId());
		field.setTitle(notice.getTitle());
		field.setAuthor(notice.getAuthor());
		field.setContent(notice.getContent());
		field.setBrowser(notice.getBrowser());
		field.setCreateDate(notice.getCreateDate());
		return field;
	}

}
