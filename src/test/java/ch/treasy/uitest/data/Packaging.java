package ch.treasy.uitest.data;

public enum Packaging {

   DINOLYTIC("Dinolytic 5 mg/ml", "50 ml", "40063.021"),
   OXYTOCIN("Oxytocin Stricker", "100 ml", "33450.044");

   private String drugName;

   private String size;

   private String swissmedicNumber;

   private Packaging(String drugName, String size, String swissmedicNumber) {
      this.drugName = drugName;
      this.size = size;
      this.swissmedicNumber = swissmedicNumber;
   }

   public String getDrugName() {
      return drugName;
   }

   public String getSize() {
      return size;
   }

   public String getSwissmedicNumber() {
      return swissmedicNumber;
   }

   public String getFullName() {
      return drugName + " (" + size + ", " + swissmedicNumber + ")";
   }

}
