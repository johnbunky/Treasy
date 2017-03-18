package ch.treasy.uitest;

import java.util.ArrayList;
import java.util.List;

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
      page.treat(treatments);
   }

   private static List<Treatment> getTreatments(List<Stock> stock) {
      List<Treatment> treatments = new ArrayList<Treatment>();
      treatments.add(new Treatment(stock.get(0 % stock.size()).getPackaging(), Integer.valueOf(2), Reason.GEBURTSHILFE, SimpleAnimal.MARY));
      treatments.add(new Treatment(stock.get(1 % stock.size()).getPackaging(), Integer.valueOf(5), Reason.INFECTION, SimpleAnimal.SUSI));
      treatments.add(new Treatment(stock.get(1 % stock.size()).getPackaging(), Integer.valueOf(2), Reason.DURCHFALL, SimpleAnimal.JOE));
      treatments.add(new Treatment(stock.get(1 % stock.size()).getPackaging(), Integer.valueOf(3), Reason.DURCHFALL, SimpleAnimal.PETER));
      return treatments;
   }
   
   private static List<Stock> getStock() {
      List<Stock> stock = new ArrayList<Stock>();
      stock.add(new Stock(Packaging.DINOLYTIC, null));
      stock.add(new Stock(Packaging.OXYTOCIN, Integer.valueOf(75)));
      return stock;
   }
}
