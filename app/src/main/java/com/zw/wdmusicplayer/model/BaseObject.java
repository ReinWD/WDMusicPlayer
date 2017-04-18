package com.zw.wdmusicplayer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ZW on 2017/4/14.
 */

abstract class BaseObject {
    @SerializedName("show_res_code")
    private int showapiResCode;
    @SerializedName("showapi_res_error")
    private String showapiResError;
    @SerializedName("showapi_res_body")
    private String showapiResBody;

    public int getShowapiResCode() {
        return showapiResCode;
    }
    public String getShowapiResError() {
        return showapiResError;
    }
    public String getShowapiResBody() {
        return showapiResBody;
    }
    public void setShowapiResCode(int showapiResCode) {
        this.showapiResCode = showapiResCode;
    }
    public void setShowapiResError(String showapiResError) {
        this.showapiResError = showapiResError;
    }
    public void setShowapiResBody(String showapiResBody) {
        this.showapiResBody = showapiResBody;
    }
}
