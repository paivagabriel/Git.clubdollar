package com.paivadeveloper.clubdollar.util;

/**
 * Created by Thalles on 21/09/2017.
 * essa classe e os metodos peguei na internet e traduzi algumas coisas
 * https://androidjson.com/upload-image-to-firebase-storage/
 *
 */
public class ImageUploadInfo {

    public String imageName;

    public String imageURL;

    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String name, String url) {

        this.imageName = name;
        this.imageURL= url;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

}