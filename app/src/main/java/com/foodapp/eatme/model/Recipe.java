package com.foodapp.eatme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe implements Parcelable {
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean cheap;
    private boolean veryPopular;
    private boolean sustainable;
    private boolean lowFodmap;
    private int weightWatcherSmartPoints;
    private String gaps;
    private int preparationMinutes;
    private int cookingMinutes;
    private int aggregateLikes;
    private int healthScore;
    private String creditsText;
    private String license;
    private String sourceName;
    private double pricePerServing;
    private int id;
    private String title;
    private int readyInMinutes;
    private int servings;
    private String sourceUrl;
    private int openLicense;
    private String image;
    private String imageType;
    private String summary;
    private List<String> cuisines;
    private List<String> dishTypes;
    private List<String> diets;
    private List<String> occasions;
    @SerializedName("analyzedInstructions")
    private List<AnalyzedInstruction> analyzedInstructions;
    private String spoonacularSourceUrl;
    private String author;
    private Nutrition nutrition;

    protected Recipe(Parcel in) {
        vegetarian = in.readByte() != 0;
        vegan = in.readByte() != 0;
        glutenFree = in.readByte() != 0;
        dairyFree = in.readByte() != 0;
        veryHealthy = in.readByte() != 0;
        cheap = in.readByte() != 0;
        veryPopular = in.readByte() != 0;
        sustainable = in.readByte() != 0;
        lowFodmap = in.readByte() != 0;
        weightWatcherSmartPoints = in.readInt();
        gaps = in.readString();
        preparationMinutes = in.readInt();
        cookingMinutes = in.readInt();
        aggregateLikes = in.readInt();
        healthScore = in.readInt();
        creditsText = in.readString();
        license = in.readString();
        sourceName = in.readString();
        pricePerServing = in.readDouble();
        id = in.readInt();
        title = in.readString();
        readyInMinutes = in.readInt();
        servings = in.readInt();
        sourceUrl = in.readString();
        openLicense = in.readInt();
        image = in.readString();
        imageType = in.readString();
        summary = in.readString();
        cuisines = in.createStringArrayList();
        dishTypes = in.createStringArrayList();
        diets = in.createStringArrayList();
        occasions = in.createStringArrayList();
        analyzedInstructions = in.createTypedArrayList(AnalyzedInstruction.CREATOR);
        spoonacularSourceUrl = in.readString();
        author = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }


    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public Recipe() {
    }


    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public void setVeryHealthy(boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public boolean isCheap() {
        return cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }

    public void setVeryPopular(boolean veryPopular) {
        this.veryPopular = veryPopular;
    }

    public boolean isSustainable() {
        return sustainable;
    }

    public void setSustainable(boolean sustainable) {
        this.sustainable = sustainable;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isLowFodmap() {
        return lowFodmap;
    }

    public void setLowFodmap(boolean lowFodmap) {
        this.lowFodmap = lowFodmap;
    }

    public int getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    public void setWeightWatcherSmartPoints(int weightWatcherSmartPoints) {
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
    }

    public String getGaps() {
        return gaps;
    }

    public void setGaps(String gaps) {
        this.gaps = gaps;
    }

    public int getPreparationMinutes() {
        return preparationMinutes;
    }

    public void setPreparationMinutes(int preparationMinutes) {
        this.preparationMinutes = preparationMinutes;
    }

    public int getCookingMinutes() {
        return cookingMinutes;
    }

    public void setCookingMinutes(int cookingMinutes) {
        this.cookingMinutes = cookingMinutes;
    }

    public int getAggregateLikes() {
        return aggregateLikes;
    }

    public void setAggregateLikes(int aggregateLikes) {
        this.aggregateLikes = aggregateLikes;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public String getCreditsText() {
        return creditsText;
    }

    public void setCreditsText(String creditsText) {
        this.creditsText = creditsText;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public double getPricePerServing() {
        return pricePerServing;
    }

    public void setPricePerServing(double pricePerServing) {
        this.pricePerServing = pricePerServing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getOpenLicense() {
        return openLicense;
    }

    public void setOpenLicense(int openLicense) {
        this.openLicense = openLicense;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public List<String> getDishTypes() {
        return dishTypes;
    }

    public void setDishTypes(List<String> dishTypes) {
        this.dishTypes = dishTypes;
    }

    public List<String> getDiets() {
        return diets;
    }

    public void setDiets(List<String> diets) {
        this.diets = diets;
    }

    public List<String> getOccasions() {
        return occasions;
    }

    public void setOccasions(List<String> occasions) {
        this.occasions = occasions;
    }

    public List<AnalyzedInstruction> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(List<AnalyzedInstruction> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeByte((byte) (vegetarian ? 1 : 0));
        parcel.writeByte((byte) (vegan ? 1 : 0));
        parcel.writeByte((byte) (glutenFree ? 1 : 0));
        parcel.writeByte((byte) (dairyFree ? 1 : 0));
        parcel.writeByte((byte) (veryHealthy ? 1 : 0));
        parcel.writeByte((byte) (cheap ? 1 : 0));
        parcel.writeByte((byte) (veryPopular ? 1 : 0));
        parcel.writeByte((byte) (sustainable ? 1 : 0));
        parcel.writeByte((byte) (lowFodmap ? 1 : 0));
        parcel.writeInt(weightWatcherSmartPoints);
        parcel.writeString(gaps);
        parcel.writeInt(preparationMinutes);
        parcel.writeInt(cookingMinutes);
        parcel.writeInt(aggregateLikes);
        parcel.writeInt(healthScore);
        parcel.writeString(creditsText);
        parcel.writeString(license);
        parcel.writeString(sourceName);
        parcel.writeDouble(pricePerServing);
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(readyInMinutes);
        parcel.writeInt(servings);
        parcel.writeString(sourceUrl);
        parcel.writeInt(openLicense);
        parcel.writeString(image);
        parcel.writeString(imageType);
        parcel.writeString(summary);
        parcel.writeStringList(cuisines);
        parcel.writeStringList(dishTypes);
        parcel.writeStringList(diets);
        parcel.writeStringList(occasions);
        parcel.writeTypedList(analyzedInstructions);
        parcel.writeString(spoonacularSourceUrl);
        parcel.writeString(author);
    }


}
