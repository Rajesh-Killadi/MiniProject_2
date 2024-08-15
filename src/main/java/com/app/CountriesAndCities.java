package com.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.entites.Cities;
import com.app.entites.Countries;
import com.app.entites.States;
import com.app.entites.UsersMaster;
import com.app.repositories.CitiesRepository;
import com.app.repositories.CountryRepository;
import com.app.repositories.StatesRepository;
import com.app.repositories.UsersRepository;
@Component
public class CountriesAndCities implements ApplicationRunner {
	
	@Autowired
	CountryRepository countryRepo;
	@Autowired
	StatesRepository stateRepo;
	@Autowired
	CitiesRepository cityRepo;
	@Autowired
	UsersRepository userRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		// TODO Auto-generated method stub
		
		Countries c1 = new Countries(1,"India");
		Countries c2 = new Countries(2,"USA");
		Countries c3 = new Countries(3,"UK");
		Countries c4 = new Countries(4,"Australia");
		
		States s1 = new States(1,"AP",c1);
		States s2 = new States(2,"Telangana",c1);
		
		States s3 = new States(3, "California", c2);
		States s4 = new States(4, "Texas", c2);
		States s5 = new States(5, "New York", c2);
		
		States s6 = new States(6, "England", c3);
		States s7 = new States(7, "Scotland", c3);
		States s8 = new States(8, "Wales", c3);

		States s9 = new States(9, "New South Wales", c4);
		States s10 = new States(10, "Victoria", c4);
		States s11 = new States(11, "Queensland", c4);


		
		Cities cc1 = new Cities(1,"Rajahmundry",s1);
		Cities cc2 = new Cities(2,"Kakinada",s1);
		
		Cities cc3 = new Cities(3, "Los Angeles", s3);    // California
		Cities cc4 = new Cities(4, "San Francisco", s3);  // California

		Cities cc5 = new Cities(5, "Houston", s4);        // Texas
		Cities cc6 = new Cities(6, "Dallas", s4);         // Texas

		Cities cc7 = new Cities(7, "New York City", s5);  // New York
		Cities cc8 = new Cities(8, "Buffalo", s5);       // New York
		
		Cities cc9 = new Cities(9, "London", s6);         // England
		Cities cc10 = new Cities(10, "Manchester", s6);   // England

		Cities cc11 = new Cities(11, "Edinburgh", s7);    // Scotland
		Cities cc12 = new Cities(12, "Glasgow", s7);     // Scotland

		Cities cc13 = new Cities(13, "Cardiff", s8);     // Wales
		Cities cc14 = new Cities(14, "Swansea", s8);     // Wales
		
		Cities cc15 = new Cities(15, "Sydney", s9);      // New South Wales
		Cities cc16 = new Cities(16, "Newcastle", s9);   // New South Wales

		Cities cc17 = new Cities(17, "Melbourne", s10);   // Victoria
		Cities cc18 = new Cities(18, "Geelong", s10);     // Victoria

		Cities cc19 = new Cities(19, "Brisbane", s11);    // Queensland
		Cities cc20 = new Cities(20, "Gold Coast", s11);  // Queensland
		
		List<Countries> asList = Arrays.asList(c1,c2,c3,c4);
		List<States> asList2 = Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11);
		
		List<Cities> asList3 = Arrays.asList(cc1,cc2,cc3,cc4,cc5,cc6,cc7,cc8,cc9,cc10,cc11,cc12,cc13,cc14,cc15,cc16,cc17,cc18,cc19,cc20);
		
		countryRepo.saveAll(asList);
		stateRepo.saveAll(asList2);
		cityRepo.saveAll(asList3);
		


           UsersMaster user = new UsersMaster();
           user.setEmail("hellosparkcoder@gmail.com");
           user.setPassword("rajesh");
           user.setPasswordUpdated("yes");
		
		userRepo.save(user);

	}

}
