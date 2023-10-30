package com.peertutor.TuitionOrderMgr.model.viewmodel.request;

import javax.validation.constraints.Pattern;

public class TutorProfileReq {

    public String name;

    @Pattern(regexp = "^[a-zA-Z0-9_ ]{1,20}$", message = "Only alphanumeric characters and underscore are allowed")
    public String displayName;

    public String accountName;

    @Pattern(regexp = "^[a-zA-Z0-9_ ]{0,255}$", message = "Only alphanumeric characters and underscore are allowed")
    public String introduction;

    public String subjects;

    public String certificates;
}
