package ir.iot.ubiqueui.rest.ws;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ir.iot.ubique.common.service.ServiceResult;
import ir.iot.ubiqueui.rest.UBQServiceImpl;
import ir.iot.ubiqueui.rest.api.UBQService;
import ir.iot.ubiqueui.rest.entity.JsonRequest;


@Path("/data")
public class Services {

  @POST
  @Path("/addData")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ServiceResult addData(JsonRequest request) {
    UBQService service = new UBQServiceImpl();
    return service.addData(request);
  }
}
