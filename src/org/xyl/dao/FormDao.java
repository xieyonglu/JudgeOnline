package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Form;
import org.xyl.idao.IFormDao;

@Repository("formDao")
public class FormDao extends BaseDao<Form> implements IFormDao{

}
