package cn.peng.spring.mvc.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //所有需要被Spring管理的程序类一定要进行配置
public class EchoAciton {
	private Logger log = LoggerFactory.getLogger(EchoAciton.class);
	
	@RequestMapping("/echo")//以后访问此方法的路径就是echo.action
	public ModelAndView echo(String msg) {//该参数会根据参数名称自动进行匹配处理
		this.log.info("接受请求参数"+msg);
		//ModelAndView主要功能是设置跳转路径以及惊醒request属性的保存
		ModelAndView mav = new ModelAndView("/pages/message/message_show.jsp");
		mav.addObject("echoMessage", "msg"+msg);//设置request属性范围
		System.out.println(msg);
		return mav;
	}
}
