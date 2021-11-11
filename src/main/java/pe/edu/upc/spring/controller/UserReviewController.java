package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Movie;
import pe.edu.upc.spring.model.User;
import pe.edu.upc.spring.model.UserReview;
import pe.edu.upc.spring.service.IMovieService;
import pe.edu.upc.spring.service.IUserReviewService;
import pe.edu.upc.spring.service.IUserService;

@Controller
@RequestMapping("/usuarioReview")
public class UserReviewController {
	@Autowired
	private IUserReviewService urService;
	
	//PARA REGISTRAR USER REVIEWS
	@Autowired
	private IUserService uService;
	@Autowired
	private IMovieService mService;

	@RequestMapping("/bienvenido")
	public String goWelcomePage() {
		return "welcome";
	}
	
	@RequestMapping("/reviews")
	public String goMovieUserReviewPage(Model model, @RequestParam(value="idMovie") Integer idMovie) {
		
		List<UserReview>objReview = urService.findByMovieId(idMovie);
		
		model.addAttribute("listUserReviews",objReview);
		model.addAttribute("userReview", new UserReview());
		
		Optional <Movie>objMovie = mService.findById(idMovie); 
		if(objMovie.isPresent())
			objMovie.ifPresent(o -> model.addAttribute("movie", o));
		
		return "movieUserReview";
	}
	
	@RequestMapping("/registrar")
	public String register(@ModelAttribute("userReview") UserReview objUserReview,
			@RequestParam(value = "idUser") Integer idUser, @RequestParam(value = "idMovie") Integer idMovie, BindingResult binRes,
			Model model, RedirectAttributes redirectAttributes) throws ParseException {
		if (binRes.hasErrors())
			return "movieUser";
		else {
			//Para registrar un objeto UserReview
			Optional<User>objUser = uService.findById(idUser);   
			if(objUser.isPresent())
				objUser.ifPresent(o -> objUserReview.setUser(o));
			
			Optional<Movie>objMovie = mService.findById(idMovie);
			if(objMovie.isPresent())
				objMovie.ifPresent(o -> objUserReview.setMovie(o));
		
			boolean flag = urService.save(objUserReview); //Se registra el objeto
			if (flag)
			{	
				//Cargando el model
				Optional <Movie>movie = mService.findById(idMovie);
				if(movie.isPresent())
					movie.ifPresent(o -> redirectAttributes.addAttribute("idMovie", o.getIdMovie()));
				
				model.addAttribute("listUserReviews",urService.findAllSortIdDesc());
				model.addAttribute("userReview", new UserReview());
				return "redirect:/usuarioReview/reviews";
			}
				
			else {
				model.addAttribute("mensaje", "Rochezaso");
				return "redirect:/usuarioReview/reviews";
			}
			
			/*
			boolean flag = urService.save(objUserReview);
			if (flag)
			{	
				if(objMovie.isPresent())
					objMovie.ifPresent(o -> redirectAttributes.addAttribute("id", o.getIdMovie()));
				return "redirect:/usuarioReview/reviews";
			}
				
			else {
				model.addAttribute("mensaje", "Rochezaso");
				return "redirect:/usuarioReview/reviews";
			}*/
		}
	}
}
