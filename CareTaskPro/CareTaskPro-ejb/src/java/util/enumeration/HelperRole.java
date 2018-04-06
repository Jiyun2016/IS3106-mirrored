package util.enumeration;

/**
 *
 * @author panjiyun
 */
public enum HelperRole {
    PROFESSIONAL(20), NONPROFESSIONAL(15);

    private int chargeRate;
    
    private HelperRole(int chargeRate) {
    this.chargeRate = chargeRate;
  }

    public int getChargeRate()
    {
       return chargeRate;
    }
    
    
}
