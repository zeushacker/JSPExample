package mvcMem.control;

import mvcMem.action.Action;
import mvcMem.action.IndexAction;
import mvcMem.action.RegFormAction;

// 명령어에 해당하는 실제 액션을 생성해줄 Factroy 클래스

public class ActionFactory {

	private static ActionFactory factory;
	
	private ActionFactory() {}
	
	public static synchronized ActionFactory getInstance() {
		if(factory == null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	
	
	public Action getAction(String cmd) {
		Action action = null;
		
		switch(cmd) {
		case "index" :
			action = new IndexAction();
			break;
			
		case "regForm" :
			action = new RegFormAction();
			break;
			
			
			
			
			
			
			
		}
		
		return action;
	}
	
	
	
	
	
	
	
}
