package com.sqli.challenge.validators.voucher.code;

import java.util.Collection;

public interface VoucherCodeValidator
{
  Collection<? extends String> validateVoucherCode(final String voucherCode);
}
