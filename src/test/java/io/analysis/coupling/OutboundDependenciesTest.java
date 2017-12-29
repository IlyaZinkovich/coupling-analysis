package io.analysis.coupling;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class OutboundDependenciesTest {

    @Test
    public void testCallerEfferentCoupling() throws IOException, URISyntaxException {
        String callerSourceFilePath = this.getClass().getResource("classes/Caller.class").getPath().substring(1);
        InstrumentedClass instrumentedClass = new InstrumentedClass(callerSourceFilePath);
        OutboundDependencies outboundDependencies = new OutboundDependencies(instrumentedClass);
        EfferentCoupling efferentCoupling = outboundDependencies.efferentCoupling();
        assertEquals("io/analysis/coupling/classes/Caller", efferentCoupling.className());
        assertEquals(3, efferentCoupling.value());
        assertEquals(2, efferentCoupling.statistics().get("io/analysis/coupling/classes/Receiver").intValue());
    }

    @Test
    public void testReceiverEfferentCoupling() throws IOException, URISyntaxException {
        String callerSourceFilePath = this.getClass().getResource("classes/Receiver.class").getPath().substring(1);
        InstrumentedClass instrumentedClass = new InstrumentedClass(callerSourceFilePath);
        OutboundDependencies outboundDependencies = new OutboundDependencies(instrumentedClass);
        EfferentCoupling efferentCoupling = outboundDependencies.efferentCoupling();
        assertEquals("io/analysis/coupling/classes/Receiver", efferentCoupling.className());
        assertEquals(2, efferentCoupling.value());
    }
}
