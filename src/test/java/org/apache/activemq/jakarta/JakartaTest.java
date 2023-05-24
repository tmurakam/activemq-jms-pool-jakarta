package org.apache.activemq.jakarta;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JakartaTest {
    @Test
    public void test() {
        PooledConnectionFactory f = new PooledConnectionFactory();

        assertTrue(f instanceof jakarta.jms.ConnectionFactory);
    }
}
