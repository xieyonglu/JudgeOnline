package org.xyl.action;


import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Match;
import org.xyl.bean.Problem;
import org.xyl.bean.User;
import org.xyl.iservice.IMatchService;
import org.xyl.iservice.IProblemService;
import org.xyl.iservice.ISubmitService;
import org.xyl.iservice.IUserService;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("reportAction")
@Scope("prototype")
public class ReportAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private ServletContext application;
	
	@Resource private IProblemService problemService;
	@Resource private ISubmitService submitService;
	@Resource private IMatchService matchService;
	@Resource private IUserService userService;
	
	private Map<String,List<User>> userMap=new LinkedHashMap<String,List<User>>();
	private Match match;
	
	private String type;
	private String form;//报表的类型
	
	
	//和报表相关的两个数据
	private List<String> leftList=new ArrayList<String>();
	private int[] array=null;
	private int totalMoney=0;
	
	
	public String report() throws Exception{
		
		if("table".equals(form)){
			table();
		}else if("pie".equals(form)){
			pie();
		}else if("timeSeries".equals(form)){
			timeSeries();
		}else if("bar".equals(form)){
			bar();
		}else if("line".equals(form)){
			line();
		}
		ActionContext.getContext().put("urlAction", "form/report.jsp");
		return Action.SUCCESS;
		
	}
	
	//折线图
	public void line() throws Exception{
		this.initReport();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.length;i++){
			Map<String,String> formMap=new LinkedHashMap<String,String>();//在这里定义一个有序的Map
			formMap.put("typeName", leftList.get(i));
			formMap.put("totalMoney",array[i]+"");
			System.out.println(leftList.get(i)+"=====time====="+array[i]);
			
			list.add(formMap);
		}
		
		System.out.println("折线图-------------"+list.size());
		
		DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
		//构造数据集合
		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map map=(Map)list.get(i);
				double totalMoney=Double.parseDouble((String)map.get("totalMoney"));
				String typeName=(String)map.get("typeName");
				lineDataset.addValue(totalMoney, "problem-count", typeName);
			}
		}
	
		JFreeChart lineChart = ChartFactory.createLineChart("line", // chart title
			    "problem", // domain axis label
			    "count", // range axis label
			    lineDataset, // data
			    PlotOrientation.VERTICAL, // orientation
			    true, // include legend
			    true, // tooltips
			    false // urls
			    );
		CategoryPlot plot = lineChart.getCategoryPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 2.0); 
			
		String filename=ServletUtilities.saveChartAsPNG(lineChart,550,400,null,session);
		String graphURL=request.getContextPath()+"/DisplayChart?filename="+filename;	
		
		request.setAttribute("filename", filename);
		request.setAttribute("graphURL",graphURL);
	}
	
	//饼图
	public void pie() throws Exception{
		this.initReport();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.length;i++){
			Map<String,String> formMap=new LinkedHashMap<String,String>();//在这里定义一个有序的Map
			formMap.put("typeName", leftList.get(i));
			formMap.put("totalMoney",array[i]+"");
			list.add(formMap);
			
			System.out.println(leftList.get(i)+"---"+array[i]);
		}
		
		System.out.println("饼图-------------"+list.size());
		
		
		DefaultPieDataset dataset=new DefaultPieDataset();
		
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String typeName=(String)map.get("typeName");
			double totalMoney=Double.parseDouble((String)map.get("totalMoney"));	
			dataset.setValue(typeName,totalMoney);
		}
			
		JFreeChart chart=ChartFactory.createPieChart3D(null,dataset,true,true,false);
			
		PiePlot3D pieplot3d=(PiePlot3D)chart.getPlot();
		pieplot3d.setStartAngle(150D);
		pieplot3d.setDirection(Rotation.CLOCKWISE);
		pieplot3d.setForegroundAlpha(0.7F);
		pieplot3d.setNoDataMessage("无数据显示");
			
		pieplot3d.setCircular(true);
		pieplot3d.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={2}({1})",NumberFormat.getNumberInstance(),NumberFormat.getPercentInstance()));
		String filename=ServletUtilities.saveChartAsPNG(chart,550,400,null,session);
		String graphURL=request.getContextPath()+"/DisplayChart?filename="+filename;
		
		request.setAttribute("filename", filename);
		request.setAttribute("graphURL", graphURL);

	}
	
	public void timeSeries() throws Exception{
		this.initReport();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(int i=0;i<array.length;i++){
			Map<String,String> formMap=new LinkedHashMap<String,String>();//在这里定义一个有序的Map
			formMap.put("typeName", leftList.get(i));
			formMap.put("totalMoney",array[i]+"");
			System.out.println(leftList.get(i)+"=====time====="+array[i]);
			
			list.add(formMap);
		}
		
		System.out.println("时序图-------------"+list.size());
		//request.setAttribute("timeSeriesList",list);
		//request.setAttribute("timeUnit",time);
		
		TimeSeries timeSeries=null;
		timeSeries=new TimeSeries("timeSeries");
			
		//时间曲线数据集合
		TimeSeriesCollection lineDataset=new TimeSeriesCollection();
		String title="problem";
		//构造数据集合
		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map map=(Map)list.get(i);
				double totalMoney=Double.parseDouble((String)map.get("totalMoney"));
				String typeName=(String)map.get("typeName");
				timeSeries.add(new Year(0001+i),totalMoney);
			}
		}
			
		lineDataset.addSeries(timeSeries);
		JFreeChart timeSeriesChart=ChartFactory.createTimeSeriesChart("timeSeries",title,"count",lineDataset,true,true,true);
			
		/*XYPlot plot=(XYPlot)timeSeriesChart.getPlot();
	    //纵轴字体
	    plot.getRangeAxis().setLabelFont(new Font("新宋体", Font.BOLD, 15));
	    //横轴框里的标题字体
	    timeSeriesChart.getLegend().setItemFont(new Font("新宋体", Font.ITALIC, 15));
	    //横轴列表字体
	    plot.getDomainAxis().setTickLabelFont(new Font("新宋体", 1, 15));
	    //横轴小标题字体
	   	plot.getDomainAxis().setLabelFont(new Font("新宋体", 1, 12));*/
			
		String filename=ServletUtilities.saveChartAsPNG(timeSeriesChart,550,400,null,session);
		String graphURL=request.getContextPath()+"/DisplayChart?filename="+filename;	
		
		request.setAttribute("filename", filename);
		request.setAttribute("graphURL",graphURL);
			
	}
	
	//表格形式报表
	public void table() throws Exception{
		/*Map<String,Integer> formMap=new LinkedHashMap<String,Integer>();//在这里定义一个有序的Map
		this.initReport();
		for(int i=0;i<array.length;i++){
			formMap.put(leftList.get(i),array[i]);
		}
		formMap.put("合计",totalMoney);
		request.setAttribute("formMap",formMap);*/
		
		
		Map<String,List<User>> formMap=new LinkedHashMap<String,List<User>>();//在这里定义一个有序的Map
		this.initReport();
		
		System.out.println(userMap+"==========");
		
		for(int i=0;i<array.length;i++){
			System.out.println(userMap.get(i+"")+"-----"+userMap.get(i+"").size());
			formMap.put(leftList.get(i),userMap.get(i+""));
		}
		//formMap.put("合计",totalMoney);
		request.setAttribute("formMap",formMap);
	}
	
	//柱形图
	public void bar() throws Exception{
		
		this.initReport();
		
		double [][] data=new double[array.length][1];
		for(int i=0;i<array.length;i++){
			data[i][0]=array[i];
		}
	  	String[] rowKeys=new String[leftList.size()];
	  	String[] columnKeys={"total"};
	  	for(int i=0;i<leftList.size();i++){
	  		rowKeys[i]=leftList.get(i);
		}
	  	
	 	CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data); 
	 	JFreeChart chart = ChartFactory.createBarChart3D("bar","problem","count",dataset,PlotOrientation.VERTICAL,true,true,false);
	 
	 	CategoryPlot plot = chart.getCategoryPlot();
	 	plot.setBackgroundPaint(Color.white);
	 	plot.setDomainGridlinePaint(Color.pink);
	 	plot.setRangeGridlinePaint(Color.pink);
	 
	 	BarRenderer3D renderer = new BarRenderer3D();
	 	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	 	renderer.setBaseItemLabelsVisible(true);
	 	renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
	 	renderer.setItemLabelAnchorOffset(10D);
	 	plot.setRenderer(renderer);
	 	plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
	 	plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
	 
	 	String filename = ServletUtilities.saveChartAsPNG(chart, 700, 400, null, session);
	 	String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
			
		request.setAttribute("filename",filename);
		request.setAttribute("graphURL", graphURL);
	}
	
	
	private void initReport() throws Exception{
		String matchId=request.getParameter("matchId");
		match=matchService.load(Long.parseLong(matchId));
		totalMoney=0;
		leftList=new ArrayList<String>();
		List<Problem> problemList=problemService.listMatchProblem(match.getMatchId());
		for(int i=0;i<match.getProblemCount();i++){
			leftList.add((char)('A'+i)+"");
		}
		
		
		array=new int[leftList.size()];
		Problem pro=null;
		if("success".equals(type)){
			
			for(int i=0;i<problemList.size();i++){
				pro=problemList.get(i);
				array[i]=Integer.parseInt(submitService.submitCount(pro.getProblemId(), 1)+"");
				userMap.put(i+"",userService.listSubmitUser(pro.getProblemId(), 1));
				totalMoney=totalMoney+array[i];
			}
				
		}else{
			for(int i=0;i<problemList.size();i++){
				pro=problemList.get(i);
				array[i]=Integer.parseInt(submitService.submitCount(pro.getProblemId(), 0)+"");
				userMap.put(i+"",userService.listSubmitUser(pro.getProblemId(), 0));
				totalMoney=totalMoney+array[i];
			}
		}
		System.out.println("userlist=========="+userMap);
		System.out.println(problemList.size()+"--"+array.length+"--"+leftList.size());
	}
	
	
	//@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		System.out.println("--执行response方法---");
		response=arg0;
		
	}

	//@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		System.out.println("--执行request方法---");
		request=arg0;
		session=request.getSession();
		application=session.getServletContext();
	}

	
	
	public Map<String, List<User>> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, List<User>> userMap) {
		this.userMap = userMap;
	}

	public Match getMatch() {
		return match;
	}
	
	

	public void setMatch(Match match) {
		this.match = match;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
	

}

