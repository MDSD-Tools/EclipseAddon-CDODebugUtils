package cdotest;

import java.io.IOException;
import java.util.UUID;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.util.ConcurrentAccessException;

import cDOWebPage.CDOWebPage;
import cDOWebPage.impl.CDOWebPageFactoryImpl;

public class DemoNewState {

	public static void main(String[] args) throws ConcurrentAccessException, CommitException, InterruptedException, IOException {
		CDOWebPage cdoWebPage = CDOWebPageFactoryImpl.eINSTANCE.createCDOWebPage();
		cdoWebPage.setTitle("foo");
		cdoWebPage.setTransientAttribute("bar");
		cdoWebPage.getMultivaluedAttribute().add("bak");
	    CDOSession session = ConnectionUtil.createConfiguration(ConnectionUtil.createConnector()).openNet4jSession();
	    String id = UUID.randomUUID().toString();
	    CDOTransaction transaction = session.openTransaction();
	    CDOResource resource = transaction.createResource(id);
	    resource.getContents().add(cdoWebPage);
	    System.out.println(">> SET BREAKPOINT HERE <<");
	    // cdoWebPage will have NEW state (viewAndState.state)
	}


}

