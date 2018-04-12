package com.sqli.challenge.validators.cart.content;

import java.util.Collection;
import java.util.Map.Entry;

import com.sqli.challenge.items.Product;

public interface CartContentValidator
{
  Collection<? extends String> validateCartContent(
      final Collection<? extends Entry<? extends Product, ? extends Integer>> cartContent);
}
