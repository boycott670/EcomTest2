package com.sqli.challenge;

import com.sqli.challenge.items.Cart;
import com.sqli.challenge.items.Machine;
import com.sqli.challenge.presenters.CartContentPresenter;
import com.sqli.challenge.presenters.DefaultCartContentPresenter;

public final class EcommerceFacade
{
  private final CartContentPresenter cartContentPresenter;

  private final Cart cart;

  public EcommerceFacade()
  {
    cart = new Cart();
    cartContentPresenter = new DefaultCartContentPresenter();
  }

  public void addMachine(final String name, final int quantity, final double price)
  {
    cart.addProduct(new Machine(name, price), quantity);
  }

  public String cartContent()
  {
    return cartContentPresenter.present(cart.getContent());
  }
}
