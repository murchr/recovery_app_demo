package persistence;

import org.json.JSONObject;

public class Addresses implements Writable {
    private String preferencesAddress;
    private String recoveryAddress;

    public Addresses(String recoveryAddress, String preferencesAddress) {
        this.preferencesAddress = preferencesAddress;
        this.recoveryAddress = recoveryAddress;
    }

    public String getRecoveryAddress() {
        return recoveryAddress;
    }

    public String getPreferencesAddress() {
        return preferencesAddress;
    }

    public void setRecoveryAddress(String recoveryAddress) {
        this.recoveryAddress = recoveryAddress;
    }

    public void setPreferencesAddress(String preferencesAddress) {
        this.preferencesAddress = preferencesAddress;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lastPreferences", preferencesAddress);
        jsonObject.put("lastAddress", recoveryAddress);
        return jsonObject;
    }
}
