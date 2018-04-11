package com.sqli.challenge.presenters;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.sqli.challenge.entities.Product;

public final class DefaultCartContentPresenter implements CartContentPresenter
{
  @Override
  public String presentCartContent(Map<? extends String, ? extends Collection<? extends Product>> products,
      Comparator<? super Product> productComparator)
  {
    final StringBuilder cartContent = new StringBuilder();
    
    for (final Entry<? extends String, ? extends Collection<? extends Product>> productsEntry : products.entrySet())
    {
      cartContent.append(String.format("%s\n", productsEntry.getKey()));
      
      for (final Product product : productsEntry.getValue()
          .stream()
          .sorted(productComparator)
          .collect(Collectors.toList()))
      {
        cartContent.append(String.format("\tName: %s", product.getName()));
        cartContent.append(String.format("\tQuantity: %d", product.getQuantity()));
        cartContent.append(String.format("\tPrice: %.0f\n", product.getPrice()));
      }
    }
    
    return cartContent.toString();
  }
}
