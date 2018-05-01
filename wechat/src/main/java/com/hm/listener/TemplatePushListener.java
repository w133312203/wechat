package com.hm.listener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.hm.utils.TemplatePushUtil;
  
public class TemplatePushListener implements ApplicationListener<ContextRefreshedEvent>  {

	private static ApplicationContext ac = null;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			if(ac == null){
				ac = event.getApplicationContext();
				try {
					TemplatePushUtil.push(ac);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
		}
	}
    
}