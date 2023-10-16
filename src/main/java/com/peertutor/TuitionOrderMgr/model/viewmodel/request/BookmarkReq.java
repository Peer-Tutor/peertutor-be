package com.peertutor.TuitionOrderMgr.model.viewmodel.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookmarkReq {
    public String name;

    public Long id;

    @NotNull
    public Long tutorID;

    @NotNull
    public Long studentID;
}