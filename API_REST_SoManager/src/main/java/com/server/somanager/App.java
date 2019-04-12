package com.server.somanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.server.somanager.App;

@SpringBootApplication
public class App {


	//Si impossible de charger la classe : 
	// Maven/UpdateProject puis Project/clean
	//Run as configurations 
	
    public static void main(String[] args) {
    	try {
        	SpringApplication.run(App.class, args);
        	System.out.println("Application démarrée");
        	
        } catch (Exception e) {
        	System.out.println("Erreur application !");
        }
    }
}