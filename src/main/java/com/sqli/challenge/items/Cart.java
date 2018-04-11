package com.sqli.challenge.items;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class Cart
{
  private final Map<Product, Integer> content;

  public Cart()
  {
    content = new HashMap<>();
  }

  public void addProduct(final Product product, final Integer quantity)
  {
    content.merge(product, quantity, Integer::sum);
  }

  public Collection<? extends Entry<? extends Product, ? extends Integer>> getContent()
  {
    return content.entrySet();
  }
}
