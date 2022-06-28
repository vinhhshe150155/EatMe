package com.foodapp.eatme.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.IngredientInCircleAdapter;
import com.foodapp.eatme.adapter.IngredientsAdapter;
import com.foodapp.eatme.adapter.StepsAdapter;
import com.foodapp.eatme.listener.NutriListener;
import com.foodapp.eatme.listener.RecipeDetailsListener;
import com.foodapp.eatme.listener.StepsListener;
import com.foodapp.eatme.model.ModelComment;
import com.foodapp.eatme.model.RecipeDetailsResponse;
import com.foodapp.eatme.model.RecipeNutriResponse;
import com.foodapp.eatme.model.StepsApiResponse;
import com.foodapp.eatme.request.RequestManagerDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsActivityMain extends AppCompatActivity {
    int id;
    TextView textView_meal_name, textView_meal_source
            , textView_meal_summary, textView_nutri_calo,
            textView_nutri_protein,textView_nutri_fat,textView_nutri_carb,
            textView_step, textView_stepDetail,idd;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients;
    RecyclerView rcvComments;
    RecyclerView rcvSteps;
    RequestManagerDetails manager;
    List<ModelComment> listComment = new ArrayList<>();
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    IngredientInCircleAdapter ingredientInCircleAdapter;
    StepsAdapter stepsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail_main);
        findView();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManagerDetails(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        manager.getRecipeDetailsWithNutri(nutriListener,id);
        //manager.getRecipeStep(stepsListener,id);

        //bingComment();
        //manager.getRecipeDetailsWithNutri

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();
    }

    private void findView() {
        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source = findViewById(R.id.textView_meal_source);
        textView_meal_summary = findViewById(R.id.textView_meal_summary);
        imageView_meal_image = findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);
        textView_nutri_calo = findViewById(R.id.textView_nutri_calo);
        textView_nutri_protein = findViewById(R.id.textView_nutri_protein);
        textView_nutri_fat = findViewById(R.id.textView_nutri_fat);
        textView_nutri_carb = findViewById(R.id.textView_nutri_carb);
        rcvComments = findViewById(R.id.recyclecomment);
    //    textView_step = findViewById(R.id.step);
      //  textView_stepDetail = findViewById(R.id.step_Detail);
      // rcvSteps = findViewById(R.id.recycler_recipe_step);
       idd = findViewById(R.id.idd);


    }
