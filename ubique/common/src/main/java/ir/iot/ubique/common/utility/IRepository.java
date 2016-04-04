package ir.iot.ubique.common.utility;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {

  void save(T entity);

  void update(T entity);

  void remove(T entity);

  T findById(UUID id);

  List<T> findAll();

  Long getTotalResult();
}
