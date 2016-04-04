package ir.iot.ubiqueui.rest.api;

import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.service.ServiceResult;
import ir.iot.ubiqueui.rest.entity.JsonRequest;

public interface UBQService {

  public void defineDevice(String name, String url, String topic) throws DomainException;

  public ServiceResult addData(JsonRequest request);
}
