package com.sqli.challenge.entities;

public final class Machine extends Product
{
  public Machine(String code, int quantity, double price)
  {
    super(code, quantity, price);
  }

  @Override
  public String groupingByIdentifier()
  {
    return "Machines";
  }
}
