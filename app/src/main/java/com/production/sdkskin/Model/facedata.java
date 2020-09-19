package com.production.sdkskin.Model;

import java.io.Serializable;

public class facedata  implements Serializable {
    generalResult generalResult;
    specialResult specialResult;
    imageinfo imageinfo;

    public com.production.sdkskin.Model.imageinfo getImageinfo() {
        return imageinfo;
    }

    public void setImageinfo(com.production.sdkskin.Model.imageinfo imageinfo) {
        this.imageinfo = imageinfo;
    }

    public facedata(com.production.sdkskin.Model.generalResult generalResult, com.production.sdkskin.Model.specialResult specialResult, com.production.sdkskin.Model.imageinfo imageinfo) {
        this.generalResult = generalResult;
        this.specialResult = specialResult;
        this.imageinfo = imageinfo;
    }
    public facedata( ) {
    }
    public com.production.sdkskin.Model.generalResult getGeneralResult() {
        return generalResult;
    }

    public void setGeneralResult(com.production.sdkskin.Model.generalResult generalResult) {
        this.generalResult = generalResult;
    }

    public com.production.sdkskin.Model.specialResult getSpecialResult() {
        return specialResult;
    }

    public void setSpecialResult(com.production.sdkskin.Model.specialResult specialResult) {
        this.specialResult = specialResult;
    }
}
