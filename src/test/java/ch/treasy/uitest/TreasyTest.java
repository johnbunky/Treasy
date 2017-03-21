package ch.treasy.uitest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Configuration;

import ch.treasy.uitest.data.DrugSource;
import ch.treasy.uitest.data.Farm;
import ch.treasy.uitest.data.Packaging;
import ch.treasy.uitest.data.Reason;
import ch.treasy.uitest.data.SimpleAnimal;
import ch.treasy.uitest.data.User;
import ch.treasy.uitest.page.TreasyPage;

public class TreasyTest {

   static List<Stock> stock = getStock();
   static List<Treatment> treatments = getTreatments(stock);

   @BeforeClass
   public static void setup() {
      Configuration.browser = "chrome";
      Configuration.holdBrowserOpen = true;
      Configuration.timeout = Duration.TEN_SECONDS.getMillis();
   }

   @Test
   public void test() {
      TreasyPage page = new TreasyPage();
      page = page.register(User.UI_TEST).baseData(Farm.PETER_MUSTER, DrugSource.MEIER_MUELLER);
      page.registerStock(stock);

      // Create treatments & check if journal corresponds with treatment list 
      page.treat(treatments);
      assertTrue(page.check(treatments));

      // Change packaging & check of treatment list is Ok
      Packaging newPackaging = Packaging.OXYTOCIN;
      int pos = 0;
      page.change(treatments, pos, newPackaging);
      assertEquals(newPackaging, treatments.get(pos).getPackaging());

      // Change dose & check if treatment list is Ok
      Integer newDose = Integer.valueOf(10);
      pos = 1;
      page.change(treatments, pos, newDose.intValue());
      assertEquals(newDose, treatments.get(pos).getDose());

      // Change one reason & check if treatment list is Ok
      Reason newReason = Reason.INFECTION;
      pos = 2;
      page.change(treatments, pos, newReason);
      assertEquals(newReason, treatments.get(pos).getReasons().get(0));

      // Change two reasons & check if treatment list is Ok
      Reason newReasonOne = Reason.GEBURTSHILFE;
      Reason newReasonTwo = Reason.DURCHFALL;
      pos = 3;
      page.change(treatments, pos, newReasonOne, newReasonTwo);
      assertEquals(2, treatments.get(pos).getReasons().size());

      // Change animal & check if treatment list is Ok
      pos = 4;
      page.change(treatments, pos, SimpleAnimal.MARY);
      assertEquals(Packaging.OXYTOCIN, treatments.get(pos).getPackaging());

      // Change every aspect of the treatment
      page.change(treatments, 5, Packaging.DINOLYTIC, 7, Reason.GEBURTSHILFE, SimpleAnimal.SUSI);

      // Check if journal still corresponds with treatment list
      assertTrue(page.check(treatments));
   }

   private static List<Treatment> getTreatments(List<Stock> stock) {
      List<Treatment> treatments = new ArrayList<Treatment>();
      treatments.add(new Treatment(Packaging.DINOLYTIC, Integer.valueOf(2), Reason.GEBURTSHILFE, SimpleAnimal.MARY));
      treatments.add(new Treatment(Packaging.DINOLYTIC, Integer.valueOf(5), Reason.INFECTION, SimpleAnimal.SUSI));
      treatments.add(new Treatment(Packaging.OXYTOCIN, Integer.valueOf(2), Reason.DURCHFALL, SimpleAnimal.JOE));
      treatments.add(new Treatment(Packaging.OXYTOCIN, Integer.valueOf(3), Reason.DURCHFALL, SimpleAnimal.PETER));
      treatments.add(new Treatment(Packaging.OXYTOCIN, Integer.valueOf(2), Reason.GEBURTSHILFE, SimpleAnimal.SUSI));
      treatments.add(new Treatment(Packaging.OXYTOCIN, Integer.valueOf(3), Reason.INFECTION, SimpleAnimal.JOE));
      return treatments;
   }

   private static List<Stock> getStock() {
      List<Stock> stock = new ArrayList<Stock>();
      stock.add(new Stock(Packaging.DINOLYTIC, null));
      stock.add(new Stock(Packaging.OXYTOCIN, Integer.valueOf(75)));
      return stock;
   }
}
