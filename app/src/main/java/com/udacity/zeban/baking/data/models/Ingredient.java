package com.udacity.zeban.baking.data.models;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("measure")
	private String measure;

	@SerializedName("ingredient")
	private String ingredient;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setMeasure(String measure){
		this.measure = measure;
	}

	public String getMeasure(){
		return measure;
	}

	public void setIngredient(String ingredient){
		this.ingredient = ingredient;
	}

	public String getIngredient(){
		return ingredient;
	}

	@Override
 	public String toString(){
		return 
			"IngredientsItem{" + 
			"quantity = '" + quantity + '\'' + 
			",measure = '" + measure + '\'' + 
			",ingredient = '" + ingredient + '\'' + 
			"}";
		}
}