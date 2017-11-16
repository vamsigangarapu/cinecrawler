package com.cinecrawler.myapp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.cinecrawler.myapp.core.MisConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cinecrawler.service.LoginService;
import com.cinecrawler.service.RegisterService;
import com.cinecrawler.service.SearchService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/Hello", method = RequestMethod.GET)
	protected ModelAndView helloWorld() {
	 
			ModelAndView modelandview = new ModelAndView("HelloPage");
			modelandview.addObject("welcomeMessage", "Hi User, welcome to the first Spring MVC Application");
			
			return modelandview;
	}
	
	@RequestMapping(value = "/hi/{countryname}/{username}")
	//public ModelAndView hi(@PathVariable String username, @PathVariable String countryname){
	public ModelAndView hi(@PathVariable Map<String, String> pathvars){
		String name = pathvars.get("username");
		String Cname = pathvars.get("countryname");
		ModelAndView model = new ModelAndView("hi");
		model.addObject("HelloMessage","Hi, Hello,"+name+", You are from "+Cname);
		return model;
	}
}

/*@Controller
@RequestMapping("/search")

class MovieSearch{
	public String searchMovie(ModelMap model){
		
	}
}*/

@Controller
@RequestMapping("/movie")

class MovieController{
	

	@Autowired
	RegisterService regService;
	@Autowired
	public LoginService loginService;
	@Autowired
	SearchService searchService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Value("$(json.key)")
	private String key;

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView searchMovie(@RequestParam(value="loginName", required=false) String loginName,@ModelAttribute("loginDetails")LoginDetails loginDetails, HttpSession session, BindingResult result, ModelMap modelMap) throws JsonParseException, JsonMappingException, IOException{
		
		if(loginName!=null){
			modelMap.put("loginDetails", loginDetails);
			modelMap.addAttribute("username", loginDetails.getUsername());
		}
		
		session.setAttribute("username", loginDetails.getUsername());
		
		final String inTheaterMoviesUri = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey="+key;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<String> resResult = restTemplate.exchange(inTheaterMoviesUri, HttpMethod.GET, entity, String.class);
	    System.out.println(resResult);
		    ObjectMapper mapper = new ObjectMapper();
		    
		    Map<String, Object> obj= mapper.readValue(resResult.getBody(), new TypeReference<Map<String,Object>>(){});

		    int i = 0;
		    int totalmovies = (Integer) obj.get("total");
		    if(totalmovies>0){
		    	i=0;
		    }
		    List<Map<String, Object>> moviesLst =  (List<Map<String,Object>>) obj.get("movies");
		    
		    Map<String, Integer> list = new HashMap <String, Integer>();
		    
		    //Map<String, Object> imageLst =  moviesLst.("posters");
		    for (Map<String, Object> movie : moviesLst) {
		    	//String movieId = (String) movie.get(MisConstants.ID);
		    	String movieTitle = (String) movie.get(MisConstants.MOVIE_TITLE);
		    	list.put(movieTitle, i);
		    	i++;
		    }
		    
		    ModelAndView model = new ModelAndView("search");
		    
		    model.addObject("lists", list);
		
		
		    final String openingMoviesUri = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json?apikey="+key;
		    
		    ResponseEntity<String> openingResult = restTemplate.exchange(openingMoviesUri, HttpMethod.GET, entity, String.class);
		    
		    
		    
		    Map<String, Object> openingObj= mapper.readValue(openingResult.getBody(), new TypeReference<Map<String,Object>>(){});
		    
		    int j = 0;
		    int openingTotalMovies = (Integer) obj.get("total");
		    if(openingTotalMovies>0){
		    	j=0;
		    }
		   
		    List<Map<String, Object>> openingMoviesLst =  (List<Map<String,Object>>) openingObj.get("movies");
		    
		    Map<String, Integer> openingMovieslist = new HashMap <String, Integer>();
		    
		    //Map<String, Object> imageLst =  moviesLst.("posters");
		    for (Map<String, Object> movie : openingMoviesLst) {
		    	//String movieId = (String) movie.get(MisConstants.ID);
		    	String movieTitle = (String) movie.get(MisConstants.MOVIE_TITLE);
		    	openingMovieslist.put(movieTitle, j);
		    	j++;
		    }
		    
		    model.addObject("openingMovieslists", openingMovieslist);
		
		    
		    
		    final String upcomingMoviesUri = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey="+key;
		    
		    ResponseEntity<String> upcomingResult = restTemplate.exchange(upcomingMoviesUri, HttpMethod.GET, entity, String.class);
		    
		    
		    
		    Map<String, Object> upcomingObj= mapper.readValue(upcomingResult.getBody(), new TypeReference<Map<String,Object>>(){});

		   
		    List<Map<String, Object>> upcomingMoviesLst =  (List<Map<String,Object>>) upcomingObj.get("movies");
		    
		    int k = 0;
		    int upcomingTotalMovies = (Integer) obj.get("total");
		    if(upcomingTotalMovies>0){
		    	k=0;
		    }
		    Map<String, Integer> upcomingMovieslist = new HashMap <String, Integer>();
		    
		    //Map<String, Object> imageLst =  moviesLst.("posters");
		    for (Map<String, Object> movie : upcomingMoviesLst) {
		    	//String movieId = (String) movie.get(MisConstants.ID);
		    	String movieTitle = (String) movie.get(MisConstants.MOVIE_TITLE);
		    	upcomingMovieslist.put(movieTitle, k);
		    	k++;
		    }
		    
		    model.addObject("upcomingMovieslists", upcomingMovieslist);
		    
		    
		    session.setAttribute("username", loginDetails.getUsername());
		    modelMap.put("loginDetails", loginDetails);
		    modelMap.addAttribute("username", loginDetails.getUsername());
		    model.addObject("username", loginDetails.getUsername());
		    
		//return "redirect:/static/html/search.html";
		    return model;
	}
	
	

	
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value="/SubmitMovie", method = RequestMethod.GET)
	public ModelAndView getSearchedMovie(@RequestParam String movieName, @RequestParam(value="loginName", required=false) String username, ModelMap model, @ModelAttribute("movieSearch") MovieSearchResultObject movieSearch, @ModelAttribute("movieSearchResult") MovieSearchResult movieSearchResult, HttpServletRequest req, BindingResult result, @ModelAttribute("username") LoginDetails loginDetails) throws Exception{
		
		if(username!=null){
			model.put("loginDetails", loginDetails);
			model.addAttribute("username", loginDetails.getUsername());
		}
		//model.put("username", loginDetails);
		//model.put(key, value)
		//session.setAttribute("username",loginDetails.getUsername());
		final String uri = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=9htuhtcb4ymusd73d4z6jxcj&q="+movieName+"&page_limit=50";
		
		model.addAttribute("movieName", movieName);
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		    ResponseEntity<String> resResult = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		    System.out.println(resResult);
		    ObjectMapper mapper = new ObjectMapper();
		    
		    Map<String, Object> obj= mapper.readValue(resResult.getBody(), new TypeReference<Map<String,Object>>(){});

		    int totalmovies = (Integer) obj.get("total");
		    System.out.println("Total movies are: totalmovies {} "+ totalmovies);
		    int i=0;
		    List<Map<String, Object>> moviesLst =  (List<Map<String,Object>>) obj.get("movies");
		   
		    List<MovieSearchResultObject> MovieSearchResultObjects= new ArrayList<MovieSearchResultObject>();
		    MovieSearchResult moviesearchresult = new MovieSearchResult();

		    
		    
		    for (Map<String, Object> movie : moviesLst) {
		    	String movieId = (String) movie.get(MisConstants.ID);
		    	String movieTitle = (String) movie.get(MisConstants.MOVIE_TITLE);		    	
		    	String movieYear = "Not Available";
		    	if(movie.get(MisConstants.YEAR) instanceof Integer){
		    		movieYear=String.valueOf(movie.get(MisConstants.YEAR));
		    		if(movieYear==""){
		    			movieYear = "**";
		    		}
		    	}
		    	String movieRuntime="Not Available";
		    	if(movie.get("runtime") instanceof Integer){
		    		movieRuntime=String.valueOf(movie.get("runtime"));
		    		if(movieRuntime==""){
		    			movieRuntime = "**";
		    		}
		    	}

		    	String movieSynopsis = (String) movie.get(MisConstants.SYNOPSIS);
		    	
		    	Map<String, Object> moviePosters = (Map<String, Object>) movie.get("posters");
		    	String movieThumbnails = (String) moviePosters.get(MisConstants.MOVIE_THUMBNAIL);
		    	
		    	List<Map<String, Object>> castLst =  (List<Map<String,Object>>) movie.get("abridged_cast");
			    
		    	Map<String, String> castMovieList = new HashMap <String, String>();
		    	Map<String, Integer> castMovieNameList = new HashMap <String, Integer>();
		    	int p =0,q =0;
		    	 
		    	 List<String> castNames = new ArrayList<String>();
		    	 String castCharName = "Not Available";
		    	for(Map<String, Object> cast : castLst) {
		    		String castName = (String) cast.get(MisConstants.ORIG_NAME);
		    		if((List<String>) cast.get(MisConstants.CHARC_NAME)!=null){
		    			castNames.addAll((List<String>) cast.get(MisConstants.CHARC_NAME));
		    			for (String s : castNames)
				    	{
				    		castCharName = s;
				    		castMovieList.put(castName, castCharName);
				    	}
		    		}
		    		else{
		    			castCharName = "Not Available";
		    			castMovieList.put(castName, castCharName);
		    		}
		    		
		    	}
		    	
		    	
		    	Map<String, Object> movieReview = (Map<String, Object>) movie.get("ratings");
			    	String movieCriticsRating = "Not Available";
			    	if(movieReview.get(MisConstants.CRITICS_SCORE) instanceof Integer){
			    		movieCriticsRating=String.valueOf(movieReview.get(MisConstants.CRITICS_SCORE));
			    		if(movieCriticsRating==""){
			    			movieCriticsRating = "**";
			    		}
			    	}
			    	String movieAudienceRating = "Not Available";
			    	if(movieReview.get(MisConstants.AUDIENCE_SCORE) instanceof Integer){
			    		movieAudienceRating=String.valueOf(movieReview.get(MisConstants.AUDIENCE_SCORE));
			    		if(movieAudienceRating==""){
			    			movieAudienceRating = "**";
			    		}
			    	}
		    	
		    	
		    	logger.info(MessageFormat.format("Receieved movie information is: with movieId {0} , movieTitle {1} and having thumbnail {2} with castList {3} having criticScore {4}", movieId, movieTitle, movieThumbnails, castMovieList, movieCriticsRating));
		    	    	
		    	MovieSearchResultObjects.add(new MovieSearchResultObject(movieId, movieTitle, movieThumbnails, movieYear, movieRuntime, movieSynopsis, movieCriticsRating, movieAudienceRating, castMovieList));
		         
				moviesearchresult.setMovieSearchResultObjects(MovieSearchResultObjects);
		    	
		    	
		    	/*int output=0;
		    	while(i<=MovieSearchResultObjects.size()){
		    		System.out.println(MovieSearchResultObjects.get(output));
		    		output++;
		    	}*/
		    	
			   /* for(Map.Entry<String, Object> entry : movie.entrySet()){
			    	if(entry.getKey()==ID){
			    		System.out.println("Value is: "+entry.getValue());
			    		movieSearch.setId(entry.getValue());
			    	}
			    	if(entry.getKey() == MOVIE_TITLE){
			    		System.out.println("Value is: "+entry.getValue());
			    		movieSearch.setMovieName(entry.getValue());
			    	}
			    	
			    	if(entry.getKey() == "posters"){
			    		//Map<String, Object> obj1= mapper.readValue(moviesLst.toString(), new TypeReference<Map<String,Object>>(){});
			    		HashMap<String,Object> obj1 =  new ObjectMapper().readValue(moviesLst.toString(), HashMap.class);
			    		Object value = movie.get("posters");
			    		List<Map<String, Object>> imgLst = (List<Map<String, Object>>) obj1.get("posters");
			    		
			    		for (Map<String, Object> map1 : imgLst) {
			    			for(Map.Entry<String, Object> entry1 : map1.entrySet()){
			    				if(entry.getKey() == "original"){
			    					System.out.println("Value is: "+entry1.getValue());
			    				}
			    			}
			    		}
			    		HashMap<String,Object> imgResult = new ObjectMapper().readValue(moviesLst.toString(), HashMap.class);
			    		for(HashMap<String, Object> hashResult : imgResult){
			    			
			    		}
			    		
			    		
			    		System.out.println("Key is: "+entry.getKey());
			    		System.out.println("\nValue is: "+entry.getValue());
			    		
			    		}
			    	}*/
		    	}
		
		    List<MovieSearchResultObject> movieSearchResultObjects = moviesearchresult.getMovieSearchResultObjects();
	         
	        if(null != movieSearchResultObjects && movieSearchResultObjects.size() > 0) {
	            for (MovieSearchResultObject  movieSearchResultObject: movieSearchResultObjects) {
	            	logger.info(MessageFormat.format("Movie information is: movieId {0}, movieName {1} and having thumbnail {2} with cast {3}", movieSearchResultObject.getId(), movieSearchResultObject.getMovieName(), movieSearchResultObject.getImageUrl(), movieSearchResultObject.getCast()));
	                //System.out.println("Movie Id: "+ movieSearchResultObject.getId());
	            }
	        }
	        
	        //model.put(key, value)
	        
	        model.addAttribute("username", loginDetails.getUsername());
	        
	        SearchDetails searchDetails = new SearchDetails(username);
	        model.addAttribute("searchDetails", searchDetails);
	        System.out.println("value is: "+username);
	        //searchService.checkZipcode(searchDetails.getUsername());
		    
		return new ModelAndView("movieSearchResult", "moviesearchresult", moviesearchresult);
			
	}
	
	/*@RequestMapping(value="/{name}", method=RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model){
		model.addAttribute("movie", name);
		return "list";
	}*/
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginUser(ModelMap model, HttpSession session) {

		LoginDetails loginDetails = new LoginDetails();
		model.addAttribute("loginDetails", loginDetails);
		//model.addAttribute("movie", "this is default movie");
		return "list";

	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public String loginUser(LoginDetails loginDetails, ModelMap model, HttpSession session, BindingResult result) {

		if(result.hasErrors()){
			return "list";
		}
		
		/*String userName = "username";
		String password = "password";
		
		loginDetails = (LoginDetails) model.get("loginDetails");
		if(!loginDetails.getUsername().equals(userName) || (!loginDetails.getPassword().equals(password))){
			return "list";
		}*/
		
		boolean userExists = loginService.checkLoginDetails(loginDetails.getUsername(), loginDetails.getPassword());
		if(userExists){
			model.put("loginDetails", loginDetails);
			model.addAttribute("username", loginDetails.getUsername());
			return "redirect:/movie/";
		}
		
		else{
			return "list";
		}
		//model.addAttribute("movie", "this is default movie");
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String newUser(ModelMap model){
		RegisterDetails registerDetails = new RegisterDetails();
		model.addAttribute("registerDetails", registerDetails);
		return "registerUser";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addDetails(RegisterDetails registerDetails, BindingResult result,
			ModelMap model ){
		
			regService.addDetails(registerDetails);
		
		return "redirect:/movie/";
	}
	
	@RequestMapping(value="/Submitlist", method = RequestMethod.POST)
	public String getDefaultMovie(@ModelAttribute("login1") LoginDetails login1, ModelMap model, BindingResult Result){
		/*String uName = pathvars.get("Username");
		String paswd = pathvars.get("Password");
		
		LoginDetails login1 = new LoginDetails();
		login1.setUsername(uName);
		login1.setPassword(paswd);
		
		
		model.addAttribute("login1", login1);*/
		
		if(Result.hasErrors()){
			return "list";
		}
		
		return "display";
		
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("username");
		return "redirect:/movie/";	
	}
	
}