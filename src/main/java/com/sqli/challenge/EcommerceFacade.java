package com.sqli.challenge;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sqli.challenge.items.Capsule;
import com.sqli.challenge.items.Cart;
import com.sqli.challenge.items.Machine;
import com.sqli.challenge.items.Voucher;
import com.sqli.challenge.presenters.CartContentPresenter;
import com.sqli.challenge.presenters.CartSummaryPresenter;
import com.sqli.challenge.presenters.DefaultCartContentPresenter;
import com.sqli.challenge.presenters.DefaultCartSummaryPresenter;
import com.sqli.challenge.validators.cart.content.CapsulesPackagingRulesValidator;
import com.sqli.challenge.validators.cart.content.CartContentValidator;
import com.sqli.challenge.validators.cart.content.EmptyCartContentValidator;
import com.sqli.challenge.validators.voucher.code.NumericVoucherCodeValidator;
import com.sqli.challenge.validators.voucher.code.VoucherCodeValidator;

public final class EcommerceFacade
{
  private final CartContentPresenter cartContentPresenter;
  private final CartSummaryPresenter cartSummaryPresenter;

  private final Collection<? extends CartContentValidator> cartContentValidators;
  private final Collection<? extends VoucherCodeValidator> voucherCodeValidators;

  private Collection<? extends String> validationErrors;

  private final Cart cart;

  private Voucher voucher;

  public EcommerceFacade()
  {
    cart = new Cart();

    cartContentPresenter = new DefaultCartContentPresenter();
    cartSummaryPresenter = new DefaultCartSummaryPresenter();

    cartContentValidators = Arrays.asList(new EmptyCartContentValidator(), new CapsulesPackagingRulesValidator());
    voucherCodeValidators = Arrays.asList(new NumericVoucherCodeValidator());
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
    validationErrors = Stream.concat(
        Stream.concat(Stream.empty(),
            cartContentValidators.stream()
                .map(cartContentValidator -> cartContentValidator.validateCartContent(cart.getContent()))
                .flatMap(Collection::stream)),
        Optional.ofNullable(voucher).map(Voucher::getCode)
            .map(voucherCode -> voucherCodeValidators.stream()
                .map(voucherCodeValidator -> voucherCodeValidator.validateVoucherCode(voucherCode))
                .flatMap(Collection::stream))
            .orElse(Stream.empty()))
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

  public void voucher(final String voucherCode)
  {
    voucher = new Voucher(voucherCode);
  }
}
