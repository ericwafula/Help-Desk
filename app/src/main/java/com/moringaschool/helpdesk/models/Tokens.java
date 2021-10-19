
package com.moringaschool.helpdesk.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tokens {

    @SerializedName("access")
    @Expose
    private String access;
    @SerializedName("refresh")
    @Expose
    private String refresh;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tokens() {
    }

    /**
     * 
     * @param access
     * @param refresh
     */
    public Tokens(String access, String refresh) {
        super();
        this.access = access;
        this.refresh = refresh;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.refresh == null)? 0 :this.refresh.hashCode()));
        result = ((result* 31)+((this.access == null)? 0 :this.access.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Tokens) == false) {
            return false;
        }
        Tokens rhs = ((Tokens) other);
        return (((this.refresh == rhs.refresh)||((this.refresh!= null)&&this.refresh.equals(rhs.refresh)))&&((this.access == rhs.access)||((this.access!= null)&&this.access.equals(rhs.access))));
    }

}
