package org.selenide;

public enum Environment {
   
   LOC("http://localhost:8080/TreasyServer"),
   TST("https://treasy-tst.eu-gb.mybluemix.net");

   private String url;

   private Environment(String url) {
      this.url = url;
   }

   public String getUrl() {
      return url;
   }
}
