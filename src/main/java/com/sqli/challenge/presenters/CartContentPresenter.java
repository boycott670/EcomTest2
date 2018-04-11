package com.sqli.challenge.presenters;

import java.util.Collection;
import java.util.Map.Entry;

import com.sqli.challenge.items.Product;

public interface CartContentPresenter
{
  String present(final Collection<? extends Entry<? extends Product, ? extends Integer>> cartContent);
}
