package com.sqli.challenge;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sqli.challenge.items.Capsule;
import com.sqli.challenge.items.Cart;
import com.sqli.challenge.items.Machine;
import com.sqli.challenge.presenters.CartContentPresenter;
import com.sqli.challenge.presenters.CartSummaryPresenter;
import com.sqli.challenge.presenters.DefaultCartContentPresenter;
import com.sqli.challenge.presenters.DefaultCartSummaryPresenter;
import com.sqli.challenge.validators.cart.content.CapsulesPackagingRulesValidator;
import com.sqli.challenge.validators.cart.content.CartContentValidator;
import com.sqli.challenge.validators.cart.content.EmptyCartContentValidator;

public final class EcommerceFacade
{
  private final CartContentPresenter cartContentPresenter;
  private final CartSummaryPresenter cartSummaryPresenter;

  private final Collection<? extends CartContentValidator> cartContentValidators;

  private Collection<? extends String> validationErrors;

  private final Cart cart;

  public EcommerceFacade()
  {
    cart = new Cart();

    cartContentPresenter = new DefaultCartContentPresenter();
    cartSummaryPresenter = new DefaultCartSummaryPresenter();

    cartContentValidators = Arrays.asList(new EmptyCartContentValidator(), new CapsulesPackagingRulesValidator());
  }

  public void addMachine(final String name, final int quantity, final double price)
  {
    cart.addProduct(new Machine(name, price), quantity);
  }

  public void removeMachine(final String name, final int quantity)
  {
    cart.addProduct(new Machine(name), -quantity);
  }

  public void addCapsule(final String name, final int quantity, final double price)
  {
    cart.addProduct(new Capsule(name, price), quantity);
  }

  public void removeCapsule(final String name, final int quantity)
  {
    cart.addProduct(new Capsule(name), -quantity);
  }

  public String cartContent()
  {
    return cartContentPresenter.present(cart.getContent());
  }

  public String summary()
  {
    return cartSummaryPresenter.present(cart.getContent());
  }

  public EcommerceFacade order()
  {
    validationErrors = Stream.concat(Stream.empty(),
        cartContentValidators.stream()
            .map(cartContentValidator -> cartContentValidator.validateCartContent(cart.getContent()))
            .flatMap(Collection::stream))
        .collect(Collectors.toList());

    return this;
  }

  public boolean hasErrors()
  {
    return !validationErrors.isEmpty();
  }

  public String errors()
  {
    return String.join("\n", validationErrors);
  }
}
