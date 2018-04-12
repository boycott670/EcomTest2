package com.sqli.challenge.validators.voucher.reqs;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map.Entry;

import com.sqli.challenge.items.Machine;
import com.sqli.challenge.items.Product;

public final class VoucherCodeRequiresOnlyMachinesValidator implements VoucherCodeRequirementsValidator
{

  @Override
  public Collection<? extends String> validateRequirements(String voucherCode,
      Collection<? extends Entry<? extends Product, ? extends Integer>> cartContent)
  {
    if ("12345".equals(voucherCode))
    {
      final boolean containsOnlyMachines = cartContent.stream().map(Entry::getKey).map(Object::getClass)
          .allMatch(Machine.class::isAssignableFrom);

      if (!containsOnlyMachines)
      {
        return Arrays.asList("Voucher requires machine purchase");
      }
    }

    return Arrays.asList();
  }

}
