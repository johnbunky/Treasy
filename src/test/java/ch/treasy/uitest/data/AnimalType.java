package ch.treasy.uitest.data;

public enum AnimalType {

   MUTTERSAU("Muttersau"),
   MASTSCHWEIN("Mastschwein"),
   JAGER("Jager"),
   EBER("Eber");

   private String name;

   private AnimalType(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

}
