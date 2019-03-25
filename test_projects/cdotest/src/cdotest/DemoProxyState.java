package cdotest;

import java.util.UUID;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.util.ConcurrentAccessException;

import cDOWebPage.CDOWebPage;
import cDOWebPage.impl.CDOWebPageFactoryImpl;

public class DemoProxyState {

	public static void main(String[] args) throws ConcurrentAccessException, CommitException {
		CDOWebPage cdoWebPage = CDOWebPageFactoryImpl.eINSTANCE.createCDOWebPage();

		String resourceId = UUID.randomUUID().toString();

	    CDOSession session1 = ConnectionUtil.createConfiguration(ConnectionUtil.createConnector()).openNet4jSession();
	    CDOSession session2 = ConnectionUtil.createConfiguration(ConnectionUtil.createConnector()).openNet4jSession();

	    CDOTransaction transaction1 = session1.openTransaction();
	    CDOResource resource1 = transaction1.createResource(resourceId);
	    resource1.getContents().add(cdoWebPage);
	    transaction1.commit();

	    CDOTransaction trasaction2 = session2.openTransaction();
	    CDOResource resource2 = trasaction2.getResource(resourceId);
	    CDOWebPage cdoWebPage2 = (CDOWebPage) resource2.getContents().get(0);

	    CDOTransaction transaction3 = session1.openTransaction();
	    CDOResource resource3 = transaction3.getResource(resourceId);
	    CDOWebPage cdoWebPage3 = (CDOWebPage) resource3.getContents().get(0);
	    cdoWebPage3.setTitle("bar");
	    transaction3.commit();

	    System.out.println(">> SET BREAKPOINT HERE <<");
	    // cdoWebPage2 will have PROXY state (viewAndState.state)
	}

}
