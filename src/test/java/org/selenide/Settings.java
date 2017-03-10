package org.selenide;

public class Settings {

   static final Environment ENV = Environment.TST;

   public static String getUrl() {
      return ENV.getUrl();
   }

   public static String getEnvId() {
      return ENV.name();
   }

}
