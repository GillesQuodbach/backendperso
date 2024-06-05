package fr.fms.apitrainings;

import fr.fms.apitrainings.dao.*;
import fr.fms.apitrainings.entities.*;
import fr.fms.apitrainings.security.entities.AppRole;
import fr.fms.apitrainings.security.entities.AppUser;
import fr.fms.apitrainings.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the API Trainings application.
 */
@SpringBootApplication
public class ApiTrainingsApplication implements CommandLineRunner {
	@Value("${app.home}")
	private String userHome;

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder getbCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	/**
	 * The entry point of the application.
	 *
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiTrainingsApplication.class, args);
	}

	/**
	 * Run method implemented from CommandLineRunner interface.
	 *
	 * @param args the command line arguments.
	 * @throws Exception if an error occurs.
	 */
	@Override
	public void run(String... args) throws Exception {
		generatedData();
		generateUsersRoles();
	}

	/**
	 * Method to generate sample data.
	 */
	public void generatedData() {

		List<Training> trainingsList = new ArrayList<>();

		Category informatique = new Category("Informatique", trainingsList);
		Category cuisine = new Category("Cuisine", trainingsList);
		Category anglais = new Category("Langues étrangères", trainingsList);
		Category finance = new Category("Finance et comptabilité", trainingsList);

		categoryRepository.save(informatique);
		categoryRepository.save(cuisine);
		categoryRepository.save(anglais);
		categoryRepository.save(finance);



		trainingRepository.save(new Training(null, "Java", "Formation Java", 150, 1, "java.jpg", informatique));
		trainingRepository.save(new Training(null, "C", "Formation C", 100, 1, "c.jpg", informatique));
		trainingRepository.save(new Training(null, "Javascript", "Formation Javascript", 120, 1, "JS.jpg", informatique));
		trainingRepository.save(new Training(null, "Python", "Formation Python", 300, 1, "python.jpg", informatique));
		trainingRepository.save(new Training(null, "NodeJS", "Formation NodeJS", 175, 1, "NodeJS.jpg", informatique));

		trainingRepository.save(new Training(null, "Bases", "Les bases de la cuisine", 90, 1, "cuisineBases.jpg", cuisine));
		trainingRepository.save(new Training(null, "Cuisine du monde", "Les cuisines du monde", 300, 1, "world-cook.jpg", cuisine));
		trainingRepository.save(new Training(null, "Pâtisserie", "Les bases de la pâtisserie", 310, 1, "cookPatisserie.jpg", cuisine));
		trainingRepository.save(new Training(null, "Vegan", "Apprendre la cuisine vegan", 45, 1, "cookVegan.jpg", cuisine));
		trainingRepository.save(new Training(null, "Santé", " Élaboration de plats équilibrés", 155, 1, "default.jpg", cuisine));

		trainingRepository.save(new Training(null, "Anglais", "Formation à l'anglais", 150, 1, "british-flag.jpg", anglais));
		trainingRepository.save(new Training(null, "Espagnol", "Formation à l'espagnol", 100, 1, "spain-flag.jpg", anglais));
		trainingRepository.save(new Training(null, "Français", "Formation au français", 120, 1, "french-flag.jpg", anglais));
		trainingRepository.save(new Training(null, "Allemand", "Formation à l'allemand", 300, 1, "german-flag.jpg", anglais));
		trainingRepository.save(new Training(null, "Mandarin", "Formation au mandarin", 175, 1, "china-flag.jpg", anglais));

		trainingRepository.save(new Training(null, "Comptabilité", "Les bases de la comptabilité", 150, 1, "compta-bases.jpg", finance));
		trainingRepository.save(new Training(null, "Analyse", "Formation aux analyses financières", 100, 1, "fin-analyst.jpg", finance));
		trainingRepository.save(new Training(null, "Gestion", "Formation au gestion budgétaire", 120, 1, "fin-gestion.jpg", finance));
		trainingRepository.save(new Training(null, "Fiscalité", "Fiscalité des entreprises", 300, 1, "fin-impots.jpg", finance));
		trainingRepository.save(new Training(null, "Investissement", "Investissement et gestion de portefeuille", 175, 1, "default.jpg", finance));

	}

	private void generateUsersRoles(){
		accountService.saveUser(new AppUser(null,"gilles", "1234", new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"anonymous", "1234", new ArrayList<>()));

		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));

		accountService.addRoleToUser("gilles", "ADMIN");
		accountService.addRoleToUser("gilles", "USER");
		accountService.addRoleToUser("anonymous", "USER");
	}

}
