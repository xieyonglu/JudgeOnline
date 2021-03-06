package org.xyl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticValue {

	public Map<String,List<String>> getBirthdayInstance(){
		//初始化出生年月
		//String[] year={"1980","1999","2000"};
		String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
		String[][] day={
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"},
				{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"}
		};
		
		Map<String,List<String>> birthdayMap=new HashMap<String,List<String>>();
		List<String> birthdayList;
		for(int i=0;i<month.length;i++){
			birthdayList=new ArrayList<String>();
			for(int j=0;j<day[i].length;j++){
				birthdayList.add(day[i][j]);
			}
			birthdayMap.put(month[i],birthdayList);
		}
		return birthdayMap;
}

	public Map<String,List<String>> getAreaInstance(){
		//初始化所在地
		String[] privinceArray={"天津","山西","辽宁","黑龙江","江苏","安徽","江西","河南","湖南","广西壮族自治区","重庆","西藏自治区","甘肃","宁夏回族自治区","台湾","澳门特别行政区"};
		String[][] cityArray={
				{"天津"},
				{"太原","大同","阳泉","长治","晋城","朔州","晋中","运城","忻州","临汾","吕梁"},
				{"沈阳","大连","鞍山","抚顺","本溪","丹东","锦州","营口","阜新","辽阳","盘锦","铁岭","朝阳","葫芦岛"},
				{"哈尔滨","齐齐哈尔","鸡西","鹤岗","双鸭山","大庆","伊春","佳木斯","七台河","牡丹江","黑河","绥化","大兴安岭地区"},
				{"南京","无锡","徐州","常州","苏州","南通","连云港","淮安","盐城","扬州","镇江","泰州","宿迁"},
				{"合肥","芜湖","蚌埠","淮南","马鞍山","淮北","铜陵","安庆","黄山","滁州","阜阳","宿州","巢湖","六安","亳州","池州","宣城"},
				{"南昌","景德镇","萍乡","九江","新余","鹰潭","赣州","吉安","宜春","抚州","上饶"},
				{"郑州","开封","洛阳","平顶山","安阳","鹤壁","新乡","焦作","濮阳","许昌","漯河","三门峡","南阳","商丘","信阳","周口","驻马店","济源"},
				{"长沙","株洲","湘潭","衡阳","邵阳","岳阳","常德","张家界","益阳","郴州","永州","怀化","娄底","湘西土家族苗族自治州"},
				{"南宁","柳州","桂林","梧州","北海","防城港","钦州","贵港","玉林","百色","贺州","河池","来宾","崇左"},
				{"重庆"},
				{"贵阳","六盘水","遵义","安顺","铜仁地区","黔西南布依族苗族自治州","毕节地区","黔东南苗族侗族自治州","黔南布依族苗族自治州","辽阳","盘锦","铁岭","朝阳","葫芦岛"},
				{"拉萨市","昌都地区","山南地区","日喀则地区","那曲地区","阿里地区","林芝地区"},
				{"兰州","嘉峪关","金昌","白银","天水","武威","张掖","平凉","酒泉","庆阳","定西","陇南","临夏回族自治州","甘南藏族自治州"},
				{"银川","石嘴山","吴忠","固原","中卫"},
				{"台北","高雄","基隆","台中","台南","新竹","嘉义"},
				{"澳门"}
		};
		Map<String,List<String>> areaMap=new HashMap<String,List<String>>();
		List<String> areaList;
		for(int i=0;i<privinceArray.length;i++){
			areaList=new ArrayList<String>();
			for(int j=0;j<cityArray[i].length;j++){
				areaList.add(cityArray[i][j]);
			}
			areaMap.put(privinceArray[i],areaList);
		}
		
		return areaMap;
	}

}
