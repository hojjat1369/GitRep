package ir.iot.unique.ubqui.main;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

public class SparkMain {
  /**
   * Map holding the books
   */


  public static void main(String[] args) {
    Spark.staticFileLocation("/public");

    FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
    Configuration freeMarkerConfiguration = new Configuration();
    freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(SparkMain.class, "/"));
    freeMarkerEngine.setConfiguration(freeMarkerConfiguration);


    get("/main", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      return freeMarkerEngine.render(new ModelAndView(attributes, "main.html"));
    });

    get("/devices", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      return freeMarkerEngine.render(new ModelAndView(attributes, "device.html"));
    });
    get("/newdevice", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      return freeMarkerEngine.render(new ModelAndView(attributes, "newdevice.html"));
    });
    get("/device", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      return freeMarkerEngine.render(new ModelAndView(attributes, "device.html"));
    });
    post("/adddevice", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      return freeMarkerEngine.render(new ModelAndView(attributes, "main.html"));
    });

    get("/login", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      return freeMarkerEngine.render(new ModelAndView(attributes, "login.html"));
    });
  }
}

