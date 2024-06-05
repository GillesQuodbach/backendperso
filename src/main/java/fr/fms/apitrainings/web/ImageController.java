package fr.fms.apitrainings.web;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.services.image.ImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controller class for handling image-related requests.
 */
@CrossOrigin("*")
@RestController
public class ImageController {

    @Autowired
    private ImageImpl iImageImpl;

    @Autowired
    private IBusinessImpl iBusiness;

    /**
     * Endpoint for downloading an image.
     *
     * @param idTraining the ID of the training associated with the image.
     * @return ResponseEntity containing the image resource.
     * @throws Exception if an error occurs while processing the request.
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadImg(@PathVariable("id") Long idTraining) throws Exception {
        Optional<Training> optionalTraining = iBusiness.getTrainingById(idTraining);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            String imgName = training.getImg();
            Resource resource = iImageImpl.loadImageAsResource(imgName);
            String contentType = iImageImpl.getContentType(imgName);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Endpoint for uploading an image.
     *
     * @param file the image file to upload.
     * @return ResponseEntity containing the upload status.
     */
    @PostMapping("/download")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("message", "Le fichier est vide.");
            return ResponseEntity.badRequest().body(response);
        }

        String fileName = file.getOriginalFilename();
        try {
            iImageImpl.uploadImage(file);
        } catch (IOException e) {
            response.put("message", "Erreur lors du téléchargement du fichier " + fileName + ".");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {
            response.put("message", "Une erreur inattendue est survenue lors du téléchargement du fichier " + fileName + ".");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("message", "Le fichier " + fileName + " a été téléchargé avec succès.");
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint for uploading and assigning an image to a training.
     *
     * @param idTraining the ID of the training to associate the image with.
     * @param file       the image file to upload.
     * @return ResponseEntity containing the upload status.
     * @throws IOException if an I/O error occurs while processing the request.
     */
    @PostMapping("/download/{id}")
    public ResponseEntity<Map<String, String>> uploadImage(@PathVariable("id") Long idTraining, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("message", "Le fichier est vide.");
            return ResponseEntity.badRequest().body(response);
        }
        String fileName = file.getOriginalFilename();

        try {
            iImageImpl.uploadAndAssignImageToTraining(idTraining, file);
        } catch (IOException e) {
            response.put("message", "Erreur lors du téléchargement du fichier " + fileName + ".");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.put("message", "Le fichier " + fileName + " a été téléchargé avec succès et à bien été affilier à la training :" + idTraining);
        return ResponseEntity.ok(response);
    }

}
