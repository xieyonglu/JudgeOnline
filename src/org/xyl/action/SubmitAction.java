package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Code;
import org.xyl.bean.Form;
import org.xyl.bean.IpAddress;
import org.xyl.bean.Match;
import org.xyl.bean.Problem;
import org.xyl.bean.Submit;
import org.xyl.bean.TestData;
import org.xyl.bean.User;
import org.xyl.iservice.ICodeService;
import org.xyl.iservice.IFormService;
import org.xyl.iservice.IIpAddressService;
import org.xyl.iservice.IMatchService;
import org.xyl.iservice.IProblemService;
import org.xyl.iservice.ISubmitService;
import org.xyl.iservice.ITestDataService;
import org.xyl.iservice.IUserService;
import org.xyl.judge.JugeManager;
import org.xyl.judge.JugeSystem;
import org.xyl.judge.OutResult;
import org.xyl.util.DateUtil;
import org.xyl.util.IPAddressUtil;
import org.xyl.util.IPTimeStamp;
import org.xyl.util.PageBean;
import org.xyl.util.PropertiesUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("submitAction")
@Scope("prototype")
public class SubmitAction extends ActionSupport implements ServletRequestAware{

	private static ISubmitService submitService;
	private static IUserService userService;
	private static IProblemService problemService;
	private static IMatchService matchService;
	private static ICodeService codeService;
	private static IFormService formService;
	
	@Resource private ITestDataService testDataService;
	@Resource private IIpAddressService ipAddressService;
	//@Resource private IMatchService matchService;
	@Resource private JugeManager jugeManager;
	
	private static HttpServletRequest request;

	private Submit submit;
	private static Code code;
	private PageBean<Submit> pageBean;
	private String condition1;
	private String condition2;
	
	private Problem problem;
	private Match match;
	private User user;
	
	@Resource
	public void setSubmitService(ISubmitService submitService) {
		SubmitAction.submitService = submitService;
	}
	
	@Resource 
	public void setUserService(IUserService userService) {
		SubmitAction.userService = userService;
	}
	@Resource 
	public void setProblemService(IProblemService problemService) {
		SubmitAction.problemService = problemService;
	}
	@Resource 
	public void setMatchService(IMatchService matchService) {
		SubmitAction.matchService = matchService;
	}
	@Resource 
	public void setCodeService(ICodeService codeService) {
		SubmitAction.codeService = codeService;
	}
	
	@Resource
	public void setFormService(IFormService formService) {
		SubmitAction.formService = formService;
	}
	
