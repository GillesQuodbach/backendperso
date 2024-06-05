package fr.fms.apitrainings.services.image;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service interface for handling images.
 */
public interface IImage {

    /**
     * Loads an image as a Resource.
     *
     * @param imgName the name of the image.
     * @return the loaded image as a Resource.
     * @throws Exception if an error occurs while loading the image.
     */
    Resource loadImageAsResource(String imgName) throws Exception;

    /**
     * Retrieves the content type of an image.
     *
     * @param imgName the name of the image.
     * @return the content type of the image.
     * @throws Exception if an error occurs while retrieving the content type.
     */
    String getContentType(String imgName) throws Exception;

    /**
     * Uploads and assigns an image to a training.
     *
     * @param idTraining the ID of the training.
     * @param file       the image file to upload.
     * @return the filename of the uploaded image.
     * @throws IOException if an I/O error occurs while uploading the image.
     */
    String uploadAndAssignImageToTraining(Long idTraining, MultipartFile file) throws IOException;

    /**
     * Uploads an image.
     *
     * @param file the image file to upload.
     * @throws IOException if an I/O error occurs while uploading the image.
     */
    void uploadImage(MultipartFile file) throws IOException;
}
