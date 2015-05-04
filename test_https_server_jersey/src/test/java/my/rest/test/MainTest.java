package my.rest.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class MainTest {
	private static final String TRUSTORE_CLIENT_FILE = "./truststore_client";
	private static final String TRUSTSTORE_CLIENT_PWD = "asdfgh";
	private static final String KEYSTORE_CLIENT_FILE = "./keystore_client";
	private static final String KEYSTORE_CLIENT_PWD = "asdfgh";
	
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		SslConfigurator sslConfig = SslConfigurator.newInstance()
				.trustStoreFile(TRUSTORE_CLIENT_FILE)
				.trustStorePassword(TRUSTSTORE_CLIENT_PWD)
				.keyStoreFile(KEYSTORE_CLIENT_FILE)
				.keyPassword(KEYSTORE_CLIENT_PWD);
		final SSLContext sslContext = sslConfig.createSSLContext();
		final ClientConfig clientConfig = new ClientConfig().connectorProvider(new HttpUrlConnectorProvider());
		Client client = ClientBuilder.newBuilder().withConfig(clientConfig)
				.sslContext(sslContext).build();
		client.register(HttpAuthenticationFeature.basic("user", "password"));
		System.out.println("Client: GET " + Server.BASE_URI);
		WebTarget target = client.target(Server.BASE_URI);
		final Response response = target.path("/").request().get(Response.class);
		System.out.println(response.getStatus());
		byte[] msg = new byte[1024];
		System.out.println(((InputStream)response.getEntity()).read(msg));
		for(int i = 0; i < msg.length; i++)
		{
			System.out.print((char) msg[i]);
		}
	}
}
