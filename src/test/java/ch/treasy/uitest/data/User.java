package ch.treasy.uitest.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum User {
   
   UI_TEST("treasy.uitest", "N3cqNkjF6RvN");
   
   private String uniqueId;
   private String address;
   private String mailPassword;

   static final String MAIL_DOMAIN = "gmail.com";
   
   private User(String address, String mailPassword) {
      this.address = address;
      this.mailPassword = mailPassword;
      uniqueId =  new SimpleDateFormat("yyMMddHHmmss").format(new Date()); // Create an unique string
   }
   
   public String getNewMailAddress() {
      return address + "+" + uniqueId + "@" + MAIL_DOMAIN;
   }

   public String getMailAddress() {
      return address + "@" + MAIL_DOMAIN;
   }

   public String getMailPassword() {
      return mailPassword;
   }

}
