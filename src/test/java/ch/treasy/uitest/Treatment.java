package ch.treasy.uitest;

import java.util.ArrayList;
import java.util.List;

import ch.treasy.uitest.data.Packaging;
import ch.treasy.uitest.data.Reason;
import ch.treasy.uitest.data.SimpleAnimal;

public class Treatment {

   private Packaging packaging;
   private Integer dose;
   private List<Reason> reasons = new ArrayList<Reason>();
   private SimpleAnimal simpleAnimal;

   public Treatment(Packaging packaging, Integer dose, Reason reason, SimpleAnimal simpleAnimal) {
      this.packaging = packaging;
      this.dose = dose;
      reasons.add(reason);
      this.simpleAnimal = simpleAnimal;
   }

   public Packaging getPackaging() {
      return packaging;
   }

   public Integer getDose() {
      return dose;
   }

   public List<Reason> getReasons() {
      return reasons;
   }

   public SimpleAnimal getSimpleAnimal() {
      return simpleAnimal;
   }

}