	//�ύ������֤
	public void validateSubmitCode(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String problemId=request.getParameter("problem.problemId");
		String matchId=request.getParameter("match.matchId");
		String userId=request.getParameter("userId");
		String flag=request.getParameter("flag");//�鿴�Ǳ���������ϰ
		
		if(!(userId!=null&&userId.trim().length()>0)){
			this.addFieldError("submitError", "���ȵ�¼�����ύ��");
		}else{
			List<TestData> ts=testDataService.findByProblemId(Long.parseLong(problemId));
			if(ts==null||ts.size()==0){
				this.addFieldError("submitError", "��������޲��������������������⣡");
			}
		}
		
		problem=problemService.load(Long.parseLong(problemId));
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
			System.out.println("ʱ���==="+(match.getEndDate().getTime()-(new Date()).getTime()));
			if(match!=null&&(match.getState()==2||
					((match.getEndDate().getTime()-(new Date()).getTime())<0))){
				this.addFieldError("submitError", "���������Ѿ�������");
			}else{
				//���IP��ַ�Ƿ�Ϸ�
				List<IpAddress> ia=ipAddressService.listAllIpAddressByMatchId(Long.parseLong(matchId));
				IpAddress ipAddress=null;
				int count=0;
				for(int i=0;i<ia.size();i++){
					ipAddress=ia.get(i);
					if(!IPAddressUtil.checkIPScole(ipAddress.getFrom(),ipAddress.getTo(),request.getRemoteAddr())){
						count++;
					}
				}
				if(count==ia.size()&&count!=0){
					this.addFieldError("submitError", "�Բ������IP��ַ�����趨�����ڣ�����ϵ����Ա��");
				}
			}
		}
		
		
		if("match".equals(flag)){
			ActionContext.getContext().put("urlAction", "problem/show_problem_match_all.jsp");
		}else{
			ActionContext.getContext().put("urlAction", "problem/show_problem_all.jsp");
		}
		
	}
	
	//�û��ύ����
	public synchronized String submitCode(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String flag="";
		try {
			
			Thread jugeSystem=null;
			String language=request.getParameter("language");//�õ�������ʹ�õ�����
						
			String codeText=request.getParameter("codetxt").trim();//�õ������ı�
			String problemId=request.getParameter("problem.problemId");
			String userId=request.getParameter("userId");
			flag=request.getParameter("flag");//�鿴�Ǳ���������ϰ
			
			problem=problemService.load(Long.parseLong(problemId));
			user=userService.load(Long.parseLong(userId));
			
			
			if(jugeSystem==null){
				//����ѡ�����ԵĲ�ͬ��������ͬ��jugeManage
				if(language.equals("java")){
					jugeSystem=new JugeSystem("Java");
				}else if(language.equals("c")){
					jugeSystem=new JugeSystem("C");
				}else if(language.equals("cpp")){
					jugeSystem=new JugeSystem("C++");
				}else{
					jugeSystem=new JugeSystem("Java");
				}
				
				jugeSystem.start();
				System.out.println("run juge targetNum="+((JugeSystem)jugeSystem).getTargetNumber());
			}
			
			IPTimeStamp its=new IPTimeStamp();
			String fileName=its.getIPTimeStamp(request.getRemoteAddr());
			
			
			System.out.println("---666666----"+fileName+"-----"+request.getRemoteAddr());
			
			if (!"".equals(codeText.trim())){
					
				jugeManager.setJugeSystem((JugeSystem) jugeSystem);
				System.out.println("begin add juge");
						
				//����ѡ�����ԵĲ�ͬ��ִ�в�ͬ���жϲ���
				if(language.equals("java")){
					jugeManager.addJuge("java",problem,user,codeText,flag,fileName);
				}else if(language.equals("c")){
					jugeManager.addJuge("c",problem,user,codeText,flag,fileName);
				}else if(language.equals("cpp")){
					jugeManager.addJuge("cpp",problem,user,codeText,flag,fileName);
				}else{
					//jugeManager.addJuge("java","444","ddd", codeText);
				}
			}else{
				//�����ύ�Ĵ���Ϊ�ա�����
				System.out.println("���ύ�Ĵ���Ϊ��......");
				flag="nullString";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		if("match".equals(flag)){
			match=problem.getMatch();
			ActionContext.getContext().put("urlAction", "code/submit_match_success.jsp");
		}else if("nullString".equals(flag)){
			match=problem.getMatch();
			ActionContext.getContext().put("urlAction", "code/submit_nullString.jsp");
		}else{
			ActionContext.getContext().put("urlAction", "code/submit_success.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	//����ƽ����ϰ
	public static void savePracticeCode(OutResult result,String submitCode){
		
		//���к�Ľ����ʾ
		System.out.println(result);
		System.out.println(result.targetId);
		String s[]=result.targetId.split("_");
		
		System.out.println("���к�:  ��ʱ����");
		System.out.println("��Ŀ:   probledId="+s[0]);
		System.out.println("�û�:    userId="+s[1]);
		System.out.println("���:    result.state="+result.getState());
		System.out.println("ʱ��:"+result.getTime());
		System.out.println("�ڴ�:"+result.getMemory());
		System.out.println("����:"+result.getLanguage());
		System.out.println("�ύʱ��:"+new Date());
		System.out.println("������Ϣ:"+submitCode+"--"+submitCode.length());
		
		String memory=result.getMemory()+"";
		String runtime=result.getTime()+"";
		if(!"rightAnswer".equals(result.getState())){
			memory="--";
			runtime="--";
		}
		
		code=new Code(submitCode,result.getState(),result.getLanguage(),memory,runtime,submitCode.length(),new Date(),request.getRemoteAddr());
		System.out.println(code.getCodeId()+"====codeService---"+codeService+"-"+userService+"-"+problemService);
		
		
		try{
			Problem problem=problemService.load(Long.parseLong(s[0]));
			User user=userService.load(Long.parseLong(s[1]));
			List<Code> listCode=codeService.listAllCodeByMatch(null);
			
			code.setRunNumber((listCode.size()+1)+"");
			code.setUser(user);
			code.setProblem(problem);
			
			if("rightAnswer".equals(code.getState())){
				
				user.setPassCount(user.getPassCount()+1);	
				problem.setPassCount(problem.getPassCount()+1);
				
				Submit submit=submitService.findByUserProblem(user.getUserId(),problem.getProblemId());
				if(submit!=null){
					
					if(submit.getState()==0){
						user.setTrueProblemCount(user.getTrueProblemCount()+1);
						user.setFalseProblemCount(user.getFalseProblemCount()-1);
						submit.setFirstTrueDate(new Date());
					}
					
					submit.setUser(user);
					submit.setProblem(problem);
					submit.setLastSubmitDate(new Date());
					submit.setPassCount(submit.getPassCount()+1);
					submit.setSubmitCount(submit.getSubmitCount()+1);
					submit.setState(1);
					submitService.updateSubmit(submit);
					
				}else{//��һ���ύ����
					user.setTrueProblemCount(user.getTrueProblemCount()+1);
					
					Submit sub=new Submit();
					sub.setUser(user);
					sub.setProblem(problem);
					
					sub.setFirstTrueDate(new Date());
					sub.setLastSubmitDate(new Date());
					sub.setPassCount(1L);
					sub.setSubmitCount(1L);
					sub.setState(1);
					submitService.addSubmit(sub);
				}
				
				
			}else{
				//�ж�user������ȷ�������Ƿ�������⣬����У������κδ������û�У����ô������û�֮��Ĺ�ϵ
				Submit submit=submitService.findByUserProblem(user.getUserId(),problem.getProblemId());
				
				if(submit!=null){
					
					submit.setUser(user);
					submit.setProblem(problem);
					submit.setLastSubmitDate(new Date());
					//submit.setPassCount(submit.getPassCount()+1);
					submit.setSubmitCount(submit.getSubmitCount()+1);
					//submit.setState(0);
					submitService.updateSubmit(submit);
				}else{//��һ���ύ����
					user.setFalseProblemCount(user.getFalseProblemCount()+1);
					
					Submit sub=new Submit();
					sub.setUser(user);
					sub.setProblem(problem);
					
					//sub.setFirstTrueDate(new Date());
					sub.setLastSubmitDate(new Date());
					sub.setPassCount(0L);
					sub.setSubmitCount(1L);
					sub.setState(0);
					submitService.addSubmit(sub);
				}
			}
			
			user.setSubmitCount(user.getSubmitCount()+1);	
			problem.setSubmitCount(problem.getSubmitCount()+1);
			
			problemService.updateProblem(problem);
			userService.updateUser(user);
			
			codeService.addCode(code);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public static void saveMatchCode(OutResult result,String submitCode){
		System.out.println("���Ǳ���============");
		//���к�Ľ����ʾ
		System.out.println(result);
		System.out.println(result.targetId);
		String s[]=result.targetId.split("_");
		
		System.out.println("���к�:  ��ʱ����");
		System.out.println("��Ŀ:   probledId="+s[0]);
		System.out.println("�û�:    userId="+s[1]);
		System.out.println("���:    result.state="+result.getState());
		System.out.println("ʱ��:"+result.getTime());
		System.out.println("�ڴ�:"+result.getMemory());
		System.out.println("����:"+result.getLanguage());
		System.out.println("�ύʱ��:"+new Date());
		System.out.println("������Ϣ:"+submitCode+"--"+submitCode.length());
		int minuteNum=Integer.parseInt(PropertiesUtil.getPropertiesValue("minuteNum"));
		int timeRate=60000;//ʱ�任��
		
		String memory=result.getMemory()+"";
		String runtime=result.getTime()+"";
		if(!"rightAnswer".equals(result.getState())){
			memory="--";
			runtime="--";
		}
		//HttpServletRequest request=ServletActionContext.getRequest();
		//System.out.println(request+"---------request-----");
		//String ipaddress=request.getRemoteAddr();
		//System.out.println(ipaddress+"-------ipaddress-------");
		
		code=new Code(submitCode,result.getState(),result.getLanguage(),memory,runtime,submitCode.length(),new Date(),request.getRemoteAddr());
		System.out.println(code.getCodeId()+"====codeService---"+codeService+"-"+userService+"-"+problemService);
		
		
		try{
			Problem problem=problemService.load(Long.parseLong(s[0]));
			User user=userService.load(Long.parseLong(s[1]));
			Match match=problem.getMatch();
			List<Code> listCode=codeService.listAllCodeByMatch(match.getMatchId());
			
			code.setRunNumber((listCode.size()+1)+"");
			code.setUser(user);
			code.setProblem(problem);
			
			if("rightAnswer".equals(code.getState())){
				
				//user.setPassCount(user.getPassCount()+1);	
				problem.setPassCount(problem.getPassCount()+1);
				
				Submit submit=submitService.findByUserProblem(user.getUserId(),problem.getProblemId());
				
				if(submit!=null){
					if(submit.getState()==0){
						submit.setFirstTrueDate(new Date());
						
						Long temp1=(new Date()).getTime()-match.getStartDate().getTime();
						Long temp2=submit.getSubmitCount();
						//Long totalTime=temp1;//+temp2*20;---------
						Long totalTime=temp1/timeRate+temp2*minuteNum;//------------
						
						Form form=formService.loadByUserAndMatch(user.getUserId(),match.getMatchId());
						if(form!=null){//һ����˵�϶�����Ϊ��
							//form.setTotalTime(form.getTotalTime()+temp1);
							form.setTotalTime(form.getTotalTime()+totalTime);
							
							form.setProblemCount(form.getProblemCount()+1);
							
							String str=temp1/timeRate+"|"+temp2;
							setFormProblem(problem.getSequence(),form,str);//�޸������¼
							form.setMatch(match);
							formService.updateForm(form);
						}
						
					}else{//��һ����ȷ���ڶ����ύҲ����ȷ��
						//�����κδ���
					}
					
					submit.setUser(user);
					submit.setProblem(problem);				
					submit.setLastSubmitDate(new Date());
					submit.setPassCount(submit.getPassCount()+1);
					submit.setSubmitCount(submit.getSubmitCount()+1);
					submit.setState(1);
					submitService.updateSubmit(submit);
					
				}else{//��һ���ύ����,һ��������
					
					Form form=formService.loadByUserAndMatch(user.getUserId(),match.getMatchId());
					if(form!=null){
						Long temp1=(new Date()).getTime()-match.getStartDate().getTime();
						form.setTotalTime(form.getTotalTime()+temp1/timeRate);
						form.setProblemCount(form.getProblemCount()+1);
						form.setUserId(user.getUserId());
						form.setUserName(user.getUserName());
						
						String str=temp1/timeRate+"|"+0;
						
						setFormProblem(problem.getSequence(),form,str);//�޸������¼
						form.setMatch(match);
						formService.updateForm(form);
					}else{
						form=new Form();
						Long temp1=(new Date()).getTime()-match.getStartDate().getTime();
						form.setTotalTime(temp1/timeRate);
						form.setProblemCount(1);
						form.setUserId(user.getUserId());
						form.setUserName(user.getUserName());
						
						String str=temp1/timeRate+"|"+0;
						
						setFormProblem(problem.getSequence(),form,str);//�޸������¼
						form.setMatch(match);
						formService.addForm(form);
					}
					
					
					
					
					
					Submit sub=new Submit();
					sub.setUser(user);
					sub.setProblem(problem);
					
					sub.setFirstTrueDate(new Date());
					sub.setLastSubmitDate(new Date());
					sub.setPassCount(1L);
					sub.setSubmitCount(1L);
					sub.setState(1);
					sub.setMatch(match);
					submitService.addSubmit(sub);
				}
				
				
			}else{
				//�ж�user������ȷ�������Ƿ�������⣬����У������κδ������û�У����ô������û�֮��Ĺ�ϵ
				Submit submit=submitService.findByUserProblem(user.getUserId(),problem.getProblemId());
				
				if(submit!=null){
					
					submit.setUser(user);
					submit.setProblem(problem);
					submit.setLastSubmitDate(new Date());
					//submit.setPassCount(submit.getPassCount()+1);
					submit.setSubmitCount(submit.getSubmitCount()+1);
					//submit.setState(0);
					submitService.updateSubmit(submit);
					
					
					//�޸ı�����Ϣ
					Long temp2=submit.getSubmitCount();
					Long totalTime=temp2*minuteNum;
					
					if(submit.getState()!=1){
						Form form=formService.loadByUserAndMatch(user.getUserId(),match.getMatchId());
						if(form!=null){//һ����˵�϶�����Ϊ��
							//form.setTotalTime(form.getTotalTime()+20L);
							//form.setProblemCount(form.getProblemCount()+1);
							
							String str=0+"|"+temp2;
							setFormProblem(problem.getSequence(),form,str);//�޸������¼
							form.setMatch(match);
							formService.updateForm(form);
						}
					}
					
				}else{//��һ���ύ����
					
					submit=new Submit();
					submit.setUser(user);
					submit.setProblem(problem);
					
					//sub.setFirstTrueDate(new Date());
					submit.setLastSubmitDate(new Date());
					submit.setPassCount(0L);
					submit.setSubmitCount(1L);
					submit.setState(0);
					submit.setMatch(match);
					submitService.addSubmit(submit);
					
					Form form=formService.loadByUserAndMatch(user.getUserId(),match.getMatchId());
					if(form!=null){
						//���汨����Ϣ
						Long temp2=submit.getSubmitCount();
						Long totalTime=temp2*minuteNum;
						
						//form.setTotalTime(form.getTotalTime()+20L);
						//form.setProblemCount(form.getProblemCount()+1);
							
						String str=0+"|"+temp2;
						setFormProblem(problem.getSequence(),form,str);//�޸������¼
						form.setMatch(match);
						form.setUserId(user.getUserId());
						form.setUserName(user.getUserName());
						formService.updateForm(form);
					}else{
						//���汨����Ϣ
						Long temp2=submit.getSubmitCount();
						Long totalTime=temp2*minuteNum;
						
						form=new Form();
						form.setTotalTime(0L);
						//form.setProblemCount(form.getProblemCount()+1);
							
						String str=0+"|"+temp2;
						setFormProblem(problem.getSequence(),form,str);//�޸������¼
						form.setMatch(match);
						form.setUserId(user.getUserId());
						form.setUserName(user.getUserName());
						form.setProblemCount(0);
						formService.addForm(form);
					}
					
				}
				
			}
			
			//user.getMatchs().add(match);//�ѱ������뵽�û���¼��
			//user.setSubmitCount(user.getSubmitCount()+1);	
			List<Form> list=formService.loadByMatchId(match.getMatchId());
			match.setPersonCount(list.size());
			problem.setSubmitCount(problem.getSubmitCount()+1);
			
			problemService.updateProblem(problem);
			userService.updateUser(user);
			matchService.updateMatch(match);
			code.setMatch(match);
			
			codeService.addCode(code);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static void setFormProblem(Integer sequence,Form form,String str){
		
		switch(sequence){//����������������
		
		case 1:
			form.setProblemA(str);
			break;
		case 2:
			form.setProblemB(str);
			break;
		case 3:
			form.setProblemC(str);
			break;
		case 4:
			form.setProblemD(str);
			break;
		case 5:
			form.setProblemE(str);
			break;
		case 6:
			form.setProblemF(str);
			break;
		case 7:
			form.setProblemG(str);
			break;
		case 8:
			form.setProblemH(str);
			break;
		case 9:
			form.setProblemI(str);
			break;
		case 10:
			form.setProblemJ(str);
			break;
		case 11:
			form.setProblemK(str);
			break;
		case 12:
			form.setProblemL(str);
			break;
		case 13:
			form.setProblemM(str);
			break;
		case 14:
			form.setProblemN(str);
			break;
		case 15:
			form.setProblemO(str);
			break;
		}
	}

	
	//���ύ������浽���ݿ���
	public static void saveOutResult(OutResult result,String submitCode,String flag){
		System.out.println(result+"=================");
		
		if("match".equals(flag)){
			System.out.println("���Ǳ���---------");
			saveMatchCode(result,submitCode);
		}else{
			System.out.println("����ƽ����ϵ��");
			savePracticeCode(result,submitCode);
		}
		
	}
	
	
	public void validateInsertSubmit(){
		ActionContext.getContext().put("urlAction", "admin/insertAdmin.jsp");
	}
	
	//����һ������Ա��Ϣ
	public String insertSubmit(){
		submitService.addSubmit(submit);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//ɾ��ָ��Id�Ĺ���Ա
	public String deleteSubmit(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long submitId=Long.parseLong(request.getParameter("submitId"));
		submitService.deleteSubmitById(submitId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateSubmit(){
		ActionContext.getContext().put("urlAction", "admin/updateAdmin.jsp");
	}
	
	//�޸Ĺ���Ա��Ϣ
	public String updateSubmit(){
		submitService.updateSubmit(submit);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//��ʾĳ���û�
	public String showSubmit(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		Long submitId=Long.parseLong(request.getParameter("submitId"));
		String action=request.getParameter("action");
		submit=submitService.load(submitId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","admin/updateAdmin.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction","admin/showAdmin.jsp");
		}
		
		System.out.println(action+"----"+submit);
		
		return Action.SUCCESS;
	}
	
	//��ʾ�û�Ϊ���˺��Ѿ����˵����⣨����ʽ��
	public String findAllSubmit(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String userId=request.getParameter("userId");
		user=userService.load(Long.parseLong(userId));
		
		String hql1="from Submit as obj where obj.user.userId='"+userId+"' and obj.state=0 and obj.problem.match.matchId is null";
		String hql2="from Submit as obj where obj.user.userId='"+userId+"' and obj.state=1 and obj.problem.match.matchId is null";
		
		List<Submit> falseList=submitService.listAllSubmitByHql(hql1);
		List<Submit> trueList=submitService.listAllSubmitByHql(hql2);
		
		
		ActionContext.getContext().put("falseList", falseList);
		ActionContext.getContext().put("trueList", trueList);
		
		System.out.println("�ܹ��ļ�¼��:"+falseList.size()+"--=========--"+trueList.size());
		
		ActionContext.getContext().put("urlAction", "submit/list_submit_simple.jsp");
		return Action.SUCCESS;
	}

	//��ҳ��ʾ�û���Ϣ
	public String pageSubmit(){
				
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String userId=request.getParameter("userId");
		String problemId=request.getParameter("problemId");
		String state=request.getParameter("state");
		
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		try{
			if(condition1!=null)condition1=new String(condition1.getBytes("ISO-8859-1"));
			if(condition2!=null)condition2=new String(condition2.getBytes("ISO-8859-1"));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		
		System.out.println(condition1+"----"+condition2+"---"+pageIndex+"---"+pageSize);
		String hql="from Submit as obj where 1=1 ";
		if(condition1!=null&&condition1.trim().length()>0&&!"--��ѡ��--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		
		//����problemId������ĳһ��������������н��
		if(problemId!=null&&problemId.trim().length()>0){
			hql=hql+" and obj.problem.problemId='"+Long.parseLong(problemId)+"'";
			problem=problemService.load(Long.parseLong(problemId));
		}
		
		//����userId������ĳһ��������������н��
		if(userId!=null&&userId.trim().length()>0){
			hql=hql+" and obj.user.userId='"+Long.parseLong(userId)+"'";
			user=userService.load(Long.parseLong(userId));
		}
		
		//����state������ĳһ��������������н��
		if(state!=null&&state.trim().length()>0){
			hql=hql+" and obj.state='"+state+"'";
		}
		hql=hql+" and obj.match.matchId is null";
		hql=hql+" order by lastSubmitDate";
		System.out.println("hql===="+hql);
		
		pageBean=submitService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Submit> list=pageBean.getList();
		
		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		
		if("all".equals(action)){
			
			
		}else if("user".equals(action)){
			if(state.equals("1")){
				ActionContext.getContext().put("urlAction","submit/list_submit_user_true.jsp");
			}else{
				ActionContext.getContext().put("urlAction","submit/list_submit_user_false.jsp");
			}
		}else if("admin".equals(action)){
			
		}
		
		return Action.SUCCESS;
	}

	public Submit getSubmit() {
		return submit;
	}

	public void setSubmit(Submit submit) {
		this.submit = submit;
	}

	public PageBean<Submit> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Submit> pageBean) {
		this.pageBean = pageBean;
	}

	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}


	public Problem getProblem() {
		return problem;
	}


	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		SubmitAction.request=arg0;
	}
	
}
