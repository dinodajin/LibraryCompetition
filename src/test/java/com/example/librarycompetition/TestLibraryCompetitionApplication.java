package com.example.librarycompetition;

import org.springframework.boot.SpringApplication;

public class TestLibraryCompetitionApplication {

    public static void main(String[] args) {
        SpringApplication.from(LibraryCompetitionApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
