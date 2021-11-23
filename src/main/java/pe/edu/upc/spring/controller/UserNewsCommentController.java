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
@RequestMapping("/noticiaComentario")
public class UserNewsCommentController {
	@Autowired
	private IUserReviewService urService;
	
	//PARA REGISTRAR USER REVIEWS
	@Autowired
	private IUserService uService;
	@Autowired
	private IMovieService mService;


}
