package mvcMem.control;

import mvcMem.action.Action;
import mvcMem.action.IndexAction;
import mvcMem.action.RegFormAction;

// ��ɾ �ش��ϴ� ���� �׼��� �������� Factroy Ŭ����

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
