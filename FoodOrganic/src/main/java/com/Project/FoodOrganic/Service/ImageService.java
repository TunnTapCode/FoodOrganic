package com.Project.FoodOrganic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Image_Avt;
import com.Project.FoodOrganic.Repository.ImageRepo;

@Service
public class ImageService {
	
	@Autowired
	ImageRepo imageRepo ;
	
	public List<Image_Avt> findAll(){
		return imageRepo.findAll();
	}
	public Optional<Image_Avt> findById(Long id){
		return imageRepo.findById(id);
	}

}
