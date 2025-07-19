package cl.hcslearning.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.Set;

public class App {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:data/inbox?noop=true")
                        .log("New file: ${header.CamelFileAbsolutePath}")
                        .to("file:data/outbox")
                ;
            }
        });

        context.start();
        //Thread.sleep(10000);
        Thread.currentThread().join();
        context.stop();
    }
}
