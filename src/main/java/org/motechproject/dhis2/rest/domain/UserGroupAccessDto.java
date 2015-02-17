package org.motechproject.dhis2.rest.domain;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "access",
        "userGroup"
})
public class UserGroupAccessDto {

    @JsonProperty("access")
    private String access;
    @JsonProperty("userGroup")
    private UserGroupDto userGroupDto;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The access
     */
    @JsonProperty("access")
    public String getAccess() {
        return access;
    }

    /**
     *
     * @param access
     *     The access
     */
    @JsonProperty("access")
    public void setAccess(String access) {
        this.access = access;
    }

    /**
     *
     * @return
     *     The userGroup
     */
    @JsonProperty("userGroup")
    public UserGroupDto getUserGroupDto() {
        return userGroupDto;
    }

    /**
     *
     * @param userGroupDto
     *     The userGroup
     */
    @JsonProperty("userGroup")
    public void setUserGroupDto(UserGroupDto userGroupDto) {
        this.userGroupDto = userGroupDto;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(access).append(userGroupDto).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserGroupAccessDto) == false) {
            return false;
        }
        UserGroupAccessDto rhs = ((UserGroupAccessDto) other);
        return new EqualsBuilder().append(access, rhs.access).append(userGroupDto, rhs.userGroupDto).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
