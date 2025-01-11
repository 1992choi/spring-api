package choi.web.api.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Container;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.valves.ErrorReportValve;
import org.springframework.boot.autoconfigure.websocket.servlet.TomcatWebSocketServletWebServerCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Slf4j
@Configuration(proxyBeanMethods = false)
class CustomTomcatConfig extends TomcatWebSocketServletWebServerCustomizer {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addContextCustomizers(context -> {
            final Container parent = context.getParent();

            if (context.getParent() instanceof StandardHost) {
                ((StandardHost) parent).setErrorReportValveClass(CustomErrorReportValve.class.getName());
                ((StandardHost) parent).addValve(new CustomErrorReportValve());
            }
        });
    }

    @Override
    public int getOrder() {
        return 100;
    }

    private static class CustomErrorReportValve extends ErrorReportValve {

        @Override
        protected void report(Request request, Response response, Throwable throwable) {
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
                out.write("{\"resultCode\":\"9999\",\"resultMessage\":\"Bad Request\"}");
                out.close();
            } catch (IOException e) {
                log.error("CustomErrorReportValve", e);
            }
        }
    }

}

