package com.foodapp.eatme.model.extend;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.foodapp.eatme.model.AnalyzedInstruction;

import java.util.List;

@Entity(tableName = "recipe")
public class RecipeExtend implements Parcelable {
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
    private String sourceName;
    private double pricePerServing;
    private List<ExtendedIngredient> extendedIngredients;
    @PrimaryKey(autoGenerate = false)
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
    private WinePairing winePairing;
    private String instructions;
    private List<AnalyzedInstruction> analyzedInstructions;
    private String originalId;
    private NutriExtend nutriExtend;

    @Ignore
    public RecipeExtend() {
    }

    protected RecipeExtend(Parcel in) {
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
        instructions = in.readString();
        analyzedInstructions = in.createTypedArrayList(AnalyzedInstruction.CREATOR);
        originalId = in.readString();
    }

    public static final Creator<RecipeExtend> CREATOR = new Creator<RecipeExtend>() {
        @Override
        public RecipeExtend createFromParcel(Parcel in) {
            return new RecipeExtend(in);
        }

        @Override
        public RecipeExtend[] newArray(int size) {
            return new RecipeExtend[size];
        }
    };

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

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
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

    public WinePairing getWinePairing() {
        return winePairing;
    }

    public void setWinePairing(WinePairing winePairing) {
        this.winePairing = winePairing;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<AnalyzedInstruction> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(List<AnalyzedInstruction> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public NutriExtend getNutriExtend() {
        return nutriExtend;
    }

    public void setNutriExtend(NutriExtend nutriExtend) {
        this.nutriExtend = nutriExtend;
    }

    public RecipeExtend(boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, boolean veryHealthy, boolean cheap, boolean veryPopular, boolean sustainable, boolean lowFodmap, int weightWatcherSmartPoints, String gaps, int preparationMinutes, int cookingMinutes, int aggregateLikes, int healthScore, String creditsText, String sourceName, double pricePerServing, List<ExtendedIngredient> extendedIngredients, int id, String title, int readyInMinutes, int servings, String sourceUrl, int openLicense, String image, String imageType, String summary, List<String> cuisines, List<String> dishTypes, List<String> diets, List<String> occasions, WinePairing winePairing, String instructions, List<AnalyzedInstruction> analyzedInstructions, String originalId, NutriExtend nutriExtend) {
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.veryHealthy = veryHealthy;
        this.cheap = cheap;
        this.veryPopular = veryPopular;
        this.sustainable = sustainable;
        this.lowFodmap = lowFodmap;
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
        this.gaps = gaps;
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.aggregateLikes = aggregateLikes;
        this.healthScore = healthScore;
        this.creditsText = creditsText;
        this.sourceName = sourceName;
        this.pricePerServing = pricePerServing;
        this.extendedIngredients = extendedIngredients;
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.sourceUrl = sourceUrl;
        this.openLicense = openLicense;
        this.image = image;
        this.imageType = imageType;
        this.summary = summary;
        this.cuisines = cuisines;
        this.dishTypes = dishTypes;
        this.diets = diets;
        this.occasions = occasions;
        this.winePairing = winePairing;
        this.instructions = instructions;
        this.analyzedInstructions = analyzedInstructions;
        this.originalId = originalId;
        this.nutriExtend = nutriExtend;
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
        parcel.writeString(instructions);
        parcel.writeTypedList(analyzedInstructions);
        parcel.writeString(originalId);
    }
}
