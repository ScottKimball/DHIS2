package org.motechproject.dhis2.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;

import java.util.List;

/**
 * Created by scott on 8/26/14.
 */





@Entity
public class IndividualRecord {

    @Field
    private String firstName;

    @Field
    private String lastName;

    @Field
    private String gender;

    @Field
    private String location;



    public IndividualRecord(String firstName, String lastName, String gender, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.location = location;

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
