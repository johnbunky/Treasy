package org.selenide;

public class Settings {

   static final Environment ENV = Environment.TST;

   public static String getConfirmationLinkSearchString() {
      return ENV.getConfirmationLinkSearchString();
   }

   public static String getEnvId() {
      return ENV.name();
   }

   public static String getServerUrl() {
      return ENV.getServerUrl();
   }
}
