package ch.treasy.uitest;

public enum Duration {

   ONE_SECOND(1000),
   TEN_SECONDS(10000),
   TWENTY_SECONDS(20000);

   private int millis;

   private Duration(int millis) {
      this.millis = millis;
   }

   public int getMillis() {
      return millis;
   }
}
