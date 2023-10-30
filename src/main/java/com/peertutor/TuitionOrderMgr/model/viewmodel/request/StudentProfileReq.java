package com.peertutor.TuitionOrderMgr.model.viewmodel.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentProfileReq {
    
    public String name;

    @Pattern(regexp = "^[a-zA-Z0-9_ ]{1,20}$", message = "Only alphanumeric characters and underscore are allowed")
    public String displayName;
    
    @Pattern(regexp = "^[a-zA-Z0-9_ ]{0,255}$", message = "Only alphanumeric characters and underscore are allowed")
    public String introduction;

    public String subjects;
}
