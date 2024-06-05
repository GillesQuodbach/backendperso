package fr.fms.apitrainings.services.image;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.entities.Training;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Service implementation for handling image-related operations.
 */
@Service
public class ImageImpl implements IImage {

    @Autowired
    private IBusinessImpl iBusiness;

    @Autowired
    private Environment env;

    private String BASE_PATH;

    /**
     * Initializes the base path for storing images.
     */
    @PostConstruct
    public void init() {
        String userHome = env.getProperty("app.home");
        BASE_PATH = userHome + File.separator + "Pictures" + File.separator + "trainings" + File.separator + "images";
    }

    /**
     * Loads an image as a Resource.
     *
     * @param imgName the name of the image.
     * @return the loaded image as a Resource.
     * @throws Exception if an error occurs while loading the image.
     */
    @Override
    public Resource loadImageAsResource(String imgName) throws Exception {
        Path imagePath = Paths.get(BASE_PATH).resolve(imgName);
        Resource resource = new UrlResource(imagePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new Exception("La lecture du fichier est impossible");
        }
    }

    /**
     * Retrieves the content type of an image.
     *
     * @param imgName the name of the image.
     * @return the content type of the image.
     * @throws Exception if an error occurs while retrieving the content type.
     */
    @Override
    public String getContentType(String imgName) throws Exception {
        Path path = Paths.get(BASE_PATH).resolve(imgName);
        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

    /**
     * Uploads an image.
     *
     * @param file the image file to upload.
     * @throws IOException if an I/O error occurs while uploading the image.
     */
    @Override
    public void uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Aucun fichier n'a été sélectionné.");
        }
        String filename = file.getOriginalFilename();
        file.transferTo(new File(BASE_PATH + File.separator + filename));
    }

    /**
     * Uploads and assigns an image to a training.
     *
     * @param idTraining the ID of the training.
     * @param file       the image file to upload.
     * @return the filename of the uploaded image.
     * @throws IOException if an I/O error occurs while uploading the image.
     */
    @Override
    public String uploadAndAssignImageToTraining(Long idTraining, MultipartFile file) throws IOException {
        Optional<Training> optionalTraining = iBusiness.getTrainingById(idTraining);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();

            if (file.isEmpty()) {
                throw new IllegalArgumentException("Aucun fichier n'a été sélectionné.");
            }
            String filename = file.getOriginalFilename();
            file.transferTo(new File(BASE_PATH + File.separator + filename));
            training.setImg(filename);
            iBusiness.saveTraining(training);
            return filename;
        } else {
            throw new IllegalArgumentException("La formation avec l'identifiant " + idTraining + " n'existe pas.");
        }
    }
}
