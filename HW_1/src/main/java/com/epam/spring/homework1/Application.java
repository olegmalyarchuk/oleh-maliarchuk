package com.epam.spring.homework1;

import com.epam.spring.homework1.config.BeansConfig;
import com.epam.spring.homework1.pet.Cheetah;
import com.epam.spring.homework1.pet.Pet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
		context.getBean(Pet.class).printPets();

		// getting bean by type
		Cheetah cheetah1 = context.getBean("cheetah", Cheetah.class); // by @Primary there are 2 possible choices, so the exception would occur)

		// getting bean by name
		Cheetah cheetah2 = context.getBean("secondCheetah", Cheetah.class); // without problems mecause the name of the method in config-class

	}

}
