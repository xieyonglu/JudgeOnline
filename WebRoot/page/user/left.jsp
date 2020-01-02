<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title></title>
<style type="text/css">
body{
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1{
	font-size: 12px;
	color: #FFFFFF;
}
.STYLE3{
	font-size: 12px;
	color: #033d61;
}
</style>
<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #ffffff; POSITION: relative; TOP: 2px 
}
.menu_title2 SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #FFCC00; POSITION: relative; TOP: 2px
}

</style>

<script type="text/javascript">
	document.ondragstart=function(){return false;}
</script>
</head>
<body>
<table width="165" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="28" background="../images/main_40.gif">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="19%"><br></td>
			        <td width="81%" height="20"><span class="STYLE1">管理菜单</span></td>
			    </tr>
			</table>
		</td>
  	</tr>
  	
  	<tr>
		<td valign="top">
		
		<table width="151" border="0" align="center" cellpadding="0" cellspacing="0">
      		<tr>
        		<td>
        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
        			<tr>
            			<td height="23" background="../images/main_47.gif" id="imgmenu1" class="menu_title" onMouseOver="this.className='menu_title2';" onClick="showsubmenu(1)" onMouseOut="this.className='menu_title';" style="cursor:hand">
            				<table width="100%" border="0" cellspacing="0" cellpadding="0">
            					<tr>
            						<td width="18%">&nbsp;</td>
            						<td width="82%" class="STYLE1">个人信息</td>
            					</tr>
            				</table>
            			</td>
            		</tr>
            		<tr>
            			<td background="../images/main_51.gif" id="submenu1">
            			<div class="sec_menu" >
            			<table width="100%" border="0" cellspacing="0" cellpadding="0">
            			<tr>
            				<td>
            					<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                  					<tr>
                    					<td width="16%" height="25"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
                    					<td width="84%" height="23">
                    					
                    						<table width="95%" border="0" cellspacing="0" cellpadding="0">
                        						<tr>
						                          <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
						                          	<span class="STYLE3"><a href="user_showUser.action?action=show&userId=${sessionScope.loginUser.userId}" target="I2">查看个人信息</a></span>
						                          </td>
                        						</tr>
                    						</table>
                    					</td>
                  					</tr>
                  					<tr>
					                    <td height="23"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
					                    <td height="23">
					                    	<table width="95%" border="0" cellspacing="0" cellpadding="0">
						                        <tr>
						                          <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
													<span class="STYLE3"><a href="user_showUser.action?action=basic&userId=${sessionScope.loginUser.userId}" target="I2">修改基本信息</a></span>
												  </td>
						                        </tr>
                    						</table>
                    					</td>
                    				</tr>
                  					<tr>
					                    <td height="23"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
					                    <td height="23">
					                    	<table width="95%" border="0" cellspacing="0" cellpadding="0">
						                        <tr>
						                          <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
													<span class="STYLE3"><a href="user_showUser.action?action=update&userId=${sessionScope.loginUser.userId}" target="I2">修改个人信息</a></span>
												  </td>
						                        </tr>
                    						</table>
                    					</td>
                    				</tr>
                    				<tr>
					                    <td height="23"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
					                    <td height="23">
					                    	<table width="95%" border="0" cellspacing="0" cellpadding="0">
						                        <tr>
						                          <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
													<span class="STYLE3"><a href="user_showUser.action?action=picture&userId=${sessionScope.loginUser.userId}" target="I2">修改图片信息</a></span>
												  </td>
						                        </tr>
                    						</table>
                    					</td>
                    				</tr>
                  					
                  
                </table></td>
              </tr>
              <tr>
                <td height="5"><img src="../images/main_52.gif" width="151" height="5" /></td>
              </tr>
            </table></div></td>
          </tr>
          
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23" background="../images/main_47.gif" id="imgmenu2" class="menu_title" onmouseover="this.className='menu_title2';" onclick="showsubmenu(2)" onmouseout="this.className='menu_title';" style="cursor:hand"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="18%">&nbsp;</td>
                  <td width="82%" class="STYLE1">试题管理</td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td background="../images/main_51.gif" id="submenu2"><div class="sec_menu" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="16%" height="25"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
                          <td width="84%" height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" 
												onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; 
												"onmouseout="this.style.borderStyle='none'">
								<span class="STYLE3"><a href="submit_pageSubmit.action?userId=${sessionScope.loginUser.userId}&state=1&action=user" target="I2">已经攻克</a></span>
							   </td>
                              </tr>
                          </table></td>
                        </tr>
                        
                        <tr>
                          <td height="23"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
                                <span class="STYLE3"><a href="submit_pageSubmit.action?userId=${sessionScope.loginUser.userId}&state=0&action=user" target="I2">正在攻克</a></span></td>
                              </tr>
                          </table></td>
                        </tr>
                      
                    </table></td>
                  </tr>
                  <tr>
                    <td height="5"><img src="../images/main_52.gif" width="151" height="5" /></td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23" background="../images/main_47.gif" id="imgmenu3" class="menu_title" onmouseover="this.className='menu_title2';" onclick="showsubmenu(3)" onmouseout="this.className='menu_title';" style="cursor:hand"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="18%">&nbsp;</td>
                  <td width="82%" class="STYLE1">运行管理</td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td background="../images/main_51.gif" id="submenu3" style="DISPLAY: none"><div class="sec_menu" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="16%" height="25"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
                          <td width="84%" height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
                                <span class="STYLE3">
								<a href="code_pageFindCode.action?action=user&userId=${sessionScope.loginUser.userId}" target="I2">我的运行</a></span></td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
                                <span class="STYLE3"><a href="match_pageMatch.action?action=user&userId=${sessionScope.loginUser.userId}" target="I2">我的比赛</a></span></td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
                                <span class="STYLE3"><a href="${pageContext.request.contextPath}/sort/sort_admin_time.jsp" target="I2">排名管理</a></span></td>
                              </tr>
                          </table></td>
                        </tr>
                        
                    </table></td>
                  </tr>
                  <tr>
                    <td height="5"><img src="../images/main_52.gif" width="151" height="5" /></td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table></td>
      </tr>
      
      
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23" background="../images/main_47.gif" id="imgmenu4" class="menu_title" onmouseover="this.className='menu_title2';" onclick="showsubmenu(4)" onmouseout="this.className='menu_title';" style="cursor:hand"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="18%">&nbsp;</td>
                  <td width="82%" class="STYLE1">评论管理</td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td background="../images/main_51.gif" id="submenu4" style="DISPLAY: none"><div class="sec_menu" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="16%" height="25"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
                          <td width="84%" height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
                                <span class="STYLE3">
								<a href="comment_pageComment.action?type=problem&userId=${sessionScope.loginUser.userId}" target="I2">试题评论</a></span></td>
                              </tr>
                          </table></td>
                        </tr>
                        <tr>
                          <td height="23"><div align="center"><img src="../images/left.gif" width="10" height="10" /></div></td>
                          <td height="23"><table width="95%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
                                <span class="STYLE3"><a href="comment_pageComment.action?type=match&userId=${sessionScope.loginUser.userId}" target="I2">比赛评论</a></span></td>
                              </tr>
                          </table></td>
                        </tr>
                        
                    </table></td>
                  </tr>
                  <tr>
                    <td height="5"><img src="../images/main_52.gif" width="151" height="5" /></td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table></td>
      </tr>
      
    </table></td>
  </tr>
  <tr>
    <td height="18" background="../images/main_58.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="18" valign="bottom"><div align="center" class="STYLE3">版本：2012V1.0</div></td>
      </tr>
    </table></td>
  </tr>
  
</table>


<script>
function showsubmenu(sid){
	whichEl = eval("submenu" + sid);
	imgmenu = eval("imgmenu" + sid);
	if(whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
		imgmenu.background="../images/main_47.gif";
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
		imgmenu.background="../images/main_48.gif";
	}
}
</script>

</body>
</html>

