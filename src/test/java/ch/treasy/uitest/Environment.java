package ch.treasy.uitest;

public enum Environment {

   LOC("http://localhost:8080/TreasyServer", 22),
   TST("https://treasy-tst.eu-gb.mybluemix.net", 25);

   private String serverUrl;

   private int searchLength;
   
   private Environment(String serverUrl, int searchLength) {
      this.serverUrl = serverUrl;
      this.searchLength = searchLength;
   }

   /**
    * The confirmation link on the GMail page will be separated by &lt;wbr&gt; tags so search will work only for the first part.
    * 
    * @return the first part of the server URL to use as search string to find the confirmation link.
    */
   public String getConfirmationLinkSearchString() {
      return serverUrl.substring(0, searchLength);
   }

   public String getServerUrl() {
      return serverUrl;
   }
}