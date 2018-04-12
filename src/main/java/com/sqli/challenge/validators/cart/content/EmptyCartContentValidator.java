package com.sqli.challenge.validators.cart.content;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Optional;

import com.sqli.challenge.items.Product;

public final class EmptyCartContentValidator implements CartContentValidator
{

  @Override
  public Collection<? extends String> validateCartContent(
      Collection<? extends Entry<? extends Product, ? extends Integer>> cartContent)
  {
    return Optional.ofNullable(cartContent).filter(Collection::isEmpty).map(__ -> Arrays.asList("Empty Cart"))
        .orElse(Arrays.asList());
  }

}
