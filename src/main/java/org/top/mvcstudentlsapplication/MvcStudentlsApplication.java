package org.top.mvcstudentlsapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.top.mvcstudentlsapplication.db.entity.Subject;
import org.top.mvcstudentlsapplication.db.repository.SubjectRepo;

@SpringBootApplication
@RestController
public class MvcStudentlsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcStudentlsApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(SubjectRepo repo) {
        return args -> {
            Subject subjectMath = new Subject();
            subjectMath.setSubjectName("Математика");
            repo.save(subjectMath);

            Subject subjectRus = new Subject();
            subjectRus.setSubjectName("Русский");
            repo.save(subjectRus);
        };
    }
}
