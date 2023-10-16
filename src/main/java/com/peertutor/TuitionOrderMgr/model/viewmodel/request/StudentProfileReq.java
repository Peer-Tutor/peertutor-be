package com.peertutor.TuitionOrderMgr.model.viewmodel.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StudentProfileReq {
    public String name;

    public String displayName;

    public String introduction;

    public String subjects;
}
