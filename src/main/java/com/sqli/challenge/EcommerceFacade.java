package com.sqli.challenge;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.sqli.challenge.entities.Machine;
import com.sqli.challenge.entities.Product;
import com.sqli.challenge.presenters.CartContentPresenter;
import com.sqli.challenge.presenters.DefaultCartContentPresenter;

public final class EcommerceFacade
{
  private final CartContentPresenter cartContentPresenter = new DefaultCartContentPresenter();
  private final Map<? super String, Product> products = new HashMap<>();
  
  private void addProduct (final Product product)
  {
    products.put(product.getName(), product);
  }
  
  public void addMachine (final String code, final int quantity, final int price)
  {
    addProduct(new Machine(code, quantity, price));
  }
  
  public String cartContent ()
  {
    return cartContentPresenter.presentCartContent(
        products.values()
          .stream()
          .collect(Collectors.groupingBy(Product::groupingByIdentifier)),
        Comparator.comparing(Product::getName));
  }
}
