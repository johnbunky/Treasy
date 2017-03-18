package ch.treasy.uitest;

import ch.treasy.uitest.data.Packaging;

public class Stock {

   private Packaging packaging;
   private Integer alternativeContent;

   public Stock(Packaging packaging, Integer alternateContent) {
      this.packaging = packaging;
      this.alternativeContent = alternateContent;
   }

   public Packaging getPackaging() {
      return packaging;
   }

   public Integer getAlternativeContent() {
      return alternativeContent;
   }

}
