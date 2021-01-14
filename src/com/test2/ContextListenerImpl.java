package com.test2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// 어노테이션을 추가함
@WebListener
public class ContextListenerImpl implements ServletContextListener {

	// 컨테이너 (Container) : 아파치 톰켓을 의미함
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		System.out.println("웹 어플리케이션 제거 ....");

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("웹 어플리케이션 초기화 (어노테이션)....");
	}

}
