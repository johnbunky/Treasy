package ch.treasy.uitest.data;

public enum Farm {
   
   PETER_MUSTER("1234567", "Musterhof", "Peter Muster", "Musterstrasse 1", "1000", "Musterlingen");

   private String address2;
   private String city;
   private String tvdNumber;
   private String name;
   private String address1;
   private String zip;

   private Farm(String tvdNumber, String name, String address1, String address2, String zip, String city) {
      this.tvdNumber = tvdNumber;
      this.name = name;
      this.address1 = address1;
      this.address2 = address2;
      this.zip = zip;
      this.city = city;
   }
   
   public String getTvdNumber() {
      return tvdNumber;
   }

   public String getName() {
      return name;
   }

   public String getAddress1() {
      return address1;
   }

   public String getAddress2() {
      return address2;
   }

   public String getZip() {
      return zip;
   }

   public String getCity() {
      return city;
   }

}
