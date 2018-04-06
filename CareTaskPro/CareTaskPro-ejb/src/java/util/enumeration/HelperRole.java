/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
