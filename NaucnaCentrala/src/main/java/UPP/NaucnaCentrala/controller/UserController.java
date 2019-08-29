package UPP.NaucnaCentrala.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.NaucnaCentrala.converter.UserToUserDTOConverter;
import UPP.NaucnaCentrala.dto.UserDTO;
import UPP.NaucnaCentrala.model.Reviewer;
import UPP.NaucnaCentrala.model.ReviewerScientificField;
import UPP.NaucnaCentrala.model.User;
import UPP.NaucnaCentrala.service.ReviewerScientificFieldService;
import UPP.NaucnaCentrala.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	IdentityService identityService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDTOConverter userToUserDTOConverter;
	
	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
		User user = userService.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
		if(user == null) {
			return new ResponseEntity<>("Wrong credentials", HttpStatus.BAD_REQUEST);
		}
		userService.setCurrentUser(user);
		
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/register",
			method = RequestMethod.POST
	)
	public void register(@RequestBody UserDTO userDTO) {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("RegisterProcess");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("name", userDTO.getName());
		userMap.put("email", userDTO.getEmail());
		userMap.put("password", userDTO.getPassword());
		userMap.put("city", userDTO.getCity());
		userMap.put("country", userDTO.getCountry());
		
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "userMap", userMap);
		formService.submitTaskForm(task.getId(), userMap);
	}
	
	@RequestMapping(
			value = "/getLoggedIn",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getLoggedIn() {
		User loggedIn = userService.getCurrentUser();
		
		if(loggedIn == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(userToUserDTOConverter.convert(loggedIn), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/logout",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> logout() {
		SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
	}

}
