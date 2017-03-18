package ch.treasy.uitest.data;

public enum Reason {

   GEBURTSHILFE("Geburtshilfe"),
   INFECTION("Bakterielle Infektion"),
   DURCHFALL("Durchfall");

   private String name;

   private Reason(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
