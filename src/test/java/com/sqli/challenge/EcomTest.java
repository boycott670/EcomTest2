package com.sqli.challenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EcomTest
{
  @Test
  public void testBuyMachines()
  {
    EcommerceFacade ecommerceFacade = new EcommerceFacade();
    ecommerceFacade.addMachine("PRODIGIO", 1, 150);
    ecommerceFacade.addMachine("PIXIE", 1, 350);
    ecommerceFacade.addMachine("CITIZ", 1, 200);
    assertEquals(
        "Machines\n\tName: CITIZ\tQuantity: 1\tPrice: 200\n\tName: PIXIE\tQuantity: 1\tPrice: 350\n\tName: PRODIGIO\tQuantity: 1\tPrice: 150\n",
        ecommerceFacade.cartContent());
  }
}
