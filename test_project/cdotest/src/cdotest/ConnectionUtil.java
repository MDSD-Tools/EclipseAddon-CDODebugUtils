package cdotest;

import java.util.concurrent.ExecutorService;

import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.net4j.FactoriesProtocolProvider;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.buffer.IBufferProvider;
import org.eclipse.net4j.internal.tcp.TCPClientConnector;
import org.eclipse.net4j.protocol.IProtocolProvider;
import org.eclipse.net4j.util.concurrent.ThreadPool;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;

public class ConnectionUtil {

	public static CDONet4jSessionConfiguration createConfiguration(
			org.eclipse.net4j.internal.tcp.TCPClientConnector connector) {
		// Create configuration
		CDONet4jSessionConfiguration configuration1 = CDONet4jUtil.createNet4jSessionConfiguration();
		configuration1.setConnector(connector);
		configuration1.setRepositoryName("repo1");
		return configuration1;
	}

	public static TCPClientConnector createConnector() {
		// Prepare receiveExecutor
		ExecutorService receiveExecutor = ThreadPool.create();

		// Prepare bufferProvider
		IBufferProvider bufferProvider = Net4jUtil.createBufferPool();
		LifecycleUtil.activate(bufferProvider);

		IProtocolProvider protocolProvider = new FactoriesProtocolProvider(
				new org.eclipse.emf.cdo.internal.net4j.protocol.CDOClientProtocolFactory());

		// Prepare selector
		org.eclipse.net4j.internal.tcp.TCPSelector selector = new org.eclipse.net4j.internal.tcp.TCPSelector();
		selector.activate();

		// Prepare connector
		org.eclipse.net4j.internal.tcp.TCPClientConnector connector = new org.eclipse.net4j.internal.tcp.TCPClientConnector();
		connector.getConfig().setBufferProvider(bufferProvider);
		connector.getConfig().setReceiveExecutor(receiveExecutor);
		connector.getConfig().setProtocolProvider(protocolProvider);
		connector.getConfig().setNegotiator(null);
		connector.setSelector(selector);
		connector.setHost("localhost"); //$NON-NLS-1$
		connector.setPort(2036);
		connector.activate();
		return connector;
	}

}
