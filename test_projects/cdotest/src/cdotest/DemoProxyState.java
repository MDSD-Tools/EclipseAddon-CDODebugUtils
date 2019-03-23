package cdotest;

import java.util.UUID;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.util.ConcurrentAccessException;
import org.eclipse.emf.ecore.EcoreFactory;

public class DemoProxyState {
	public static void main(String[] args) throws ConcurrentAccessException, CommitException, InterruptedException {
	    CDOSession session1 = ConnectionUtil.createConfiguration(ConnectionUtil.createConnector()).openNet4jSession();
	    CDOSession session2 = ConnectionUtil.createConfiguration(ConnectionUtil.createConnector()).openNet4jSession();
	    String id = UUID.randomUUID().toString();
	    CDOTransaction transaction = session1.openTransaction();
	    CDOResource resource = transaction.createResource(id);
	    resource.getContents().add(EcoreFactory.eINSTANCE.createEObject());
	    transaction.commit();
	    CDOResource resource2 = session2.openTransaction().getResource(id);
	    System.out.println(">> SET BREAKPOINT HERE <<");
	    // resource2 will have PROXY state (viewAndState.state)
	}
}
