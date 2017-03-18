package ch.treasy.uitest;

import ch.treasy.uitest.data.Packaging;
import ch.treasy.uitest.data.Reason;
import ch.treasy.uitest.data.SimpleAnimal;

public class Treatment {

   private Packaging packaging;
   private Integer dose;
   private Reason reason;
   private SimpleAnimal simpleAnimal;

   public Treatment(Packaging packaging, Integer dose, Reason reason, SimpleAnimal simpleAnimal) {
      this.packaging = packaging;
      this.dose = dose;
      this.reason = reason;
      this.simpleAnimal = simpleAnimal;
   }

   public Packaging getPackaging() {
      return packaging;
   }

   public Integer getDose() {
      return dose;
   }

   public Reason getReason() {
      return reason;
   }

   public SimpleAnimal getSimpleAnimal() {
      return simpleAnimal;
   }

}
