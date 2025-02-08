/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author DANH NGUYEN
 */
public interface Acceptable {
    public final String CUS_ID_VALD = "^[CcGgKk]\\d{4}$";
    public final String NAME_VALID = "^.{2,25}$";
    public final String PHONE_VALID = "^0\\d{9}$";
    public final String VIETTEL_VALID = "^(098|097|096|086|039|038|037|036|035|034|033|032)";
    public final String VNPT_VALID = "^(081|082|083|084|085|088|091|094)\\d{7}$";
    public final String EMAIL_VALID = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
