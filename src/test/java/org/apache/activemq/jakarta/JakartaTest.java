package org.apache.activemq.jakarta;

import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.junit.Test;

import java.net.ConnectException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JakartaTest {
    private PooledConnectionFactory createPooledConnectionFactory(String userName, String password, String brokerURL) {
        PooledConnectionFactory pf = new PooledConnectionFactory();
        assertTrue(pf instanceof jakarta.jms.ConnectionFactory);

        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(userName, password, brokerURL);

        pf.setConnectionFactory(cf);
        pf.setMaxConnections(10);

        return pf;
    }

    @Test
    public void testConnectFail() throws JMSException {
        //PooledConnectionFactory pf = createPooledConnectionFactory("admin", "admin", "tcp://localhost:61616");
        PooledConnectionFactory pf = createPooledConnectionFactory("admin", "admin", "tcp://localhost:65535");

        try {
            Connection connection = pf.createConnection();
            fail();
        } catch (JMSException e) {
            //e.printStackTrace();
            Throwable cause = e.getCause().getCause();
            assertTrue(cause instanceof ConnectException);
        }
    }
}
