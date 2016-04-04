package ir.iot.ubiqueui.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ir.iot.ubique.common.device.Device;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.message.Message;
import ir.iot.ubiqueui.service.UiServiceImpl;
import ir.iot.ubiqueui.service.api.UiService;

public class DeviceServlet extends HttpServlet {
  private static final long serialVersionUID = 8687783231956463346L;

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String chName = request.getParameter("deviceName");
    String url = request.getParameter("url");
    String topic = request.getParameter("topic");

    try {
      UiService service = new UiServiceImpl();
      service.addDevice(chName, url, topic);

      List<Device> devices = service.getDevices();
      // request.setAttribute("devices", devices);
      // request.getRequestDispatcher("/WEB-INF/devices.jsp").forward(request, response);

      HttpSession session = request.getSession();
      session.setAttribute("devices", devices);

      System.out.println(Arrays.toString(devices.toArray()));
      out.print(Arrays.toString(devices.toArray()));
    } catch (DomainException e) {
      e.printStackTrace();
      out.print("failed");
    }

    out.close();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html");
      PrintWriter pwriter = response.getWriter();

      String topic = request.getParameter("deviceTopic");

      try {
        UiService service = new UiServiceImpl();
        List<Message> messages = service.getMessages(topic);
        pwriter.print(Arrays.toString(messages.toArray()));

      } catch (DomainException e) {
        e.printStackTrace();
      }


      pwriter.close();
    } catch (Exception exp) {
      System.out.println(exp);
    }
  }


}
