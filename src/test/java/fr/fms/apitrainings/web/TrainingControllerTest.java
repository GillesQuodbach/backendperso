//package fr.fms.apitrainings.web;
//
//import fr.fms.apitrainings.business.IBusinessImpl;
//import fr.fms.apitrainings.dao.CategoryRepository;
//import fr.fms.apitrainings.dao.TrainingRepository;
//import fr.fms.apitrainings.entities.Category;
//import fr.fms.apitrainings.entities.Training;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TrainingControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private IBusinessImpl iBusiness;
//
//    @MockBean
//    private TrainingRepository trainingRepository;
//
//    @MockBean
//    private CategoryRepository categoryRepository;
//
//    @BeforeEach
//    public void setup() {
//
//        Training training1 = new Training(1L, "Training 1", "Description 1", 100, 1, "imgName", null);
//        Training training2 = new Training(2L, "Training 2", "Description 2", 200, 1, "imgName", null);
//
//        List<Training> trainings = Arrays.asList(training1, training2);
//
//        Category category1 = new Category(1L, "Category 1", trainings);
//        Category category2 = new Category(2L, "Category 2", trainings);
//
//        List<Category> categories = Arrays.asList(category1, category2);
//
//        when(iBusiness.getTrainings()).thenReturn(trainings);
//        when(iBusiness.getTrainingById(1L)).thenReturn(Optional.of(training1));
//        when(iBusiness.getCategories()).thenReturn(categories);
//    }
//
//    @Test
//    public void testGetTrainings() throws Exception {
//        mockMvc.perform(get("/trainings")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].name").value("Training 1"))
//                .andExpect(jsonPath("$[0].description").value("Description 1"))
//                .andExpect(jsonPath("$[1].id").value(2L))
//                .andExpect(jsonPath("$[1].name").value("Training 2"))
//                .andExpect(jsonPath("$[1].description").value("Description 2"));
//    }
//
//    @Test
//    public void testGetTrainingById() throws Exception {
//        mockMvc.perform(get("/training/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.name").value("Training 1"))
//                .andExpect(jsonPath("$.description").value("Description 1"))
//                .andExpect(jsonPath("$.img").value("imgName"))
//                .andExpect(jsonPath("$.price").value(100));
//    }
//
//    @Test
//    public void testGetCategories() throws Exception {
//        mockMvc.perform(get("/categories")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].name").value("Category 1"))
//                .andExpect(jsonPath("$[1].id").value(2L))
//                .andExpect(jsonPath("$[1].name").value("Category 2"));
//    }
//}
