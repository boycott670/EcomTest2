package com.sqli.challenge.validators.voucher.reqs;

import java.util.Collection;
import java.util.Map.Entry;

import com.sqli.challenge.items.Product;

public interface VoucherCodeRequirementsValidator
{
  Collection<? extends String> validateRequirements(final String voucherCode,
      final Collection<? extends Entry<? extends Product, ? extends Integer>> cartContent);
}
