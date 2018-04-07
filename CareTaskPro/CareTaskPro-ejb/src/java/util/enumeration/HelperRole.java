package util.enumeration;

import java.math.BigDecimal;

/**
 *
 * @author panjiyun
 */
public enum HelperRole {
    PROFESSIONAL(new BigDecimal(20.00)), NONPROFESSIONAL(new BigDecimal(15.00));

    private BigDecimal chargeRate;
    
    private HelperRole(BigDecimal  chargeRate) {
    this.chargeRate = chargeRate;
  }

    public BigDecimal  getChargeRate()
    {
       return chargeRate;
    }
    
    
}
