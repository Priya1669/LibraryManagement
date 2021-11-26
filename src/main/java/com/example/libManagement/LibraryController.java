package com.example.libManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.libManagement.jwt.JwtUtil;
import com.example.libManagement.model.AuthenticationRequest;
import com.example.libManagement.model.AuthenticationResponse;
import com.example.libManagement.model.Book;
import com.example.libManagement.service.LibraryService;
import com.example.libManagement.service.MyUserDetailsService;

@RestController
public class LibraryController {

	/*
	 * @Autowired private Book book;
	 */

	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private AuthenticationManager authManager ;
	
	@Autowired
	private MyUserDetailsService userDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello";
	}
	
	@RequestMapping(value = "/authenticate" , method = RequestMethod.POST)
	public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest authReq) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUserName(), authReq.getPassword()));
		}
		catch(BadCredentialsException e){
			throw new Exception("Incorrect Username or password" + e);
		}
		
		UserDetails userDetails = userDetailService.loadUserByUsername(authReq.getUserName());
		
		String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	 @RequestMapping("/availablebooks") 
	 public List<Book> getAvailableBooks() {
		 return libraryService.getAvailableBooks(); 
	 }
	 
	
	@RequestMapping("/books")
	public List<Book> getAllBooks() {
		return libraryService.getAllBooks();
	}
	
	@RequestMapping( value = "/addbooks" , method = RequestMethod.POST) 
	public void addBooks(@RequestBody Book book) { 
		libraryService.addBooks(book); 
	}
	
	@RequestMapping("/book/{id}")
	public void checkOutBookById(@PathVariable Integer id) {
		libraryService.checkOutBookById(id);
	}

	@RequestMapping("/checkedbooks")
	public List<Book> getCheckedBooks() {
		return libraryService.getCheckedOutBooks();
	}
	
	@RequestMapping("/duebooks")
	public List<Book> getDueBooks(){
		return libraryService.getCheckedBooksOutofDue();
	}

}
