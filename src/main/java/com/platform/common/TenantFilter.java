package com.platform.common;
import com.platform.tenant.TenantContext;
import jakarta.servlet.*; import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class TenantFilter implements Filter {
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest r = (HttpServletRequest) req;
    String tid = r.getHeader("X-Tenant-ID");
    if(tid==null||tid.isBlank()) tid="default";
    try { TenantContext.setTenantId(tid); chain.doFilter(req,res); } finally { TenantContext.clear(); }
  }
}