//    public void bingComment(){
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        rcvComments.setLayoutManager(layoutManager);
//        listComment = new ArrayList<>();
//       // listComment.add(new ModelComment("1","Hay","30/1/2021 01:44 PM","1"));
//
//        rcvComments.setHasFixedSize(true);
//        rcvComments.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivityMain.this, LinearLayoutManager.VERTICAL, false));
//        AdapterComment adapterComment = new AdapterComment(RecipeDetailsActivityMain.this,listComment,"1","1");
//        rcvComments.setAdapter(adapterComment);
//    }
    public List getListComment() {
        List<ModelComment> l = new ArrayList<>();
       // l.add(new ModelComment("1","hay",null,null,null,null,null));
        return  l;
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);

            textView_meal_source.setText(response.sourceName);
            String ss = response.summary;
          //  ArrayList<Step> arr = response.analyzedInstructions;
            String sss = formatInstruction(response.instructions);
            String summary = formatSummary(ss);
            textView_meal_summary.setText(summary);
            idd.setText(response.instructions);
            Picasso.get().load(response.image).into(imageView_meal_image);
            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivityMain.this, LinearLayoutManager.VERTICAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivityMain.this, response.extendedIngredients);
            ingredientInCircleAdapter = new IngredientInCircleAdapter(RecipeDetailsActivityMain.this, response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientInCircleAdapter);

          //  recycler_meal_ingredients.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivityMain.this,message, Toast.LENGTH_SHORT).show();

        }
        public List<String> steps(String instruction) {

            List<String> stepss = new ArrayList<>();
            for (int i = 0; i < instruction.length(); i++) {
//                if(instruction.charAt(i)!= '<'|| instruction.charAt(i)!= '>' || instruction.charAt(i)!= '/'){
//                    if(i>8 && i < instruction.length()-10){
//                        if(instruction.charAt(i-1)=='>'&& instruction.charAt(i-2)=='i' && instruction.charAt(i-3)=='l'){
//
//                        }
//                    }
//                }
//                if (!(instruction.charAt(i)!= '<'|| instruction.charAt(i)!= '>' || instruction.charAt(i)!= '/' )) {
//                    if(i>)
//                }

            }
            return  stepss;
        }
        public String formatInstruction(String sum) {
            StringBuilder s = new StringBuilder();
            int id =0;
            int saveDotPosition = 0;
            for (int i = 0; i < sum.length(); i++) {

                if(sum.charAt(i) == '<' || sum.charAt(i) =='>' || sum.charAt(i) =='/'){
                    id= 2;
                }else if(i>0 && sum.charAt(i) == 'o') {
                    int j = i-1;
                    if(sum.charAt(j) == '<' || sum.charAt(j)=='/'){
                        id=2;
                    }else{

                        s.append(sum.charAt(i));
                        if(sum.charAt(i)=='.'){
                            saveDotPosition = s.length();
                        }
                    }
                }else if(i < sum.length()-4 && sum.charAt(i)=='a') {
                    int j = i + 2;
                    if(sum.charAt(j)=='h' && sum.charAt(j+1) =='r'){
                        break;
                    }else{
                        s.append(sum.charAt(i));
                        if(sum.charAt(i)=='.'){
                            saveDotPosition = s.length();
                        }
                    }

                }else if(i>2 && i<sum.length()-2 && sum.charAt(i)=='l'){
                    if(!((sum.charAt(i-1)=='<' || sum.charAt(i)=='/') && sum.charAt(i+1)=='i')){
                        s.append(sum.charAt(i));
                    }


                }else if(i>6 && i<sum.length()-6 && sum.charAt(i)=='i'){
                    if(!(sum.charAt(i-1)=='l'  && sum.charAt(i+1)=='>')){
                        s.append(sum.charAt(i));
                    }
                }
                else {
                    s.append(sum.charAt(i));
                    if(sum.charAt(i)=='.'){
                        saveDotPosition = s.length();
                    }
                }

            }

            String result = s.toString();
            return result;
        }
        public String formatSummary(String sum) {
            StringBuilder s = new StringBuilder();
            int id =0;
            int saveDotPosition = 0;
            for (int i = 0; i < sum.length(); i++) {

                if(sum.charAt(i) == '<' || sum.charAt(i) =='>' || sum.charAt(i) =='/'){
                    id= 2;
                }else if(i>0 && sum.charAt(i) == 'b') {
                            int j = i-1;
                            if(sum.charAt(j) == '<' || sum.charAt(j)=='/'){
                                id=2;
                            }else{

                                s.append(sum.charAt(i));
                                if(sum.charAt(i)=='.'){
                                    saveDotPosition = s.length();
                                }
                            }
                }else if(i < sum.length()-4 && sum.charAt(i)=='a') {
                    int j = i + 2;
                    if(sum.charAt(j)=='h' && sum.charAt(j+1) =='r'){
                        break;
                    }else{
                        s.append(sum.charAt(i));
                        if(sum.charAt(i)=='.'){
                            saveDotPosition = s.length();
                        }
                    }

                }
                else {
                        s.append(sum.charAt(i));
                    if(sum.charAt(i)=='.'){
                        saveDotPosition = s.length();
                    }
                    }

            }
            String x = String.valueOf(saveDotPosition);
            String r = s.toString();
            String result = r.substring(0, saveDotPosition);
            return result;
        }
    };
    private final NutriListener nutriListener = new NutriListener() {
        @Override
        public void didFetch(RecipeNutriResponse response, String message) {
            textView_nutri_calo.setText(response.calories);
            textView_nutri_carb.setText(response.carbs);
            textView_nutri_fat.setText(response.fat);
            textView_nutri_protein.setText(response.protein);


        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivityMain.this,message, Toast.LENGTH_SHORT).show();
        }
    };

    private final StepsListener stepsListener = new StepsListener() {
        @Override
        public void didFetch(StepsApiResponse response, String message) {

            textView_step.setText(response.name);

            textView_stepDetail.setText("response.steps.toString()");
            rcvSteps.setHasFixedSize(true);

            rcvSteps.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivityMain.this, LinearLayoutManager.VERTICAL,false));
            stepsAdapter = new StepsAdapter(RecipeDetailsActivityMain.this, response.steps);
            rcvSteps.setAdapter(stepsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivityMain.this,message, Toast.LENGTH_LONG).show();

        }
    };
}
