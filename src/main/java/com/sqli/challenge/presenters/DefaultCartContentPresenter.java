package com.sqli.challenge.presenters;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.sqli.challenge.items.Product;

public final class DefaultCartContentPresenter implements CartContentPresenter
{

  @Override
  public String present(Collection<? extends Entry<? extends Product, ? extends Integer>> cart)
  {
    final StringBuilder cartContentPresentation = new StringBuilder();

    cart.stream().collect(Collectors.groupingBy(cartEntry -> cartEntry.getKey().getClass().getSimpleName(),
        TreeMap::new, Collectors.toList())).forEach((productsEntriesType, productsEntries) ->
        {
          cartContentPresentation.append(productsEntriesType + "s" + "\n");

          productsEntries.stream().sorted(Comparator.comparing(productEntry -> productEntry.getKey().getName()))
              .forEach(productEntry ->
              {
                cartContentPresentation.append(String.format("\tName: %s", productEntry.getKey().getName()));
                cartContentPresentation.append(String.format("\tQuantity: %d", productEntry.getValue()));
                cartContentPresentation.append(
                    String.format("\tPrice: %.0f\n", productEntry.getKey().getPrice() * productEntry.getValue()));
              });
        });

    return cartContentPresentation.toString();
  }

}
