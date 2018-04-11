package com.sqli.challenge.entities;

public abstract class Product
{
  private final String name;
  private final int quantity;
  private final double price;
  
  Product(String name, int quantity, double price)
  {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }
  
  public abstract String groupingByIdentifier ();
  
  public String getName()
  {
    return name;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public double getPrice()
  {
    return price;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (name == null)
    {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
}
