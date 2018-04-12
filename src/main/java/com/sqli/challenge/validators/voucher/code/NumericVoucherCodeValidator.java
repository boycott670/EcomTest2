package com.sqli.challenge.validators.voucher.code;

import java.util.Arrays;
import java.util.Collection;

public final class NumericVoucherCodeValidator implements VoucherCodeValidator
{

  @Override
  public Collection<? extends String> validateVoucherCode(String voucherCode)
  {
    return !voucherCode.matches("^\\d+$") ? Arrays.asList("Invalid voucher code") : Arrays.asList();
  }

}
