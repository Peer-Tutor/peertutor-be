package com.peertutor.TuitionOrderMgr.model.viewmodel.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentProfileReq {
    public String name;

    public String displayName;

    public String introduction;

    public String subjects;
}
