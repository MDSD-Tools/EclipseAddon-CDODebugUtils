package cdotest;

import cDOWebPage.CDOWebPage;
import cDOWebPage.impl.CDOWebPageFactoryImpl;

public class DemoTransientState {
	
	public static void main(String[] args) {
		CDOWebPage cdoWebPage = CDOWebPageFactoryImpl.eINSTANCE.createCDOWebPage();
		cdoWebPage.setTitle("foo");
	    System.out.println(">> SET BREAKPOINT HERE <<");
	    // cdoWebPage will have TRANSIENT state (viewAndState.state)
	}

}
