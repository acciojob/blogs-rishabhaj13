package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(description,dimensions,blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] scrarr = screenDimensions.split("X");

        Image image = imageRepository2.findById(id).get();

        String imageDimensions = image.getDimensions();
        String[] imgarr = imageDimensions.split("X");

        int scrl = Integer.parseInt(scrarr[0]);
        int scrb = Integer.parseInt(scrarr[1]);

        int imgl = Integer.parseInt(imgarr[0]);
        int imgb = Integer.parseInt(imgarr[1]);

        return ((scrl/imgl)*(scrb/imgb));
    }
}
