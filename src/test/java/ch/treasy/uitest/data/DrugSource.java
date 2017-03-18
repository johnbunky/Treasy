package ch.treasy.uitest.data;

public enum DrugSource {
   
   MEIER_MUELLER("Meier & Müller Tierärzte");
   
   private String name;

   private DrugSource(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

}
