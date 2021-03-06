package com.ub.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ub.model.AppUser;
import com.ub.model.Comment;
import com.ub.model.JobExperience;
import com.ub.model.PhotoUser;
import com.ub.model.Publication;
import com.ub.model.Studies;
import com.ub.repository.CommentRepository;
import com.ub.repository.JobExperienceRepository;
import com.ub.repository.PhotoUserRepository;
import com.ub.repository.PublicationRepository;
import com.ub.repository.StudiesRepository;
import com.ub.repository.UserRepository;
import com.ub.service.SecurityServiceImpl;
import com.ub.service.UserServiceImpl;
import com.ub.utils.LevenshteinDistance;

@RestController
@RequestMapping(value ="/users")
public class UserController {
	
	@Autowired
    private UserServiceImpl userService;

    @Autowired
    private SecurityServiceImpl securityService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JobExperienceRepository jobExperienceRepository;
	
	@Autowired
	private StudiesRepository studiesRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PhotoUserRepository photoRepo;
			
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {
		model.addAttribute("appUser",  new AppUser());

		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> createUser(@RequestBody AppUser appUser) {
		appUser.setId(Long.MAX_VALUE);
		String password = appUser.getPassword();
		AppUser savedUser = userService.save(appUser);
		//System.out.println(appUser.getStudies_list().get(0).getBeginYear()+"SAHIDFGASD");
		
        securityService.login(appUser.getEmail(), password);
        
        if (savedUser == null) {
        	return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}
        
	}

	@RequestMapping(value = "/register/{phase}",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String updateUser(@RequestBody AppUser appUser , @PathVariable("phase") int phase) {
		
		//Optional<AppUser> userOptional = userRepository.findByEmail(appUser.getEmail());

		AppUser savedUser = null;
		savedUser = userRepository.save(appUser);
		if (savedUser == null) {
			return "403Page";
		} else {
			
			if (phase == 1) {
				return "resgister_1";
			}
			else if (phase ==2) {
				return "resgister_2";
			}
			else if (phase ==3) {
				return "resgister_3";
			}
		}
		return null;
	}
   
	@RequestMapping(value = "/performlogin", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> performlogin(@RequestBody AppUser appUser) {
        try {
        	securityService.login(appUser.getEmail(), appUser.getPassword());
        } catch (AuthenticationException e) {
        	return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
		
	@GetMapping("/{id}")
	public AppUser retrieveUser(@PathVariable long id) {
		Optional<AppUser> foundUser = userRepository.findById(id);

		if (!foundUser.isPresent())
			System.out.println("id-" + id+ " not found");

		return foundUser.get();
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody AppUser user, @PathVariable long id) {

		Optional<AppUser> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/addStudies", method = RequestMethod.POST)
	public ResponseEntity<Object> addStudies(@RequestBody Studies studies, Principal user) {
		

		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		foundUser.addStudies(studies);
		
			
		studiesRepository.save(studies);

		userRepository.save(foundUser);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/addJobExperience", method = RequestMethod.POST)
	public ResponseEntity<Object> addJobExperience(@RequestBody JobExperience job, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);

		foundUser.addJobExperience(job);

		jobExperienceRepository.save(job);	
		userRepository.save(foundUser);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/addPersonalInfo", method = RequestMethod.POST)
	public ResponseEntity<Object> addPersonalInfo(@RequestBody AppUser appUser, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		foundUser.setCountry(appUser.getCountry());
		foundUser.setPostalCode(appUser.getPostalCode());
		userRepository.save(foundUser);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/addPublication", method = RequestMethod.POST)
	public ResponseEntity<Object> addPublication(@RequestBody Publication p, Principal user) {

		p.setId(Long.MAX_VALUE);
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		
		foundUser.addPublication(p);
		
		publicationRepository.save(p);
		userRepository.save(foundUser);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/addComment/{id}")
	public ResponseEntity<Object> addComment(@PathVariable long id, @RequestBody Comment comment, Principal user) {
		
		Optional<Publication> publi = publicationRepository.findById(id);
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		comment.setUser(foundUser);
		
		if (publi.isPresent()) {
			publi.get().addComment(comment);
			commentRepository.save(comment);
			publicationRepository.save(publi.get());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/updatePersonalInfo", method = RequestMethod.POST)
	public ResponseEntity<Object> updatePersonalInfo(@RequestBody Object appUser, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		Map info = ((Map)appUser);
		Set s = info.keySet();
		for (Object key: s){
			String k = key.toString().replace("[", "").replaceAll("]","");
			switch (k) {
				case "firstName": 
					System.out.println(info.get(key).toString());
					foundUser.setFirstName(info.get(key).toString());
					break;
				case "secondName": foundUser.setSecondName(info.get(key).toString());
					break;
				case "password": foundUser.setPassword(info.get(key).toString());
					userService.save(foundUser);
					break;
				case "country": foundUser.setCountry(info.get(key).toString());
					break;
				case "email": foundUser.setEmail(info.get(key).toString());
					break;
				case "postalCode": foundUser.setPostalCode(Integer.parseInt(info.get(key).toString()));
					break;
			}
		}

		userRepository.save(foundUser);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/updateStudies", method = RequestMethod.POST)
	public ResponseEntity<Object> updateStudies(@RequestBody Object studies, Principal user) {
		
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		Map info = ((Map)studies);
		Set s = info.keySet();
		
		Integer index = (Integer) info.get("index");

		Studies oldStudy = foundUser.getStudies_list().get(index);
		for (Object key: s){
			String k = key.toString().replace("[", "").replaceAll("]","");
			switch (k) {
				case "title":
					oldStudy.setTitle(info.get(key).toString());
					break;
				case "university":
					oldStudy.setUniversity(info.get(key).toString());
					break;
				case "mark":
					oldStudy.setMark(Integer.parseInt(info.get(key).toString()));
					break;
				case "comment":
					if (info.get(key)!=null) {
						oldStudy.setComment(info.get(key).toString());
					}
					
					break;
				case "beginYear":
					oldStudy.setBeginYear(Integer.parseInt(info.get(key).toString()));
					break;
				case "endYear":
					oldStudy.setEndYear(Integer.parseInt(info.get(key).toString()));
					break;
			}
		}
		
		//studiesRepository.
		
		userRepository.save(foundUser);
		return ResponseEntity.noContent().build();

	}
	
	@RequestMapping(value = "/updateJobExperience", method = RequestMethod.POST)
	public ResponseEntity<Object> updateJobExperience(@RequestBody Object job, Principal user) {
		
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		Map info = ((Map)job);
		Set s = info.keySet();
		
		Integer index = (Integer) info.get("index");

		JobExperience oldJob = foundUser.getExperiences().get(index);
		
		for (Object key: s){
			String k = key.toString().replace("[", "").replaceAll("]","");
			switch (k) {
				case "title":
					oldJob.setTitle(info.get(key).toString());
					break;
				case "company_Name":
					oldJob.setCompany_Name((info.get(key).toString()));
					break;
				case "beginYear":
					oldJob.setBeginYear(Integer.parseInt(info.get(key).toString()));
					break;
			}
		}
		
		//studiesRepository.
		userRepository.save(foundUser);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/addFriends", method = RequestMethod.POST)
	public ResponseEntity<Object> addFriends(@RequestBody Object friends, Principal user) {
		String email = user.getName(); //Email
		AppUser loggedUser = userRepository.findByEmail(email);
		
		Map info = ((Map)friends);
		Set s = info.keySet();
		
		List<String> addFriends = (List<String>) info.get("list");
		
		
		String friendEmail = "";
		
		for (int i = 0; i < addFriends.size(); i ++ ) { 
			friendEmail = addFriends.get(i);
			if (!friendEmail.equals(email) && !loggedUser.getFriends().contains(friendEmail)) {				
				loggedUser.addFriend(friendEmail);
				AppUser friendRequested = userRepository.findByEmail(friendEmail);
				friendRequested.addFriend(email);
			}
		}
		
		userRepository.save(loggedUser);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/getUserByMail", method= RequestMethod.GET)
	public AppUser getUserByMail(@RequestBody Object email) {
		String k = email.toString().replace("[", "").replaceAll("]","");
		return userRepository.findByEmail(k);
		
	}
	
	@RequestMapping(value="getSortedPubli", method = RequestMethod.GET)
	public List<Publication> getSortedList(Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		return foundUser.sortedPublications(userRepository);
	}
	
	@RequestMapping(value="getAppUserFriends", method = RequestMethod.GET) 
	public List<AppUser> getAppUserFriends(Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		return foundUser.getAppUserFriends(userRepository);
		
	}
	
	@RequestMapping(value = "/deleteFriend", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteFriend(@RequestBody Object emailDelete, Principal user) {
		String email = user.getName(); //Email
		emailDelete = emailDelete.toString().replace("[", "").replaceAll("]","");
		AppUser foundUser = userRepository.findByEmail(email);

		if (foundUser.getFriends().contains(emailDelete)) {
			foundUser.getFriends().remove(emailDelete);
			AppUser friend = userRepository.findByEmail((String)emailDelete);
			friend.getFriends().remove(foundUser.getEmail());
			userRepository.save(foundUser);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public ResponseEntity<Object> search(@RequestBody Object search, Principal user) {
		
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		
		Map info = ((Map)search);
		Set s = info.keySet();
		
		String input = (String) info.get("search");
		foundUser.setUserSearch(input);
		userRepository.save(foundUser);

	    return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/getSIndex", method = RequestMethod.GET)
	public Integer getSIndex(Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		return foundUser.getsIndex();
	}
	
	
	@RequestMapping(value = "/getJIndex", method = RequestMethod.GET)
	public Integer getJIndex(Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		return foundUser.getjIndex();
	}
	
	
	
	@RequestMapping(value = "/setJIndex", method = RequestMethod.POST)
	public ResponseEntity<Object> setJIndex(@RequestBody Object indexJSON, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		Map info = ((Map)indexJSON);
		Set s = info.keySet();
		Integer index = (Integer) info.get("index");
		foundUser.setjIndex(index);
		userRepository.save(foundUser);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/setSIndex", method = RequestMethod.POST)
	public ResponseEntity<Object> setSIndex(@RequestBody Object indexJSON, Principal user) {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);
		Map info = ((Map)indexJSON);
		Set s = info.keySet();
		Integer index = (Integer) info.get("index");
		foundUser.setsIndex(index);
		userRepository.save(foundUser);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/addPhoto", method  = RequestMethod.POST)
	public ResponseEntity<Object> addPhoto(@RequestParam MultipartFile file, Principal user) throws IOException {
		String email = user.getName(); //Email
		AppUser foundUser = userRepository.findByEmail(email);

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			
			PhotoUser photo = new PhotoUser(file.getOriginalFilename(), new String(Base64.getEncoder().encode(bytes)));
			foundUser.setPic(photo);
			photoRepo.save(photo);
			userRepository.save(foundUser);
			return ResponseEntity.noContent().build();
        }
		
		return ResponseEntity.notFound().build();
		
		
	}

}
