package com.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.entites.Cities;
import com.app.entites.Countries;
import com.app.entites.States;
import com.app.entites.UsersMaster;
import com.app.repositories.CitiesRepository;
import com.app.repositories.CountryRepository;
import com.app.repositories.StatesRepository;
import com.app.repositories.UsersRepository;
import com.appbindings.LoginFormBinding;
import com.appbindings.RegisterFormBinding;
import com.appbindings.UpdatePasswordBinding;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryRepository countriesRepo;
	@Autowired
	private StatesRepository statesRepo;
	@Autowired
	private CitiesRepository citiesRepo;
	@Autowired
	private UsersRepository usersRepo;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public Map<Integer, String> getCountries() {

		List<Countries> countryList = countriesRepo.findAll();
		Map<Integer, String> countries = new HashMap<>();
		countryList.forEach(country -> {

			countries.put(country.getCountryId(), country.getCountryName());

		});
		return countries;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		Countries country = new Countries();
		country.setCountryId(countryId);

		List<States> statesList = statesRepo.findByCountryId(country);
		Map<Integer, String> states = new HashMap<>();

		statesList.forEach(state -> {
			states.put(state.getStateId(), state.getStateName());
		});

		return states;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {

		States states = new States();
		states.setStateId(stateId);

		List<Cities> citiesList = citiesRepo.findByStateId(states);

		Map<Integer, String> cities = new HashMap<>();

		citiesList.forEach(city -> {

			cities.put(city.getCityId(), city.getCityName());

		});

		return cities;
	}

	@Override
	public boolean register(RegisterFormBinding form) {

		UsersMaster user = new UsersMaster();
		user.setPasswordUpdated("no");
		BeanUtils.copyProperties(form, user);

		String generatedPwd = generatePassword();
		
		user.setPassword(generatedPwd);
		
		usersRepo.save(user);

		String subject = "Password Generated";
		String body = "Your Password is = " + generatedPwd;
		
//		EmailUtils emailUtils = new EmailUtils();

		return sendEmail(form.getEmail(), subject, body);

	}

	@Override
	public UsersMaster login(LoginFormBinding form) {

		return usersRepo.findByEmailAndPassword(form.getEmail(), form.getPassword());
	}

	@Override
	public boolean checkUser(String email) {

		UsersMaster user = usersRepo.findByEmail(email);

		if (user == null)
			return false;

		return true;
	}

	@Override
	public boolean updatePassword(UpdatePasswordBinding form) {

		if (form.getPassword().equals(form.getConfirmPassword())) {
			
			Optional<UsersMaster> findById = usersRepo.findById(form.getUserId());
			UsersMaster user = findById.get();
			
			user.setPassword(form.getPassword());

			user.setPasswordUpdated("yes");

			usersRepo.save(user);

			return true;
		}

		return false;
	}

	public String generatePassword() {

		String passwordCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		StringBuilder password = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			int randomIndex = random.nextInt(passwordCharacters.length());
			password.append(passwordCharacters.charAt(randomIndex));
		}

		return new String(password);
	}
	
	///// EMAIL SERVICE
	
	
	/*
	 * @Autowired public void setMailSender(JavaMailSender mailSender) {
	 * System.out.println("mail sender is injecting"); this.mailSender = mailSender;
	 * System.out.println(mailSender == null);
	 * System.out.println("mail sender is injected ... .hureeeh"); }
	 */

	public boolean sendEmail(String to , String subject , String body) {
		
	   SimpleMailMessage message = new SimpleMailMessage();
	   message.setTo(to);
	   message.setSubject(subject);
	   message.setText(body);
	   
	   mailSender.send(message);
	   
	   
	   
		return true;
		
	}


}
