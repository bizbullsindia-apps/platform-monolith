package com.platform.tenant;
public class TenantContext {
  private static final ThreadLocal<String> CURRENT = new ThreadLocal<>();
  public static void setTenantId(String id){CURRENT.set(id);}
  public static String getTenantId(){return CURRENT.get();}
  public static void clear(){CURRENT.remove();}
}
