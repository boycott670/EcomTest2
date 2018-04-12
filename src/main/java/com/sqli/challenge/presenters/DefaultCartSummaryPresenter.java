package com.sqli.challenge.presenters;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.sqli.challenge.items.Product;

public final class DefaultCartSummaryPresenter implements CartSummaryPresenter
{

  @Override
  public String present(Collection<? extends Entry<? extends Product, ? extends Integer>> cart)
  {
    final Map<? extends String, ? extends Integer> sumOfQuantitiesByGroupingType = cart.stream()
        .collect(Collectors.groupingBy(cartEntry -> cartEntry.getKey().getClass().getSimpleName(),
            Collectors.summingInt(Entry::getValue)));

    final Map<? extends String, ? extends Double> sumOfTotalPricesByGroupingType = cart.stream()
        .collect(Collectors.groupingBy(cartEntry -> cartEntry.getKey().getClass().getSimpleName(),
            Collectors.summingDouble(cartEntry -> cartEntry.getKey().getPrice() * cartEntry.getValue())));

    final double sumOfTotalPrices = sumOfTotalPricesByGroupingType.entrySet().stream().map(Entry::getValue)
        .mapToDouble(Double::valueOf).sum();

    final StringBuilder cartSummaryPresentation = new StringBuilder();

    sumOfQuantitiesByGroupingType.keySet().stream().sorted().forEach(groupingType ->
    {
      cartSummaryPresentation.append(String.format("%ss\n", groupingType));
      cartSummaryPresentation.append(String.format("\tQuantity: %d\tPrice: %.0f\n",
          sumOfQuantitiesByGroupingType.get(groupingType), sumOfTotalPricesByGroupingType.get(groupingType)));
    });

    cartSummaryPresentation.append(String.format("Total Price: %.0f\n", sumOfTotalPrices));

    return cartSummaryPresentation.toString();
  }

}
