package ch.treasy.uitest.data;

public enum SimpleAnimal {

   MARY("Mary", AnimalType.MUTTERSAU),
   SUSI("Susi", AnimalType.MASTSCHWEIN),
   JOE("Joe", AnimalType.JAGER),
   PETER("Peter", AnimalType.EBER);

   private String name;

   private AnimalType type;

   private SimpleAnimal(String name, AnimalType type) {
      this.name = name;
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public AnimalType getType() {
      return type;
   }

}
