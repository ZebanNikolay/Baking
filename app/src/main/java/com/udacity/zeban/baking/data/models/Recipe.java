package com.udacity.zeban.baking.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class Recipe implements Parcelable {

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

	public String getIngredientsText(){
	    List<String> result = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            result.add(ingredient.getIngredient());
        }
		return TextUtils.join(",\n", result);
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.image);
		dest.writeInt(this.servings);
		dest.writeString(this.name);
		dest.writeList(this.ingredients);
		dest.writeInt(this.id);
		dest.writeTypedList(this.steps);
	}

	public Recipe() {
	}

	protected Recipe(Parcel in) {
		this.image = in.readString();
		this.servings = in.readInt();
		this.name = in.readString();
		this.ingredients = new ArrayList<Ingredient>();
		in.readList(this.ingredients, Ingredient.class.getClassLoader());
		this.id = in.readInt();
		this.steps = in.createTypedArrayList(Step.CREATOR);
	}

	public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
		@Override
		public Recipe createFromParcel(Parcel source) {
			return new Recipe(source);
		}

		@Override
		public Recipe[] newArray(int size) {
			return new Recipe[size];
		}
	};
}