// JavaScript Document
$(function(){
	//������һ��Ԫ�صı߾�
	var $indexfang=$('.index-fang-list');
	 $indexfang.each(function(){
	     $(this).find("dl:last").css("margin-right","0");
	   })
	//ָ������
	$(".zhiding").click(function(){
		$(".bg100").show();
		$(".zhidinggoufang").fadeIn();
		})	
	$(".close").click(function(){
		$(".bg100").hide();
		$(".zhidinggoufang").fadeOut();
		})
	//��Ʒ����
	$(".pro-search td a").click(function(){
		 $(this).addClass("pro-cur").siblings("a").removeClass("pro-cur");
		})
	$(".paixu a").click(function(){
		 $(this).addClass("pai-cur").siblings("a").removeClass("pai-cur");
		})
	//��ע��Դ
	$(".guanzhulist:first").fadeIn();
	$(".guanzhueq li").click(function(){
		$(this).addClass("guanzhueqcur").siblings("li").removeClass("guanzhueqcur");
		var guan=$(this).index();
		$(".guanzhulist").eq(guan).fadeIn().siblings(".guanzhulist").hide();
		})
	//��Ʒ����ҳ
	$(".proList:first").fadeIn();
	$(".proEq li").click(function(){
		$(this).addClass("proEqCur").siblings("li").removeClass("proEqCur");
		var proeq=$(this).index();
		$(".proList").eq(proeq).fadeIn().siblings(".proList").hide();
		})	
	})
	
//�����ղ� ��Ϊ��ҳ	
function AddFavorite(sURL, sTitle)
{
    try
    {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e)
        {
            alert("�����ղ�ʧ�ܣ���ʹ��Ctrl+D�������");
        }
    }
}
//��Ϊ��ҳ <a onclick="SetHome(this,window.location)">��Ϊ��ҳ</a>
function SetHome(obj,vrl){
        try{
                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
        }
        catch(e){
                if(window.netscape) {
                        try {
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                        }
                        catch (e) {
                                alert("�˲�����������ܾ���\n�����������ַ�����롰about:config�����س�\nȻ�� [signed.applets.codebase_principal_support]��ֵ����Ϊ'true',˫�����ɡ�");
                        }
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                        prefs.setCharPref('browser.startup.homepage',vrl);
                 }
        }
}	
	