package com.production.mytest.Model;

import java.io.Serializable;

public class facedata  implements Serializable {
    generalResult generalResult;
    specialResult specialResult;
    imageinfo imageinfo;

    public  imageinfo getImageinfo() {
        return imageinfo;
    }

    public void setImageinfo( imageinfo imageinfo) {
        this.imageinfo = imageinfo;
    }

    public facedata( generalResult generalResult,  specialResult specialResult, imageinfo imageinfo) {
        this.generalResult = generalResult;
        this.specialResult = specialResult;
        this.imageinfo = imageinfo;
    }
    public facedata( ) {
    }
    public generalResult getGeneralResult() {
        return generalResult;
    }

    public void setGeneralResult( generalResult generalResult) {
        this.generalResult = generalResult;
    }

    public  specialResult getSpecialResult() {
        return specialResult;
    }

    public void setSpecialResult( specialResult specialResult) {
        this.specialResult = specialResult;
    }
}
