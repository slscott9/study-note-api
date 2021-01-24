package com.stuart.studynoteapi;

import com.stuart.studynoteapi.repository.FolderRepository;
import com.stuart.studynoteapi.repository.SetRepository;
import com.stuart.studynoteapi.repository.TermRepository;
import com.stuart.studynoteapi.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, FolderRepository.class, SetRepository.class, TermRepository.class})
public class StudyNoteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyNoteApiApplication.class, args);
	}

}
