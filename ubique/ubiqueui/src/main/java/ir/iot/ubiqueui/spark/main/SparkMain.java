package ir.iot.ubiqueui.spark.main;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;


public class SparkMain {


  public static void main(String[] args) {


    FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
    Configuration freeMarkerConfiguration = new Configuration();
    freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(SparkMain.class, "/"));
    freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

    staticFileLocation("/public");

    // get all post (using HTTP get method)
    get("/posts", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("message", "hello");
      return freeMarkerEngine.render(new ModelAndView(attributes, "templates/hello.ftl"));
    });

  }
}
