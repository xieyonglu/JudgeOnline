package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Comment;
import org.xyl.idao.ICommentDao;

@Repository("commentDao")
public class CommentDao extends BaseDao<Comment> implements ICommentDao{

}
