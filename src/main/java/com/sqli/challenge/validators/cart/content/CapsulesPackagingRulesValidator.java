package com.sqli.challenge.validators.cart.content;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.sqli.challenge.items.Capsule;
import com.sqli.challenge.items.Product;

public class CapsulesPackagingRulesValidator implements CartContentValidator
{

  @Override
  public Collection<? extends String> validateCartContent(
      Collection<? extends Entry<? extends Product, ? extends Integer>> cartContent)
  {
    return cartContent.stream().filter(productEntry -> productEntry.getKey() instanceof Capsule)
        .filter(productEntry -> productEntry.getValue() % 5 != 0).map(Entry::getKey).map(Product::getName)
        .map(capsuleName -> String.format("%s: Invalid Quantity, must be a multiple of 5", capsuleName))
        .collect(Collectors.toList());
  }

}
