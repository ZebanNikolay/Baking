package com.udacity.zeban.baking.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe{

	@SerializedName("image")
	private String image;

	@SerializedName("servings")
	private int servings;

	@SerializedName("name")
	private String name;

	@SerializedName("ingredients")
	private List<Ingredient> ingredients;

	@SerializedName("id")
	private int id;

	@SerializedName("steps")
	private List<Step> steps;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setServings(int servings){
		this.servings = servings;
	}

	public int getServings(){
		return servings;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIngredients(List<Ingredient> ingredients){
		this.ingredients = ingredients;
	}

	public List<Ingredient> getIngredients(){
		return ingredients;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSteps(List<Step> steps){
		this.steps = steps;
	}

	public List<Step> getSteps(){
		return steps;
	}

	@Override
 	public String toString(){
		return 
			"Recipe{" + 
			"image = '" + image + '\'' + 
			",servings = '" + servings + '\'' + 
			",name = '" + name + '\'' + 
			",ingredients = '" + ingredients + '\'' + 
			",id = '" + id + '\'' + 
			",steps = '" + steps + '\'' + 
			"}";
		}
}