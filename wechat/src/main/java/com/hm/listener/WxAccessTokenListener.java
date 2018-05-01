package com.hm.listener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.hm.utils.AccessTokenUtil;
  
public class WxAccessTokenListener implements ApplicationListener<ContextRefreshedEvent>  {  
      
	private static ApplicationContext ac = null;
	
    public void onApplicationEvent(ContextRefreshedEvent event) {  
        if(event.getApplicationContext().getParent() == null){  
        	if(ac == null){
    			ac = event.getApplicationContext();
    			Runnable runnable = new Runnable() {  
                    public void run() {  
                        /** 
                         * 定时设置accessToken 
                         */
                    	try {
                    		AccessTokenUtil.initAndSetAccessToken(ac);
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                        
                    }  
                };  
                  
                ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
                service.scheduleAtFixedRate(runnable, 1, 7000, TimeUnit.SECONDS);
            }
        }  
    }
    
}