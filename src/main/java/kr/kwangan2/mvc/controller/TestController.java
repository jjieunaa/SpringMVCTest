package kr.kwangan2.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.kwangan2.mvc.domain.PersonDTO;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/test/*", method={RequestMethod.GET, RequestMethod.POST})	// http://localhost:8080/test/
@Log4j
public class TestController {
	
	// http://localhost:8080/test/test1 (GET)
	@RequestMapping(value="/test1", method={RequestMethod.GET})
	public void test1() {
		log.info("test1() 호출!");
	}
	
	// http://localhost:8080/test/test2 (POST)
	@RequestMapping(value="/test2", method={RequestMethod.POST})
	public void test2() {
		log.info("test2() 호출!");
	}
	
	@RequestMapping(value="/personParam")
	public void testPersonDTO(@RequestParam("name") String n, @RequestParam("age") int a) {
		log.info(n + ":" + a);
	}
	
	@RequestMapping(value="/personDTO")
	public void testPersonDTO(PersonDTO personDTO) {
		log.info(personDTO);
	}
	
	@RequestMapping(value="/fileupload")
	public void testFileupload(@RequestParam("uploadFile") ArrayList<String> listuploadFiles) {
		log.info(listuploadFiles);
	}
	
	@RequestMapping(value="/personDTOList")
	public void testPersonDTOList(PersonDTO personDTO) {
		log.info(personDTO);
	}
	
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	*/
	
	@GetMapping("/initBinder")
	public void binder(PersonDTO personDTO) {
		log.info(personDTO);
	}
	
	@GetMapping("/getModelInfo")
	public String getModelInfo(Model model, @ModelAttribute("addr") String addr) {
		model.addAttribute("name", "홍길동");
		model.addAttribute("age", 20);
		log.info(addr);
		
		return "getModelInfo";
	}
	
	@GetMapping("/getInfo")
	public String getInfo(Model model, String name, int age) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "redirect:/";
	}
	
	@GetMapping("/getJson")
	public @ResponseBody PersonDTO getJson() {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setName("홍길동");
		personDTO.setAge(20);
		personDTO.setBirth(new Date());
		personDTO.setPersonDTOList(null);
		
		PersonDTO personDTO2 = new PersonDTO();
		personDTO2.setName("홍길동");
		personDTO2.setAge(20);
		personDTO2.setBirth(new Date());
		personDTO2.setPersonDTOList(null);
		
		PersonDTO personDTO3 = new PersonDTO();
		personDTO3.setName("홍길동");
		personDTO3.setAge(20);
		personDTO3.setBirth(new Date());
		personDTO3.setPersonDTOList(null);
		
		ArrayList<PersonDTO> personDTOList = new ArrayList<PersonDTO>();
		personDTOList.add(personDTO2);
		personDTOList.add(personDTO3);
		
		personDTO.setPersonDTOList(personDTOList);
		
		return personDTO;
	}
	
	@GetMapping("modelAndView")
	public ModelAndView modelAndView(Model model) {
		model.addAttribute("name", "홍길동");
		model.addAttribute("age", 20);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("modelAndView");
		// mav.setStatus(HttpStatus.OK);
		
		return mav;
	}
	
	@GetMapping("/realUploadForm")
	public String realUploadForm() {
		return "realUploadForm";
	}
	
	@PostMapping("/realfileupload")
	public void realfileupload(ArrayList<MultipartFile> uploadFile) {
		uploadFile.forEach(file->{
			log.info("업로드할 때의 파일명: " +file.getOriginalFilename());
			log.info("업로드할 때의 파일크기: " +file.getSize());
		});	
	}
}	// class
