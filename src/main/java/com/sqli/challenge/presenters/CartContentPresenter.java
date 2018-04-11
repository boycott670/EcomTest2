package com.sqli.challenge.presenters;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

import com.sqli.challenge.entities.Product;

public interface CartContentPresenter
{
  String presentCartContent (
      final Map<? extends String, ? extends Collection<? extends Product>> products,
      final Comparator<? super Product> productComparator);
}
