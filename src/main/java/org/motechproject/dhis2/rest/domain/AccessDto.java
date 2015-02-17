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
        "update",
        "externalize",
        "write",
        "delete",
        "read",
        "manage"
})
public class AccessDto {

    @JsonProperty("update")
    private Boolean update;
    @JsonProperty("externalize")
    private Boolean externalize;
    @JsonProperty("write")
    private Boolean write;
    @JsonProperty("delete")
    private Boolean delete;
    @JsonProperty("read")
    private Boolean read;
    @JsonProperty("manage")
    private Boolean manage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The update
     */
    @JsonProperty("update")
    public Boolean getUpdate() {
        return update;
    }

    /**
     *
     * @param update
     *     The update
     */
    @JsonProperty("update")
    public void setUpdate(Boolean update) {
        this.update = update;
    }

    /**
     *
     * @return
     *     The externalize
     */
    @JsonProperty("externalize")
    public Boolean getExternalize() {
        return externalize;
    }

    /**
     *
     * @param externalize
     *     The externalize
     */
    @JsonProperty("externalize")
    public void setExternalize(Boolean externalize) {
        this.externalize = externalize;
    }

    /**
     *
     * @return
     *     The write
     */
    @JsonProperty("write")
    public Boolean getWrite() {
        return write;
    }

    /**
     *
     * @param write
     *     The write
     */
    @JsonProperty("write")
    public void setWrite(Boolean write) {
        this.write = write;
    }

    /**
     *
     * @return
     *     The delete
     */
    @JsonProperty("delete")
    public Boolean getDelete() {
        return delete;
    }

    /**
     *
     * @param delete
     *     The delete
     */
    @JsonProperty("delete")
    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    /**
     *
     * @return
     *     The read
     */
    @JsonProperty("read")
    public Boolean getRead() {
        return read;
    }

    /**
     *
     * @param read
     *     The read
     */
    @JsonProperty("read")
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     *
     * @return
     *     The manage
     */
    @JsonProperty("manage")
    public Boolean getManage() {
        return manage;
    }

    /**
     *
     * @param manage
     *     The manage
     */
    @JsonProperty("manage")
    public void setManage(Boolean manage) {
        this.manage = manage;
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
        return new HashCodeBuilder().append(update).append(externalize).append(write).append(delete).append(read).append(manage).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AccessDto) == false) {
            return false;
        }
        AccessDto rhs = ((AccessDto) other);
        return new EqualsBuilder().append(update, rhs.update).append(externalize, rhs.externalize).append(write, rhs.write).append(delete, rhs.delete).append(read, rhs.read).append(manage, rhs.manage).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
