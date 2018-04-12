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

  @Test
  public void testGroupMachinesWithTheSameName()
  {
    EcommerceFacade ecommerceFacade = new EcommerceFacade();
    ecommerceFacade.addMachine("PRODIGIO", 1, 150);
    ecommerceFacade.addMachine("PIXIE", 1, 350);
    ecommerceFacade.addMachine("CITIZ", 1, 200);
    ecommerceFacade.addMachine("PIXIE", 1, 350);
    assertEquals(
        "Machines\n\tName: CITIZ\tQuantity: 1\tPrice: 200\n\tName: PIXIE\tQuantity: 2\tPrice: 700\n\tName: PRODIGIO\tQuantity: 1\tPrice: 150\n",
        ecommerceFacade.cartContent());
  }

  @Test
  public void testRomoveMachine()
  {
    EcommerceFacade ecommerceFacade = new EcommerceFacade();
    ecommerceFacade.addMachine("PRODIGIO", 1, 150);
    ecommerceFacade.addMachine("PIXIE", 1, 350);
    ecommerceFacade.addMachine("CITIZ", 1, 200);
    ecommerceFacade.addMachine("PIXIE", 1, 350);
    ecommerceFacade.removeMachine("PIXIE", 1);
    assertEquals(
        "Machines\n\tName: CITIZ\tQuantity: 1\tPrice: 200\n\tName: PIXIE\tQuantity: 1\tPrice: 350\n\tName: PRODIGIO\tQuantity: 1\tPrice: 150\n",
        ecommerceFacade.cartContent());
  }

  @Test
  public void testBuyCapsules()
  {
    EcommerceFacade ecommerceFacade = new EcommerceFacade();
    ecommerceFacade.addCapsule("ROMA", 20, 3);
    ecommerceFacade.addCapsule("RISTRETTO", 15, 4);
    ecommerceFacade.addCapsule("KAZAAR", 10, 5);
    assertEquals(
        "Capsules\n\tName: KAZAAR\tQuantity: 10\tPrice: 50\n\tName: RISTRETTO\tQuantity: 15\tPrice: 60\n\tName: ROMA\tQuantity: 20\tPrice: 60\n",
        ecommerceFacade.cartContent());
  }
}
