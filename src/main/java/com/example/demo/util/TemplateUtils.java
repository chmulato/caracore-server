package com.example.demo.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jarbas on 26/10/15.
 */
public class TemplateUtils {

    public static VelocityEngine getVelocityEngine() {
        Properties properties = new Properties();
        properties.put("input.encoding", "UTF-8");
        properties.put("output.encoding", "UTF-8");
        properties.put("resource.loader", "classpath");
        properties.put("classpath.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
                "org.apache.velocity.runtime.log.Log4JLogChute");
        velocityEngine.setProperty("runtime.log.logsystem.log4j.logger", "application.velocity");
        velocityEngine.init(properties);

        return velocityEngine;
    }

    public static String getContent(String templateName, Map<String, Object> params) {
        VelocityEngine velocityEngine = getVelocityEngine();
        Template template = velocityEngine.getTemplate("/templates/" + templateName);

        VelocityContext velocityContext = new VelocityContext();
        params.entrySet().forEach(p -> velocityContext.put(p.getKey(), p.getValue()));

        StringWriter output = new StringWriter();
        template.merge(velocityContext, output);

        return output.toString();
    }
}
