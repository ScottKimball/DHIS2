package org.motechproject.dhis2.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OptionDto extends BasicLinkedEntity {
    @JsonProperty("publicAccess")
    private String publicAccess;
    @JsonProperty("access")
    private AccessDto access;
    @JsonProperty("externalAccess")
    private boolean externalAccess;
    @JsonProperty("userGroupAccesses")
    private List<UserGroupAccessDto> userGroupAccesses;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("user")
    private UserDto user;

    public String getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(String publicAccess) {
        this.publicAccess = publicAccess;
    }

    public AccessDto getAccess() {
        return access;
    }

    public void setAccess(AccessDto access) {
        this.access = access;
    }

    public boolean isExternalAccess() {
        return externalAccess;
    }

    public void setExternalAccess(boolean externalAccess) {
        this.externalAccess = externalAccess;
    }

    public List<UserGroupAccessDto> getUserGroupAccesses() {
        return userGroupAccesses;
    }

    public void setUserGroupAccesses(List<UserGroupAccessDto> userGroupAccesses) {
        this.userGroupAccesses = userGroupAccesses;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
