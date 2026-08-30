package com.platform.tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TenantService {
  @Autowired private TenantRepository repo;
  public List<Tenant> list(){return repo.findAll();}
  public Tenant create(Tenant t){
    if(repo.existsById(t.getId())) throw new RuntimeException("Tenant exists");
    return repo.save(t);
  }
}
